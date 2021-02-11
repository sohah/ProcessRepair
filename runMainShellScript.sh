#!/usr/bin/env bash
#creates two directories one for inclusive stats and another for non-inclusive. It splits the states from the input paramter according to that.
#input must be like "props/Multi-Mutation_1Mutation_False" and "~/git/ranger-discovery/src/DiscoveryExamples"
#where the first is the directory inside ProcessRepair we want for the new experiment and the second it the directory of the original files of the actual expirments, where we can find all the experiments details

PROCESSREPAIRDIR=$1
ORIGEXPERDIR=$2

echo "0. move results to ProcessRepair"

mkdir ${PROCESSREPAIRDIR}
mkdir ${PROCESSREPAIRDIR}/stats
mkdir ${PROCESSREPAIRDIR}/tightProp
mkdir ${PROCESSREPAIRDIR}/unique

cp ${ORIGEXPERDIR}/*/*/gpca_all_prop*.txt ${PROCESSREPAIRDIR}/tightProp/
cp ${ORIGEXPERDIR}/*/*/gpca_prop*_stats.txt ${PROCESSREPAIRDIR}/stats/
cp ${ORIGEXPERDIR}/*/*/gpca_unique_prop*.txt ${PROCESSREPAIRDIR}/unique/

cp ${ORIGEXPERDIR}/*/*/infusion_all_prop*.txt ${PROCESSREPAIRDIR}/tightProp/
cp ${ORIGEXPERDIR}/*/*/infusion_prop*_stats.txt ${PROCESSREPAIRDIR}/stats/
cp ${ORIGEXPERDIR}/*/*/infusion_unique_prop*.txt ${PROCESSREPAIRDIR}/unique/

cp ${ORIGEXPERDIR}/*/*/wbs_all_prop*.txt ${PROCESSREPAIRDIR}/tightProp/
cp ${ORIGEXPERDIR}/*/*/wbs_prop*_stats.txt ${PROCESSREPAIRDIR}/stats/
cp ${ORIGEXPERDIR}/*/*/wbs_unique_prop*.txt ${PROCESSREPAIRDIR}/unique/

cp ${ORIGEXPERDIR}/*/*/tcas_all_prop*.txt ${PROCESSREPAIRDIR}/tightProp/
cp ${ORIGEXPERDIR}/*/*/tcas_prop*_stats.txt ${PROCESSREPAIRDIR}/stats/
cp ${ORIGEXPERDIR}/*/*/tcas_unique_prop*.txt ${PROCESSREPAIRDIR}/unique/


echo " 1.creating directory structure"

mkdir ${PROCESSREPAIRDIR}_True
mkdir ${PROCESSREPAIRDIR}_True/Body
mkdir ${PROCESSREPAIRDIR}_True/log
mkdir ${PROCESSREPAIRDIR}_True/splitStats
mkdir ${PROCESSREPAIRDIR}_True/splitStats/noproof
mkdir ${PROCESSREPAIRDIR}_True/splitStats/noproof/aggregate
mkdir ${PROCESSREPAIRDIR}_True/stats
mkdir ${PROCESSREPAIRDIR}_True/tightProp
mkdir ${PROCESSREPAIRDIR}_True/unique


mkdir ${PROCESSREPAIRDIR}_False
mkdir ${PROCESSREPAIRDIR}_False/Body
mkdir ${PROCESSREPAIRDIR}_False/log
mkdir ${PROCESSREPAIRDIR}_False/splitStats
mkdir ${PROCESSREPAIRDIR}_False/splitStats/noproof
mkdir ${PROCESSREPAIRDIR}_False/splitStats/noproof/aggregate
mkdir ${PROCESSREPAIRDIR}_False/stats
mkdir ${PROCESSREPAIRDIR}_False/tightProp
mkdir ${PROCESSREPAIRDIR}_False/unique


echo "2. split stats to the two experiments, i.e., inclusive and non-inclusive"
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop1_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop1_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop2_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop2_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop3_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop3_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop4_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop4_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop5_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop5_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop6_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop6_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop7_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop7_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop8_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop8_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop9_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop9_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop10_stats.txt > ${PROCESSREPAIRDIR}_False/stats/gpca_prop10_stats.txt

sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop1_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop1_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop2_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop2_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop3_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop3_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop4_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop4_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop5_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop5_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop6_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop6_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop7_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop7_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop8_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop8_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop9_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop9_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop10_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop10_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop11_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop11_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop12_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop12_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop13_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop13_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop14_stats.txt > ${PROCESSREPAIRDIR}_False/stats/infusion_prop14_stats.txt

sed '/true,/d' ${PROCESSREPAIRDIR}/stats/tcas_prop1_stats.txt > ${PROCESSREPAIRDIR}_False/stats/tcas_prop1_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/tcas_prop2_stats.txt > ${PROCESSREPAIRDIR}_False/stats/tcas_prop2_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/tcas_prop4_stats.txt > ${PROCESSREPAIRDIR}_False/stats/tcas_prop4_stats.txt

