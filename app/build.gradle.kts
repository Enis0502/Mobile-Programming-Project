plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.mobileprogrammingproject"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.mobileprogrammingproject"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    val nav_version = "2.9.7"
    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")
    // Hilt core library
    implementation("com.google.dagger:hilt-android:2.59.2")

    // Hilt annotation processor (KSP)
    ksp("com.google.dagger:hilt-android-compiler:2.59.2")

    // Hilt + Jetpack Navigation Compose integration
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    val roomVersion = "2.8.4" //use the appropriate version number
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Retrofit - HTTP client abstraction
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp - the underlying HTTP engine
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Logging interceptor - prints requests/responses in Logcat
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:34.14.0"))
    // Auth and Firestore
    implementation("com.google.firebase:firebase-auth:23.2.0")
    implementation("com.google.firebase:firebase-firestore:25.1.4")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}