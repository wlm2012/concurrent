dependencies {
    api(project(":application"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.bootJar { enabled = true }
tasks.jar { enabled = true }
