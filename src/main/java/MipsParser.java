/*package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Scanner;

public class MipsParser {
    private Word[] memory= new Word[128];


    public void interpret(String fileName) throws IOException {
        FileReader fr= new FileReader(fileName);
        BufferedReader br= new BufferedReader(fr);
        String currentLine;
        while((currentLine = br.readLine())!= null){
            String[] line= currentLine.split(" ");
            for (int i=0; i<line.length ;i++) {
                switch(line[i]) {
                        case "print":
                            print(line[++i]);
                        break;
                        case "assign":
                            if(line[i+2].equals("input"))
                                assign(line[i+1],input());
                            else if(line[i+2].equals("readFile")){
                                assign(line[i+1],readFile(line[i+3]));
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
                            writeFile(line[i+1],line[i+2]);
                            i+=2;
                        break;
                        case "readFile":
                            readFile(line[++i]);
                        break;
                        default:
                        continue;
        }
        }

    }}

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

    public static void main(String[]args) {
        MipsParser parser = new MipsParser();
        //use the interpreter to read any program
        try {
            parser.interpret("src/main/programs/Program 3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}*/

