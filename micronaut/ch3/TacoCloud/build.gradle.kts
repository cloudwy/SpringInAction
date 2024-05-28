plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.3.5"
    id("io.micronaut.aot") version "4.3.5"
}

version = "0.1"
group = "com.example"

repositories {
    mavenCentral()
}

dependencies {
//    //lombok
//    // https://mvnrepository.com/artifact/org.projectlombok/lombok
//    compileOnly("org.projectlombok:lombok") //important for @Data //--
//    annotationProcessor("org.projectlombok:lombok") ////important for @Data //--
//    // https://mvnrepository.com/artifact/io.micronaut/micronaut-inject-java
//    annotationProcessor("io.micronaut:micronaut-inject-java") //--
//    annotationProcessor("io.micronaut:micronaut-http-validation") //--
//    annotationProcessor("io.micronaut.serde:micronaut-serde-processor") //--
//    annotationProcessor("io.micronaut.validation:micronaut-validation-processor") // related to test2 in MessageServiceTest //--
//    implementation("io.micronaut.serde:micronaut-serde-jackson") //--
//    implementation("io.micronaut.validation:micronaut-validation") // related to test2 in MessageServiceTest //--
//    //view
//    implementation("io.micronaut.views:micronaut-views-fieldset") //--
//    implementation("io.micronaut.views:micronaut-views-thymeleaf") //--
//    // session
//    implementation("io.micronaut.session:micronaut-session") // can't be at the end //--
//    // https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api
//    implementation("jakarta.validation:jakarta.validation-api") //---
//    compileOnly("io.micronaut:micronaut-http-client") //--
//    runtimeOnly("ch.qos.logback:logback-classic") //--
//    testImplementation("io.micronaut:micronaut-http-client")
//    // https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
////    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
//    implementation("io.micronaut.data:micronaut-data-hibernate-jpa") //--
//    implementation("io.micronaut.sql:micronaut-hibernate-jpa") //--
//    // https://mvnrepository.com/artifact/io.micronaut/micronaut-session
//    implementation("io.micronaut:micronaut-session:3.10.4")
//    // ModelAndView
//    // https://mvnrepository.com/artifact/io.micronaut/micronaut-http-server
//    implementation("io.micronaut:micronaut-http-server:4.4.1")
//    // https://mvnrepository.com/artifact/io.micronaut/micronaut-views
//    implementation("io.micronaut:micronaut-views:1.3.2")
//    // core
//    implementation("io.micronaut:micronaut-core") //--



    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok") //important for @Data
    annotationProcessor("org.projectlombok:lombok") ////important for @Data
    annotationProcessor("io.micronaut:micronaut-inject-java")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    //session
    implementation("io.micronaut.session:micronaut-session") // can't be at the end
    // jpa and h2
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.h2database:h2")
    //annotation validation
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("io.micronaut.sql:micronaut-hibernate-jpa")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("io.micronaut.beanvalidation:micronaut-hibernate-validator")
    //view
    implementation("io.micronaut.views:micronaut-views-fieldset")
    implementation("io.micronaut.views:micronaut-views-thymeleaf")
    implementation("io.micronaut:micronaut-http-server:4.4.1")
    implementation("io.micronaut:micronaut-views:1.3.2")
//    implementation("io.micronaut:micronaut-session:3.10.4")
    //core
    implementation("io.micronaut:micronaut-core")
    //for application.yml
    runtimeOnly("org.yaml:snakeyaml")
    //jsoup
    implementation("org.jsoup:jsoup:1.14.2")
    //test
    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("io.micronaut.test:micronaut-test-junit5")
    testImplementation("org.mockito:mockito-core:4.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.seleniumhq.selenium:selenium-java:3.141.59") //selenium
    testImplementation("io.micronaut:micronaut-test") // port
}


application {
    mainClass.set("com.example.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}



