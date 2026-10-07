What is JDK, JRE, JVM

JDK is Java Development kit which contains tools that help in
development such as compiler, debugger, JRE. JRE is Java runtime
environment which contains java specific class libraries along
with JVM (Java virtual machine) which converts byte code into 
machine code.It also has its architecture with loaders, linking,
execution engine, memory.

What is bytecode

JDK command javac converts .java file into .class file which is
a byte code. Then JVM command java converts .class file into 
machine code. Basically it is a class file which are instructions
for the JVM.

What does “write once, run anywhere” mean (1–2 short paragraphs)

When code is converted to byte code (.class file) any machine that
has a JVM can execute the byte code instructions & it does not need
any additional compilation. But the catch is JVM should be present
in the other machines without it byte code cannot run.