import java.util.Scanner;

public class dfa {
    
    //Enums to denote the current state of the input string
    public enum state { START, ZERO, ONE, STOP}

    // Empty constructor
    public dfa(){}

    public void check_state(String str) {
        char[] input_string = str.toCharArray();
        state current_state = state.START;
        int i = 0;

        if (str.length() == 0) { // We received a string of 0 length which fits the epsilon case
            System.out.println("The string, '', fits the DFA.");
            return;
        }
        
        for (char c: input_string) {
            boolean cero = Character.toString(c).matches("0"); // true if the string is "0"
            boolean uno = Character.toString(c).matches("1"); // true if the string is "1"
            
            if(cero && (current_state == state.START || current_state == state.ZERO)) { // condition when the string is zero
                if (i == 0 || i == 1) {
                    current_state = state.ZERO;
                    i = 1;
                    System.out.println("Processing 0..");
                } else {
                    i = -1;
                    current_state = state.STOP;
                }
            } else if( uno && current_state != state.STOP) { // condition when the string is one
                if (i >= 1 || current_state == state.START) {
                    i = 2;
                    current_state = state.ONE;
                    System.out.println("Processing 1..");
                } else {
                    i = -1;
                    current_state = state.STOP;
                }
            } else { // condition when the string has values apart from 0's and 1's
                current_state = state.STOP;
            }

            if (current_state == state.STOP){
                System.out.printf("Processing %c \n", c);
                break;
            }
        }

        if (current_state == state.STOP || i < 1) {
            System.out.printf("The string, %s, doesn't match the DFA\n\n", str);
        } else if( i >= 1) {
            System.out.printf("The string, %s, matches the DFA\n\n", str);
        }
    }

    // Testing some sample strings
    public void testStrings() {
        String testString_1 = "0011";
        System.out.printf("Testing the string: %s \n", testString_1);
        check_state(testString_1);
        
        String testString_2 = "10011";
        System.out.printf("Testing the string: %s \n", testString_2);
        check_state(testString_2);
        
        String testString_3 = "001011";
        System.out.printf("Testing the string: %s \n", testString_3);
        check_state(testString_3);
        
        String testString_4 = "0012";
        System.out.printf("Testing the string: %s \n", testString_4);
        check_state(testString_4);
        
        String testString_5 = "0000";
        System.out.printf("Testing the string: %s \n", testString_5);
        check_state(testString_5);
        
        String testString_6 = "1111";
        System.out.printf("Testing the string: %s \n", testString_6);
        check_state(testString_6);

        String testString_7 = "";
        System.out.printf("Testing the string: %s \n", testString_7);
        check_state(testString_7);
    }

    public static void displayMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1) Input strings\n2) Run tests\n3) Quit");
    }

    public static void main(String[] args) {
        dfa dfaObj = new dfa();
        int menuChoice = 0;
        String line;
        Scanner scanner = new Scanner(System.in);

        try{
        while (menuChoice != 3) {
            displayMenu();
            menuChoice = scanner.nextInt();
            scanner.nextLine();
           
            // enter the switch statement based on the user's choice
            // case 1: Input strings, case 2: test sample strings, case 3: quit the application
            switch(menuChoice){
                case 1:
                     System.out.println("Please input your string.");
                     line = scanner.nextLine();
                     if (line.equals("")){
                         dfaObj.check_state("");
                     } else {
                         dfaObj.check_state(line);
                     }
                     break;
                case 2:
                    dfaObj.testStrings();
                    break;
                case 3:
                    System.out.println("Quit the application..");
                default:
                    System.out.println("Invalid choice. Choose again");
                    continue;
            }
        }
        }catch(InputMismatchException e) {
            System.err.println("InputMismatchException: The menu input is not a valid integer");
        }finally{
            scanner.close();
        }
    }
}
