package com.god.autogeneration.webswagger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * 对爬取网站进行处理
 */
public class MagicProcessor implements PageProcessor {
    // 第一个参数：超时时间第二个参数：重试次数
    private Site site = Site.me().setTimeOut(2000).setRetryTimes(3);


    @Override
    public void process(Page page) {
        //System.out.println(page.getHtml());
        // 获取页面中路径的xpath路径中的所有 text()、@href、
        List<String> imags = page.getHtml().xpath("div[@class='fallsFlow']/ul/li/h3/a/@title").all();
        page.putField("url",imags);
        // 获取当前路径
        System.out.println(page.getUrl().get());
        for (int i = 0; i < imags.size(); i++) {
            System.out.println("抓取的：" + imags.get(i));
        }
        // 如果url地址相同。
        // 将数据进行展示
        page.putField("imags",imags);

//        if(page.getUrl().get().equals("http://tech.huanqiu.com/science/")){
//            // 获取页面中的xpath路径中的所有text地址
//            List<String> target=page.getHtml().xpath("div[@id='pages']/a/@href").all();
//            // 删除第一页
//            target.remove(0);
//            // 删除第二页
//            target.remove(target.size()-1);
//            // 继续抓取
//            page.addTargetRequests(target);
//        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
