package GuavaLang;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Guava {

    public static void main(String[] args){
        String inputFile = args[0];
        String fileContents = readFileToString(inputFile);
        if (fileContents == null){
            return;
        }

        ArrayList<Token> tokens = Lexer.tokenise(fileContents);
        // for (Token token : tokens){
        //     System.out.println("{" + token.value + ", " + token.type.name() + "}");
        // }
        Parser parser = new Parser();
        Program prog = parser.toSyntaxTree(tokens);
        System.out.print(prog.toString());

    }

    static String readFileToString(String filename){
        try{
            FileReader fr = new FileReader(filename);
            BufferedReader fileRead = new BufferedReader(fr);
            StringBuilder str = new StringBuilder();
            char ch;
            int current;
            while ((current=fileRead.read())!= -1){
                ch = (char)current;
                str.append(ch);
            }
            fileRead.close();
            return str.toString();
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
            return null;
        } catch (IOException e){
            System.out.println("IO Exception!!");
            return null;
        }
    }
}