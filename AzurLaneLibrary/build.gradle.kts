plugins {
    val kotlinVersion = "1.8.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.14.0"
}

group = "org.nymph"
version = "0.1.2"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://jitpack.io")
    mavenCentral()
}


dependencies {
    implementation("com.github.AdorableParker.Nymph-ToolDependency-repo:sqliteJDBC:0.3.12")
    "shadowLink"("com.github.AdorableParker.Nymph-ToolDependency-repo:sqliteJDBC")
}