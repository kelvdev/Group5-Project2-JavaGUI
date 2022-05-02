package UserInterface;

import java.awt.*;

// TODO: SET DESIGN VARIABLES AND METHODS IN HERE FOR SCREEN COMPONENTS SUCH AS BUTTONS, PANELS, ETC
public class ComponentDesign {

    public Font buttonFont, labelFont, textFont, tableTextFont;

    // init fonts

    public ComponentDesign(){
        initFonts();
    }

    private void initFonts(){
        buttonFont = new Font("buttonfont", Font.BOLD, 20);
        labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
        tableTextFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    }

}
