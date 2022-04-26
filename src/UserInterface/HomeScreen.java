package UserInterface;

import Database.DBReaderWriter;
import Main.Application;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static UserInterface.GUI.DEFAULT_HEIGHT;
import static UserInterface.GUI.DEFAULT_WIDTH;

public class HomeScreen extends JPanel{

    private JButton patientsButton, visitsButton, analyticsButton, clearPatientButton, endVisitButton;
    public static final int SPARE_HEIGHT = 100;
    private int buttonWidth = DEFAULT_WIDTH/2;
    private int buttonHeight = (DEFAULT_HEIGHT/2) - SPARE_HEIGHT;
    private ComponentDesign componentDesign = new ComponentDesign();
    private Font mainFont;

    public HomeScreen(){
        this.setBackground(GUI.bgColor);

        initLayout();
        initFonts();
        initButtons();
        initActionListeners();
    }

    // initializes panel (this) layout

    private void initLayout(){

        // set main layout to null for positional based layout
        this.setLayout(null);

        // set panel layouts

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 550, DEFAULT_WIDTH, SPARE_HEIGHT);
        controlPanel.setBackground(GUI.bgColor);
        controlPanel.setLayout(null);

        clearPatientButton = new JButton("Clear Current Patient");
        clearPatientButton.setBounds(0, 0, DEFAULT_WIDTH/2, SPARE_HEIGHT);
        clearPatientButton.setFont(componentDesign.textFont);
        clearPatientButton.setBackground(GUI.bgColor);
        clearPatientButton.setForeground(Color.WHITE);

        endVisitButton = new JButton("End Current Visit");
        endVisitButton.setBounds(DEFAULT_WIDTH/2, 0, DEFAULT_WIDTH/2, SPARE_HEIGHT);
        endVisitButton.setFont(componentDesign.textFont);
        endVisitButton.setBackground(GUI.bgColor);
        endVisitButton.setForeground(Color.WHITE);

        controlPanel.add(clearPatientButton);
        controlPanel.add(endVisitButton);

        System.out.println(controlPanel.getY());

            // main panel
        this.setBounds(0,0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setBackground(Color.BLACK);

        this.add(controlPanel);
    }

    // initializes all buttons for home screen
    private void initButtons(){
        patientsButton = new JButton("Patients");
        visitsButton = new JButton("Visits");
        analyticsButton = new JButton("Analytics");

        patientsButton.setBounds(0, 0, buttonWidth, buttonHeight);
        visitsButton.setBounds(DEFAULT_WIDTH/2,0, buttonWidth, buttonHeight);
        analyticsButton.setBounds(0, (DEFAULT_HEIGHT/2)-SPARE_HEIGHT, DEFAULT_WIDTH, buttonHeight);

        patientsButton.setFont(mainFont);
        visitsButton.setFont(mainFont);
        analyticsButton.setFont(mainFont);

        patientsButton.setForeground(Color.WHITE);
        visitsButton.setForeground(Color.WHITE);
        analyticsButton.setForeground(Color.WHITE);

        patientsButton.setBackground(GUI.bgColor);
        visitsButton.setBackground(GUI.bgColor);
        analyticsButton.setBackground(GUI.bgColor);

        this.add(patientsButton);
        this.add(visitsButton);
        this.add(analyticsButton);
    }

    // init fonts

    private void initFonts(){
        mainFont = new Font("name", Font.BOLD, 28);
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){

        patientsButton.addMouseListener(new MouseListener() {
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
                patientsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                patientsButton.setBackground(GUI.bgColor);
            }
        });

        visitsButton.addMouseListener(new MouseListener() {
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
                visitsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                visitsButton.setBackground(GUI.bgColor);
            }
        });

        analyticsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.ANALYTICS_SCREEN);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                analyticsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                analyticsButton.setBackground(GUI.bgColor);
            }
        });

        clearPatientButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentPatientTHC(Application.PATIENT_THC_EMPTY);
                Application.updateTitle();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });

        endVisitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentVisitID(Application.VISIT_ID_EMPTY);
                Application.updateTitle();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

    }

}
