package com.Asm;

/*
 * the thing that actually gives us the binary
 * I will implement it line by line, and in Main we will write out to the file
 * line by line.
 *
 * I think to have this write to a file directly would be too messy
 * so we r gonna do it this way
 *
 * these two funcs are essentially a wrapper to the Parser that is inside this assembler
 *
 * hasMoreCommands() -> boolean - tells is if there are more commands to parse
 * advance() -> void - advances the line that is pointed to by the Assembler
 * translateLine() -> String (binary rep) - gives us the binary form of the line we are pointing at
 *
 *
 *
 */
public class Assembler {

    private Parser parser;
    private Code code;
    private SymbolTable symbols;

    public Assembler(Parser p, Code c) {
	this.parser = p;
	this.code = c;
//	this.symbols = s;
    }

    private String decToBin(int decnum) {
	String binnum = "";
	int r;
	while (decnum > 1) {
	    r = decnum % 2;
	    binnum = r + binnum;
	    decnum = decnum / 2;
	}

	if (decnum == 1) {
	    binnum = 1 + binnum;
	}

	
	if (decnum == 0) {
	    binnum = 0 + binnum;
	}

	if (binnum.length() > 16) {
	    binnum = binnum.substring(0,17);
	}

	while (binnum.length() < 16) {
	    binnum = "0" + binnum;
	}
	return binnum;
    }
    public boolean hasMoreCommands() {
	return this.parser.hasMoreCommands();
    }

    public void advance() {
	this.parser.advance();
    }

    public String translateLine() {
	String type = this.parser.commandType();

	if (type.equals("C_COMMAND")) {
	    String dest = this.code.dest(this.parser.dest());
	    String comp = this.code.comp(this.parser.comp());
	    String jump = this.code.jump(this.parser.jump());

	    return "111" + comp + dest + jump; 
	} else if (type.equals("A_COMMAND")) {
	    //implement code to handle non ints later
	    return this.decToBin(Integer.valueOf(this.parser.symbol()));
	} else if (type.equals("L_COMMAND")) {
	    //placeholder, we dont need to do this yet
	    return "";
	}

	return "syntax error";
    }


}
