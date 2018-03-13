package phase1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileAnalyzer {
	
	private String pathToTextFile, pathToOutput;
	private int lineNumber , blankLine, wordNumber;
	private double avgWordPerLine, avgCharLine;
	
	public TextFileAnalyzer(String pathToTextFile, String pathToOutput) throws IOException {
		this.pathToTextFile = pathToTextFile;
		this.pathToOutput = pathToOutput;
		this.lineNumber = 0;
		this.blankLine = 0;
		this.wordNumber = 0;
		this.avgWordPerLine = 0;
		this.avgCharLine = 0;
		AnalyzeFile();
	}
	
	private void AnalyzeFile() throws IOException {
		int charPerLine = 0;
		File file = new File(this.pathToTextFile);
		BufferedReader br = new BufferedReader(new FileReader(file));  
		String line = null;  
		while ((line = br.readLine()) != null) {
			lineNumber ++;
			if(line.trim().isEmpty()) {
				blankLine ++;
			}
			String [] wordArray = line.split("\\w+");
			wordNumber += wordArray.length;
			charPerLine += AnalyzeWordArray(wordArray);
		} 
		
		avgWordPerLine = wordNumber / lineNumber;
		avgCharLine = charPerLine;
		br.close();	
	}
	
	private int AnalyzeWordArray(String[] wordArray) {
		int sumOfChar = 0;
		for(int i = 0 ; i < wordArray.length ; i++) {
			sumOfChar += wordArray[i].length();
		}
		return sumOfChar;	
	}
}
	

