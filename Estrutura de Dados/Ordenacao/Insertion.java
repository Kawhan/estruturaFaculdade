import java.util.Comparator;
import java.util.Scanner;

public class Insertion{
    private Insertion(){}

    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            for(int j = i; j>0 && less(a[j], a[j-1]);j--){
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        for(int i =lo;i <= hi; i++){
            for(int j = i; j> lo && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator){
        int N = a.length;
        for(int i = 0; i < N; i++){
            for(int j = i; j>0 && less(a[j], a[j-1], comparator);j--){
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }

    public static void sort(Object[] a, int lo, int hi, Comparator comparator){
        for(int i =lo;i <= hi; i++){
            for(int j = i; j> lo && less(a[j], a[j-1], comparator); j--){
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi, comparator);
    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static boolean less(Object v, Object w, Comparator comparator){
        return comparator.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void exch(int[] a, int i, int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length -1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i= lo+1; i <= hi;i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator){
        return isSorted(a, 0, a.length-1, comparator);
    }

    private static boolean isSorted(Object[] a, int lo,int hi, Comparator comparator){
        for(int i=lo+1; i<= hi;i++)
          if(less(a[i], a[i-1], comparator)) return false;  
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
        for(int i=0;i<n;i++){
            a[i] = sc.nextLine();
        }
        Insertion.sort(a);
        show(a);
    }
}