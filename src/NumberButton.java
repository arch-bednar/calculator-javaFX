import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class NumberButton extends Button{

    private Calculator calc;
    private int number;
    private String num;

    public NumberButton(String number, Calculator calc){
        super(number);
        setFocusTraversable(false);
        this.calc = calc;
        this.num = number;
        this.number = Integer.parseInt(number);
        System.out.println(number);
        this.setOnAction(setActionEvent());
        /*
        this.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e) {
                if(e.getText().equals(num)){
                    System.out.println("xDDDDD");
                }
            }
        });
         */
        this.setMinWidth(55);
    }

    private EventHandler<ActionEvent> setActionEvent(){

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                //System.out.println("Siema");
                setFocusTraversable(false);
                if(calc.getText().equals("0")){
                    calc.setText(num);
                }else if(calc.getText().equals("-0")){
                    calc.setText("-" + num);
                }else{
                    calc.setText(calc.getText() + num);
                }
            }
        };

        return event;
    }
}
