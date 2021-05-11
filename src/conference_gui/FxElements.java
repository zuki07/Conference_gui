



package conference_gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class FxElements {

    public TextField setTextField(Pos position, double width, String prompt_text){
        TextField text_field=new TextField();
        text_field.setAlignment(position);
        text_field.setMaxWidth(width);
        text_field.setPromptText(prompt_text);
        text_field.setFocusTraversable(false);
        return text_field;
    }
    
    public PasswordField setPasswordField(Pos position, double width, String prompt_text){
        PasswordField password_field=new PasswordField();
        password_field.setAlignment(position);
        password_field.setMaxWidth(width);
        password_field.setPromptText(prompt_text);
        password_field.setFocusTraversable(false);
        return password_field;
    }
    
    public void setAdminGrid(GridPane grid, Label current_label, 
                            TextField input, Button btn, int row){
        grid.add(current_label, 0, row);
        GridPane.setHalignment(current_label, HPos.RIGHT);
        grid.add(input, 1, row);
        grid.add(btn, 2, row);
    }
    
    public void setUserGrid(GridPane grid, Label label, ComboBox input, int row){
        grid.add(label, 0, row);
        GridPane.setHalignment(label, HPos.RIGHT);
        grid.add(input, 1, row);
    }
}
