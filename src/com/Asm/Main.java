package com.Asm;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	Parser p = new Parser(new File("test.asm"));
	System.out.println(p.getSymbols());
	System.out.println(p.getTypes());
	while (p.hasMoreCommands()) {
	    p.advance();
	    if (p.commandType().equals("C_COMMAND")) {
		System.out.println(p.jump());
	    }
	}
    }

}
