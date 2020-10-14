package InputOutput;

import jkind.lustre.NamedType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class takes Daikon's invariants and translates it to a lustre form string.
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 * <p>
 * TCAS constants:
 * public static int OLEV = 600;
 * public static int MAXALTDIFF = 300;
 * public static int MINSEP = 600;
 * public static int NOZCROSS = 100;
 * public static boolean High_Confidence;
 * public static boolean Two_of_Three_Reports_Valid;
 * public static int Positive_RA_Alt_Thresh_0;
 * public static int Positive_RA_Alt_Thresh_1;
 * public static int Positive_RA_Alt_Thresh_2;
 * public static int Positive_RA_Alt_Thresh_3;
 * public static int NO_INTENT = 0;
 * public static int DO_NOT_CLIMB = 1;
 * public static int DO_NOT_DESCEND = 2;
 * public static int TCAS_TA = 1;
 * public static int OTHER = 2;
 * public static int UNRESOLVED = 0;
 * public static int UPWARD_RA = 1;
 * public static int DOWNWARD_RA = 2;
 * <p>
 * Infusion constants:
 * final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 * final static int INFUSION_MGR_Functional_IN_Basal = 1;
 * final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 * final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 * final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 * final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 * final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 * final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 * final static int INFUSION_MGR_Functional_IN_OFF = 1;
 * final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 * final static int INFUSION_MGR_Functional_IN_ON = 2;
 * final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 * final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 * final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 * final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 * final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 * final static int INFUSION_MGR_Functional_IN_THERAPY = 2;
 */

/**
 * TCAS constants:
 *  public static int OLEV = 600;
 public static int MAXALTDIFF = 300;
 public static int MINSEP = 600;
 public static int NOZCROSS = 100;
 public static boolean High_Confidence;
 public static boolean Two_of_Three_Reports_Valid;
 public static int Positive_RA_Alt_Thresh_0;
 public static int Positive_RA_Alt_Thresh_1;
 public static int Positive_RA_Alt_Thresh_2;
 public static int Positive_RA_Alt_Thresh_3;
 public static int NO_INTENT = 0;
 public static int DO_NOT_CLIMB = 1;
 public static int DO_NOT_DESCEND = 2;
 public static int TCAS_TA = 1;
 public static int OTHER = 2;
 public static int UNRESOLVED = 0;
 public static int UPWARD_RA = 1;
 public static int DOWNWARD_RA = 2;
 */

/**
 * Infusion constants:
 *      final static int INFUSION_MGR_Functional_IN_ACTIVE = 1;
 final static int INFUSION_MGR_Functional_IN_Basal = 1;
 final static int INFUSION_MGR_Functional_IN_IDLE = 1;
 final static int INFUSION_MGR_Functional_IN_Infusion_Manager = 1;
 final static int INFUSION_MGR_Functional_IN_Intermittent_Bolus = 2;
 final static int INFUSION_MGR_Functional_IN_LOCKOUT = 1;
 final static int INFUSION_MGR_Functional_IN_Manual_Paused_KVO = 1;
 final static int INFUSION_MGR_Functional_IN_NOT_ON = 2;
 final static int INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD = 0;
 final static int INFUSION_MGR_Functional_IN_OFF = 1;
 final static int INFUSION_MGR_Functional_IN_OFF_b = 2;
 final static int INFUSION_MGR_Functional_IN_ON = 2;
 final static int INFUSION_MGR_Functional_IN_ON_b = 3;
 final static int INFUSION_MGR_Functional_IN_PAUSED = 2;
 final static int INFUSION_MGR_Functional_IN_Patient_Bolus = 3;
 final static int INFUSION_MGR_Functional_IN_Paused_KVO = 2;
 final static int INFUSION_MGR_Functional_IN_Paused_NoKVO = 3;
 final static int INFUSION_MGR_Functional_IN_THERAPY = 2;

 */

/**
 * Alarm constants:
 *     public static final int ALARM_Functional_IN_AlarmDisplay = 1;
 public static final int ALARM_Functional_IN_Alarms = 1;
 public static final int ALARM_Functional_IN_Check = 1;
 public static final int ALARM_Functional_IN_Disabled = 1;
 public static final int ALARM_Functional_IN_Monitor = 2;
 public static final int ALARM_Functional_IN_NOT_ON = 2;
 public static final int ALARM_Functional_IN_NO_ACTIVE_CHILD = 0;
 public static final int ALARM_Functional_IN_No = 1;
 public static final int ALARM_Functional_IN_OFF = 2;
 public static final int ALARM_Functional_IN_OFF_i = 1;
 public static final int ALARM_Functional_IN_ON = 3;
 public static final int ALARM_Functional_IN_ON_a = 2;
 public static final int ALARM_Functional_IN_Silenced = 4;
 public static final int ALARM_Functional_IN_Yes = 2;
 public static final int ALARM_Functional_IN_Yes_o = 3;
 public static final int ALARM_Functional_IN_counting = 3;
 */

public class TranslateDaikonInv {

    public static String benchmark = null;

