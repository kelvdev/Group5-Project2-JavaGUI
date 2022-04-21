package UserInterface;

import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VisitScreen extends JPanel {

    private JButton addVisitButton, viewDeleteVisitsButton, backButton;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/5;
    private Font buttonFont;

    public VisitScreen(){
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
        addVisitButton = new JButton("Add Visit");
        viewDeleteVisitsButton = new JButton("View/Delete Visit");
        backButton = new JButton("⬅");

        int xpos = (GUI.DEFAULT_WIDTH/2)-(buttonWidth/2);

        addVisitButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/2)-(buttonHeight + 10), buttonWidth, buttonHeight);
        viewDeleteVisitsButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/2) + 10, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        addVisitButton.setFont(buttonFont);
        viewDeleteVisitsButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        addVisitButton.setForeground(Color.WHITE);
        viewDeleteVisitsButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        addVisitButton.setBackground(GUI.bgColor);
        viewDeleteVisitsButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(addVisitButton);
        this.add(viewDeleteVisitsButton);
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

        viewDeleteVisitsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.VIEW_DELETE_VISITS_SCREEN);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                viewDeleteVisitsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                viewDeleteVisitsButton.setBackground(GUI.bgColor);
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
