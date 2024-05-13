# rt-music-search

This is a simple project aiming to demonstrate developing skills, it uses https://openwhyd.org/ API.
It consists of a screen to search for songs and clicking in one you are redirected to details,
where you can see an disk art, if available, and a link to listen to it, if available.

## Stack

The main technologies and concepts used are:

- Kotlin
- Compose
- Clean Architecture + MVVM
- Multi module
- Coroutines
- DI with Hilt
- Retrofit for Rest
- Coil for image loading
- Mockk for unit tests
- Build Conventions
- Version Catalog

## Setup

This application was built using the Android Gradle Plugin 8.4.0, Gradle 8.6 and Kotlin 1.9.20.

## Next Steps

- Add code coverage tool (JaCoCo or Kover)
- Do android tests with Espresso
- Snapshot tests (Paparazzi)
- Add localization

## LICENSE

```
The MIT License (MIT)

Copyright (c) 2024 Robson Teixeira

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```