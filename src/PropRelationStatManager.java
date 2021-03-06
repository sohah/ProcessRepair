

import InputOutput.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class PropRelationStatManager {

    private static List<OrigPropRelationResult> origPropRelation = new ArrayList<>();

    private static LinkedHashMap<Pair<String, String>, ArrayList<OtherPropRelationResult>> otherPropRelation = new LinkedHashMap<>();
    private static Path origFile;
    private static Path otherOrigFile;
    private static Path tautologyDetailsFile;

    public static void create() throws IOException {
        origFile = Paths.get(OriginalPropTest.directory + OriginalPropTest.benchmark + "OrigPropRelation_all_stats" + ".txt");
        otherOrigFile = Paths.get(OriginalPropTest.directory + OriginalPropTest.benchmark + "OtherOrigPropRelation_all_stats" + ".txt");
        tautologyDetailsFile = Paths.get(OriginalPropTest.directory + OriginalPropTest.benchmark + "TautologyDetails_all_stats" + ".txt");
        Files.write(origFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(otherOrigFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(tautologyDetailsFile, new ArrayList<>(), StandardCharsets.UTF_8);
    }

    public static void addOrigRelation(OrigPropRelationResult origPropRelationResult) {
        origPropRelation.add(origPropRelationResult);
    }

    public static void addOtherOrigRelation(String benchmark, String origProp, OtherPropRelationResult otherPropRelationResult) {
        Pair<String, String> key = new Pair<String, String>(benchmark, origProp);
        ArrayList<OtherPropRelationResult> otherOrigRelations = otherPropRelation.get(key);
        if (otherOrigRelations == null) {
            ArrayList<OtherPropRelationResult> otherOrigList = new ArrayList();
            otherOrigList.add(otherPropRelationResult);
            otherPropRelation.put(key, otherOrigList);
        } else {
            otherOrigRelations.add(otherPropRelationResult);
        }
    }

    public static void writeOrigRelationToFile() {


        List lines = new ArrayList<String>();

        lines.add("bench,      OriginalPropName,      tautology,      equiv#,      loose#,      tight#,      incomparable#\n");

        for (OrigPropRelationResult origPropRelationResult : origPropRelation) {
            lines.add(origPropRelationResult.toString());
        }

        try {
            Files.write(origFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("problem writing to origPropRelation_All_stats file");
            assert false;
        }
    }


    public static void writeOtherOrigRelationToFile() {


        Set<Pair<String, String>> keys = otherPropRelation.keySet();


        for (Pair key : keys) {
            List lines = new ArrayList<>();
            ArrayList<OtherPropRelationResult> aRow = otherPropRelation.get(key);
            String header = "         ,";
            for (int i = 0; i < aRow.size(); i++) {  //making header
                header += aRow.get(i).otherOrigProp + ",         ";
            }
            lines.add(header);

            String rowValue = key.toString() + ",          ";
            for (int i = 0; i < aRow.size(); i++) {
                rowValue += aRow.get(i).tightCount + ",         ";
            }
            lines.add(rowValue);

            try {
                Files.write(otherOrigFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("problem writing to origPropRelation_All_stats file");
                assert false;
            }
        }

    }

    public static void addEmptyOrig(String benchmark, String origPropName) {
        origPropRelation.add(new OrigPropRelationResult(benchmark, origPropName));
    }

    public static void addEmptyOtherOrig(String benchmark, String origPropName) {
        otherPropRelation.put(new Pair<>(benchmark, origPropName), new ArrayList<>());
    }

    public static void writeTautologyDetails() {

        List lines = new ArrayList();


        for (OrigPropRelationResult origPropRelationResult : origPropRelation) {
            lines.add(origPropRelationResult.tautologyToString());
        }

        try {
            Files.write(tautologyDetailsFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("problem writing to tautology details file");
            assert false;
        }
    }
}
