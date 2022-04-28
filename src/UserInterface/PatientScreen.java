package UserInterface;
import com.sun.tools.javac.Main;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PatientScreen extends JPanel {

    private JButton addPatientsButton, viewEditPatientsButton, backButton;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/5;
    private Font buttonFont;

    public PatientScreen(){
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

        // main panel
        this.setBounds(0,0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        this.setBackground(Color.BLACK);
    }

    // initializes all buttons for home screen
    private void initButtons(){
        addPatientsButton = new JButton("Add Patient");
        viewEditPatientsButton = new JButton("Edit/View Patient");
        backButton = new JButton("⬅");

        int xpos = (GUI.DEFAULT_WIDTH/2)-(buttonWidth/2);

        addPatientsButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/2)-(buttonHeight + 10), buttonWidth, buttonHeight);
        viewEditPatientsButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/2) + 10, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        addPatientsButton.setFont(buttonFont);
        viewEditPatientsButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        addPatientsButton.setForeground(Color.WHITE);
        viewEditPatientsButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        addPatientsButton.setBackground(GUI.bgColor);
        viewEditPatientsButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(addPatientsButton);
        this.add(viewEditPatientsButton);
        this.add(backButton);
    }

    // init fonts

    private void initFonts(){
        buttonFont = new Font("name", Font.BOLD, 28);
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){
        addPatientsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.ADD_PATIENTS_SCREEN);
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
                addPatientsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                addPatientsButton.setBackground(GUI.bgColor);
            }
        });

        viewEditPatientsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.VIEW_EDIT_PATIENTS_SCREEN);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                viewEditPatientsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                viewEditPatientsButton.setBackground(GUI.bgColor);
            }
        });

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.HOME_SCREEN);
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

    }

}
