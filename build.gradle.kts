import pl.andrzejressel.plugin.License

plugins {
  id("java")
  id("parent-plugin")
  id("child-plugin")
}

group = "pl.andrzejressel"

childPlugin {
  license = License.LGPL
  disableJavaFormatter = true
  childAndParentInTheSameProject = true
}

repositories { mavenCentral() }

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

dependencies {
  implementation("com.pulumi:pulumi:0.9.9")
  testImplementation(platform("org.junit:junit-bom:5.9.1"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test { useJUnitPlatform() }

tasks.withType<JavaCompile>().configureEach { options.compilerArgs.add("--enable-preview") }

tasks.withType<Test>().configureEach { jvmArgs("--enable-preview") }

tasks.withType<JavaExec>().configureEach { jvmArgs("--enable-preview") }

tasks.jacocoTestReport {
  dependsOn("test")

  executionData.setFrom(fileTree(layout.buildDirectory).include("/jacoco/*.exec"))
  reports {
    xml.required = true
    html.required = true
  }
}
