//1.Two Sum

//public class Solution {
//    public int[] twoSum(int[] numbers, int target) {
//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        int[] result = new int[2];
//
//        for (int i = 0; i < numbers.length; i++) {
//            if (map.containsKey(numbers[i])) {
//                int index = map.get(numbers[i]);
//                result[0] = index+1 ;
//                result[1] = i+1;
//                break;
//            } else {
//                map.put(target - numbers[i], i);
//            }
//        }
//
//        return result;
//    }
//}

//2. Binary Search

//class Solution {
//    public int search(int[] nums, int target) {
//        if(nums == null || nums.length == 0)
//            return -1;
//        else
//            return search(nums, 0, nums.length-1, target);
//    }
//
//    private int search(int[] nums, int left, int right, int target){
//        if (left > right)
//            return -1;
//        int mid = left + (right - left)/2;
//        if(nums[mid] == target)
//            return mid;
//        else if(nums[mid] > target)
//            return search(nums, left, mid-1, target);
//        else
//            return search(nums, mid+1, right, target);
//    }
//}

//3. Find Third Max Number

//public class Solution {
//    public int thirdMax(int[] nums) {
//        TreeSet<Integer> set = new TreeSet<>();
//        for(int num : nums) {
//            set.add(num);
//            if(set.size() > 3) {
//                set.remove(set.first());
//            }
//        }
//        return set.size() == 3 ? set.first() : set.last();
//    }
//}

//4. Longest Palindrome

//class Solution {
//    public:
//    int longestPalindrome(string s) {
//
//        int hashtable[128];
//        memset(hashtable, 0, sizeof(hashtable));
//
//        for(char ch : s) {
//            hashtable[ch]++;
//        }
//        int result = 0;
//        bool hasOdd = false;
//        for (int n : hashtable) {
//            if ( n%2 == 0 ) {
//                result += n;
//            } else {
//                result += n -1;
//                hasOdd = true;
//            }
//        }
//
//        return hasOdd ? result + 1 : result;
//    }
//};

//5. Reverse Integer

//class Solution {
//    public int reverse(int x) {
//        int rev = 0;
//        while (x != 0) {
//            int pop = x % 10;
//            x /= 10;
//            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
//            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
//            rev = rev * 10 + pop;
//        }
//        return rev;
//    }
//}

//6. Merge Sorted Array

//public class Solution {
//    public void merge(int A[], int m, int B[], int n) {
//        int i = m - 1;
//        int j = n - 1;
//        int k = m + n - 1;
//
//        while (k >= 0) {
//            if (j < 0 || (i >= 0 && A[i] > B[j]))
//                A[k--] = A[i--];
//            else
//                A[k--] = B[j--];
//        }
//    }
//}

//7. Rotate Array

//public class Solution {
//    public void rotate(int[] nums, int k) {
//        k %= nums.length;
//        reverse(nums, 0, nums.length - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, nums.length - 1);
//    }
//    public void reverse(int[] nums, int start, int end) {
//        while (start < end) {
//            int temp = nums[start];
//            nums[start] = nums[end];
//            nums[end] = temp;
//            start++;
//            end--;
//        }
//    }
//}

//8. Fibonacci Number

//class Solution {
//    public:
//    int fib(int N) {
//        if (N <= 1) return N;
//        return fib(N - 1) + fib(N - 2);
//    }
//};

//9. Squares of a Sorted Array

//class Solution {
//    public int[] sortedSquares(int[] A) {
//        int N = A.length;
//        int[] ans = new int[N];
//        for (int i = 0; i < N; ++i)
//            ans[i] = A[i] * A[i];
//
//        Arrays.sort(ans);
//        return ans;
//    }
//}

//10. Reverse String

//class Solution {
//public String reverseString(String s) {
//    StringBuilder sb = new StringBuilder(s);
//    return sb.reverse().toString();
//    }
//}

//11.First Unique Character in a String

//class Solution {
//    public:
//    int firstUniqChar(string s) {
//        unordered_map<char, int> m;
//        for (char c : s) ++m[c];
//        for (int i = 0; i < s.size(); ++i) {
//            if (m[s[i]] == 1) return i;
//        }
//        return -1;
//    }
//};

//12.Reverse Vowels of a String

//class Solution {
//    public:
//    string reverseVowels(string s) {
//        int left = 0, right = s.size() - 1;
//        while (left < right) {
//            left = s.find_first_of("aeiouAEIOU", left);
//            right = s.find_last_of("aeiouAEIOU", right);
//            if (left < right) {
//                swap(s[left++], s[right--]);
//            }
//        }
//        return s;
//    }
//};

//13. Top K Frequent Elements

//class Solution {
//    public:
//    vector<int> topKFrequent(vector<int>& nums, int k) {
//        unordered_map<int, int> m;
//        priority_queue<pair<int, int>> q;
//        vector<int> res;
//        for (auto a : nums) ++m[a];
//        for (auto it : m) q.push({it.second, it.first});
//        for (int i = 0; i < k; ++i) {
//            res.push_back(q.top().second); q.pop();
//        }
//        return res;
//    }
//};

//14. Middle of the Linked List

//class Solution {
//    public ListNode middleNode(ListNode head) {
//        ListNode[] A = new ListNode[100];
//        int t = 0;
//        while (head.next != null) {
//            A[t++] = head;
//            head = head.next;
//        }
//        return A[t / 2];
//    }
//}

//15. Linked List Cycle

//public boolean hasCycle(ListNode head) {
//        Set<ListNode> nodesSeen = new HashSet<>();
//        while (head != null) {
//        if (nodesSeen.contains(head)) {
//        return true;
//        } else {
//        nodesSeen.add(head);
//        }
//        head = head.next;
//        }
//        return false;
//        }