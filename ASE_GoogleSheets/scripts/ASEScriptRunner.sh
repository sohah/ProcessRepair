#!/usr/bin/env bash

scripts/ASEGoogleSheetStat.sh gpca
scripts/ASEGoogleSheetStat.sh infusion
scripts/ASEGoogleSheetStat.sh tcas
scripts/ASEGoogleSheetStat.sh wbs

CURRDIR=~/git/ProcessRepair/ASE_GoogleSheets

mkdir ${CURRDIR}/table1
mkdir ${CURRDIR}/table2
mkdir ${CURRDIR}/table3
mkdir ${CURRDIR}/table4
mkdir ${CURRDIR}/table5

#${CURRDIR}/table 1 - consolidation of the two expirements
cat ${CURRDIR}/*cantRepair*.txt > ${CURRDIR}/table1/cantRepair.txt
cat ${CURRDIR}/*equiv*.txt *tight*.txt > ${CURRDIR}/table1/match.txt
less ${CURRDIR}/*relevant*.txt > table1/relevant.txt
cat ${CURRDIR}/table1/match.txt ${CURRDIR}/table1/relevant.txt > ${CURRDIR}/table1/repaired.txt
cat ${CURRDIR}/*unRepaired*.txt > ${CURRDIR}/table1/unRepaired.txt
cat ${CURRDIR}/*looseRepair*.txt > ${CURRDIR}/table1/looseRepair.txt
cat ${CURRDIR}/*unknown*.txt > ${CURRDIR}/table1/unknownRepair.txt
cat ${CURRDIR}/*tautProved*.txt > ${CURRDIR}/table1/tautProved.txt

#${CURRDIR}/table2 - consolidation of match and relevant over two expirements.
cat ${CURRDIR}/*equiv.txt ${CURRDIR}/*tight.txt > ${CURRDIR}/table2/match.txt
cat ${CURRDIR}/*relevant.txt > ${CURRDIR}/table2/relevant.txt

#${CURRDIR}/table 3 - benchmarks over the two experiments
cp ${CURRDIR}/*.txt ${CURRDIR}/table3

#${CURRDIR}/table 4 - inclusive non-inclusive vs. match and relevant
cat ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/*_tightRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/*tightRepairStatFile.txt > ${CURRDIR}/table4/matchInclusive.txt
cat ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/*tightRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/*tightRepairStatFile.txt > ${CURRDIR}/table4/matchNonInclusive.txt

cat ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/*relevantRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/*relevantRepairStatFile.txt> ${CURRDIR}/table4/relevantInclusive.txt
cat ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/*relevantRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/*relevantRepairStatFile.txt> ${CURRDIR}/table4/relevantNonInclusive.txt


${CURRDIR}/table 5 - 1fault, 2fault vs. match and relevant
cat ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/*tightRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/*tightRepairStatFile.txt > ${CURRDIR}/table5/1FaultMatch.txt

cat ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/*relevant*.txt ~/git/ProcessRepair/props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/*relevant*.txt  > ${CURRDIR}/table5/1FaultRelevant.txt


cat ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/*tightRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/*equivRepairStatFile.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/*tightRepairStatFile.txt > ${CURRDIR}/table5/2FaultMatch.txt

cat ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/*relevant*.txt ~/git/ProcessRepair/props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/*relevant*.txt  > ${CURRDIR}/table5/2FaultRelevant.txt
