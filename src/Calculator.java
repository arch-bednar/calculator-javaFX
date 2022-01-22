import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Calculator extends Application{

    /*
        Calculator - application JavaFx
     */

    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    public String result="0";       //like temp variable, used to store result of operation or first number when awaits for another
    protected char state = 10;      //state of operation (=10 then is no current state), defines operation like add, div, etc...
    private Display textField;

    public Calculator(){
        //super() is not needed
        //makeFrame();
        //makeLayout();
    }

    public void makeFrame(){

    }

    //make layout for application with buttons and display
    public void makeLayout(){
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(300, 300);
        //gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        //gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        makeField();
        makeButtons();

        //Scene scene = new Scene( gridPane,300, 300);
        ActionScene scene = new ActionScene(gridPane, 300, 300, this);
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        stage.setScene(scene);
    }

    //make display and add to grid
    public void makeField(){
        textField = new Display();
        textField.setPadding(new Insets(5,0,5,0));
        gridPane.setMargin(textField, new Insets(0,0,5,0));
        gridPane.add(textField,0,0,5, 2);
    }

    //make buttons
    public void makeButtons(){
        makeNumbers();
        makeSigns();
    }

    //make number buttons
    public void makeNumbers(){
        int[][] numbers = { {7,8,9},
                            {4,5,6},
                            {1,2,3},
                            {0}};

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                NumberButton button = new NumberButton(Integer.toString(numbers[i][j]), this);
                //makeEvent(button);
                //button.setActionEvent();
                gridPane.add(button, 0+j, 4+i,1,1);
            }
        }

        NumberButton button = new NumberButton(Integer.toString(numbers[3][0]), this);
        gridPane.add(button,1,7,1,1);
    }

    //makes operation buttons
    public void makeSigns(){
        ActionButton sign = new ActionButton("C", this);
        sign.setMaxWidth(Double.MAX_VALUE);
        sign.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(sign,3,4,2,1);

        sign = new ActionButton("CE", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign, 3, 5, 2, 1);

        sign = new ActionButton("%", this);
        gridPane.add(sign, 3, 6, 1, 1);

        sign = new ActionButton("sqrt", this);
        gridPane.add(sign, 4, 6, 1, 1);

        sign = new ActionButton(".", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign, 0 ,7,1,1);

        sign = new ActionButton("+/-", this);
        //sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sign.setMaxWidth(35);
        gridPane.add(sign, 2, 7, 1, 1);

        sign = new ActionButton("-", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign, 3,7,1,1);

        sign = new ActionButton("/", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign, 4, 7, 1, 1);

        sign = new ActionButton("=", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign, 0, 8, 3, 1);

        sign = new ActionButton("+", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign,3, 8, 1,1);

        sign = new ActionButton("*", this);
        sign.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(sign, 4,8,1,1);

    }

    //calculate operation
    protected void calculate(){
        if(state != 10){
            //System.out.println("state "+calculator.state);

            switch(state){
                case '+':
                    add();
                    break;
                case '-':
                    sub();
                    break;
                case '*':
                    mul();
                    break;
                case '/':
                    div();
                    break;
                case '%':
                    percent();
                    break;
                case 'q':
                    sqrt();
                    break;
                default:
                    break;
            }
            setState();
        }
    }

    private void add(){
        //System.out.println("state +"+state);
        result = String.valueOf(Double.parseDouble(result) + Double.parseDouble(getNumber()));
        //System.out.println("wynik +"+getNumber());
        //System.out.println("dodawanie");
        setNumber(result);
    }

    private void sub(){
        result = String.valueOf(Double.parseDouble(result) - Double.parseDouble(getNumber()));
        setNumber(result);
    }

    private void mul(){
        result = String.valueOf(Double.parseDouble(result) * Double.parseDouble(getNumber()));
        setNumber(result);
    }

    private void div(){
        result = String.valueOf(Double.parseDouble(result) / Double.parseDouble(getNumber()));
        setNumber(result);
    }

    private void percent(){
        result = String.valueOf(Double.parseDouble(result) * Double.parseDouble(getNumber())/100);
        setNumber(result);
    }

    public void sqrt(){
        //System.out.println(Double.parseDouble(getNumber()));
        result = String.valueOf(Math.sqrt(Double.parseDouble(result)));
        //System.out.println(result);
        setText(result);
        setState();
    }

    public void makeDouble(){
        if(!getNumber().contains(".")){
            setText(getNumber()+".");
        }
    }

    public void setOperation(char sign){
        setState(sign);
        result=getNumber();
        setNumber();
    }

    public String getText(){
        return textField.getText();
    }

    public void setText(){
        textField.setText("0");
    }

    public void setText(String newString){
        textField.setText(newString);
    }

    //change negative number to positive and vice versa, used in 'C' operation
    public void changeNegative(){
        if(getText().substring(0, 1).equals("-")){
            setText(getText().substring(1));
        }else{
            setText("-"+getText());
        }
    }

    //clear state of calculator with numbers, used in 'CE' operation
    public void clearState(){
        result="0";
        textField.setText(result);
        state=10;
    }


    private String getNumber(){
        return getText();
    }

    private void setNumber(){
        setText();
    }

    private void setNumber(String number){
        setText(number);
    }

    private void setState(){
        state = 10;
    }

    private void setState(char state){
        this.state = state;
    }

    //clear display
    public void clearField(){
        textField.setText("0");
    }


    //start the application
    @Override
    public void start(Stage stage){
        this.stage=stage;
        makeFrame();
        makeLayout();
        this.stage.show();
        this.stage.setTitle("Calculator javaFX");
        this.setText();
/*
        stage.addEventHandler(KeyEvent.KEY_PRESSED, ev->{
            if(ev.getCode() == KeyCode.NUMPAD3){
                setText(getText()+String.valueOf(KeyCode.NUMPAD3));
            }
        });

 */
    }


    public static void main(String[] args){
        launch(args);
    }
}


