plugins {
    id("java")
    jacoco
}

group = "pl.andrzejressel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

dependencies {
    implementation("com.pulumi:pulumi:0.9.9")
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<Test>().configureEach {
    jvmArgs("--enable-preview")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-preview")
}

tasks.named("check") { dependsOn("jacocoTestReport") }

tasks.jacocoTestReport {
    dependsOn("test")

    executionData.setFrom(fileTree(layout.buildDirectory).include("/jacoco/*.exec"))
    reports {
        xml.required = true
        html.required = true
    }
}