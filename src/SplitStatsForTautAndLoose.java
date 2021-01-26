/*
This class is used to split the statistics file based on properties listed, such that we loop over the statistics
file and divide it by its matching or not over the taut props. This should be called where the first input is the name of the
statistics files for the prop, and the second input is the benchmark, and finally the third arguement is the prop we are interested in.

IMPORTANT: to run this correctly one has to manually populate the tautProp according to the tautology properties obtained from the expirement.
* */

import java.io.*;
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
public class SplitStatsForTautAndLoose {
    //tautology props, that we have not confirmed yet that they cant be tightened using unbounded check
    public static List<String> tautProp = new ArrayList<>();
    public static List<String> looseProp = new ArrayList<>();

    //tautology props, that we have CONFIRMED that they cant be tightened using unbounded check
    public static List<String> validTautProp = new ArrayList<>();

    public static String directory; // passed as input and it is the parent directory of the repair process for the expirement
    public static String benchmark; // passed as an input
    public static String prop; //passed as an input
    public static String mutantExistsDirectory; // this is also passed as input, and it directs to the directory of the mutant expirement, where we can find the last exists query

    public static String fileName;
    private static Path _tautRepairStatFile;
    private static Path _looseRepairStatFile;

    private static Path _unRepairedStatFile;
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

        int tautPropIndex = 0;
        int loosePropIndex = 0;
/*

        if (tautProp.size() == 0)
            return;
*/

        _tautRepairStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_tautRepairStatFile" + ".txt");
        _looseRepairStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_looseRepairStatFile" + ".txt");
        _unRepairedStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_unRepairedStatFile" + ".txt");
        _cantRepairStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_cantRepairStatFile" + ".txt");
        _repairedStatFile = Paths.get(directory + "/splitStats/noproof/" + benchmark + "_" + prop + "_repairedStatFile" + ".txt");

