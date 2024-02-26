plugins {
    id("java")
    id("jacoco")
}

group = "kr.bgmsound"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.apache.logging.log4j:log4j-api:2.17.1")
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

jacoco {
    toolVersion = "0.8.11"
}


tasks {
    test {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    jacocoTestReport {
        dependsOn("test")
        reports {
            xml.required = false
            csv.required = false
            html.required = true
        }
        classDirectories.setFrom(
            files(classDirectories.files.map {
                fileTree(it) {
                    exclude("**/Main*")
                }
            })
        )
        finalizedBy("jacocoTestCoverageVerification")
    }

    jacocoTestCoverageVerification {
        violationRules {
            rule {
                enabled = true
                element = "CLASS"
                excludes = mutableListOf("**.*Main*")
                limit {
                    value = "COVEREDRATIO"
                    minimum = "1.0".toBigDecimal()
                }
            }
        }
    }
}