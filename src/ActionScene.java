import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ActionScene extends Scene{

    /*
        ActionScene based on Scene
        logic for scene inside
     */

    Calculator calculator; //for easy access to objects of calculator

    ActionScene(GridPane pane, double width, double height, Calculator calc){
        super(pane, width, height);
        this.calculator = calc;
        setEvent();
    }

    private void setEvent(){

        /*
        if operation = '.' then make double
        else if operation = '+/-' then change sign for plus/minus of number -> changeNegative function
        else if oepration = '+' then add two numbers
        else if operation = '-' then subtract two numbers
        else if operation = '*' then multiply two numbers
        else if operation = '/' then divide two numbers
        else if operation = '%' then calculate percent of number
        else if operation = 'CE' then clear field
        else if operation = 'C' then clear field with state
        else if opertaion = '=' then calculate operation
         */

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String sign="";

                if(event.getCode() == KeyCode.MINUS){
                    calculator.changeNegative();
                }else if(event.getCode() == KeyCode.PERIOD) {
                    if (!isDot()) {
                        setDot();
                    }
                }else if(event.getCode() == KeyCode.ENTER){
                    if(calculator.state != 10){
                        calculator.calculate();
                    }
                }else if(event.getCode() == KeyCode.P){
                    calculator.setOperation('%');
                }else if(event.getCode() == KeyCode.Q){
                    calculator.setOperation('q');
                    calculator.calculate();
                }else if(event.getCode() == KeyCode.M){
                    calculator.setOperation('*');
                }else if(event.getCode() == KeyCode.E){
                    calculator.clearField();
                }else if(event.getCode() == KeyCode.D){
                    calculator.setOperation('/');
                }else if(event.getCode() == KeyCode.C){
                    calculator.clearState();
                }else if(event.getCode() == KeyCode.A){
                    calculator.setOperation('+');
                }else if(event.getCode() == KeyCode.S){
                    calculator.setOperation('-');
                }else{
                    if(event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1){
                        sign="1";
                    }else if(event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2){
                        sign="2";
                    }else if(event.getCode() == KeyCode.DIGIT3 || event.getCode() == KeyCode.NUMPAD3){
                        sign="3";
                    }else if(event.getCode() == KeyCode.DIGIT4 || event.getCode() == KeyCode.NUMPAD4){
                        sign="4";
                    }else if(event.getCode() == KeyCode.DIGIT5 || event.getCode() == KeyCode.NUMPAD5){
                        sign="5";
                    }else if(event.getCode() == KeyCode.DIGIT6 || event.getCode() == KeyCode.NUMPAD6){
                        sign="6";
                    }else if(event.getCode() == KeyCode.DIGIT7 || event.getCode() == KeyCode.NUMPAD7){
                        sign="7";
                    }else if(event.getCode() == KeyCode.DIGIT8 || event.getCode() == KeyCode.NUMPAD8){
                        sign="8";
                    }else if(event.getCode() == KeyCode.DIGIT9 || event.getCode() == KeyCode.NUMPAD9){
                        sign="9";
                    }else if(event.getCode() == KeyCode.DIGIT0 || event.getCode() == KeyCode.NUMPAD0){
                        sign="0";
                    }
                    if(calculator.getText().equals("0")){
                        calculator.setText(sign);
                    }else if(calculator.getText().equals("-0")){
                        calculator.setText("-" + sign);
                    }else{
                        calculator.setText(calculator.getText()+sign);
                    }
                }
            }
        });
    }


    private boolean isDot(){
        return calculator.getText().contains(".");
    }

    private void setDot(){
        calculator.setText(calculator.getText() + ".");
    }
}
