#!/usr/bin/env bash
# this is running the valid properties and using mutation for them, should be called from /home/soha/git/ProcessRepair


alias runDiscovery='java -Djava.library.path-/home/soha/git/ProcessRepair/lib -classpath /home/soha/git/ProcessRepair/out/production/ProcessRepair:/home/soha/git/ProcessRepair/lib/org.eclipse.core.runtime-3.1.0.jar:/home/soha/git/ProcessRepair/lib/jkind.jar:/home/soha/git/ProcessRepair/lib/jkind-api.jar SplitStatsForTaut '

shopt -s expand_aliases


EXPIREMENTDIR=props/Multi-Mutation_1Mutation_False/
LUSTREDIR=/media/soha/DATA/MultiMutationExpr/1Mutation/ranger-discovery/src/DiscoveryExamples/


runDiscovery $EXPIREMENTDIR wbs prop1 ${LUSTREDIR}WBS >& ${EXPIREMENTDIR}log/WBS_Prop1_SplitTaut.log
runDiscovery $EXPIREMENTDIR wbs prop3 ${LUSTREDIR}WBS >& ${EXPIREMENTDIR}log/WBS_Prop3_SplitTaut.log

runDiscovery $EXPIREMENTDIR tcas prop1 ${LUSTREDIR}TCAS >& ${EXPIREMENTDIR}log/TCAS_Prop1_SplitTaut.log
runDiscovery $EXPIREMENTDIR tcas prop2 ${LUSTREDIR}TCAS >& ${EXPIREMENTDIR}log/TCAS_Prop2_SplitTaut.log
runDiscovery $EXPIREMENTDIR tcas prop4 ${LUSTREDIR}TCAS >& ${EXPIREMENTDIR}log/TCAS_Prop4_SplitTaut.log


runDiscovery $EXPIREMENTDIR infusion prop1 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop1_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop2 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop2_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop3 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop3_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop4 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop4_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop5 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop5_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop6 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop6_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop7 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop7_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop8 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop8_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop9 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop9_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop10 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop10_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop11 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop11_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop12 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop12_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop13 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop13_SplitTaut.log
runDiscovery $EXPIREMENTDIR infusion prop14 ${LUSTREDIR}GPCA_Infusion >& ${EXPIREMENTDIR}log/INFUSION_Prop14_SplitTaut.log


runDiscovery $EXPIREMENTDIR gpca prop1 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop1_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop2 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop2_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop3 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop3_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop4 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop4_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop5 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop5_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop6 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop6_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop7 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop7_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop8 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop8_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop9 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop9_SplitTaut.log
runDiscovery $EXPIREMENTDIR gpca prop10 ${LUSTREDIR}GPCA_Alarm >& ${EXPIREMENTDIR}log/Alarm_Prop10_SplitTaut.log
