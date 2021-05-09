



package conference_gui;

import java.io.IOException;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;


public class AdminDataView  {


    public void startViewStage() {
        Stage admin_view=new Stage();
        TableView table=new TableView();
        
        System.out.println(Datas.getUserMap().entrySet().toString());
        Datas.getCloneUserMap().forEach((key,value)->{
            value.put("user",key);
            ObservableList<Map<String, String>> data = FXCollections.<Map<String, String>>observableArrayList();
            data.add(value);
            table.getItems().addAll(data);
        });
        
        int col_width=100;
        TableColumn user_col=new TableColumn("User Name");
        user_col.setCellValueFactory(new MapValueFactory<>("user"));
        user_col.setPrefWidth(col_width);
        
        TableColumn general_col=new TableColumn<>("General");
        general_col.setCellValueFactory(new MapValueFactory<>("general_info"));
        general_col.setPrefWidth(col_width);
        
        TableColumn student_col=new TableColumn("Student");
        student_col.setCellValueFactory(new MapValueFactory<>("student_info"));
        student_col.setPrefWidth(col_width);
        
        TableColumn dinner_col=new TableColumn("Dinner");
        dinner_col.setCellValueFactory(new MapValueFactory<>("dinner_info"));
        dinner_col.setPrefWidth(col_width);
        
        TableColumn commerce_col=new TableColumn("Commerce");
        commerce_col.setCellValueFactory(new MapValueFactory<>("commerce_info"));
        commerce_col.setPrefWidth(col_width);
        
        TableColumn web_col=new TableColumn("Web");
        web_col.setCellValueFactory(new MapValueFactory<>("web_info"));
        web_col.setPrefWidth(col_width);
        
        TableColumn java_col=new TableColumn("Java");
        java_col.setCellValueFactory(new MapValueFactory<>("java_info"));
        java_col.setPrefWidth(col_width);
        
        TableColumn network_col=new TableColumn("Network");
        network_col.setCellValueFactory(new MapValueFactory<>("network_info"));
        network_col.setPrefWidth(col_width);
        
        TableColumn total_col=new TableColumn("Total");
        total_col.setCellValueFactory(new MapValueFactory<>("total_price"));
        total_col.setPrefWidth(col_width);
        
        Button back_btn=new Button("Back");
        Button log_out_btn=new Button("Log Out");
        
        back_btn.setOnAction(event->{
            Admin admin_stage=new Admin();
            admin_view.close();
            admin_stage.adminStage();
        });
        log_out_btn.setOnAction(event->{
            Conference_gui c_gui=new Conference_gui();
            try {
                admin_view.close();
                c_gui.start(admin_view);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        

        table.getColumns().addAll(user_col,general_col,student_col,dinner_col,commerce_col,web_col,java_col,network_col,total_col);
        HBox hbox=new HBox(back_btn, log_out_btn);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(40);
        VBox vbox=new VBox(table, hbox);
        vbox.setId("vbox");
        vbox.setSpacing(30);
        table.setMaxWidth(920);
        table.setPrefHeight(750);
        
        Scene scene = new Scene(vbox, 1000, 900);
        scene.getStylesheets().add("user_data.css");
        admin_view.setTitle("User Selections");
        admin_view.setScene(scene);
        admin_view.show();
    }

}
