package com.transport.transportback.security;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CorsFilter {


    @Bean
    public org.springframework.web.filter.CorsFilter CorsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(allowedOrigins());
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        source.registerCorsConfiguration("/v2/api-docs", config);
        return new org.springframework.web.filter.CorsFilter(source);

    }

    /**
     * Front Url value
     */
    @Value("${front.base.url}")
    private String frontUrl;


    private List<String> allowedOrigins() {
        List<String> urlList = new ArrayList<>();
        if (frontUrl != null && !frontUrl.isEmpty()) {
            urlList.add(frontUrl);
        }

        log.info("Allow origin " + urlList.toString());
        return urlList;
    }
}
