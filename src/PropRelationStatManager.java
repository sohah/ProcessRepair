import javafx.util.Pair;

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

        Path file = Paths.get("OrigPropRelation_all_stats" + ".txt");
        List lines = new ArrayList<>();

        for (OrigPropRelationResult origPropRelationResult : origPropRelation) {
            lines.add(origPropRelationResult.toString());
        }

        try {
            Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("problem writing to origPropRelation_All_stats file");
            assert false;
        }
    }


    public static void writeOtherOrigRelationToFile() {

        Path file = Paths.get("OtherOrigPropRelation_all_stats" + ".txt");
        List lines = new ArrayList<>();

        Set<Pair<String, String>> keys = otherPropRelation.keySet();

        String header = "";
        for (Pair key : keys) {
            ArrayList<OtherPropRelationResult> aRow = otherPropRelation.get(key);
            for (int i = 0; i <= aRow.size(); i++) {  //making header
                header += aRow.get(i).otherOrigProp + ",         ";
            }
            lines.add(header);

            String rowValue = key.toString();
            for (int i = 0; i <= aRow.size(); i++) {
                rowValue += aRow.get(i).tightCount + ",         ";
            }
            lines.add(rowValue);
        }


        try {
            Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("problem writing to origPropRelation_All_stats file");
            assert false;
        }
    }

}
