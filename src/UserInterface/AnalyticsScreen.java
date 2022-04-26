package UserInterface;

import DataObjects.Patient;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

public class AnalyticsScreen extends JPanel {
    private JButton backButton;
    private JTextField visitCountTF, patientsRegisteredTF,
            thiCollectedCountTF, hyperacBackgroundTF;
    private JTextArea tinCommentsTA, hCommentsTA;

    private JLabel visitCountLabel, patientsRegisteredLabel,
            thiCollectedCountLabel, visitCountDataLabel, patientsRegisteredDataLabel,
            thiCollectedCountDataLabel;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/10;
    private ComponentDesign componentDesign = new ComponentDesign();


    public AnalyticsScreen(){
        this.setBackground(GUI.bgColor);

        initLayout();
        initLabels();
        initButtons();
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

        backButton = new JButton("<");

        int buttonX = GUI.DEFAULT_WIDTH - (buttonWidth + 20);

        backButton.setBounds(0, 0, 80, 80);

        backButton.setFont(componentDesign.buttonFont);

        backButton.setForeground(Color.YELLOW);

        backButton.setBackground(GUI.bgColor);

        this.add(backButton);
    }

    // init labels

    private void initLabels(){
        // set main label
        JLabel title = new JLabel("Analytics");
        title.setBounds(0, 2, GUI.DEFAULT_WIDTH, 70);
        title.setForeground(Color.WHITE);
        title.setFont(componentDesign.labelFont);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);

        // set textfield labels

        String dateString = "# of visits today";

        visitCountLabel = new JLabel(dateString);
        patientsRegisteredLabel = new JLabel("# of patients registered");
        thiCollectedCountLabel = new JLabel("# of THI collected");

        visitCountDataLabel = new JLabel("0");
        patientsRegisteredDataLabel = new JLabel("0");
        thiCollectedCountDataLabel = new JLabel("0");

        visitCountLabel.setForeground(Color.WHITE);
        patientsRegisteredLabel.setForeground(Color.WHITE);
        thiCollectedCountLabel.setForeground(Color.WHITE);

        visitCountDataLabel.setForeground(Color.WHITE);
        patientsRegisteredDataLabel.setForeground(Color.WHITE);
        thiCollectedCountDataLabel.setForeground(Color.WHITE);


        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 0;

        visitCountLabel.setBounds(x1, 80, GUI.DEFAULT_WIDTH, height);
        visitCountDataLabel.setBounds(x1, 160, GUI.DEFAULT_WIDTH, height);
        patientsRegisteredLabel.setBounds(x1, 240, GUI.DEFAULT_WIDTH, height);
        patientsRegisteredDataLabel.setBounds(x1, 320, GUI.DEFAULT_WIDTH, height);
        thiCollectedCountLabel.setBounds(x1, 400, GUI.DEFAULT_WIDTH, height);
        thiCollectedCountDataLabel.setBounds(x1, 480, GUI.DEFAULT_WIDTH, height);

        visitCountLabel.setHorizontalAlignment(JLabel.CENTER);
        patientsRegisteredLabel.setHorizontalAlignment(JLabel.CENTER);
        thiCollectedCountLabel.setHorizontalAlignment(JLabel.CENTER);

        visitCountDataLabel.setHorizontalAlignment(JLabel.CENTER);
        patientsRegisteredDataLabel.setHorizontalAlignment(JLabel.CENTER);
        thiCollectedCountDataLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(visitCountLabel);
        this.add(patientsRegisteredLabel);
        this.add(thiCollectedCountLabel);
        this.add(visitCountDataLabel);
        this.add(patientsRegisteredDataLabel);
        this.add(thiCollectedCountDataLabel);

    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){
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
