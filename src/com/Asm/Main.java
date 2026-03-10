package com.Asm;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	Parser p = new Parser(new File(args[0]));
	Code c = new Code();
	Assembler a = new Assembler(p, c);
	while (a.hasMoreCommands()) {
	    a.advance();
	    
	    System.out.println(a.translateLine());
	} 
	
    }

}
