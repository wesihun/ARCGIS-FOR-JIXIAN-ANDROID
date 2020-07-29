package com.winto.develop.ThreeTones.util;

import com.winto.develop.ThreeTones.bean.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {

    /**
     * 传入node  返回排序后的Node
     *
     * @param datas
     * @param defaultExpandLevel
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T, B> List<Node<T, B>> getSortedNodes(List<Node<T, B>> datas, int defaultExpandLevel) {
        List<Node<T, B>> result = new ArrayList<>();
        // 设置Node间父子关系
        List<Node<T, B>> nodes = convetData2Node(datas);
        // 拿到根节点
        List<Node<T, B>> rootNodes = getRootNodes(nodes);
        // 排序以及设置Node间关系
        for (Node<T, B> node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 过滤出所有可见的Node
     *
     * @param nodes
     * @return
     */
    public static <T, B> List<Node<T, B>> filterVisibleNode(List<Node<T, B>> nodes) {
        List<Node<T, B>> result = new ArrayList<>();

        for (Node<T, B> node : nodes) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 设置Node间，父子关系;让每两个节点都比较一次，即可设置其中的关系
     */
    private static <T, B> List<Node<T, B>> convetData2Node(List<Node<T, B>> nodes) {

        for (int i = 0; i < nodes.size(); i++) {
            Node<T, B> n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node<T, B> m = nodes.get(j);
                if (m.getpId() instanceof String) {
                    if (m.getpId().equals(n.getId())) {
                        n.getChildren().add(m);
                        m.setParent(n);
                    } else if (m.getId().equals(n.getpId())) {
                        m.getChildren().add(n);
                        n.setParent(m);
                    }
                } else {
                    if (m.getpId() == n.getId()) {
                        n.getChildren().add(m);
                        m.setParent(n);
                    } else if (m.getId() == n.getpId()) {
                        m.getChildren().add(n);
                        n.setParent(m);
                    }
                }
            }
        }
        return nodes;
    }

    private static <T, B> List<Node<T, B>> getRootNodes(List<Node<T, B>> nodes) {
        List<Node<T, B>> root = new ArrayList<>();
        for (Node<T, B> node : nodes) {
            if (node.isRoot())
                root.add(node);
        }
        return root;
    }

    /**
     * 把一个节点上的所有的内容都挂上去
     */
    private static <T, B> void addNode(List<Node<T, B>> nodes, Node<T, B> node, int defaultExpandLevel, int currentLevel) {
        nodes.add(node);

        if (node.isNewAdd && defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }

        if (node.isLeaf())
            return;
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(nodes, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
        }
    }

    /**
     * 设置节点的图标
     *
     * @param node
     */
    private static<T, B> void setNodeIcon(Node<T, B> node) {
        if (node.getChildren().size() > 0 && node.isExpand()) {
            node.setIcon(node.iconExpand);
        } else if (node.getChildren().size() > 0 && !node.isExpand()) {
            node.setIcon(node.iconNoExpand);
        } else {
            node.setIcon(-1);
        }
    }

}