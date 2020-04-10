#!/usr/bin/perl

$filename = $ARGV[0];

open(FILE, $filename) or die "Could not read from $filename, program halting.";

my $counter = 0;
while(my $line = <FILE>)
{
  $line =~ s/\s+$//;
  my $prop = substr $line, 3, -1;
  print "p$counter";
  print "$prop\n";
  ++$counter;
}

for ( my $i = 0; $i < $counter; $i++ ) {
    print"p$counter : bool;\n";
  }
close FILE;