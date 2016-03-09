#!/usr/local/bin/perl

@files = `find src/ -name '*.java'`;
foreach (@files){
	chomp;
	s/^src/classes/;
	s/\.java$/.class/;
	print "$_ ";
}
