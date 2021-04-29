



package conference_gui;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


public class NewUser{


    public void startNewUser() {
        
        Stage newUserStage=new Stage();
        
        
        TextField new_user_name=new TextField();
        new_user_name.setAlignment(Pos.CENTER);
        new_user_name.setMaxWidth(350);
        new_user_name.setPromptText("--New User--");
        new_user_name.setFocusTraversable(false);
        
        TextField new_password=new TextField();
        new_password.setAlignment(Pos.CENTER);
        new_password.setMaxWidth(350);
        new_password.setPromptText("--New Password--");
        new_password.setFocusTraversable(false);
        
        TextField secure_question=new TextField();
        secure_question.setAlignment(Pos.CENTER);
        secure_question.setMaxWidth(350);
        secure_question.setPromptText("--What is your favorite movie?--");
        secure_question.setFocusTraversable(false);
        
        Button create_btn=new Button("Submit");
        Button back_btn=new Button("Back");
        back_btn.setVisible(false);
        
        Label new_error=new Label();
        new_error.setVisible(false);
        new_error.setId("log_in_error");

        create_btn.setOnAction(event ->{
            new_error.setVisible(false);
            boolean key_found=false;
            if(new_user_name.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please enter a user name");
            }
            else if(new_password.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please enter a password");
            }
            else{  
                Map map;
                try {
                    map = Data.readDataFile();
                    Set<String> keys=map.keySet();
                    System.out.println(keys);
                    for(String k:keys){
                        if(k.equalsIgnoreCase(new_user_name.getText())){
                            key_found=true;
                            new_error.setVisible(true);
                            new_error.setText("User name already exists");
                            back_btn.setVisible(true);
                            break;
                        }
                        else{
                            new_error.setVisible(false);
                        }
                    }
                    if(key_found==false){
                        conference_gui.Data.writeDataFile(new_user_name, new_password, secure_question); 
                        Conference_gui c_gui;
                        c_gui = new Conference_gui();
                        c_gui.start(newUserStage);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }     
            }
            new_user_name.clear();
            new_password.clear();
            secure_question.clear();
            
        });
        
        back_btn.setOnAction(event ->{
            
            Conference_gui c_gui;
            try {
                c_gui = new Conference_gui();
                c_gui.start(newUserStage);
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
        });


        VBox vbox=new VBox(new_user_name, new_password, secure_question, create_btn, back_btn, new_error);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        Scene new_user_scene = new Scene(vbox, 800, 875);
        new_user_scene.getStylesheets().add("styles.css");
        newUserStage.setTitle("Create User");
        newUserStage.setScene(new_user_scene);
        newUserStage.show();
    }

}
