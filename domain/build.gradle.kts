dependencies {
    api(project(":infrastructure"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
tasks.bootJar { enabled = false }
tasks.jar { enabled = true }
