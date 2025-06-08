plugins {
    java
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.springframework.cloud.contract") version "4.3.0"
}

group = "ru.yandex.practicum"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

contracts {
    contractsDslDir.set(file("src/test/resources/contracts"))
    testFramework.set(org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5)
    baseClassForTests.set("ru.yandex.practicum.serviceb.BaseContractTest")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:4.3.0")

    testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.3.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:spring-mock-mvc:5.5.5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named("test") {
    dependsOn("generateContractTests")
    mustRunAfter("generateContractTests")
}

tasks.named("generateContractTests") {
    mustRunAfter("compileTestJava")
}
