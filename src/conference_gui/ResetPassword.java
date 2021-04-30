



package conference_gui;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


public class ResetPassword{


    public void startReset() {
        
        Stage newUserStage=new Stage();
        
        
        TextField user_name=new TextField();
        user_name.setAlignment(Pos.CENTER);
        user_name.setMaxWidth(350);
        user_name.setPromptText("--User Name--");
        user_name.setFocusTraversable(false);
        
        
        TextField secure_question=new TextField();
        secure_question.setAlignment(Pos.CENTER);
        secure_question.setMaxWidth(350);
        secure_question.setPromptText("--What is your favorite movie?--");
        secure_question.setFocusTraversable(false);
        
        TextField new_password=new TextField();
        new_password.setAlignment(Pos.CENTER);
        new_password.setMaxWidth(350);
        new_password.setPromptText("--New Password--");
        new_password.setFocusTraversable(false);
        
        TextField confirm_password=new TextField();
        confirm_password.setAlignment(Pos.CENTER);
        confirm_password.setMaxWidth(350);
        confirm_password.setPromptText("--Confirm Password--");
        confirm_password.setFocusTraversable(false);
        
        Button change_btn=new Button("Submit");
        Button back_btn=new Button("Back");
        back_btn.setId("back_btn");
        
        Label new_error=new Label();
        new_error.setVisible(false);
        new_error.setId("log_in_error");

        change_btn.setOnAction(event ->{
            new_error.setVisible(false);
            if(user_name.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please enter a user name");
            }
            else if(secure_question.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please Answer the security question");
            }
            else if(new_password.getText().equals("") || confirm_password.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please enter a password");
            }
            else{  
                Map map;
                try {
                    map = Datas.readDataFile();
                    Set<String> keys=map.keySet();
                    for(String k:keys){
                        if(k.equalsIgnoreCase(user_name.getText())){
                            List<Object> value_list=(List<Object>) map.get(k);
                            Object question=value_list.get(1);
                            if(question.toString().equalsIgnoreCase(secure_question.getText()) &&
                            new_password.getText().equals(confirm_password.getText())){
                                value_list.set(0, new_password.getText());
                                map.replace(k, value_list);
                                Datas.replaceData(map);
                                new_error.setVisible(true);
                                new_error.setText("New Password is Saved");
                                new_error.setStyle("-fx-background-color: rgb(0,255,0);");
                                user_name.clear();
                                new_password.clear();
                                confirm_password.clear();
                                secure_question.clear();
                            }
                            else{
                                new_error.setVisible(true);
                                new_error.setStyle("");
                                if(!new_password.getText().equalsIgnoreCase(confirm_password.getText())){
                                    new_error.setText("Passwords do not match");
                                    confirm_password.clear();
                                    new_password.clear();
                                }
                                else{
                                    new_error.setText("Security Answer is not correct");
                                    secure_question.clear();
                                    confirm_password.clear();
                                    new_password.clear();
                                    
                                }
                            }
                            break;
                        }
                        else{
                            new_error.setVisible(true);
                            new_error.setText("User Names does not Exist");
                            user_name.clear();
                            new_password.clear();
                            confirm_password.clear();
                            secure_question.clear();
                        }
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }     
            }        
        });
        
        back_btn.setOnAction(event ->{
            
            Conference_gui c_gui;
            try {
                c_gui = new Conference_gui();
                c_gui.start(newUserStage);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });


        VBox vbox=new VBox(user_name, secure_question, new_password, confirm_password, change_btn, back_btn, new_error);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        Scene new_user_scene = new Scene(vbox, 850, 600);
        new_user_scene.getStylesheets().add("log_in.css");
        newUserStage.setTitle("Reset Password");
        newUserStage.setScene(new_user_scene);
        newUserStage.show();
    }

}
