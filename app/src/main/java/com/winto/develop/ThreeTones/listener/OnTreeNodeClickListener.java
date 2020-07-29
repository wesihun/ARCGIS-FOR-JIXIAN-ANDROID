package com.winto.develop.ThreeTones.listener;

import com.winto.develop.ThreeTones.bean.Node;

public interface OnTreeNodeClickListener<T, B> {
    void onClick(Node<T, B> node, int position);
}