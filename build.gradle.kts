import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "2.0.21"
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.price-tracker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(kotlin("stdlib-jdk8"))

    // Navigation in Kotlin Compose:
    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")

    // To create references and inject classes where necessary
    implementation("io.insert-koin:koin-core:4.0.1")

    // SQLite
    implementation("org.xerial:sqlite-jdbc:3.47.1.0")

    // Retrofit - Get data from APIs
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "PriceTracker"
            packageVersion = "1.0.0"
        }
    }
}
