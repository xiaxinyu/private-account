package com.account.web.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TreeNode {
    private String id;
    private String text;
    private List<TreeNode> children;
}
