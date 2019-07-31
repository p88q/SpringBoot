package com.god.autogeneration.webswagger;

import us.codecraft.webmagic.Spider;

/**
 * 爬虫工具
 */
public class WebMagic {

    public static void main(String[] args) {
        Spider.create(new MagicProcessor())
                .addUrl("http://tech.huanqiu.com/science/")
                .addPipeline(new NewPipaline()).thread(3).run();
    }
}
