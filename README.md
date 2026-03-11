# 🔍 GitHub Search

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-REST%20API-48B983?style=for-the-badge)
![Material3](https://img.shields.io/badge/Material%203-Design-6200EE?style=for-the-badge)

**Android app that fetches and displays a user's public GitHub repositories via the GitHub REST API.**

---



## 📸 Screenshot

![image](https://user-images.githubusercontent.com/5827265/188474294-4472bcc0-24ee-4ccd-80a8-7cee0372e7fa.png)



---

## 📱 Features

- ✅ Search any GitHub user by username
- ✅ List all public repositories from the searched user
- ✅ Local persistence of the last searched username
- ✅ Ability to reset the saved username
- ✅ Clean and responsive UI with Material Design
- ✅ Error handling for invalid usernames or network failures

---

## 🌐 API

This project consumes the GitHub public REST API:

```
https://api.github.com/users/{username}/repos
```

No authentication required for public repositories.

---

## 🏗️ Architecture

```
com.example.githubsearch/
│
├── 📱 MainActivity.kt                  # Main entry point
│
├── 🧩 ui/                              # UI layer
│   ├── UserInputActivity.kt           # Username input screen
│   └── RepositoryListActivity.kt      # Repository list screen
│
├── 🌐 api/                             # Network layer
│   ├── GithubService.kt               # Retrofit API interface
│   └── RetrofitClient.kt              # Retrofit instance setup
│
├── 📊 model/                           # Data models
│   └── Repository.kt                  # Repository data class
│
└── 💾 data/                            # Local persistence
    └── UserPreferences.kt             # SharedPreferences wrapper
```

### Design Principles Applied

- **Separation of Concerns** — network, UI, and data layers are decoupled
- **Repository Pattern** — data access abstracted from UI
- **Clean Code** — readable and self-documented code

---

## 🔧 Technologies

| Technology      | Version | Description                              |
|-----------------|---------|------------------------------------------|
| **Kotlin**      | 1.9+    | Modern, concise Android language         |
| **Retrofit**    | 2.9+    | Type-safe HTTP client for Android        |
| **Gson**        | Latest  | JSON serialization/deserialization       |
| **RecyclerView**| Latest  | Efficient scrollable list rendering      |
| **SharedPrefs** | —       | Local persistence for username storage   |
| **Android SDK** | 24+     | Compatible with 95%+ of devices          |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or higher
- JDK 17+
- Android SDK 34
- Physical device or emulator with API 24+
- Active internet connection

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/TallesGuerra/desafio-github-search.git
   cd desafio-github-search
   ```

2. **Open in Android Studio**
   - File → Open → Select the project folder

3. **Sync dependencies**
   - Gradle will sync automatically

4. **Run the app**
   - Click **Run** ▶️ or press `Shift + F10`
   - Select a device or emulator

---

## 💡 Concepts Demonstrated

- **REST API integration** with Retrofit and Gson
- **Asynchronous network calls** with Coroutines / Callbacks
- **RecyclerView** with custom adapter for list rendering
- **SharedPreferences** for lightweight local data persistence
- **Multi-screen navigation** between Activities
- **Error handling** for network and API responses
- Material Design components and theming

---

## 🔄 Roadmap

- [x] GitHub username input and persistence
- [x] Fetch and display public repositories via API
- [x] Reset saved username
- [ ] Migrate to Jetpack Compose UI
- [ ] Add repository detail screen
- [ ] Implement search history with Room Database
- [ ] Add ViewModel + StateFlow architecture

---

## 👨‍💻 Author

- 📧 [talles-guerra@hotmail.com](mailto:talles-guerra@hotmail.com)
- 💼 [LinkedIn](https://www.linkedin.com/in/talles-guerra/)
- 🐙 [GitHub](https://github.com/TallesGuerra)

---

**Made with ❤️ and Kotlin**
