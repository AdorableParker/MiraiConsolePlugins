plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.12.0"
}

group = "org.nymph"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
//    implementation (files("lib/ansj_seg-5.1.6.jar"))
    implementation("com.github.AdorableParker.Nymph-ToolDependency-repo:sqliteJDBC:0.3.10")
    "shadowLink"("com.github.AdorableParker.Nymph-ToolDependency-repo:sqliteJDBC")
}