import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class Display extends TextField{

    Display(){

    }

    private void setKeyListener(){
        this.setOnKeyTyped(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1){
                    setText(getText()+"1");
                }
            }
        });
    }


}
