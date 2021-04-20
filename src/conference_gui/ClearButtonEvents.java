



package conference_gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ClearButtonEvents extends Conference_gui{
    private ComboBox general_box=new ComboBox();
    private ComboBox student_box=new ComboBox();
    private ComboBox dinner_box=new ComboBox();
    private ComboBox commerce_box=new ComboBox();
    private ComboBox web_box=new ComboBox();
    private ComboBox java_box=new ComboBox();
    private ComboBox network_box=new ComboBox();
    
    private Label total=new Label();
    private Label error=new Label();
    
    private String person="0 People";    
    
                            //CONSTRUCTOR
    public ClearButtonEvents(ComboBox general, ComboBox student, ComboBox dinner,                    //constructor sets combox and labels
                        ComboBox commerce, ComboBox web, ComboBox java, 
                        ComboBox network, Label total_label, Label error_label){
        general_box=general;
        student_box=student;
        dinner_box=dinner;
        commerce_box=commerce;
        web_box=web;
        java_box=java;
        network_box=network;
        total=total_label;
        error=error_label;
        
    }
    
                                    //DISPLAY YES/NO STAGE
    public void displayNewStage(){
        Button yes_btn=new Button("Yes");                                                       //setup buttons and label
        Button no_btn=new Button("No");
        Label clear_all_label=new Label("Are you sure you want to CLEAR EVERYTHING?");
        
        Stage clearStage=new Stage();
        
                                //yes button to clear values
        yes_btn.setOnAction(event ->{
            setPerson0();                                                                //setPerson0 method to set to default values
            clearStage.close();
        });
        
                                //no button to close stage
        no_btn.setOnAction(event ->{
                clearStage.close();                                                       //only close stage, keep remaining comboBox values
            });
        
        HBox hbox=new HBox(yes_btn, no_btn);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox=new VBox(clear_all_label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene clearScene=new Scene(vbox, 450, 150 );
        clearScene.getStylesheets().add("clear-stage.css");
        clearStage.setTitle("Clear Everything");
        clearStage.setScene(clearScene);
        clearStage.show();
    }
    
    public void setPerson0(){
        ComboBox [] box_array={general_box, student_box, dinner_box, commerce_box,        //setup array for combobox
                                web_box, java_box, network_box};
        
        general_box.setValue(person);                                                     //set all comboBox to person0
        student_box.setValue(person);
        dinner_box.setValue(person);
        commerce_box.setValue(person);
        web_box.setValue(person);
        java_box.setValue(person);
        network_box.setValue(person);

        for (int i=0; i<box_array.length; i++){                                           //set all combobox css to default
            box_array[i].setStyle("");
        }
        total.setText("");                                                                //clear out total_label
        total.setStyle("");

        error.setText("");                                                                 //clear out error_label
        error.setStyle(""); 
    }
}
