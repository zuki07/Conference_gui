



package conference_gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Datas {

    private String str="";
    private Map <String, Map<String, String>> user_map=new HashMap<>();
    private Map <String, String> admin_map=new HashMap();
    
    
    public Datas() throws IOException{
        String string;
        FileInputStream fstream=new FileInputStream("data/database.dat");
        try (DataInputStream input_file = new DataInputStream(fstream)) {
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
        }
        System.out.println(user_map);
    }
    public void addUserToMap(String map_key, Map<String,String> map_values){
        user_map.put(map_key, map_values);
    }
    
    public void replaceUserPassword(String user, String password) throws IOException{
        Map <String, String> new_password_map;
        new_password_map=user_map.get(user);
        new_password_map.replace("password", password);
        writeDataFile();
    }
    
    public Map<String, Map<String,String>> getUserMap(){
        return user_map;
    }
    
    public Map getAdminMap(){
        return admin_map;
    }
    
    public void writeDataFile() throws IOException{
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
    
    public void readAdminData() throws IOException{
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
    }
    
    public void writeAdminData(Map new_map) throws IOException{
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
}
