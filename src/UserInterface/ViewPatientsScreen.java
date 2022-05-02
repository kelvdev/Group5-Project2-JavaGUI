package UserInterface;

import Main.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewPatientsScreen extends JPanel {

    private JButton backButton;
    private JTable patientsTable;
    private JScrollPane scrollPane;
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private ComponentDesign componentDesign = new ComponentDesign();


    public ViewPatientsScreen(){
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
        JLabel title = new JLabel("View Patients");
        title.setBounds(0, 2, GUI.DEFAULT_WIDTH, 70);
        title.setForeground(Color.WHITE);
        title.setFont(componentDesign.labelFont);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);

    }

    public void updateTable(){
        try {
            this.remove(scrollPane);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        scrollPane = null;
        patientsTable = null;
        initTable();
    }

    public void initTable(){

        int yOffSet = 100;

        String[] columnNames = {"THC", "Full Name", "DOB", "Insurance", "# of Visits", "Latest THI Score"};
        Object[][] rows = Application.dbReaderWriter.getAllPatientsFullInformation();

        //table
        patientsTable = new JTable(rows, columnNames);
        patientsTable.setLocation(0,0);
        patientsTable.setSize(GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT - yOffSet);
        patientsTable.setForeground(Color.WHITE);
        patientsTable.setBackground(GUI.bgColor);
        patientsTable.setFont(componentDesign.tableTextFont);
        patientsTable.setRowHeight(70);

        //scroll pane
        scrollPane = new JScrollPane(patientsTable);
        scrollPane.setBounds(0, yOffSet, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT - 200);
        scrollPane.createVerticalScrollBar();
        scrollPane.setBackground(GUI.bgColor);

        this.add(scrollPane);
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

    }

}
