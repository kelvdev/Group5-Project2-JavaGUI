package Main;

// JAVA SWING Documentation https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html
// JAVA SWING Tutorials from Oracle https://docs.oracle.com/javase/tutorial/uiswing/components/index.html

public class Main {

    public static Application application;

    public static void main(String[] args) {

        // Starts the entire hearing clinic application

        application = new Application();
        application.start();

    }

}
