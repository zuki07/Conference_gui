
package conference_gui;

import java.io.IOException;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;


public class User_gui{
    String person0="0 People",
           person1="1 Person",
           person2="2 People",
           person3="3 People",
           person4="4 People";

    
    public void start() throws IOException {
        
        Stage userStage=new Stage();
        Map<String,String> user_price=Datas.getAdminMap();
        
//                                SETUP LABELS & BUTTON
        Label general_label=new Label("General Registration: $"+user_price.get("general"));
        Label student_label=new Label("Student Registration: $"+user_price.get("student"));
        Label workshop_label=new Label("Optional Workshops");
        workshop_label.setId("h1");
        Label dinner_label=new Label("Optional Night Dinner: $"+user_price.get("dinner"));
        Label commerce_label=new Label("Introduction to E-commerce: $"+user_price.get("commerce"));
        Label web_label=new Label("The Future of the Web: $"+user_price.get("web"));
        Label java_label=new Label("Advanced Java Programming: $"+user_price.get("java"));
        Label network_label=new Label("Network Security: $"+user_price.get("network"));
        
        Label total_label=new Label();
        total_label.setId("total_label");                                           //total label ID
        
        Label error_label=new Label();
        error_label.setId("error_label");                                           //error label ID
        
        Button total_btn=new Button("Get Total");
        Button clear_btn=new Button("Clear All");
        clear_btn.setId("clear_btn");
        Button logout_btn=new Button("Log Out");
        logout_btn.setId("logout_btn");
                            
//                                SETUP COMBO BOX SELECTIONS
        ComboBox<String> general=new ComboBox<>();
        ComboBox<String> student=new ComboBox<>();
        ComboBox<String> dinner=new ComboBox<>();
        ComboBox<String> commerce=new ComboBox<>();
        ComboBox<String> web=new ComboBox<>();
        ComboBox<String> java=new ComboBox<>();
        ComboBox<String> network=new ComboBox<>();
        
//                                ADD SELECTIONS 
        general.getItems().addAll(person0,person1, person2);                            //add person selection to general
        general.setValue(person0);
        
        student.getItems().addAll(person0,person1, person2);                            //add person selection to student
        student.setValue(person0);
        
        dinner.getItems().addAll(person0,person1, person2, person3, person4);           //add person selection to dinner
        dinner.setValue(person0);
        
        commerce.getItems().addAll(person0,person1, person2, person3, person4);         //add person selection to e-commerce
        commerce.setValue(person0); 
        
        web.getItems().addAll(person0,person1, person2, person3, person4);              //add person selection to web
        web.setValue(person0);
        
        java.getItems().addAll(person0,person1, person2, person3, person4);             //add person selection to java
        java.setValue(person0);
        
        network.getItems().addAll(person0,person1, person2, person3, person4);          //add person selection to network
        network.setValue(person0);
                
//                                  TOTAL BUTTON LAMBDA
        total_btn.setOnAction(event ->{
            int general_value=setPersonValue(general.getValue());                   //set general value
            int student_value=setPersonValue(student.getValue());                   //set student value
            int dinner_value=setPersonValue(dinner.getValue());                     //set dinner value
            int commerce_value=setPersonValue(commerce.getValue());                 //set e-commerce value
            int web_value=setPersonValue(web.getValue());                           //set web value
            int java_value=setPersonValue(java.getValue());                         //set java value
            int network_value=setPersonValue(network.getValue());                   //set network value
            
            int [] combobox_value={dinner_value, commerce_value, web_value,         //set up array for int combobox values
                                    java_value, network_value};
            
            ComboBox [] boxArray={dinner, commerce, web, java, network};            //set up array for combobox
            
            for (int i=0; i<boxArray.length; i++){                                  //set combobox array to default css
                    boxArray[i].setStyle("");
                }
            general.setStyle("");                                                   //set combobox to default css
            student.setStyle("");                                                   //set combobox to default css
            
            if (general_value==0 && student_value==0){                                            //registration is not selected, display error1 method
                general.setStyle("-fx-background-color: red");
                student.setStyle("-fx-background-color: red");
                Error1(error_label, total_label);
            }
            else if (general_value+student_value==1 && (dinner_value>1 | 
                    commerce_value>1 | web_value>1 | java_value>1 | network_value>1)){            //not enough registrations, display error2 method
                for (int i=0; i<combobox_value.length; i++){
                    if (combobox_value[i]>1){
                        boxArray[i].setStyle("-fx-background-color: red");
                        Error2(error_label, total_label);
                    }
                }
            }
            else if (general_value+student_value==2 && (dinner_value>2 |                           // not enough registrations, display error2 method
                    commerce_value>2 | web_value>2 | java_value>2 | network_value>2)){
                for (int i=0; i<combobox_value.length; i++){
                    if (combobox_value[i]>2){
                        boxArray[i].setStyle("-fx-background-color: red");
                        Error2(error_label, total_label);
                    }
                }
            }
            else if (general_value+student_value==3 && (dinner_value>3 |                                                         //not enough registrations, display error2 method
                    commerce_value>3 | web_value>3 | java_value>3 | network_value>3)){
                for (int i=0; i<combobox_value.length; i++){
                    if (combobox_value[i]>3){
                        boxArray[i].setStyle("-fx-base: red");
                        Error2(error_label, total_label);
                    }
                }    
            }
            else {                                                                                                              //registraion is selected, display grand total
                for (int i=0; i<boxArray.length; i++){
                    boxArray[i].setStyle("");
                }
                general.setStyle("");
                student.setStyle("");
                
                int general_price=general_value*Integer.parseInt(user_price.get("general"));
                int student_price=student_value*Integer.parseInt(user_price.get("student"));
                int dinner_price=dinner_value*Integer.parseInt(user_price.get("dinner"));
                int commerce_price=commerce_value*Integer.parseInt(user_price.get("commerce"));
                int web_price=web_value*Integer.parseInt(user_price.get("web"));
                int java_price=java_value*Integer.parseInt(user_price.get("java"));
                int network_price=network_value*Integer.parseInt(user_price.get("network"));
                int total_amount=general_price+student_price+dinner_price+commerce_price+web_price+java_price+network_price;        //add everthing together
                
                String[] values_array={String.valueOf(general_value)+"="+"$"+String.valueOf(general_price),
                                        String.valueOf(student_value)+"="+"$"+String.valueOf(student_price),
                                        String.valueOf(dinner_value)+"="+"$"+String.valueOf(dinner_price),
                                        String.valueOf(commerce_value)+"="+"$"+String.valueOf(commerce_price),
                                        String.valueOf(web_value)+"="+"$"+String.valueOf(web_price),
                                        String.valueOf(java_value)+"="+"$"+String.valueOf(java_price),
                                        String.valueOf(network_value)+"="+"$"+String.valueOf(network_price),
                                        String.valueOf("$"+total_amount),
                                        Datas.getLogedInUser()};
                Datas.replaceUserSelections(values_array);
                try {
                    Datas.writeDataFile();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                total_label.setText(String.format("Grand Total: $%,d",total_amount));
                total_label.setStyle("-fx-border-width: 1.5px; -fx-border-radius: 10px;"+                                           //*does not look good without inline styling
                                    " -fx-border-color: rgb(255,255,255); -fx-background-radius: 10px;"
                                    + "-fx-padding: 7px;");                                                                         //*over ride css
                error_label.setText("");
                error_label.setStyle("");                          //*same as above
                }
        });
        
        
//                                  CLEAR ALL BUTTON LAMBDA
        clear_btn.setOnAction(event ->{
              ClearButtonEvents buttonEvents=new ClearButtonEvents(general, student, dinner, commerce,                                     //pass in combobox and label values
                                                     web, java, network, total_label, error_label);
              buttonEvents.displayNewStage();                                                                                    //display new stage
        });
        
//                                    LOG OUT BUTTON LAMBDA
        logout_btn.setOnAction(event ->{
            userStage.close();
            Conference_gui c_gui;
            c_gui = new Conference_gui();
            try {
                c_gui.start(userStage);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        
//                                  GRID LAYOUT
        GridPane grid_pane=new GridPane();
        FxElements general_element=new FxElements();
        general_element.setUserGrid(grid_pane, general_label, general, 0);
        
        FxElements student_element=new FxElements();
        student_element.setUserGrid(grid_pane, student_label, student, 1);
        
        FxElements dinner_element=new FxElements();
        dinner_element.setUserGrid(grid_pane, dinner_label, dinner, 2);
        
        grid_pane.add(workshop_label, 0, 3, 2, 1);                                        
        GridPane.setHalignment(workshop_label, HPos.CENTER);
        
        FxElements commerce_element=new FxElements();
        commerce_element.setUserGrid(grid_pane, commerce_label, commerce, 4);
        
        FxElements web_element=new FxElements();
        web_element.setUserGrid(grid_pane, web_label, web, 5);
        
        FxElements java_element=new FxElements();
        java_element.setUserGrid(grid_pane, java_label, java, 6);
        
        FxElements network_element=new FxElements();
        network_element.setUserGrid(grid_pane, network_label, network, 7);
        
        grid_pane.add(logout_btn, 0, 8);
        GridPane.setHalignment(logout_btn, HPos.RIGHT);
        
        grid_pane.add(total_btn, 1, 8);                                            //total button
        
        grid_pane.add(clear_btn, 2,8);                                             //clear all button
        
        grid_pane.add(total_label, 1, 9, 2, 1);                                          //total label
        GridPane.setHalignment(total_label, HPos.LEFT);
        
        grid_pane.add(error_label, 1, 9, 2, 1);                                            //error label
        GridPane.setHalignment(error_label, HPos.LEFT);
        
        grid_pane.setAlignment(Pos.BOTTOM_CENTER);
        grid_pane.setHgap(10);
        grid_pane.setVgap(8);
        grid_pane.setPadding(new Insets(30));


        Scene scene = new Scene(grid_pane, 800, 875);
        scene.getStylesheets().add("styles.css");
        userStage.setTitle("Conference Registraion System");
        userStage.setScene(scene);
        userStage.show();
    }
    
//                            SET PERSON VALUE METHOD
    public int setPersonValue(String combobox_value){
        int set_value;
        switch (combobox_value) {
            case "1 Person":            //1 person = 1
                set_value=1;
                break;
            case "2 People":            //2 people = 2
                set_value=2;
                break;
            case "3 People":            //3 people = 3
                set_value=3;
                break;
            case "4 People":            //4 people = 4
                set_value=4;
                break;
            default:                    //defalut = 0
                set_value=0;
                break;
        }
            return set_value;
        }
    
//                                ERROR1 LABEL
    public void Error1(Label error_label, Label total_label){
        error_label.setText("Select a Registration");                                                                   
        error_label.setStyle("-fx-border-width: 1.5px; -fx-border-radius: 10px;"+                                       //*does not look good without inline styling
                            "-fx-border-color: rgb(255,255,255); -fx-background-radius: 10px;"
                            + "-fx-padding: 7px;");                                                                      //*over ride css
        total_label.setText("");
        total_label.setStyle("");
                
    }
    
//                                ERROR2 LABEL
    public void Error2(Label error_label, Label total_label){
        error_label.setText("Value to Many");                                                                   
        error_label.setStyle("-fx-border-width: 1.5px; -fx-border-radius: 10px;"+                                       //*does not look good without inline styling
                            "-fx-border-color: rgb(255,255,255); -fx-background-radius: 10px;"
                            + "-fx-padding: 7px;");                                                                      //*over ride css
        total_label.setText("");
        total_label.setStyle("-fx-border-style: none; -fx-border-color: none; fx-padding: 0px;");
        
        
    }
}
