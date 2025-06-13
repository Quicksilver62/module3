package ru.yandex.practicum.frontend.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignConfig {

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
}