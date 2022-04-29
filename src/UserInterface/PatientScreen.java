package UserInterface;
import com.sun.tools.javac.Main;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PatientScreen extends JPanel {

    private JButton addPatientsButton, viewEditPatientsButton, deletePatientButton, backButton;
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
        viewEditPatientsButton = new JButton("View Patients");
        deletePatientButton = new JButton("Delete Patient");
        backButton = new JButton("<");

        int xpos = (GUI.DEFAULT_WIDTH/2)-(buttonWidth/2);

        addPatientsButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/3)-(buttonHeight + 10), buttonWidth, buttonHeight);
        viewEditPatientsButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/3) + 10, buttonWidth, buttonHeight);
        deletePatientButton.setBounds(xpos, (GUI.DEFAULT_HEIGHT/3) + buttonHeight + 30, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        addPatientsButton.setFont(buttonFont);
        viewEditPatientsButton.setFont(buttonFont);
        deletePatientButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        addPatientsButton.setForeground(Color.WHITE);
        viewEditPatientsButton.setForeground(Color.WHITE);
        deletePatientButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        addPatientsButton.setBackground(GUI.bgColor);
        viewEditPatientsButton.setBackground(GUI.bgColor);
        deletePatientButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(addPatientsButton);
        this.add(viewEditPatientsButton);
        this.add(deletePatientButton);
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
                Application.setCurrentScreen(Application.VIEW_PATIENTS_SCREEN);
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

        deletePatientButton.addMouseListener(new MouseListener() {
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
                deletePatientButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                deletePatientButton.setBackground(GUI.bgColor);
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

    private void openDeletePopup(){
        JFrame jFrame = new JFrame("Enter Patient THC to Delete Patient");
        jFrame.setLocation(Application.getApplicationGUI().getFrame().getX(),
                Application.getApplicationGUI().getFrame().getY());
        jFrame.setSize(500,300);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setLayout(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(GUI.bgColor);
        jPanel.setSize(500, 300);
        jPanel.setLocation(0,0);
        jPanel.setLayout(null);

        JTextField thcTextField = new JTextField();
        thcTextField.setSize(500, 50);
        thcTextField.setLocation(0,50);
        thcTextField.setHorizontalAlignment(JTextField.CENTER);
        thcTextField.setBackground(GUI.bgColor);
        thcTextField.setForeground(Color.WHITE);

        JButton jButton = new JButton("Delete Patient");
        jButton.setSize(500, 100);
        jButton.setLocation(0,100);
        jButton.setHorizontalAlignment(JButton.CENTER);
        jButton.setBackground(GUI.bgColor);
        jButton.setForeground(Color.RED);

        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (Application.dbReaderWriter.deletePatient(Integer.parseInt(thcTextField.getText()))){
                    Application.displayMessage("Delete Confirmation",
                            "Patient " + thcTextField.getText() + " successfully deleted");
                } else {
                    Application.displayMessage("Delete Failed",
                            "Patient " + thcTextField.getText() + " failed to delete");
                }

                jFrame.setVisible(false);
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

        jPanel.add(thcTextField);
        jPanel.add(jButton);

        jFrame.add(jPanel);
    }

    private void openEditPopup(){
        JFrame jFrame = new JFrame("Enter Patient THC to Edit Patient");
        jFrame.setLocation(Application.getApplicationGUI().getFrame().getX()/2,
                Application.getApplicationGUI().getFrame().getY()/2);
        jFrame.setSize(500,300);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setLayout(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(GUI.bgColor);
        jPanel.setSize(500, 300);
        jPanel.setLocation(0,0);
        jPanel.setLayout(null);

        JTextField thcTextField = new JTextField();
        thcTextField.setSize(500, 50);
        thcTextField.setLocation(0,0);
        thcTextField.setHorizontalAlignment(JTextField.CENTER);
        thcTextField.setBackground(GUI.bgColor);
        thcTextField.setForeground(Color.WHITE);

        JButton jButton = new JButton("Edit Patient");
        jButton.setSize(500, 100);
        jButton.setLocation(0,200);
        jButton.setHorizontalAlignment(JButton.CENTER);
        jButton.setBackground(GUI.bgColor);
        jButton.setForeground(Color.RED);

        jPanel.add(thcTextField);
        jPanel.add(jButton);

        jFrame.add(jPanel);
    }

}
