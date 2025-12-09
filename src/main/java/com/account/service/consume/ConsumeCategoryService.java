package com.account.service.consume;

import com.account.persist.model.ConsumeCategory;
import com.account.web.rest.model.TreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsumeCategoryService extends IService<ConsumeCategory> {
    List<ConsumeCategory> listAll();
    List<TreeNode> tree();
}
