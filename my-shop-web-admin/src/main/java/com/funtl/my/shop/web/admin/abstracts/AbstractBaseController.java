package com.funtl.my.shop.web.admin.abstracts;


import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>> {

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    public abstract String list();

    public abstract String form();

    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    public abstract BaseResult delete(String ids);

    /**
     * 分页
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest request, T entity){
        String strdraw = request.getParameter("draw");
        String strstart = request.getParameter("start");
        String strlength = request.getParameter("length");

        int draw=strdraw==null ? 0 :Integer.parseInt(strdraw);
        int start=strstart==null ? 0 :Integer.parseInt(strstart);
        int length=strlength==null ? 10 :Integer.parseInt(strlength);

        PageInfo<T> pageInfo = service.page(start, length, draw,entity);
        return pageInfo;
    }

    public abstract String detail();
}
