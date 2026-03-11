# NAND2TETRIS Assembler

Assembler for the hack assembly language specified in nand2tetris.
Written in java cuz i need to practice for AP test.


## Building
You can build on a unix-like system by running

`./make.sh`

or

`./make.sh jar`


if you want a new jar file.

## Running

run the supplied jar file with

`java -jar Assembler.jar [ARGS]`

right now you can supply -o to specify where you want to write out.
otherwise it just takes your file and replaces .asm with .hack.
