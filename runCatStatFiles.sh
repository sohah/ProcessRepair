#!/usr/bin/env bash
cat $1/stats/gpca_*noTautStatFile.txt > $1//gpca_noTautStat.txt
cat $1/stats/gpca_*tautStatFile.txt > $1/gpca_tautStat.txt
cat $1/stats/gpca_*tautOtherStatFile.txt > $1/gpca_tautOtherStat.txt

cat $1/stats/infusion_*noTautStatFile.txt > $1/infusion_noTautStat.txt
cat $1/stats/infusion_*tautStatFile.txt > $1/infusion_tautStat.txt
cat $1/stats/infusion_*tautOtherStatFile.txt > $1/infusion_tautOtherStat.txt

cat $1/stats/tcas_*noTautStatFile.txt > $1/tcas_noTautStat.txt
cat $1/stats/tcas_*tautStatFile.txt > $1/tcas_tautStat.txt
cat $1/stats/tcas_*tautOtherStatFile.txt > $1/tcas_tautOtherStat.txt

cat $1/stats/wbs_*noTautStatFile.txt > $1/wbs_noTautStat.txt
cat $1/stats/wbs_*tautStatFile.txt > $1/wbs_tautStat.txt
cat $1/stats/wbs_*tautOtherStatFile.txt > $1/wbs_tautOtherStat.txt