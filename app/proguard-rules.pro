# ProGuard rules for Firebase, Hilt, Retrofit, Gson, Compose, and AndroidX

# Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

# Hilt (Dependency Injection)
-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }
-keep class dagger.hilt.internal.** { *; }
-dontwarn javax.annotation.**

# Retrofit and Gson
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Gson
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Jetpack Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Lifecycle, ViewModel, LiveData
-keep class androidx.lifecycle.** { *; }
-keepclassmembers class * {
    @androidx.lifecycle.* <methods>;
}

# Fragment and Core
-keep class androidx.fragment.** { *; }
-keep class androidx.core.** { *; }

# Activity and Activity Compose
-keep class androidx.activity.** { *; }
-keep class androidx.activity.compose.** { *; }

# Serializable objects
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    private void readObjectNoData();
}

# Preserve line number information for debugging stack traces
-keepattributes SourceFile,LineNumberTable

# Keep annotations for Retrofit, Firebase, and other libraries that rely on reflection
-keepattributes *Annotation*

# Keep inner classes and methods for better debugging
-keepattributes EnclosingMethod
-keepattributes InnerClasses

# Retrofit
-keep class com.nextbigthing.thepunchline.data.** { *; }

# Gson
-keepattributes Signature
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Coroutines
-dontwarn kotlinx.coroutines.**