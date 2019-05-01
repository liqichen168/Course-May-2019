class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int[] res = new int[]{map.get(target - nums[i]), i};
                return res;
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}