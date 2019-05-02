class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        char[] cs = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !vowels.contains(cs[l])) {
                l++;
            }
            while (l < r && !vowels.contains(cs[r])) {
                r--;
            }
            if (l < r) {
                char temp = cs[l];
                cs[l] = cs[r];
                cs[r] = temp;
                l++;
                r--;
            }
        }
        return new String(cs);
    }
}