package UserInterface;

import DataObjects.Patient;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class AddPatientScreen extends JPanel {

    private JButton createPatientButton, cancelButton, addDemographicsButton, backButton;
    private JTextField firstNameTF, lastNameTF,
            dobTF, stateTF, zipTF, countryTF, ssnTF, insuranceTF;

    private JLabel firstNameLabel, lastNameLabel,
            dobLabel, stateLabel, zipLabel, countryLabel, ssnLabel, insuranceLabel;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/10;
    private ComponentDesign componentDesign = new ComponentDesign();


    public AddPatientScreen(){
        this.setBackground(GUI.bgColor);

        initLayout();
        initLabels();
        initButtons();
        initTextFields();
        initActionListeners();
    }

    // initializes panel (this) layout

    private void initLayout() {

        // set main layout to null for positional based layout
        this.setLayout(null);

        // main panel
        this.setBounds(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        this.setBackground(GUI.bgColor);
    }

    // initializes all buttons for home screen
    private void initButtons(){
        createPatientButton = new JButton("Create");
        cancelButton = new JButton("Cancel");
        addDemographicsButton = new JButton("Add Demographics");
        backButton = new JButton("⬅");

        int buttonX = GUI.DEFAULT_WIDTH - (buttonWidth + 20);

        createPatientButton.setBounds(buttonX, 80, buttonWidth, buttonHeight);
        cancelButton.setBounds(buttonX, 160, buttonWidth, buttonHeight);
        addDemographicsButton.setBounds(buttonX, 240, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        createPatientButton.setFont(componentDesign.buttonFont);
        cancelButton.setFont(componentDesign.buttonFont);
        addDemographicsButton.setFont(componentDesign.buttonFont);
        backButton.setFont(componentDesign.buttonFont);

        createPatientButton.setForeground(Color.GREEN);
        cancelButton.setForeground(Color.RED);
        addDemographicsButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        createPatientButton.setBackground(GUI.bgColor);
        cancelButton.setBackground(GUI.bgColor);
        addDemographicsButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(createPatientButton);
        this.add(cancelButton);
        this.add(addDemographicsButton);
        this.add(backButton);
    }

    // init labels

    private void initLabels(){
        // set main label
        JLabel title = new JLabel("Create New Patient");
        title.setBounds((GUI.DEFAULT_WIDTH/2)-100, 2, 400, 70);
        title.setForeground(Color.WHITE);
        title.setFont(componentDesign.labelFont);
        this.add(title);

        // set textfield labels

        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        dobLabel = new JLabel("Date of Birth (YYYY/MM/DD)");
        stateLabel = new JLabel("State");
        zipLabel = new JLabel("Zip");
        countryLabel = new JLabel("Country");
        ssnLabel = new JLabel("SSN");
        insuranceLabel = new JLabel("Insurance");

        firstNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setForeground(Color.WHITE);
        dobLabel.setForeground(Color.WHITE);
        stateLabel.setForeground(Color.WHITE);
        zipLabel.setForeground(Color.WHITE);
        countryLabel.setForeground(Color.WHITE);
        ssnLabel.setForeground(Color.WHITE);
        insuranceLabel.setForeground(Color.WHITE);

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 70;
        int x2 = x1+400;
        int y = 40;

        firstNameLabel.setBounds(x1, y + 80, width, height);
        lastNameLabel.setBounds(x1, y + 160, width, height);
        dobLabel.setBounds(x2-50, y + 80, width, height);
        stateLabel.setBounds(x2, y + 160, width, height);
        zipLabel.setBounds(x1, y + 240, width, height);
        countryLabel.setBounds(x1, y + 320, width, height);
        ssnLabel.setBounds(x2, y + 240, width, height);
        insuranceLabel.setBounds(x2, y + 320, width, height);

        this.add(firstNameLabel);
        this.add(lastNameLabel);
        this.add(dobLabel);
        this.add(stateLabel);
        this.add(zipLabel);
        this.add(countryLabel);
        this.add(ssnLabel);
        this.add(insuranceLabel);

    }

    // init text fields

    private void initTextFields(){
        firstNameTF = new JTextField();
        lastNameTF = new JTextField();
        dobTF = new JTextField();
        stateTF = new JTextField();
        zipTF = new JTextField();
        countryTF = new JTextField();
        ssnTF = new JTextField();
        insuranceTF = new JTextField();

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 180;
        int x2 = x1 + 400;
        int y = 40;

        firstNameTF.setBounds(x1, y + 80, width, height);
        lastNameTF.setBounds(x1, y + 160, width, height);
        dobTF.setBounds(x2, y + 80, width, height);
        stateTF.setBounds(x2, y + 160, width, height);
        zipTF.setBounds(x1, y + 240, width, height);
        countryTF.setBounds(x1, y + 320, width, height);
        ssnTF.setBounds(x2, y + 240, width, height);
        insuranceTF.setBounds(x2, y + 320, width, height);

        this.add(firstNameTF);
        this.add(lastNameTF);
        this.add(dobTF);
        this.add(stateTF);
        this.add(zipTF);
        this.add(countryTF);
        this.add(ssnTF);
        this.add(insuranceTF);
    }

    private boolean submitInformation(){

        try {
            Patient patient = new Patient(
                    -1, countryTF.getText(), stateTF.getText(),
                    Integer.parseInt(zipTF.getText()), 0, lastNameTF.getText(),
                    firstNameTF.getText(), ssnTF.getText(), dobTF.getText(), insuranceTF.getText(),
                    "", "",
                    "", "",
                    ""
            );

            int newPatientTHC = Application.dbReaderWriter.createPatient(patient);

            if (newPatientTHC > 0) {
                System.out.println("Patient " + newPatientTHC + " created");

                Application.setCurrentPatientTHC(newPatientTHC);
                Application.updateTitle();

                clearScreen();
                Application.setCurrentScreen(Application.PATIENTS_SCREEN);
                Application.updateTables();
                return true;
            } else {
                System.out.println("Patient " + patient.getTHC() + " create FAILED");
                return false;
            }
        } catch (Exception e){

            return false;
        }
    }

    private void clearScreen(){
        for(Component component : getComponents()){
            try{
                JTextField textField = (JTextField) component;
                System.out.println(textField.getText());
                textField.setText("");
            } catch (Exception e){

            }
        }
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.PATIENTS_SCREEN);
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

        createPatientButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                submitInformation();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                createPatientButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                createPatientButton.setBackground(GUI.bgColor);
            }
        });

        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                clearScreen();
                Application.setCurrentScreen(Application.PATIENTS_SCREEN);
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

        addDemographicsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (submitInformation()) {
                    Application.setCurrentScreen(Application.ADD_DEMOGRAPHICS_SCREEN);
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
                addDemographicsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                addDemographicsButton.setBackground(GUI.bgColor);
            }
        });
    }

}