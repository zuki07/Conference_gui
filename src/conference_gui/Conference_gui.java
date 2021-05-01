



package conference_gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class Conference_gui extends Application {

    Map map;
    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    public Conference_gui() throws IOException {
        this.map = Datas.readDataFile();
        
    }

    @Override
    public void start(Stage logInStage) {
        FxElements user_name_element=new FxElements();
        TextField user_name=user_name_element.setTextField(Pos.CENTER, 350, "--User Name--");
        
        FxElements password_element=new FxElements();
        PasswordField password=password_element.setPasswordField(Pos.CENTER, 350, "--Password--");
        
        Button log_in_btn=new Button("Log In");
        Button reset_btn=new Button("Reset Password");
        Button create_user_btn=new Button("Create User");
        create_user_btn.setId("create_btn");
        
        Label error=new Label();
        error.setVisible(false);
        error.setId("log_in_error");
        
        log_in_btn.setOnAction(event ->{
            if(user_name.getText().equals("")){
                error.setVisible(true);
                error.setText("Please enter a user name.");
            }
            else if(password.getText().equals("")){
                error.setVisible(true);
                error.setText("Please enter a password.");
            }
            else{
                error.setVisible(false);
                error.setText("");
                if(!map.containsKey(user_name.getText().toLowerCase())){
                    error.setVisible(true);
                    error.setText(user_name.getText()+" user does not exist");
                    user_name.clear();
                    password.clear();
                    return;
                }
                List<Object> value_list=(List<Object>) map.get(user_name.getText().toLowerCase());
                Object password_value=value_list.get(0);
                if(!password.getText().equals(password_value)){
                        error.setVisible(true);
                        error.setText("The password is invalid");
                        password.clear();
                }
                else if(!"admin".equalsIgnoreCase(user_name.getText())){
                    User_gui user_gui=new User_gui();
                    try {
                        user_gui.start();
                        logInStage.close();
                    } 
                    catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
                else{
                    Admin admin;
                    try {
                        admin = new Admin();
                        admin.adminStage();
                        user_name.clear();
                        password.clear();
                        logInStage.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        
        create_user_btn.setOnAction(event ->{
                NewUser newUser=new NewUser();
                newUser.startNewUser();
                logInStage.close();
        });
        
        reset_btn.setOnAction(event ->{
            ResetPassword reset_stage=new ResetPassword();
            reset_stage.startReset();
            logInStage.close();
        });

        VBox vbox=new VBox(user_name, password, log_in_btn, create_user_btn, reset_btn, error);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        Scene logInScene=new Scene(vbox, 850, 600);
        logInScene.getStylesheets().add("log_in.css");
        logInStage.setTitle("Log In");
        logInStage.setScene(logInScene);
        logInStage.show();
    }
}
