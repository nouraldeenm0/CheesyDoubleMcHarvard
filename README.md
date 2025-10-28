# CheesyDoubleMcHarvard — Simple Java CPU Simulator

This is a small university project: a minimal, word-addressable CPU simulator written in Java.  
It implements a tiny instruction set, basic register and memory models, a simple program file parser, and error handling for invalid values and instruction arguments.

## Quick start

Compile and run from the project root:

```sh
javac *.java
java CPU
```

The simulator reads instructions from [Program.txt](Program.txt). Example program lines:
- `MOVI R1 33`
- `ADD R2 R1`

## What each main file does

- [`CPU`](CPU.java) — main simulator loop: fetch, decode, execute, and print register values. Also initializes registers and loads program from [Program.txt](Program.txt).  
- [`Instruction`](Instruction.java) — models decoded instructions (R-type and I-type) and validates arguments.  
- [`Register`](Register.java) — simple named register with a 16-bit value constraint.  
- [`Memory`](Memory.java) — static arrays for instructions and data.  
- [`ProgramFileParser`](ProgramFileParser.java) — helper to read and parse `Program.txt`.  
- [`ValueOutOfBounds`](ValueOutOfBounds.java) and [`InvalidInstructionArguments`](InvalidInstructionArguments.java) — custom exceptions used for validation.

## How to modify the program
- Edit [Program.txt](Program.txt) to change or add instruction lines.
- Re-run `javac *.java && java CPU` after changes.

## Notes and limitations
- Registers are limited to 16-bit unsigned range in `Register.java`.  
- Immediate values use a limited range validated in [`Instruction`](Instruction.java).  
- This repo was created as a university assignment and is intentionally minimal.
