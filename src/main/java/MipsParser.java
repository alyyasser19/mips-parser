package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

public class MipsParser {
    private Hashtable<String,String>  memory= new Hashtable();


    public void interpret(String fileName) throws IOException {
        FileReader fr= new FileReader(fileName);
        BufferedReader br= new BufferedReader(fr);
        boolean instruction=false;
        String currentInstruction;
        ArrayList variables= new ArrayList();
        String currentLine;
        while((currentLine = br.readLine())!= null){
            String[] line= currentLine.split(" ");
            for (int i=0; i<line.length ;i++) {
                System.out.println(line[i]);
                switch(line[i]) {
                        case "print":
                        print(line[i++]);
                        break;
                        case "assign":
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
                        readFile(line[i++]);
                        break;
default:
        continue;
        }
        }

    }}

    public String readFile(String fileName) throws IOException {
        // src/main/programs/Program 1.txt
        String file = fileName;
        Path path = Paths.get(file);
        BufferedReader bufferedReader = Files.newBufferedReader(path);
        String text="";
        String curLine;
        while ((curLine = bufferedReader.readLine()) != null){
            text+=curLine+"\n";
        }
        bufferedReader.close();
        return text;
    }
    public void writeFile(String fileName, String input) throws IOException {
        FileWriter fr = new FileWriter(fileName,true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(input);
        br.close();
        fr.close();
    }

    public void assign(String var, String val){
        memory.put(var, val);
    }

    public void add(String var1, String var2){
        double val1=  Double.parseDouble(memory.get(var1));
        double val2=  Double.parseDouble(memory.get(var2));
        double sum= val1+val2;
        memory.put(var1, String.valueOf(sum));
    }

    public void print(Object var){
        System.out.println(memory.get(var));
    }


    public static void main(String[]args){
        MipsParser parser= new MipsParser();
        try {
            parser.interpret("src/main/programs/Program 2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

