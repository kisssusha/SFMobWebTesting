plugins {
    id("java")
}

group = "org.kisssusha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.16.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")
    testImplementation("io.appium:java-client:9.0.0")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation("org.slf4j:slf4j-simple:2.0.12")
}

tasks.test {
    useTestNG()
}