/*
This class is used to split the statistics file based on properties listed, such that we loop over the statistics
file and divide it by its matching or not over the taut props. This should be called where the first input is the name of the
statistics files for the prop, and the second input is the benchmark, and finally the third arguement is the prop we are interested in.

IMPORTANT: to run this correctly one has to manually populate the tautProp according to the tautology properties obtained from the expirement.
* */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * this file just splits that stats to
 * 1. stats without taut or loose
 * 2. stats for tautology (whether proved  valid or not)
 * 4. stats for loose
 * * No unbounded verification here, this happens in the SplitStatsForTaut for tautology files.
 */
public class SplitStatsForMatchAndRelevant {
    //tautology props, that we have not confirmed yet that they cant be tightened using unbounded check
    public static List<String> tightProp = new ArrayList<>();
    public static List<String> equivProp = new ArrayList<>();
    public static List<String> relevantProp = new ArrayList<>();

    //tautology props, that we have CONFIRMED that they cant be tightened using unbounded check
    public static List<String> validTautProp = new ArrayList<>();

    public static String directory; // passed as input and it is the parent directory of the repair process for the experiment
    public static String benchmark; // passed as an input
    public static String prop; //passed as an input

    public static String fileName;
    private static Path _tightRepairStatFile;
    private static Path _equivRepairStatFile;

    private static Path _relevantRepairedStatFile;
    private static Path _cantRepairStatFile;
    private static Path _repairedStatFile;

    //this data structure needs to maintain the insertion order.// I have split this map into two list, just in case there was a collision among the names of mutants due to the random selection. With the list DS we will have the right order and will have the duplicate mutant names as well
    //private static LinkedHashMap<String, String> tautMutantsToProp = new LinkedHashMap<>();
    private static List<String> tautMutantList = new ArrayList<>();
    private static List<String> tautPropList = new ArrayList<>();
    private static List<String> loosePropList = new ArrayList<>();


    //hashset that holds the enteries for tautology stats that were not because of TRUE_FOR_MAX_STEPS
    static HashSet<String> tautForOtherReasons = new HashSet<>();

    static int timeOut = 900;

//    static int unknownCount = 0;

    public static void filterStatisticsFile() throws IOException {


        setupDataStructure();

        //find the mutant names that corresponds to the initially tautology props.
        fillTautMutantsToPropMap();

//        splitStatFileToTaut();
//        System.out.println(benchmark +" "+ prop +" -- number of unknown taut prop = " + unknownCount);
    }


