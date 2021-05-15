package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class MipsParser {
    private Hashtable  memory= new Hashtable();



    public void interpret(){

    }
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

    public void assign(String var, Object val){
        memory.put(var, val);
    }

    public void add(double var1, double var2){
        double val1= (double) memory.get(var1);
        double val2= (double) memory.get(var2);
        double sum= val1+val2;
        memory.put(var1, sum);
    }

    public void print(Object var){
        System.out.println(memory.get(var));
    }

}
