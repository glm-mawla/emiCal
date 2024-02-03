plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
//    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.sprial.emical"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "com.sprial.emical"
        minSdk = 21
        targetSdk = 34
        versionCode = 4
        versionName = "2.0.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
//        compose = true
    }
}

sourceSets {

//    main.java.srcDirs += "${protobuf.generatedFilesBaseDir}/main/javalite"
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation ("com.google.code.gson:gson:2.8.9")

    //datastore proto
    implementation  ("com.google.protobuf:protobuf-javalite:3.18.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-rxjava3:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("com.google.firebase:firebase-crashlytics:18.6.0")
    implementation("com.google.firebase:firebase-analytics:21.5.0")

    //unit test
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
//    testImplementation ("androidx.arch.core:core-ktx:2.2.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation ("org.mockito:mockito-core:3.10.0")

    //android test start
    //https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/test-setup
    val androidXTestVersion0 = "1.5.0"
    val testRunnerVersion = "1.5.2"
    val testRulesVersion = "1.5.0"
    val testJunitVersion = "1.5.0"
    val truthVersion = "1.5.0"
    val espressoVersion = "2.0"
    // Core library
    androidTestImplementation ("androidx.test:core:$androidXTestVersion0")
    androidTestImplementation("com.google.truth:truth:1.1.5")

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation ("androidx.test:runner:$testRunnerVersion")
    androidTestImplementation ("androidx.test:rules:$testRulesVersion")

    // Assertions
//    androidTestImplementation ("androidx.test.ext:junit:$testJunitVersion")
    androidTestImplementation ("androidx.test.ext:truth:$truthVersion")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")

    // Espresso dependencies
//    androidTestImplementation "androidx.test.espresso:espresso-core:$espressoVersion"
//    androidTestImplementation "androidx.test.espresso:espresso-contrib:$espressoVersion"
//    androidTestImplementation "androidx.test.espresso:espresso-intents:$espressoVersion"
//    androidTestImplementation "androidx.test.espresso:espresso-accessibility:$espressoVersion"
//    androidTestImplementation "androidx.test.espresso:espresso-web:$espressoVersion"
//    androidTestImplementation "androidx.test.espresso.idling:idling-concurrent:$espressoVersion"
    // dependency to appear on your APK’"s compile classpath or the test APK
    // classpath.
//    androidTestImplementation ("androidx.test.espresso:espresso-idling-resource:$espressoVersion")

    //android test end

}

/*
protobuf {
    protoc {
        path = "/Users/Newton/protoc"
        artifact = "com.google.protobuf:protoc:3.21.7"
    }

    plugins {
        */
/*javalite {
            // The codegen for lite comes as a separate artifact
            artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0"
        }*//*

    }

    generateProtoTasks {
        all().forEach { task ->
            */
/*task.plugins {
                create("kotlin")
                create("java")
            }*//*

            */
/*task.builtins {
                remove(java)
            }

            task.plugins {
                javalite {}
            }*//*

        }
    }
}*/
