/*
 * *
 *  *packageName    : ${PACKAGE_NAME}
 *  * fileName       : ${NAME}
 *  * author         : ${USER}
 *  * date           : ${DATE}
 *  * description    :
 *  * ===========================================================
 *  * DATE              AUTHOR             NOTE
 *  * -----------------------------------------------------------
 *  * ${DATE}        ${USER}       최초 생성
 *
 */

package com.yoonho.holostats.configs;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * packageName    : com.yoonho.holostats.configs
 * fileName       : YoutubeConfig
 * author         : kim-yoonho
 * date           : 1/15/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/15/24        kim-yoonho       최초 생성
 */

@Configuration
public class YoutubeConfig {

    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Value("${youtube.application.name}")
    private String APPLICATION_NAME;

    @Bean
    public YouTube getYoutube() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        return new YouTube.Builder(httpTransport, JSON_FACTORY, httpRequest -> {
            //do nothing
        })
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}