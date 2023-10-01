plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
        versionCode = 3
        versionName = "1.1.0"

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
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation ("com.google.code.gson:gson:2.8.7")

    //datastore proto
    implementation  ("com.google.protobuf:protobuf-javalite:3.18.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-rxjava3:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")

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
