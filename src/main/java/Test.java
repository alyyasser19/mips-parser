package main.java;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Parser x= new Parser();
        x.fillMemory("src/main/programs");
        x.schedule();
        System.out.println("memory Content:");

        int currentProgram = 0;
        for(Word word : x.memory){

            if(word.getKey().equals("pid")){
                currentProgram= (int) word.getValue();
            }

            if(word.getKey().equals("variables")){
                System.out.println("Variables for program :"+ currentProgram);
                for(Word cur: (Word[])word.getValue()){
                    try{
                    System.out.println("Variable "+ cur.getKey()+": "+cur.getValue());}
                    catch (NullPointerException e){
                        break;
                    }
                }
                continue;
            }

            System.out.println("type: "+word.getKey());
            System.out.println("value: "+word.getValue());
        }



//        System.out.println("Number of processes: "+ x.processCounter);






//        System.out.println(x.memory[x.getStart(1)+8].getKey());

    }
}
