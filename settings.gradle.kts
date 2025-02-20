rootProject.name = "eshop"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("springBoot", "3.4.2")
            version("dependencyManagement", "1.1.7")
            version("sonarqube", "6.0.1.5171")
            version("seleniumJava", "4.14.1")
            version("seleniumJupiter", "5.0.1")
            version("webdrivermanager", "5.6.3")
        }
    }
}