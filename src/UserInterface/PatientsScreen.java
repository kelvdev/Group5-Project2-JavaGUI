package UserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PatientsScreen extends JPanel {

    private JButton addPatientsButton, viewEditPatientsButton;
    private int buttonWidth = GUI.DEFAULT_WIDTH/2;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/2;
    private Font buttonFont;

    public PatientsScreen(){
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

        addPatientsButton.setBounds(GUI.DEFAULT_WIDTH/2, GUI.DEFAULT_HEIGHT/2, buttonWidth/3, buttonHeight/3);
        viewEditPatientsButton.setBounds(GUI.DEFAULT_WIDTH/2, GUI.DEFAULT_HEIGHT/2, buttonWidth/3, buttonHeight/3);

        addPatientsButton.setFont(buttonFont);
        viewEditPatientsButton.setFont(buttonFont);

        addPatientsButton.setForeground(Color.WHITE);
        viewEditPatientsButton.setForeground(Color.WHITE);

        addPatientsButton.setBackground(GUI.bgColor);
        viewEditPatientsButton.setBackground(GUI.bgColor);

        this.add(addPatientsButton);
        this.add(viewEditPatientsButton);
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
                viewEditPatientsButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                viewEditPatientsButton.setBackground(GUI.bgColor);
            }
        });

    }

}
