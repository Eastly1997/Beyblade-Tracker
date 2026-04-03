# Beyblade Tracker

A professional-grade, community-driven battle ladder and record tracking application for Beyblade enthusiasts.

## 🏗 Architecture

This project follows **Clean Architecture** principles and is fully **Modularized** to ensure scalability, testability, and clear separation of concerns.

### Module Structure
- **`:app`**: The "Empty Shell" orchestrator. Handles Splash, Home, and Navigation composition.
- **`:common`**: Infrastructure and shared components. Contains the Design System (Theme, Colors, Typography), base DI (Koin), and common utilities like `IdProvider`.
- **`:feature:auth`**: Encapsulated Authentication logic using Google Sign-In and Firebase Auth.
- **`:feature:tournament`**: Core business logic for tournament creation, management, and tracking.

### Design Patterns
- **MVI (Model-View-Intent)**: Unidirectional Data Flow using `StateFlow` and `Channel` for side effects.
- **Dependency Injection**: Powered by **Koin** for lightweight, Kotlin-first DI.
- **Navigation Extensions**: Each feature module provides its own `NavGraph` extension, keeping the `:app` module decoupled from feature internals.
- **Repository Pattern**: Abstracting data sources (Firestore, Local) from business rules.

## 🚀 Tech Stack

- **Language**: Kotlin 2.x
- **UI Framework**: Jetpack Compose (Material 3)
- **Dependency Injection**: Koin
- **Database/Backend**: Firebase (Auth, Firestore)
- **Architecture**: Multi-Module Clean Architecture + MVI
- **Navigation**: Compose Navigation with Feature-based Decoupling
- **Animations**: Lottie Compose

## 🛠 Setup & Development

1. **Gradle Sync**: The project uses Gradle Version Catalogs (`libs.versions.toml`) for centralized dependency management.
2. **Firebase**: Ensure you have a valid `google-services.json` in the `/app` directory.
3. **Architecture Rules**:
    - **Domain Layer**: Must be pure Kotlin. No Android or Firebase dependencies allowed.
    - **Data Layer**: Contains Repository implementations (marked as `internal`) and Data Sources.
    - **Presentation Layer**: ViewModels handle `Intents` and emit `State` / `Effect`.

## 📈 Status

**Current Phase**: 1 - MVP Core Infrastructure (Completed)
- [x] Multi-module Project Setup
- [x] Design System in `:common`
- [x] Auth Module with Google Sign-In
- [x] Tournament Management Infrastructure
- [x] Decoupled Navigation System
- [ ] Tournament Dashboard UI
- [ ] Battle Ladder Logic

---
*Practicing professional Android Architecture.*