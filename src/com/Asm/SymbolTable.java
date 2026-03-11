package com.Asm;

import java.util.HashMap;

/*
 * addEntry(String symbol, int address) -> void - Adds symbol, address to the table
 * contains(String symbol) -> boolean - reports if symbol is in the table
 * GetAddress(String symbol) -> int - gets the address of symbol
 *
 *
 */
public class SymbolTable {

    private HashMap<String, Integer> table;
    private int memAddr;
    
    public SymbolTable() {
	this.table = new HashMap<String, Integer>();
	this.table.put("@SP", 0);
	this.table.put("@LCL", 1);
	this.table.put("@ARG", 2);
	this.table.put("@THIS", 3);
	this.table.put("@THAT", 4);

	this.table.put("@R0", 0);
	this.table.put("@R1", 1);
	this.table.put("@R2", 2);
	this.table.put("@R3", 3);
	this.table.put("@R4", 4);
	this.table.put("@R5", 5);
	this.table.put("@R6", 6);
	this.table.put("@R7", 7);
	this.table.put("@R8", 8);
	this.table.put("@R9", 9);
	this.table.put("@R10", 10);
	this.table.put("@R11", 11);
	this.table.put("@R12", 12);
	this.table.put("@R13", 13);
	this.table.put("@R14", 14);
	this.table.put("@R15", 15);

	this.table.put("@SCREEN", 16384);
	this.table.put("@KBD", 24576);
	this.memAddr = 16;
    }

    //im changing this, memory addresses for symbols are now handled by this symbol table
    public void addEntry(String symbol) {
	this.table.put(symbol, this.memAddr);
	memAddr++;
    }
    //i jst realized i needed the old func anyways . . .
    public void addEntry(String symbol, int address) {
	this.table.put(symbol, address);
    }

    public boolean contains(String symbol) {
	return this.table.containsKey(symbol);
    }

    public int GetAddress(String symbol) {
	return this.table.get(symbol);
    }
}
