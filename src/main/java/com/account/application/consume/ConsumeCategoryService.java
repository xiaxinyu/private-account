package com.account.application.consume;

import com.account.domain.model.ConsumeCategory;
import com.account.web.rest.model.TreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsumeCategoryService extends IService<ConsumeCategory> {
    List<ConsumeCategory> listAll();
    List<TreeNode> tree();
}
