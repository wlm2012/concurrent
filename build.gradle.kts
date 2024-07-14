plugins {
    java
    `java-library`
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
//    id("org.graalvm.buildtools.native") version "0.9.28"
}


allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    group = "com.study"
    version = "0.0.1-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        // 依赖使用阿里云 maven 源
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
            setUrl("https://maven.aliyun.com/repository/spring/")
        }
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }
    tasks.bootJar {
        mainClass = "com.study.concurrent.ConcurrentApplication"
    }

    springBoot {
        mainClass = "com.study.concurrent.ConcurrentApplication"
    }
}

subprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")
//        implementation("org.springframework.ai:spring-ai-vertex-ai-gemini-spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-websocket")
        compileOnly("org.projectlombok:lombok:1.18.30")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
        runtimeOnly("com.mysql:mysql-connector-j")
        runtimeOnly("org.postgresql:postgresql")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        annotationProcessor("org.projectlombok:lombok:1.18.30")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation(kotlin("script-runtime"))
        implementation("com.lmax:disruptor:4.0.0")
        implementation("org.jsoup:jsoup:1.17.2")
        implementation("org.apache.httpcomponents.client5:httpclient5:5.3")
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
        implementation("org.jooq:jool:0.9.15")

        testCompileOnly("org.projectlombok:lombok:1.18.30")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    extra["springAiVersion"] = "1.0.0-M1"
    dependencyManagement {
        imports {
            mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
        }
    }

    if (project.name != "adapter") {
        tasks.bootJar {
            enabled = false
        }
    }

    tasks.jar {
        enabled = true
    }

}

