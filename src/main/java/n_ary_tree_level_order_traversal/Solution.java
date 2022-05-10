package n_ary_tree_level_order_traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Node> current = List.of(root);
        List<Integer> levelValues = getLevelValues(current);
        result.add(levelValues);
        while (!levelValues.isEmpty()) {
            current = current.stream()
                    .flatMap(nodes -> nodes.children.stream())
                    .collect(Collectors.toList());
            levelValues = getLevelValues(current);
            result.add(levelValues);
        }
        return result.stream().filter(nodes -> !nodes.isEmpty())
                .collect(Collectors.toList());
    }

    private List<Integer> getLevelValues(List<Node> nodes) {
        return nodes.stream()
                .filter(Objects::nonNull)
                .map(node -> node.val)
                .collect(Collectors.toList());
    }
}
