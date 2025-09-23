package BookAnalyticsProject;

class Runner {

    public static void main(String[] args) {
        GraydonBookAnalytics analytics = new GraydonBookAnalytics("BookAnalyticsProject/TheRaven.txt","BookAnalyticsProject/output.txt");
        // System.out.println(analytics);
        // System.out.print("alphabatized by letter: ");
        // System.out.println(analytics.alphabetizeAllByChar());

        // System.out.print("alphabatized by word: ");
        // System.out.println(analytics.alphabetizeAllByWord());
        
        // System.out.println("\nthe CAt was a fat cat, it might be Cat-astrophic");
        // System.out.println(analytics.replaceWordAndPreserveCase("the CAt was a fat cat, it might be Cat-astrophic","cat","banana"));
        
        // String[] words = {" I ","Door"};
        // String[] replacements = {" you ", "cabinet"};
        // analytics.replaceWordsAndWriteToFile(words,replacements);

        
    }
}