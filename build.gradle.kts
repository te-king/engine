plugins {
    kotlin("multiplatform") version "1.7.0"
}

repositories {
    mavenCentral()
//    maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
//    maven { url "https://dl.bintray.com/dominaezzz/kotlin-native" }
}

kotlin {

    jvm {

    }

//    js(IR) {
//        browser()
//        binaries.executable()
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.lwjgl:lwjgl:3.2.2")
                implementation("org.lwjgl:lwjgl-glfw:3.2.2")
                implementation("org.lwjgl:lwjgl-opengl:3.2.2")
                runtimeOnly("org.lwjgl:lwjgl:3.2.2:natives-windows")
                runtimeOnly("org.lwjgl:lwjgl-glfw:3.2.2:natives-windows")
                runtimeOnly("org.lwjgl:lwjgl-opengl:3.2.2:natives-windows")
            }
        }
        val jvmTest by getting
//        val jsMain by getting
//        val jsTest by getting
//        val nativeMain by getting
//        val nativeTest by getting
    }
//
//    sourceSets {
//        commonMain {
//            dependencies {
//                implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2'
//            }
//        }
////        jsMain {}
//        jvmMain {
//            dependencies {
//                implementation "org.lwjgl:lwjgl:3.2.2"
//                implementation "org.lwjgl:lwjgl-glfw:3.2.2"
//                implementation "org.lwjgl:lwjgl-opengl:3.2.2"
//                runtimeOnly "org.lwjgl:lwjgl:3.2.2:natives-windows"
//                runtimeOnly "org.lwjgl:lwjgl-glfw:3.2.2:natives-windows"
//                runtimeOnly "org.lwjgl:lwjgl-opengl:3.2.2:natives-windows"
//            }
//        }
//        all {
//            languageSettings {
//                languageVersion = '1.5'
//                apiVersion = '1.5'
//            }
//        }
//    }
}

group = "me.user"
version = "1.0-SNAPSHOT"