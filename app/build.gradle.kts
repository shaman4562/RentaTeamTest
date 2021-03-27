plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    defaultConfig {
        applicationId = "com.delta22.rentateamtest"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildToolsVersion = "29.0.3"
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.0")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Test
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.test:core:1.3.0")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.7")
    testImplementation("io.mockk:mockk:1.9.3")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.activity:activity:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    // Navigation
    val navCompVersion = "2.3.4"
    implementation("androidx.navigation:navigation-fragment:$navCompVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navCompVersion")
    implementation("androidx.navigation:navigation-ui:$navCompVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navCompVersion")

    // Room
    val roomVersion = "2.2.6"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-rxjava2:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Dagger 2
    val daggerVersion = "2.24"
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.4.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.3.0")

    // Rx
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.5.2")

}
