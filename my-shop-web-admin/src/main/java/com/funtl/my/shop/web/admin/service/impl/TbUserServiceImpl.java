package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    @Override
    @Transactional(readOnly = false)
    public TbUser login(String email, String password) {
        TbUser tbUser=dao.getByEmail(email);
        if (tbUser!=null){
            //明文密码加密
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
            if (md5DigestAsHex.equals(tbUser.getPassword())){
                return  tbUser;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if (validator !=null){
               return BaseResult.fail(validator);
        }
        //验证通过
        else {
            tbUser.setUpdated(new Date());
            if (tbUser.getId()==null){
                /*新增用户*/
                /*密码加密*/
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));

                    tbUser.setCreated(new Date());
                    dao.insert(tbUser);
            }else {
                /*编辑用户*/
                update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }

    }

}