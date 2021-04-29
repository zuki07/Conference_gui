



package conference_gui;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;


public class Admin {

    Map<String, String> admin_map;
    
    String general_txt="Current General Registration Price: $",
            student_txt="Current Student Registration Price: $",
            dinner_txt="Current Night Dinner Price: $",
            commerce_txt="Current E-commerce Price: $",
            web_txt="Current Web Price: $",
            java_txt="Current Advanced Java Price: $",
            network_txt="Current Network Security Price: $",
            error_txt="Not a whole number\nPlease try a different value";
    
    boolean save=false;
    
    public static void main(String[] args) {
        launch(args);
    }

    public Admin() {
        try {
            this.admin_map = Data.readAdminData();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adminStage() {
        Stage admin_stage=new Stage();
        
        TextField general_input=new TextField();
        general_input.setAlignment(Pos.CENTER);
        general_input.setMaxWidth(200);
        general_input.setPromptText("--General Registration--");
        general_input.setFocusTraversable(false);
        Label general_current=new Label(general_txt+admin_map.get("general"));
        Button general_btn=new Button("Set Price");
        
        TextField student_input=new TextField();
        student_input.setAlignment(Pos.CENTER);
        student_input.setMaxWidth(200);
        student_input.setPromptText("--Student Registration--");
        student_input.setFocusTraversable(false);
        Label student_current=new Label(student_txt+admin_map.get("student"));
        Button student_btn=new Button("Set Price");
        
        TextField dinner_input=new TextField();
        dinner_input.setAlignment(Pos.CENTER);
        dinner_input.setMaxWidth(200);
        dinner_input.setPromptText("--Night Dinner--");
        dinner_input.setFocusTraversable(false);
        Label dinner_current=new Label(dinner_txt+admin_map.get("dinner"));
        Button dinner_btn=new Button("Set Price");
        
        TextField commerce_input=new TextField();
        commerce_input.setAlignment(Pos.CENTER);
        commerce_input.setMaxWidth(200);
        commerce_input.setPromptText("--E-commerce--");
        commerce_input.setFocusTraversable(false);
        Label commerce_current=new Label(commerce_txt+admin_map.get("commerce"));
        Button commerce_btn=new Button("Set Price");
        
        TextField web_input=new TextField();
        web_input.setAlignment(Pos.CENTER);
        web_input.setMaxWidth(200);
        web_input.setPromptText("--Web--");
        web_input.setFocusTraversable(false);
        Label web_current=new Label(web_txt+admin_map.get("web"));
        Button web_btn=new Button("Set Price");
        
        TextField java_input=new TextField();
        java_input.setAlignment(Pos.CENTER);
        java_input.setMaxWidth(200);
        java_input.setPromptText("--Advanced Java--");
        java_input.setFocusTraversable(false);
        Label java_current=new Label(java_txt+admin_map.get("java"));
        Button java_btn=new Button("Set Price");
        
        TextField network_input=new TextField();
        network_input.setAlignment(Pos.CENTER);
        network_input.setMaxWidth(200);
        network_input.setPromptText("--Network Security--");
        network_input.setFocusTraversable(false);
        Label network_current=new Label(network_txt+admin_map.get("network"));
        Button network_btn=new Button("Set Price");

        Button save_btn=new Button("Save Changes");
        Label error_label=new Label();
        error_label.setVisible(false);
        error_label.setId("error_label");
        error_label.setTextAlignment(TextAlignment.CENTER);
        
        general_btn.setOnAction(event ->{
            save=buttonEvent(general_current, general_input, 
                            "general", general_txt, 
                            error_label, save);
        });
        student_btn.setOnAction(event ->{
            save=buttonEvent(student_current, student_input, 
                       "student", student_txt, 
                       error_label, save);
        });
        dinner_btn.setOnAction(event ->{
            save=buttonEvent(dinner_current, dinner_input, 
                        "dinner", dinner_txt, 
                        error_label, save);
        });
        commerce_btn.setOnAction(event ->{
            save=buttonEvent(commerce_current, commerce_input, 
                        "commerce", commerce_txt, 
                        error_label, save);
        });
        web_btn.setOnAction(event ->{
            save=buttonEvent(web_current, web_input, 
                    "web", web_txt, error_label, save);
        });
        java_btn.setOnAction(event ->{
            save=buttonEvent(java_current, java_input, 
                    "java", java_txt, error_label, save);
        });
        network_btn.setOnAction(event ->{
            save=buttonEvent(network_current, network_input, 
                        "network", network_txt, 
                        error_label,save);
        });
        save_btn.setOnAction(event ->{
//            if(save=true);
            try {
                Data.writeAdminData(admin_map);
            } 
            catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
        });
        
        GridPane grid=new GridPane();
        grid.add(general_current, 0, 0);
        GridPane.setHalignment(general_current, HPos.RIGHT);
        grid.add(general_input, 1, 0);
        grid.add(general_btn, 2, 0);
        
        grid.add(student_current, 0, 1);
        GridPane.setHalignment(student_current, HPos.RIGHT);
        grid.add(student_input, 1, 1);
        grid.add(student_btn, 2, 1);
        
        grid.add(dinner_current, 0, 2);
        GridPane.setHalignment(dinner_current, HPos.RIGHT);
        grid.add(dinner_input, 1, 2);
        grid.add(dinner_btn, 2, 2);
        
        grid.add(commerce_current, 0, 3);
        GridPane.setHalignment(commerce_current, HPos.RIGHT);
        grid.add(commerce_input, 1, 3);
        grid.add(commerce_btn, 2, 3);
        
        grid.add(web_current, 0, 4);
        GridPane.setHalignment(web_current, HPos.RIGHT);
        grid.add(web_input, 1, 4);
        grid.add(web_btn, 2, 4);
        
        grid.add(java_current, 0, 5);
        GridPane.setHalignment(java_current, HPos.RIGHT);
        grid.add(java_input, 1, 5);
        grid.add(java_btn, 2, 5);
        
        grid.add(network_current, 0, 6);
        GridPane.setHalignment(network_current, HPos.RIGHT);
        grid.add(network_input, 1, 6);
        grid.add(network_btn, 2, 6);
        
        grid.add(save_btn, 0, 7, 3, 1);
        GridPane.setHalignment(save_btn, HPos.CENTER);
        
        grid.add(error_label, 0, 8, 3, 1);
        GridPane.setHalignment(error_label, HPos.CENTER);
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(30));
        
        Scene new_user_scene = new Scene(grid, 800, 875);
        new_user_scene.getStylesheets().add("admin_styles.css");
        admin_stage.setTitle("Create User");
        admin_stage.setScene(new_user_scene);
        admin_stage.show();
    }
    
    public boolean buttonEvent(Label label, TextField input, 
                                String map_value, String txt, 
                                Label error, boolean save){
        try{
                Integer.parseInt(input.getText());
                error.setVisible(false);
                input.setStyle("");
                admin_map.replace(map_value, input.getText());
                input.clear();
                label.setText(txt+admin_map.get(map_value));
                return save=true;
            }
            catch(NumberFormatException nfe){
                error.setVisible(true);
                error.setText(error_txt);
                input.setStyle("-fx-background-color: red;");
            }
        return save=false;
    }

}
