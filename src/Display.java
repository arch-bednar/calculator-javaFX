import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


class Display extends TextField{

    /*
        Display based on TextField
        listener prevents from focusing on that Display
     */

    Display(){
        setFocusTraversable(false);
        setEditable(false);     //edition disabled
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
