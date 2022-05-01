package UserInterface;

import DataObjects.Patient;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class AnalyticsScreen extends JPanel {
    private JButton backButton;
    private JTextField visitCountTF, insuranceRegisteredTF,
            thiCollectedCountTF, hyperacBackgroundTF;
    private JTextArea tinCommentsTA, hCommentsTA;

    private JTable insuranceTable;
    private JScrollPane scrollPane;

    private JLabel visitCountLabel, insuranceRegisteredLabel,
            thiCollectedCountLabel, visitCountDataLabel, insuranceRegisteredDataLabel,
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
        insuranceRegisteredLabel = new JLabel("# of patients registered");
        thiCollectedCountLabel = new JLabel("# of THIs collected");

        visitCountDataLabel = new JLabel("0");
        insuranceRegisteredDataLabel = new JLabel("0");
        thiCollectedCountDataLabel = new JLabel("0");

        visitCountLabel.setForeground(Color.WHITE);
        insuranceRegisteredLabel.setForeground(Color.WHITE);
        thiCollectedCountLabel.setForeground(Color.WHITE);

        visitCountDataLabel.setForeground(Color.WHITE);
        insuranceRegisteredDataLabel.setForeground(Color.WHITE);
        thiCollectedCountDataLabel.setForeground(Color.WHITE);


        int height = GUI.DEFAULT_HEIGHT/20;

        int x1 = 0;

        visitCountLabel.setBounds(x1, 80, GUI.DEFAULT_WIDTH/2, height);
        visitCountDataLabel.setBounds(x1, 160, GUI.DEFAULT_WIDTH/2, height);
        insuranceRegisteredLabel.setBounds(x1, 240, GUI.DEFAULT_WIDTH/2, height);
        insuranceRegisteredDataLabel.setBounds(x1, 320, GUI.DEFAULT_WIDTH/2, height);
        thiCollectedCountLabel.setBounds(x1, 400, GUI.DEFAULT_WIDTH/2, height);
        thiCollectedCountDataLabel.setBounds(x1, 480, GUI.DEFAULT_WIDTH/2, height);

        visitCountLabel.setHorizontalAlignment(JLabel.CENTER);
        insuranceRegisteredLabel.setHorizontalAlignment(JLabel.CENTER);
        thiCollectedCountLabel.setHorizontalAlignment(JLabel.CENTER);

        visitCountLabel.setFont(componentDesign.textFont);
        insuranceRegisteredLabel.setFont(componentDesign.textFont);
        thiCollectedCountLabel.setFont(componentDesign.textFont);

        visitCountDataLabel.setHorizontalAlignment(JLabel.CENTER);
        insuranceRegisteredDataLabel.setHorizontalAlignment(JLabel.CENTER);
        thiCollectedCountDataLabel.setHorizontalAlignment(JLabel.CENTER);

        visitCountDataLabel.setFont(componentDesign.textFont);
        insuranceRegisteredDataLabel.setFont(componentDesign.textFont);
        thiCollectedCountDataLabel.setFont(componentDesign.textFont);

        this.add(visitCountLabel);
        this.add(insuranceRegisteredLabel);
        this.add(thiCollectedCountLabel);
        this.add(visitCountDataLabel);
        this.add(insuranceRegisteredDataLabel);
        this.add(thiCollectedCountDataLabel);

    }

    public void updateAnalytics(){
        LocalDateTime dateTime = LocalDateTime.now();
        String dateString = dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth();

        visitCountDataLabel.setText(String.valueOf(Application.dbReaderWriter.getAllPatientVisitsOnDate(dateString)));
        insuranceRegisteredDataLabel.setText(String.valueOf(Application.dbReaderWriter.getRegisteredPatientCount()));
        thiCollectedCountDataLabel.setText(String.valueOf(Application.dbReaderWriter.getAllTHICollected()));
    }

    public void updateTable(){
        try {
            this.remove(scrollPane);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        scrollPane = null;
        insuranceTable = null;
        initTable();
    }

    public void initTable(){

        JLabel insuranceLabel = new JLabel("Insurance Analytics");
        insuranceLabel.setBounds((GUI.DEFAULT_WIDTH/2)-20, 60, GUI.DEFAULT_WIDTH/2, 70);
        insuranceLabel.setFont(componentDesign.textFont);
        insuranceLabel.setForeground(Color.WHITE);
        insuranceLabel.setHorizontalAlignment(JLabel.CENTER);

        int yOffSet = 120;

        String[] columnNames = {"Insurance", "# of Registered Patients"};
        Object[][] rows = Application.dbReaderWriter.getInsuranceAnalytics();

        System.out.println("Row length" + rows.length);

        //table
        insuranceTable = new JTable(rows, columnNames);
        insuranceTable.setLocation(0,0);
        insuranceTable.setSize((GUI.DEFAULT_WIDTH/2)-50, GUI.DEFAULT_HEIGHT - yOffSet);
        insuranceTable.setForeground(Color.WHITE);
        insuranceTable.setBackground(GUI.bgColor);
        insuranceTable.setFont(componentDesign.textFont);
        insuranceTable.setRowHeight(70);

        //scroll pane
        scrollPane = new JScrollPane(insuranceTable);
        scrollPane.setBounds(GUI.DEFAULT_WIDTH/2, yOffSet, (GUI.DEFAULT_WIDTH/2) - 50, GUI.DEFAULT_HEIGHT - 200);
        scrollPane.createVerticalScrollBar();
        scrollPane.setBackground(GUI.bgColor);

        this.add(insuranceLabel);
        this.add(scrollPane);
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
