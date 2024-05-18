dependencies {
    api(project(":application"))
}

tasks.bootJar {
    archiveBaseName = "concurrent"
}
