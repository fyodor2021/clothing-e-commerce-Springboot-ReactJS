plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}
val springCloudVersion by extra("2023.0.2")

group = "com.finefoods"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.apache.httpcomponents.client5:httpclient5:5.2")
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.2")
	runtimeOnly ("org.postgresql:postgresql")
//	implementation("org.springframework.session:spring-session-data-redis")
//	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.0.3")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.1.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator")


}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}




