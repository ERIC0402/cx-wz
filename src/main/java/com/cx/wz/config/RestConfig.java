package com.cx.wz.config;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan({"com.cx.wz"})
@Import({RepoConfig.class})
@EnableScheduling
@EnableSwagger2
@PropertySources({
        @PropertySource(value = "classpath:application.properties"),
        @PropertySource(value = "classpath:sulucenter/core.properties"),
        @PropertySource(value = "file://${CONFIG_HOME}/hawkeye/core.properties", ignoreResourceNotFound = true)
})
public class RestConfig implements WebMvcConfigurer {

    public static final ImmutableList<String> LOGIN_EXCLUDE_URLS = ImmutableList.of(
            "/swagger-ui.html","/webjars/**","/swagger-resources/**"
            ,"/**/resources/**","/resources/**", "/lang/**", "/**/js/**", "/**/css/**", "/**/*.xml", "/**/bootstrap/**", "/**/plugins/**","/**/404", "/**/500", "/**/error",  "/files/**","/**/images/**"
    );

    @Autowired
    private DeviceInterceptor deviceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        registry.addInterceptor(localeChangeInterceptor);

        registry.addInterceptor(deviceInterceptor).addPathPatterns("/**").excludePathPatterns(LOGIN_EXCLUDE_URLS);
    }

}
