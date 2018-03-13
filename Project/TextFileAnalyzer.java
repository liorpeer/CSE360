import	java.io.BufferedReader;
import	java.io.File;
import	java.io.FileReader;
import	java.io.IOException;
import	java.io.PrintWriter;
import	java.util.Scanner;

public	class	TextFileAnalyzer	{
    private	String	pathToTextFile,	pathToOutput;
    private	int	lineNumber	,	blankLine,	wordNumber;
    private	double	avgWordPerLine,	avgCharLine;

    public	TextFileAnalyzer(String	pathToTextFile,	String	pathToOutput)	throws	IOException
    {
        this.pathToTextFile	=	pathToTextFile;
        this.pathToOutput	=	pathToOutput;
        this.lineNumber	=	0;
        this.blankLine	=	0;
        this.wordNumber	=	0;
        this.avgWordPerLine	=	0;
        this.avgCharLine	=	0;
        AnalyzeFile();
        writeOutputFile();
    }

    private	void	AnalyzeFile()	throws	IOException	{
        int	charPerLine	=	0;
        File	file	=	new	File(this.pathToTextFile);
        BufferedReader	br	=	new	BufferedReader(new	FileReader(file));
        String	line	=	null;
        while	((line	=	br.readLine())	!=	null)	{
            lineNumber	++;
            if(line.trim().isEmpty())	{
                blankLine	++;
            }
            String	[]	wordArray	=	line.split("\\w+");
            wordNumber	+=	wordArray.length;
            charPerLine	+=	AnalyzeWordArray(wordArray);
        }
        avgWordPerLine	=	wordNumber	/	lineNumber;
        avgCharLine	=	charPerLine / lineNumber;
        br.close();
    }

    private	int	AnalyzeWordArray(String[]	wordArray)	{
        int	sumOfChar	=	0;
        for(int	i	=	0	;	i	<	wordArray.length	;	i++)	{
            sumOfChar	+=	wordArray[i].length();
        }
        return	sumOfChar;
    }

    private	void	writeOutputFile()	throws	IOException	{
        Scanner	sc	=	new	Scanner(new	File(pathToTextFile));
        String	output	=	"";
        String	curLine	=	"";
        String	s;
        while(sc.hasNext()){
            s	=	sc.next();
            if(curLine.length()	+	s.length()	>	80)	{
                output	+=	curLine	+	'\n';
                curLine	=	"";
            }
            else	{
                curLine	+=	" "	+	s;
            }
        }

        output	+=	curLine	+	'\n';
        PrintWriter	out	=	new	PrintWriter(pathToOutput);
        out.println(output);
        out.close();
        sc.close();
    }

    //Get methods
    public	int	getLineNumber()	{
        return	lineNumber;
    }
    public	int	getBlankLine()	{
        return	blankLine;
    }
    public	int	getWordNumber()	{
        return	wordNumber;
    }
    public	double	getAvgWordPerLine()	{
        return	avgWordPerLine;
    }
    public	double	getAvgCharLine()	{
        return	avgCharLine;
    }

    /*public	static	void	main(String[]	args)	throws	Exception{
        TextFileAnalyzer	tf	=	new	TextFileAnalyzer("C:\\Users\\luke_\\Desktop\\try.txt",	"C:\\Users\\luke_\\Desktop\\out.txt");
        System.out.println("\nWord	Number	=	"+tf.wordNumber	+	"\nLine	number	=	"+tf.getLineNumber()+"\nBlank	number	=	"+tf.blankLine+	"\nAverage	Word	Per	Line	=	"+tf.avgWordPerLine	+	"\nAverage	Line	Length	=	"+tf.avgCharLine);
    }*/
}