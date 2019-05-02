class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (dict[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}