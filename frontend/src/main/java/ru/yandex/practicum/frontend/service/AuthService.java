package ru.yandex.practicum.frontend.service;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.frontend.configuration.KeycloakProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KeycloakProperties keycloakProperties;
    private final PasswordEncoder passwordEncoder;
    private Keycloak keycloak;

    @PostConstruct
    public void init() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())
                .realm(keycloakProperties.getRealm())
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(keycloakProperties.getAdmin().getClientId())
                .username(keycloakProperties.getAdmin().getUsername())
                .password(keycloakProperties.getAdmin().getPassword())
                .build();
    }

    public List<String> credentialValidation(String login,
                                      String password,
                                      String confirm_password,
                                      String name,
                                      String birthdate) {
        List<String> errors = new ArrayList<>();

        if (login == null || login.isBlank()) {
            errors.add("Логин не может быть пустым");
        }

        if (password == null || password.isBlank()) {
            errors.add("Пароль не может быть пустым");
        }

        if (confirm_password == null || confirm_password.isBlank()) {
            errors.add("Подтверждение пароля не может быть пустым");
        }

        if (name == null || name.isBlank()) {
            errors.add("ФИО не может быть пустым");
        }

        if (birthdate == null || birthdate.isBlank()) {
            errors.add("Дата рождения не может быть пустой");
        }

        if (!errors.isEmpty()) {
            return errors;
        }

        if (!password.equals(confirm_password)) {
            errors.add("Пароли не совпадают");
        }

        LocalDate birthDate = LocalDate.parse(birthdate);
        if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            errors.add("Возраст должен быть не менее 18 лет");
        }
        return errors;
    }

    public Response keycloakSignUp(String login,
                                   String password,
                                   String name,
                                   String birthdate) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(passwordEncoder.encode(password));
        credential.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(login);
        user.setEnabled(true);
        user.setCredentials(List.of(credential));

        user.setFirstName(name.split(" ")[0]);
        user.setLastName(name.split(" ").length > 1 ? name.split(" ")[1] : "");

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("birthdate", List.of(birthdate));
        user.setAttributes(attributes);

        System.out.println("Access Token: " + keycloak.tokenManager().getAccessToken().getToken());

        return keycloak.realm(keycloakProperties.getRealm()).users().create(user);
    }
}
