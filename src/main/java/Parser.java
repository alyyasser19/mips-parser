package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
    public Word[] memory= new Word[38];
    public static int processCounter=1;
    int curProgram= 1;

    public void schedule(){
        Queue programQueue= new LinkedList<>();
        //Queue programs
        int exe=0;
        for(int i=1; i<=processCounter; i++){
            programQueue.add(i);
        }
        // to Dynamically get the first program in the queue
        programQueue.remove(curProgram);
        programQueue.add(curProgram);
        while(!programQueue.isEmpty()){

            int quantas= 0;
            int start= getStart(curProgram)+getCurOffset(curProgram);
            int end= getEnd(curProgram);
            if(start==end){
                curProgram= (int) programQueue.remove();
                continue;
            }
            System.out.println("\n"+"Current Program: "+curProgram+"\n");
            if(start!=end){
                setState(curProgram,true);
                execute((String) memory[start].getValue());
                quantas++;
                setCurOffset(curProgram,getCurOffset(curProgram)+1);
                exe++;
                setPc(curProgram,getPc(curProgram)+1);
                System.out.println("Current Instruction: "+ memory[start].getValue());
            }
            if(start==end){
                System.out.println("\n"+"Number of Quantas for program "+curProgram+" is: " +quantas+"\n");
                System.out.println("\n"+"Program "+curProgram+" is done."+"\n");
                setState(curProgram,false);
                curProgram= (int) programQueue.remove();
                System.out.println("\n"+"Executed: "+exe+" Instructions"+"\n");
                continue;
            }
            start= getStart(curProgram)+getCurOffset(curProgram);


            if(start!=end){
                setState(curProgram,true);
                execute((String) memory[start].getValue());
                quantas++;
                setCurOffset(curProgram,getCurOffset(curProgram)+1);
                exe++;
                setPc(curProgram,getPc(curProgram)+1);
                System.out.println("Current Instruction: "+ memory[start].getValue());
                System.out.println("\n"+"Executed: "+exe+" Instructions"+"\n");
            }

            if(start==end){
                System.out.println("Program "+curProgram+" is done.");
            }
            System.out.println("Number of Quantas for program "+curProgram+" is: " +quantas);
            setState(curProgram,false);
            int prevProgram=curProgram;
            curProgram= (int) programQueue.remove();
            int oldStart;
            int oldEnd;
            if(programQueue.size()!=1 ||programQueue.size()!=0) {
                 oldStart = 1;
                 oldEnd = 0;
            }else {
                 oldStart = start;
                 oldEnd = end;
            }
            start= getStart(curProgram)+getCurOffset(curProgram);
            end= getEnd(curProgram);
            if(programQueue.size()==0){
                return;
            }
            if(start!=end){
                programQueue.add(curProgram);
            }

            if(oldStart!=oldEnd){
                System.out.println("added " + prevProgram+" back to the queue");
                programQueue.add(prevProgram);
            }

        }
    }

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
            programFile= new File(program);
            printPCB((Integer) pid.getValue());
        }
        processCounter-=1;
    }

    public int getStart(int pid){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid")){
                curProgram= (int) word.getValue();
            }
            if(word.getKey().equals("start") && curProgram==pid)
                return (int) word.getValue();
        }
        return -1;
    }

    public int getEnd(int pid){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("end") && curProgram==pid)
                return (int) word.getValue();
        }
        return -1;
    }

    public boolean getState(int pid){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("state") && curProgram==pid)
                return (boolean) word.getValue();
        }
        return false;
    }

    public int getCurOffset(int pid){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("curOffset") && curProgram==pid)
                return (int) word.getValue();
        }
        return -1;
    }

    public int getPc(int pid){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("pc") && curProgram==pid)
                return (int) word.getValue();
        }
        return -1;
    }

    public Object getVariable(int pid, String variable){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("variables") && curProgram==pid)
                for(Word var : (Word[])word.getValue())
                    if(var.getKey().equals(variable))
                        return var.getValue();
        }
        return null;
    }

    public void setState(int pid, boolean state){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("state") && curProgram==pid)
                word.setValue(state);
        }
    }

    public void setPc(int pid, int state){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("pc") && curProgram==pid)
                word.setValue(state);
        }
    }

    public void setCurOffset(int pid, int state){
        int curProgram= 1;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("curOffset") && curProgram==pid)
                word.setValue(state);
        }
    }

    public void setVariable(int pid,String variable ,Object state){
        int curProgram= 1;
        int variables= 0;
        for(Word word : memory){
            if(word.getKey().equals("pid"))
                curProgram= (int) word.getValue();
            if(word.getKey().equals("variables") && curProgram==pid){
                for(Word var : (Word[])word.getValue()) {
                    if(var==null)
                        continue;

                    if(var!=null)
                        variables++;
                    if (var.getKey().equals(variable)) {
                        var.setValue(state);
                        return;
                    }
                }
                Word newWord= new Word(variable,state);
                ((Word[])word.getValue())[variables]=newWord;
            }
        }
    }




    public String readFile(String fileName) throws IOException {
        String text="";
        try{
            String variable;
            if(getVariable(curProgram,fileName)!=null) {
                System.out.println("read from"+" "+fileName+" "+"::"+"  "+getVariable(curProgram,fileName));
                variable = (String) getVariable(curProgram, fileName);
            }
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
            if(getVariable(curProgram,fileName)!=null)
                variable= (String) getVariable(curProgram,fileName);
            else
                variable=fileName;
            FileWriter fr = new FileWriter(variable,true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write((String) getVariable(curProgram,input));
            br.write("\n");
            br.close();
            fr.close();}
        catch(IOException e){
            System.out.println("Please Enter a Valid Location");
        }
    }

    public void assign(String var, String val){
        setVariable(curProgram,var,val);
        System.out.println("content of"+" "+var+" "+"is:"+"  "+getVariable(curProgram,var));
    }

    public void add(String var1, String var2){
        double val1;
        double val2;
        try {
            if (getVariable(curProgram,var1) != null)
                val1 = Double.parseDouble((String) getVariable(curProgram,var1));
            else{
                System.out.println("Variable Does Not Exist");
                return;}
            if (getVariable(curProgram,var2) != null)
                val2 = Double.parseDouble((String) getVariable(curProgram,var2));
            else
                val2 = Double.parseDouble(var2);
            double sum = val1 + val2;
            setVariable(curProgram,var1,sum);
            System.out.println("content of" + " " + var1 + " " + "is:" + "  " + getVariable(curProgram,var1));
        }
        catch(Exception e){
            System.out.println("Invalid Datatype");
        }
    }

    public void print(Object var){
        try{
            System.out.println("Printed: "+getVariable(curProgram, (String) var) +"\n"+ "from: " +var);
}
        catch(NullPointerException e){
            System.out.println(var);
        }
    }

    public String input (){
        Scanner sc = new Scanner(System.in);
        System.out.println("input?");
        String input = sc.nextLine();
        return input;
    }

    public void execute(String instuction){
        String[] line= instuction.split(" ");

        for (int i=0; i<line.length ;i++) {
            switch(line[i]) {
                case "print":
                    print(line[++i]);
                    break;
                case "assign":
                    if(line[i+2].equals("input"))
                        assign(line[i+1],input());
                    else if(line[i+2].equals("readFile")){
                        try {
                            assign(line[i+1],readFile(line[i+3]));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        i+=3;
                        continue;
                    }
                    else
                        assign(line[i+1],line[i+2]);
                    i+=2;
                    break;
                case "add":
                    add(line[i+1],line[i+2]);
                    i+=2;
                    break;
                case "writeFile":
                    try {
                        writeFile(line[i+1],line[i+2]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i+=2;
                    break;
                case "readFile":
                    try {
                        readFile(line[++i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    continue;
            }
    }
    }
    public void printMemory(){
        System.out.println("\n"+"memory Content:"+"\n"+"-----------------------------------");
        int currentProgram = 0;
        int j=0;
        for(Word word : memory){
            if(word.getKey().equals("pid")){
                currentProgram= (int) word.getValue();
            }

            if(word.getKey().equals("variables")){
                System.out.println("\n"+"Variables for program :"+ currentProgram+"\n"+"------------------------------------");
                int i=0;
                for(Word cur: (Word[])word.getValue()){
                    try{
                        System.out.println("Variable "+ cur.getKey()+" at position "+j+"."+i+": "+cur.getValue());
                    }
                    catch (NullPointerException e){
                        break;
                    }
                    i++;
                }
                continue;
            }

            System.out.println("\n"+"at position: "+j+"\n");
            System.out.println("type: "+word.getKey());
            System.out.println("value: "+word.getValue());
            j++;
        }
    }

    public void printPCB(int pid){
        System.out.println("\n"+"PCB for program: "+pid+" "+"\n"+"-----------------------------------");
        int currentProgram = 0;

        for(Word word : memory){
            try{
            if(word.getKey().equals("pid")){
                currentProgram= (int) word.getValue();
            }
            if(currentProgram != pid){
                continue;
            }
            if(word.getKey().equals("variables")){
                continue;
            }

            if(word.getKey().contains("instruction")){
                continue;
            }
            }
            catch(NullPointerException e){
                continue;
            }
            System.out.println("\n");
            System.out.println("type: "+word.getKey());
            System.out.println("value: "+word.getValue());
        }
    }
}
