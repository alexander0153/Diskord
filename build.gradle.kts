plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.3.71"
    id("org.jetbrains.dokka") version "0.10.1"
}

val diskordVersion: String by project
val kotlinVersion: String by project
val kotlinxCoroutinesVersion: String by project
val kotlinSerializationVersion: String by project
val ktorVersion: String by project
val okhttpVersion: String by project

group = "com.jessecorbett"
version = diskordVersion

repositories {
    mavenCentral()
    jcenter() // Needed for dokka
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinSerializationVersion")
    implementation("io.github.microutils:kotlin-logging-common:1.7.9")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinSerializationVersion")
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
}

sourceSets["main"].java {
    srcDir("src/commonMain/kotlin")
    srcDir("src/jvmMain/kotlin")
}
