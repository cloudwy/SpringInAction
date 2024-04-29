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
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok") //important for @Data
    annotationProcessor("org.projectlombok:lombok") ////important for @Data
    // https://mvnrepository.com/artifact/io.micronaut/micronaut-inject-java
    annotationProcessor("io.micronaut:micronaut-inject-java")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor") // related to test2 in MessageServiceTest
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.validation:micronaut-validation") // related to test2 in MessageServiceTest
    implementation("io.micronaut.views:micronaut-views-fieldset")
    implementation("io.micronaut.views:micronaut-views-thymeleaf")
    // session
    implementation("io.micronaut.session:micronaut-session") // can't be at the end
//    implementation("org.thymeleaf:thymeleaf:3.0.11.RELEASE")
    // https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api
    implementation("jakarta.validation:jakarta.validation-api")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation("io.micronaut:micronaut-http-client")
    // https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    // https://mvnrepository.com/artifact/io.micronaut/micronaut-session
    implementation("io.micronaut:micronaut-session:3.10.4")
    // ModelAndView
    // https://mvnrepository.com/artifact/io.micronaut/micronaut-http-server
    implementation("io.micronaut:micronaut-http-server:4.4.1")
    // https://mvnrepository.com/artifact/io.micronaut/micronaut-views
    implementation("io.micronaut:micronaut-views:1.3.2")
    // core
    implementation("io.micronaut:micronaut-core")
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



