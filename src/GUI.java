import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* Creates the main Graphical User Interface for the Hearing Clinic.
*  Includes methods to add any Javax.Swing components to the GUI easily
* */

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private String appName;
    private int width, height;

    // dark theme
    private Color bgColor = new Color(18, 18, 18);

    // Instantiates application main window
    public GUI(String appName, int width, int height){

        this.appName = appName;

        this.width = width;
        this.height = height;

        // creates new frame (window)
        frame = new JFrame(this.appName);
        frame.setSize(width, height);
        frame.setLocation(180, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(bgColor);

        // creates main panel for frame
        mainPanel = new JPanel();
        mainPanel.setSize(width, height);
        mainPanel.setBackground(bgColor);

        frame.add(mainPanel);
    }

    // show window

    public void show() {
        frame.setVisible(true);
    }

    // easily add components onto screen through the panel

    public void addComponent(Component component){
        mainPanel.add(component);
        component.setVisible(true);
    }

    // update screen

    public void update(){

    }

    // get width

    public int getWidth(){
        return width;
    }

    // get height

    public int getHeight(){
        return height;
    }

    // get main panel from this GUI

    public JPanel getMainPanel(){
        return mainPanel;
    }

    // get main frame from this GUI

    public JFrame getFrame(){
        return frame;
    }

}
