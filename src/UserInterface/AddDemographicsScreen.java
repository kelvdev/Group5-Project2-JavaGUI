package UserInterface;

import DataObjects.Patient;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddDemographicsScreen extends JPanel {

    private JButton addDemographicsButton, addVisitButton, cancelButton, backButton;
    private JTextField occupationTF, workStatusTF,
            tinOnsetTF, tinEtiologyTF, hyperacOnsetTF, hyperacEtiologyTF;
    private JTextArea tinCommentsTA, hCommentsTA;

    private JLabel occupationLabel, workStatusLabel,
            tinOnsetLabel, tinEtiologyLabel, hyperacOnsetLabel, hyperacEtiologyLabel, tinCommentsLabel, hCommentsLabel;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/10;
    private ComponentDesign componentDesign = new ComponentDesign();


    public AddDemographicsScreen(){
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
        addDemographicsButton = new JButton("Save");
        addVisitButton = new JButton("Add Visit");
        cancelButton = new JButton("Cancel");
        backButton = new JButton("⬅");

        int buttonX = GUI.DEFAULT_WIDTH - (buttonWidth + 20);

        addDemographicsButton.setBounds(buttonX, 80, buttonWidth, buttonHeight);
        addVisitButton.setBounds(buttonX, 160, buttonWidth, buttonHeight);
        cancelButton.setBounds(buttonX, 240, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        addDemographicsButton.setFont(componentDesign.buttonFont);
        cancelButton.setFont(componentDesign.buttonFont);
        addVisitButton.setFont(componentDesign.buttonFont);
        backButton.setFont(componentDesign.buttonFont);

        addDemographicsButton.setForeground(Color.GREEN);
        cancelButton.setForeground(Color.RED);
        addVisitButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        addDemographicsButton.setBackground(GUI.bgColor);
        cancelButton.setBackground(GUI.bgColor);
        addVisitButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(addDemographicsButton);
        this.add(cancelButton);
        this.add(addVisitButton);
        this.add(backButton);
    }

    // init labels

    private void initLabels(){
        // set main label
        JLabel title = new JLabel("Add Demographics");
        title.setBounds((GUI.DEFAULT_WIDTH/2)-100, 2, 400, 70);
        title.setForeground(Color.WHITE);
        title.setFont(componentDesign.labelFont);
        this.add(title);

        // set textfield labels

        occupationLabel = new JLabel("Occupation");
        workStatusLabel = new JLabel("Work Status");
        tinOnsetLabel = new JLabel("Tinnitus Onset");
        tinEtiologyLabel = new JLabel("Tinnitus Etiology");
        hyperacOnsetLabel = new JLabel("Hyperacusis Onset");
        hyperacEtiologyLabel = new JLabel("Hyperacusis Etiology");

        tinCommentsLabel = new JLabel("Tinnitus Additional Comments");
        hCommentsLabel = new JLabel("Hyperacusis Additional Comments");

        occupationLabel.setForeground(Color.WHITE);
        workStatusLabel.setForeground(Color.WHITE);
        tinOnsetLabel.setForeground(Color.WHITE);
        tinEtiologyLabel.setForeground(Color.WHITE);
        hyperacOnsetLabel.setForeground(Color.WHITE);
        hyperacEtiologyLabel.setForeground(Color.WHITE);

        tinCommentsLabel.setForeground(Color.WHITE);
        hCommentsLabel.setForeground(Color.WHITE);

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 40;
        int x2 = x1+400;

        occupationLabel.setBounds(x1, 80, width, height);
        workStatusLabel.setBounds(x1, 160, width, height);
        tinOnsetLabel.setBounds(x1, 240, width, height);
        tinEtiologyLabel.setBounds(x1, 320, width, height);
        hyperacOnsetLabel.setBounds(x1, 400, width, height);
        hyperacEtiologyLabel.setBounds(x1, 480, width, height);

        tinCommentsLabel.setBounds(x2, 80, width, height);
        hCommentsLabel.setBounds(x2, 240, width, height);

        this.add(occupationLabel);
        this.add(workStatusLabel);
        this.add(tinEtiologyLabel);
        this.add(tinOnsetLabel);
        this.add(hyperacOnsetLabel);
        this.add(hyperacEtiologyLabel);
        this.add(tinCommentsLabel);
        this.add(hCommentsLabel);

    }

    // init text fields

    private void initTextFields(){
        occupationTF = new JTextField();
        workStatusTF = new JTextField();
        tinOnsetTF = new JTextField();
        tinEtiologyTF = new JTextField();
        hyperacOnsetTF = new JTextField();
        hyperacEtiologyTF = new JTextField();

        tinCommentsTA = new JTextArea();
        hCommentsTA = new JTextArea();

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 200;
        int x2 = x1 + 400;

        occupationTF.setBounds(x1, 80, width, height);
        workStatusTF.setBounds(x1, 160, width, height);
        tinOnsetTF.setBounds(x1, 240, width, height);
        tinEtiologyTF.setBounds(x1, 320, width, height);
        hyperacOnsetTF.setBounds(x1, 400, width, height);
        hyperacEtiologyTF.setBounds(x1, 480, width, height);

        tinCommentsTA.setBounds(x2 - 150, 120, width + 50, height + 50);
        hCommentsTA.setBounds(x2 - 150, 280, width + 50, height + 50);

        tinCommentsTA.setLineWrap(true);
        hCommentsTA.setLineWrap(true);

        this.add(occupationTF);
        this.add(workStatusTF);
        this.add(tinOnsetTF);
        this.add(tinEtiologyTF);
        this.add(hyperacOnsetTF);
        this.add(hyperacEtiologyTF);
        this.add(tinCommentsTA);
        this.add(hCommentsTA);
    }


    private void submitInformation(){
        Patient patient = new Patient(
                -1, -1, -1,
                -1, -1, "",
                "", "", "", "",
                "", tinEtiologyTF.getText(),
                hyperacEtiologyTF.getText(), tinCommentsTA.getText(),
                hCommentsTA.getText()
        );

        if(Application.dbReaderWriter.addDemographicsInformation(patient)){
            System.out.println("Patient " + patient.getTHC() + " demographics added");
            clearScreen();
        } else {
            System.out.println("Patient " + patient.getTHC() + " demographics added FAILED");
        }
    }

    private void clearScreen(){
        for(Component component : getComponents()){
            try{
                JTextField textField = (JTextField) component;
                textField.setText("");
            } catch (Exception e){

            }

            try{
                JTextArea textArea = (JTextArea) component;
                textArea.setText("");
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

        addDemographicsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                submitInformation();
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
                addDemographicsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                addDemographicsButton.setBackground(GUI.bgColor);
            }
        });

        addVisitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                submitInformation();
                Application.setCurrentScreen(Application.ADD_VISIT_SCREEN);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                addVisitButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                addVisitButton.setBackground(GUI.bgColor);
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
    }

}