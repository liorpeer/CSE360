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
import java.io.*;
import javax.swing.*;
import	java.io.IOException;
import	java.util.Scanner;

public class Main {
    public static int wordsProcessed;
    public static int lines;
    public static int blanksRemoved;
    public static double averageWPL;
    public static double averageLL;
    public static TextFileAnalyzer textFileAnalyzer;

    //Text Field
    static JTextField inputFile = new JTextField("C:\\Users\\luke_\\Desktop\\try.txt", 20);
    static JTextField outputFile = new JTextField("C:\\Users\\luke_\\Desktop\\out.txt", 20);

    //Labels
    static JLabel formatData = new JLabel();

    //Fonts
    static Font font = new Font("name", Font.PLAIN, 40);

    private static class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String buttonPressed = e.getActionCommand();

            if(buttonPressed.equals("Format my File")){
                try {
                    textFileAnalyzer = new TextFileAnalyzer(inputFile.getText(), outputFile.getText());
                } catch (IOException e1) {
                    //error
                }

                wordsProcessed = textFileAnalyzer.getWordNumber();
                lines = textFileAnalyzer.getLineNumber();
                blanksRemoved = textFileAnalyzer.getBlankLine();
                averageWPL = textFileAnalyzer.getAvgWordPerLine();
                averageLL = textFileAnalyzer.getAvgCharLine();

                JPanel panel = new JPanel();

                JTextArea out = new JTextArea();
                out.setEditable(false);
                out.setFont(font);
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(outputFile.getText()));
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

                JFrame outFile = new JFrame();
                outFile.setContentPane(panel);
                outFile.pack();
                outFile.setLocation(1000,1000);
                outFile.setVisible(true);
                outFile.setResizable(false);
                outFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if(buttonPressed.equals("Reset File")){
                wordsProcessed = 0;
                lines = 0;
                blanksRemoved = 0;
                averageWPL = 0;
                averageLL = 0;
            }
            else if(buttonPressed.equals("Left Justtified (Default)")){

                JPanel panel = new JPanel();

                JTextArea out = new JTextArea();

                out.setEditable(false);
                out.setFont(font);
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(outputFile.getText()));
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
                    out.append(String.format("%-80s\n", line));
                    try {
                        line = in.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                panel.add(out);

                JFrame outFile = new JFrame();
                outFile.setContentPane(panel);
                outFile.pack();
                outFile.setLocation(1000,1000);
                outFile.setVisible(true);
                outFile.setResizable(false);
                outFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if(buttonPressed.equals("Right Justified")){
                //right justify

                JPanel panel = new JPanel();

                JTextArea out = new JTextArea();
                out.setEditable(false);
                out.setFont(font);
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(outputFile.getText()));
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
                    //System.out.print(String.format("%80s\n", line));
                    out.append(String.format("%80s\n", line));
                    try {
                        line = in.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                panel.add(out);

                JFrame outFile = new JFrame();
                outFile.setContentPane(panel);
                outFile.pack();
                outFile.setLocation(1000,1000);
                outFile.setVisible(true);
                outFile.setResizable(false);
                outFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            }

            formatData.setText(String.format("<html><table> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%d</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%.2f</td></tr> <tr><td align=\"right\">%s</td><td>&emsp;%.2f</td></tr> </table></html>", "Words Processed:", wordsProcessed, "Lines:", lines, "Blank Lines Removed:", blanksRemoved, "Average Words Per Line:", averageWPL, "Average Line Length:", averageLL));
        }
    }

    public static void main(String[] args){
        //Fonts


        //Layouts
        GridLayout grid = new GridLayout(2,1);
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        FlowLayout flow = new FlowLayout();
        FlowLayout flow2 = new FlowLayout(FlowLayout.CENTER, 50, 20);
        SpringLayout spring = new SpringLayout();

        //Components
        //Buttons
        JButton browseButton = new JButton("Browse");
        JButton selectButton = new JButton("Select");
        JButton leftJustifiedButton = new JButton("Left Justtified (Default)");
        JButton rightJustifiedButton = new JButton("Right Justified");
        JButton formatButton = new JButton("Format my File");
        JButton downloadButton = new JButton("Reset File");

            //Set Button Font
            browseButton.setFont(font);
            selectButton.setFont(font);
            leftJustifiedButton.setFont(font);
            rightJustifiedButton.setFont(font);
            formatButton.setFont(font);
            downloadButton.setFont(font);

            //Set prefered size
            formatButton.setPreferredSize(new Dimension(1110, 60));
            downloadButton.setPreferredSize(new Dimension(600, 60));

            //Add action listeners
            browseButton.addActionListener(new ButtonHandler());
            selectButton.addActionListener(new ButtonHandler());
            leftJustifiedButton.addActionListener(new ButtonHandler());
            rightJustifiedButton.addActionListener(new ButtonHandler());
            formatButton.addActionListener(new ButtonHandler());
            downloadButton.addActionListener(new ButtonHandler());



            //set fonts
            inputFile.setFont(font);
            outputFile.setFont(font);

        //Labels
        JLabel inputLabel = new JLabel("Select Input File:");
        JLabel outputLabel = new JLabel("Select Output File:");
        JLabel justifyLabel = new JLabel("Justification");


            //Set Text
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

            //Set fonts
            inputLabel.setFont(font);
            outputLabel.setFont(font);
            justifyLabel.setFont(font);
            formatData.setFont(font);


        //Panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(flow2);

        //add components
        //row 1
        mainPanel.add(inputLabel);
        //mainPanel.add(browseButton);
        mainPanel.add(inputFile);
        //row 2
        mainPanel.add(outputLabel);
        //mainPanel.add(selectButton);
        mainPanel.add(outputFile);
        //row 3
        mainPanel.add(justifyLabel);
        mainPanel.add(leftJustifiedButton);
        mainPanel.add(rightJustifiedButton);
        //row 4
        mainPanel.add(formatButton);

        //bottom half
        mainPanel.add(formatData);
        mainPanel.add(downloadButton);

        //Main Frame
        JFrame window = new JFrame("360 Project: Text Formater");
        window.setContentPane(mainPanel);
        window.pack();
        window.setSize(1200,850);
        window.setLocation(100,100);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        inFile.setLocation(1000,100);
        inFile.setVisible(true);
        inFile.setResizable(false);
        inFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
