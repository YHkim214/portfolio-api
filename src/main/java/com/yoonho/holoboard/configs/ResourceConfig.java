package com.yoonho.holoboard.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : com.yoonho.holostats.configs
 * fileName       : ResourceConfig
 * author         : kim-yoonho
 * date           : 1/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/4/24        kim-yoonho       최초 생성
 */

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Value("${file.upload.base}")
    private String fileBase;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        final Path FILE_ROOT = Paths.get("." + fileBase).toAbsolutePath().normalize();

        registry.addResourceHandler(fileBase +"/**").addResourceLocations(FILE_ROOT.toUri().toString());
    }
}
