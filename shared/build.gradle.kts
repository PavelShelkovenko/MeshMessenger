plugins {
    kotlin(Plugins.multiplatform)
    kotlin(Plugins.cocoapods)
    kotlin(Plugins.serialization) version Versions.kotlin_version
    id(Plugins.androidLib)
    id(Plugins.SQLDelight)
    id(Plugins.mokoSharedRes)
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

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(Deps.Multiplatform.mokoCore)
            export(Deps.Multiplatform.mokoFLow)
            export(Deps.Multiplatform.mokoState)
            export(Deps.Multiplatform.mokoFlowRes)
            export(Deps.Multiplatform.mokoSharedRes)
            export(Deps.Multiplatform.mokoGraphics)
        }
        binaries
            .filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
            .forEach {
                it.transitiveExport = true
                //it.export(Deps.Multiplatform.sharedStorage)
            }
    }

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

                // Coroutines
                implementation(Deps.Multiplatform.coroutines_core)
                api(Deps.Multiplatform.coroutines_core)

                // Kotlin Date Time
                implementation(Deps.Multiplatform.kotlinDateTime)

                // Ktor
                implementation(Deps.Multiplatform.ktorCore)
                implementation(Deps.Multiplatform.ktorSerialization)
                implementation(Deps.Multiplatform.ktorSerializationJson)

                // Shared Storage
                //implementation(Deps.Multiplatform.sharedStorage)

                // SQLDelight
                implementation(Deps.Multiplatform.sqlDelightRuntime)

                // Koin
                api(Deps.Multiplatform.koinCore)
                api(Deps.Multiplatform.koinTest)

                // MOKO-MVVM
                api(Deps.Multiplatform.mokoCore)
                api(Deps.Multiplatform.mokoFLow)
                api(Deps.Multiplatform.mokoState)
                api(Deps.Multiplatform.mokoFlowRes)
                api(Deps.Multiplatform.mokoSharedRes)

                //kable
                implementation(Deps.Multiplatform.kable)

                //Kvault
                implementation(Deps.Multiplatform.kvault)
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
                implementation(Deps.Android.coroutines)
                api(Deps.Android.coroutines)

                // Shared Storage
                //implementation(Deps.Android.sharedStorage)
                //api(Deps.Android.sharedStorage)

                // MOKO-MVVM
                api(Deps.Android.mokoFlowCompose)

                // Kvault
                implementation(Deps.Multiplatform.kvault)
            }
        }
        val androidUnitTest by getting
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
                //implementation(Deps.IOS.sharedStorage)
                //api(Deps.IOS.sharedStorage)

                //Kvault
                implementation(Deps.Multiplatform.kvault)
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

sqldelight {
    database("AppDatabase") {
        packageName = "com.example.meshmessenger.database"
        sourceFolders = listOf("sqldelight")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.meshmessenger"
    multiplatformResourcesClassName = "SharedRes"
}

android {
    namespace = "com.example.meshmessenger"
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
    }
}