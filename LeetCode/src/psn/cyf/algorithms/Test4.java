package psn.cyf.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test4 {
    public static void main(String[] args) {
        System.out.println(new Test4().findMedianSortedArrays(null,new Integer[]{1,9,10}));
        System.out.println(new Test4().findMedianSortedArrays(null,new Integer[]{1,9}));
        System.out.println(new Test4().findMedianSortedArrays(new Integer[]{1,9,10},null));
        System.out.println(new Test4().findMedianSortedArrays(new Integer[]{1,9},null));

        System.out.println(new Test4().findMedianSortedArrays(new Integer[]{1,9},new Integer[]{4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        return findMedianSortedArrays(toIntegerArr(nums1),toIntegerArr(nums2));
    }
    private Integer[] toIntegerArr(int[] nums){
        if(nums==null){
            return null;
        }
        List<Integer> list=new ArrayList<>(nums.length);
        for(Integer in :nums){
            list.add(in);
        }
        return list.toArray(new Integer[nums.length]);

    }
    public double findMedianSortedArrays(Integer[] nums1, Integer[] nums2) {
        if(nums1==null&&nums2!=null){
           /* System.out.println(nums2.length/2);
            System.out.println(nums2.length/2+1);
            System.out.println(nums2[nums2.length/2]+nums2[nums2.length/2+1]);*/
            return isOddNum(nums2.length)?nums2[nums2.length/2]:(nums2[nums2.length/2-1]+nums2[nums2.length/2])/2.0;
        }else if(nums2==null&&nums1!=null){
            return findMedianSortedArrays(nums2,nums1);
        }
        if(nums1!=null&&nums2!=null){
            List<Integer> list=new ArrayList<>(nums1.length+nums2.length);
            for (Integer in:nums1){
                list.add(in);
            }
            for (Integer in:nums2){
                list.add(in);
            }
            Collections.sort(list);
            return findMedianSortedArrays(list.toArray(new Integer[0]),null);
        }

        return 0;
    }

    /**
     * @return 判断是否是奇数
     */
    public boolean isOddNum(int num){
        return num%2!=0;
    }

    /**
     * 判断是否是偶数
     * @param num
     * @return
     */
    public boolean isEvenNum(int num){
        return num%2==0;
    }
}
