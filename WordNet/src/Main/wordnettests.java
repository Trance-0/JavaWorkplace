package Main;

import edu.princeton.cs.algs4.Digraph;

public class wordnettests {
        private Digraph graph;
        private int size;
    // constructor takes the name of the two input files
    public wordnettests(String synsets, String hypernyms) {
        if(synsets==null||hypernyms==null) {
            throw new IllegalArgumentException();
        }

    }
    private int findAncestor(int a) {
        return 0;
    }
    // returns all WordNet nouns
    public Iterable<String> nouns(){
        return null;
    }
    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return false;
    }
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if(nounA==null||nounB==null||!isNoun(nounA)||!isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return -1;
    }
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
// in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
       return null;
    }
    // do unit testing of this class
    public static void main(String[] args) {

    }
}
