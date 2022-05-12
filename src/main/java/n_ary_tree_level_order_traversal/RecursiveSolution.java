package n_ary_tree_level_order_traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecursiveSolution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return List.of();
        }

        return getLevelValues(List.of(root), new ArrayList<>());
    }

    private List<List<Integer>> getLevelValues(List<Node> nodes, List<List<Integer>> valuesByLevel) {
        var levelValues = nodes.stream()
                .map(node -> node.val)
                .collect(Collectors.toList());
        if (levelValues.isEmpty()) {
            return valuesByLevel;
        }
        valuesByLevel.add(levelValues);
        return getLevelValues(
                nodes.stream()
                        .filter(node -> Objects.nonNull(node.children))
                        .flatMap(node -> node.children.stream())
                        .collect(Collectors.toList()),
                valuesByLevel
        );
    }
}
