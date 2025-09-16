package BookAnalyticsProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.LinkedHashMap;


public class GraydonBookAnalytics extends BookAnalytics{

    protected File file;
	protected Scanner scan;
	protected PrintWriter write; 
    protected String fileAsString;
    
    public GraydonBookAnalytics(String aTextFile, String outputFile){
        super(aTextFile, outputFile);
        file = new File(aTextFile);
        try {
            fileAsString = Files.readString(Path.of(aTextFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

		try {
			scan = new Scanner(file);
			write = new PrintWriter(outputFile, "UTF-8");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}  
    }

    @Override
    public String mostFrequentWord() {
        String message = fileAsString;
        char[] invalid = {'.',',','?','!','"','\'','\\','/','”',';','\t','\n','˜'};
  
        for(char c : invalid){
                message = message.replace(""+c, " ");
        }
        String[] messageWords=message.split(" ");

        LinkedHashMap<String, Integer> wordCounts = new LinkedHashMap<>();
        wordCounts.put("",0);
        wordCounts.put(" ",0);
        
        for(int i = 0; i<messageWords.length;i++){
            if(wordCounts.get(messageWords[i].toLowerCase()) == null) {
                wordCounts.put(messageWords[i].toLowerCase(),0);
                
            }
                wordCounts.put(messageWords[i].toLowerCase(),wordCounts.get(messageWords[i].toLowerCase()) + 1);
        }

        wordCounts.put("",0);
        wordCounts.put(" ",0);

        String output = "";
        for(String word : wordCounts.keySet()){
            if(wordCounts.get(word) > wordCounts.get(output)){
            output=word;
            
            }
        }
        return output;
    }

    @Override
    public String mostFrequentWordWithCapitalFirstLetter() {
        String message = fileAsString;

        char[] invalid = {'.',',','?','!','"','\'','\\','/','”',';','\t','\n','˜'};
  
        for(char c : invalid){
                message = message.replace(""+c, " ");
        }
        String[] messageWords=message.split(" ");

        LinkedHashMap<String, Integer> wordCounts = new LinkedHashMap<>();
        wordCounts.put("",0);
        
        for(int i = 0; i<messageWords.length;i++){
            if(messageWords[i]!="" && Character.isUpperCase(messageWords[i].charAt(0))){
                
                if(wordCounts.get(messageWords[i]) == null) {
                    wordCounts.put(messageWords[i],0);
                }
                wordCounts.put(messageWords[i],wordCounts.get(messageWords[i]) + 1);
            }
        }

        wordCounts.put("",0);
        wordCounts.put(" ",0);

        String output = "";
        for(String word : wordCounts.keySet()){
            
            if(wordCounts.get(word) > wordCounts.get(output)){
            output=word;
            
            }
        }
        return output;
    }

    @Override
    public char mostFrequentLetter() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int wordCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int characterCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int vowelCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int consonantCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double averageWordLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int sentenceCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double averageWordsPerSentance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int numberOfDifferentWords() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String alphabetizeAllByChar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String alphabetizeAllByWord() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String replaceWordAndPreserveCase(String aLine, String wordOne, String wordTwo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void replaceWordsAndWriteToFile(String[] wordsToBeReplaced, String[] wordsToReplaceWith) {
        // TODO Auto-generated method stub
        
    }
    
}