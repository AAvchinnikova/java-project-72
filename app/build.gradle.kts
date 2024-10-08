import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    application
    java
    id("checkstyle")
    id ("jacoco")
    id("war")
    id("org.gretty") version "4.1.0"
    id("io.freefair.lombok") version "8.6"
    id("com.github.ben-manes.versions") version "0.48.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application { mainClass.set("hexlet.code.App") }

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("org.zalando:logbook-core:3.9.0")
    implementation("org.zalando:logbook-servlet:3.7.1")
    implementation("io.javalin:javalin:6.1.4")
    implementation("gg.jte:jte:3.1.12")
    implementation("io.javalin:javalin-rendering:6.1.6")
    implementation("io.javalin:javalin-bundle:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("net.datafaker:datafaker:2.3.0")

    implementation("com.h2database:h2:2.2.224") //для работы с бd
    implementation("com.zaxxer:HikariCP:5.1.0")//бд хикари
    implementation("org.postgresql:postgresql:42.7.3") //для работы с  бд типа postgresql
    implementation("com.mysql:mysql-connector-j:9.0.0") //драйвер для работы с бд в принципе

    implementation("com.konghq:unirest-java:3.14.5")//для простого создания разных HTTP\HTTPS-запросов
    implementation ("org.jsoup:jsoup:1.18.1")

    implementation("org.apache.commons:commons-lang3:3.15.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("commons-validator:commons-validator:1.8.0")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0") //для тестирования посылания запросов на другой сервер
    implementation("io.javalin:javalin-bundle:6.1.3")//для тестировония проекта на джавалине

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("com.konghq:unirest-java-core:4.3.1")
    testImplementation("com.konghq:unirest-java-bom:4.3.2")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        description = file("$/app/build/reports/jacoco/test/jacocoTestReport.xml").toString()
    }
}
