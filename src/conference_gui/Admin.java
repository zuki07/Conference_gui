



package conference_gui;

import java.io.IOException;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;


public class Admin {

    
    String general_txt="Current General Registration Price: $",
            student_txt="Current Student Registration Price: $",
            dinner_txt="Current Night Dinner Price: $",
            commerce_txt="Current E-commerce Price: $",
            web_txt="Current Web Price: $",
            java_txt="Current Advanced Java Price: $",
            network_txt="Current Network Security Price: $",
            error_txt="Not a whole number\nPlease try a different value";
    Map admin_map=Datas.getAdminMap();

    public void adminStage() {
        Stage admin_stage=new Stage();
        
        double text_width=200;
        Pos text_position=Pos.CENTER;

        FxElements general_element=new FxElements();
        TextField general_input=general_element.setTextField(text_position, text_width, "--General Registration--");
        Label general_current=new Label(general_txt+admin_map.get("general"));
        Button general_btn=new Button("Set Price");

        FxElements student_element=new FxElements();
        TextField student_input=student_element.setTextField(text_position, text_width, "--Student Registration--");
        Label student_current=new Label(student_txt+admin_map.get("student"));
        Button student_btn=new Button("Set Price");

        FxElements dinner_element=new FxElements();
        TextField dinner_input=dinner_element.setTextField(text_position, text_width, "--Night Dinner--");
        Label dinner_current=new Label(dinner_txt+admin_map.get("dinner"));
        Button dinner_btn=new Button("Set Price");

        FxElements commerce_element=new FxElements();
        TextField commerce_input=commerce_element.setTextField(text_position, text_width, "--E-commerce--");
        Label commerce_current=new Label(commerce_txt+admin_map.get("commerce"));
        Button commerce_btn=new Button("Set Price");

        FxElements web_element=new FxElements();
        TextField web_input=web_element.setTextField(text_position, text_width, "--Web--");
        Label web_current=new Label(web_txt+admin_map.get("web"));
        Button web_btn=new Button("Set Price");

        FxElements java_element=new FxElements();
        TextField java_input=java_element.setTextField(text_position, text_width, "--Advanced Java--");
        Label java_current=new Label(java_txt+admin_map.get("java"));
        Button java_btn=new Button("Set Price");

        FxElements network_element=new FxElements();
        TextField network_input=network_element.setTextField(text_position, text_width, "--Network Security--");
        Label network_current=new Label(network_txt+admin_map.get("network"));
        Button network_btn=new Button("Set Price");

        Button exit_btn=new Button("Exit");
        Button save_btn=new Button("Save Changes");
        Button view_users=new Button("View Users");
        
        Label error_label=new Label();
        error_label.setVisible(false);
        error_label.setId("error_label");
        error_label.setTextAlignment(TextAlignment.CENTER);
        
        general_btn.setOnAction(event ->{
            buttonEvent(general_current, general_input, 
                            "general", general_txt, error_label);
        });
        student_btn.setOnAction(event ->{
            buttonEvent(student_current, student_input, 
                       "student", student_txt, error_label);
        });
        dinner_btn.setOnAction(event ->{
            buttonEvent(dinner_current, dinner_input, 
                        "dinner", dinner_txt, error_label);
        });
        commerce_btn.setOnAction(event ->{
            buttonEvent(commerce_current, commerce_input, 
                        "commerce", commerce_txt, error_label);
        });
        web_btn.setOnAction(event ->{
            buttonEvent(web_current, web_input, 
                    "web", web_txt, error_label);
        });
        java_btn.setOnAction(event ->{
            buttonEvent(java_current, java_input, 
                    "java", java_txt, error_label);
        });
        network_btn.setOnAction(event ->{
            buttonEvent(network_current, network_input, 
                        "network", network_txt, 
                        error_label);
        });
        exit_btn.setOnAction(event ->{
            admin_stage.close();
            Conference_gui main=new Conference_gui();
            try {
                main.start(admin_stage);
            } catch (IOException ex) {
                System.out.println(ex);;
            }
        });
        save_btn.setOnAction(event ->{
            try {
                conference_gui.Datas.writeAdminData(admin_map);
                error_label.setVisible(true);
                error_label.setText("Values Saved");
                error_label.setStyle("-fx-background-color: rgb(0,255,0);");
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });
        view_users.setOnAction(event->{
            AdminDataView view_data=new AdminDataView();
            view_data.startViewStage();
            admin_stage.close();
        });
        
        GridPane grid=new GridPane();
        FxElements grid_add=new FxElements();
        
        grid_add.setGridAdd(grid, general_current, general_input, general_btn, 0);
        grid_add.setGridAdd(grid, student_current, student_input, student_btn, 1);
        grid_add.setGridAdd(grid, dinner_current, dinner_input, dinner_btn, 2);
        grid_add.setGridAdd(grid, commerce_current, commerce_input, commerce_btn, 3);
        grid_add.setGridAdd(grid, web_current, web_input, web_btn, 4);
        grid_add.setGridAdd(grid, java_current, java_input, java_btn, 5);
        grid_add.setGridAdd(grid, network_current, network_input, network_btn, 6);
        
        HBox hbox=new HBox(exit_btn, save_btn, view_users);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        grid.add(hbox, 0, 7, 3, 1);
        GridPane.setHalignment(exit_btn, HPos.CENTER);
        
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
    
    public void buttonEvent(Label label, TextField input, 
                                String map_value, String txt, 
                                Label error){
        error.setStyle("");
        try{
                Integer.parseInt(input.getText());
                error.setVisible(false);
                input.setStyle("");
                admin_map.replace(map_value, input.getText());
                input.clear();
                label.setText(txt+admin_map.get(map_value));
            }
            catch(NumberFormatException nfe){
                error.setVisible(true);
                error.setText(error_txt);
                input.setStyle("-fx-background-color: red;");
            }
    }

}
