#!/usr/bin/perl

foreach (@ARGV){
    #print "file: $_\n";
    open(FILE, $_) or die "Could not read from $_, program halting.";
    while (my $line=<FILE>) { if ($line) {$last = $line;} } print $last;
close FILE;
}
print "\n";