



package conference_gui;

import java.io.IOException;
import java.util.HashMap;
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
        Map<String,Map<String,String>> map_data=new HashMap<>();
        map_data.putAll(Datas.getUserMap());
        map_data.forEach((key,value)->{
            if(key.equals("admin")){
                return;
            }
            value.put("user",key);
            ObservableList<Map<String, String>> data = FXCollections.<Map<String, String>>observableArrayList();
            data.add(value);
            table.getItems().addAll(data);
        });
        
        int col_width=100;
        
        TableColumn user_col=tableColumn("User Name", "user", col_width);
        TableColumn general_col=tableColumn("General", "general_info", col_width);
        TableColumn student_col=tableColumn("Student", "student_info", col_width);
        TableColumn dinner_col=tableColumn("Dinner", "dinner_info", col_width);
        TableColumn commerce_col=tableColumn("Commerce", "commerce_info", col_width);
        TableColumn web_col=tableColumn("Web", "web_info", col_width);
        TableColumn java_col=tableColumn("Java", "java_info", col_width);
        TableColumn network_col=tableColumn("Network", "network_info", col_width);
        TableColumn total_col=tableColumn("Total", "total_price", col_width);
        table.getColumns().addAll(user_col,general_col,student_col,dinner_col,commerce_col,web_col,java_col,network_col,total_col);

        
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
    
    public TableColumn tableColumn(String header, String value, int col_width){
        TableColumn col=new TableColumn(header);
        col.setCellValueFactory(new MapValueFactory<>(value));
        col.setPrefWidth(col_width);
        return col;
    }

}
