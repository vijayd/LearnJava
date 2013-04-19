/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.util.*;
import java.io.*;

/**
 *
 * @author durairaj
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void printAList(ArrayList<Integer> l){
        System.out.print("Array = ");
        for (Integer i : l) {
            System.out.print(i+" ");
        }
        System.out.print("\n");
    }
    public static ArrayList<Integer> Merge(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        int len = l1.size()+l2.size();
        int pos1 = 0;
        int pos2 = 0;
        boolean l1end = false;
        boolean l2end = false;
        Integer j = new Integer(0);
        Integer k = new Integer(0);
        for (int i=0; i<len; i++){
            if (!l1end)
                j = l1.get(pos1);
            if (!l2end)
                k = l2.get(pos2);
            
            if (l1end) {
                ret.add(k);
                pos2++;
                continue;
            }
            if (l2end) {
                ret.add(j);
                pos1++;
                continue;
            }
            if (j.intValue() <= k.intValue()){
                ret.add(j);
                pos1++;
                if (pos1 >= l1.size())
                    l1end = true;
            } else {
                ret.add(k);
                pos2++;
                if (pos2 >= l2.size())
                    l2end = true;
            }
        }
              
        return ret;
    }
    public static ArrayList<Integer> MergeSplit(ArrayList<Integer> l, int start, int end){
        //System.out.println("start = "+start+" end = "+end);
        ArrayList<Integer> ret;
        if (end == start) { //Termination case 1 (only one element)
            ret = new ArrayList<Integer>();
            ret.add(l.get(start));
            return ret;
        }
        if ((end-start)==1) { // Termination case 2 (two elements)
            Integer i = l.get(start);
            Integer j = l.get(end);
            ret = new ArrayList<Integer>();
            if (i.intValue() <= j.intValue()) {
                ret.add(i);
                ret.add(j);
            }
            else {
                ret.add(j);
                ret.add(i);
            }
            return ret;
        }
        // First half of array
        ArrayList<Integer> l1 = MergeSplit(l,start, (end-start)/2+start); 
        // Second half of array
        ArrayList<Integer> l2 = MergeSplit(l, ((end-start)/2)+1+start, end);
        // Merge sorted 1st and 2nd half
        ret = Merge(l1,l2);
        return ret;
    }
    
    public static ArrayList<Integer> MergeSort(ArrayList<Integer> l) {
        int len = l.size();
        if (len <= 1) 
            return l;
        //Split array into 2
        ArrayList<Integer> l1 = MergeSplit(l,0,(len-1)/2); 
        ArrayList<Integer> l2 = MergeSplit(l,((len-1)/2)+1, len-1);
        ArrayList<Integer> ret = Merge(l1,l2); // Merge results
        printAList(ret);
        return ret;
    }
    
   
    public static void main(String[] args) {
   
       ArrayList<Integer> MS = new ArrayList<Integer>();
       
       if (args.length > 0) {
           System.out.println("arglen = "+args.length);
           System.out.println("arg 0 = "+args[0]);
           
           File file = new File(args[0]);
           try (FileInputStream fis = new FileInputStream(file)) {
               Scanner s = new Scanner(fis);
               while(s.hasNextInt()) {
                   int i = s.nextInt();
                   MS.add(new Integer(i));
               }
           }
           catch (IOException e){
               System.out.println("Input file does not exist");
           }
       }
       
       printAList(MS);
       ArrayList<Integer> l = MergeSort(MS); 
    }
}
