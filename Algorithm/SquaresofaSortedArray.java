class Solution {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] res = new int[n];
        int l = 0, r = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(A[l]) > Math.abs(A[r])) {
                res[i] = A[l] * A[l];
                l++;
            } else {
                res[i] = A[r] * A[r];
                r--;
            }
        }
        return res;
    }
}