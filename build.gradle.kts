// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

plugins {
    id("com.android.application") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.android.library") version "7.2.1" apply false
//    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.android.dynamic-feature") version "7.3.0" apply false
}