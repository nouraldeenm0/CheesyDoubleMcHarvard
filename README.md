````markdown
# CheesyDoubleMcHarvard — Simple Java CPU Simulator

A minimal, word-addressable CPU simulator written in Java.  
This small university project implements:
- A tiny instruction set  
- Basic register and memory models  
- A simple program file parser  
- Error handling for invalid values and instruction arguments  

````

## 🏃 Quick Start

Compile and run from the project root:

```sh
javac *.java
java CPU
````

The simulator reads instructions from [`Program.txt`](Program.txt).
Example program lines:

```
MOVI R1 33
ADD R2 R1
```

---

## 📁 Main Files Overview

| File                                                                                                                      | Description                                                                                                                             |
| ------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| [`CPU.java`](CPU.java)                                                                                                    | Main simulator loop: fetch, decode, execute, and print register values. Initializes registers and loads the program from `Program.txt`. |
| [`Instruction.java`](Instruction.java)                                                                                    | Models decoded instructions (R-type and I-type) and validates arguments.                                                                |
| [`Register.java`](Register.java)                                                                                          | Represents a simple named register with a 16-bit value constraint.                                                                      |
| [`Memory.java`](Memory.java)                                                                                              | Contains static arrays for instructions and data memory.                                                                                |
| [`ProgramFileParser.java`](ProgramFileParser.java)                                                                        | Helper class to read and parse `Program.txt`.                                                                                           |
| [`ValueOutOfBounds.java`](ValueOutOfBounds.java) / [`InvalidInstructionArguments.java`](InvalidInstructionArguments.java) | Custom exceptions used for validation.                                                                                                  |

---

## ✏️ Modifying the Program

* Edit [`Program.txt`](Program.txt) to add or modify instruction lines.
* Re-run:

  ```sh
  javac *.java && java CPU
  ```

---

## 🧮 Registers and Memory

* **Registers:** General-purpose registers `R0`..`R63`.
* **Special registers:** `CPU.program_counter`, `CPU.status`.
* **Register values:** 0–65535 (`2^16 − 1`), unsigned 16-bit.
* **Memory:**

  * Instruction memory: `Memory.instructions`
  * Data memory: `Memory.data`
* Program lines are loaded from `Program.txt` via `ProgramFileParser.readFile`.
* Decoding and execution logic are implemented in `CPU.decode` and `CPU.execute`.
* Instruction type mapping is defined in `Instruction.getTypeDependingOnName`.

---

## 📘 Instruction Reference

| Mnemonic | Type | Syntax           | Semantics                                             | Flags / Notes                                           | Source                       |
| -------- | ---- | ---------------- | ----------------------------------------------------- | ------------------------------------------------------- | ---------------------------- |
| **ADD**  | R    | `ADD Rdest Rsrc` | `Rdest = Rdest + Rsrc`                                | Sets zero flag if result == 0 (`bit 0` of `CPU.status`) | `CPU.execute`                |
| **SUB**  | R    | `SUB Rdest Rsrc` | `Rdest = Rdest - Rsrc`                                | Sets zero flag if result == 0                           | `CPU.execute`                |
| **MUL**  | R    | `MUL Rdest Rsrc` | `Rdest = Rdest * Rsrc`                                | Sets zero flag if result == 0                           | `CPU.execute`                |
| **EOR**  | R    | `EOR Rdest Rsrc` | `Rdest = Rdest ^ Rsrc` (bitwise XOR)                  | Sets zero flag if result == 0                           | `CPU.execute`                |
| **BR**   | R    | `BR Rdest Rsrc`  | `CPU.program_counter = Rdest \| Rsrc` (bitwise OR)    | Sets zero flag if `PC == 0`                             | `CPU.execute`                |
| **MOVI** | I    | `MOVI Rdest imm` | `Rdest = imm`                                         | Immediate parsed in `CPU.decode`                        | `CPU.decode`, `Instruction`  |
| **BEQZ** | I    | `BEQZ Rsrc imm`  | If `Rsrc == 0` then `PC += 1 + imm` (relative branch) | Branch offset handled in `CPU.execute`                  | `CPU.execute`                |
| **ANDI** | I    | `ANDI Rdest imm` | `Rdest = Rdest & imm`                                 | I-type op                                               | `CPU.execute`                |
| **SAL**  | I    | `SAL Rdest imm`  | `Rdest = Rdest << imm`                                | Arithmetic left shift                                   | `CPU.execute`                |
| **SAR**  | I    | `SAR Rdest imm`  | `Rdest = Rdest >> imm`                                | Arithmetic right shift                                  | `CPU.execute`                |
| **LDR**  | I    | `LDR Rdest addr` | `Rdest = Memory.data[addr]`                           | Address from immediate                                  | `CPU.execute`, `Memory.data` |
| **STR**  | I    | `STR Rsrc addr`  | `Memory.data[addr] = Rsrc`                            | Address from immediate                                  | `CPU.execute`, `Memory.data` |

---

## ⚙️ Parsing & Operand Rules

* Instruction lines are split on whitespace in `CPU.decode`.
* The decoder tries `Integer.parseInt()` on the third token:

  * Success → I-type instruction
  * Failure → R-type instruction
* Register names must follow the format `R#` (where `#` is 0–63).
* Immediate bounds are checked in `Instruction`, though note a minor bug:
  the code checks `this.immediate` before assigning `immediate_value` in the constructor.

---

## 🧩 Notes & Limitations

* Registers are limited to **16-bit unsigned** values (`0–65535`).
* Immediate values have range checks in `Instruction.java`.
* This repository was created for a **university assignment** and is intentionally minimal for learning purposes.

```
```
