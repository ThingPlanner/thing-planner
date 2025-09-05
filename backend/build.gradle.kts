plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-rest")
    implementation("jakarta.validation:jakarta.validation-api:3.1.1") // https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0") // https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
    implementation("io.quarkus:quarkus-hibernate-orm-panache:3.26.2") // https://mvnrepository.com/artifact/io.quarkus/quarkus-hibernate-orm-panache

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured") // https://mvnrepository.com/artifact/io.quarkus/quarkus-junit5-mockito
    testImplementation("io.quarkus:quarkus-junit5-mockito")

}

group = "com.thingplanner"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
