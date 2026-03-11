#!/bin/sh

JAVAC=$(command -v javac)
JAVA=$(command -v java)


if [ -z "$JAVAC" ]; then
    echo "no javac found"
    exit
fi

OUT="out"
SRC="src/com/Asm"
PKG="com.Asm"
mkdir -p out
$JAVAC -d "$OUT" "$SRC"/*.java


if [ "$1" = "run" ]; then
    if [ -z "$JAVA" ]; then
	echo "no java found"
	exit
    fi

    $JAVA -cp "$OUT" "$PKG".Main $2
fi



if [ "$1" = "jar" ]; then
    JAR=$(command -v jar)
    echo "Main-Class: $PKG.Main" > "$OUT/MANIFEST.MF"
    if [ -z "$JAR" ]; then
	echo "no jar found"
	exit
    fi

    $JAR cfm "Assembler.jar" "$OUT/MANIFEST.MF" -C "$OUT" com
fi
