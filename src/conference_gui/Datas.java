



package conference_gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TextField;


public class Datas {

    public static Map readDataFile() throws IOException{
         Map<String, List<String>> map=new HashMap<>();
        boolean end_of_file=false;
        String string;
        FileInputStream fstream=new FileInputStream("data/database.dat");
        DataInputStream input_file=new DataInputStream(fstream);
        while(!end_of_file){
            try{
                string=input_file.readUTF();
                String[] token=string.split("[:,]");
                List<String> str_list=new ArrayList<>();
                str_list.add(token[1]);
                str_list.add(token[2]);
                map.put(token[0], str_list);
            }
            catch (EOFException e){
                end_of_file=true;
            }
        }
        return map;
    }
    
    public static boolean writeDataFile(TextField user, TextField password, TextField question) throws IOException{

        FileOutputStream fstream2=new FileOutputStream("data/database.dat");
        try (DataOutputStream output_file = new DataOutputStream(fstream2)) {
                output_file.writeUTF(user.getText()+":"+password.getText()+","+question.getText());
                return true;
        }
        catch (EOFException e){
                return false;
            }
    }
    
    public static Map readAdminData() throws IOException{
        Map<String, String> map=new HashMap<>();
        boolean end_of_file=false;
        String string;
        FileInputStream fstream=new FileInputStream("data/adminData.dat");
        DataInputStream input_file=new DataInputStream(fstream);
        while(!end_of_file){
            try{
                string=input_file.readUTF();
                String[] token=string.split("[:]");
                map.put(token[0], token[1]);
            }
            catch (EOFException e){
                end_of_file=true;
            }
        }
        return map;
    }
    
    public static void writeAdminData(Map new_map) throws IOException{
        
        FileOutputStream fstream2=new FileOutputStream("data/adminData.dat");
        try (DataOutputStream output_file = new DataOutputStream(fstream2)) {
            new_map.forEach((key, value)->{
                try {
                    output_file.writeUTF(key+":"+value);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
        }
    }
    
    public static void replaceData(Map changed_map) throws IOException{
        FileOutputStream fstream2=new FileOutputStream("data/database.dat");
        try (DataOutputStream output_file = new DataOutputStream(fstream2)) {
            changed_map.forEach((key,value)->{
                List<String> values=(List<String>) changed_map.get(key);
                try {
                    output_file.writeUTF(key+":"+values.get(0)+","+values.get(1));
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
        }
    }
}
