#!/usr/bin/perl

=pod

foreach (@ARGV){
    #print "file: $_\n";
    open(FILE, $_) or die "Could not read from $_, program halting.";
    while (my $line=<FILE>) { if ($line) {$last = $line;} } print $last;
close FILE;
}
print "\n";
=cut



#!/usr/bin/perl

use autodie ':io';
foreach (@ARGV){

open(my $fh, '<', $_);
# get the data that is needed, then:
my $last_non_blank_line;
while (my $line = readline $fh) {
    # choose one of the following two lines, depending what you meant
    if ( $line =~ /\S/ ) { $last_non_blank_line = $line }
    # if ( line !~ /^$/ ) { $last_non_blank_line = $line }
}

print "$last_non_blank_line";
}