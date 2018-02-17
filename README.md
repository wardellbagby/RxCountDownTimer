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
        implementation 'com.github.wardellbagby:RxCountDownTimer:0.1.0'
}
```

### Using

```kotlin
    //Counts down from 60000 millis with a new value being emitted at least 1000 millis after the last one. Emits in milliseconds.
    RxCountDownTimer.create(60000, 1000)
        .subscribe {
            println("Emitted value: $it")
        }
   
   //Counts down from 60 seconds with a new value being emitted at least 1 second after the last one. Emits in seconds.
   RxCountDownTimer.create(60, 1, TimeUnit.SECONDS)
       .subscribe {
           println("Emitted value: $it")
       }
```

#### "Can't create RxCountDownTimer inside thread that has not called Looper.prepare()"

The CountDownTimer class uses a Handler behind the scenes, which requires that the thread that the Handler is created on has an associated Looper. With Rx, you'll likely run into this situation if you're attempting to create an RxCountDownTimer on one of the default (e.g, `Schedulers.io` or `Schedulers.computation`) schedulers, as they don't have Loopers associated with them. The easiest option is to just have this created on the main thread. Even though this has a requirement to be created on a thread with a looper, its values can be emitted on any Scheduler by following the `create()` call with an `observeOn()` call. 
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
