plugins { id("com.gradle.enterprise") version ("3.17.5") }

if (!System.getenv("CI").isNullOrEmpty()) {
  gradleEnterprise {
    buildScan {
      termsOfServiceUrl = "https://gradle.com/terms-of-service"
      termsOfServiceAgree = "yes"
    }
  }
}

rootProject.name = "pulumi-java-string-interpolator"

includeBuild(".plugin")
