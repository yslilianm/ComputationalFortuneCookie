package sample;

import javafx.application.Application;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //"throws Exception" makes bufferWriter/bufferReader deal with exception  automatically
    public static void main(String[] args)
    //throws Exception{
    {
        Scanner in = new Scanner(System.in);
        File file = new File("test.txt");
        ArrayList<String> answerText = new ArrayList<>();
        ArrayList<Integer> answerInt = new ArrayList<>();

        //Create BufferReader and BufferWriter
        //Writing while reading files one line a time
        //source: https://github.com/a-r-d/java-1-class-demos/blob/master/files-reading-and-writing/examples/ReadAndWriteStreamsAndTransform.java
        try {
            BufferedReader bReader = new BufferedReader(new FileReader("New_Year_Plan.txt"));
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
            String line = bReader.readLine();


            while (line != null) {
                System.out.println(line);
                bWriter.write(line + "\n");

                if (line.contains("?")) {
                    String input = in.nextLine();
                    answerText.add(input);
                    if (input.contains(" ")) {
                        answerInt.add(Transfer.getLongestLen(input.split(" ")));
                    } else
                        answerInt.add(input.length());
                    // write the file.
                    bWriter.write(input + "\n");
                    bWriter.flush();
                }
                // get the next line
                line = bReader.readLine();
            }
            // close up all of the resources.
            bReader.close();
            bWriter.close();

            //Assign user name to Form class for png filename
            String newFileName = answerText.get(0);
            Form.name = newFileName;
/*        //For testing
        System.out.println(newFileName + answerInt);
        System.out.println(newFileName + answerText);*/


            //Rename filename with user's name
            File newFile = new File(newFileName + ".txt");
            System.out.println(file.renameTo(newFile));

        } catch (Exception e) {
            System.out.println("Oops, something goes wrong...");
        }


        //Set user-defined parameter with user input
        Form.cameraAngle = Transfer.transferForCamera(answerInt.get(1));
        Form.colorSeed = Transfer.transferForColor(answerInt.get(2));
        Form.pullHeight = answerInt.get(3);
        Form.pullNum = answerInt.get(4);

        // launch application from other class
        // resource: https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class
        Application.launch(Form.class, args);

    }

}