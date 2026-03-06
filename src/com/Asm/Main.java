package com.Asm;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	Parser p = new Parser(new File("test.asm"));
	System.out.println(p.getLines());
    }

}
