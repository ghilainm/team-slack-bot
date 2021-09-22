plugins {
  id("org.springframework.boot") version "2.5.4"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  `java-library`
}

repositories {
  mavenCentral()
}

dependencies {
  annotationProcessor("org.projectlombok:lombok:1.18.20")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.4")
  compileOnly("org.projectlombok:lombok:1.18.20")
  implementation("com.slack.api:bolt:1.12.1")
  implementation("com.slack.api:bolt-servlet:1.12.1")
  implementation("io.chucknorris:client-java:1.0.1")
  implementation("org.gitlab4j:gitlab4j-api:4.18.0")
  implementation("com.squareup.okhttp3:okhttp:4.9.1")
  implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun>().forEach {
  it.environment = mapOf(
    "SLACK_BOT_TOKEN" to project.properties["slack.bot.token"],
    "SLACK_SIGNING_SECRET" to project.properties["slack.signing.secret"],
    "GITLAB_API_URL" to project.properties["gitlab.api.url"],
    "GITLAB_API_TOKEN" to project.properties["gitlab.api.token"],
  )
}
