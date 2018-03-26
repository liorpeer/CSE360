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
    static int wordsProcessed, lines, blanksRemoved, spacesAdded;
    static double averageWPL;
    static double averageLL;
    static TextFileAnalyzer textFileAnalyzer;
	static String input, output;
	
    //Layouts
    static FlowLayout flow_right = new FlowLayout(FlowLayout.RIGHT, 40, 20);
    static FlowLayout flow_center = new FlowLayout(FlowLayout.CENTER, 40, 20);

    //Text Fields - Windows Settings
    static JTextField inputFile = new JTextField("", 15);
    static JTextField outputFile = new JTextField("", 15);
    static JTextField maxChars = new JTextField("80", 5);
    
    //Text Fields - Mac Settings
    //static JTextField inputFile = new JTextField("try.txt", 13);
    //static JTextField outputFile = new JTextField("githubout.txt", 13);
    

    //Labels
    static JLabel formatData = new JLabel();
    static JLabel inputLabel = new JLabel("Select Input File:");
    static JLabel outputLabel = new JLabel("Select Output File:");
    static JLabel justifyLabel = new JLabel("Justification:");
    static JLabel spacingLabel = new JLabel("Spacing:");
    static JLabel maxCharLabel = new JLabel("How many characters would you like in the formated text file?");

    //Fonts
    static Font font = new Font("name", Font.PLAIN, 14);

    //Buttons
    static JButton inputBrowseButton = new JButton("Browse");
    static JButton outputBrowseButton = new JButton("Browse");
    static JButton formatButton = new JButton("Format my File");
    static JButton resetButton = new JButton("Reset");

    //Radio Buttons
    static JRadioButton leftJustifyButton = new JRadioButton("Left (Default)");
    static JRadioButton rightJustifyButton = new JRadioButton("Right");
    static JRadioButton fullJustifyButton = new JRadioButton("Left/Right (Full)");
    static JRadioButton singleSpaceButton = new JRadioButton("Single Spaced (Default)");
    static JRadioButton doubleSpaceButton = new JRadioButton("Double Spaced");

    //Panels
    static JPanel mainPanel = new JPanel();

    /**
     * This class implements all the actions needed when a button is pressed.
     */
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
					catch(Exception a){}

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
					catch(Exception a){}
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
				} 
				catch (IOException e2) {
					//Error: input file does not exist
                    JOptionPane.showMessageDialog(new JFrame(), "Error: Your input file doesn't exist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
				}
				catch (NullPointerException e1) {
					//Error: output file does not exist
                    JOptionPane.showMessageDialog(new JFrame(), "Error: Your output file doesn't exist.", "File Not Found", JOptionPane.ERROR_MESSAGE);
				}

				wordsProcessed = textFileAnalyzer.getWordNumber();
				lines = textFileAnalyzer.getLineNumber();
				blanksRemoved = textFileAnalyzer.getBlankLine();
				averageWPL = textFileAnalyzer.getAvgWordPerLine();
				averageLL = textFileAnalyzer.getAvgCharLine();
				//spacesAdded = textFileAnalyser.getSpacesAdded();

			}
			else if(source == resetButton) {
				wordsProcessed = 0;
				lines = 0;
				blanksRemoved = 0;
				averageWPL = 0;
				averageLL = 0;
				spacesAdded = 0;
			}
			
			//Reset the text of the Data Label
			setTextFormatDataLabel();
		}
    }

    /**
     * This gives all the components the correct size, font, etc. 
     * Mostly used to make the application look prettier.
     */
    public static void setupComponents() {
    	//Set Fonts for readability.
        //Buttons
        inputBrowseButton.setFont(font);
        outputBrowseButton.setFont(font);
        leftJustifyButton.setFont(font);
        rightJustifyButton.setFont(font);
        fullJustifyButton.setFont(font);
        formatButton.setFont(font);
        resetButton.setFont(font);
        singleSpaceButton.setFont(font);
        doubleSpaceButton.setFont(font);

        //Text Fields
        inputFile.setFont(font);
        outputFile.setFont(font);
        maxChars.setFont(font);

        //Labels
        inputLabel.setFont(font);
        outputLabel.setFont(font);
        justifyLabel.setFont(font);
        spacingLabel.setFont(font);
        maxCharLabel.setFont(font);
        formatData.setFont(font);

    //Set preferred button size
        formatButton.setPreferredSize(new Dimension(500, 25));
        resetButton.setPreferredSize(new Dimension(100, 25));

    //Add action listeners to buttons
        inputBrowseButton.addActionListener(new ButtonHandler());
        outputBrowseButton.addActionListener(new ButtonHandler());
        leftJustifyButton.addActionListener(new ButtonHandler());
        rightJustifyButton.addActionListener(new ButtonHandler());
        formatButton.addActionListener(new ButtonHandler());
        resetButton.addActionListener(new ButtonHandler());

    //Group Radio Buttons
        ButtonGroup justifyButtonGroup = new ButtonGroup();
        justifyButtonGroup.add(leftJustifyButton);
        justifyButtonGroup.add(rightJustifyButton);
        justifyButtonGroup.add(fullJustifyButton);
        
        ButtonGroup spacingButtonGroup = new ButtonGroup();
        spacingButtonGroup.add(singleSpaceButton);
        spacingButtonGroup.add(doubleSpaceButton);
        
    //Set text of the important data label
        setTextFormatDataLabel();
    }
    
    /**
     * This function is used to set the JLabel that contains the data
     * from formating the input file. It's kind of complicated. I 
     * read online that you have to use HTML to have multiple lines
     * and stuff in a JLabel.
     * 
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
     *         
     *         //Row 6
     *         <tr>
     *             <td align="right">
     *                 Spaces Added:
     *             </td>
     *             <td>
     *                 &emsp;###
     *             </td>
     *         </tr>
     *     </table>
     * </html>
     */
    public static void setTextFormatDataLabel() {
        formatData.setText(	
        		String.format("<html> <table>") +
        		String.format("<tr> <td align=\"right\"> %s </td><td> &emsp; %d   </td> </tr>", "Words Processed:", wordsProcessed) +
        		String.format("<tr> <td align=\"right\"> %s </td><td> &emsp; %d   </td> </tr>", "Lines:", lines) +
        		String.format("<tr> <td align=\"right\"> %s </td><td> &emsp; %d   </td> </tr>", "Blank Lines Removed:", blanksRemoved) +
        		String.format("<tr> <td align=\"right\"> %s </td><td> &emsp; %.2f </td> </tr>", "Average Words Per Line:", averageWPL) +
        		String.format("<tr> <td align=\"right\"> %s </td><td> &emsp; %.2f </td> </tr>", "Average Line Length:", averageLL) +
        		String.format("<tr> <td align=\"right\"> %s </td><td> &emsp; %d   </td> </tr>", "Spaces Added: ", spacesAdded) +
        		String.format("</table> </html>"));
    }
    
    public static void main(String[] args){
    //Set up JPanel
        mainPanel.setLayout(flow_center);
        setupComponents();

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
        mainPanel.add(fullJustifyButton);
        //row 4
        mainPanel.add(spacingLabel);
        mainPanel.add(singleSpaceButton);
        mainPanel.add(doubleSpaceButton);
        //row 5
        mainPanel.add(maxCharLabel);
        mainPanel.add(maxChars);
        //row 6
        mainPanel.add(formatData);
        //row 7
        mainPanel.add(formatButton);
        //row 8
        mainPanel.add(resetButton);

    //Main Frame
        JFrame window = new JFrame("360 Project: Text Formater");
        window.setContentPane(mainPanel);
        window.pack();
        window.setSize(600,570);
        window.setLocation(100,100);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}