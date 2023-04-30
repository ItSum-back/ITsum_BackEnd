package itsum.study.auth.config;

import io.swagger.models.Contact;
import io.swagger.models.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
        "itsum.study.auth.controller",
        "itsum.study.posts.controller",
})
public class SwaggerConfig {
    /** swagger */
    @Bean
    public Docket ItsumApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ITSUM API")
                .apiInfo(getApiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("ITSUM project REST API")
                .description("ITSUM Java Backend REST API Guide Document")
                .version("1.0")
                .build();
    }
}
