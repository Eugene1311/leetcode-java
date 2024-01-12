package rotate_string;

public class Solution {
  public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }
    if (s.isEmpty()) {
      return true;
    }

    int sPointer = 0;
    String doubledS = s.concat(s);
    char firstGoalChar = goal.charAt(0);

    for (int i = 0; i < goal.length(); i++) {
      if (doubledS.charAt(sPointer) != goal.charAt(i)) {
        while (sPointer < doubledS.length() && doubledS.charAt(sPointer) != firstGoalChar) {
          sPointer++;
        }
        i = -1;
      } else {
        sPointer++;
      }

      if (sPointer >= doubledS.length()) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.rotateString("abcde", "cdeab"));
    System.out.println(solution.rotateString("abcde", "abced"));
    System.out.println(solution.rotateString("bbbacddceeb", "ceebbbbacdd"));
  }
}
