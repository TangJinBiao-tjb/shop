package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

public abstract   class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>> {

    @Autowired
    protected S service;

    public abstract String list(Model model);

    public abstract String form(T entity);

    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    public abstract BaseResult delete(String ids);

    public abstract List<T> treeData(Long id);

    /**
     * 排序
     * @param sourceList    数据源集合
     * @param targetList    排序后集合
     * @param parentId      父节点的ID
     */
    protected void sortList(List<T>sourceList,List<T> targetList,Long parentId){
        for (T SourceEntity : sourceList) {
            if (SourceEntity.getParent().getId().equals(parentId)) {
                targetList.add(SourceEntity);

                // 判断有没有子节点，如果有则继续追加
                if (SourceEntity.getIsParent()) {
                    for (T currentEntity : sourceList) {
                        if (currentEntity.getParent().getId().equals(SourceEntity.getId())) {
                            sortList(sourceList, targetList, SourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
