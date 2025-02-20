plugins {
    java
    jacoco
    id("org.springframework.boot") version libs.versions.springBoot.get()
    id("io.spring.dependency-management") version libs.versions.dependencyManagement.get()
    id("org.sonarqube") version libs.versions.sonarqube.get()
}

group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

sonar {
    properties {
        property("sonar.projectKey", "terserahdehh_eshop")
        property("sonar.organization", "terserahdehh")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/test/jacocoTestReport.xml")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.seleniumhq.selenium:selenium-java:${libs.versions.seleniumJava.get()}")
    testImplementation("io.github.bonigarcia:selenium-jupiter:${libs.versions.seleniumJupiter.get()}")
    testImplementation("io.github.bonigarcia:webdrivermanager:${libs.versions.webdrivermanager.get()}")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.register<Test>("unitTest") {
    description = "Runs unit tests."
    group = "verification"
    filter {
        excludeTestsMatching("*FunctionalTest")
    }
}

tasks.register<Test>("functionalTest") {
    description = "Runs functional tests."
    group = "verification"
    filter {
        includeTestsMatching("*FunctionalTest")
    }
}

tasks.test {
    filter {
        excludeTestsMatching("*FunctionalTest")
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

