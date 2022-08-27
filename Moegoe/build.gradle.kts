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
    mavenCentral()
}

dependencies {
    implementation("ws.schild:jave-all-deps:3.3.1")
//    implementation("ws.schild:jave-core:3.3.1")
//    implementation("ws.schild:jave-nativebin-linux64:3.3.1") // linux-AMD64
//    implementation("ws.schild:jave-nativebin-linux-arm64:3.3.1") // linux-arm64
//    implementation("ws.schild:jave-nativebin-win64:3.3.1")  // windows-x64
//    implementation("ws.schild:jave-nativebin-osx64:3.3.1")  // macOS-x64
}