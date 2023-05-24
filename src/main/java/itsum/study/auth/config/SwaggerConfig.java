package itsum.study.auth.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.Contact;
import io.swagger.models.Tag;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
        "itsum.study.auth.controller",
        "itsum.study.posts.controller",
})
public class SwaggerConfig {
    TypeResolver typeResolver = new TypeResolver();
    @Data
    @ApiModel
    static class MyPageable {
        @ApiModelProperty(value = "페이지 번호 (0..N)")
        private int page;
        @ApiModelProperty(value = "페이지 크기",allowableValues ="range[0,1000]")
        private int size;
        @ApiModelProperty(value = "정렬(사용법 : 컬럼명,asc|desc)")
        private List<String> sort;
    }
    /** swagger */
    @Bean
    public Docket ItsumApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ITSUM API")
                .apiInfo(getApiInfo())
                .select()
                .paths(PathSelectors.any())
                .build().alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(MyPageable.class)));

    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("ITSUM project REST API")
                .description("ITSUM Java Backend REST API Guide Document")
                .version("1.0")
                .build();
    }
}
