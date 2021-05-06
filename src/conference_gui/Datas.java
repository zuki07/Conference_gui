



package conference_gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class Datas {

    private static String str="";
    private static int log_in_count=0;
    private static Map <String, Map<String, String>> user_map=new HashMap<>();
    private static Map <String, String> admin_map=new HashMap();
    

    public static void addUserToMap(String map_key, Map<String,String> map_values){
        user_map.put(map_key, map_values);
        try {
            writeDataFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void replaceUserPassword(String user, String password) throws IOException{
        user_map.get(user).replace("password", password);
        writeDataFile();
    }
    
    public static Map<String, Map<String,String>> getUserMap(){
        return user_map;
    }
    
    public static Map getAdminMap(){
        return admin_map;
    }
    
    public static void writeDataFile() throws IOException{
        FileOutputStream fstream2=new FileOutputStream("data/database.dat");
        try (DataOutputStream output_file = new DataOutputStream(fstream2)) {
            user_map.forEach((key, value)->{
                str="";
                value.forEach((keys, values) ->{
                    str=str+keys+"/"+values+",";
                });
                try {
                    output_file.writeUTF(key+":"+str);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
            output_file.close();
            fstream2.close();
        }
    }
    
    public static void readDataFile() throws IOException{
        String string;
        FileInputStream fstream=new FileInputStream("data/database.dat");
        DataInputStream input_file = new DataInputStream(fstream);
        while (input_file.available()>0) {
            string=input_file.readUTF();
            String[] token=string.split(":");
            String[] str_token=token[1].split(",");
            Map <String, String> temp_map=new HashMap<>();
            for (String str_token1 : str_token) {
                String[] temp2_token = str_token1.split("/");
                temp_map.put(temp2_token[0], temp2_token[1]);
            }
            user_map.put(token[0], temp_map);
        }
        System.out.println(user_map);
    }

    public static void writeAdminData(Map new_map) throws IOException{
        admin_map.forEach((key, value)->{
            value=new_map.get(key).toString();
        });
        FileOutputStream fstream2=new FileOutputStream("data/adminData.dat");
        try (DataOutputStream output_file = new DataOutputStream(fstream2)) {
            admin_map.forEach((key, value)->{
                try {
                    output_file.writeUTF(key+":"+value);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
        }
    }
    
    public static void readAdminMap() throws IOException{
        String string;
        FileInputStream fstream=new FileInputStream("data/adminData.dat");
        DataInputStream input_file=new DataInputStream(fstream);
        while(input_file.available()>0){
                string=input_file.readUTF();
                String[] token=string.split(":");
                admin_map.put(token[0], token[1]);
        }
    }
    
    public static void changeCount(int count){
        log_in_count=1;
    }
    
    public static int getCount(){
        return log_in_count;
    }
}
