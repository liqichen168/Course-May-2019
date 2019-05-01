class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = nums1.length - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[cur--] = nums1[i--];
            } else {
                nums1[cur--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[cur--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[cur--] = nums2[j--];
        }
    }
}