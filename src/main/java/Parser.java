package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public Word[] memory= new Word[38];
    private static int processCounter=1;

    public void fillMemory(String parentDirctory) throws IOException {
        String program= parentDirctory+"/Program 1.txt";
        File programFile= new File(program);
        int counter=1;
        int offset=0;
        while(programFile.exists()){
            //position 0: pid position 1: state position 2: pc position 3: start position 4: end position 5:curOffset position 6: variables position 7: process
            Process curProcess= new Process(processCounter, program, offset);
            Word pid=new Word("pid", curProcess.getPid());
            memory[offset]=pid;
            Word state=new Word("state", curProcess.isProcessState());
            memory[offset+1]=state;
            Word pc=new Word("pc", curProcess.getPc());
            memory[offset+2]=pc;
            Word start=new Word("start", curProcess.getStart());
            memory[offset+3]=start;
            Word end=new Word("end", curProcess.getEnd());
            memory[offset+4]=end;
            Word curOffset=new Word("curOffset", curProcess.getCurOffset());
            memory[offset+5]=curOffset;
            Word variables=new Word("variables", curProcess.getVariables());
            memory[offset+6]=variables;
            Word process=new Word("process", curProcess);
            memory[offset+7]=process;
            for(int i=0;i<curProcess.getInstructions().length;i++){
                Word curInstruction= new Word("instruction"+i,curProcess.getInstructions()[i]);
                memory[offset+8+i]=curInstruction;
            }
            offset+=curProcess.getInstructions().length+8;
            processCounter++;
            program= parentDirctory+"/Program "+ processCounter+".txt";
            System.out.println(program);
            programFile= new File(program);
        }
        processCounter-=1;
    }

    public String readFile(String fileName) throws IOException {
        String text="";
        try{
            String variable;
            if(memory.get(fileName)!=null)
                variable=memory.get(fileName);
            else
                variable=fileName;
            String file = variable;
            Path path = Paths.get(file);
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            String curLine;
            while ((curLine = bufferedReader.readLine()) != null){
                text+=curLine+"\n";
            }
            bufferedReader.close();}
        catch(IOException e){
            System.out.println("Please Enter a Valid Location");
        }
        return text;
    }
    public void writeFile(String fileName, String input) throws IOException {
        try{
            String variable;
            if(memory.get(fileName)!=null)
                variable=memory.get(fileName);
            else
                variable=fileName;
            FileWriter fr = new FileWriter(variable,true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(memory.get(input));
            br.write("\n");
            br.close();
            fr.close();}
        catch(IOException e){
            System.out.println("Please Enter a Valid Location");
        }
    }

    public void assign(String var, String val){
        memory.put(var, val);
        System.out.println("content of"+" "+var+" "+"is:"+"  "+memory.get(var));
    }

    public void add(String var1, String var2){
        double val1;
        double val2;
        try {
            if (memory.get(var1) != null)
                val1 = Double.parseDouble(memory.get(var1));
            else{
                System.out.println("Variable Does Not Exist");
                return;}
            if (memory.get(var2) != null)
                val2 = Double.parseDouble(memory.get(var2));
            else
                val2 = Double.parseDouble(var2);
            double sum = val1 + val2;
            memory.put(var1, String.valueOf(sum));
            System.out.println("content of" + " " + var1 + " " + "is:" + "  " + memory.get(var1));
        }
        catch(Exception e){
            System.out.println("Invalid Datatype");
        }
    }

    public void print(Object var){
        if(memory.get(var)==null)
            System.out.println(var);
        else{
            System.out.println(memory.get(var));
        }
    }

    public String input (){
        Scanner sc = new Scanner(System.in);
        System.out.println("input?");
        String input = sc.nextLine();
        return input;
    }
}
