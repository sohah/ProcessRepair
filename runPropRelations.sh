#!/usr/bin/env bash
# runs OriginalPropTest, takes as an input the directory of the experiment, by default this runs the OriginalPropTest without verification, thus all calls to OriginalPropTest are passed false 
# input must be something like "props/Multi-Mutation_1Mutation_False/"

alias runDiscovery='java -Djava.library.path-/home/soha/git/ProcessRepair/lib -classpath /home/soha/git/ProcessRepair/out/production/ProcessRepair:/home/soha/git/ProcessRepair/lib/org.eclipse.core.runtime-3.1.0.jar:/home/soha/git/ProcessRepair/lib/jkind.jar:/home/soha/git/ProcessRepair/lib/jkind-api.jar OriginalPropTest '

shopt -s expand_aliases


EXPIREMENTDIR=$1


runDiscovery $EXPIREMENTDIR/ wbs false | tee ${EXPIREMENTDIR}/log/WBS_Prop1_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ wbs false | tee ${EXPIREMENTDIR}/log/WBS_Prop3_OrigPropTest.log

runDiscovery $EXPIREMENTDIR/ tcas false | tee ${EXPIREMENTDIR}/log/TCAS_Prop1_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ tcas false | tee ${EXPIREMENTDIR}/log/TCAS_Prop2_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ tcas false | tee ${EXPIREMENTDIR}/log/TCAS_Prop4_OrigPropTest.log


runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop1_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop2_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop3_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop4_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop5_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop6_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop7_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop8_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop9_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop10_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop11_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop12_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop13_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_Prop14_OrigPropTest.log


runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop1_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop2_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop3_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop4_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop5_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop6_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop7_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop8_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop9_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_Prop10_OrigPropTest.log
