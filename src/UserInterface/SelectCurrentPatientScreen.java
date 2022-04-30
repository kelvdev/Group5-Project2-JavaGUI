package UserInterface;

import DataObjects.Patient;
import Database.DBConnector;
import Database.DBReaderWriter;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectCurrentPatientScreen extends JPanel {

    JLabel titleLabel, patientTHCPromptLabel, searchResultLabel;
    JTextField enterTHCTF;
    JButton enterTHCButton, backButton;

    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/10;
    ComponentDesign componentDesign = new ComponentDesign();

    public SelectCurrentPatientScreen(){
        this.setBackground(GUI.bgColor);

        initLayout();
        initLabels();
        initButtons();
        initTextFields();
        initActionListeners();
    }

    private void initLayout() {

        // set main layout to null for positional based layout
        this.setLayout(null);

        // main panel
        this.setBounds(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        this.setBackground(GUI.bgColor);
    }

    private void initLabels(){
        titleLabel = new JLabel("Set Current Patient");

        titleLabel.setBounds(0, 2, GUI.DEFAULT_WIDTH, 70);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(componentDesign.labelFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        patientTHCPromptLabel = new JLabel("Enter Patient THC");
        searchResultLabel = new JLabel("Patient not found! Try a different THC.");
        searchResultLabel.setVisible(false);

        patientTHCPromptLabel.setFont(componentDesign.textFont);

        patientTHCPromptLabel.setForeground(Color.WHITE);
        searchResultLabel.setForeground(Color.WHITE);

        int height = GUI.DEFAULT_HEIGHT/20;

        patientTHCPromptLabel.setBounds(0, 160, GUI.DEFAULT_WIDTH, height);
        searchResultLabel.setBounds(0, 440, GUI.DEFAULT_WIDTH, height);

        patientTHCPromptLabel.setHorizontalAlignment(JLabel.CENTER);
        searchResultLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleLabel);
        this.add(patientTHCPromptLabel);
        this.add(searchResultLabel);
    }

    // initializes all buttons for home screen
    private void initButtons(){
        enterTHCButton = new JButton("Search");
        backButton = new JButton("⬅");

        int buttonX = (GUI.DEFAULT_WIDTH/2) - (buttonWidth/2);

        enterTHCButton.setBounds(buttonX, 250, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        enterTHCButton.setHorizontalAlignment(JButton.CENTER);

        enterTHCButton.setFont(componentDesign.buttonFont);
        backButton.setFont(componentDesign.buttonFont);

        enterTHCButton.setForeground(Color.GREEN);
        backButton.setForeground(Color.YELLOW);

        enterTHCButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(enterTHCButton);
        this.add(backButton);
    }

    private void initTextFields(){
        enterTHCTF = new JTextField();

        int width = GUI.DEFAULT_WIDTH/4;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = (GUI.DEFAULT_WIDTH/2) - (width/2);
        int y = 160;

        enterTHCTF.setBounds(x1, y + 50, width, height);
        enterTHCTF.setHorizontalAlignment(JTextField.CENTER);

        this.add(enterTHCTF);

    }

    private boolean verifyPatientExists() {

        String thcString = enterTHCTF.getText();

        int thc = Integer.parseInt(thcString);

        try {
            if (Application.dbReaderWriter.getPatient(thc) != null) {
                searchResultLabel.setVisible(false);
                Application.setCurrentPatientTHC(thc);
                Application.setCurrentVisitID(Application.VISIT_ID_EMPTY);
                Application.updateTitle();
                return true;
            } else {
                searchResultLabel.setVisible(true);
                return false;
            }
        } catch (Exception e){
            searchResultLabel.setVisible(true);
            System.out.println("Error locating the patient info " + e.getMessage());
            return false;
        }
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){

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

        enterTHCButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(verifyPatientExists()) {
                    Application.setCurrentScreen(Application.ADD_VISIT_SCREEN);
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
                enterTHCButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                enterTHCButton.setBackground(GUI.bgColor);
            }
        });
    }

}
