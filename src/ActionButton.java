import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class ActionButton extends Button{

    private String operation;
    private Calculator calculator;

    public ActionButton(String operation, Calculator calc){
        super(operation);
        this.setMinWidth(55);
        calculator = calc;
        this.operation=operation;
        this.setOnAction(setActionEvent());
        if(operation.equals("+")){
            System.out.println("siuema");
            this.setOnKeyPressed(setKeyHandler());
        }
    }

    public String concat(String sign){
        return "";
    }

    public void sqrt(){
        calculator.result = String.valueOf(Math.sqrt(Double.parseDouble(calculator.getText())));
        calculator.setText(calculator.result);
        setState();
    }

    public void makeDouble(){
        if(!getNumber().contains(".")){
            calculator.setText(getNumber()+".");
        }
    }

    public void setOperation(char sign){
        setState(sign);
        calculator.result=getNumber();
        setNumber();
    }

    private void calculate(){
        if(calculator.state != ' '){
            //System.out.println("state "+calculator.state);

            switch(calculator.state){
                case '+':
                    //System.out.println("state +"+state);
                    calculator.result = String.valueOf(Double.parseDouble(calculator.result) + Double.parseDouble(getNumber()));
                    //System.out.println("wynik +"+getNumber());
                    //System.out.println("dodawanie");
                    setNumber(calculator.result);
                    break;
                case '-':
                    calculator.result = String.valueOf(Double.parseDouble(calculator.result) - Double.parseDouble(getNumber()));
                    setNumber(calculator.result);
                    break;
                case '*':
                    calculator.result = String.valueOf(Double.parseDouble(calculator.result) * Double.parseDouble(getNumber()));
                    setNumber(calculator.result);
                    break;
                case '/':
                    calculator.result = String.valueOf(Double.parseDouble(calculator.result) / Double.parseDouble(getNumber()));
                    setNumber(calculator.result);
                    break;
                case '%':
                    calculator.result = String.valueOf(Double.parseDouble(calculator.result) * Double.parseDouble(getNumber())/100);
                    setNumber(calculator.result);
                    break;
                default:
                    //System.out.println("derfault");
                    break;
            }
            setState();
        }
    }

    private EventHandler<ActionEvent> setActionEvent(){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                try {
                    if (operation.equals(".")) {
                        makeDouble();
                    } else if (operation.equals("sqrt")) {
                        sqrt();
                    } else if (operation.equals("+/-")) {
                        calculator.changeNegative();
                    } else if (operation.equals("+")) {
                        setOperation('+');
                    } else if (operation.equals("-")) {
                        setOperation('-');
                    } else if (operation.equals("*")) {
                        setOperation('*');
                    } else if (operation.equals("/")) {
                        setOperation('/');
                    } else if (operation.equals("%")) {
                        setOperation('%');
                    } else if (operation.equals("C")) {
                        calculator.clearState();
                    } else if (operation.equals("CE")) {
                        calculator.clearField();
                    } else if (operation.equals("=")) {
                        calculate();
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
