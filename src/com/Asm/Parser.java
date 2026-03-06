package com.Asm;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/*
 * This object should read a .asm file and kick out
 * all comments and such
 *
 */
public class Parser {

    private File asmFile;
    private ArrayList<String> symbols;

    public Parser(File f) {
	this.asmFile = f;
	this.symbols = new ArrayList<String>();

	try {
	    Scanner fin = new Scanner(this.asmFile);
	    String line;

	    while (fin.hasNextLine()) {
		line = fin.nextLine();
		if (line.indexOf("//") != -1) {
		    int commentIndex = line.indexOf("//");
		    System.out.println(commentIndex);
		    line = line.substring(commentIndex);
		}
		this.symbols.add(line);
	    }
	} catch (Exception e) {
	    System.out.println("IO Error");
	}
    }

    public ArrayList<String> getLines() {
	return this.symbols;
    }

    
}
