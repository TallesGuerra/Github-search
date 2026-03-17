# 🔍 GitHub Search

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-REST%20API-48B983?style=for-the-badge)
![Material3](https://img.shields.io/badge/Material%203-Design-6200EE?style=for-the-badge)

Android app that fetches and displays public repositories from any GitHub user via the GitHub REST API, with local persistence of the last searched username.

---

## 📸 Screenshot

<!-- Adiciona aqui um screenshot do teu app -->

---

## 📱 Features

- ✅ Search any GitHub user by username
- ✅ List all public repositories from the searched user
- ✅ Local persistence of the last searched username
- ✅ Ability to reset the saved username
- ✅ Error handling for invalid usernames or network failures
- ✅ Clean UI with Material Design 3

---

## 🏗️ Architecture
```
com.example.githubsearch/
│
├── 📱 MainActivity.kt
│
├── 🧩 ui/
│   ├── UserInputActivity.kt
│   └── RepositoryListActivity.kt
│
├── 🌐 api/
│   ├── GithubService.kt
│   └── RetrofitClient.kt
│
├── 📊 model/
│   └── Repository.kt
│
└── 💾 data/
    └── UserPreferences.kt
```

### Design Principles Applied

- **Separation of Concerns** — network, UI, and data layers are decoupled
- **Repository Pattern** — data access abstracted from UI
- **Clean Code** — readable and self-documented code

---

## 🌐 API

Consumes the GitHub public REST API — no authentication required:
```
GET https://api.github.com/users/{username}/repos
```

---

## 🔧 Tech Stack

| Technology | Description |
|---|---|
| **Kotlin 1.9+** | Modern, concise Android language |
| **Retrofit 2.9+** | Type-safe HTTP client |
| **Gson** | JSON serialization |
| **RecyclerView** | Efficient list rendering |
| **SharedPreferences** | Lightweight local persistence |
| **Android SDK 24+** | Compatible with 95%+ of devices |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or higher
- JDK 17+
- Android SDK 34
- Device or emulator with API 24+

### Steps

1. Clone the repository
```bash
   git clone https://github.com/TallesGuerra/Github-search.git
```
2. Open in Android Studio → File → Open → Select the project folder
3. Let Gradle sync automatically
4. Click Run ▶️ or press `Shift + F10`

---

## 💡 Concepts Demonstrated

- REST API integration with Retrofit and Gson
- Asynchronous network calls with Coroutines
- RecyclerView with custom adapter
- SharedPreferences for local persistence
- Multi-screen navigation between Activities
- Error handling for network and API responses

---

## 🔄 Roadmap

- [x] GitHub username input and persistence
- [x] Fetch and display public repositories
- [x] Reset saved username
- [ ] Migrate UI to Jetpack Compose
- [ ] Add ViewModel + StateFlow
- [ ] Repository detail screen
- [ ] Search history with Room Database

---

## 👨‍💻 Author

- 📧 [talles-guerra@hotmail.com](mailto:talles-guerra@hotmail.com)
- 💼 [LinkedIn](https://www.linkedin.com/in/talles-guerra/)
- 🐙 [GitHub](https://github.com/TallesGuerra)

---

**Made with ❤️ and Kotlin**
