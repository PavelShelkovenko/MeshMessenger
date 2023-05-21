object Deps {

    // Gradle plugins
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_version}"
    const val sqlDelight_gradle_plugin = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelightGradleVersion}"

    object Android {

        // Compose
        const val compose_ui = "androidx.compose.ui:ui:${Versions.compose_version}"
        const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
        const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
        const val compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
        const val compose_material = "androidx.compose.material:material:${Versions.compose_version}"
        const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity_version}"
        const val compose_navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
        const val compose_icons_extended = "androidx.compose.material:material-icons-extended:${Versions.compose_version}"
        const val coil_compose = "io.coil-kt:coil-compose:${Versions.coilComposeVersion}"

        // Coroutines
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"

        // Ktor
        const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktorVersion}"

        // SQLdelight
        const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelightVersion}"

        // ViewModel
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_version}"
        const val lifeData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
        const val viewModelSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"


        // Koin
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
        //const val koinAndroidCompose = "io.insert-koin:koin-androidx-compose${Versions.koinVersion}"
        const val koinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"

        // Shared Storage
        const val sharedStorage = "com.linecorp.abc:kmm-shared-storage:${Versions.sharedStorageVersion}"

        // MOKO-MVVM
        const val mokoFlowCompose = "dev.icerock.moko:mvvm-flow-compose:${Versions.mokoMvvmVersion}"
    }

    object Multiplatform {

        // Coroutines
        const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

        // Ktor
        const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
        const val ktorSerialization = "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
        const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"

        // Kotlin Date Time
        const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTimeVersion}"

        // SQLdelight
        const val sqlDelightRuntime = "com.squareup.sqldelight:runtime:${Versions.sqlDelightVersion}"
        const val sqlDelightCoroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelightVersion}"

        // Shared Storage
        const val sharedStorage = "com.linecorp.abc:kmm-shared-storage:${Versions.sharedStorageVersion}"

        // Koin
        const val koinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.koinVersion}"

        // MOKO-MVVM
        const val mokoCore = "dev.icerock.moko:mvvm-core:${Versions.mokoMvvmVersion}"
        const val mokoFLow = "dev.icerock.moko:mvvm-flow:${Versions.mokoMvvmVersion}"
        const val mokoState = "dev.icerock.moko:mvvm-state:${Versions.mokoMvvmVersion}"
        const val mokoFlowRes = "dev.icerock.moko:mvvm-flow-resources:${Versions.mokoMvvmVersion}"
    }

    object IOS {

        // Ktor
        const val ktorIOS = "io.ktor:ktor-client-ios:${Versions.ktorVersion}"

        // SQLdelight
        const val sqlDelightIOSDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelightVersion}"

        // Shared Storage
        const val sharedStorage = "com.linecorp.abc:kmm-shared-storage:${Versions.sharedStorageVersion}"
    }

}