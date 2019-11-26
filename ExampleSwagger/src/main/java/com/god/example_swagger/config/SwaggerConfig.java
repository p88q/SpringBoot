package com.god.example_swagger.config;

import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/28 18:14
 * @ClassName: SwaggerConfig
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("${server.servlet-path}")
    private String pathMapping;

    @Value("${server.port}")
    private String port;

    private ApiInfo initApiInfo() {
//        return new ApiInfoBuilder().title("测试swagger项目配置").description(initContextInfo()).license().build();
        ApiInfo apiInfo = new ApiInfo("测试swagger项目配置", initContextInfo(), "0.0.1-SNAPSHOT",
                "服务条款", "作者", "超链接", "http://www.baidu.com");
        return apiInfo;
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。")
                .append("<br />")
                .append("本文实现了reest 风格的Api");
        return sb.toString();
    }

    public Docket createRestApi() {
        LOGGER.info("http://localhost:{}{}/swagger-ui.html", port, pathMapping);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("RestfulApi")
//                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .pathMapping(pathMapping) // base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(doFilteringRules())
                .build()
                .apiInfo(initApiInfo());
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(regex("/**"));
    }

}
