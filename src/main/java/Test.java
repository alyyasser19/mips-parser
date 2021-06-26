package main.java;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Parser x= new Parser();
        x.fillMemory("src/main/programs");
        x.schedule();
        x.printMemory();



//        System.out.println("Number of processes: "+ x.processCounter);






//        System.out.println(x.memory[x.getStart(1)+8].getKey());

    }
}
