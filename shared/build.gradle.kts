plugins {
    kotlin(Plugins.multiplatform)
    kotlin(Plugins.cocoapods)
    kotlin(Plugins.serialization) version Versions.kotlin_version
    id(Plugins.androidLib)
    id(Plugins.SQLDelight)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {

                // ViewModel
                //implementation(Deps.Multiplatform.viewModel)
                //implementation(Deps.Multiplatform.viewModelCompose)
                //implementation(Deps.Multiplatform.viewModelSavedstate)
                //implementation(Deps.Multiplatform.lifecycleRuntime)
                //implementation(Deps.Multiplatform.lifeData)

                // Coroutines
                // implementation(Deps.Multiplatform.coroutines_core)

                // Kotlin Date Time
                implementation(Deps.Multiplatform.kotlinDateTime)

                // Ktor
                implementation(Deps.Multiplatform.ktorCore)
                implementation(Deps.Multiplatform.ktorSerialization)
                implementation(Deps.Multiplatform.ktorSerializationJson)

                // Shared Storage
                // implementation(Deps.Multiplatform.sharedStorage)
                // api(Deps.Multiplatform.sharedStorage)

                // SQLDelight
                implementation(Deps.Multiplatform.sqlDelightRuntime)
                implementation(Deps.Multiplatform.sqlDelightCoroutinesExtensions)

                // Koin
                // implementation(Deps.Multiplatform.koin)
                // implementation(Deps.Multiplatform.koinTest)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {

                // Ktor
                implementation(Deps.Android.ktorAndroid)

                // SQLDelight
                implementation(Deps.Android.sqlDelightAndroidDriver)

                // Coroutines
                // implementation(Deps.Android.coroutines)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {

                // Ktor
                implementation(Deps.IOS.ktorIOS)

                // SQLDelight
                implementation(Deps.IOS.sqlDelightIOSDriver)

                // Shared Storage
                // implementation(Deps.IOS.sharedStorage)
                // api(Deps.IOS.sharedStorage)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.meshmessenger"
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
    }
}