package com.Asm;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
	String asmFileName = args[0];
	String hackFileName = asmFileName.replace(".asm", ".hack");
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
