import	java.io.BufferedReader;
import	java.io.File;
import	java.io.FileReader;
import	java.io.IOException;
import	java.io.PrintWriter;
import	java.util.Scanner;

public	class	TextFileAnalyzer	{
    private	String	pathToTextFile, pathToOutput;
    private	int	lineNumber, blankLine, wordNumber;
    private	double	avgWordPerLine,	avgCharLine;
    private boolean leftJustify;

    public	TextFileAnalyzer(String	pathToTextFile,	String pathToOutput, boolean leftJustify) throws IOException {
        this.pathToTextFile	= pathToTextFile;
        this.pathToOutput = pathToOutput;
        this.lineNumber	= 0;
        this.blankLine	= 0;
        this.wordNumber	= 0;
        this.avgWordPerLine	= 0;
        this.avgCharLine = 0;
        this.leftJustify = leftJustify;
        AnalyzeFile();
        writeOutputFile();
    }
    // upload file and analyze it
    private	void AnalyzeFile() throws IOException {
        int	totalChar	=	0;
        File	file	=	new	File(this.pathToTextFile);
        BufferedReader	br	=	new	BufferedReader(new	FileReader(file));
        String	line	=	null;

        while	((line	=	br.readLine())	!=	null)	{
            lineNumber	++;
			char []letters = line.toCharArray();


            if(line.trim().isEmpty())	{
                blankLine++;
            }
			else
			{

				for(int i = 0; i < line.length();i++)
				{
						for(i = 0; i < line.length();i++)
						{

							//Checks if there's a char and a space in that order, if the last char at the last index is not a space then it counts as a word as well
							//32 represents a space
							if(i > 0)
								if(letters[i] == 32 && letters[i-1] != 32)
									wordNumber++;
									else if(i == line.length() - 1 && letters[i] != 32)
											wordNumber++;
							totalChar++;
						}
           		}
			}


        }
        avgWordPerLine	=	wordNumber	/	lineNumber;
        avgCharLine	=	totalChar / lineNumber;
        br.close();
    }
/*		No longer needed---------------------------------------------
    private	int	AnalyzeWordArray(String[]	wordArray)	{
        int	sumOfChar	=	0;
        for(int	i	=	0	;	i	<	wordArray.length	;	i++)	{
            sumOfChar	+=	wordArray[i].length();
        }
        return	sumOfChar;
    }
*/
    //create output file
    private	void writeOutputFile() throws IOException {
        Scanner	sc	=	new	Scanner(new	File(pathToTextFile));
        PrintWriter	out	= new PrintWriter(pathToOutput);

        String output = "";
        String s;

        while(sc.hasNext()){
            s = sc.next();

            if(output.length() + s.length() > 80){


                if(leftJustify){
                    out.println(String.format("%-80s", output.trim()));
                }
                else {
                    out.println(String.format("%80s",output.trim()));
                }

                output	= "";
            }
            else{
                output	+=	" "	+ s;
            }
        }

        //Print final line
        if(leftJustify){
            out.print(String.format("%-80s", output.trim()));
        }
        else {
            out.print(String.format("%80s",output.trim()));
        }

        out.close();
        sc.close();
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
    public double getAvgCharLine()	{
        return	avgCharLine;
    }

}