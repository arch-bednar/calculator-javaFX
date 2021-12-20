public class Logic {

    Logic(){

    }

    private String calculate(char state){
        if(state != 10){
            //System.out.println("state "+calculator.state);
            String result="";
            switch(state){
                case '+':
                    result = String.valueOf(Double.parseDouble(result) + Double.parseDouble(getNumber()));
                    break;
                case '-':
                    result = String.valueOf(Double.parseDouble(result) - Double.parseDouble(getNumber()));
                    break;
                case '*':
                    result = String.valueOf(Double.parseDouble(result) * Double.parseDouble(getNumber()));
                    break;
                case '/':
                    result = String.valueOf(Double.parseDouble(result) / Double.parseDouble(getNumber()));
                    break;
                case '%':
                    result = String.valueOf(Double.parseDouble(result) * Double.parseDouble(getNumber())/100);
                    break;
                default:
                    break;
            }

        }
    }


}
