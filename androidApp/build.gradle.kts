plugins {
    kotlin(Plugins.android)
    kotlin(Plugins.serialization) version Versions.kotlin_version
    id(Plugins.androidApp)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
}

android {
    namespace = "com.example.meshmessenger.android"
    compileSdk = Versions.compileSdk
    defaultConfig {
        applicationId = "com.example.meshmessenger.android"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    // Compose
    implementation(Deps.Android.compose_ui)
    implementation(Deps.Android.compose_ui_tooling)
    implementation(Deps.Android.compose_ui_tooling_preview)
    implementation(Deps.Android.compose_foundation)
    implementation(Deps.Android.compose_material)
    implementation(Deps.Android.compose_activity)
    implementation(Deps.Android.compose_navigation)
    implementation(Deps.Android.coil_compose)

    // SQLDelight
    implementation(Deps.Android.sqlDelightAndroidDriver)

    // Ktor
    implementation(Deps.Android.ktorAndroid)

    // Coroutines
    implementation(Deps.Android.coroutines)

    // Koin
    implementation(Deps.Android.koinAndroid)
    implementation(Deps.Android.koinCore)
    implementation(Deps.Android.koinAndroidCompose)

    // ViewModel
    implementation(Deps.Android.viewModel)
    implementation(Deps.Android.viewModelCompose)
    implementation(Deps.Android.viewModelSavedstate)
    implementation(Deps.Android.lifecycleRuntime)
    implementation(Deps.Android.lifeData)

    // Kotlin date Time
    implementation(Deps.Android.kotlinDateTime)

    // Kable
    implementation(Deps.Multiplatform.kable)

    ///?
    implementation("com.juul.kable:core:0.7.0-issue-142-1-SNAPSHOT")
    implementation("com.airbnb.android:lottie-compose:6.0.0")

    // ???
    implementation("androidx.work:work-runtime:2.8.1")
}