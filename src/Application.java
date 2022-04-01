import javax.swing.*;

public class Application {

    private GUI gui, errorGUI;

    // instantiates the application and both the main frame and error frame
    public Application(){
        initAllFrames();
    }

    // starts the Hearing Clinic Application
    public void start(){
        if(initDatabaseConnection() == true){
            gui.show();
        } else {
            errorGUI.show();
        }
    }

    // carries out any frame initiation method
    private void initAllFrames(){
        initMainFrame();
        initErrorFrame();
    }

    // creates the main user application frame
    public void initMainFrame(){
        // instantiating the GUI automatically shows the window
        gui = new GUI("Hearing Clinic App", 1000, 600);

        // Example title

        JLabel titleLabel = new JLabel("ListenHere Hearing Clinic");

        gui.addComponent(titleLabel);
    }

    // creates the error frame that pops up if there is a database error
    private void initErrorFrame(){
        // instantiate error GUI
        errorGUI = new GUI("ERROR", 500, 500);

        JLabel errorLabel = new JLabel("Sorry, we couldn't connect to the server! Please try again later.");

        errorGUI.addComponent(errorLabel);
    }

    /*
    TODO: CONNECT DATABASE
    try to connect to database, if connects then return true
     and application can start else return false, send error message, and don't open app.
     */

    private boolean initDatabaseConnection(){
        return true;
    }

}
