



package conference_gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


public class NewUser {


    public void startNewUser() throws IOException {
        
        Stage newUserStage=new Stage();
//        Datas data=new Datas();
        
        double text_width=350;
        Pos text_pos=Pos.CENTER;
        
        FxElements new_user_element=new FxElements();
        TextField new_user_name=new_user_element.setTextField(text_pos, text_width, "--New User--");

        FxElements new_password_element=new FxElements();
        TextField new_password=new_password_element.setTextField(text_pos, text_width, "--Password--");

        FxElements question_element=new FxElements();
        TextField secure_question=question_element.setTextField(text_pos, text_width, "--What is your favorite movie?--");
        
        Button create_btn=new Button("Submit");
        Button back_btn=new Button("Back");
        back_btn.setId("back_btn");
//        back_btn.setVisible(false);
        
        Label new_error=new Label();
        new_error.setVisible(false);
        new_error.setId("log_in_error");

        create_btn.setOnAction(event ->{
            new_error.setVisible(false);
            if(new_user_name.getText().isEmpty()){
                new_error.setVisible(true);
                new_error.setText("Please enter a user name");
            }
            else if(new_password.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please enter a password");
            }
            else if(secure_question.getText().equals("")){
                new_error.setVisible(true);
                new_error.setText("Please enter a sequrity question");
            }
            else{  
                Map<String, Map<String, String>> get_user_map=Datas.getUserMap();
                if(get_user_map.containsKey(new_user_name.getText())){
                    new_error.setVisible(true);
                    new_error.setText("User name already exists");
                    back_btn.setVisible(true);
                }
                else {
                    Map <String, String> new_user_map=new HashMap<>();
                    new_user_map.put("password", new_password.getText());
                    new_user_map.put("question", secure_question.getText());
                    new_user_map.put("general_info","null");
                    new_user_map.put("student_info","null");
                    new_user_map.put("dinner_info","null");
                    new_user_map.put("commerce_info","null");
                    new_user_map.put("web_info","null");
                    new_user_map.put("java_info","null");
                    new_user_map.put("network_info","null");
                    new_user_map.put("total_price", "null");
                    Datas.addUserToMap(new_user_name.getText(), new_user_map);
                    Conference_gui c_gui;
                    c_gui = new Conference_gui();
                    try {
                        c_gui.start(newUserStage);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
            new_user_name.clear();
            new_password.clear();
            secure_question.clear();
            
        });
        
        back_btn.setOnAction(event ->{
            Conference_gui c_gui;
            c_gui = new Conference_gui();
            try {
                c_gui.start(newUserStage);
            } catch (IOException ex) {
                System.out.println(ex);;
            }
        });


        VBox vbox=new VBox(new_user_name, new_password, secure_question, create_btn, back_btn, new_error);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        Scene new_user_scene = new Scene(vbox, 850, 600);
        new_user_scene.getStylesheets().add("log_in.css");
        newUserStage.setTitle("Create User");
        newUserStage.setScene(new_user_scene);
        newUserStage.show();
    }

}
