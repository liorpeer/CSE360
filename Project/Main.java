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

public class Main {
     public static int wordsProcessed = 434;
     public static int lines = 34;
     public static int blanksRemoved = 38;
     public static double averageWPL = 14.24;
     public static double averageLL = 74.24;


     private static class ButtonHandler implements ActionListener {
          public void actionPerformed(ActionEvent e) {
               //Do Stuff Here
          }
     }

     public static void main(String[] args){
          //Fonts
               Font font = new Font("name", Font.PLAIN, 42);

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
               JButton leftJustifiedButton = new JButton("Left Justtified");
               JButton rightJustifiedButton = new JButton("Right Justified");
               JButton formatButton = new JButton("Format my File");
               JButton downloadButton = new JButton("Download Output File");

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

               //Text Field
               JTextField inputFile = new JTextField("input.txt", 10);
               JTextField outputFile = new JTextField("output.txt", 10);

                    //set fonts
                    inputFile.setFont(font);
                    outputFile.setFont(font);

               //Labels
               JLabel inputLabel = new JLabel("Select Input File:");
               JLabel outputLabel = new JLabel("Select Output File:");
               JLabel justifyLabel = new JLabel("Justification");
               JLabel formatData = new JLabel();

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
               JPanel topPanel = new JPanel();
                    topPanel.setLayout(flow2);
                    //add components
                    //row 1
                    topPanel.add(inputLabel);
                    topPanel.add(browseButton);
                    topPanel.add(inputFile);
                    //row 2
                    topPanel.add(outputLabel);
                    topPanel.add(selectButton);
                    topPanel.add(outputFile);
                    //row 3
                    topPanel.add(justifyLabel);
                    topPanel.add(leftJustifiedButton);
                    topPanel.add(rightJustifiedButton);
                    //row 4
                    topPanel.add(formatButton);

                    //bottom half
                    topPanel.add(formatData);
                    topPanel.add(downloadButton);

               /***************************************
               * Old code I didn't want to get rid of *
               ****************************************
               * JPanel bottomPanel = new JPanel();
               *     bottomPanel.setLayout(flow);
               *     //add components
               *     bottomPanel.add(formatData);
               *     bottomPanel.add(downloadButton);
               *
               * JPanel mainPanel = new JPanel();
               *     mainPanel.setLayout(grid);
               *     //add components
               *     mainPanel.add(topPanel);
               *     mainPanel.add(bottomPanel);
               ***************************************/

          //Main Frame
               JFrame window = new JFrame("360 Project: Text Formater");
               window.setContentPane(topPanel); //normaly mainPanel
               window.pack();
               window.setSize(1200,850);
               window.setLocation(100,100);
               window.setVisible(true);
               window.setResizable(false);
               window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     }
}
