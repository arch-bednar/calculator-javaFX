import javafx.application.Application;
import javafx.event.EventHandler;
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

    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    public String result="0";
    protected char state = 10;
    private Display textField;

    public Calculator(){
        //super() is not needed
        //makeFrame();
        //makeLayout();
    }

    public void makeFrame(){

    }

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

    public void makeField(){
        textField = new Display();
        gridPane.add(textField,0,0,5, 2);
    }

    public void makeButtons(){
        makeNumbers();
        makeSigns();
    }

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
        //button.setActionEvent();
        //makeEvent(button);
        gridPane.add(button,1,7,1,1);
    }
/*
    private void makeEvent(Button button){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("elo");
            }
        };

        button.setOnAction(event);
    }

 */

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

    public String getText(){
        return textField.getText();
    }

    public void setText(){
        textField.setText("0");
    }

    public void setText(String newString){
        textField.setText(newString);
    }

    public void changeNegative(){
        if(getText().substring(0, 1).equals("-")){
            setText(getText().substring(1));
        }else{
            setText("-"+getText());
        }
    }

    public void clearState(){
        result="0";
        textField.setText(result);
        state=10;
    }

    public void clearField(){
        textField.setText("0");
    }


    @Override
    public void start(Stage stage){
        this.stage=stage;
        makeFrame();
        makeLayout();
        this.stage.show();
        this.stage.setTitle("Calculator javaFX");
        this.setText();
        //textField.setText("siema");

    }


    public static void main(String[] args){
        launch(args);
    }
}


