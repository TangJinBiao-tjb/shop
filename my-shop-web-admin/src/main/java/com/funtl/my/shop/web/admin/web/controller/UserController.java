package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseController;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;
        if (id!=null){
            //id不为空
            tbUser=service.getById(id);
        }else {
            tbUser=new TbUser();
        }
        return tbUser;
    }


    /**
     * 保存用户信息
     * 重定向
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbUser);
        if (baseResult.getStatus()==200){
            //保存成功
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else {
            //保存失败
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    /**
     * 删除用户信息
     *
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户成功");
        } else {
            baseResult = BaseResult.fail("删除用户失败");
        }

        return baseResult;
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser){
        String strdraw = request.getParameter("draw");
        String strstart = request.getParameter("start");
        String strlength = request.getParameter("length");

        int draw=strdraw==null ? 0 :Integer.parseInt(strdraw);
        int start=strstart==null ? 0 :Integer.parseInt(strstart);
        int length=strlength==null ? 10 :Integer.parseInt(strlength);

        PageInfo<TbUser> pageInfo = service.page(start, length, draw,tbUser);
        return pageInfo;
    }

    /**
     * 显示用户详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(){
        return "user_detail";
    }

    /**
     * 跳转至用户列表页
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    /**
     * 跳转至表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

}
