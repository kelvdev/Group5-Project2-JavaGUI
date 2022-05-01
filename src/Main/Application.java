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
    private static ViewVisitsScreen viewVisitsScreen = new ViewVisitsScreen();
    private static ViewPatientsScreen viewPatientsScreen = new ViewPatientsScreen();
    private static AnalyticsScreen analyticsScreen = new AnalyticsScreen();
    private static SelectCurrentPatientScreen selectCurrentPatientScreen = new SelectCurrentPatientScreen();

    private static JFrame dialogFrame = new JFrame();
    private static JLabel dialogLabel = new JLabel();
    private static JPanel dialogPanel = new JPanel();

    public static final int HOME_SCREEN = 0;
    public static final int PATIENTS_SCREEN = 1;
    public static final int ADD_PATIENTS_SCREEN = 2;
    public static final int ADD_DEMOGRAPHICS_SCREEN = 3;
    public static final int VISIT_SCREEN = 4;
    public static final int ADD_VISIT_SCREEN = 5;
    public static final int THI_SCREEN = 6;
    public static final int VIEW_VISITS_SCREEN = 7;
    public static final int VIEW_PATIENTS_SCREEN = 8;
    public static final int ANALYTICS_SCREEN = 9;
    public static final int SELECT_CURRENT_PATIENT_SCREEN = 10;

    public static final int PATIENT_THC_EMPTY = -1;
    public static final int VISIT_ID_EMPTY = -1;

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
            viewVisitsScreen,
            viewPatientsScreen,
            analyticsScreen,
            selectCurrentPatientScreen
    };

    public static DBConnector dbConnector = new DBConnector(
            "jdbc:mysql://localhost/team5",
            "root",
            "myawesomepassword");

    public static DBReaderWriter dbReaderWriter;

    private static ComponentDesign componentDesign = new ComponentDesign();

    // instantiates the application and both the main frame and error frame
    public Application(){
        initAllFrames();
    }

    // starts the Hearing Clinic Main.Main.Application
    public void start(){
        if(testDatabaseConnection()){
            gui.show();
            viewPatientsScreen.initTable();
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

        initMessageDialog();
        updateTitle();
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

        // remove current screen
        gui.removeScreen(allScreens[currentScreenId]);

        // set current screen id and screen with new screen
        currentScreenId = applicationScreenId;
        gui.addScreen(allScreens[applicationScreenId]);

        System.out.println("current screen ID " + currentScreenId);
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

    public static GUI getApplicationGUI(){
        return gui;
    }

    public static void updateTables(){
        analyticsScreen.updateTable();
        viewPatientsScreen.updateTable();
        viewVisitsScreen.updateTable();
    }

    public static void updateAnalytics(){
        analyticsScreen.updateAnalytics();
    }

    public static void updateTitle() {

        StringBuilder titleString = new StringBuilder(GUI.appName);

        String patientString = " | Current Patient THC: ";
        titleString.append(patientString);

        if (currentPatientTHC == -1) {

            titleString.append("NONE");

        } else {

            titleString.append(currentPatientTHC);

        }

        titleString.append(" | Current Visit ID: ");

        if(currentVisitID == -1) {

            titleString.append("NONE");

        } else {

            titleString.append(currentVisitID);

        }

        gui.getFrame().setTitle(titleString.toString());
    }

    private static void initMessageDialog(){
        dialogFrame.setLocation(Application.getApplicationGUI().getFrame().getX(),
                Application.getApplicationGUI().getFrame().getY());
        dialogFrame.setSize(500,200);
        dialogFrame.setResizable(true);
        dialogFrame.setVisible(true);
        dialogFrame.setLayout(null);
        dialogFrame.setBackground(GUI.bgColor);

        dialogLabel.setSize(500, 50);
        dialogLabel.setLocation(0,0);
        dialogLabel.setFont(componentDesign.textFont);
        dialogLabel.setHorizontalAlignment(JTextField.CENTER);
        dialogLabel.setBackground(GUI.bgColor);
        dialogLabel.setForeground(Color.WHITE);

        dialogPanel.setBackground(GUI.bgColor);
        dialogPanel.setSize(500, 300);
        dialogPanel.setLocation(0,0);
        dialogPanel.setLayout(null);

        dialogPanel.add(dialogLabel);
        dialogFrame.add(dialogPanel);
        dialogFrame.setVisible(false);
    }

    public static void displayMessage(String title, String message){
        dialogFrame.setTitle(title);
        dialogLabel.setText(message);
        dialogFrame.setVisible(true);
    }

    /*
    try to connect to database, if connects then return true
     and application can start else return false, send error message, and don't open app.
     */

    private boolean testDatabaseConnection() {
        if(dbConnector.getConnection() != null) {
            dbReaderWriter = new DBReaderWriter(dbConnector);
            return true;
        }else {
            return false;
        }
    }

    // close DBConnection
    public static void stop(){
        dbConnector.closeConnection();
    }

}
