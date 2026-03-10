package com.Asm;
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
	    while (a.hasMoreCommands()) {
		a.advance();
		
		out.println(a.translateLine());
	    } 
	} catch (Exception e) {
	    System.err.println("Error Occured when reading/writing to file");
	}
	
    }

}