sed '/true,/d' ${PROCESSREPAIRDIR}/stats/wbs_prop1_stats.txt > ${PROCESSREPAIRDIR}_False/stats/wbs_prop1_stats.txt
sed '/true,/d' ${PROCESSREPAIRDIR}/stats/wbs_prop3_stats.txt > ${PROCESSREPAIRDIR}_False/stats/wbs_prop3_stats.txt

#inclusive
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop1_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop1_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop2_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop2_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop3_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop3_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop4_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop4_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop5_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop5_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop6_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop6_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop7_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop7_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop8_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop8_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop9_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop9_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/gpca_prop10_stats.txt > ${PROCESSREPAIRDIR}_True/stats/gpca_prop10_stats.txt

sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop1_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop1_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop2_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop2_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop3_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop3_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop4_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop4_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop5_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop5_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop6_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop6_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop7_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop7_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop8_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop8_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop9_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop9_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop10_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop10_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop11_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop11_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop12_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop12_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop13_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop13_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/infusion_prop14_stats.txt > ${PROCESSREPAIRDIR}_True/stats/infusion_prop14_stats.txt

sed '/false,/d' ${PROCESSREPAIRDIR}/stats/tcas_prop1_stats.txt > ${PROCESSREPAIRDIR}_True/stats/tcas_prop1_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/tcas_prop2_stats.txt > ${PROCESSREPAIRDIR}_True/stats/tcas_prop2_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/tcas_prop4_stats.txt > ${PROCESSREPAIRDIR}_True/stats/tcas_prop4_stats.txt

sed '/false,/d' ${PROCESSREPAIRDIR}/stats/wbs_prop1_stats.txt > ${PROCESSREPAIRDIR}_True/stats/wbs_prop1_stats.txt
sed '/false,/d' ${PROCESSREPAIRDIR}/stats/wbs_prop3_stats.txt > ${PROCESSREPAIRDIR}_True/stats/wbs_prop3_stats.txt


grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop2_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop2.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop3_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop3.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop4_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop4.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop5_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop5.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop6_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop6.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop7_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop7.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop8_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop8.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop9_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop9.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/gpca_prop10_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/gpca_all_prop10.txt

grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop2_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop2.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop3_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop3.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop4_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop4.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop5_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop5.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop6_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop6.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop7_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop7.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop8_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop8.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop9_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop9.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop10_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop10.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop11_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop11.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop12_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop12.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop13_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop13.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/infusion_prop14_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/infusion_all_prop14.txt

grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/tcas_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/tcas_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/tcas_prop2_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/tcas_all_prop2.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/tcas_prop4_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/tcas_all_prop4.txt

grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/wbs_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/wbs_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_True/stats/wbs_prop3_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_True/tightProp/wbs_all_prop3.txt


#non-inclusive
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop2_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop2.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop3_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop3.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop4_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop4.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop5_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop5.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop6_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop6.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop7_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop7.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop8_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop8.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop9_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop9.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/gpca_prop10_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/gpca_all_prop10.txt

grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop2_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop2.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop3_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop3.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop4_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop4.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop5_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop5.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop6_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop6.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop7_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop7.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop8_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop8.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop9_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop9.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop10_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop10.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop11_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop11.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop12_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop12.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop13_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop13.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/infusion_prop14_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/infusion_all_prop14.txt

grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/tcas_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/tcas_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/tcas_prop2_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/tcas_all_prop2.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/tcas_prop4_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/tcas_all_prop4.txt

grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/wbs_prop1_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/wbs_all_prop1.txt
grep -o 'p1 = .*' ${PROCESSREPAIRDIR}_False/stats/wbs_prop3_stats.txt | sed 's/,//' > ${PROCESSREPAIRDIR}_False/tightProp/wbs_all_prop3.txt


echo "4.make lustre body to check."
./executeMakeBody.sh ${PROCESSREPAIRDIR}_True
./executeMakeBody.sh ${PROCESSREPAIRDIR}_False

echo "5.run OriginalPropTest for inclusive"
./runPropRelations.sh ${PROCESSREPAIRDIR}_True

echo "6.run OriginalPropTest for non-inclusive"
./runPropRelations.sh ${PROCESSREPAIRDIR}_False



echo "7.run SplitStatsForTaut, where unbounded  verification is used inclusive"
./runSplitStatsForTaut_General.sh ${PROCESSREPAIRDIR}_True/ ${ORIGEXPERDIR}/

echo "8.run SplitStatsForTaut, where unbounded  verification is used non-inclusive"
./runSplitStatsForTaut_General.sh ${PROCESSREPAIRDIR}_False/ ${ORIGEXPERDIR}/

echo "9.run SplitForTautAndLoose, no verification here - inclusive"
./runSplitStatsForTautAndLoose_General.sh ${PROCESSREPAIRDIR}_True/ ${ORIGEXPERDIR}/

echo "10.run SplitForTautAndLoose, no verification here - non-inclusive"
./runSplitStatsForTautAndLoose_General.sh ${PROCESSREPAIRDIR}_False/ ${ORIGEXPERDIR}/

echo "DONE!"
