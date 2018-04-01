import	java.io.BufferedReader;
import	java.io.File;
import	java.io.FileReader;
import	java.io.IOException;
import	java.io.PrintWriter;

public class TextFileAnalyzer {
    private final int LEFT_JUSTIFY = 1;
    private final int RIGHT_JUSTIFY = 2;
    //private final int FULL_JUSTIFY = 3;

    private	String pathToInput,	pathToOutput;
    private String [] words;
    private	int	lineNumber,	blankLine, wordNumber, spacesAdded, totalSpacesAdded, lineLength, justify;
    private	double	avgWordPerLine,	avgCharLine;
    private boolean doubleSpaced;

    public TextFileAnalyzer(String pathToInput, String pathToOutput, int justify, int lineLength, boolean doubleSpaced) throws IOException {
        this.pathToInput = pathToInput;
        this.pathToOutput = pathToOutput;
        this.lineNumber	= 0;
        this.blankLine	= 0;
        this.wordNumber	= 0;
        this.avgWordPerLine	= 0;
        this.avgCharLine = 0;
        this.justify = justify;
        this.lineLength = lineLength;
        this.doubleSpaced = doubleSpaced;
        AnalyzeInputFile();
        writeOutputFile();
    }

    private	void AnalyzeInputFile() throws IOException {
        BufferedReader br =	new	BufferedReader(new	FileReader(new	File(this.pathToInput)));
        String line;
        String full = "";

        while((line = br.readLine()) !=	null) {
            line = line.trim();

            full += line + " ";
            lineNumber++;

            if(line.isEmpty()){
                blankLine++;
            }
        }

        words = full.split("\\s+");
        wordNumber = words.length;
        for(int	i =	0; i < words.length; i++) {
            avgCharLine += words[i].length();
        }
        avgCharLine = avgCharLine / lineNumber;
        avgWordPerLine = (double) wordNumber / lineNumber;

        br.close();
    }

    private	void writeOutputFile() throws IOException {
        PrintWriter	out	= new PrintWriter(pathToOutput);
        String outputLine = "";

        if(doubleSpaced) {
        //Double Spaced
            for (int i = 0; i < words.length; i++) {
                if (outputLine.length() + words[i].length() > lineLength) {
                    outputLine = outputLine.trim();
                    spacesAdded = lineLength - outputLine.trim().length();
                    totalSpacesAdded += spacesAdded;

                    if (justify == LEFT_JUSTIFY) {
                        out.println(String.format("%-" + lineLength + "s\n", outputLine.trim()));
                    } else if (justify == RIGHT_JUSTIFY) {
                        out.println(String.format("%" + lineLength + "s\n", outputLine.trim()));
                    } else { //Full Justify
                        //I want to have it go thorugh eahc space and add a new  space to it every time.
                        int j = 0;
                        while(j < spacesAdded) {
                            for (int k = -1; (k = outputLine.indexOf(" ", k + 1)) != -1 && j < spacesAdded; k++) {
                                String firstpart = outputLine.substring(0, k);
                                String lastpart = outputLine.substring(k + 1, outputLine.length());
                                outputLine = firstpart + "  " + lastpart;

                                j++;
                            }
                        }
                        out.print(outputLine.trim());
                        out.println("\n"); //For double spacing
                    }

                    outputLine = ""; //Reset outputLine string.
                    i--; //Don't skip the word that didn't fit into the outputLine line.

                }
                else {
                    outputLine += words[i] + " ";
                }
            }
        }
        //Single Spaced
        else {
            for (int i = 0; i < words.length; i++) {
                if (outputLine.length() + words[i].length() > lineLength) {
                    outputLine = outputLine.trim();
                    spacesAdded = lineLength - outputLine.trim().length();
                    totalSpacesAdded += spacesAdded;
                    if (justify == LEFT_JUSTIFY) {
                        out.println(String.format("%-" + lineLength + "s", outputLine.trim()));
                    }
                    else if (justify == RIGHT_JUSTIFY) {
                        out.println(String.format("%" + lineLength + "s", outputLine.trim()));
                    }
                    else { //Full Justify
                        //I want to have it go through each space and add a new  space to it every time.
                        int j = 0;
                        while(j < spacesAdded) {
                            for (int k = -1; (k = outputLine.indexOf(" ", k + 1)) != -1 && j < spacesAdded; k++) {
                                String firstpart = outputLine.substring(0, k);
                                String lastpart = outputLine.substring(k + 1, outputLine.length());
                                outputLine = firstpart + "  " + lastpart;

                                j++;
                            }
                        }
                        out.println(outputLine.trim());
                    }

                    outputLine = ""; //Reset outputLine string.
                    i--; //Don't skip the word that didn't fit into the outputLine line.

                }
                else {
                    outputLine += words[i] + " ";
                }
            }
        }


        //write final line to output file
        outputLine = outputLine.trim();
        spacesAdded = lineLength - outputLine.trim().length();
        totalSpacesAdded += spacesAdded;

        if(justify == LEFT_JUSTIFY) {
            out.print(String.format("%-" + lineLength + "s", outputLine.trim()));
        }
        else if(justify == RIGHT_JUSTIFY){
            out.print(String.format("%" + lineLength + "s", outputLine.trim()));
        }
        else {
            //I want to have it go through each space and add a new  space to it every time.
            if(outputLine.indexOf(' ') != -1) {
                int j = 0;
                while (j < spacesAdded) {
                    for (int k = -1; (k = outputLine.indexOf(" ", k + 1)) != -1 && j < spacesAdded; k++) {
                        String firstpart = outputLine.substring(0, k);
                        String lastpart = outputLine.substring(k + 1, outputLine.length());
                        outputLine = firstpart + "  " + lastpart;

                        j++;
                    }
                }
                out.print(outputLine);
            }
            else {
                out.print(String.format("%-" + lineLength + "s", outputLine.trim()));
            }

        }

        out.close();
    }

    //Get methods
    public int getLineNumber(){
        return lineNumber;
    }
    public int getBlankLine()	{
        return	blankLine;
    }
    public int getWordNumber()	{
        return	wordNumber;
    }
    public double getAvgWordPerLine()	{
        return	avgWordPerLine;
    }
    public double getAvgCharLine() {
        return avgCharLine;
    }
    public int getSpacesAdded() {
        return totalSpacesAdded;
    }
}