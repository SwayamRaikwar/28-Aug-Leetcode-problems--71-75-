import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String simplifyPath(String path) {
        // Use Deque as a stack
        Deque<String> stack = new ArrayDeque<>();
        
        // Split path by slashes
        String[] components = path.split("/");
        
        for (String dir : components) {
            // Ignore empty strings and "."
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            }
            
            // If "..", go up one directory level if possible
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                // Push valid directory/file names
                stack.offerLast(dir);
            }
        }
        
        // Construct the simplified canonical path
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        // If the path was empty or resolved to root, return "/"
        return result.length() == 0 ? "/" : result.toString();
    }
}
