import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomeScreen extends JPanel{

    private JButton patientsButton, visitsButton, analyticsButton, otherButton;
    private int buttonWidth = GUI.DEFAULT_WIDTH/2;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/2;
    JPanel topPanel, bottomPanel;

    public HomeScreen(){
        this.setBackground(GUI.bgColor);

        initLayout();
        initButtons();
        initActionListeners();
    }

    // initializes panel (this) layout

    private void initLayout(){

        // set main layout to null for positional based layout
        this.setLayout(null);

        //create top and bottom panel

        topPanel = new JPanel();
        bottomPanel = new JPanel();

        // set panel layouts

            // main panel
        this.setBounds(0,0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        this.setBackground(Color.GREEN);
    }

    // initializes all buttons for home screen
    private void initButtons(){
        patientsButton = new JButton("Patients");
        visitsButton = new JButton("Visits");
        analyticsButton = new JButton("Analytics");
        otherButton = new JButton("Other");

        patientsButton.setBounds(0, 0, buttonWidth, buttonHeight);
        visitsButton.setBounds(GUI.DEFAULT_WIDTH/2,0, buttonWidth, buttonHeight);
        analyticsButton.setBounds(0, GUI.DEFAULT_HEIGHT/2, buttonWidth, buttonHeight);
        otherButton.setBounds(GUI.DEFAULT_WIDTH/2, GUI.DEFAULT_HEIGHT/2, buttonWidth, buttonHeight);

        patientsButton.setBackground(GUI.bgColor);
        visitsButton.setBackground(GUI.bgColor);
        analyticsButton.setBackground(GUI.bgColor);
        otherButton.setBackground(GUI.bgColor);

        this.add(patientsButton);
        this.add(visitsButton);
        this.add(analyticsButton);
        this.add(otherButton);
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){
        patientsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("Patients Button");
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
                System.out.println("Visits Button");
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
                System.out.println("Analytics Button");
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

        otherButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("Other Button");
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                otherButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                otherButton.setBackground(GUI.bgColor);
            }
        });
    }

}