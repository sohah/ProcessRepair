/*
This class is used to split the statistics file based on properties listed, such that we loop over the statistics
file and divide it by its matching or not over the taut props. This should be called where the first input is the name of the
statistics files for the prop, and the second input is the benchmark, and finally the third arguement is the prop we are interested in.

IMPORTANT: to run this correctly one has to manually populate the tautProp according to the tautology properties obtained from the expirement.
* */

import jkind.api.JKindApi;
import jkind.api.results.JKindResult;
import jkind.api.results.Status;
import org.eclipse.core.runtime.NullProgressMonitor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SplitStatsForTaut {
    //tautology props, that we have not confirmed yet that they cant be tightened using unbounded check
    public static List<String> tautProp = new ArrayList<>();

    //tautology props, that we have CONFIRMED that they cant be tightened using unbounded check
    public static List<String> validTautProp = new ArrayList<>();

    public static String directory; // passed as input and it is the parent directory of the repair process for the expirement
    public static String benchmark; // passed as an input
    public static String prop; //passed as an input
    public static String mutantExistsDirectory; // this is also passed as input, and it directs to the directory of the mutant expirement, where we can find the last exists query

    public static String fileName;
    private static Path tautStatFile;
    private static Path noTautStatFile;

    private static Path tautOtherStatFile; //holds the split enteries where the last property is a taut, but the reason for the termination was not because of TRUE_FOR_MAX_STEPS

    //this data structure needs to maintain the insertion order.// I have split this map into two list, just in case there was a collision among the names of mutants due to the random selection. With the list DS we will have the right order and will have the duplicate mutant names as well
    //private static LinkedHashMap<String, String> tautMutantsToProp = new LinkedHashMap<>();
    private static List<String> tautMutantList = new ArrayList<>();
    private static List<String> tautPropList = new ArrayList<>();


    //hashset that holds the enteries for tautology stats that were not because of TRUE_FOR_MAX_STEPS
    static HashSet<String> tautForOtherReasons = new HashSet<>();

    static int timeOut = 900;

//    static int unknownCount = 0;

    public static void filterStatisticsFile() throws IOException {


        setupDataStructure();

        //find the mutant names that corresponds to the initially tautology props.
        fillTautMutantsToPropMap();

        //run unbounded mode for the last exists query for all mutants that were tautology, has the side effect of populating validTautProp with valid props
        runUnboundedVerification();

        splitStatFileToTaut();
//        System.out.println(benchmark +" "+ prop +" -- number of unknown taut prop = " + unknownCount);
    }

    //run unbounded verification for the last exists query for each mutant in tautMutantToProp map, and populates validTautProp with the valid ones. Those are later used for spliting the stat file
    private static void runUnboundedVerification() {
        // mutantExistsDirectory must be something that has the exiprement like /media/soha/DATA/MultiMutationExpr/1Mutation/ranger-discovery/src/DiscoveryExamples/WBS/

/*
        for (Map.Entry<String, String> mutantProp : tautMutantsToProp.entrySet()) {
            String mutantName = mutantProp.getKey();
*/
        for (int i = 0; i < tautMutantList.size(); i++) {
            String mutantName = tautMutantList.get(i);
            String propWithFirstLetterCapitablized = prop.substring(0, 1).toUpperCase() + prop.substring(1);

            String exitsFileName = mutantExistsDirectory.concat("/" + propWithFirstLetterCapitablized + "/output/" + mutantName + "/minimal/" + mutantName + "_exists.lus");
            exitsFileName = exitsFileName.replaceAll(" ", "");
            JKindResult jkindResutl = callJkind(exitsFileName);

            System.out.println("running existsFileName = " + exitsFileName);
            System.out.println("Query Result = " + jkindResutl.getPropertyResult("fail").getStatus().toString());

            if (jkindResutl.getPropertyResult("fail").getStatus() == Status.VALID)
//                validTautProp.add(mutantProp.getValue());
                validTautProp.add(tautPropList.get(i));
        }
    }

    //the output of this method is a pair of the matching mutant name with its prop, this is populated in tautMutantsToProp
    public static void fillTautMutantsToPropMap() throws IOException {

        // Open the file
        FileInputStream fstream = new FileInputStream(directory + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        int tautPropIndex = 0;

        String currTautProp = tautProp.get(tautPropIndex);

        //Read File Line By Line -- and get the mutants names along with their properties for those that are tautology and failed because of TRUE_FOR_MAX_STEPS
        while ((strLine = br.readLine()) != null && tautPropIndex < tautProp.size()) {
            if (strLine.contains(tautProp.get(tautPropIndex)) && strLine.contains("TRUE_FOR_MAX_STEPS")) {//if match then we need to write it to the taut file

                //get the name of the mutant to later run its last exists query in an unbounded mode.
                String[] statsEntry = strLine.split(",");
                for (int i = 0; i < statsEntry.length; i++)
                    if (statsEntry[i].contains("ROR") || statsEntry[i].contains("LOR")) {
                        tautMutantList.add(statsEntry[i]);
                        tautPropList.add(tautProp.get(tautPropIndex));
                        tautPropIndex++;
//                            tautMutantsToProp.put(statsEntry[i], currTautProp);
                        break;
                    }
            } else if (strLine.contains(tautProp.get(tautPropIndex))) { // there is another reason for the failure, then log that seperately
                tautForOtherReasons.add(strLine);
                tautPropIndex++;
//                    System.out.println(currTautProp + " is taut for another reason than TRUE_FOR_MAX_STEPS");
            }
        }

        //Close the input stream

        fstream.close();
    }

    public static void splitStatFileToTaut() throws IOException {

        if (!validTautProp.isEmpty())
            System.out.println("No valid taut prop to split on. It might be okay, so check if indeed there are no valid taut prop we could find");

        tautStatFile = Paths.get(directory + "/splitStats/" + benchmark + "_" + prop + "_tautStatFile" + ".txt");
        noTautStatFile = Paths.get(directory + "/splitStats/" + benchmark + "_" + prop + "_noTautStatFile" + ".txt");
        tautOtherStatFile = Paths.get(directory + "/splitStats/" + benchmark + "_" + prop + "_tautOtherStatFile" + ".txt");

        Files.write(tautStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(noTautStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(tautOtherStatFile, new ArrayList<>(), StandardCharsets.UTF_8);


        ArrayList<String> tautStatEntry = new ArrayList<>();
        ArrayList<String> noTautStatEntry = new ArrayList<>();
        ArrayList<String> tautStatOtherEntry = new ArrayList<>();

        // Open the file
        FileInputStream fstream = new FileInputStream(directory + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        int propIndex = 0;


        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            if (propIndex >= validTautProp.size()) {
                if (tautForOtherReasons.contains(strLine))
                    tautStatOtherEntry.add(strLine);
                else//we need to write it to non-taut file, while still looking for the prop
                    noTautStatEntry.add(strLine);
            } else {
                String currTautProp = validTautProp.get(propIndex);
                if (strLine.contains(currTautProp)) {//if match then we need to write it to the taut file
                    assert strLine.contains("TRUE_FOR_MAX_STEPS") : "tautology repair mutant must have been selected at that point because they failed due to TRUE_FOR_MAX_STEPS. Assumptions violated. Failing";
                    propIndex++; // we matched that prop, so we need to move on
                    tautStatEntry.add(strLine);
                } else { //we check if it is a tautology record where the failure was for other reasons than MAX_FOR_TRUE_STEPS
                    if (tautForOtherReasons.contains(strLine))
                        tautStatOtherEntry.add(strLine);
                    else//we need to write it to non-taut file, while still looking for the prop
                        noTautStatEntry.add(strLine);
                }
            }
        }

        Files.write(tautStatFile, tautStatEntry, StandardCharsets.UTF_8);
        Files.write(noTautStatFile, noTautStatEntry, StandardCharsets.UTF_8);
        Files.write(tautOtherStatFile, tautStatOtherEntry, StandardCharsets.UTF_8);


        //Close the input stream
        fstream.close();
    }

    //this needs to be manually setup for correct running
    private static void setupDataStructure() throws IOException {
        String matchingString = initializeFileName(); // at that point fileName has the right name of the stat file for the benchmark and the prop name. We return the matching string that we are looking for as well

        initializeTautProp(matchingString);


    }

    private static void initializeTautProp(String matchingString) throws IOException {
        String tautStateFile = benchmark + "TautologyDetails_all_stats.txt";
        // Open the file
        FileInputStream fstream = new FileInputStream(directory + tautStateFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            if (strLine.contains(matchingString)) {
                String tautPropStr = strLine.substring(matchingString.length() + 1, strLine.length() - 1);
                if (tautPropStr.length() != 0) { //there is some tautology props to look for

                    String[] tautPropNames = tautPropStr.split(",");

                    for (int i = 0; i < tautPropNames.length; i++) {
                        String tautPropName = tautPropNames[i];
                        tautPropName = tautPropName.replace(" ", "");
                        tautPropName = tautPropName.concat("=");
                        String bodyFileName = getCorrespondingBodyFileName();
                        //now we open the body file as a lustre file and look for the corresponding property we are looking for.

                        FileInputStream bodyFstream = new FileInputStream(bodyFileName);
                        BufferedReader bodybr = new BufferedReader(new InputStreamReader(bodyFstream));
                        String bodyLine;
                        while ((bodyLine = bodybr.readLine()) != null) {
                            if (bodyLine.contains(tautPropName)) {
                                tautProp.add(bodyLine.substring(tautPropName.length()));
                                break;
                            }
                        }
                        bodyFstream.close();
                    }
                }

                //Close the input stream
                fstream.close();
                return;
            }
        }

        assert false : "this cant happen as it means there were matching text for the matchingString";
    }

    private static String getCorrespondingBodyFileName() {
        String bodyFileName = directory + "Body/" + benchmark + "_" + prop + ".lus";

        return bodyFileName;
    }


    private static String initializeFileName() {
        String matchingString = "tautology props for " + benchmark + ": ";
        if (benchmark.equals("wbs")) {
            if (prop.equals("prop1")) {
                fileName = "stats/wbs_prop1_stats.txt";
                matchingString = matchingString.concat("p1");
            } else {
                fileName = "stats/wbs_prop3_stats.txt";
                matchingString = matchingString.concat("p3");
            }
        } else if (benchmark.equals("tcas")) {
            if (prop.equals("prop1")) {
                fileName = "stats/tcas_prop1_stats.txt";
                matchingString = matchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/tcas_prop2_stats.txt";
                matchingString = matchingString.concat("p2");
            } else {
                fileName = "stats/tcas_prop4_stats.txt";
                matchingString = matchingString.concat("p4");
            }
        } else if (benchmark.equals("infusion")) {
            if (prop.equals("prop1")) {
                fileName = "stats/infusion_prop1_stats.txt";
                matchingString = matchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/infusion_prop2_stats.txt";
                matchingString = matchingString.concat("p2");
            } else if (prop.equals("prop3")) {
                fileName = "stats/infusion_prop3_stats.txt";
                matchingString = matchingString.concat("p3");
            } else if (prop.equals("prop4")) {
                fileName = "stats/infusion_prop4_stats.txt";
                matchingString = matchingString.concat("p4");
            } else if (prop.equals("prop5")) {
                fileName = "stats/infusion_prop5_stats.txt";
                matchingString = matchingString.concat("p5");
            } else if (prop.equals("prop6")) {
                fileName = "stats/infusion_prop6_stats.txt";
                matchingString = matchingString.concat("p6");
            } else if (prop.equals("prop7")) {
                fileName = "stats/infusion_prop7_stats.txt";
                matchingString = matchingString.concat("p7");
            } else if (prop.equals("prop8")) {
                fileName = "stats/infusion_prop8_stats.txt";
                matchingString = matchingString.concat("p8");
            } else if (prop.equals("prop9")) {
                fileName = "stats/infusion_prop9_stats.txt";
                matchingString = matchingString.concat("p9");
            } else if (prop.equals("prop10")) {
                fileName = "stats/infusion_prop10_stats.txt";
                matchingString = matchingString.concat("p10");
            } else if (prop.equals("prop11")) {
                fileName = "stats/infusion_prop11_stats.txt";
                matchingString = matchingString.concat("p11");
            } else if (prop.equals("prop12")) {
                fileName = "stats/infusion_prop12_stats.txt";
                matchingString = matchingString.concat("p12");
            } else if (prop.equals("prop13")) {
                fileName = "stats/infusion_prop13_stats.txt";
                matchingString = matchingString.concat("p13");
            } else {
                fileName = "stats/infusion_prop14_stats.txt";
                matchingString = matchingString.concat("p14");
            }
        } else { //must be a gpca
            assert benchmark.equals("gpca");
            if (prop.equals("prop1")) {
                fileName = "stats/gpca_prop1_stats.txt";
                matchingString = matchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/gpca_prop2_stats.txt";
                matchingString = matchingString.concat("p2");
            } else if (prop.equals("prop3")) {
                fileName = "stats/gpca_prop3_stats.txt";
                matchingString = matchingString.concat("p3");
            } else if (prop.equals("prop4")) {
                fileName = "stats/gpca_prop4_stats.txt";
                matchingString = matchingString.concat("p4");
            } else if (prop.equals("prop5")) {
                fileName = "stats/gpca_prop5_stats.txt";
                matchingString = matchingString.concat("p5");
            } else if (prop.equals("prop6")) {
                fileName = "stats/gpca_prop6_stats.txt";
                matchingString = matchingString.concat("p6");
            } else if (prop.equals("prop7")) {
                fileName = "stats/gpca_prop7_stats.txt";
                matchingString = matchingString.concat("p7");
            } else if (prop.equals("prop8")) {
                fileName = "stats/gpca_prop8_stats.txt";
                matchingString = matchingString.concat("p8");
            } else if (prop.equals("prop9")) {
                fileName = "stats/gpca_prop9_stats.txt";
                matchingString = matchingString.concat("p9");
            } else {
                fileName = "stats/gpca_prop10_stats.txt";
                matchingString = matchingString.concat("p10");
            }

        }
        return matchingString.concat(" are: ");
    }


    /**
     * Example of the passed arguments is
     * props/Multi-Mutation_1Mutation_False/
     * wbs
     * prop1
     * /media/soha/DATA/MultiMutationExpr/1Mutation/ranger-discovery/src/DiscoveryExamples/WBS
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        directory = args[0];
        benchmark = args[1];
        prop = args[2];
        mutantExistsDirectory = args[3];
        filterStatisticsFile();
    }


    public static JKindResult callJkind(String fileName) {
        File file1;

        file1 = new File(fileName);

        JKindApi api = new JKindApi();
        JKindResult result = new JKindResult("");

        api.setJKindJar("../../jkindNoRand/jkind.jar");

        api.setTimeout(timeOut);

        api.execute(file1, result, new NullProgressMonitor());

        return result;
    }
}