    public static FileWriter fw;
    public static BufferedWriter bw;
    public static PrintWriter out;
    public static String className;
    public static ArrayList<String> classNames = new ArrayList<>();

    public static HashMap<String, Integer> constantsMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        assert (args.length == 2) : "a file that contains Daikon's invariants must be passed as well as the benchmark name";
        Path path = Paths.get(args[0]);
        benchmark = args[1];
        fw = new FileWriter("resources/LustreFromDaikon_" + benchmark, false);
        bw = new BufferedWriter(fw);
        out = new PrintWriter(bw);
//        if (benchmark.equals("wbs"))
//            fillWBSConstants();
//        if (benchmark.equals("tcas"))
//            fillTcasConstants();
//        else if (benchmark.equals("alarm"))
//            fillAlarmConstants();
//        else if (benchmark.equals("infusion"))
//            fillInfusionConstants();

        if (benchmark.equals("infusion"))
            fillInfusionConstants();
        else if(benchmark.equals("alarm"))
            fillAlarmConstants();

        if (!Files.exists(path)) { // prop was not executed in the run.
            System.out.println("prop file not found, unexpected. aborting");
            assert false;
        }

        String content = readFile(path, StandardCharsets.US_ASCII);

        ArrayList<String> invariants = new ArrayList<String>(Arrays.asList(content.split("\n")));
        SpecInOutManager specInOutMgr = new SpecInOutManager();
        specInOutMgr.discoverVars();

        ArrayList<String> lustreInv = new ArrayList<>();
        for (String inv : invariants) {
            if (!inv.contains("null")) {
//                if (benchmark.equals("wbs")) {
//                    inv = inv.replace(className, "");
//                    inv = replaceVarsToSpecNameWBS(inv);
//                } else if (benchmark.equals("tcas")) {
//                    inv = inv.replace(className, "");
//                    inv = replaceVarsToSpecNameTCAS(inv);
//                } else if (benchmark.equals("infusion") || benchmark.equals("alarm"))
//                    inv = replaceAllClassNames(inv);
//                else
//                    assert false : "unexpected benchmark. Failing.";

                if (benchmark.equals("infusion") || benchmark.equals("alarm"))
                    inv = replaceAllClassNames(inv);

                inv = inv.replaceAll("this.", "");
                inv = inv.replaceAll("==.", "=");
                inv = inv.replaceAll("!=.", "<>");
                inv = inv.replaceAll("!", "not");
                inv = inv.replaceAll("%", "mod");
                inv = inv.replaceAll("\\|\\|", "or");
                inv = inv.replaceAll("\\\\result.", "");

                if (!inv.contains("old("))
                    lustreInv.add(inv);
                else {
                    while (inv != null && inv.contains("old")) {
                        int[] leftRightBracket = findFirstOldIndex(inv);
                        assert leftRightBracket.length == 2;
                        inv = toLustre(inv, leftRightBracket, specInOutMgr);
                    }
                    if (inv != null)
                        lustreInv.add(inv);
                }
            }
        }

        for (String inv : lustreInv) {
            out.println(inv);
        }

