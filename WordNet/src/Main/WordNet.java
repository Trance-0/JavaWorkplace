//package Main;

import java.util.HashMap;

public class WordNet {
	private final int[] ancestor;
	private final int size;
	private final HashMap<String,Integer> wordmap;
	private final String[] orimap;
// constructor takes the name of the two input files
public WordNet(String synsets, String hypernyms) {
	wordmap=new HashMap<>();
	if(synsets==null||hypernyms==null) {
		throw new IllegalArgumentException();
	}
	String[] temp=synsets.split("/n");
	size=temp.length;
	orimap=new String[size];
	for(int i=0;i<size;i++) {
		String trim=temp[i].substring(temp[i].indexOf(" "), temp[i].indexOf(",", temp[i].indexOf(",")));
		wordmap.put(trim, i);
		orimap[i]=trim;
	}
	ancestor=new int[size];

		ancestor[findAncestor(wordmap.get(hypernyms))]=findAncestor(wordmap.get(synsets));
	}
private int findAncestor(int a) {
		return ancestor[a];
}
// returns all WordNet nouns
public Iterable<String> nouns(){
	return wordmap.keySet();
}
// is the word a WordNet noun?
public boolean isNoun(String word) {
	return wordmap.containsKey(word);
}
// distance between nounA and nounB (defined below)
public int distance(String nounA, String nounB) {
	if(nounA==null||nounB==null||!isNoun(nounA)||!isNoun(nounB)) {
		throw new IllegalArgumentException();
	}
	int[] checked=new int[size];
	int a=wordmap.get(nounA);
	int b=wordmap.get(nounB);
	int note=1;
	while(checked[findAncestor(a)]==0&&checked[findAncestor(b)]==0) {
		a=findAncestor(a);
		b=findAncestor(b);
		checked[a]=note;
		checked[b]=note;
		note++;
	}
	int index=Math.max(checked[findAncestor(a)], checked[findAncestor(b)]);
	return note+index;
}
// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
// in a shortest ancestral path (defined below)
public String sap(String nounA, String nounB) {
	if(nounA==null||nounB==null||!isNoun(nounA)||!isNoun(nounB)) {
		throw new IllegalArgumentException();
	}
	int[] checked=new int[size];
	int a=wordmap.get(nounA);
	int b=wordmap.get(nounB);
	int note=1;
	while(checked[findAncestor(a)]==0&&checked[findAncestor(b)]==0) {
		a=findAncestor(a);
		b=findAncestor(b);
		checked[a]=note;
		checked[b]=note;
		note++;
	}
	int index=Math.max(findAncestor(a),findAncestor(b));
	return orimap[index];
}
// do unit testing of this class
public static void main(String[] args) {
	
}
}
