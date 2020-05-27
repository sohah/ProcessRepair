#!/usr/bin/perl
use strict; use warnings;


=pod
This scripts takes as an input (1) filenamae of the set of tighter properties we have found. (2) name of the benchmark "infusion, tcas, wbs, or gpca".
Also, it assumes the existance of shellFiles, and OrigProps files for each benchmark to operate.
The outut is a spec defined in lustre.

sample of used commands
perl makeLustreBody.pl props/tightProp/TCAS_Prop1  tcas > props/Body/tcas_prop1.lus
 ~/jkindNoRand/jkind props/Body/tcas_prop1.lus

=cut


my $tightPropCounter;
my @invalidProp = (0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
my $origPropFileName;
my $shellFileName;
my $spec = $ARGV[1];

if($spec eq "infusion"){
    #$invalidProp[4] = 1;
    #$invalidProp[14] = 1;
    $tightPropCounter = 15;
    $origPropFileName="props/InfusionOrigProps";
    $shellFileName="props/InfusionShell";
} elsif($spec eq "tcas"){
    $invalidProp[3] = 1;
    $tightPropCounter = 5;
    $origPropFileName="props/TCASOrigProps";
    $shellFileName="props/TCASShell";
} elsif($spec eq "wbs"){
    $invalidProp[2] = 1;
    $invalidProp[4] = 1;
    $invalidProp[5] = 1;
    $tightPropCounter = 6;
    $origPropFileName="props/WBSOrigProps";
    $shellFileName="props/WBSShell";
} else { #must be the gpca
    $tightPropCounter = 11;
    $origPropFileName="props/AlarmOrigProps";
    $shellFileName="props/AlarmShell";
}

my @tightPropsStr;
my @declarations;
my $tightPropFileName = $ARGV[0];

open(FILE, $tightPropFileName) or die "Could not read from $tightPropFileName, program halting.";

while(my $line = <FILE>)
{
  $line =~ s/\s+$//;
  # my $prop = substr $line, 3, -1;
  my $prop = substr $line, 3;
  push(@tightPropsStr,"p$tightPropCounter");
  push(@tightPropsStr,"$prop\n");
  #print "p$tightPropCounter";
  #print "$prop\n";
  ++$tightPropCounter;
}
close FILE;



for ( my $i = 1; $i < $tightPropCounter; $i++ ) {
    if(($spec eq "infusion" or $spec eq "tcas" or $spec eq "wbs") && $i<=14){
        if($invalidProp[$i] != 1){
            push(@declarations,"p$i : bool;\n");}}
    else{
      push(@declarations,"p$i : bool;\n");
    }

  }


local $/;
open(FILE, $shellFileName) or die "Can't read file 'shellFileName' [$!]\n";
my $shellFile = <FILE>;
close (FILE);


local $/;
open(FILE, $origPropFileName) or die "Can't read file 'origPropFileName' [$!]\n";
my $origPropFile = <FILE>;
close (FILE);


print $shellFile;
print "\n@declarations";
print "let\n";
print "$origPropFile";
print "\n";
print @tightPropsStr;
print "tel;\n";