        Files.write(_tautRepairStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(_looseRepairStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(_unRepairedStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(_cantRepairStatFile, new ArrayList<>(), StandardCharsets.UTF_8);
        Files.write(_repairedStatFile, new ArrayList<>(), StandardCharsets.UTF_8);


        ArrayList<String> _tautRepairStatEntry = new ArrayList<>();
        ArrayList<String> _looseRepairStatEntry = new ArrayList<>();
        ArrayList<String> _unRepairedStatEntry = new ArrayList<>();
        ArrayList<String> _cantRepairStatEntry = new ArrayList<>();
        ArrayList<String> _repairedStatEntry = new ArrayList<>();


        //Read File Line By Line -- and get the mutants names along with their properties for those that are tautology and failed because of TRUE_FOR_MAX_STEPS
        while ((strLine = br.readLine()) != null) {
            if (tautPropIndex < tautProp.size() && strLine.contains(tautProp.get(tautPropIndex))) {//if match then we need to write it to the taut file
                _tautRepairStatEntry.add(strLine);
                tautPropIndex++;
            } else if (loosePropIndex < looseProp.size() && strLine.contains(looseProp.get(loosePropIndex))) { // there is another reason for the failure, then log that seperately
                _looseRepairStatEntry.add(strLine);
                loosePropIndex++;
//                    System.out.println(currTautProp + " is taut for another reason than TRUE_FOR_MAX_STEPS");
            } else { // it is not either, thus this is an actual repair, if it does not contain matching or no valid synthesis in it.
                if (strLine.contains("OUTERLOOP_MAX_LOOP_REACHED") || strLine.contains("OUTERLOOP_FORALL_UKNOWN") || strLine.contains("OUTERLOOP_EXISTS_UNKNOWN")) { //no repair was found
                    _unRepairedStatEntry.add(strLine);
                } else if (strLine.contains("NO_VALID_SYNTHESIS_FOR_GRAMMAR") || strLine.contains("ALREADY_MATCHING")) {
                    _cantRepairStatEntry.add(strLine);
                } else {
                    _repairedStatEntry.add(strLine);
                }
            }
        }


        Files.write(_tautRepairStatFile, _tautRepairStatEntry, StandardCharsets.UTF_8);
        Files.write(_looseRepairStatFile, _looseRepairStatEntry, StandardCharsets.UTF_8);
        Files.write(_unRepairedStatFile, _unRepairedStatEntry, StandardCharsets.UTF_8);
        Files.write(_cantRepairStatFile, _cantRepairStatEntry, StandardCharsets.UTF_8);
        Files.write(_repairedStatFile, _repairedStatEntry, StandardCharsets.UTF_8);

        //Close the input stream
        fstream.close();

        assert tautPropIndex == tautProp.size();
        assert loosePropIndex == looseProp.size();
    }


    //this needs to be manually setup for correct running
    private static void setupDataStructure() throws IOException {
        String[] matchingString = initializeFileName(); // at that point fileName has the right name of the stat file for the benchmark and the prop name. We return the matching string that we are looking for as well

        initializeTautProp(matchingString);
    }

    private static void initializeTautProp(String[] matchingStrings) throws IOException {
        String tautStateFile = benchmark + "TautologyDetails_all_stats.txt";
        // Open the file
        FileInputStream fstream = new FileInputStream(directory + tautStateFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String tautMatchingString = matchingStrings[0];

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) { //finding tautology props and filling tautProp
            if (strLine.contains(tautMatchingString)) {
                String tautPropStr = strLine.substring(tautMatchingString.length() + 1, strLine.length() - 1);
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

        String looseMatchingString = matchingStrings[1];
        while ((strLine = br.readLine()) != null) { //finding loose props and filling looseProp
            if (strLine.contains(looseMatchingString)) {
                String tautPropStr = strLine.substring(looseMatchingString.length() + 1, strLine.length() - 1);
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
                                looseProp.add(bodyLine.substring(tautPropName.length()));
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
        String tautMatchingString = "tautology props for " + benchmark + ": ";
        if (benchmark.equals("wbs")) {
            if (prop.equals("prop1")) {
                fileName = "stats/wbs_prop1_stats.txt";
                tautMatchingString = tautMatchingString.concat("p1");
            } else {
                fileName = "stats/wbs_prop3_stats.txt";
                tautMatchingString = tautMatchingString.concat("p3");
            }
        } else if (benchmark.equals("tcas")) {
            if (prop.equals("prop1")) {
                fileName = "stats/tcas_prop1_stats.txt";
                tautMatchingString = tautMatchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/tcas_prop2_stats.txt";
                tautMatchingString = tautMatchingString.concat("p2");
            } else {
                fileName = "stats/tcas_prop4_stats.txt";
                tautMatchingString = tautMatchingString.concat("p4");
            }
        } else if (benchmark.equals("infusion")) {
            if (prop.equals("prop1")) {
                fileName = "stats/infusion_prop1_stats.txt";
                tautMatchingString = tautMatchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/infusion_prop2_stats.txt";
                tautMatchingString = tautMatchingString.concat("p2");
            } else if (prop.equals("prop3")) {
                fileName = "stats/infusion_prop3_stats.txt";
                tautMatchingString = tautMatchingString.concat("p3");
            } else if (prop.equals("prop4")) {
                fileName = "stats/infusion_prop4_stats.txt";
                tautMatchingString = tautMatchingString.concat("p4");
            } else if (prop.equals("prop5")) {
                fileName = "stats/infusion_prop5_stats.txt";
                tautMatchingString = tautMatchingString.concat("p5");
            } else if (prop.equals("prop6")) {
                fileName = "stats/infusion_prop6_stats.txt";
                tautMatchingString = tautMatchingString.concat("p6");
            } else if (prop.equals("prop7")) {
                fileName = "stats/infusion_prop7_stats.txt";
                tautMatchingString = tautMatchingString.concat("p7");
            } else if (prop.equals("prop8")) {
                fileName = "stats/infusion_prop8_stats.txt";
                tautMatchingString = tautMatchingString.concat("p8");
            } else if (prop.equals("prop9")) {
                fileName = "stats/infusion_prop9_stats.txt";
                tautMatchingString = tautMatchingString.concat("p9");
            } else if (prop.equals("prop10")) {
                fileName = "stats/infusion_prop10_stats.txt";
                tautMatchingString = tautMatchingString.concat("p10");
            } else if (prop.equals("prop11")) {
                fileName = "stats/infusion_prop11_stats.txt";
                tautMatchingString = tautMatchingString.concat("p11");
            } else if (prop.equals("prop12")) {
                fileName = "stats/infusion_prop12_stats.txt";
                tautMatchingString = tautMatchingString.concat("p12");
            } else if (prop.equals("prop13")) {
                fileName = "stats/infusion_prop13_stats.txt";
                tautMatchingString = tautMatchingString.concat("p13");
            } else {
                fileName = "stats/infusion_prop14_stats.txt";
                tautMatchingString = tautMatchingString.concat("p14");
            }
        } else { //must be a gpca
            assert benchmark.equals("gpca");
            if (prop.equals("prop1")) {
                fileName = "stats/gpca_prop1_stats.txt";
                tautMatchingString = tautMatchingString.concat("p1");
            } else if (prop.equals("prop2")) {
                fileName = "stats/gpca_prop2_stats.txt";
                tautMatchingString = tautMatchingString.concat("p2");
            } else if (prop.equals("prop3")) {
                fileName = "stats/gpca_prop3_stats.txt";
                tautMatchingString = tautMatchingString.concat("p3");
            } else if (prop.equals("prop4")) {
                fileName = "stats/gpca_prop4_stats.txt";
                tautMatchingString = tautMatchingString.concat("p4");
            } else if (prop.equals("prop5")) {
                fileName = "stats/gpca_prop5_stats.txt";
                tautMatchingString = tautMatchingString.concat("p5");
            } else if (prop.equals("prop6")) {
                fileName = "stats/gpca_prop6_stats.txt";
                tautMatchingString = tautMatchingString.concat("p6");
            } else if (prop.equals("prop7")) {
                fileName = "stats/gpca_prop7_stats.txt";
                tautMatchingString = tautMatchingString.concat("p7");
            } else if (prop.equals("prop8")) {
                fileName = "stats/gpca_prop8_stats.txt";
                tautMatchingString = tautMatchingString.concat("p8");
            } else if (prop.equals("prop9")) {
                fileName = "stats/gpca_prop9_stats.txt";
                tautMatchingString = tautMatchingString.concat("p9");
            } else {
                fileName = "stats/gpca_prop10_stats.txt";
                tautMatchingString = tautMatchingString.concat("p10");
            }

        }
        tautMatchingString = tautMatchingString.concat(" are: ");
        String looseMatchingString = "loose props are:";
        return new String[]{tautMatchingString, looseMatchingString};
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
}
