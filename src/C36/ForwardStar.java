package C36;

import java.util.LinkedList;

public class ForwardStar {
    public class edge {
        private int to, weight, nexthead;
    }

    private int[] head;
    private edge[] stars;
    private int count;

    public ForwardStar(int size) {
        head = new int[size];
        for (int i = 0; i < size; i++) {
            head[size] = -1;
        }
        stars = new edge[size];
        count = 0;
    }

    public void add(int from, int to, int weight) {
        edge tempEdge = new edge();
        tempEdge.to = to;
        tempEdge.weight = weight;
        tempEdge.nexthead = head[from];
        head[from] = count;
        stars[count] = tempEdge;
        count++;
    }

    public LinkedList<int[]> get(int from) {
        LinkedList<int[]> result = new LinkedList<int[]>();
        for (int i = head[from]; i != -1; i = stars[i].nexthead) {
            int[] temp = new int[3];
            temp[0] = from;
            temp[1] = stars[i].to;
            temp[2] = stars[i].weight;
            result.add(temp);
        }
        return result;
    }
}
