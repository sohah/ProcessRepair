#!/usr/bin/perl

$filename = $ARGV[0];

open(FILE, $filename) or die "Could not read from $filename, program halting.";

my $counter = 1;
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