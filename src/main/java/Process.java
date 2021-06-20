package main.java;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Process {
    String[] instructions;
    Word[] variables;

    //PCB

    int pid;
    boolean processState;
    int pc;
    int start;
    int end;
    int curOffset;
    //offset??


    public Process(int pid, String fileName, int offset) {
        int noOfInstructions= countLineBufferedReader(fileName);

        this.pid = pid;
        instructions = new String[noOfInstructions];
        instructions = setInstructions(fileName, noOfInstructions);
        variables = new Word[noOfInstructions];
        processState = false;
        pc = 0;
        start = offset;
        end= noOfInstructions + 7;

    }

    public static int countLineBufferedReader(String fileName) {

        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static String[] setInstructions(String fileName,int size){

    }

}
