import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ActionButton extends Button {

    private String operation;
    private Calculator calculator;

    public ActionButton(String operation, Calculator calc){
        super(operation);
        setFocusTraversable(false);
        this.setMinWidth(55);
        calculator = calc;
        this.operation=operation;
        this.setOnAction(setActionEvent());
    }


    private EventHandler<ActionEvent> setActionEvent(){

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

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                setFocusTraversable(false);
                try {
                    if (operation.equals(".")) {
                        calculator.makeDouble();
                    } else if (operation.equals("sqrt")) {
                        calculator.setOperation('q');
                        calculator.calculate();
                    } else if (operation.equals("+/-")) {
                        calculator.changeNegative();
                    } else if (operation.equals("+")) {
                        calculator.setOperation('+');
                    } else if (operation.equals("-")) {
                        calculator.setOperation('-');
                    } else if (operation.equals("*")) {
                        calculator.setOperation('*');
                    } else if (operation.equals("/")) {
                        calculator.setOperation('/');
                    } else if (operation.equals("%")) {
                        calculator.setOperation('%');
                    } else if (operation.equals("C")) {
                        calculator.clearState();
                    } else if (operation.equals("CE")) {
                        calculator.clearField();
                    } else if (operation.equals("=")) {
                        calculator.calculate();
                    }
                }catch(RuntimeException exception){
                    calculator.setText("0");
                }
            }
        };

        return event;
    }

    private EventHandler<KeyEvent> setKeyHandler(){
        EventHandler<KeyEvent> event = new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                if(e.getCode() == KeyCode.EQUALS){
                    calculator.changeNegative();
                }
            }
        };

        return event;
    }

    private String getNumber(){
        return calculator.getText();
    }

    private void setNumber(){
        calculator.setText();
    }

    private void setNumber(String number){
        calculator.setText(number);
    }

    private void setState(){
        calculator.state = 10;
    }

    private void setState(char state){
        calculator.state = state;
    }
}
