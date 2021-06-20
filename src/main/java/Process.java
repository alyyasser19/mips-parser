package main.java;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    //position 0: pid position 1: state position 2: pc position 3: start position 4: end position 5:curOffset position 6: variables position 7: process

    public Process(int pid, String fileName, int offset) throws IOException {
        int noOfInstructions= countLineBufferedReader(fileName);

        this.pid = pid;
        instructions = new String[noOfInstructions];
        instructions = setInstructions(fileName, noOfInstructions);
        variables = new Word[noOfInstructions];
        processState = false;
        pc = 0;
        curOffset = 8;
        start = offset;
        end= noOfInstructions + 8 + offset;

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

    public static String[] setInstructions(String fileName,int size) throws IOException {
        String[] set= new String[size];
        try{
        FileReader fr= new FileReader(fileName);
        BufferedReader br= new BufferedReader(fr);
        String currentLine;
        int i=0;
        while((currentLine = br.readLine())!= null){
            set[i]=currentLine;
            i++;
        }}
        catch(IOException e){
            System.out.println("Invalid filename!!");
            }
        return set;
    }
    public String[] getInstructions() {
        return instructions;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public Word[] getVariables() {
        return variables;
    }

    public void setVariables(Word[] variables) {
        this.variables = variables;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public boolean isProcessState() {
        return processState;
    }

    public void setProcessState(boolean processState) {
        this.processState = processState;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurOffset() {
        return curOffset;
    }

    public void setCurOffset(int curOffset) {
        this.curOffset = curOffset;
    }

}
