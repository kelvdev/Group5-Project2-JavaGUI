package UserInterface;

import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddPatientScreen extends JPanel {

    private JButton createPatientButton, cancelButton, addDemographicsButton, backButton;
    private JTextField firstNameTF, middleNameTF, lastNameTF,
            dobTF, genderTF, phoneTF, emailTF, addressTF, cityTF,
            stateTF, zipTF, countryTF, ssnTF, insuranceTF;

    private JLabel firstNameLabel, middleNameLabel, lastNameLabel,
            dobLabel, genderLabel, phoneLabel, emailLabel, addressLabel, cityLabel,
            stateLabel, zipLabel, countryLabel, ssnLabel, insuranceLabel;
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
        middleNameLabel = new JLabel("Middle Name");
        lastNameLabel = new JLabel("Last Name");
        dobLabel = new JLabel("Date of Birth");
        genderLabel = new JLabel("Gender");
        phoneLabel = new JLabel("Phone");
        emailLabel = new JLabel("Email");
        addressLabel = new JLabel("Address");
        cityLabel = new JLabel("City");
        stateLabel = new JLabel("State");
        zipLabel = new JLabel("Zip");
        countryLabel = new JLabel("Country");
        ssnLabel = new JLabel("SSN");
        insuranceLabel = new JLabel("Insurance");

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 40;
        int x2 = x1+400;

        firstNameLabel.setBounds(x1, 80, width, height);
        middleNameLabel.setBounds(x1, 160, width, height);
        lastNameLabel.setBounds(x1, 240, width, height);
        dobLabel.setBounds(x1, 320, width, height);
        genderLabel.setBounds(x1, 400, width, height);
        phoneLabel.setBounds(x1, 480, width, height);
        emailLabel.setBounds(x1, 560, width, height);
        addressLabel.setBounds(x2, 80, width, height);
        cityLabel.setBounds(x2, 160, width, height);
        stateLabel.setBounds(x2, 240, width, height);
        zipLabel.setBounds(x2, 320, width, height);
        countryLabel.setBounds(x2, 400, width, height);
        ssnLabel.setBounds(x2, 480, width, height);
        insuranceLabel.setBounds(x2, 560, width, height);

        this.add(firstNameLabel);
        this.add(middleNameLabel);
        this.add(lastNameLabel);
        this.add(genderLabel);
        this.add(dobLabel);
        this.add(phoneLabel);
        this.add(emailLabel);
        this.add(addressLabel);
        this.add(cityLabel);
        this.add(stateLabel);
        this.add(zipLabel);
        this.add(countryLabel);
        this.add(ssnLabel);
        this.add(insuranceLabel);

    }

    // init text fields

    private void initTextFields(){
        firstNameTF = new JTextField();
        middleNameTF = new JTextField();
        lastNameTF = new JTextField();
        dobTF = new JTextField();
        genderTF = new JTextField();
        phoneTF = new JTextField();
        emailTF = new JTextField();
        addressTF = new JTextField();
        cityTF = new JTextField();
        stateTF = new JTextField();
        zipTF = new JTextField();
        countryTF = new JTextField();
        ssnTF = new JTextField();
        insuranceTF = new JTextField();

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 150;
        int x2 = x1 + 400;

        firstNameTF.setBounds(x1, 80, width, height);
        middleNameTF.setBounds(x1, 160, width, height);
        lastNameTF.setBounds(x1, 240, width, height);
        dobTF.setBounds(x1, 320, width, height);
        genderTF.setBounds(x1, 400, width, height);
        phoneTF.setBounds(x1, 480, width, height);
        emailTF.setBounds(x1, 560, width, height);
        addressTF.setBounds(x2, 80, width, height);
        cityTF.setBounds(x2, 160, width, height);
        stateTF.setBounds(x2, 240, width, height);
        zipTF.setBounds(x2, 320, width, height);
        countryTF.setBounds(x2, 400, width, height);
        ssnTF.setBounds(x2, 480, width, height);
        insuranceTF.setBounds(x2, 560, width, height);

        this.add(firstNameTF);
        this.add(middleNameTF);
        this.add(lastNameTF);
        this.add(genderTF);
        this.add(dobTF);
        this.add(phoneTF);
        this.add(emailTF);
        this.add(addressTF);
        this.add(cityTF);
        this.add(stateTF);
        this.add(zipTF);
        this.add(countryTF);
        this.add(ssnTF);
        this.add(insuranceTF);
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
                System.out.println("CREATE PATIENT INFO TO DATABASE");
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
                Application.setCurrentScreen(Application.ADD_DEMOGRAPHICS_SCREEN);
                System.out.println("addDemographicsButton");
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