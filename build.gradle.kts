import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.50"
}

group = "dev.reeve"
version = "1.0-SNAPSHOT"

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(group = "com.1stleg", name = "jnativehook", version = "2.0.2")
}

repositories {
	mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}