// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
//    alias(libs.plugins.kotlin.compose) apply false
//    alias(libs.plugins.android.)apply false
    id("com.google.devtools.ksp")  version "2.1.0-1.0.28" apply false
    id("com.google.dagger.hilt.android") version "2.53.1" apply false
    val room_version: String = "2.6.1"
    id ("androidx.room") version room_version apply false
}