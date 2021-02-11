#!/usr/bin/env bash
# runs OriginalPropTest, takes as an input the directory of the experiment, by default this runs the OriginalPropTest without verification, thus all calls to OriginalPropTest are passed false 
# input must be something like "props/Multi-Mutation_1Mutation_False/"

alias runDiscovery='java -Djava.library.path-/home/soha/git/ProcessRepair/lib -classpath /home/soha/git/ProcessRepair/out/production/ProcessRepair:/home/soha/git/ProcessRepair/lib/org.eclipse.core.runtime-3.1.0.jar:/home/soha/git/ProcessRepair/lib/jkind.jar:/home/soha/git/ProcessRepair/lib/jkind-api.jar OriginalPropTest '

shopt -s expand_aliases


EXPIREMENTDIR=$1


runDiscovery $EXPIREMENTDIR/ wbs false | tee ${EXPIREMENTDIR}/log/WBS_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ tcas false | tee ${EXPIREMENTDIR}/log/TCAS_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ infusion false | tee ${EXPIREMENTDIR}/log/INFUSION_OrigPropTest.log
runDiscovery $EXPIREMENTDIR/ gpca false | tee ${EXPIREMENTDIR}/log/Alarm_OrigPropTest.log
