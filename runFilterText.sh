#!/usr/bin/env bash
#used to filter out mutants from the stats for which they are already matching or there is no valid synthesis for them.


sed '/NO_VALID_SYNTHESIS_FOR_GRAMMAR/d' $1/splitStats/gpca_noTautStat.txt | sed '/ALREADY_MATCHING/d' >  $1/splitStats/gpca_noTautStat_noMatch.txt
sed '/NO_VALID_SYNTHESIS_FOR_GRAMMAR/d' $1/splitStats/infusion_noTautStat.txt | sed '/ALREADY_MATCHING/d' >  $1/splitStats/infusion_noTautStat_noMatch.txt
sed '/NO_VALID_SYNTHESIS_FOR_GRAMMAR/d' $1/splitStats/tcas_noTautStat.txt | sed '/ALREADY_MATCHING/d' >  $1/splitStats/tcas_noTautStat_noMatch.txt
sed '/NO_VALID_SYNTHESIS_FOR_GRAMMAR/d' $1/splitStats/wbs_noTautStat.txt | sed '/ALREADY_MATCHING/d' >  $1/splitStats/wbs_noTautStat_noMatch.txt

