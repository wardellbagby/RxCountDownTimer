# RxCountDownTimer

An Android library written in Kotlin that wraps the CountDownTimer so it can be used with RxJava.

## CountDownTimer?

A CountDownTimer is a class that exists in the Android libs that will, given an amount of time in the future, will regularly post the amount of time left until the moment of time in the future has come to pass. Maybe the [docs will explain it better than I can...](https://developer.android.com/reference/android/os/CountDownTimer.html)

### Okay, but like...who even would need this?

Well...I needed it, first off. I don't like your tone! But more seriously, I really dig Rx and I needed to use a CountDownTimer for a thing so...here we are.

### I wish I was a little bit taller.

I wish I was a baller. I wish I had a function that compiled; I would call it. I wish I had a REPL and a ripple and some treble and a '19 Mac Mini.

## Getting Started

Simply check out this project and import it into Android Studio! Gradle and Android Studio should take care of the rest!

### As A Dependency

In your root `build.gradle`:

```
allprojects {
        repositories {
                ...
                maven { url 'https://jitpack.io' }
        }
}
```

In your app `build.gradle`:

```
depencencies {
        ...
        implementation 'com.github.wardellbagby:RxCountDownTimer:-SNAPSHOT'
}
```

### Building

You can build this lib using:

```
./gradlew build
```

### Code Style

This app uses [ktlint](https://ktlint.github.io/) for enforcing style constraints. Most ktlint errors can be fixed by running

```
./gradlew ktlintFormat
```

but not all. ktlint will output to console any errors it encounters.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for the process for submitting pull requests to this project.

## License

This project is licensed under the LGPL-3.0 License - see the [LICENSE](LICENSE) file for details

## Acknowledgments
* Props to the [Sona Pona](https://github.com/wardellbagby/sona-pona) for being the app I needed to make this lib for!
