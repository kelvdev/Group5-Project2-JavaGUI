package Main;

import Database.DBConnector;
import Database.DBReaderWriter;
import UserInterface.*;

import javax.swing.*;
import java.awt.*;

public class Application {

    private static GUI gui, errorGUI;
    private static HomeScreen homeScreen = new HomeScreen();
    private static PatientScreen patientsScreen = new PatientScreen();
    private static AddPatientScreen addPatientScreen = new AddPatientScreen();
    private static AddDemographicsScreen addDemographicsScreen = new AddDemographicsScreen();
    private static VisitScreen visitScreen = new VisitScreen();
    private static AddVisitScreen addVisitScreen = new AddVisitScreen();
    private static THIScreen thiScreen = new THIScreen();
    private static ViewDeleteVisitsScreen viewDeleteVisitsScreen = new ViewDeleteVisitsScreen();
    private static ViewEditPatientsScreen viewEditPatientsScreen = new ViewEditPatientsScreen();
    private static AnalyticsScreen analyticsScreen = new AnalyticsScreen();

    public static final int HOME_SCREEN = 0;
    public static final int PATIENTS_SCREEN = 1;
    public static final int ADD_PATIENTS_SCREEN = 2;
    public static final int ADD_DEMOGRAPHICS_SCREEN = 3;
    public static final int VISIT_SCREEN = 4;
    public static final int ADD_VISIT_SCREEN = 5;
    public static final int THI_SCREEN = 6;
    public static final int VIEW_DELETE_VISITS_SCREEN = 7;
    public static final int VIEW_EDIT_PATIENTS_SCREEN = 8;
    public static final int ANALYTICS_SCREEN = 9;

    private static int currentPatientTHC = -1;
    private static int currentVisitID = -1;

    private static int currentScreenId;

    private static JPanel[] allScreens = {
            homeScreen,
            patientsScreen,
            addPatientScreen,
            addDemographicsScreen,
            visitScreen,
            addVisitScreen,
            thiScreen,
            viewDeleteVisitsScreen,
            viewEditPatientsScreen,
            analyticsScreen
    };

    public static DBConnector dbConnector = new DBConnector(
            "jdbc:mysql://localhost/Team5",
            "root",
            "myawesomepassword");

    public static DBReaderWriter dbReaderWriter = new DBReaderWriter(dbConnector);

    // instantiates the application and both the main frame and error frame
    public Application(){
        initAllFrames();
    }

    // starts the Hearing Clinic Main.Main.Application
    public void start(){
        if(testDatabaseConnection()){
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
        JLabel errorLabel = new JLabel("Error connecting to database! Please try again later");
        errorLabel.setSize(400, 400);
        errorLabel.setLocation(0, 0);
        errorLabel.setForeground(Color.WHITE);

        JPanel errorPanel = new JPanel();
        errorPanel.setSize(400, 400);
        errorPanel.setLocation(0,0);
        errorPanel.setBackground(GUI.bgColor);

        errorPanel.add(errorLabel);

        errorGUI.getFrame().setSize(400, 400);
        errorGUI.addScreen(errorPanel);
    }

    // set screen
    public static void setCurrentScreen(int applicationScreenId){

        System.out.println("THE CURRENT SCREEN ID: " + currentScreenId);
        // remove current screen
        gui.removeScreen(allScreens[currentScreenId]);

        // set current screen id and screen with new screen
        currentScreenId = applicationScreenId;
        gui.addScreen(allScreens[applicationScreenId]);
        System.out.println("THE NEW CURRENT SCREEN ID AFTER ADDING: " + currentScreenId);

        // update GUI
        gui.getFrame().update(gui.getFrame().getGraphics());
    }

    // set current patient THC as a global variable
    public static void setCurrentPatientTHC(int THC){
        Application.currentPatientTHC = THC;
    }

    public static int getCurrentPatientTHC(){
        return Application.currentPatientTHC;
    }

    // set current visitID as a global variable
    public static void setCurrentVisitID(int visitID){
        Application.currentVisitID = visitID;
    }

    public static int getCurrentVisitID(){
        return Application.currentVisitID;
    }

    /*
    try to connect to database, if connects then return true
     and application can start else return false, send error message, and don't open app.
     */

    private boolean testDatabaseConnection() {
        if(dbConnector.getConnection() != null) {
            return true;
        } else {
            return false;
        }
    }

}
