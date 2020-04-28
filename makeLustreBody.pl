#!/usr/bin/perl

$filename = $ARGV[0];

open(FILE, $filename) or die "Could not read from $filename, program halting.";


# gpca counter needs to start from 10, since 0-9 are the already existing properties that we want to see if we have met them while trying to repair others.
# infusion counter needs to start from 12 since 0-11 are the already existing properties that we want to see if we have met them while trying to repair others. Note that for p4 and p14 are taken out and so p4 is actually p5 and p5 is actually p6 and so on. Since we cannot check with invalid prop.

my $counter = 12;
while(my $line = <FILE>)
{
  $line =~ s/\s+$//;
  my $prop = substr $line, 3, -1;
  print "p$counter";
  print "$prop\n";
  ++$counter;
}

for ( my $i = 1; $i < $counter; $i++ ) {
    print"p$i : bool;\n";
  }
close FILE;