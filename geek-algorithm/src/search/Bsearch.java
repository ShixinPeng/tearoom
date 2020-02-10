package search;

public class Bsearch {

    /**
     * 不存在重复值的二分查找
     * @param a
     * @param n
     * @param value
     * @return
     */
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

    /**
     * 1.查找第一个值等于给定值的元素
     * 2.查找最后一个值等于给定值的元素
     * 3.查找第一个大于等于给定值的元素
     * 4.查找最后一个小于等于给定值的元素
     *
     */

    public static int bSearch1(int[] a,int n,int value){
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>2);
            if (a[mid]>value){
                high = mid - 1;
            }else if (a[mid]<value){
                low = mid+1;
            }else {
                if ((mid==0)||a[mid - 1] != value){
                    // 如果mid不为其实元素，则前一个值如果不等于当前值，则判断为第一个给定值
                    return mid;
                }else {
                    high = mid-1;
                }
            }
        }

        return -1;
    }

    public static int bSearch2(int[] a,int n,int value){

        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>2);
            if (a[mid]>value){
                high = mid - 1;
            } else if (a[mid] < value){
                low = mid + 1;

            }else {
                if ((mid == n-1)||(a[mid + 1] != value)){
                    return mid;
                }else {
                    low = mid+1;
                }

            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bSearch3(int[] a,int n,int value) {

        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high - low)>>1);
            if (a[mid] >= value){
                if ((mid == 0)||(a[mid-1]<value)){
                    return mid;
                }else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }

    /**
     * 查找最后一个小于等于给定元素的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bSearch4(int[] a,int n,int value) {
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high - low)>>1);
            if (a[mid] > value){
                high = mid - 1;
            }else {
                if ((mid == n - 1)||(a[mid + 1] > value)){
                    return mid;
                }else {
                    low = mid + 1;
                }
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
