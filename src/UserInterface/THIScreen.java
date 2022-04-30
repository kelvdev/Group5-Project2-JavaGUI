package UserInterface;

import DataObjects.THI;
import Database.DBReaderWriter;
import Main.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class THIScreen extends JPanel {

    JLabel title, questionLabel;
    JButton yesButton, sometimesButton, noButton, backButton;
    ComponentDesign componentDesign = new ComponentDesign();
    private int buttonWidth = GUI.DEFAULT_WIDTH/4;
    private int buttonHeight = GUI.DEFAULT_HEIGHT/10;
    private int patientTHC;
    private int visitId;

    private int currentQuestion = 0;

    private int[] answers = new int[25];
    private String[] questions = {
            "Difficult to concentrate?",
            "Difficult to hear people?",
            "Tinnitus make you angry?",
            "Tinnitus make you confused?",
            "Tinnitus make you feel desperate?",
            "Do you complain a great deal about your tinnitus?",
            "Trouble falling asleep at night?",
            "Do you feel like you cannot escape your tinnitus?",
            "Does tinnitus interfere with your ability to enjoy social activities?",
            "Tinnitus make you feel frustrated?",
            "Tinnitus make you feel like you have a terrible disease?",
            "Tinnitus make it difficult for you to enjoy life?",
            "Tinnitus interfere with your job or household responsibilities?",
            "Tinnitus make you often irritable?",
            "Tinnitus make it difficult for you to read?",
            "Tinnitus make you upset?",
            "Tinnitus has caused stress on your relationships with family and\n" +
                    "friends?",
            "Difficult to focus attention away from tinnitus and on to other things?",
            "Do you feel you have no control over your tinnitus?",
            "Tinnitus make you often feel tired?",
            "Tinnitus make you feel depressed?",
            "Tinnitus make you feel anxious?",
            "Do you feel that you can no longer cope with your tinnitus?",
            "Does your tinnitus get worse when you are under stress?",
            "Does your tinnitus make you feel insecure?"
    };

    public THIScreen(){
        initLayout();
        initLabels();
        initButtons();
        initActionListeners();
    }

    private void initLayout() {

        // set main layout to null for positional based layout
        this.setLayout(null);

        // main panel
        this.setBounds(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
        this.setBackground(GUI.bgColor);
    }

    private void initLabels(){
        try {
            title = new JLabel("THI QUESTIONAIRE FOR " + Application.dbReaderWriter.getPatient(patientTHC).getFirstName());
        } catch (Exception e){
            title = new JLabel("THI QUESTIONAIRE");
        }
        title.setBounds(0, 2, GUI.DEFAULT_WIDTH, 70);
        title.setForeground(Color.WHITE);
        title.setFont(componentDesign.labelFont);
        title.setHorizontalAlignment(JLabel.CENTER);

        this.add(title);

        questionLabel = new JLabel("Question #" + (currentQuestion+1) + " " + questions[0]);
        questionLabel.setBounds(0, 200, GUI.DEFAULT_WIDTH, 70);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(componentDesign.textFont);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(title);
        this.add(questionLabel);
    }

    // initializes all buttons for home screen
    private void initButtons(){
        yesButton = new JButton("Yes");
        sometimesButton = new JButton("Sometimes");
        noButton = new JButton("No");
        backButton = new JButton("<");

        int startButtonX = 40;
        int middleButtonX = (GUI.DEFAULT_WIDTH/2)-(buttonWidth/2);
        int endButtonX = (GUI.DEFAULT_WIDTH) - (buttonWidth) - 40;

        yesButton.setBounds(startButtonX, 400, buttonWidth, buttonHeight);
        sometimesButton.setBounds(middleButtonX, 400, buttonWidth, buttonHeight);
        noButton.setBounds(endButtonX, 400, buttonWidth, buttonHeight);
        backButton.setBounds(0, 0, 80, 80);

        yesButton.setFont(componentDesign.buttonFont);
        sometimesButton.setFont(componentDesign.buttonFont);
        noButton.setFont(componentDesign.buttonFont);
        backButton.setFont(componentDesign.buttonFont);

        yesButton.setForeground(Color.GREEN);
        sometimesButton.setForeground(Color.RED);
        noButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.YELLOW);

        yesButton.setBackground(GUI.bgColor);
        sometimesButton.setBackground(GUI.bgColor);
        noButton.setBackground(GUI.bgColor);
        backButton.setBackground(GUI.bgColor);

        this.add(yesButton);
        this.add(sometimesButton);
        this.add(noButton);
        this.add(backButton);
    }

    private void setAnswer(int value){
        answers[currentQuestion] = value;
        if(!showNextQuestion()){
            submitTHI();
            reset();

            System.out.println("Submitting THI");
        }
    }

    // submit the THI to database
    private void submitTHI() {
        int Sc_F = answers[0] + answers[1] + answers[3] +
                answers[6] + answers[8] + answers[11] +
                answers[12] + answers[14] + answers[17] +
                answers[19] + answers[23];

        int Sc_E = answers[2] + answers[5] + answers[9] +
                answers[13] + answers[15] + answers[16] +
                answers[20] + answers[21] + answers[24];

        int Sc_C = answers[4] + answers[7] + answers[10] +
                answers[18] + answers[22];

        int Sc_T = Sc_F + Sc_E + Sc_C;

        THI thi = new THI(
                Application.getCurrentVisitID(),
                Sc_T, Sc_F, Sc_E, Sc_C,
                answers[0], answers[1], answers[2], answers[3], answers[4],
                answers[5], answers[6], answers[7], answers[8], answers[9],
                answers[10], answers[11], answers[12], answers[13], answers[14],
                answers[15], answers[16], answers[17], answers[18], answers[19],
                answers[20], answers[21], answers[22], answers[23], answers[24]);

        Application.dbReaderWriter.createPatientTHI(thi);
        Application.displayMessage("THI Result", "THI determination: "
                + Application.dbReaderWriter.getResultTHI(Application.getCurrentVisitID()));
        Application.setCurrentScreen(Application.HOME_SCREEN);
        Application.setCurrentPatientTHC(Application.PATIENT_THC_EMPTY);
        Application.setCurrentVisitID(Application.VISIT_ID_EMPTY);
        Application.updateTitle();
    }

    private boolean showNextQuestion(){
        System.out.println(currentQuestion);
        System.out.println(questions.length);
        if(currentQuestion < questions.length-1) {
            currentQuestion++;
            questionLabel.setText("Question #" + (currentQuestion+1) + " " + questions[currentQuestion]);
            return true;
        }

        return false;
    }

    // initializes all action listeners for the buttons
    private void initActionListeners(){

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Application.setCurrentScreen(Application.ADD_VISIT_SCREEN);
                reset();
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

        yesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setAnswer(4);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                yesButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                yesButton.setBackground(GUI.bgColor);
            }
        });

        sometimesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setAnswer(2);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                sometimesButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                sometimesButton.setBackground(GUI.bgColor);
            }
        });

        noButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setAnswer(0);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                noButton.setBackground(GUI.BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                noButton.setBackground(GUI.bgColor);
            }
        });
    }

    public void reset(){
        currentQuestion = 0;
        visitId = -1;
        patientTHC = -1;
        questionLabel.setText(questions[currentQuestion]);
    }

}
