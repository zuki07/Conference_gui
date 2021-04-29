



package conference_gui;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.TextField;


public class Data {

   public static Map readDataFile() throws FileNotFoundException{
        java.io.File file=new java.io.File("data/database.txt");
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
    
    public static boolean writeDataFile(TextField user, TextField password, TextField question){
        try {
            FileWriter fwriter=new FileWriter("data/database.txt", true);
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
    
    public static Map readAdminData() throws FileNotFoundException{
        java.io.File file=new java.io.File("data/adminData.txt");
        Scanner input_file=new Scanner(file);
        Map<String, String> map=new HashMap<>();
        while(input_file.hasNext()){
            String key=input_file.nextLine();
            String [] token=key.split(":");
            map.put(token[0],token[1]);
        }
        return map;
    }
    
    public static void writeAdminData(Map new_map) throws FileNotFoundException{
        try {
            FileWriter fwriter;
            new FileWriter("data/adminData.txt", false).close();
            fwriter=new FileWriter("data/adminData.txt", true);
            PrintWriter output_file;
            output_file = new PrintWriter(fwriter);
            new_map.forEach((key,value)->output_file.println(key+":"+value));
            output_file.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void replaceData(Map changed_map) throws FileNotFoundException{
        try {
            FileWriter fwriter;
            new FileWriter("data/database.txt", false).close();
            fwriter=new FileWriter("data/database.txt", true);
            PrintWriter output_file;
            output_file = new PrintWriter(fwriter);
            changed_map.forEach((key,value)->{
                List<String> values=(List<String>) changed_map.get(key);
                output_file.println(key+":"+values.get(0)+","+values.get(1));
                    });
            output_file.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
