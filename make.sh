#!/bin/sh


OUT="out"
SRC="src/com/Asm"
PKG="com.Asm"
JAVAC=$(command -v javac)
JAVA=$(command -v java)
JAR=$(command -v jar)


if [ -z "$JAVAC" ]; then
    echo "no javac found"
    exit
fi

mkdir -p out
$JAVAC -d "$OUT" "$SRC"/*.java





if [ "$1" = "jar" ]; then
    echo "Main-Class: $PKG.Main" > "$OUT/MANIFEST.MF"
    if [ -z "$JAR" ]; then
	echo "no jar found"
	exit
    fi

    $JAR cfm "Assembler.jar" "$OUT/MANIFEST.MF" -C "$OUT" com
fi
