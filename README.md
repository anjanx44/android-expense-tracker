# Expense Tracker Android App

A simple expense tracker Android application built with Jetpack Compose, Room Database, and MVVM architecture.

## Tech Stack

- **Kotlin**: Main programming language
- **Jetpack Compose**: Modern UI toolkit for building native Android UI
- **Room Database**: Local data persistence
- **MVVM Architecture**: Separation of concerns and testability
- **AndroidX Libraries**: Lifecycle, ViewModel, etc.

## Folder Structure

```
android/
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── anjanx44/
│   │   │   │           └── expensetracker/
│   │   │   │               ├── MainActivity.kt
│   │   │   │               ├── data/
│   │   │   │               │   ├── Expense.kt
│   │   │   │               │   ├── ExpenseDao.kt
│   │   │   │               │   └── ExpenseDatabase.kt
│   │   │   │               ├── repository/
│   │   │   │               │   └── ExpenseRepository.kt
│   │   │   │               ├── ui/
│   │   │   │               │   ├── screens/
│   │   │   │               │   │   ├── AddExpenseScreen.kt
│   │   │   │               │   │   └── ExpenseListScreen.kt
│   │   │   │               │   └── theme/
│   │   │   │               │       └── ExpenseTrackerTheme.kt
│   │   │   │               └── viewmodel/
│   │   │   │                   └── ExpenseViewModel.kt
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       └── values/
│   │   └── test/
│   │       └── java/
│   └── build/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── local.properties
```

## Getting Started

1. **Clone the repository**
2. **Open in Android Studio**
3. **Build and run the app on an emulator or device**

## Features
- Add, view, and track expenses
- Monthly total calculation
- Modern Compose UI

---

Feel free to contribute or open issues!

