rootProject.name = "concurrent"


pluginManagement {
    repositories {
        // 插件使用阿里云 maven 源
        maven {
            setUrl("https://maven.aliyun.com/repository/gradle-plugin")
        }
    }
}

include("adapter")
include("application")
include("common")
include("domain")
include("infrastructure")
