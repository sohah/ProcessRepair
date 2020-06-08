#!/usr/bin/perl
use strict; use warnings;


=pod
This scripts takes as an input (1) filenamae of stats where we want to look for specific properties. (2) another file name with all the properties we wish to look for
This script can be used to select the stats of tautology repair to investigate them deeper
sample of used commands


=cut

my $stateFileName = $ARGV[0];
my $propSelectionFileName = $ARGV[1];


open(PROPFILE, $propSelectionFileName) or die "Could not read from $propSelectionFileName], program halting.";

while(my $propline = <PROPFILE>)
{
  open(STATSFILE, $stateFileName) or die "Could not read from $stateFileName], program halting.";
  while(my $statsline = <STATSFILE>)
  {
  #print "$statsline";
  #print "$propline";
  if (index($statsline, $propline) != -1){
    print "$statsline";}
  }
  close STATSFILE;
}
close PROPFILE;
