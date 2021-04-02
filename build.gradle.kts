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
    implementation("net.sourceforge.tess4j:tess4j:3.4.2")
    implementation("com.googlecode.json-simple:json-simple:1.1")

    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    jar {
        archiveName = "PdfToTextConverter.jar"

        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)

        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })

        manifest {
            attributes["Main-Class"] = "PdfToTextConverter"
        }

        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}

tasks.register("export") {
    group = "build"
    description = "Exports the project as jar file"

    dependsOn("jar")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}