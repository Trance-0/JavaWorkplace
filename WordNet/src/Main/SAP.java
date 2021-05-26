//package Main;

import edu.princeton.cs.algs4.Digraph;
public class SAP {
	private final Digraph graph;
	private final int size;
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		graph=G;
		size=G.V();
	}
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w){
		int[] checked=new int[size];
		int note=0;
		while(graph.indegree(v)!=0&&graph.indegree(w)!=0){
			if(checked[v]!=0||checked[w]!=0){
				return note+Math.max(checked[v],checked[w]);
			}else{
				checked[v]=note;
				checked[w]=note;
			}
			note++;
			v=Integer.parseInt(graph.adj(v).toString());
			v=Integer.parseInt(graph.adj(v).toString());
		}
		return -1;
	}
	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w){
		boolean[] checked=new boolean[size];
		while(graph.indegree(v)!=0&&graph.indegree(w)!=0){
			if(checked[v]||checked[w]){
				if(checked[v]){
					return v;
				}
				if(checked[w]){
					return w;
				}
			}else{
				checked[v]=true;
				checked[w]=true;
			}
			v=Integer.parseInt(graph.adj(v).toString());
			v=Integer.parseInt(graph.adj(v).toString());
		}
		return -1;
	}
	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		int shortest=Integer.MAX_VALUE;
		for(int i:v) {
			for (int j : w) {
				if (length(i, j) < shortest) {
					shortest=length(i, j);
				}
			}
		}
		if(shortest!=Integer.MAX_VALUE){
			return shortest;
		}
		return -1 ;
	}
	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		int length=length(v,w);
		for(int i:v){
			for(int j:w){
				if(length(i,j)==length){
					return ancestor(i,j);
				}
			}
		}
		return -1;
	}
	// do unit testing of this class
	public static void main(String[] args){}
	}