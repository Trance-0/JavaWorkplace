package Main;
import edu.princeton.cs.algs4.*;
public class SAP {
	private Digraph graph;
	private int size;
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		graph=G;
		size=G.V();
	}
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w){
		int l=-1;
		int[] checked=new int[size];
		int note=1;
		while(graph.indegree(v)!=0&&graph.indegree(w)!=0){
			if(){

			}
			checked[]
		}
		return v;
	}
	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w){
		return v;
	}
	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		return 0;
	}
	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		return 0;
	}
	// do unit testing of this class
	public static void main(String[] args){}
	}