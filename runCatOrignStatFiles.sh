#!/usr/bin/env bash
cat $1/stats/gpca_prop*.txt > $1/stats/gpca_stats.txt
cat $1/stats/infusion_prop*.txt > $1/stats/infusion_stats.txt
cat $1/stats/tcas_prop*.txt > $1/stats/tcas_stats.txt
cat $1/stats/wbs_prop*.txt > $1/stats/wbs_stats.txt


