class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(new ArrayList<>(), 0, target, candidates, res);
        return res;
    }
    
    private void dfs(List<Integer> path, int index, int target, int[] candidates, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target < candidates[i]) {
                return;
            }
            path.add(candidates[i]);
            dfs(path, i, target - candidates[i], candidates, res);
            path.remove(path.size() - 1);
        }
    }
}