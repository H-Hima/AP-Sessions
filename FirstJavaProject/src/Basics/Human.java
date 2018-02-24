package Basics;

public class Human {
    Human.Eye right,left;
    String text="This is a string to represent a human";

    Human() {
        initializeEyes();
    }

    void print() {
        System.out.println("The value of the string is: "+text);
    }

    protected void initializeEyes() {
        right=new Eye();
        left=new Eye();
    }

    private void observe() {
    }

    class Eye {
        Human parent;

        Eye() {
            parent=Human.this;
        }

        String text="This is a string to represent a human eye";
        void print() {
            System.out.println("The value of the string is: "+text);
        }

        void sendObservation() {
            Human.this.observe();
        }
    }

    static class HumanStatistics{
        String text="This is a string to represent humankind statistics";
        void print() {
            System.out.println("The value of the string is: "+text);
        }
        static void print2() {
            System.out.println("The static function of the human class");
        }
    }
}
