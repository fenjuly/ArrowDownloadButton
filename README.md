# ArrowDownloadButton
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ArrowDownloadButton-green.svg?style=flat)](https://android-arsenal.com/details/1/2307)

A download button with pretty cool animation, this is an implemention of [https://dribbble.com/shots/2012292-Download-Animation](https://dribbble.com/shots/2012292-Download-Animation)

Enjoying it :)

### ScreenShot
![sreenshot](https://github.com/fenjuly/ArrowDownloadButton/blob/master/screenshots/arrowdownloadbutton.gif)

### Usage
One scene is when you want to download something.
* `startAnimating()`
* `setProgress()`

And if you want to reload
* use`reset()`, the button will recover to initial state.

That's all.

### Get this into your build

Gradle
* Step1
```groovy
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```
* Step2
```groovy
dependencies {
    compile 'com.github.fenjuly:ArrowDownloadButton:9e15b85e8a'
}
```

Maven
* Step1
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

* Step2
```xml
<dependency>
    <groupId>com.github.fenjuly</groupId>
    <artifactId>ArrowDownloadButton</artifactId>
    <version>9e15b85e8a</version>
</dependency>
```
   

### LICENSE
[LICENSE UNDER MIT](https://github.com/fenjuly/ArrowDownloadButton/raw/master/LICENSE)





