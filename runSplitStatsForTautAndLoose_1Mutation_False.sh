#!/usr/bin/env bash
# this is running the valid properties and using mutation for them, should be called from /home/soha/git/ProcessRepair


alias runDiscovery='java -Djava.library.path-/home/soha/git/ProcessRepair/lib -classpath /home/soha/git/ProcessRepair/out/production/ProcessRepair:/home/soha/git/ProcessRepair/lib/org.eclipse.core.runtime-3.1.0.jar:/home/soha/git/ProcessRepair/lib/jkind.jar:/home/soha/git/ProcessRepair/lib/jkind-api.jar SplitStatsForTautAndLoose '

shopt -s expand_aliases


EXPIREMENTDIR=props/Multi-Mutation_1Mutation_False/
LUSTREDIR=/media/soha/DATA/MultiMutationExpr/1Mutation/ranger-discovery/src/DiscoveryExamples/


runDiscovery $EXPIREMENTDIR wbs prop1 ${LUSTREDIR}WBS
runDiscovery $EXPIREMENTDIR wbs prop3 ${LUSTREDIR}WBS
cp ${EXPIREMENTDIR}splitStats/wbs_prop1_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/wbs_prop1_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/wbs_prop3_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/wbs_prop3_tautProvedStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/wbs_prop1_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/wbs_prop1_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/wbs_prop1_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/wbs_prop3_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/wbs_prop3_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/wbs_prop3_unknownTautStatFile.txt


runDiscovery $EXPIREMENTDIR tcas prop1 ${LUSTREDIR}TCAS
runDiscovery $EXPIREMENTDIR tcas prop2 ${LUSTREDIR}TCAS
runDiscovery $EXPIREMENTDIR tcas prop4 ${LUSTREDIR}TCAS
cp ${EXPIREMENTDIR}splitStats/tcas_prop1_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/tcas_prop1_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/tcas_prop2_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/tcas_prop2_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/tcas_prop4_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/tcas_prop4_tautProvedStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/tcas_prop1_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/tcas_prop1_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/tcas_prop1_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/tcas_prop2_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/tcas_prop2_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/tcas_prop2_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/tcas_prop4_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/tcas_prop4_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/tcas_prop4_unknownTautStatFile.txt



runDiscovery $EXPIREMENTDIR infusion prop1 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop2 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop3 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop4 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop5 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop6 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop7 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop8 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop9 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop10 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop11 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop12 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop13 ${LUSTREDIR}GPCA_Infusion
runDiscovery $EXPIREMENTDIR infusion prop14 ${LUSTREDIR}GPCA_Infusion
cp ${EXPIREMENTDIR}splitStats/infusion_prop1_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop1_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop2_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop2_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop3_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop3_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop4_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop4_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop5_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop5_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop6_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop6_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop7_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop7_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop8_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop8_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop9_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop9_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop10_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop10_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop11_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop11_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop12_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop12_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop13_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop13_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/infusion_prop14_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/infusion_prop14_tautProvedStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop1_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop1_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop1_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop2_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop2_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop2_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop3_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop3_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop3_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop4_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop4_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop4_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop5_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop5_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop5_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop6_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop6_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop6_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop7_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop7_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop7_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop8_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop8_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop8_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop9_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop9_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop9_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop10_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop10_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop10_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop11_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop11_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop11_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop12_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop12_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop12_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop13_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop13_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop13_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/infusion_prop14_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/infusion_prop14_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/infusion_prop14_unknownTautStatFile.txt



runDiscovery $EXPIREMENTDIR gpca prop1 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop2 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop3 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop4 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop5 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop6 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop7 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop8 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop9 ${LUSTREDIR}GPCA_Alarm
runDiscovery $EXPIREMENTDIR gpca prop10 ${LUSTREDIR}GPCA_Alarm
cp ${EXPIREMENTDIR}splitStats/gpca_prop1_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop1_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop2_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop2_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop3_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop3_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop4_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop4_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop5_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop5_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop6_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop6_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop7_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop7_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop8_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop8_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop9_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop9_tautProvedStatFile.txt
cp ${EXPIREMENTDIR}splitStats/gpca_prop10_tautStatFile.txt   ${EXPIREMENTDIR}splitStats/noproof/gpca_prop10_tautProvedStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop1_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop1_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop1_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop2_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop2_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop2_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop3_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop3_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop3_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop4_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop4_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop4_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop5_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop5_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop5_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop6_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop6_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop6_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop7_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop7_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop7_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop8_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop8_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop8_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop9_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop9_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop9_unknownTautStatFile.txt
combine ${EXPIREMENTDIR}splitStats/noproof/gpca_prop10_tautRepairStatFile.txt not ${EXPIREMENTDIR}splitStats/gpca_prop10_tautStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/gpca_prop10_unknownTautStatFile.txt


cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_cantRepairStatFile.txt > ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_cantRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_looseRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_looseRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_repairedStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_repairedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_tautProvedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_tautProvedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_tautRepairStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_tautRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_unknownTautStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_unknownTautStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/gpca_prop*_unRepairedStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/gpca_unRepairedStatFile.txt

cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_cantRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_cantRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_looseRepairStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_looseRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_repairedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_repairedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_tautProvedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_tautProvedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_tautRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_tautRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_unknownTautStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_unknownTautStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_unRepairedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_unRepairedStatFile.txt

cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_cantRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_cantRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_looseRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_looseRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_repairedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_repairedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_tautProvedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_tautProvedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_tautRepairStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_tautRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_unknownTautStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_unknownTautStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/tcas_prop*_unRepairedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/tcas_unRepairedStatFile.txt

cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_cantRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_cantRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_looseRepairStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_looseRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_repairedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_repairedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_tautProvedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_tautProvedStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_tautRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_tautRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_unknownTautStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_unknownTautStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/wbs_prop*_unRepairedStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/wbs_unRepairedStatFile.txt
