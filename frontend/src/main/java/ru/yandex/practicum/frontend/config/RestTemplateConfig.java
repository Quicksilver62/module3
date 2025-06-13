package ru.yandex.practicum.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(OAuth2AuthorizedClientService authorizedClientService) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new OAuth2ClientHttpRequestInterceptor(
                        authorizedClientService,
                        "frontend"
                )
        );
        return restTemplate;
    }
}
