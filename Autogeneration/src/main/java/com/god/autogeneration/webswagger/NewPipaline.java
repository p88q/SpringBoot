package com.god.autogeneration.webswagger;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * 专门对数据进行处理
 */
public class NewPipaline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        // 获取存储的值
        List<String> url=resultItems.get("imags");
        System.out.println("!!!!!!!!!!!!"+url);
        // 对返回结果集的处理，适用service实现批量新增
        for (int i = 0; i < url.size(); i++) {
            System.out.println(url.get(i));
        }
    }

}
