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
        String message = fileAsString;
        LinkedHashMap<Character, Integer> letterCounts = new LinkedHashMap<>();
        letterCounts.put(' ',0);
        for(int i = 0; i<message.length();i++){
            if(letterCounts.get(Character.toLowerCase(message.charAt(i))) == null) {
                letterCounts.put(Character.toLowerCase(message.charAt(i)),1);
            }
            else if(message.charAt(i) != ' '){
                letterCounts.put(Character.toLowerCase(message.charAt(i)),letterCounts.get(Character.toLowerCase(message.charAt(i)))+1);
                
            }
        }
        char output = ' ';
        for(char letter : letterCounts.keySet()){
            if(letterCounts.get(letter)>letterCounts.get(output)){
            output=letter;
            }
        }
        return output;
    }
    
    @Override
    public int wordCount() {
        String message = fileAsString;
        char[] invalid = {'.',',','?','!','"','\'','\\','/','”',';','\t','\n','˜'};
  
        for(char c : invalid){
                message = message.replace(""+c, "");
        }
        String[] messageWords=message.split(" ");
        
        int count=0;
        for(int i = 0; i<messageWords.length;i++){
            if(messageWords[i].strip()!="" ){
                count+=1;
            }
        }
        
        return count;
    }

    @Override
    public int characterCount() {
        String message = fileAsString;
        message.replaceAll("\n","");
        return message.length();

    }

    @Override
    public int vowelCount() {
        String message = fileAsString.toLowerCase();
        char[] vowels = {'a','e','i','o','u','y'};
        int count = 0;
        for(int i=0;i<message.length();i++){
            for(char vowel : vowels){
                if(message.charAt(i) == vowel){
                    count+=1;
                }
            }
        }
        return count;
    }

    @Override
    public int consonantCount() {
        String message = fileAsString.toLowerCase();
        char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z'};
        int count = 0;
        for(int i=0;i<message.length();i++){
            for(char consonant : consonants){
                if(message.charAt(i) == consonant){
                    count+=1;
                }
            }
        }
        return count;
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