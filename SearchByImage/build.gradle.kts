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

//dependencies {
//    implementation("org.jsoup:jsoup:1.15.2")
//    implementation("com.beust:klaxon:5.6")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
//}