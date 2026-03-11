package com.Asm;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
	if (args.length < 1) {
	    System.err.println("You must provide an assembly file!");
	    System.exit(-1);
	}

	String asmFileName = "";
	String hackFileName = "";
	//i want to be able to add some options so we have to loop through our array to do so
	for (int i = 0; i < args.length; i++) {
	    switch (args[i]) {
		case "-o":
		    if (i + 1 >= args.length || args[i + 1].charAt(0) == '-') {
			System.err.println("You must provide a name to write out to!");
			System.exit(-1);
		    }
		    
		    if (!hackFileName.isEmpty()) {
			System.err.println("You have already specified a file to write out to!");
			System.exit(-1);
		    }
		    
		    hackFileName = args[i+1];
		    i++; //skip the next file so its not treated as input
		    break;
		default:
		    if (!asmFileName.isEmpty()) {
			System.err.println("Too many input files!");
			System.exit(-1);
		    }
		    //we assume that it is a .asm file
		    asmFileName = args[i];
		    break;
	    }
	}


	if (hackFileName.isEmpty()) {
	    hackFileName = asmFileName.replace(".asm", ".hack");
	}

	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(hackFileName)))){
	    Parser p = new Parser(new File(asmFileName));
	    Code c = new Code();
	    Assembler a = new Assembler(p, c);

	    //this is a dumb workaround but i need it to get rid of the trailing newline i have when using out.println
	    List<String> writeOut = new ArrayList<String>();
	    while (a.hasMoreCommands()) {
		a.advance();
		
		
		writeOut.add(a.translateLine());
	    } 

	    out.print(String.join("\n", writeOut));
	} catch (Exception e) {
	    System.err.println("Error Occured when reading/writing to file");
	    System.err.println(e);
	}
	
    }

}
