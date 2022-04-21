package UserInterface;

import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

// JAVA SWING Documentation https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html
// JAVA SWING Tutorials from Oracle https://docs.oracle.com/javase/tutorial/uiswing/components/index.html

/* Creates the main Graphical User Interface for the Hearing Clinic.
*  Includes methods to add any Javax.Swing components to the UserInterface.GUI easily
* */

public class GUI {

    private static final String appName = "Awesome Hearing Clinic";
    private JFrame frame = new JFrame(appName);
    public static final int DEFAULT_WIDTH = 1100; //frame.getGraphicsConfiguration().getBounds().width - 100;
    public static final int DEFAULT_HEIGHT = 700; //frame.getGraphicsConfiguration().getBounds().height - 100;
    private int width, height;

    // dark theme
    public static final Color bgColor = Color.BLACK;
    public static final Color BUTTON_HOVER_COLOR = new Color(35, 35, 35);


    // Instantiates application main window
    public GUI(){

        // creates new frame (window)

        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;

        frame.setLayout(null);
        frame.setSize(this.width, this.height);
        frame.setResizable(false);
        try {
            frame.setLocation(frame.getGraphicsConfiguration().getBounds().width / 2 - (width/2),
                    frame.getGraphicsConfiguration().getBounds().height / 2 - (height/2));
        } catch (Exception e){
            System.out.println("Error setting screen middle" + e.getMessage());
            frame.setLocation(0,0);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(bgColor);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                Application.stop();
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

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

    public void removeScreen(JPanel jPanel){
        frame.remove(jPanel);
    }

    public JFrame getFrame(){
        return frame;
    }

}
