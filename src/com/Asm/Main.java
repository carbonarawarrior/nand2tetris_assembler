package com.Asm;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	Parser p = new Parser(new File(args[0]));
	System.out.println(args[0]);
	System.out.println(p.getSymbols());
	System.out.println(p.getTypes());
	while (p.hasMoreCommands()) {
	    p.advance();
	    if (p.commandType().equals("C_COMMAND")) {
		System.out.println("JUMP: " + p.jump());
	    }
	    if (p.commandType().equals("C_COMMAND")) {
		System.out.println("DEST: " + p.dest());
	    }
	    if (p.commandType().equals("C_COMMAND")) {
		System.out.println("COMP: " + p.comp());
	    }
	} 
	
    }

}
