plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "org.ghost4j.repository.releases"
        url = uri("http://repo.ghost4j.org/maven2/releases")
    }
    maven {
        name = "org.ghost4j.repository.snapshots"
        url = uri("http://repo.ghost4j.org/maven2/snapshots")
    }
}

dependencies {
    implementation("org.ghost4j:ghost4j:1.0.0")

    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}