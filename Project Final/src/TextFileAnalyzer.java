import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileAnalyzer {
	//Constants
	final int LEFT_JUSTIFY = 0, RIGHT_JUSTIFY = 1, FULL_JUSTIFY = 2;

	//Local Variables
    private	String	pathToTextFile, pathToOutput;
    private	int	lineNumber,postLineNum, blankLine, wordNumber, totalSpaces;
    private	double avgCharLine;
    private int justification, charPL;
    private boolean double_spaced;

    //Constructor
    TextFileAnalyzer(String	pathToTextFile,	String pathToOutput, int leftJustify, int charPL, boolean dbl_space) throws IOException {
        this.pathToTextFile	= pathToTextFile;
        this.pathToOutput = pathToOutput;
        this.justification = leftJustify;
        this.charPL = charPL;
        this.double_spaced = dbl_space;
        AnalyzeFile();

        writeOutputFile();
    }

    /*
     * This method opens the input file and analyzes it.
     */
    private	void AnalyzeFile() throws IOException {
        File file =	new	File(this.pathToTextFile);
        BufferedReader br =	new	BufferedReader(new FileReader(file));
        String line = br.readLine();
        
        while(line != null) {
            lineNumber++;
            char[] letters = line.toCharArray();

            if(line.trim().isEmpty()) {
                blankLine++;
            }
			else {
				for(int i = 0; i < line.length();i++) {
					//Checks if there's a char and a space in that order, if the last char at the last index is not a space then it counts as a word as well
					if(letters[i] == ' ' && letters[i - 1] != ' ')
						wordNumber++;
					else if(i == line.length() - 1 && letters[i] != ' ')
						wordNumber++;
                }
			}
            
            line	= br.readLine();
        }

        //if dbl_space is true, line number doubles in size - 1 because we insert a line
		if(double_spaced == true)
			lineNumber = (lineNumber*2) - 1;

        br.close();
    }

    /*
     * This method opens the output file and writes the formatted text to it.
     */
    private	void writeOutputFile() throws IOException {
        Scanner	sc = new Scanner(new File(pathToTextFile));
        PrintWriter	out	= new PrintWriter(pathToOutput);

        String output = "";
        String word;
		String left_justify_format;
		String right_justify_format;
		String tempString;
		char[] tempCharArray;
		int numOfEndSpaces = 0;

		while(sc.hasNext()) {
            word = sc.next();

            if(output.length() + word.length() > charPL) {
				//create string for string format
				left_justify_format = "%" + (-charPL) + "s";
				right_justify_format = "%" + (charPL) + "s";

                if(justification == this.LEFT_JUSTIFY) {
					avgCharLine += output.length();
					postLineNum++;
					out.println(String.format(left_justify_format, output.trim()));
                }
                else if(justification == this.RIGHT_JUSTIFY) {
					postLineNum++;
					avgCharLine += output.length();
					out.println(String.format(right_justify_format, output.trim()));
                }
                else if(justification == this.FULL_JUSTIFY) {
					avgCharLine += output.length();
					tempString = String.format(left_justify_format, output.trim());
					tempCharArray = tempString.toCharArray(); //charArray
					int full_length = tempString.length();	// size of line
					postLineNum++;
					
					int p = full_length;
					while(tempCharArray[p - 1] == ' ') {
						p--;
						numOfEndSpaces++;
					}

					for(int i = 1, k = 0; i <= numOfEndSpaces; k++) {
						if(k < full_length - 1) //What does this line do?
						
						if(tempCharArray[k] == ' ') {
							tempString = tempString.substring(0, k) + " " + tempString.substring(k, full_length - 1);
							totalSpaces++;
							tempCharArray = tempString.toCharArray();
							k++; //k is already incremented in the for loop? do you want it go up by 2's?
							i++;
						}
						
						if(k == full_length - 1)
							k = 0;
					}
					
					numOfEndSpaces = 0;
					out.println(tempString);
				}

                output	= ""; //Reset the output line.

                if(double_spaced == true) {
            		out.println();
            		totalSpaces++;
                }
            }
            else {
                output += " " + word;
            }
        }

        //Print final line (doesn't do anything currently)
        if(justification == 0){
            //out.print(String.format("%-80s", output.trim()));
        }
        else if(justification == 1){
            //out.print(String.format("%80s",output.trim()));
        }
        else {
        	//Do the full justify stuff
        }

        out.close();
        sc.close();
    }

    //Get methods
    public int getLineNumber(){
        if(double_spaced == true)
        	return 2*postLineNum - 1;
        else
        	return postLineNum;
    }
    
    public int getBlankLine() {
        return	blankLine;
    }
    
    public int getWordNumber() {
        return	wordNumber;
    }
    
    public double getAvgWordPerLine() {
		if(double_spaced == true)
        	return	wordNumber / ((2*postLineNum) - 1);
        else
        	return wordNumber / postLineNum;
    }
    
    public double getAvgCharLine() {
		if(double_spaced == true)
			return	avgCharLine / ((2*postLineNum) - 1);
        else
        	return avgCharLine / postLineNum;
    }
    
    public int getSpacesAdded() {
		return totalSpaces;
	}

}