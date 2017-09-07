public class main {
    public enum state { START, ZERO, ONE, DEAD}

    public static void check_state(String str) {
        char[] s = str.toCharArray();
        state current_state = state.START;
        int i = 0;

        if (str.length() == 0) { // We received a string of 0 length which fits the epsilon case
            System.out.println("The string, '', fits the DFA.");
            return;
        }

        for (char c: s) {
            boolean cero = Character.toString(c).matches("0");
            boolean uno = Character.toString(c).matches("1");
            if (!cero && !uno) { // Probably redundant now.
                current_state = state.DEAD;
            } else if(cero && (current_state == state.START || current_state == state.ZERO)) {
                if (i == 0 || i == 1) {
                    current_state = state.ZERO;
                    i = 1;
                    System.out.println("Processing 0");
                } else {
                    i = -1;
                    current_state = state.DEAD;
                }
            } else if( uno && current_state != state.DEAD) {
                if (i >= 1 || current_state == state.START) {
                    i = 2;
                    current_state = state.ONE;
                    System.out.println("Processing 1");
                } else {
                    i = -1;
                    current_state = state.DEAD;
                }
            } else {
                current_state = state.DEAD;
            }

            if (current_state == state.DEAD){
                System.out.printf("Processing %c\tIt doesn't match the DFA\n", c);
                break;
            }
        }

        if (current_state == state.DEAD || i < 1) {
            System.out.printf("The string, %s, doesn't match the DFA\n", str);
        } else if( i >= 1) {
            System.out.printf("The string, %s, matches the DFA\n", str);
        }
    }

    public static void main(String[] args) {
        String test_1 = "0011";
        String test_2 = "10011";
        String test_3 = "001011";
        String test_4 = "0012";
        String test_5 = "0000";
        String test_6 = "1111";

        check_state(test_1);
        check_state(test_2);
        check_state(test_3);
        check_state(test_4);
        check_state(test_5);
        check_state(test_6);
    }
}
