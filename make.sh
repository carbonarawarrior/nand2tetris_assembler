#!/bin/sh

JAVAC=$(command -v javac)

if [ -z "$JAVAC" ]; then
    echo "no javac found"
    exit
fi

OUT="out"
SRC="src/com/Asm"
mkdir -p out
$JAVAC -d "$OUT" "$SRC"/*.java
