package com.god.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import com.god.easypoi.entity.MemberFailed;
import com.god.easypoi.entity.User;
import com.god.easypoi.handler.MyVerifyHandler;
import com.god.easypoi.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 17:13
 * @ClassName: HelloController
 * @Description:
 */
@RestController
public class HelloController {

    /**
     * 导出excel
     *
     * @param response
     */
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        List<User> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setAge("12");
            user.setBirthday(new Date());
            user.setId((long) i);
            user.setLoginName("123" + i);
            user.setName("username");
            user.setPhone("13462246097");
            user.setPic("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=108228188,2741176027&fm=27&gp=0.jpg");
            user.setSex("男");
            personList.add(user);
        }
        ExcelUtils.exportExcel(personList, "员工信息表", "员工信息", User.class, "员工信息", response);
    }

    /**
     * 简单导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/easyImport")
    public void easyImport(MultipartFile file) throws Exception {
        List<User> list = ExcelUtils.importExcel(file, User.class);
        for (User member : list) {
            System.out.println(member);
        }
    }

    /**
     * 复杂导入(导入时实现数据校验)
     * @param file
     * @param response
     * @throws Exception
     */
    @PostMapping("/complexImport")
    public void complexImport(MultipartFile file, HttpServletResponse response) throws Exception {
        ImportParams params = new ImportParams();
        //在有标题列的时候，需要指明标题列在1，默认是0
        params.setTitleRows(1);
        params.setNeedVerfiy(true);
        params.setVerifyHandler(new MyVerifyHandler());
        ExcelImportResult importResult = new ExcelImportService().importExcelByIs(file.getInputStream(),
                User.class, params, true);
        //list里面包含的是所有校验成功的数据
        List<User> list = importResult.getList();
        if (list != null) {
            for (User member : list) {
                System.out.println(member);
            }
        }
        System.out.println("-----------------------");
        //failWorkbook和failList里面包含的是所有校验失败的数据
        //我们可以直接将他们返回给前端
        Workbook failWorkbook = importResult.getFailWorkbook();
        List<User> failList = importResult.getFailList();

        List<MemberFailed> memberFaileds = MemberFailed.members2MemberFaileds(failList);
        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, MemberFailed.class, failList);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
    }


}
