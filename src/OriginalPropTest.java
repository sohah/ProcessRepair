import jkind.api.JKindApi;
import jkind.api.results.JKindResult;
import jkind.api.results.Status;
import jkind.lustre.*;
import jkind.lustre.parsing.LustreParseUtil;
import org.eclipse.core.runtime.NullProgressMonitor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class checks if the first property is either implied
 */

public class OriginalPropTest {

    public static String equivPropName = "matchEquiv";
    public static String loosePropName = "loose";
    public static String tightPropName = "tight";
    public static String tautologyPropName = "tautology";

    public static boolean debug = false;

    public static String directory;

    /**
     * Takes three arguments, the file that we will analyze the properties in it, the benchmark name, and property name we were repairing, for example
     * Body/Infusion_Prop1_body infusion p1
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        directory = args[0]; //for example props/Exp1_5M_30min_100Rand/

        execute(directory + "Body/gpca_prop1.lus", "gpca", "p1");
        execute(directory + "Body/gpca_prop2.lus", "gpca", "p2");
        execute(directory + "Body/gpca_prop3.lus", "gpca", "p3");
        execute("Body/gpca_prop4.lus", "gpca", "p4");
        execute("Body/gpca_prop5.lus", "gpca", "p5");
        execute("Body/gpca_prop6.lus", "gpca", "p6");
        execute("Body/gpca_prop7.lus", "gpca", "p7");
        execute("Body/gpca_prop8.lus", "gpca", "p8");
        execute("Body/gpca_prop9.lus", "gpca", "p9");


        execute("Body/infusion_prop1.lus", "infusion", "p1");
        execute("Body/infusion_prop2.lus", "infusion", "p2");
        execute("Body/infusion_prop3.lus", "infusion", "p3");
        execute("Body/infusion_prop5.lus", "infusion", "p5");
        execute("Body/infusion_prop6.lus", "infusion", "p6");
        execute("Body/infusion_prop7.lus", "infusion", "p7");
        execute("Body/infusion_prop8.lus", "infusion", "p8");
        execute("Body/infusion_prop9.lus", "infusion", "p9");
        execute("Body/infusion_prop10.lus", "infusion", "p10");
        execute("Body/infusion_prop11.lus", "infusion", "p11");
        execute("Body/infusion_prop12.lus", "infusion", "p12");
        execute("Body/infusion_prop13.lus", "infusion", "p13");

        execute("Body/tcas_Prop1_body", "tcas", "p1");
        execute("Body/tcas_Prop2_body", "tcas", "p2");
        execute("Body/tcas_Prop4_body", "tcas", "p4");

        execute("Body/wbs_Prop1_body", "wbs", "p1");
        execute("Body/wbs_Prop3_body", "wbs", "p3");

        PropRelationStatManager.writeOrigRelationToFile();
        PropRelationStatManager.writeOtherOrigRelationToFile();

    }


    public static void execute(String fName, String benchmark, String origPropName) throws IOException {
//existingPropsEndIndex, is inclusive

        Integer lastIndexOfOrigProps = -1;

        if (benchmark.equals("infusion")) //estimated last index of only valid-original properties.
            lastIndexOfOrigProps = 11;
        else if (benchmark.equals("gpca"))
            lastIndexOfOrigProps = 9;
        else if (benchmark.equals("tcas"))
            lastIndexOfOrigProps = 2;
        else if (benchmark.equals("wbs"))
            lastIndexOfOrigProps = 1;
        else assert false;


        PropRelationStatManager.create();
        File fileName = new File(fName);

        String jkindQueryFileName = fName + "_jkindQuery";

        Path path = Paths.get(fileName.toString());
        if (!Files.exists(path)) { // prop was not executed in the run.
            System.out.println("prop file not found, unexpected. aborting");
            assert false;
        }
        Program pgm = LustreParseUtil.program(new String(Files.readAllBytes(Paths.get(fileName.toString())), "UTF-8"));

        if (pgm.getMainNode().equations.size() == lastIndexOfOrigProps + 1) { //no repair was found
            PropRelationStatManager.addEmptyOrig(benchmark, origPropName);
            PropRelationStatManager.addEmptyOtherOrig(benchmark, origPropName);
            return;
        }

        OrigPropRelationResult relationToOrig = computeRelationToOriginalProp(benchmark, origPropName, lastIndexOfOrigProps + 1, jkindQueryFileName, pgm);
        PropRelationStatManager.addOrigRelation(relationToOrig);


        List<Equation> equations = pgm.getMainNode().equations;

        for (int i = 0; i <= lastIndexOfOrigProps; i++) {
            String otherOrigPropName = equations.get(i).lhs.get(0).id;

            if (!otherOrigPropName.equals(origPropName)) {
                OtherPropRelationResult relationToOtherOrig = computeRelationToOtherProp(benchmark, origPropName, otherOrigPropName, lastIndexOfOrigProps + 1, jkindQueryFileName, pgm);
                PropRelationStatManager.addOtherOrigRelation(benchmark, origPropName, relationToOtherOrig);
            } else {
                PropRelationStatManager.addOtherOrigRelation(benchmark, origPropName, relationToOrig.makeOtherPropRelationResult());
            }
        }

    }


    public static OrigPropRelationResult computeRelationToOriginalProp(String benchmark, String originalPropName, int repairsStartIndex, String jkindQueryFileName, Program pgm) throws IOException {
        OrigPropRelationResult relationResult = new OrigPropRelationResult(benchmark, originalPropName);

        List<Equation> equations = pgm.getMainNode().equations;

        for (int j = repairsStartIndex; j < equations.size(); j++) {
            IdExpr repairedPropName = equations.get(j).lhs.get(0);
            Node newMain = updateProp(pgm.getMainNode(), originalPropName, repairedPropName);
            Program newPgm = replaceMain(pgm, newMain);
            JKindResult res;
            if (debug) {
                writeToFile(jkindQueryFileName + j, newPgm.toString());
                res = callJkind(jkindQueryFileName + j);
            } else {
                writeToFile(jkindQueryFileName, newPgm.toString());
                res = callJkind(jkindQueryFileName);
            }
            if (res.getPropertyResult(tautologyPropName).getStatus() == Status.VALID) ++relationResult.tautologyCount;
            else if (res.getPropertyResult(equivPropName).getStatus() == Status.VALID) {
                ++relationResult.equivCount;
                relationResult.equivProps.add("p" + j);
            } else if (res.getPropertyResult(loosePropName).getStatus() == Status.VALID) ++relationResult.looseCount;
            else if (res.getPropertyResult(tightPropName).getStatus() == Status.VALID) {
                ++relationResult.tightCount;
                relationResult.tightProps.add("p" + j);
            } else {
                ++relationResult.incomparableCount;
                relationResult.inComparableProps.add("p" + j);
            }
        }


        /*System.out.println("OriginalPropName,      tautology,      equiv#,      loose#,      tight#,      incomparable#");
        System.out.println(originalPropName + ",      " + relationResult.tautologyCount + ",      " + relationResult.equivCount + ",      " + relationResult.looseCount + ",      " + relationResult.tightCount + ",      " + relationResult.incomparableCount);

        System.out.println("tightProps are:" + relationResult.tightProps);
        System.out.println("equivProps are:" + relationResult.equivProps);
        System.out.println("inComparable Props are:" + relationResult.inComparableProps);*/
        return relationResult;
    }


    /**
     * takes the origPropName that we were trying to repair, otherOrigPropName which are the already existing props we knew about the benchmark and we want to compare to
     * repairStartIndex, the start index of the repaired props
     *
     * @param otherOrigPropName
     * @param repairsStartIndex
     * @return
     * @throws IOException
     */
    public static OtherPropRelationResult computeRelationToOtherProp(String benchmark, String origProp, String otherOrigPropName, int repairsStartIndex, String jkindQueryFileName, Program pgm) throws IOException {
        OtherPropRelationResult relationResult = new OtherPropRelationResult(benchmark, origProp, otherOrigPropName);

        List<Equation> equations = pgm.getMainNode().equations;

        for (int j = repairsStartIndex; j < equations.size(); j++) {
            IdExpr repairedPropName = equations.get(j).lhs.get(0);
            Node newMain = updatePropForOther(pgm.getMainNode(), otherOrigPropName, repairedPropName);
            Program newPgm = replaceMain(pgm, newMain);
            JKindResult res;
            if (debug) {
                writeToFile(jkindQueryFileName + "_" + otherOrigPropName + j, newPgm.toString());
                res = callJkind(jkindQueryFileName + "_" + otherOrigPropName + j);
            } else {
                writeToFile(jkindQueryFileName, newPgm.toString());
                res = callJkind(jkindQueryFileName);
            }
            if (res.getPropertyResult(tightPropName).getStatus() == Status.VALID) {
                ++relationResult.tightCount;
                relationResult.tightProps.add("p" + j);
            }
        }

        System.out.println("tight# to " + otherOrigPropName + "=" + relationResult.tightCount);

        System.out.println("tightProps are:" + relationResult.tightProps);
        return relationResult;
    }

    private static Program replaceMain(Program pgm, Node newMain) {
        ArrayList<Node> newNodes = new ArrayList<Node>();
        newNodes.add(newMain);
        for (Node node : pgm.nodes) {
            if (!node.id.equals(newMain.id)) {
                newNodes.add(node);
            }
        }
        return new Program(pgm.location, pgm.types, pgm.constants, pgm.functions, newNodes, pgm.main);
    }

    private static Node updateProp(Node mainNode, String originalPropName, IdExpr repairedProp) {
        List<VarDecl> localVars = new ArrayList();
        localVars.addAll(mainNode.locals);
        localVars.add(new VarDecl(equivPropName, NamedType.BOOL));
        localVars.add(new VarDecl(loosePropName, NamedType.BOOL));
        localVars.add(new VarDecl(tightPropName, NamedType.BOOL));
        localVars.add(new VarDecl(tautologyPropName, NamedType.BOOL));

        Equation propEquiv = new Equation(new IdExpr(equivPropName), new BinaryExpr(new IdExpr(originalPropName), BinaryOp.EQUAL, repairedProp));
        Equation propLoose = new Equation(new IdExpr(loosePropName), new BinaryExpr(new IdExpr(originalPropName), BinaryOp.IMPLIES, repairedProp));
        Equation propTight = new Equation(new IdExpr(tightPropName), new BinaryExpr(repairedProp, BinaryOp.IMPLIES, new IdExpr(originalPropName)));
        Equation propTaut = new Equation(new IdExpr(tautologyPropName), new BinaryExpr(repairedProp, BinaryOp.EQUAL, new BoolExpr(true)));

        List<Equation> newEquations = new ArrayList<>();
        newEquations.addAll(mainNode.equations);

        newEquations.add(propEquiv);
        newEquations.add(propLoose);
        newEquations.add(propTight);
        newEquations.add(propTaut);

        List<String> newProperties = new ArrayList<>();
        newProperties.add(equivPropName);
        newProperties.add(loosePropName);
        newProperties.add(tightPropName);
        newProperties.add(tautologyPropName);


        return new Node(mainNode.id, mainNode.inputs, mainNode.outputs, localVars, newEquations, newProperties, mainNode.assertions, mainNode.realizabilityInputs, mainNode.contract, mainNode.ivc);
    }

    private static Node updatePropForOther(Node mainNode, String OtherOrigPropName, IdExpr repairedProp) {
        List<VarDecl> localVars = new ArrayList();
        localVars.addAll(mainNode.locals);
        localVars.add(new VarDecl(tightPropName, NamedType.BOOL));

        Equation propTight = new Equation(new IdExpr(tightPropName), new BinaryExpr(repairedProp, BinaryOp.IMPLIES, new IdExpr(OtherOrigPropName)));

        List<Equation> newEquations = new ArrayList<>();
        newEquations.addAll(mainNode.equations);

        newEquations.add(propTight);

        List<String> newProperties = new ArrayList<>();
        newProperties.add(tightPropName);

        return new Node(mainNode.id, mainNode.inputs, mainNode.outputs, localVars, newEquations, newProperties, mainNode.assertions, mainNode.realizabilityInputs, mainNode.contract, mainNode.ivc);
    }

    public static boolean writeToFile(String fileName, String content) {
        String directory;

//        directory = OriginalPropTest.directory;

//        fileName = directory + fileName;

        try {

//            if (!Files.exists(Paths.get(directory))) Files.createDirectories(Paths.get(directory));
            if (Files.exists(Paths.get(fileName))) {
                Files.delete(Paths.get(fileName));
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            writer.write(content);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to write to file!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("unable to write to file!");
            e.printStackTrace();
        }
        return true;
    }

    public static JKindResult callJkind(String fileName) {
        File file1;

        file1 = new File("/Users/sohahussein/git/ProcessRepair/" + fileName);
        JKindApi api = new JKindApi();
        JKindResult result = new JKindResult("");

        api.setJKindJar("../../jkindNoRand/jkind.jar");

        api.execute(file1, result, new NullProgressMonitor());

        return result;
    }

}
