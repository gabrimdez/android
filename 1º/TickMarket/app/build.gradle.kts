plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.tickmarket"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.tickmarket"
        minSdk = 28
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
        viewBinding = true
    }
}

dependencies {

    // Glide correcto y versión estable
    // ✅ Librería GridView con header y footer
    implementation("in.srain.cube:grid-view-with-header-footer:1.0.12")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}