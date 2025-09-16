package BookAnalyticsProject;

class Runner {

    public static void main(String[] args) {
        GraydonBookAnalytics analytics = new GraydonBookAnalytics("BookAnalyticsProject/TheRaven.txt","output.txt");
        System.out.print("most frequent word: ");
        System.out.println(analytics.mostFrequentWord());
        
        System.out.print("most frequent word starting with capital: ");
        System.out.println(analytics.mostFrequentWordWithCapitalFirstLetter());
        
        System.out.print("most frequent letter: ");
        System.out.println(analytics.mostFrequentLetter() );

        System.out.print("word count: ");
        System.out.println(analytics.wordCount());
    }
}