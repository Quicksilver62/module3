//package ru.yandex.practicum.frontend.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//@RequiredArgsConstructor
//public class RestTemplateConfig {
//
//    private final OAuth2AuthorizedClientService authorizedClientService;
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
//                String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
//
//                OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
//                        clientRegistrationId,
//                        oauthToken.getName()
//                );
//
//                if (authorizedClient != null && authorizedClient.getAccessToken() != null) {
//                    String tokenValue = authorizedClient.getAccessToken().getTokenValue();
//                    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue);
//                }
//            }
//
//            return execution.execute(request, body);
//        });
//
//        return restTemplate;
//    }
//}
