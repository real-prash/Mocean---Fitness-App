plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.moceanskeleton"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moceanskeleton"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(files(fileTree("libs") { include("*.jar") }))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.4.0")
    //Database
    implementation("org.hsqldb:hsqldb:2.4.1")
    implementation("androidx.test.espresso:espresso-intents:3.5.1")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":app"))
}