        out.close();
    }

    private static int[] findFirstOldIndex(String inv) {
        int[] leftRightBracket = new int[2];
        leftRightBracket[0] = inv.indexOf("old") + 3;
        leftRightBracket[1] = inv.indexOf(")", leftRightBracket[0]);

        return leftRightBracket;
    }


    private static String replaceAllClassNames(String inv) {
        for (int i = 0; i < classNames.size(); i++) {
            inv = inv.replaceAll(classNames.get(i), "");
        }
        return inv;
    }

    private static String replaceVarsToSpecNameWBS(String inv) {
       /* inv = inv.replaceAll("pedal", "pedal_r");
        inv = inv.replaceAll("auto", "autoBreak_r");
        inv = inv.replaceAll("skid", "skid_r");
        inv = inv.replaceAll("Nor_Pressure", "NormalPressure_r");
        inv = inv.replaceAll("Alt_Pressure", "AltPressure_r");*/
        return inv;
    }


    private static String replaceVarsToSpecNameTCAS(String inv) {
        /*assert false : "undefined";
        inv = inv.replaceAll("pedal", "pedal_r");
        inv = inv.replaceAll("autoBreak", "autoBreak_r");
        inv = inv.replaceAll("skid", "skid_r");
        inv = inv.replaceAll("Nor_Pressure", "NormalPressure_r");
        inv = inv.replaceAll("Alt_Pressure", "AltPressure_r");*/
        return inv;
    }

    private static void fillWBSConstants() {
        className = "wbs.WBS_Output.";
    }


    private static void fillInfusionConstants() {
        constantsMap.put("INFUSION_MGR_Functional_IN_Basal", 1);
        constantsMap.put("INFUSION_MGR_Functional_IN_IDLE", 1);
        constantsMap.put("INFUSION_MGR_Functional_IN_Infusion_Manager", 1);
        constantsMap.put("INFUSION_MGR_Functional_IN_Intermittent_Bolus", 2);
        constantsMap.put("INFUSION_MGR_Functional_IN_LOCKOUT", 1);
        constantsMap.put("INFUSION_MGR_Functional_IN_Manual_Paused_KVO", 1);
        constantsMap.put("INFUSION_MGR_Functional_IN_NOT_ON", 2);
        constantsMap.put("INFUSION_MGR_Functional_IN_NO_ACTIVE_CHILD", 0);
        constantsMap.put("INFUSION_MGR_Functional_IN_OFF", 1);
        constantsMap.put("INFUSION_MGR_Functional_IN_OFF_b", 2);
        constantsMap.put("INFUSION_MGR_Functional_IN_ON", 2);
        constantsMap.put("INFUSION_MGR_Functional_IN_ON_b", 3);
        constantsMap.put("INFUSION_MGR_Functional_IN_PAUSED", 2);
        constantsMap.put("INFUSION_MGR_Functional_IN_Patient_Bolus", 3);
        constantsMap.put("INFUSION_MGR_Functional_IN_Paused_KVO", 2);
        constantsMap.put("INFUSION_MGR_Functional_IN_Paused_NoKVO", 3);
        constantsMap.put("INFUSION_MGR_Functional_IN_THERAPY", 2);
        classNames.add("infusionTargetedDaikon.Infusion_Result_static.");

    }

    private static void fillAlarmConstants() {
        constantsMap.put("ALARM_Functional_IN_AlarmDisplay", 1);
        constantsMap.put("ALARM_Functional_IN_Alarms", 1);
        constantsMap.put("ALARM_Functional_IN_Check", 1);
        constantsMap.put("ALARM_Functional_IN_Disabled", 1);
        constantsMap.put("ALARM_Functional_IN_Monitor", 2);
        constantsMap.put("ALARM_Functional_IN_NOT_ON", 2);
        constantsMap.put("ALARM_Functional_IN_NO_ACTIVE_CHILD", 0);
        constantsMap.put("ALARM_Functional_IN_No", 1);
        constantsMap.put("ALARM_Functional_IN_OFF", 2);
        constantsMap.put("ALARM_Functional_IN_OFF_i", 1);
        constantsMap.put("ALARM_Functional_IN_ON", 3);
        constantsMap.put("ALARM_Functional_IN_ON_a", 2);
        constantsMap.put("ALARM_Functional_IN_Silenced", 4);
        constantsMap.put("ALARM_Functional_IN_Yes", 2);
        constantsMap.put("ALARM_Functional_IN_Yes_o", 3);
        constantsMap.put("ALARM_Functional_IN_counting", 3);
        classNames.add("alarmDaikon.Alarm_Result_Static.");


    }

    private static void fillTcasConstants() {
        constantsMap.put("OLEV", 600);
        constantsMap.put("MAXALTDIFF", 300);
        constantsMap.put("MINSEP", 600);
        constantsMap.put("NOZCROSS", 100);
        constantsMap.put("Positive_RA_Alt_Thresh_0", 400);
        constantsMap.put("Positive_RA_Alt_Thresh_1", 500);
        constantsMap.put("Positive_RA_Alt_Thresh_2", 640);
        constantsMap.put("Positive_RA_Alt_Thresh_3", 740);
        constantsMap.put("NO_INTENT", 0);
        constantsMap.put("DO_NOT_CLIMB", 1);
        constantsMap.put("DO_NOT_DESCEND", 2);
        constantsMap.put("TCAS_TA", 1);
        constantsMap.put("OTHER", 2);
        constantsMap.put("UNRESOLVED", 0);
        constantsMap.put("UPWARD_RA", 1);
        constantsMap.put("DOWNWARD_RA", 2);
        className = "tcas.TCAS_Output.";
    }

    /**
     * returns a pre expression only for the ids that are in the input and the output otherwise it returns the same id.
     *
     * @param inv
     * @param leftRightBracket
     * @param specInOutMgr
     * @return
     */
    private static String toLustre(String inv, int[] leftRightBracket, SpecInOutManager specInOutMgr) {
        String idInOld = inv.substring(leftRightBracket[0] + 1, leftRightBracket[1]);

        StringBuffer invStrBuff = new StringBuffer(inv);

        NamedType type = specInOutMgr.getTypeForName(idInOld);

        if(specInOutMgr.isFreeInVar(idInOld, type))
            return invStrBuff.replace(leftRightBracket[0] - 4, leftRightBracket[1] + 1, idInOld).toString();

        if (type != null) {
            if (type == NamedType.INT)
                return invStrBuff.replace(leftRightBracket[0] - 4, leftRightBracket[1] + 1, "(0 -> pre " + idInOld + ")").toString();
            else if (type == NamedType.BOOL)
                return invStrBuff.replace(leftRightBracket[0] - 4, leftRightBracket[1] + 1, "(false -> pre " + idInOld + ")").toString();
            else {
                assert false;
                return null;
            }
        } else {
            System.out.println("couldn't find variable:" + idInOld + ", ignoring invariant: " + idInOld);
            return null;
        }
    }


    static String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }
}
