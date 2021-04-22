#!/usr/bin/env bash
# this is running the valid properties and using mutation for them, should be called from /home/soha/git/ProcessRepair


alias runDiscovery='java -Djava.library.path-/home/soha/git/ProcessRepair/lib -classpath /home/soha/git/ProcessRepair/out/production/ProcessRepair:/home/soha/git/ProcessRepair/lib/org.eclipse.core.runtime-3.1.0.jar:/home/soha/git/ProcessRepair/lib/jkind.jar:/home/soha/git/ProcessRepair/lib/jkind-api.jar SplitStatsForMatchAndRelevant '

shopt -s expand_aliases

#varying the directory name to run the split on different versions.
EXPIREMENTDIR=props/Multi-Mutation_2Mutation_True/



runDiscovery $EXPIREMENTDIR infusion prop1 
runDiscovery $EXPIREMENTDIR infusion prop2 
runDiscovery $EXPIREMENTDIR infusion prop3 
runDiscovery $EXPIREMENTDIR infusion prop4 
runDiscovery $EXPIREMENTDIR infusion prop5 
runDiscovery $EXPIREMENTDIR infusion prop6 
runDiscovery $EXPIREMENTDIR infusion prop7 
runDiscovery $EXPIREMENTDIR infusion prop8 
runDiscovery $EXPIREMENTDIR infusion prop9 
runDiscovery $EXPIREMENTDIR infusion prop10 
runDiscovery $EXPIREMENTDIR infusion prop11 
runDiscovery $EXPIREMENTDIR infusion prop12 
runDiscovery $EXPIREMENTDIR infusion prop13 
runDiscovery $EXPIREMENTDIR infusion prop14 


cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_equivRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_equivRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_relevantRepairStatFile.txt >  ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_relevantRepairStatFile.txt
cat ${EXPIREMENTDIR}splitStats/noproof/infusion_prop*_tightRepairStatFile.txt  > ${EXPIREMENTDIR}splitStats/noproof/aggregate/infusion_tightRepairStatFile.txt
