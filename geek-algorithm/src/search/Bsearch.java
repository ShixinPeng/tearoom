package search;

public class Bsearch {

    public static int simpleBSearch(int[] a,int n,int value){
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>1);
            if (a[mid]==value){
                return mid;
            }else if (a[mid]<value){
                low = mid+1;
            }else {
                high = mid-1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {


        int[] a= new int[]{1,2,3,4,5,6,7,8,9,12,18};
        int i = simpleBSearch(a, a.length, 3);
        System.out.println(i);
    }
}
