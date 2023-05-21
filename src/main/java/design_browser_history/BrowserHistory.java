package design_browser_history;

import java.util.ArrayList;
import java.util.List;

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 *
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 *
 * void visit(string url) Visits url from the current page. It clears up all the forward history.
 *
 * string back(int steps) Move steps back in history.
 * If you can only return x steps in the history and steps > x, you will return only x steps.
 * Return the current url after moving back in history at most steps.
 *
 * string forward(int steps) Move steps forward in history.
 * If you can only forward x steps in the history and steps > x, you will forward only x steps.
 * Return the current url after forwarding in history at most steps.
 */
class BrowserHistory {
    private final List<String> history = new ArrayList<>();
    private int current = -1;

    public BrowserHistory(String homepage) {
        history.add(homepage);
        current++;
    }

    public void visit(String url) {
        history.subList(current + 1, history.size()).clear();
        history.add(url);
        current++;
    }

    public String back(int steps) {
        current = Math.max(current - steps, 0);
        return history.get(current);
    }

    public String forward(int steps) {
        current = Math.min(current + steps, history.size() - 1);
        return history.get(current);
    }

    public static void main(String[] args) {
//["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
//[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2));
        System.out.println(browserHistory.back(2));
        System.out.println(browserHistory.back(7));
    }
}
