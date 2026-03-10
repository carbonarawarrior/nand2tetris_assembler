package com.Asm;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

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

    public Parser(File f) {
	this.asmFile = f;
	this.symbols = new ArrayList<String>();
	this.types   = new ArrayList<String>();
	this.currentLine = -1;

	try {
	    Scanner fin = new Scanner(this.asmFile);
	    String line;

	    while (fin.hasNextLine()) {
		line = fin.nextLine();
		if (line.indexOf("//") != -1) {
		    line = line.substring(0, line.indexOf("//")).trim();
		}

		if (!line.isEmpty()) {
		    this.symbols.add(line);
		    if (line.contains("@")) {
			this.types.add("A_COMMAND");
		    } else if (line.contains("(") && line.contains(")")) {
			this.types.add("L_COMMAND");
		    } else {
			this.types.add("C_COMMAND");
		    }

		}

	    }
	} catch (Exception e) {
	    System.out.println("IO Error");
	}
    }

    public String symbol() {
	if (this.commandType().equals("A_COMMAND")) {
	    return this.symbols.get(this.currentLine).substring(1);
	}
	if (this.commandType().equals("L_COMMAND")) {
	    return this.symbols.get(this.currentLine).substring(1, this.symbols.get(this.currentLine).length()-2);
	}
	return null;
    }
    
    public boolean hasMoreCommands() {
	if (this.currentLine < this.symbols.size() - 1) {
	    return true;
	}

	return false;
    }

    public void advance() {
	this.currentLine++;
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
	}
	if (semiIndex != -1) {
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
