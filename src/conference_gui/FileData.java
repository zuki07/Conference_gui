



package conference_gui;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.TextField;


public class FileData {

    public static Map readFile() throws FileNotFoundException{
        java.io.File file=new java.io.File("database.txt");
        Scanner input_file=new Scanner(file);
        Map<String, List<String>> map=new HashMap<>();
        while(input_file.hasNext()){
            List<String> values=new ArrayList<>();
            String key=input_file.nextLine();
            String [] token=key.split("[:,]");
            values.add(token[1]);
            values.add(token[2]);
            map.put(token[0],values);
        }
        return map;
    }
    
    public static boolean writeFile(TextField user, TextField password, TextField question){
        try {
            FileWriter fwriter=new FileWriter("database.txt", true);
            PrintWriter output_file;
            output_file = new PrintWriter(fwriter);
            output_file.println(user.getText()+":"+password.getText()+","+question.getText());
            output_file.close();
            return true;

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return false;
    }
}
