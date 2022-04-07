import javax.swing.*;
import javax.swing.text.html.HTML;
import java.awt.*;

public class Application {

    private GUI gui, errorGUI;
    HomeScreen homeScreen = new HomeScreen();

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
        gui = new GUI("Hearing Clinic App", GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        gui.addScreen(homeScreen);
    }

    // creates the error frame that pops up if there is a database error
    private void initErrorFrame(){
        // instantiate error GUI
        errorGUI = new GUI("ERROR", 500, 500);
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
