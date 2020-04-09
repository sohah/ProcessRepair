import jkind.api.JKindApi;
import jkind.api.results.JKindResult;
import jkind.api.results.Status;
import jkind.lustre.*;
import jkind.lustre.parsing.LustreParseUtil;
import org.eclipse.core.runtime.NullProgressMonitor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class checks if the first property is either implied
 */

public class OriginalPropTest {

    public static String equivPropName = "matchEquiv";
    public static String loosePropName = "looseEquiv";
    public static String tightPropName = "tightEquiv";

    public static void main(String[] args) {


    }


    public static void execute() throws IOException {

        int equivCount = 0;
        int looseCount = 0;
        int tightCount = 0;
        int incomparableCount = 0;

        File folder = new File("props");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String currentFileName = listOfFiles[i].getName();
                Program pgm = LustreParseUtil.program(new String(Files.readAllBytes(Paths.get(currentFileName)), "UTF-8"));
                List<Equation> equations = pgm.getMainNode().equations;

                Equation originalProp = equations.get(0);
                assert (originalProp.lhs.equals("p0")); // we are assuming that all properties must obey the naming of pn where n starts from 0 until the number of the properties.
                for (int j = 1; j < equations.size(); j++) {
                    Node newMain = updateProp(pgm.getMainNode(), j);
                    Program newPgm = replaceMain(pgm, newMain);
                    writeToFile(currentFileName, newPgm.toString());
                    JKindResult res = callJkind(currentFileName);
                    if (res.getPropertyResult(equivPropName).getStatus() == Status.VALID)
                        ++equivCount;
                    if (res.getPropertyResult(loosePropName).getStatus() == Status.VALID)
                        ++looseCount;
                    if (res.getPropertyResult(tightPropName).getStatus() == Status.VALID)
                        ++tightCount;

                    if (!((res.getPropertyResult(equivPropName).getStatus() == Status.VALID) || (res.getPropertyResult(loosePropName).getStatus() == Status.VALID) || (res.getPropertyResult(tightPropName).getStatus() == Status.VALID)))
                        ++incomparableCount;
                }
                System.out.println("PropName,      equiv#,      loose#,      tight#,      incomparable#");
                System.out.println(currentFileName + ",      " + equivCount + ",      " + looseCount + ",      " + tightCount + ",      " + incomparableCount);
            }
        }
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

    private static Node updateProp(Node mainNode, int equationIndex) {
        List<VarDecl> localVars = mainNode.locals;

        localVars.add(new VarDecl(equivPropName, NamedType.BOOL));
        localVars.add(new VarDecl(loosePropName, NamedType.BOOL));
        localVars.add(new VarDecl(tightPropName, NamedType.BOOL));

        Equation propEquiv = new Equation(new IdExpr(equivPropName), new BinaryExpr(new IdExpr("p0"), BinaryOp.EQUAL, new IdExpr("p" + equationIndex)));
        Equation propLoose = new Equation(new IdExpr(loosePropName), new BinaryExpr(new IdExpr("p" + equationIndex), BinaryOp.IMPLIES, new IdExpr("p0")));
        Equation propTight = new Equation(new IdExpr(tightPropName), new BinaryExpr(new IdExpr("p0"), BinaryOp.IMPLIES, new IdExpr("p" + equationIndex)));

        List<Equation> newEquations = mainNode.equations;
        newEquations.add(propEquiv);
        newEquations.add(propLoose);
        newEquations.add(propTight);

        List<String> newProperties = new ArrayList<>();
        newProperties.add(equivPropName);
        newProperties.add(loosePropName);
        newProperties.add(tightPropName);


        return new Node(mainNode.id, mainNode.inputs, mainNode.outputs, localVars, newEquations, newProperties, mainNode.assertions, mainNode.realizabilityInputs, mainNode.contract, mainNode.ivc);
    }


    public static boolean writeToFile(String fileName, String content) {
        String directory;

        directory = "props/";

        fileName = directory + fileName;

        try {

            if (!Files.exists(Paths.get(directory)))
                Files.createDirectories(Paths.get(directory));

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

        file1 = new File("../props/" + fileName);
        JKindApi api = new JKindApi();
        JKindResult result = new JKindResult("");

        api.execute(file1, result, new NullProgressMonitor());

        return result;
    }

}
