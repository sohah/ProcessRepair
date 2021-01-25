#!/usr/bin/env bash
cat $1/splitStats/gpca_*noTautStatFile.txt > $1/splitStats/gpca_noTautStat.txt
cat $1/splitStats/gpca_*tautStatFile.txt > $1/splitStats/gpca_tautStat.txt
cat $1/splitStats/gpca_*tautOtherStatFile.txt > $1/splitStats/gpca_tautOtherStat.txt

cat $1/splitStats/infusion_*noTautStatFile.txt > $1/splitStats/infusion_noTautStat.txt
cat $1/splitStats/infusion_*tautStatFile.txt > $1/splitStats/infusion_tautStat.txt
cat $1/splitStats/infusion_*tautOtherStatFile.txt > $1/splitStats/infusion_tautOtherStat.txt

cat $1/splitStats/tcas_*noTautStatFile.txt > $1/splitStats/tcas_noTautStat.txt
cat $1/splitStats/tcas_*tautStatFile.txt > $1/splitStats/tcas_tautStat.txt
cat $1/splitStats/tcas_*tautOtherStatFile.txt > $1/splitStats/tcas_tautOtherStat.txt

cat $1/splitStats/wbs_*noTautStatFile.txt > $1/splitStats/wbs_noTautStat.txt
cat $1/splitStats/wbs_*tautStatFile.txt > $1/splitStats/wbs_tautStat.txt
cat $1/splitStats/wbs_*tautOtherStatFile.txt > $1/splitStats/wbs_tautOtherStat.txt