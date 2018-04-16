import	java.io.BufferedReader;
import	java.io.File;
import	java.io.FileReader;
import	java.io.IOException;
import	java.io.PrintWriter;
import	java.util.Scanner;

public	class	TextFileAnalyzer	{
    private	String	pathToTextFile, pathToOutput;
    private	int	lineNumber,postLineNum, blankLine, wordNumber, totalSpaces;
    private	double	avgWordPerLine,	avgCharLine;
    private int leftJustify, charPL;
    private boolean dbl_space;

    public	TextFileAnalyzer(String	pathToTextFile,	String pathToOutput, int leftJustify, int charPL, boolean dbl_space) throws IOException {
        this.pathToTextFile	= pathToTextFile;
        this.pathToOutput = pathToOutput;
        this.lineNumber	= 0;
        this.postLineNum = 0;
        this.blankLine	= 0;
        this.wordNumber	= 0;
        this.avgWordPerLine	= 0;
        this.avgCharLine = 0;
        this.totalSpaces = 0;
        this.leftJustify = leftJustify;
        this.charPL = charPL;
        this.dbl_space = dbl_space;
        AnalyzeFile();

        writeOutputFile();
    }
    // upload file and analyze it
    private	void AnalyzeFile() throws IOException {
        int	totalChar	=	0;

        File	file	=	new	File(this.pathToTextFile);
        BufferedReader	br	=	new	BufferedReader(new	FileReader(file));
        String	line;
        while	((line	=	br.readLine())	!=	null)	{


            lineNumber++;
			char []letters = line.toCharArray();


            if(line.trim().isEmpty())	{
                blankLine++;
            }
			else
			{

				for(int i = 0; i < line.length();i++)
				{


							//Checks if there's a char and a space in that order, if the last char at the last index is not a space then it counts as a word as well
							//32 represents a space
							if(i >= 0)
								if(letters[i] == 32 && letters[i-1] != 32)
									wordNumber++;
									else if(i == line.length() - 1 && letters[i] != 32)
											wordNumber++;
							totalChar++;

           		}
			}


        }
        //if dbl_space is true, linenumber doubles in size - 1 because we insert a line
		if(dbl_space == true)
		{

			//postLineNum = (2*lineNumber) - 1;
			lineNumber = (lineNumber*2) -1;
		}

        //avgWordPerLine	=	wordNumber	/	postLineNum;
        avgWordPerLine	=	wordNumber	/	lineNumber;
        avgCharLine	=	totalChar; // lineNumber;
        br.close();
    }

    //create output file
    private	void writeOutputFile() throws IOException {
        Scanner	sc	=	new	Scanner(new	File(pathToTextFile));
        PrintWriter	out	= new PrintWriter(pathToOutput);

        String output = "";
        String s;
		String left;
		String right;
		String bothJust;
		char []both;
		int count = 0;
		int sizeBoth = 0;

		while(sc.hasNext()){
            s = sc.next();

            if(output.length() + s.length() > charPL){

				//create string for string format
				left = "%" + (-charPL) + "s";
				right = "%" + (charPL) + "s";

                //0 for leftjustification, 1 for right justification, 2 for both justification
                if(leftJustify == 0){
					postLineNum++;
                out.println(String.format(left, output.trim()));
                }
                else if(leftJustify == 1){
					postLineNum++;
                    //out.println(String.format("%80s",output.trim()));

                out.println(String.format(right, output.trim()));
                }
                else if(leftJustify == 2)
                {
					bothJust = String.format(left, output.trim());
					both = bothJust.toCharArray(); //charArray
					sizeBoth = bothJust.length();	// size of line
					postLineNum++;
					int p = sizeBoth;
					while(both[p-1] == 32)
					{
						p--;
						count++;
					}

					for(int i = 1, k = 0; i <= count ; k++)
					{
						if(k < sizeBoth-1)
						if(both[k] == 32)
						{

								bothJust = bothJust.substring(0,k) + " " + bothJust.substring(k,sizeBoth-1);
								totalSpaces++;
								both = bothJust.toCharArray();
								k++;
								i++;

						}
						if(k == sizeBoth -1)
							k = 0;
					}
					count = 0;
					out.println(bothJust);

				}

                output	= "";

                if(dbl_space == true) {
                		out.println();
                		totalSpaces++;
                }
            }
            else{
                output	+=	" "	+ s;
            }
        }

        //Print final line
        if(leftJustify == 0){
            //out.print(String.format("%-80s", output.trim()));
        }
        else if(leftJustify == 1){
            //out.print(String.format("%80s",output.trim()));
        }

        out.close();
        sc.close();
    }

    //Get methods
    public int getLineNumber(){

        //return lineNumber;
        if(dbl_space == true)
        	return 2*postLineNum -1;
        else
        	return postLineNum;

    }
    public int getBlankLine()	{
        return	blankLine;
    }
    public int getWordNumber()	{
        return	wordNumber;
    }
    public double getAvgWordPerLine()	{
		if(dbl_space == true)
        	return	wordNumber/((2*postLineNum)-1);
        else
        	return wordNumber/postLineNum;
    }
    public double getAvgCharLine()	{
		if(dbl_space == true)
        return	avgCharLine/((2*postLineNum) - 1);
        else
        return avgCharLine/postLineNum;
    }
    public int getSpacesAdded()
    {
		return totalSpaces;
	}

}