package BookAnalyticsProject;

class Runner {

    public static void main(String[] args) {
        GraydonBookAnalytics analytics = new GraydonBookAnalytics("BookAnalyticsProject/TheRaven.txt","BookAnalyticsProject/output.txt");
        System.out.print("most frequent word: ");
        System.out.println(analytics.mostFrequentWord());
        
        System.out.print("most frequent word starting with capital: ");
        System.out.println(analytics.mostFrequentWordWithCapitalFirstLetter());
        
        System.out.print("most frequent letter: ");
        System.out.println(analytics.mostFrequentLetter() );

        System.out.print("word count: ");
        System.out.println(analytics.wordCount());

        System.out.print("character count: ");
        System.out.println(analytics.characterCount());

        System.out.print("vowel count: ");
        System.out.println(analytics.vowelCount());

        System.out.print("consonant count: ");
        System.out.println(analytics.consonantCount());

        System.out.print("average word length: ");
        System.out.println(analytics.averageWordLength());

        System.out.print("sentance count: ");
        System.out.println(analytics.sentenceCount());

        System.out.print("average words per sentance: ");
        System.out.println(analytics.averageWordsPerSentance());

        System.out.print("unique word count: ");
        System.out.println(analytics.numberOfDifferentWords());

        System.out.print("alphabatized: ");
        System.out.println(analytics.alphabetizeAllByChar());

        

        
    }
}