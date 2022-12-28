ViewLifecycleProperty
=====

ViewLifecycleProperty makes easy declaring the property that has the same lifecycle as Fragment's view.

## Overview

ViewLifecycleProperty is

- lazy initialization
- accessible when Fragment's view isn't null i.e., between onCreateView and onDestroyView
- automatically cleared by null when Fragment's view released i.e., after onDestroyView

## Usage

### val by lazy style

```kotlin
class SampleFragment : Fragment(R.layout.sample_fragment) {
    private val property: SampleProperty by viewLifecycle { SampleProperty(viewLifecycleOwner) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can use property
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // You can use property unlike AutoClearedValue
    }
}
```

### AutoClearedValue style

```kotlin
class SampleFragment : Fragment(R.layout.sample_fragment) {
    private var property: SampleProperty by viewLifecycle()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You must initialize before using property
        property = SampleProperty(viewLifecycleOwner)
        // You can use property
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // You can use property unlike AutoClearedValue
    }
}
```

## Gradle

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.wada811.viewlifecycleproperty/viewlifecycleproperty/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.wada811.viewlifecycleproperty/viewlifecycleproperty)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.wada811.viewlifecycleproperty:viewlifecycleproperty:x.y.z'
}
```

## License

Copyright (C) 2020 wada811

Licensed under the Apache License, Version 2.0
