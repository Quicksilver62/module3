package ru.yandex.practicum.frontend.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.io.IOException;
import java.time.Instant;

public class OAuth2ClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final String clientRegistrationId;

    public OAuth2ClientHttpRequestInterceptor(
            OAuth2AuthorizedClientService authorizedClientService,
            String clientRegistrationId) {
        this.authorizedClientService = authorizedClientService;
        this.clientRegistrationId = clientRegistrationId;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                clientRegistrationId, "anonymousUser");

        if (authorizedClient == null || isTokenExpired(authorizedClient.getAccessToken())) {
            throw new IllegalStateException("OAuth2 token is not available or expired");
        }

        request.getHeaders().setBearerAuth(authorizedClient.getAccessToken().getTokenValue());
        return execution.execute(request, body);
    }

    private boolean isTokenExpired(OAuth2AccessToken token) {
        return token.getExpiresAt() != null && token.getExpiresAt().isBefore(Instant.now());
    }
}
