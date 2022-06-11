package iducs.springboot.bootjpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"iducs.springboot.bootjpa.controller", "iducs.springboot.bootjpa.domain", "iducs.springboot.bootjpa.entity", "iducs.springboot.bootjpa.repository", "iducs.springboot.bootjpa.service"},
        basePackageClasses = AppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AppConfig {
}
