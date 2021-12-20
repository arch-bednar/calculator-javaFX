import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class Display extends TextField{

    Display(){
        setFocusTraversable(false);
        setEditable(false);
        setListener();
    }

    private void setListener(){
        this.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                setFocusTraversable(false);
            }
        });
    }


}