    //the output of this method is a pair of the matching mutant name with its prop, this is populated in tautMutantsToProp
    public static void fillTautMutantsToPropMap() throws IOException {

        // Open the file
        FileInputStream fstream = new FileInputStream(directory + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        int tightPropIndex = 0;
        int equivPropIndex = 0;
        int relevantPropIndex = 0;
/*

        if (tautProp.size() == 0)
            return;
*/

        _tightRepairStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_tightRepairStatFile" + ".txt");
        _equivRepairStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_equivRepairStatFile" + ".txt");
        _relevantRepairedStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_relevantRepairStatFile" + ".txt");

        Files.write(_tightRepairStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(_equivRepairStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(_relevantRepairedStatFile, new ArrayList<>(), StandardCharsets.UTF_8);


        ArrayList<String> _tightRepairStatEntry = new ArrayList<>();
        ArrayList<String> _equivRepairStatEntry = new ArrayList<>();
        ArrayList<String> _relevantRepairedStatEntry = new ArrayList<>();


        //Read File Line By Line -- and get the mutants names along with their properties for those that are tautology and failed because of TRUE_FOR_MAX_STEPS
        while ((strLine = br.readLine()) != null) {
            if (tightPropIndex < tightProp.size() && strLine.contains(tightProp.get(tightPropIndex))) {//if match then we need to write it to the taut file
                _tightRepairStatEntry.add(strLine);
                tightPropIndex++;
            } else if (equivPropIndex < equivProp.size() && strLine.contains(equivProp.get(equivPropIndex))) { // there is another reason for the failure, then log that seperately
                _equivRepairStatEntry.add(strLine);
                equivPropIndex++;
            } else if (relevantPropIndex < relevantProp.size() && strLine.contains(relevantProp.get(relevantPropIndex))) { // there is another reason for the failure, then log that seperately
                _relevantRepairedStatEntry.add(strLine);
                relevantPropIndex++;
            }
        }


        Files.write(_tightRepairStatFile, _tightRepairStatEntry, StandardCharsets.UTF_8);
        Files.write(_equivRepairStatFile, _equivRepairStatEntry, StandardCharsets.UTF_8);
        Files.write(_relevantRepairedStatFile, _relevantRepairedStatEntry, StandardCharsets.UTF_8);

        //Close the input stream
        fstream.close();

        assert tightPropIndex == tightProp.size();
        assert equivPropIndex == equivProp.size();
        assert relevantPropIndex == relevantProp.size();
    }


    //this needs to be manually setup for correct running
    private static void setupDataStructure() throws IOException {
        String[] matchingString = initializeFileName(); // at that point fileName has the right name of the stat file for the benchmark and the prop name. We return the matching string that we are looking for as well

        initializeTautPropCat(matchingString);
    }

    private static void initializeTautPropCat(String[] matchingStrings) throws IOException {
        String matchRelevantStatFile = benchmark + "OrigPropRelation_all_stats_Match_Relevant.txt";
        // Open the file
        FileInputStream fstream = new FileInputStream(directory + matchRelevantStatFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String tightMatchingString = matchingStrings[0];

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) { //finding tautology props and filling tautProp
            if (strLine.contains(tightMatchingString)) {
                String tightPropStr = strLine.substring(tightMatchingString.length() + 1, strLine.length() - 1);
                if (tightPropStr.length() != 0) { //there is some tautology props to look for

                    String[] tightPropNames = tightPropStr.split(",");

                    for (int i = 0; i < tightPropNames.length; i++) {
                        String tightPropName = tightPropNames[i];
                        tightPropName = tightPropName.replace(" ", "");
                        tightPropName = tightPropName.concat("=");
                        String bodyFileName = getCorrespondingBodyFileName();
                        //now we open the body file as a lustre file and look for the corresponding property we are looking for.

                        FileInputStream bodyFstream = new FileInputStream(bodyFileName);
                        BufferedReader bodybr = new BufferedReader(new InputStreamReader(bodyFstream));
                        String bodyLine;
                        while ((bodyLine = bodybr.readLine()) != null) {
                            if (bodyLine.contains(tightPropName)) {
                                tightProp.add(bodyLine.substring(tightPropName.length()));
                                break;
                            }
                        }
                        bodyFstream.close();
                    }
                }

           /*     //Close the input stream
                fstream.close();
                break;*/
            }
        }
        //Close the input stream
        fstream.close();


        fstream = new FileInputStream(directory + matchRelevantStatFile);
        br = new BufferedReader(new InputStreamReader(fstream));


        String equivMatchingString = matchingStrings[1];
        while ((strLine = br.readLine()) != null) { //finding loose props and filling looseProp
            if (strLine.contains(equivMatchingString)) {
                String equivPropStr = strLine.substring(equivMatchingString.length() + 1, strLine.length() - 1);
                if (equivPropStr.length() != 0) { //there is some tautology props to look for

                    String[] tautPropNames = equivPropStr.split(",");

                    for (int i = 0; i < tautPropNames.length; i++) {
                        String equivPropName = tautPropNames[i];
                        equivPropName = equivPropName.replace(" ", "");
                        equivPropName = equivPropName.concat("=");
                        String bodyFileName = getCorrespondingBodyFileName();
                        //now we open the body file as a lustre file and look for the corresponding property we are looking for.

                        FileInputStream bodyFstream = new FileInputStream(bodyFileName);
                        BufferedReader bodybr = new BufferedReader(new InputStreamReader(bodyFstream));
                        String bodyLine;
                        while ((bodyLine = bodybr.readLine()) != null) {
                            if (bodyLine.contains(equivPropName)) {
                                equivProp.add(bodyLine.substring(equivPropName.length()));
                                break;
                            }
                        }
                        bodyFstream.close();
                    }
                }
/*
                //Close the input stream
                fstream.close();*/
            }
        }

        //Close the input stream
        fstream.close();

        fstream = new FileInputStream(directory + matchRelevantStatFile);
        br = new BufferedReader(new InputStreamReader(fstream));


        String relevantMatchingString = matchingStrings[2];
        while ((strLine = br.readLine()) != null) { //finding loose props and filling looseProp
            if (strLine.contains(relevantMatchingString)) {
                String relevantPropStr = strLine.substring(relevantMatchingString.length() + 1, strLine.length() - 1);
                if (relevantPropStr.length() != 0) { //there is some tautology props to look for

                    String[] relevantPropNames = relevantPropStr.split(",");

                    for (int i = 0; i < relevantPropNames.length; i++) {
                        String relevantPropName = relevantPropNames[i];
                        relevantPropName = relevantPropName.replace(" ", "");
                        relevantPropName = relevantPropName.concat("=");
                        String bodyFileName = getCorrespondingBodyFileName();
                        //now we open the body file as a lustre file and look for the corresponding property we are looking for.

                        FileInputStream bodyFstream = new FileInputStream(bodyFileName);
                        BufferedReader bodybr = new BufferedReader(new InputStreamReader(bodyFstream));
                        String bodyLine;
                        while ((bodyLine = bodybr.readLine()) != null) {
                            if (bodyLine.contains(relevantPropName)) {
                                relevantProp.add(bodyLine.substring(relevantPropName.length()));
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


    private static String[] initializeFileName() {
        String tightMatchingString = "tight props for " + benchmark + ": ";
        if (benchmark.equals("wbs")) {
            if (prop.equals("prop1")) {
                fileName = "stats/wbs_prop1_stats.txt";
                tightMatchingString = tightMatchingString.concat("p1");
            } else {
                fileName = "stats/wbs_prop3_stats.txt";
                tightMatchingString = tightMatchingString.concat("p3");
            }
        } else if (benchmark.equals("tcas")) {
            if (prop.equals("prop1")) {
                fileName = "stats/tcas_prop1_stats.txt";
                tightMatchingString = tightMatchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/tcas_prop2_stats.txt";
                tightMatchingString = tightMatchingString.concat("p2");
            } else {
                fileName = "stats/tcas_prop4_stats.txt";
                tightMatchingString = tightMatchingString.concat("p4");
            }
        } else if (benchmark.equals("infusion")) {
            if (prop.equals("prop1")) {
                fileName = "stats/infusion_prop1_stats.txt";
                tightMatchingString = tightMatchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/infusion_prop2_stats.txt";
                tightMatchingString = tightMatchingString.concat("p2");
            } else if (prop.equals("prop3")) {
                fileName = "stats/infusion_prop3_stats.txt";
                tightMatchingString = tightMatchingString.concat("p3");
            } else if (prop.equals("prop4")) {
                fileName = "stats/infusion_prop4_stats.txt";
                tightMatchingString = tightMatchingString.concat("p4");
            } else if (prop.equals("prop5")) {
                fileName = "stats/infusion_prop5_stats.txt";
                tightMatchingString = tightMatchingString.concat("p5");
            } else if (prop.equals("prop6")) {
                fileName = "stats/infusion_prop6_stats.txt";
                tightMatchingString = tightMatchingString.concat("p6");
            } else if (prop.equals("prop7")) {
                fileName = "stats/infusion_prop7_stats.txt";
                tightMatchingString = tightMatchingString.concat("p7");
            } else if (prop.equals("prop8")) {
                fileName = "stats/infusion_prop8_stats.txt";
                tightMatchingString = tightMatchingString.concat("p8");
            } else if (prop.equals("prop9")) {
                fileName = "stats/infusion_prop9_stats.txt";
                tightMatchingString = tightMatchingString.concat("p9");
            } else if (prop.equals("prop10")) {
                fileName = "stats/infusion_prop10_stats.txt";
                tightMatchingString = tightMatchingString.concat("p10");
            } else if (prop.equals("prop11")) {
                fileName = "stats/infusion_prop11_stats.txt";
                tightMatchingString = tightMatchingString.concat("p11");
            } else if (prop.equals("prop12")) {
                fileName = "stats/infusion_prop12_stats.txt";
                tightMatchingString = tightMatchingString.concat("p12");
            } else if (prop.equals("prop13")) {
                fileName = "stats/infusion_prop13_stats.txt";
                tightMatchingString = tightMatchingString.concat("p13");
            } else {
                fileName = "stats/infusion_prop14_stats.txt";
                tightMatchingString = tightMatchingString.concat("p14");
            }
        } else { //must be a gpca
            assert benchmark.equals("gpca");
            if (prop.equals("prop1")) {
                fileName = "stats/gpca_prop1_stats.txt";
                tightMatchingString = tightMatchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/gpca_prop2_stats.txt";
                tightMatchingString = tightMatchingString.concat("p2");
            } else if (prop.equals("prop3")) {
                fileName = "stats/gpca_prop3_stats.txt";
                tightMatchingString = tightMatchingString.concat("p3");
            } else if (prop.equals("prop4")) {
                fileName = "stats/gpca_prop4_stats.txt";
                tightMatchingString = tightMatchingString.concat("p4");
            } else if (prop.equals("prop5")) {
                fileName = "stats/gpca_prop5_stats.txt";
                tightMatchingString = tightMatchingString.concat("p5");
            } else if (prop.equals("prop6")) {
                fileName = "stats/gpca_prop6_stats.txt";
                tightMatchingString = tightMatchingString.concat("p6");
            } else if (prop.equals("prop7")) {
                fileName = "stats/gpca_prop7_stats.txt";
                tightMatchingString = tightMatchingString.concat("p7");
            } else if (prop.equals("prop8")) {
                fileName = "stats/gpca_prop8_stats.txt";
                tightMatchingString = tightMatchingString.concat("p8");
            } else if (prop.equals("prop9")) {
                fileName = "stats/gpca_prop9_stats.txt";
                tightMatchingString = tightMatchingString.concat("p9");
            } else {
                fileName = "stats/gpca_prop10_stats.txt";
                tightMatchingString = tightMatchingString.concat("p10");
            }

        }
        tightMatchingString = tightMatchingString.concat(" are: ");
        String equivMatchingString = tightMatchingString.replace("tight", "equiv");
        String relevantMatchingString = tightMatchingString.replace("tight", "relevant");
        return new String[]{tightMatchingString, equivMatchingString, relevantMatchingString};
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
        filterStatisticsFile();
    }
}
