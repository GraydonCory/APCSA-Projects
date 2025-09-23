package BookAnalyticsProject;

class Runner {

    public static void main(String[] args) {
        GraydonBookAnalytics analytics = new GraydonBookAnalytics("BookAnalyticsProject/TheRaven.txt","BookAnalyticsProject/output.txt");
        System.out.println(analytics);
        // System.out.print("alphabatized by letter: ");
        // System.out.println(analytics.alphabetizeAllByChar());

        // System.out.print("alphabatized by word: ");
        // System.out.println(analytics.alphabetizeAllByWord());

        //System.out.println(analytics.replaceWordAndPreserveCase("the banAna was a fat bANana","banana","cat"));
        String[] words = {" I ","Door"};
        String[] replacements = {" you ", "cabinet"};
        analytics.replaceWordsAndWriteToFile(words,replacements);

        
    }
}