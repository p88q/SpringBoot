package com.god.easypoi.handler;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.god.easypoi.entity.User;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 17:22
 * @ClassName: MyVerifyHandler
 * @Description: 自定义的导入校验器
 */
public class MyVerifyHandler implements IExcelVerifyHandler<User> {

    /**
     * 校验数据信息
     * @param member
     * @return
     */
    @Override
    public ExcelVerifyHandlerResult verifyHandler(User member) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
        //假设我们要添加用户，
        //现在去数据库查询loginName，如果存在则表示校验不通过。
        //假设现在数据库中有个loginName 1234560
        if ("123456".equals(member.getLoginName())) {
            result.setMsg("该用户已存在");
            result.setSuccess(false);
            System.out.println("证明错误检查可以使用");
            return result;
        }
        result.setSuccess(true);
        return result;
    }
}