package main.java;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Parser x= new Parser();
        x.fillMemory("src/main/programs");
        int c=1;
        for(int i=0;i<x.memory.length;i++){
            System.out.println(x.memory[i].getValue());
            System.out.println(c++);
        }
    }
}
