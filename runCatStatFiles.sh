#!/usr/bin/env bash
cat $1/stats/gpca_*noTautStatFile.txt > $1/splitStats/gpca_noTautStat.txt
cat $1/stats/gpca_*tautStatFile.txt > $1/splitStats/gpca_tautStat.txt
cat $1/stats/gpca_*tautOtherStatFile.txt > $1/splitStats/gpca_tautOtherStat.txt

cat $1/stats/infusion_*noTautStatFile.txt > $1/splitStats/infusion_noTautStat.txt
cat $1/stats/infusion_*tautStatFile.txt > $1/splitStats/infusion_tautStat.txt
cat $1/stats/infusion_*tautOtherStatFile.txt > $1/splitStats/infusion_tautOtherStat.txt

cat $1/stats/tcas_*noTautStatFile.txt > $1/splitStats/tcas_noTautStat.txt
cat $1/stats/tcas_*tautStatFile.txt > $1/splitStats/tcas_tautStat.txt
cat $1/stats/tcas_*tautOtherStatFile.txt > $1/splitStats/tcas_tautOtherStat.txt

cat $1/stats/wbs_*noTautStatFile.txt > $1/splitStats/wbs_noTautStat.txt
cat $1/stats/wbs_*tautStatFile.txt > $1/splitStats/wbs_tautStat.txt
cat $1/stats/wbs_*tautOtherStatFile.txt > $1/splitStats/wbs_tautOtherStat.txt