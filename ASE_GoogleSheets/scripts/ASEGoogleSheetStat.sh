#!/usr/bin/env bash

#should be invoked rom ASE_GoogleSheets directory

BENCHMARK=$1
cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_equivRepairStatFile.txt  ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_equivRepairStatFile.txt  ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_equivRepairStatFile.txt  ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_equivRepairStatFile.txt  > ${BENCHMARK}_equiv.txt


cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_tightRepairStatFile.txt  ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_tightRepairStatFile.txt  ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_tightRepairStatFile.txt  ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_tightRepairStatFile.txt > ${BENCHMARK}_tight.txt

cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_relevantRepairStatFile.txt  ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_relevantRepairStatFile.txt  ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_relevantRepairStatFile.txt  ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_relevantRepairStatFile.txt  > ${BENCHMARK}_relevant.txt

cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_cantRepairStatFile.txt ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_cantRepairStatFile.txt   ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_cantRepairStatFile.txt   ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_cantRepairStatFile.txt  > ${BENCHMARK}_cantRepairStatFile.txt


cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_tautProvedStatFile.txt ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_tautProvedStatFile.txt   ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_tautProvedStatFile.txt   ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_tautProvedStatFile.txt  > ${BENCHMARK}_tautProvedStatFile.txt


cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_looseRepairStatFile.txt ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_looseRepairStatFile.txt   ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_looseRepairStatFile.txt   ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_looseRepairStatFile.txt > ${BENCHMARK}_looseRepairStatFile.txt


cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_unRepairedStatFile.txt ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_unRepairedStatFile.txt   ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_unRepairedStatFile.txt   ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_unRepairedStatFile.txt > ${BENCHMARK}_unRepairedStatFile.txt

cat ../props/Multi-Mutation_1Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_unknownTautStatFile.txt ../props/Multi-Mutation_1Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_unknownTautStatFile.txt   ../props/Multi-Mutation_2Mutation_False/splitStats/noproof/aggregate/${BENCHMARK}_unknownTautStatFile.txt   ../props/Multi-Mutation_2Mutation_True/splitStats/noproof/aggregate/${BENCHMARK}_unknownTautStatFile.txt > ${BENCHMARK}_unknownTautStatFile.txt
