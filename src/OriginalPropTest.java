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
 * IN VERIFICATION MODE MUST SET THE isInstance FLAG MANNUALLY IN THE SOURCE CODE
 */

public class OriginalPropTest {

    public static String equivPropName = "matchEquiv";
    public static String loosePropName = "loose";
    public static String tightPropName = "tight";
    public static String tautologyPropName = "tautology";

    public static boolean debug = true;
    public static boolean mac = false;

    public static String directory;
    public static String benchmark;

    /**
     * Takes three arguments, the file that we will analyze the properties in it, the benchmark name, and property name we were repairing, for example
     * Body/Infusion_Prop1_body infusion p1
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        directory = args[0]; //for example props/Exp1_5M_30min_100Rand/
        benchmark = args[1];
        boolean verification = Boolean.parseBoolean(args[2]);

        if (verification) {
            Boolean isInstance = true;

            if (benchmark.equals("gpca")) {
                String fileName = isInstance ? "Body/Instance/alarm152.lus" : "Body/Static/alarm152.lus";
                String fileNameCombined = isInstance ? "Body/Instance/alarm152_combined.lus" : "Body/Static/alarm152_combined.lus";
                execute(directory + fileName, benchmark, "p1");
                execute(directory + fileName, benchmark, "p2");
                execute(directory + fileName, benchmark, "p3");
                execute(directory + fileName, benchmark, "p4");
                execute(directory + fileName, benchmark, "p5");
                execute(directory + fileName, benchmark, "p6");
                execute(directory + fileName, benchmark, "p7");
                execute(directory + fileName, benchmark, "p8");
                execute(directory + fileName, benchmark, "p9");
                execute(directory + fileName, benchmark, "p10");

                execute(directory + fileNameCombined, benchmark, "p1");
                execute(directory + fileNameCombined, benchmark, "p2");
                execute(directory + fileNameCombined, benchmark, "p3");
                execute(directory + fileNameCombined, benchmark, "p4");
                execute(directory + fileNameCombined, benchmark, "p5");
                execute(directory + fileNameCombined, benchmark, "p6");
                execute(directory + fileNameCombined, benchmark, "p7");
                execute(directory + fileNameCombined, benchmark, "p8");
                execute(directory + fileNameCombined, benchmark, "p9");
                execute(directory + fileNameCombined, benchmark, "p10");
            } else if (benchmark.equals("infusion")) {
                String fileName = isInstance ? "Body/Instance/infusion5.lus" : "Body/Static/infusion5.lus";
                String fileNameCombined = isInstance ? "Body/Instance/infusion5_combined.lus" : "Body/Static/infusion5_combined.lus";
                execute(directory + fileName, benchmark, "p1");
                execute(directory + fileName, benchmark, "p2");
                execute(directory + fileName, benchmark, "p3");
                execute(directory + fileName, benchmark, "p4");
                execute(directory + fileName, benchmark, "p5");
                execute(directory + fileName, benchmark, "p6");
                execute(directory + fileName, benchmark, "p7");
                execute(directory + fileName, benchmark, "p8");
                execute(directory + fileName, benchmark, "p9");
                execute(directory + fileName, benchmark, "p10");
                execute(directory + fileName, benchmark, "p11");
                execute(directory + fileName, benchmark, "p12");
                execute(directory + fileName, benchmark, "p13");
                execute(directory + fileName, benchmark, "p14");

                //passing the combined invariants from daikon that has passed the verification.
                execute(directory + fileNameCombined, benchmark, "p1");
                execute(directory + fileNameCombined, benchmark, "p2");
                execute(directory + fileNameCombined, benchmark, "p3");
                execute(directory + fileNameCombined, benchmark, "p4");
                execute(directory + fileNameCombined, benchmark, "p5");
                execute(directory + fileNameCombined, benchmark, "p6");
                execute(directory + fileNameCombined, benchmark, "p7");
                execute(directory + fileNameCombined, benchmark, "p8");
                execute(directory + fileNameCombined, benchmark, "p9");
                execute(directory + fileNameCombined, benchmark, "p10");
                execute(directory + fileNameCombined, benchmark, "p11");
                execute(directory + fileNameCombined, benchmark, "p12");
                execute(directory + fileNameCombined, benchmark, "p13");
                execute(directory + fileNameCombined, benchmark, "p14");
            } else if (benchmark.equals("targetedInfusion")) {
                benchmark="infusion";
                String fileName = isInstance ? "Body/Instance/infusion_targetedp9.lus" : "Body/Static/infusion_targetedp9.lus";
                execute(directory + fileName, benchmark, "p9");
            } else if (benchmark.equals("tcas")) {
                String fileName = isInstance ? "Body/Instance/tcas2.lus" : "Body/Static/tcas2.lus";
                String fileNameCombined = isInstance ? "Body/Instance/tcas2_combined.lus" : "Body/Static/tcas2_combined.lus";
                //regurlar one-by-one invariants checking happening here
                execute(directory + fileName, benchmark, "p1");
                execute(directory + fileName, benchmark, "p2");
                execute(directory + fileName, benchmark, "p4");

                //passing the combined invariants from daikon that has passed the verification.
                execute(directory + fileNameCombined, benchmark, "p1");
                execute(directory + fileNameCombined, benchmark, "p2");
                execute(directory + fileNameCombined, benchmark, "p4");
            } else if (benchmark.equals("wbs")) {
                String fileName = isInstance ? "Body/Instance/wbs5.lus" : "Body/Static/wbs5.lus";
                String fileNameCombined = isInstance ? "Body/Instance/wbs5_combined.lus" : "Body/Static/wbs5_combined.lus";
                // regurlar one-by-one invariants checking happening here
                execute(directory + fileName, benchmark, "p1");
                execute(directory + fileName, benchmark, "p3");

                //passing the combined invariants from daikon that has passed the verification.
                execute(directory + fileNameCombined, benchmark, "p1");
                execute(directory + fileNameCombined, benchmark, "p3");
            } else assert false;
        } else if (benchmark.equals("gpca")) {

            execute(directory + "Body/gpca_prop1.lus", benchmark, "p1");
            execute(directory + "Body/gpca_prop2.lus", benchmark, "p2");
            execute(directory + "Body/gpca_prop3.lus", benchmark, "p3");
            execute(directory + "Body/gpca_prop4.lus", benchmark, "p4");
            execute(directory + "Body/gpca_prop5.lus", benchmark, "p5");
            execute(directory + "Body/gpca_prop6.lus", benchmark, "p6");
            execute(directory + "Body/gpca_prop7.lus", benchmark, "p7");
            execute(directory + "Body/gpca_prop8.lus", benchmark, "p8");
            execute(directory + "Body/gpca_prop9.lus", benchmark, "p9");
            execute(directory + "Body/gpca_prop10.lus", benchmark, "p10");
        } else if (benchmark.equals("infusion")) {

            execute(directory + "Body/infusion_prop1.lus", benchmark, "p1");
            execute(directory + "Body/infusion_prop2.lus", benchmark, "p2");
            execute(directory + "Body/infusion_prop3.lus", benchmark, "p3");
            execute(directory + "Body/infusion_prop4.lus", benchmark, "p4");
            execute(directory + "Body/infusion_prop5.lus", benchmark, "p5");
            execute(directory + "Body/infusion_prop6.lus", benchmark, "p6");
            execute(directory + "Body/infusion_prop7.lus", benchmark, "p7");
            execute(directory + "Body/infusion_prop8.lus", benchmark, "p8");
            execute(directory + "Body/infusion_prop9.lus", benchmark, "p9");
            execute(directory + "Body/infusion_prop10.lus", benchmark, "p10");
            execute(directory + "Body/infusion_prop11.lus", benchmark, "p11");
            execute(directory + "Body/infusion_prop12.lus", benchmark, "p12");
            execute(directory + "Body/infusion_prop13.lus", benchmark, "p13");
            execute(directory + "Body/infusion_prop14.lus", benchmark, "p14");

        } else if (benchmark.equals("tcas")) {

            execute(directory + "Body/tcas_prop1.lus", benchmark, "p1");
            execute(directory + "Body/tcas_prop2.lus", benchmark, "p2");
            execute(directory + "Body/tcas_prop4.lus", benchmark, "p4");

        } else if (benchmark.equals("wbs")) {

            execute(directory + "Body/wbs_prop1.lus", benchmark, "p1");
            execute(directory + "Body/wbs_prop3.lus", benchmark, "p3");
        } else assert false;
        PropRelationStatManager.writeOrigRelationToFile();
        PropRelationStatManager.writeOtherOrigRelationToFile();
        PropRelationStatManager.writeTautologyDetails();
    }


    public static void execute(String fName, String benchmark, String origPropName) throws IOException {
//existingPropsEndIndex, is inclusive

        Integer lastIndexOfOrigProps = -1;

        if (benchmark.equals("infusion")) //estimated last index of only valid-original properties.
            lastIndexOfOrigProps = 13;
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
        System.out.println("Computing benchmark:" + benchmark + " prop: " + originalPropName);

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
            if (res.getPropertyResult(tautologyPropName).getStatus() == Status.VALID) {
                ++relationResult.tautologyCount;
                relationResult.tautProps.add(repairedPropName.toString());
            } else if (res.getPropertyResult(equivPropName).getStatus() == Status.VALID) {
                ++relationResult.equivCount;
                relationResult.equivProps.add(repairedPropName.toString());
            } else if (res.getPropertyResult(loosePropName).getStatus() == Status.VALID) {
                ++relationResult.looseCount;
                relationResult.looseProps.add(repairedPropName.toString());
            } else if (res.getPropertyResult(tightPropName).getStatus() == Status.VALID) {
                ++relationResult.tightCount;
                relationResult.tightProps.add(repairedPropName.toString());
            } else {
                ++relationResult.incomparableCount;
                relationResult.inComparableProps.add(repairedPropName.toString());
            }
        }
        if (debug) {
            System.out.println("tautology props are:" + relationResult.tautProps);
            System.out.println("loose props are:" + relationResult.looseProps);
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

//        System.out.println("tight# to " + otherOrigPropName + "=" + relationResult.tightCount);

//        System.out.println("tightProps are:" + relationResult.tightProps);
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

        if (mac)
            file1 = new File("/Users/sohahussein/git/ProcessRepair/" + fileName);
        else// assuming linux
            file1 = new File("/home/soha/git/ProcessRepair/" + fileName);
        JKindApi api = new JKindApi();
        JKindResult result = new JKindResult("");

        api.setJKindJar("../../jkindNoRand/jkind.jar");

        api.execute(file1, result, new NullProgressMonitor());

        return result;
    }

}
