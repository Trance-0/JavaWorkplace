import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.PriorityQueue;

public class SeamCarver {
    private Picture pic;
    private PriorityQueue<Integer> xdir;
    private PriorityQueue<Integer> ydir;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture){
    pic=picture;
    }

    // current picture
    public Picture picture(){
        return pic;
    }

    // width of current picture
    public int width(){
        return pic.width();
    }

    // height of current picture
    public int height(){
        return pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y){
        double e=0;
        if(isBorder(x,y)){
            return 1000;
        }
        else{
            Color down=pic.get(x,y+1);
            Color up=pic.get(x,y-1);
            Color left=pic.get(x-1,y);
            Color right=pic.get(x+1,y);
            double part1=Math.pow(down.getRed()-up.getRed(),2)+Math.pow(down.getGreen()-up.getGreen(),2)+Math.pow(down.getBlue()-up.getBlue(),2);
            double part2=Math.pow(left.getRed()-right.getRed(),2)+Math.pow(left.getGreen()-right.getGreen(),2)+Math.pow(left.getBlue()-right.getBlue(),2)
            return Math.sqrt(part1+part2);
        }
    }
    private boolean isBorder(int x,int y){
    return x==width()||x==0||y==height()||y==0;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam(){
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam(){
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam){}

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam){}

    //  unit testing (optional)
    public static void main(String[] args){}

}