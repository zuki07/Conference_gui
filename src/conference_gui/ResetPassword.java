



package conference_gui;

import java.io.IOException;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


public class ResetPassword{


    public void startReset() throws IOException {
        
        Stage newUserStage=new Stage();
        Datas data=new Datas();
        
        double text_width=350;
        Pos text_pos=Pos.CENTER;
        
        FxElements user_element=new FxElements();
        TextField user_name=user_element.setTextField(text_pos, text_width, "--User Name--");
        
        FxElements secure_element=new FxElements();
        TextField secure_question=secure_element.setTextField(text_pos, text_width, "--What is your favorite movie?--");

        FxElements new_element=new FxElements();
        TextField new_password=new_element.setTextField(text_pos, text_width, "--New Password--");

        FxElements confirm_element=new FxElements();
        TextField confirm_password=confirm_element.setTextField(text_pos, text_width, "--Confirm Password--");
        
        Button change_btn=new Button("Submit");
        Button back_btn=new Button("Back");
        back_btn.setId("back_btn");
        
        Label new_error=new Label();
        new_error.setVisible(false);
        new_error.setId("log_in_error");

        change_btn.setOnAction(event ->{
            new_error.setVisible(true);
            if(user_name.getText().equals("")){
                new_error.setText("Please enter a user name");
            }
            else if(secure_question.getText().equals("")){
                new_error.setText("Please Answer the security question");
            }
            else if(new_password.getText().equals("") || confirm_password.getText().equals("")){
                new_error.setText("Please enter a password");
            }
            else{  
                Map<String, Map<String,String>> map=data.getUserMap();
                if(!map.containsKey(user_name.getText().toLowerCase())){
                    new_error.setText("User Names does not Exist");
                    user_name.clear();
                    new_password.clear();
                    confirm_password.clear();
                    secure_question.clear();
                }
                else if(!map.get(user_name.getText()).get("question").equalsIgnoreCase(secure_question.getText())){
                    new_error.setText("Security Answer is not correct");
                    secure_question.clear();
                    confirm_password.clear();
                    new_password.clear();
                }
                else if(!new_password.getText().equals(confirm_password.getText())){
                    new_error.setText("Passwords do not match");
                    confirm_password.clear();
                    new_password.clear();
                }
                else{
                    try {
                        data.replaceUserPassword(user_name.getText(), confirm_password.getText());
                        new_error.setText("New Password is Saved");
                        new_error.setStyle("-fx-background-color: rgb(0,255,0);");
                        user_name.clear();
                        new_password.clear();
                        confirm_password.clear();
                        secure_question.clear();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }        
        });
        
        back_btn.setOnAction(event ->{
            Conference_gui c_gui;
            c_gui = new Conference_gui();
            try {
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
