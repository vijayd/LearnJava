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
        // ListIterator allows the iterator to move both forward and backward
        //   using previous() and next()
        ListIterator<Integer> it1 = l1.listIterator();
        ListIterator<Integer> it2 = l2.listIterator();
        while (it1.hasNext() && it2.hasNext() ) {
            Integer j = it1.next();
            Integer k = it2.next();
               
            if (j.intValue() <= k.intValue()){
               ret.add(j);
               it2.previous(); //revert back to previous position
            }
            else {
                ret.add(k);
                it1.previous(); //revert back to previous position
            }
        }   
        while (it1.hasNext()) { 
            ret.add(it1.next());
        }
        while (it2.hasNext()) {
            ret.add(it2.next());
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
