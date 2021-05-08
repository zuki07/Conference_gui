



package conference_gui;

import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;


public class AdminDataView  {


    public void startViewStage() {
        Stage admin_view=new Stage();
        TableView table=new TableView();
        
        System.out.println(Datas.getUserMap().entrySet().toString());
        Datas.getUserMap().forEach((key,value)->{
            value.put("user",key);
            ObservableList<Map<String, String>> data = FXCollections.<Map<String, String>>observableArrayList();
            data.add(value);
            table.getItems().addAll(data);
        });
        TableColumn user_col=new TableColumn("User Name");
        user_col.setCellValueFactory(new MapValueFactory<>("user"));
        
        TableColumn general_col=new TableColumn<>("General");
        general_col.setCellValueFactory(new MapValueFactory<>("general_info"));
        
        TableColumn student_col=new TableColumn("Student");
        student_col.setCellValueFactory(new MapValueFactory<>("student_info"));
        
        TableColumn dinner_col=new TableColumn("Dinner");
        dinner_col.setCellValueFactory(new MapValueFactory<>("dinner_info"));
        
        TableColumn commerce_col=new TableColumn("Commerce");
        commerce_col.setCellValueFactory(new MapValueFactory<>("commerce_info"));
        
        TableColumn web_col=new TableColumn("Web");
        web_col.setCellValueFactory(new MapValueFactory<>("web_info"));
        
        TableColumn java_col=new TableColumn("Java");
        java_col.setCellValueFactory(new MapValueFactory<>("java_info"));
        
        TableColumn network_col=new TableColumn("Network");
        network_col.setCellValueFactory(new MapValueFactory<>("network_info"));
        
        TableColumn total_col=new TableColumn("Total");
        total_col.setCellValueFactory(new MapValueFactory<>("total_price"));
        

        table.getColumns().addAll(user_col,general_col,student_col,dinner_col,commerce_col,web_col,java_col,network_col,total_col);
        VBox vbox=new VBox(table);
        vbox.setPadding(new Insets(20));
        
//        Stage admin_view=new Stage();

        Scene scene = new Scene(vbox, 600, 500);


//        Scene scene = new Scene(table, 300, 250);

        admin_view.setTitle("User Selections");
        admin_view.setScene(scene);
        admin_view.show();
    }

}
