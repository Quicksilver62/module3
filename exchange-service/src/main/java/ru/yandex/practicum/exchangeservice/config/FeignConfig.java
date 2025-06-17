package ru.yandex.practicum.exchangeservice.config;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Bean
    public Encoder feignEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public Decoder feignDecoder() {
        return new SpringDecoder(messageConverters);
    }

    private final ObjectFactory<HttpMessageConverters> messageConverters = () -> 
        new HttpMessageConverters(new MappingJackson2HttpMessageConverter());

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            OAuth2AccessToken accessToken = getAccessToken();
            if (accessToken != null) {
                template.header("Authorization", "Bearer " + accessToken.getTokenValue());
            }
        };
    }

    private OAuth2AccessToken getAccessToken() {
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("external")
                .principal("principal-name")
                .build();

        return oAuth2AuthorizedClientManager.authorize(request) != null
                ? oAuth2AuthorizedClientManager.authorize(request).getAccessToken()
                : null;
    }
}