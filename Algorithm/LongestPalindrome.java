class Solution {
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                cnt++;
            } else {
                set.add(s.charAt(i));
            }
        }
        return set.isEmpty() ? cnt * 2 : cnt * 2 + 1;
    }
}