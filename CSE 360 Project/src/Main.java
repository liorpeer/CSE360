/**************************************
 * Luke Mains
 * 27 February 2018
 * 360 Project
 *
 * This is just a rough draft of the gui part. Nothing works and it doesn't
 * look super pretty but it's something.
 *
 * To Do:
 *    - add JUnit tests
 *    - make everything look prettier
 *    - add functionality
 *         ^ browse/select file buttons
 *         ^ add red border to justify Buttons
 *         ^ format Stuff
 *              ~ count words
 *              ~ count number of Lines
 *              ~ count number of blank lines Removed
 *              ~ calculate average words per line
 *              ~ calculate average line Length (characters per line?)
 *         ^ download output file
 ***************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;
import java.io.IOException;

public class Main {
    //Global Variables
    static int wordsProcessed;
    static int lines;
    static int blanksRemoved;
    static double averageWPL;
    static double averageLL;
    static TextFileAnalyzer textFileAnalyzer;

    //File Chooser
    static JFileChooser fileChooser = new JFileChooser();

    //Layouts
    static FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 50, 20);

    //Text Field
    static JTextField inputFile = new JTextField("C:\\Users\\luke_\\Desktop\\try.txt", 20);
    static JTextField outputFile = new JTextField("C:\\Users\\luke_\\Desktop\\out1.txt", 20);

    //Labels
    static JLabel formatData = new JLabel();
    static JLabel inputLabel = new JLabel("   Select Input File:");
    static JLabel outputLabel = new JLabel("Select Output File:");
    static JLabel justifyLabel = new JLabel("Justification");

    //Fonts
    static Font font = new Font("name", Font.PLAIN, 34);

    //Buttons
    static JButton inputBrowseButton = new JButton("Browse");
    static JButton outputBrowseButton = new JButton("Browse");
    static JButton formatButton = new JButton("Format my File");
    static JButton resetButton = new JButton("Reset File");

    //Radio Buttons
    static JRadioButton leftJustifyButton = new JRadioButton("Left Justified (Default)");
    static JRadioButton rightJustifyButton = new JRadioButton("Right Justified");

    //Panels
    static JPanel mainPanel = new JPanel();

    //Button Handler Class
    private static class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonPressed = e.getActionCommand();
            String inputPath = inputFile.getText();
            String outputPath = outputFile.getText();
            switch (buttonPressed) {
                case "Format my File": {
                    try {
                        if(leftJustifyButton.isSelected()) {
                            textFileAnalyzer = new TextFileAnalyzer(inputPath, outputPath, true);
                        }
                        else{
                            textFileAnalyzer = new TextFileAnalyzer(inputPath, outputPath, false);
                        }

                        wordsProcessed = textFileAnalyzer.getWordNumber();
                        lines = textFileAnalyzer.getLineNumber();
                        blanksRemoved = textFileAnalyzer.getBlankLine();
                        averageWPL = textFileAnalyzer.getAvgWordPerLine();
                        averageLL = textFileAnalyzer.getAvgCharLine();
                    } catch (IOException e1) {
                        //Error: input file not found
                        JOptionPane.showMessageDialog(new JFrame(), "Error: Your input file doesn't exist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                }
                case "Reset File": {
                    wordsProcessed = 0;
                    lines = 0;
                    blanksRemoved = 0;
                    averageWPL = 0;
                    averageLL = 0;
                    break;
                }
                case "Browse Input": {
                    int returnVal = fileChooser.showOpenDialog(new JFrame());

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        inputFile.setText(file.getAbsolutePath());
                    }

                    break;
                }
                case "Browse Output": {
                    int returnVal = fileChooser.showOpenDialog(new JFrame());

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        outputFile.setText(file.getAbsolutePath());
                    }

                    break;
                }
            }

            formatData.setText(String.format("<html><table> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%.2f</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%.2f</td></tr> </table></html>", "Words Processed:", wordsProcessed, "Lines:", lines, "Blank Lines Removed:", blanksRemoved, "Average Words Per Line:", averageWPL, "Average Line Length:", averageLL));
        }
    }


    public static void main(String[] args){
    //Set Fonts for readability.
        //Buttons
        inputBrowseButton.setFont(font);
        outputBrowseButton.setFont(font);
        leftJustifyButton.setFont(font);
        rightJustifyButton.setFont(font);
        formatButton.setFont(font);
        resetButton.setFont(font);

        //Text Fields
        inputFile.setFont(font);
        outputFile.setFont(font);

        //Labels
        inputLabel.setFont(font);
        outputLabel.setFont(font);
        justifyLabel.setFont(font);
        formatData.setFont(font);

    //Set preferred button size
        formatButton.setPreferredSize(new Dimension(1100, 50));
        resetButton.setPreferredSize(new Dimension(1100, 50));
    //Set Action Commands
        inputBrowseButton.setActionCommand("Browse Input");
        outputBrowseButton.setActionCommand("Browse Output");

    //Add action listeners to buttons
        inputBrowseButton.addActionListener(new ButtonHandler());
        outputBrowseButton.addActionListener(new ButtonHandler());
        leftJustifyButton.addActionListener(new ButtonHandler());
        rightJustifyButton.addActionListener(new ButtonHandler());
        formatButton.addActionListener(new ButtonHandler());
        resetButton.addActionListener(new ButtonHandler());

    //Group Radio Buttons
        ButtonGroup group = new ButtonGroup();
        group.add(leftJustifyButton);
        group.add(rightJustifyButton);

    //Set Text for main data JLabel
        /**************************************************
         * To get the JLabel to look how I wanted, stack
         * overflow said that i had to use HTML so I ended
         * up making a table in HTML so that each part was
         * on its own line and the colons would line up.
         *
         * Here's the HTML code in a more readable format:
         *
         * <html>
         *     <table>
         *         //Row 1
         *         <tr>
         *             //column 1
         *             <td align="right">
         *                 Words Processed:
         *             </td>
         *
         *             //column 2
         *             <td>
         *                 &emsp;###
         *             </td>
         *         </tr>
         *
         *         //Row 2
         *         <tr>
         *             <td align="right">
         *                 Lines:
         *             </td>
         *             <td>
         *                 &emsp;###
         *             </td>
         *         </tr>
         *
         *         //Row 3
         *         <tr>
         *             <td align="right">
         *                 Blank Lines Removed:
         *             </td>
         *             <td>
         *                 &emsp;###
         *             </td>
         *         </tr>
         *
         *         //Row 4
         *         <tr>
         *             <td align="right">
         *                 Average Words Per Line:
         *             </td>
         *             <td>
         *                 &emsp;###.##
         *             </td>
         *         </tr>
         *
         *         //Row 5
         *         <tr>
         *             <td align="right">
         *                 Average Line Length:
         *             </td>
         *             <td>
         *                 &emsp;###.##
         *             </td>
         *         </tr>
         *     </table>
         * </html>
         *
         ************************************************/
        formatData.setText(String.format("<html><table> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%.2f</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%.2f</td></tr> </table></html>", "Words Processed:", wordsProcessed, "Lines:", lines, "Blank Lines Removed:", blanksRemoved, "Average Words Per Line:", averageWPL, "Average Line Length:", averageLL));


    //Set up JPanel
        mainPanel.setLayout(flow);

    //add components
        //row 1
        mainPanel.add(inputLabel);
        mainPanel.add(inputBrowseButton);
        mainPanel.add(inputFile);
        //row 2
        mainPanel.add(outputLabel);
        mainPanel.add(outputBrowseButton);
        mainPanel.add(outputFile);
        //row 3
        mainPanel.add(justifyLabel);
        mainPanel.add(leftJustifyButton);
        mainPanel.add(rightJustifyButton);
        //row 4
        mainPanel.add(formatButton);

        //bottom half
        mainPanel.add(formatData);
        mainPanel.add(resetButton);

    //Main Frame
        JFrame window = new JFrame("360 Project: Text Formater");
        window.setContentPane(mainPanel);
        window.pack();
        window.setSize(1200,750);
        window.setLocation(100,100);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        JPanel panel = new JPanel();

        JTextArea out = new JTextArea();
        out.setEditable(false);
        out.setFont(font);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(inputFile.getText()));
        } catch (FileNotFoundException e1) {
            in = null;
        }


        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        while (line != null) {
            out.append(line + "\n");
            try {
                line = in.readLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        panel.add(out);

        JFrame inFile = new JFrame();
        inFile.setContentPane(panel);
        inFile.pack();
        inFile.setLocation(1500,100);
        inFile.setVisible(true);
        inFile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        */
    }
}
