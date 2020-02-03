package sort;

import SelectionSort.SortTestHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shixinpeng
 * @description mergeSort O(㏒n)
 * @ClassName: MergeSortDemo
 * @date 2020/1/12
 *
 */
public class MergeSortDemo {

    public static void mergeSort(int[] a,int n){
        mergeSortSub(a,0,n-1);
    }

    /**
     * merge sub sort
     * @param a array
     * @param p start
     * @param r end
     */
    private static void mergeSortSub(int[] a,int p, int r){

        // Basic conditions of recursion
       if (p>=r) return;

       // get mid seat q from p to r
        int q = (p+r)/2;



        mergeSortSub(a,p,q);
        mergeSortSub(a,q+1,r);
        // Divide and conquer recursion
        merge(a,p,q,r);
        System.out.printf("\np=%d q=%d r=%d",p,q,r);
        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    }

    /**
     * merge two sub array
     * @param a source array
     * @param p
     * @param q
     * @param r
     */
    private static void merge(int[] a,int p, int q, int r){
        // use temp array save sorted data
        int[] temp = new int[a.length];

        int i = p, j = q+1, k = 0;

        // merge two array
        while (i <= q && j <= r){
            if (a[i]<=a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }

        // one of two arrays end
        while (i<=q){
            temp[k++] = a[i++];
        }

        while (j<=r){
            temp[k++] = a[j++];
        }

        // copy
        for (int x = p,  t = 0; x <= r ; x++) {
            a[x] = temp[t++];
        }

    }


    private static void merge2(int[] a,int p, int q, int r){
        // use temp array save sorted data
        int[] temp = new int[r-p+1];

        int i = p, j = q+1, k = 0;

        // merge two array
        while (i <= q && j <= r){
            if (a[i]<=a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }
        int start = i ,end = q;
        if (j<=r) {
            start = j;
            end=r;
        }
        int length = end-start;

        // set max array
        for (;start<=end;start++){
            a[r-length] = a[start];
        }

        // copy
        for (int x = p,  t = 0; x <= (r-length) ; x++) {
            a[x] = temp[t++];
        }

    }

    public static void main(String[] args) {
        int[] a = SortTestHelper.generateRandomArray(8, 0, 9);

        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));

        mergeSort(a,8);
        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    }
}
