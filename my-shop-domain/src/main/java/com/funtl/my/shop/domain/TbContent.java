package com.funtl.my.shop.domain;


import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class TbContent extends BaseEntity {

    @Length(min = 1, max = 20, message = "标题长度介于 1 - 20 个字符之间")
    private String title;

    @Length(min = 1, max = 20, message = "子标题长度介于 1 - 20 个字符之间")
    private String subTitle;

    @Length(min = 1, max = 50, message = "标题描述长度介于 1 - 50 个字符之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TbContentCategory getTbContentCategory() {
        return tbContentCategory;
    }

    public void setTbContentCategory(TbContentCategory tbContentCategory) {
        this.tbContentCategory = tbContentCategory;
    }

    @Length(min = 1, message = "内容不能为空")
    private String content;

    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;

}
