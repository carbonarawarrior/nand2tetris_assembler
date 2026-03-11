package com.Asm;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/*
 * This object should read a .asm file and kick out
 * all comments and such
 *
 * hasMoreCommands() -> boolean, tells us if has more commands
 * advance() -> void, moves the selected command up
 * commandType() -> A_COMMAND, C_COMMAND, or L_COMMAND, tells us the type of command, important to ensure we call the right things in the right places/
 * jump() -> String, returns the jump symbol in a C_COMMAND, assumes current command is a C_COMMAND
 * dest() -> String, returns the dest symbol in a C_COMMAND, assumes current command is a C_COMMAND
 * comp() -> String, returns the comp symbol in a C_COMMAND, assumes current command is a C_COMMAND
 * symbol() -> String, returns the symbol of a A_COMMAND or a L_COMMAND
 */
public class Parser {

    private File asmFile;
    private ArrayList<String> symbols;
    private ArrayList<String> types;
    private int currentLine;
    private SymbolTable symbolTable;


    //public Parser(File f, int i) {
	//try {
	    //Parser p = new Parser(f);
	    //this.asmFile = p.asmFile;
	    //this.symbols = p.symbols;
	    //this.types   = p.types;  
	    //this.symbolTable = p.symbolTable;
	    //this.currentLine = p.currentLine;
//
	//} catch (Exception e) {
	    //System.out.println("oop");
	//}
    //}
    public Parser(File f) throws IOException {
	this.asmFile = f;
	this.symbols = new ArrayList<String>();
	this.types   = new ArrayList<String>();
	this.symbolTable = new SymbolTable();
	this.currentLine = -1;

        Scanner fin = new Scanner(this.asmFile);
        String line;

	int lineNum = 0;
	while (fin.hasNextLine()) {
	    line = fin.nextLine();
	    if (line.indexOf("//") != -1) {
		line = line.substring(0, line.indexOf("//")).trim();
	    }

	    if (!line.isEmpty()) {
		if (line.contains("@")) {
		    this.types.add("A_COMMAND");
		    this.symbols.add(line.trim());
		    lineNum++;
		} else if (line.contains("(") && line.contains(")")) {
		    this.symbolTable.addEntry(line.trim(), lineNum);
		} else {
		    this.types.add("C_COMMAND");
		    this.symbols.add(line.trim());
		    lineNum++;
		}
	    }
	}
    }

    public String symbol() {
	if (this.symbols.get(this.currentLine).trim().substring(1).matches("\\d+")) {
	    return this.symbols.get(this.currentLine).trim().substring(1);
	} else if (this.symbolTable.contains(this.symbols.get(currentLine).trim())) {
	    return "" + this.symbolTable.GetAddress(this.symbols.get(this.currentLine).trim());
	} else {
	    this.symbolTable.addEntry(this.symbols.get(this.currentLine).trim());
	    return "" + this.symbolTable.GetAddress(this.symbols.get(this.currentLine).trim());
	}
    }
    
    public boolean hasMoreCommands() {
	if (this.currentLine < this.symbols.size() - 1) {
	    return true;
	}

	return false;
    }

    public void advance() {
	this.currentLine++;
	//System.out.println(this.symbols.get(this.currentLine));
	//System.out.println(this.types.get(this.currentLine));
	//System.out.println(this.currentLine);
    }

    //not a nand2tetris method but very useful for the double loop for resolving labels
    //i dont wanna create another list in Assembler for basically no reason
    public void reset() {
	this.currentLine = -1;
    }

    public String commandType() {
	return types.get(currentLine);
    }

    public String jump() {
	int semiIndex = this.symbols.get(this.currentLine).indexOf(";");
	String jumpStr = null;
	if (semiIndex != -1) {
	    jumpStr = this.symbols.get(this.currentLine).substring(semiIndex+1);
	}

	if (semiIndex == -1) {
	    return "";
	}
	switch (jumpStr) {
	    case "JGT":
	    case "JEQ":
	    case "JGE":
	    case "JLT":
	    case "JNE":
	    case "JLE":
	    case "JMP":
		return jumpStr;
	}
	return null;
    }

    public String dest() {
	int eqIndex= this.symbols.get(this.currentLine).indexOf("=");
	String destString = null;
	if (eqIndex != -1) {
	    destString = this.symbols.get(this.currentLine).substring(0, eqIndex);
	}

	if (eqIndex == -1) {
	    return "";
	}
	switch (destString.trim()) {
	    case "M":
	    case "D":
	    case "MD":
	    case "A":
	    case "AM":
	    case "AD":
	    case "AMD":
		return destString.trim();
	}
	return null;
    }
    public String comp() {
	int eqIndex= this.symbols.get(this.currentLine).indexOf("=");
	int semiIndex = this.symbols.get(this.currentLine).indexOf(";");
	String compString = null;
	if (eqIndex != -1) {
	    compString = this.symbols.get(this.currentLine).substring(1+eqIndex);
	} else if (semiIndex != -1) {
	    compString = this.symbols.get(this.currentLine).substring(0, semiIndex);
	}

	switch (compString.trim()) {
	    case "0":
	    case "1":
	    case "-1":
	    case "D":
	    case "A":
	    case "!D":
	    case "!A":
	    case "-D":
	    case "-A":
	    case "D+1":
	    case "A+1":
	    case "D-1":
	    case "A-1":
	    case "D+A":
	    case "D-A":
	    case "A-D":
	    case "D&A":
	    case "D|A":
	    case "M":
	    case "!M":
	    case "-M":
	    case "M+1":
	    case "M-1":
	    case "D+M":
	    case "D-M":
	    case "M-D":
	    case "D&M":
	    case "D|M":
		return compString.trim();
	    default:
		return null;
	}
    }
    //non nand2tetris methods for debugging
    public ArrayList<String> getSymbols() {
	return this.symbols;
    }

    public ArrayList<String> getTypes() {
	return this.types;
    }

}
