package UserInterface;

import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddDemographicsScreen extends JPanel {

    private JButton addDemographicsButton, cancelButton, backButton;
    private JTextField occupationTF, workStatusTF, educationalTF,
            tinOnsetTF, tinEtiologyTF, hyperacOnsetTF, hyperacEtiologyTF, additionalCommentsTF;

    private JLabel occupationLabel, workStatusLabel, educationalLabel,
            tinOnsetLabel, tinEtiologyLabel, hyperacOnsetLabel, hyperacEtiologyLabel, additionalCommentsLabel;
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
        cancelButton = new JButton("Cancel");
        backButton = new JButton("⬅");

        int buttonX = GUI.DEFAULT_WIDTH - (buttonWidth + 20);

        addDemographicsButton.setBounds(buttonX, 80, buttonWidth, buttonHeight);
        cancelButton.setBounds(buttonX, 160, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        addDemographicsButton.setFont(componentDesign.buttonFont);
        cancelButton.setFont(componentDesign.buttonFont);
        addDemographicsButton.setFont(componentDesign.buttonFont);
        backButton.setFont(componentDesign.buttonFont);

        addDemographicsButton.setForeground(Color.GREEN);
        cancelButton.setForeground(Color.RED);
        addDemographicsButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        addDemographicsButton.setBackground(GUI.bgColor);
        cancelButton.setBackground(GUI.bgColor);
        addDemographicsButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(addDemographicsButton);
        this.add(cancelButton);
        this.add(addDemographicsButton);
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
        educationalLabel = new JLabel("Educational Degree");
        tinOnsetLabel = new JLabel("Tinnitus Onset");
        tinEtiologyLabel = new JLabel("Tinnitus Etiology");
        hyperacOnsetLabel = new JLabel("Hyperacusis Onset");
        hyperacEtiologyLabel = new JLabel("Hyperacusis Etiology");
        additionalCommentsLabel = new JLabel("Additional Comments");

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 40;
        int x2 = x1+400;

        occupationLabel.setBounds(x1, 80, width, height);
        workStatusLabel.setBounds(x1, 160, width, height);
        educationalLabel.setBounds(x1, 240, width, height);
        tinOnsetLabel.setBounds(x1, 320, width, height);
        tinEtiologyLabel.setBounds(x1, 400, width, height);
        hyperacOnsetLabel.setBounds(x1, 480, width, height);
        hyperacEtiologyLabel.setBounds(x1, 560, width, height);
        additionalCommentsLabel.setBounds(x2, 80, width, height);

        this.add(occupationLabel);
        this.add(workStatusLabel);
        this.add(educationalLabel);
        this.add(tinEtiologyLabel);
        this.add(tinOnsetLabel);
        this.add(hyperacOnsetLabel);
        this.add(hyperacEtiologyLabel);
        this.add(additionalCommentsLabel);

    }

    // init text fields

    private void initTextFields(){
        occupationTF = new JTextField();
        workStatusTF = new JTextField();
        educationalTF = new JTextField();
        tinOnsetTF = new JTextField();
        tinEtiologyTF = new JTextField();
        hyperacOnsetTF = new JTextField();
        hyperacEtiologyTF = new JTextField();
        additionalCommentsTF = new JTextField();

        int width = GUI.DEFAULT_WIDTH/6;
        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 200;
        int x2 = x1 + 400;

        occupationTF.setBounds(x1, 80, width, height);
        workStatusTF.setBounds(x1, 160, width, height);
        educationalTF.setBounds(x1, 240, width, height);
        tinOnsetTF.setBounds(x1, 320, width, height);
        tinEtiologyTF.setBounds(x1, 400, width, height);
        hyperacOnsetTF.setBounds(x1, 480, width, height);
        hyperacEtiologyTF.setBounds(x1, 560, width, height);
        additionalCommentsTF.setBounds(x2, 80, width, height);

        this.add(occupationTF);
        this.add(workStatusTF);
        this.add(educationalTF);
        this.add(tinOnsetTF);
        this.add(tinEtiologyTF);
        this.add(hyperacOnsetTF);
        this.add(hyperacEtiologyTF);
        this.add(additionalCommentsTF);
    }



    // initializes all action listeners for the buttons
    private void initActionListeners(){
        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.ADD_PATIENTS_SCREEN);
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
                System.out.println("addDemographicsButtonn");
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

        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.ADD_PATIENTS_SCREEN);
                System.out.println("cancelButtonn");
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