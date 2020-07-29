package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.winto.develop.ThreeTones.bean.Node;
import com.winto.develop.ThreeTones.util.TreeHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeListViewAdapter<T, B> extends BaseAdapter {

    Context mContext;
    /**
     * 存储所有可见的Node
     */
    private List<Node<T, B>> mNodes;
    LayoutInflater mInflater;
    /**
     * 存储所有的Node
     */
    private List<Node<T, B>> mAllNodes;
    /**
     * 默认不展开
     */
    private int defaultExpandLevel;
    /**
     * 展开与关闭的图片
     */
    private int iconExpand, iconNoExpand;

    TreeListViewAdapter(Context context, List<Node<T, B>> dataList, int defaultExpandLevel, int iconExpand, int iconNoExpand) {

        this.iconExpand = iconExpand;
        this.iconNoExpand = iconNoExpand;

        for (Node<T, B> node : dataList) {
            node.getChildren().clear();
            node.iconExpand = iconExpand;
            node.iconNoExpand = iconNoExpand;
        }

        this.defaultExpandLevel = defaultExpandLevel;
        mContext = context;
        /*
          对所有的Node进行排序
         */
        mAllNodes = TreeHelper.getSortedNodes(dataList, defaultExpandLevel);
        /*
          过滤出可见的Node
         */
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);
        /*
          设置节点点击时，可以展开以及关闭；并且将ItemClick事件继续往外公布
         */
        /*mTree.setOnItemClickListener((parent, view, position, id) -> {
            expandOrCollapse(position);
            if (onTreeNodeClickListener != null) {
                onTreeNodeClickListener.onClick(mNodes.get(position), position);
            }
        });*/
    }

    /**
     * @param defaultExpandLevel 默认展开几级树
     */
    TreeListViewAdapter(Context context, List<Node<T, B>> nodeList, int defaultExpandLevel) {
        this(context, nodeList, defaultExpandLevel, -1, -1);
    }

    /**
     * 清除掉之前数据并刷新  重新添加
     */
    public void addDataAll(List<Node<T, B>> nodeList, int defaultExpandLevel) {
        mAllNodes.clear();
        addData(-1, nodeList, defaultExpandLevel);
    }

    /**
     * 在指定位置添加数据并刷新 可指定刷新后显示层级
     */
    private void addData(int index, List<Node<T, B>> nodeList, int defaultExpandLevel) {
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(index, nodeList);
    }

    /**
     * 在指定位置添加数据并刷新
     */
    public void addData(int index, List<Node<T, B>> nodeList) {
        notifyData(index, nodeList);
    }

    /**
     * 添加数据并刷新
     */
    public void addData(List<Node<T, B>> nodeList) {
        addData(nodeList, defaultExpandLevel);
    }

    /**
     * 添加数据并刷新 可指定刷新后显示层级
     */
    private void addData(List<Node<T, B>> nodeList, int defaultExpandLevel) {
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(-1, nodeList);
    }

    /**
     * 添加数据并刷新
     */
    public void addData(Node<T, B> node) {
        addData(node, defaultExpandLevel);
    }

    /**
     * 添加数据并刷新 可指定刷新后显示层级
     */
    public void addData(Node<T, B> node, int defaultExpandLevel) {
        List<Node<T, B>> nodes = new ArrayList<>();
        nodes.add(node);
        this.defaultExpandLevel = defaultExpandLevel;
        notifyData(-1, nodes);
    }

    /**
     * 移除node
     */
    public void removeData(Node<T, B> node) {
        if (node == null) {
            return;
        }
        removeDeleteNode(node);
        for (Node<T, B> n : mAllNodes) {
            n.getChildren().clear();
        }
        mAllNodes = TreeHelper.getSortedNodes(mAllNodes, defaultExpandLevel);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        //刷新数据
        notifyDataSetChanged();
    }

    /**
     * 批量移除node
     */
    public void removeData(List<Node<T, B>> nodeList) {
        if (nodeList == null || nodeList.isEmpty()) {
            return;
        }
        for (Node<T, B> node : nodeList) {
            removeDeleteNode(node);
        }
        for (Node<T, B> n : mAllNodes) {
            n.getChildren().clear();
        }
        mAllNodes = TreeHelper.getSortedNodes(mAllNodes, defaultExpandLevel);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        //刷新数据
        notifyDataSetChanged();
    }

    private void removeDeleteNode(Node<T, B> node) {
        if (node == null) {
            return;
        }
        List<Node<T, B>> children = node.getChildren();
        if (children != null && !children.isEmpty()) {
            for (Node<T, B> n : children) {
                removeDeleteNode(n);
            }
        }
        mAllNodes.remove(node);
    }


    /**
     * 刷新数据
     */
    private void notifyData(int index, List<Node<T, B>> mListNodes) {
        for (int i = 0; i < mListNodes.size(); i++) {
            Node<T, B> node = mListNodes.get(i);
            node.getChildren().clear();
            node.iconExpand = iconExpand;
            node.iconNoExpand = iconNoExpand;
        }
        for (int i = 0; i < mAllNodes.size(); i++) {
            Node<T, B> node = mAllNodes.get(i);
            node.getChildren().clear();
            node.isNewAdd = false;
        }
        if (index != -1) {
            mAllNodes.addAll(index, mListNodes);
        } else {
            mAllNodes.addAll(mListNodes);
        }
        /*
          对所有的Node进行排序
         */
        mAllNodes = TreeHelper.getSortedNodes(mAllNodes, defaultExpandLevel);
        /*
          过滤出可见的Node
         */
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        //刷新数据
        notifyDataSetChanged();
    }

    /**
     * 获取排序后所有节点
     */
    public List<Node<T, B>> getAllNodes() {
        if (mAllNodes == null)
            mAllNodes = new ArrayList<>();
        return mAllNodes;
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     */
    public void expandOrCollapse(int position) {
        Node<T, B> n = mNodes.get(position);

        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    public void expandNode(Node<T, B> n, boolean b) {
        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                n.setExpand(b);
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    @Override
    public int getCount() {
        return mNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node<T, B> node = mNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        // 设置内边距
        convertView.setPadding(node.getLevel() * 50, 2, 50, 2);
        return convertView;
    }

    /**
     * 设置多选
     */
    public void setChecked(final Node<T, B> node, boolean checked) {
        node.setChecked(checked);
        setChildChecked(node, checked);
        if (node.getParent() != null) {
            setNodeParentChecked(node.getParent(), checked);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置是否选中
     */
    public void setChildChecked(Node<T, B> node, boolean checked) {
        if (!node.isLeaf()) {
            node.setChecked(checked);
            for (Node<T, B> childrenNode : node.getChildren()) {
                setChildChecked(childrenNode, checked);
            }
        } else {
            node.setChecked(checked);
        }
    }

    public void setLeafNodeChecked(Node<T, B> node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < getAllNodes().size(); i++) {
            Node<T, B> n = getAllNodes().get(i);
            n.setChecked(n.getId() == node.getId());
        }
        notifyDataSetChanged();
    }

    private void setNodeParentChecked(Node<T, B> node, boolean isCheck) {
        if (isCheck) {
            node.setChecked(true);
            if (node.getParent() != null) {
                setNodeParentChecked(node.getParent(), true);
            }
        } else {
            List<Node<T, B>> children = node.getChildren();
            boolean isChecked = false;
            for (Node<T, B> child : children) {
                if (child.isChecked()) {
                    isChecked = true;
                    break;
                }
            }
            //如果所有自节点都没有被选中 父节点也不选中
            if (!isChecked) {
                node.setChecked(false);
            }
            if (node.getParent() != null) {
                setNodeParentChecked(node.getParent(), false);
            }
        }
    }

    public abstract View getConvertView(Node<T, B> node, int position, View convertView, ViewGroup parent);
}
