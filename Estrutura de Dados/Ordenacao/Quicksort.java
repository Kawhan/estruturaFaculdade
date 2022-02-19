import java.util.Random;
import java.util.Scanner;

public class Quicksort {

    private Quicksort(){}
    private static Random gerador;

    public static void sort(Comparable[] a){
        gerador = new Random();
        sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int j = rand_partition(a, lo, hi);
        sort(a,lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }

    private static int rand_partition(Comparable[] a, int lo,int hi){
        int n = hi-lo;
        int i = gerador.nextInt(n) + lo;

        exch(a, i, hi);
        return partition(a, lo, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i =lo-1;
        int j = lo;
        Comparable v = a[hi];
        for(; j<hi;j++){
            if(less(a[j], v)){
                ++i;
                exch(a,i,j);
            }
        }
        exch(a, hi, i+1);
        return i+1;
    }

    public static Comparable select(Comparable[] a, int k){
        if(k<0 || k >= a.length){
            throw new IllegalArgumentException("index is not between 0 and "+a.length+ ": "+k);  
        }
        int lo = 0, hi = a.length -1;
        while(hi >lo){
            int i = rand_partition(a, lo, hi);
            if(i >k) hi = i-1;
            else if( i< k) lo = i+1;
            else return a[i];
        }
        return a[lo];
    }

    private static boolean less(Comparable v, Comparable w){
        if(v == w) return false;
        return v.compareTo(w) <0;
    }

    private static void exch(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length-1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i=lo+1; i<=hi; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Comparable[] a){
        for(int i=0; i< a.length; i++){
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] a = new String[n];
        for(int i=0; i<n;i++){
            a[i] = sc.nextLine();
        }
        Quicksort.sort(a);
        show(a);
        assert isSorted(a);
    }
}
