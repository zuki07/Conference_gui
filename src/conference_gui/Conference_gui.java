



package conference_gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        Map map=mapsFile();
        launch(args);
    }

    public Conference_gui() throws FileNotFoundException {
        this.map = Data.readDataFile();
        
    }

    @Override
    public void start(Stage logInStage) {
        TextField user_name=new TextField();
        user_name.setAlignment(Pos.CENTER);
        user_name.setMaxWidth(350);
        user_name.setPromptText("--User Name--");
        user_name.setFocusTraversable(false);
        
        PasswordField password=new PasswordField();
        password.setAlignment(Pos.CENTER);
        password.setMaxWidth(350);
        password.setPromptText("--Password--");
        password.setFocusTraversable(false);
        
        Button log_in_btn=new Button("Log In");
        Button create_user_btn=new Button("Create User");
        
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
                boolean key_found=false;
                Set<String> keys=map.keySet();
                
                for(String k:keys){
                    if(k.equalsIgnoreCase(user_name.getText())){
                        error.setVisible(false);
                        key_found=true;
                        break;
                    }
                }
                if(key_found==false){
                    error.setVisible(true);
                    error.setText("User name does not exist");
                    user_name.clear();
                    password.clear();
                }
                else if(key_found==true){
                    List<Object> value_list=(List<Object>) map.get(user_name.getText());
                    Object password_value=value_list.get(0);
                    if(password_value!=null){
                        if(password.getText().equals(password_value)){
                            if(user_name.getText().equalsIgnoreCase("admin")){
                                Admin admin=new Admin();
                                admin.adminStage();
                                user_name.clear();
                                password.clear();
//                                logInStage.close();
                            }
                            else{
                                User_gui user_gui=new User_gui();
                                try {
                                    user_gui.start();
                                } catch (FileNotFoundException ex) {
                                    System.out.println(ex);
                                }
                                logInStage.close();
                            }
                        }
                        else{
                            error.setVisible(true);
                            error.setText("The password is invalid");
                            password.clear();
                        }
                    }
                }
            }
        });
        
        create_user_btn.setOnAction(event ->{
                NewUser newUser=new NewUser();
                newUser.startNewUser();
                logInStage.close();
        });

        VBox vbox=new VBox(user_name, password, log_in_btn, create_user_btn, error);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        Scene logInScene=new Scene(vbox, 800, 875);
        logInScene.getStylesheets().add("styles.css");
        logInStage.setTitle("Log In");
        logInStage.setScene(logInScene);
        logInStage.show();
    }
    
    public static Map mapsFile() throws FileNotFoundException{
        File file=new File("maps.txt");
        Scanner input_file=new Scanner(file);
        Map<String, String> map=new HashMap<>();
        while(input_file.hasNext()){
            String key=input_file.nextLine();
            String [] token=key.split(":");
            map.put(token[0], token[1]);
        }
        return map;
    }

}
