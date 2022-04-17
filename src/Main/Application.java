package Main;

import UserInterface.*;

import javax.swing.*;

public class Application {

    private static GUI gui, errorGUI;
    private static HomeScreen homeScreen = new HomeScreen();
    private static PatientScreen patientsScreen = new PatientScreen();
    private static AddPatientScreen addPatientScreen = new AddPatientScreen();
    private static AddDemographicsScreen addDemographicsScreen = new AddDemographicsScreen();

    public static final int HOME_SCREEN = 0;
    public static final int PATIENTS_SCREEN = 1;
    public static final int ADD_PATIENTS_SCREEN = 2;
    public static final int ADD_DEMOGRAPHICS_SCREEN = 3;
    public static final int VIEW_EDIT_PATIENT_SCREEN = 4;
    public static final int VISITS_SCREEN = 5;
    public static final int ADD_VISITS_SCREEN = 6;
    public static final int VIEW_EDIT_VISITS_SCREEN = 7;
    public static final int PREDICT_DIAGNOSIS_SCREEN = 8;
    public static final int RECOMMEND_TREATMENT_SCREEN = 9;
    public static final int KNOWLEDGE_BASE = 10;

    private static int currentScreenId;

    private static JPanel[] allScreens = {
            homeScreen, patientsScreen, addPatientScreen,
            addDemographicsScreen};

    // instantiates the application and both the main frame and error frame
    public Application(){
        initAllFrames();
    }

    // starts the Hearing Clinic Main.Main.Application
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
        // instantiating the UserInterface.GUI automatically shows the window
        gui = new GUI();
        gui.addScreen(homeScreen);
    }

    // creates the error frame that pops up if there is a database error
    private void initErrorFrame(){
        // instantiate error UserInterface.GUI
        errorGUI = new GUI();
    }

    // set screen
    public static void setCurrentScreen(int applicationScreenId){

        System.out.println("THE CURRENT SCREEN ID: " + currentScreenId);
        // remove current screen
        gui.removeScreen(allScreens[currentScreenId]);

        // set current screen id and screen with new screne
        currentScreenId = applicationScreenId;
        gui.addScreen(allScreens[applicationScreenId]);
        System.out.println("THE NEW CURRENT SCREEN ID AFTER ADDING: " + currentScreenId);

        // update GUI
        gui.getFrame().update(gui.getFrame().getGraphics());
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
