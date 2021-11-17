# PaltaLib

Simple wrapper around [Amplitude-Android] (https://github.com/amplitude/Amplitude-Android)

### Gradle

Add dependencies:

```groovy
dependencies {
    implementation 'com.paltabrain:analytics:1.0.0'
}
```

Make sure that you have `mavenCentral()` in the list of repositories:

```
repository {
    mavenCentral()
}
```

### Example

Amplitude
```
val client = Amplitude.getInstance()
    .initialize(getApplicationContext(), "YOUR_API_KEY_HERE")
    .enableForegroundTracking(application)
```
```
client.logEvent("Button Clicked");
```

PaltaAnalytics

```
PaltaAnalytics.instance.initialize(applicationContext, "YOUR_API_KEY_HERE")
```

```
PaltaAnalytics.instance.logEvent("Button Clicked")
```