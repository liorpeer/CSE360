/************************************************
 * Group:
 * 	Luke Mains
 * 	Karissa Kauspedas
 * 	Tin Pham
 * 	Lior Peer
 * 
 * Date 3-14-2018
 * 
 * Description:
 * 
 * This program is a text file formater. It 
 * takes an input file and converts it to an
 * ouptut file (both .txt files). The output 
 * is limited to 80 characters per line while
 * still keeping full words intact. It also 
 * removes all blank lines. Lastly the program
 * analyzes the input file. The main metrics 
 * are:
 * 	- Words Processed
 *  - Number of lines
 *  - Number of blank lines removed
 *  - Average words per line
 *  - Average line length (characters per line)
 **************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class Main {
    //Global Variables
    static int wordsProcessed, lines, blanksRemoved;
    static double averageWPL;
    static double averageLL;
    static TextFileAnalyzer textFileAnalyzer;
	static String input, output;
	
    //Layouts
    static FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 50, 20);

    //Text Field
    static JTextField inputFile = new JTextField("try.txt", 13);
    static JTextField outputFile = new JTextField("githubout.txt", 13);

    //Labels
    static JLabel formatData = new JLabel();
    static JLabel inputLabel = new JLabel("  Select Input File:");
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
            Object source = e.getSource();

			if(source == inputBrowseButton)
			{
					JFileChooser chooser = new JFileChooser();
					try
					{
						chooser.setCurrentDirectory( new java.io.File("."));
						if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
							input = chooser.getSelectedFile().getAbsolutePath();
							inputFile.setText(chooser.getSelectedFile().getName());
					}
					catch(Exception a){
						//Add JDialogBox for error
					}

			}
			else if(source == outputBrowseButton)
			{
					JFileChooser chooser = new JFileChooser();
					try
					{
						chooser.setCurrentDirectory( new java.io.File("."));
						if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)

							output = chooser.getSelectedFile().getAbsolutePath();
							outputFile.setText(chooser.getSelectedFile().getName());
					}
					catch(Exception a){
						
					}
			}
			else if(source == formatButton)
			{
				try {
					if(leftJustifyButton.isSelected()) {
						textFileAnalyzer = new TextFileAnalyzer(input, output, true);
					}
					else{
						textFileAnalyzer = new TextFileAnalyzer(input, output, false);
					}
				} catch (NullPointerException e1) {
					//Error: input file not found
                    JOptionPane.showMessageDialog(new JFrame(), "Error: Your input file doesn't exist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
				}
				catch (IOException e2) {
                    JOptionPane.showMessageDialog(new JFrame(), "Error: Your input file doesn't exist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
				}

				wordsProcessed = textFileAnalyzer.getWordNumber();
				lines = textFileAnalyzer.getLineNumber();
				blanksRemoved = textFileAnalyzer.getBlankLine();
				averageWPL = textFileAnalyzer.getAvgWordPerLine();
				averageLL = textFileAnalyzer.getAvgCharLine();

			}
			else if(source == resetButton)
			{
				wordsProcessed = 0;
				lines = 0;
				blanksRemoved = 0;
				averageWPL = 0;
				averageLL = 0;

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
        formatButton.setPreferredSize(new Dimension(1100, 60));
        resetButton.setPreferredSize(new Dimension(1100, 60));

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
    }
}