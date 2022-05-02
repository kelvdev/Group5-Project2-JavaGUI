package UserInterface;

import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VisitScreen extends JPanel {

    private JButton addVisitButton, viewVisitsButton, deleteVisitButton, backButton;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/5;
    private Font buttonFont;
    private JFrame deleteFrame;

    public VisitScreen(){
        this.setBackground(GUI.bgColor);

        initLayout();
        initFonts();
        initButtons();
        initPopups();
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
        addVisitButton = new JButton("Add Visit");
        viewVisitsButton = new JButton("View Visits");
        deleteVisitButton = new JButton("Delete Visit");
        backButton = new JButton("<");

        int xpos = (GUI.DEFAULT_WIDTH/2)-(buttonWidth/2);

        addVisitButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/3)-(buttonHeight + 10), buttonWidth, buttonHeight);
        viewVisitsButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/3) + 10, buttonWidth, buttonHeight);
        deleteVisitButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/3) + buttonHeight + 30, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        addVisitButton.setFont(buttonFont);
        viewVisitsButton.setFont(buttonFont);
        deleteVisitButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        addVisitButton.setForeground(Color.WHITE);
        viewVisitsButton.setForeground(Color.WHITE);
        deleteVisitButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        addVisitButton.setBackground(GUI.bgColor);
        viewVisitsButton.setBackground(GUI.bgColor);
        deleteVisitButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(addVisitButton);
        this.add(viewVisitsButton);
        this.add(deleteVisitButton);
        this.add(backButton);
    }

    // init fonts

    private void initFonts(){
        buttonFont = new Font("name", Font.BOLD, 28);
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){
        addVisitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (Application.getCurrentPatientTHC() == Application.PATIENT_THC_EMPTY) {
                    Application.setCurrentScreen(Application.SELECT_CURRENT_PATIENT_SCREEN);
                } else {
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
                addVisitButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                addVisitButton.setBackground(GUI.bgColor);
            }
        });

        viewVisitsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.VIEW_VISITS_SCREEN);
                Application.updateTables();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                viewVisitsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                viewVisitsButton.setBackground(GUI.bgColor);
            }
        });

        deleteVisitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                openDeletePopup();
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

    private void initPopups(){

        // delete popup
        deleteFrame = new JFrame("Enter Visit ID to Delete Visit");
        deleteFrame.setLocation(300, 300);
        deleteFrame.setSize(500,300);
        deleteFrame.setResizable(false);
        deleteFrame.setVisible(true);
        deleteFrame.setLayout(null);

        JPanel deletePanel = new JPanel();
        deletePanel.setBackground(GUI.bgColor);
        deletePanel.setSize(500, 300);
        deletePanel.setLocation(0,0);
        deletePanel.setLayout(null);

        JTextField visitIDDTextField = new JTextField();
        visitIDDTextField.setSize(500, 50);
        visitIDDTextField.setLocation(0,50);
        visitIDDTextField.setHorizontalAlignment(JTextField.CENTER);
        visitIDDTextField.setBackground(GUI.bgColor);
        visitIDDTextField.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("Delete Visit");
        deleteButton.setSize(500, 100);
        deleteButton.setLocation(0,100);
        deleteButton.setHorizontalAlignment(JButton.CENTER);
        deleteButton.setBackground(GUI.bgColor);
        deleteButton.setForeground(Color.RED);

        deleteButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (Application.dbReaderWriter.deleteVisit(Integer.parseInt(visitIDDTextField.getText()))){
                    Application.displayMessage("Delete Confirmation",
                            "Visit successfully deleted");
                } else {
                    Application.displayMessage("Delete Failed",
                            "Visit failed to delete");
                }

                deleteFrame.setVisible(false);
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

        deletePanel.add(visitIDDTextField);
        deletePanel.add(deleteButton);

        deleteFrame.add(deletePanel);

        deleteFrame.setVisible(false);
    }

    private void openDeletePopup(){
        deleteFrame.setVisible(true);
    }
}
