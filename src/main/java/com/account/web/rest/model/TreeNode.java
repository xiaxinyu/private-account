package com.account.web.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TreeNode {
    private String id;
    private String text;
    private String state;
    private List<TreeNode> children;

    public void setId(String id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setState(String state) { this.state = state; }
    public void setChildren(List<TreeNode> children) { this.children = children; }
}
