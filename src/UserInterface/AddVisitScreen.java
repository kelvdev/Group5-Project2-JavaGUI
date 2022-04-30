package UserInterface;

import DataObjects.Patient;
import DataObjects.THI;
import Database.DBReaderWriter;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddVisitScreen extends JPanel {

    JLabel title, reasonForVisitLabel;
    JTextArea reasonForVisitTA;
    JButton createVisitButton, startTHIButton, cancelButton, backButton;
    ComponentDesign componentDesign = new ComponentDesign();
    private int buttonWidth = GUI.DEFAULT_WIDTH / 4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT / 10;
    private int patientTHC;
    private int visitId;

    public AddVisitScreen() {
        initLayout();
        initLabels();
        initTextAreas();
        initButtons();
        initActionListeners();
    }

    private void initLayout() {

        // set main layout to null for positional based layout
        this.setLayout(null);

        // main panel
        this.setBounds(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        this.setBackground(GUI.bgColor);
    }

    private void initLabels() {

        try {
            Patient patient = Application.dbReaderWriter.getPatient(patientTHC);
            title = new JLabel("Create Visit for " +
                    patient.getFirstName() + " " + patient.getSurname() +
                    "/ THC: " + patientTHC);
        } catch (Exception e) {
            title = new JLabel("Create Visit");
        }
        title.setBounds(0, 2, GUI.DEFAULT_WIDTH, 70);
        title.setForeground(Color.WHITE);
        title.setFont(componentDesign.labelFont);
        title.setHorizontalAlignment(JLabel.CENTER);

        reasonForVisitLabel = new JLabel("What is the reason for visit?");
        reasonForVisitLabel.setBounds(0, 100, GUI.DEFAULT_WIDTH, 70);
        reasonForVisitLabel.setForeground(Color.WHITE);
        reasonForVisitLabel.setFont(componentDesign.textFont);
        reasonForVisitLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(title);
        this.add(reasonForVisitLabel);
    }

    private void initTextAreas(){
        reasonForVisitTA = new JTextArea();
        int width = GUI.DEFAULT_WIDTH-250;
        int height = GUI.DEFAULT_HEIGHT/4;
        int x = (GUI.DEFAULT_WIDTH/2) - (width/2);
        int y = 175;
        reasonForVisitTA.setBounds(x, y, width, height);
        reasonForVisitTA.setLineWrap(true);
        reasonForVisitTA.setBackground(Color.LIGHT_GRAY);
        reasonForVisitTA.setForeground(Color.BLACK);

        this.add(reasonForVisitTA);
    }

    // initializes all buttons for home screen
    private void initButtons() {
        createVisitButton = new JButton("Create Visit");
        startTHIButton = new JButton("Start THI Questionnaire");
        cancelButton = new JButton("Cancel Visit");
        backButton = new JButton("<");

        int startButtonX = 40;
        int middleButtonX = (GUI.DEFAULT_WIDTH / 2) - (buttonWidth / 2);
        int endButtonX = (GUI.DEFAULT_WIDTH) - (buttonWidth) - 40;

        createVisitButton.setBounds(startButtonX, 400, buttonWidth, buttonHeight);
        startTHIButton.setBounds(middleButtonX, 400, buttonWidth, buttonHeight);
        cancelButton.setBounds(endButtonX, 400, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        createVisitButton.setFont(componentDesign.buttonFont);
        startTHIButton.setFont(componentDesign.buttonFont);
        cancelButton.setFont(componentDesign.buttonFont);
        backButton.setFont(componentDesign.buttonFont);

        createVisitButton.setForeground(Color.GREEN);
        startTHIButton.setForeground(Color.BLUE);
        cancelButton.setForeground(Color.RED);
        backButton.setForeground(Color.YELLOW);

        createVisitButton.setBackground(GUI.bgColor);
        startTHIButton.setBackground(GUI.bgColor);
        cancelButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(createVisitButton);
        this.add(startTHIButton);
        this.add(cancelButton);
        this.add(backButton);
    }

    public boolean createVisit(){

        int visitId = Application.dbReaderWriter.createVisit(Application.getCurrentPatientTHC(), reasonForVisitTA.getText());

        if(visitId > 0){
            Application.setCurrentVisitID(visitId);
            Application.updateTitle();
            try {
                Application.updateTables();
                Application.updateAnalytics();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            Application.displayMessage("Visit created", "Visit successfully created");
            clearScreen();
            return true;
        } else {
            Application.displayMessage("Visit create failed", "Visit failed to create");
            return false;
        }
    }

    private void clearScreen(){
        for(Component component : getComponents()){
            try{
                JTextField textField = (JTextField) component;
                textField.setText("");
            } catch (Exception e){

            }
        }
    }

    // initializes all action listeners for the buttons
    private void initActionListeners() {

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.VISIT_SCREEN);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                backButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                backButton.setBackground(GUI.bgColor);
            }
        });

        createVisitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (createVisit()) {
                    Application.setCurrentScreen(Application.HOME_SCREEN);
                    Application.setCurrentVisitID(Application.VISIT_ID_EMPTY);
                } else {

                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                createVisitButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                createVisitButton.setBackground(GUI.bgColor);
            }
        });

        startTHIButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (createVisit()) {
                    Application.setCurrentScreen(Application.THI_SCREEN);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                startTHIButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                startTHIButton.setBackground(GUI.bgColor);
            }
        });

        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.VISIT_SCREEN);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                cancelButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                cancelButton.setBackground(GUI.bgColor);
            }
        });
    }
}
