//package Main;

public class Outcast {
    private WordNet wn;
    public Outcast(WordNet wordnet) {
        wn=wordnet;
    }// constructor takes a WordNet object
    public String outcast(String[] nouns) {
        int greatestDistance=0;
        String greatest=nouns[0];
        for(int i=0;i<nouns.length;i++){
            int distance=0;
            for (String j: nouns){
                distance+=wn.distance(nouns[i],j);
            }
            if(distance>greatestDistance){
                greatest=nouns[i];
                greatestDistance=distance;
            }
        }
        return greatest;
    }// given an array of WordNet nouns, return an outcast
    public static void main(String[] args) {}// see test client below
}