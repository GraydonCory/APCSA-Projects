import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public abstract class BookAnalytics {

	protected File file;
	protected Scanner scan;
	protected PrintWriter write; 
	
	public BookAnalytics(String aTextFile, String outputFile) {
		file = new File(aTextFile);
		try {
			scan = new Scanner(file);
			write = new PrintWriter(outputFile, "UTF-8");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * This method finds the most frequently occurring word in the file attribute.
	 * Should two words appear the same number of times, the word that reaches the last iteration first in the string should be returned. 
	 * An empty String should return "". 
	 * Strings with varying letter cases should be counted as equivalent. 
	 * The method also takes out common punctuation to ensure that a word is equivalent to a word with punctuation (. , ? ! " ' \ /) in an adjacent char.
	 * @return The word that most frequently is found in the the file attribute.
	 */
	public abstract String mostFrequentWord(); 
	
	/**
	 * This method finds the most frequently occurring word in the file attribute that has a capital first letter.
	 * Note: Do not count instances of a word the immediately are preceded by ". "
	 * Should two words appear the same number of times, the word that reaches the last iteration first in the string should be returned. 
	 * An empty String should return "". 
	 * The method also takes out common punctuation to ensure that a word is equivalent to a word with punctuation (. , ? ! " ' \ /) in an adjacent char.
	 * @return The word that most frequently is found in the txt file.
	 */
	public abstract String mostFrequentWordWithCapitalFirstLetter();
	
	/**
	 * This method finds the most frequently occurring letter in the file attribute.
	 * The method should protect against the space being the most frequent character.
	 * @return The character that most frequently is found in the file attribute.
	 */
	public abstract char mostFrequentLetter(); 
	
	/**
	 * Counts the number of words in the file attribute
	 * @return an int for the total number of words in the file attribute
	 */
	public abstract int wordCount();
	
	/**
	 * Counts the number of characters in the file attribute
	 * Note: spaces count toward the character count. Newlines do not. 
	 * @return an int for the total number of characters in the file attribute
	 */
	public abstract int characterCount();
	
	/**
	 * Counts the number of vowels in the file attribute
	 * Note: Check both cases 
	 * @return an int for the total number of vowels in the file attribute
	 */
	public abstract int vowelCount();
	
	/**
	 * Counts the number of consonants in the file attribute
	 * Note: Check both cases
	 * @return an int for the total number of consonants in the file attribute
	 */
	public abstract int consonantCount();
	
	/**
	 * Finds the average length of all the words in the file attribute
	 * Note: common punctuation (. , ? ! " ' \ /)  does not count toward word length even if next to each other (Example: "word." has a length of 4) 
	 * @return a double representing the average length of all words in the file attribute 
	 */
	public abstract double averageWordLength(); 
	
	/**
	 * Counts the number of sentences in the file attribute
	 * Note: This method should not count ellipses as a sentence break
	 * 		 The method should also take into account proper titles Mr. Mrs. Ms. Dr. Jr. Sr. as not being new sentences
	 *       There are many more exceptions such as units of measure(lb.), country abbreviations(U.S.A.), time (A.M.) but this method does NOT need to cover these. 
	 * @return an int for the total number of sentences in the file attribute
	 */
	public abstract int sentenceCount();
	
	/**
	 * Finds the average number of words per sentence in the file attribute
	 * Note: This method should not count ellipses as a sentence break
	 * 		 The method should also take into account proper titles Mr. Mrs. Ms. Dr. Jr. Sr. as not being new sentences
	 *       There are many more exceptions such as units of measure(lb.), country abbreviations(U.S.A.), time (A.M.) but this method does NOT need to cover these. 
	 * @return a double representing the average number of words per sentence in the entire file attribute 
	 */
	public abstract double averageWordsPerSentance(); 
	
	/**
	 * This method finds the number of different words that are used in the file attribute.
	 * It only counts a word on its first occurrence in the file attribute.
	 * Example: "The cat ate the fish." has 4 different words because "the" occurs twice.  
	 * @return The number of different words in the file attribute 
	 */
	public abstract int numberOfDifferentWords(); 
	
	/**
	 * Takes the entire file attribute and alphabetizes all characters by their ASCII values
	 * @return A String containing all characters in the file attribute in alphabetic order
	 */
	public abstract String alphabetizeAllByChar();
	
	/**
	 * Takes the entire file attribute and alphabetizes all words by their ASCII values
	 * Note: common punctuation (. , ? ! " ' \ /)  does not count toward word length when alphabetizing and should be removed
	 * @return A String containing all words in the file attribute in alphabetic order
	 */
	public abstract String alphabetizeAllByWord();
	
	/**
	 * This method searches through aLine and replaces all instances of wordOne with wordTwo
	 * The method preserves capitalization so if wordOne is found in the String with a capital first letter 
	 *      wordTwo will replace it with a capital first letter
	 * @param aLine - A string to iterate through
	 * @param wordOne - The word to be located in aLine regardless of case
	 * @param wordTwo - The word to be replace wordOne in aLine preserving the case.
	 * @return aLine with all instances of wordOne replaced by wordTwo preserving first letter capitalization
	 */
	public abstract String replaceWordAndPreserveCase(String aLine, String wordOne, String wordTwo); 
	
	 
	/**
	 * This method should return a String analysis of the file
	 * @return A String in the format
	 * "\t" + FileName +  
	 * "\nMost Frequent Word : " + (word) + 
	 * "\nMost Frequent Letter: " + (letter) + 
	 * "\nMost Likely Subject: " + (most frequent word with capital first letter) + 
	 * "\nWord Count: " + (number of words) + 
	 * "\nCharacter Count: " + (Number of characters including spaces) + 
	 * "\nVowel Count: " + (number of vowels) +
	 * "\nConsonant Count: " + (number of Consonants) +
	 * "\nSentence Count: " + (Number of sentences) +
	 * "\nAverage Word Length: " + (average length of words) + 
	 * "\nAverage Words Per Sentence: " + (The average number of words in each sentence) + 
	 * "\nNumber of Different Words Used: " + (Number of different words in document) + 
	 */
	public abstract String toString(); 
	
	/**
	 * This method takes two String arrays of the same length
	 * The method will find and replace words from the file attribute. 
	 * The result will be output using the PrintWriter attribute. 
	 * Every String in the first array should be found and replaced with the matching index from the second array
	 * Key methods to use: scan.hasNextLine()  scan.nextLine();  write.println(aLineToBeWritten); replaceWordAndPreserveCase(param, param, param); 
	 * @param wordsToBeReplaced - the words to be replaced in the file
	 * @param wordsToReplaceWith - the new words that will be placed in the file for the wordsToBeReplaced
	 */
	public abstract void replaceWordsAndWriteToFile(String[] wordsToBeReplaced, String[] wordsToReplaceWith); 
	
	
}
