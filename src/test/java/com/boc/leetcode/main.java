package com.boc.leetcode;


/**
 * Created by YinChong on 2018/9/4.
 */

public class main{
    public static void main(String[] args){
        Solution solution = new Solution();
        int [] nums = {2,7,2,7,3};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        for (int i = 0; i < result.length; i++){
            System.out.println(result[i]);

        }
    }
}

