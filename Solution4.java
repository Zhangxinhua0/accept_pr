import java.util.Arrays;

/**
 * @description:
 * 给定一个无序的数组 nums，返回数组在排序之后，相邻元素之间的最大差值。 
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 */
class Solution4 {
    public int maximumGap(int[] nums) {

        int n = nums.length;
        
        if (n < 2) {
            return 0;
        }
        
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1)); 
        int bucketCount = (maxVal - minVal) / bucketSize + 1;
        
        int[] minBucket = new int[bucketCount];
        int[] maxBucket = new int[bucketCount];
        Arrays.fill(minBucket, Integer.MAX_VALUE);  
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        
        for (int num : nums) {
            int idx = (num - minVal) / bucketSize;
            minBucket[idx] = Math.min(minBucket[idx], num);
            maxBucket[idx] = Math.max(maxBucket[idx], num);
        }
        
        int prevMax = minVal;
        int ret = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) {
                continue;
            }

            ret = Math.max(maxGap, minBucket[i] - prevMax);
            prevMax = maxBucket[i];
        }

        return ret;
    }
}
