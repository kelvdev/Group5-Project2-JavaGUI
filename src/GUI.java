import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// JAVA SWING Documentation https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html
// JAVA SWING Tutorials from Oracle https://docs.oracle.com/javase/tutorial/uiswing/components/index.html

/* Creates the main Graphical User Interface for the Hearing Clinic.
*  Includes methods to add any Javax.Swing components to the GUI easily
* */

public class GUI {

    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 500;
    private JFrame frame;
    private String appName;
    private int width, height;

    // dark theme
    public static final Color bgColor = new Color(18, 18, 18);
    public static final Color BUTTON_HOVER_COLOR = new Color(35, 35, 35);

    // Instantiates application main window
    public GUI(String appName, int width, int height){

        this.appName = appName;

        this.width = width;
        this.height = height;

        // creates new frame (window)
        frame = new JFrame(this.appName);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocation(180, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(bgColor);
        frame.setLayout(null);
    }

    // show window

    public void show() {
        frame.setVisible(true);
    }

    // easily add components onto screen through the panel

    public void addScreen(JPanel jPanel){
        frame.add(jPanel);
        jPanel.setVisible(true);
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


    // specify a layout for the main panel in the GUI to use: examples BoxLayout, GridLayout

    public void setMainLayout(LayoutManager layoutManager){
        frame.setLayout(layoutManager);
    }

    // get main frame from this GUI

    public JFrame getFrame(){
        return frame;
    }

}
