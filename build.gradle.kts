plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Dodanie wtyczki KSP do projektu
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}