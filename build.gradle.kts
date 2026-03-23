plugins {
	java
	id("org.springframework.boot") version "3.5.11"
	id("io.spring.dependency-management") version "1.1.7"
}

val springdocVersion = project.findProperty("springdocVersion") as String
group = "com.yellesdve"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Jenkins CI practice"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	// Mapstruct
	implementation("org.mapstruct:mapstruct:1.6.3")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    implementation("org.flywaydb:flyway-core:11.20.3")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:11.20.3")

//	runtimeOnly("com.h2database:h2")
	// Source: https://mvnrepository.com/artifact/org.postgresql/postgresql
	runtimeOnly("org.postgresql:postgresql:42.7.10")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sourceSets {
	test {
		java {
			srcDirs(
				"src/test/java/unit",
				"src/test/java/int"
			)
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
