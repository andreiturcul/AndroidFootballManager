# Football Manager — Android Final Project

A Kotlin + Jetpack Compose Android app for building a fantasy football manager:
browse real teams/players pulled from a public API, propose and vote on transfers,
and assemble your own squad with a formation and tactic.

## How this maps to the grading rubric (barem.png)

| Requirement | Where it lives |
|---|---|
| Provide GIT access | See "Git access" section below |
| Activity, Screens & Navigation Component | Single `MainActivity` + `androidx.navigation:navigation-compose` graph in `ui/navigation/NavGraph.kt` (Splash → Login/Register → Home) with a **nested** NavHost inside `ui/home/HomeScreen.kt` for the bottom-nav tabs (Teams, Players, Transfers, My Team, Profile) |
| Authentication (Login + Register) | `ui/auth/LoginScreen.kt`, `RegisterScreen.kt` + `AuthRepository` (Room-backed, SHA-256 hashed passwords) |
| Store data into local DB + scrollable list | Room `AppDatabase` (15 entities matching your schema) — e.g. `PlayersScreen.kt` renders a `LazyColumn` of `Player` rows read live from Room |
| Data in SharedPreferences/DataStore | `data/datastore/SessionManager.kt` (Jetpack DataStore) persists the logged-in user id across restarts, used by `SplashScreen` |
| HTTP requests (min. 2) | `data/remote/ApiService.kt` — `searchTeams()` and `lookupPlayers()` against TheSportsDB's free public test endpoint. Both responses are deserialized (Gson) and written into Room (`FootballDataRepository`) |
| User friendly app | Material 3, bottom navigation, loading/error states, empty states, dialogs |
| No crash | Defensive null-handling throughout (`Result`, `runCatching`, nullable FKs, `mapNotNull` on DTOs) |

## Database schema

All 15 tables you specified are implemented as Room `@Entity` classes under
`data/local/entities/`: `Player`, `PlayerTeam`, `Transfer`, `TransferVoting`,
`User`, `UserTeam`, `UserTeamPlayer`, `UserTeamVoting`, `UserTransferProposal`,
`UserTransferProposalVoting`, `TeamFormation`, `FormationPosition`, `Tactic`,
`Event`, `Vote`.

A couple of small, clearly-marked additions were made where the original
schema was ambiguous, so the tables have working foreign keys:
- `TransferVoting` gained a `transferId` column (which transfer is being voted on).
- `UserTransferProposalVoting` gained a `proposalId` column (same reason).
- `User.passwordHash` stores a SHA-256 hash, never the raw password.

## Architecture

```
ui/            Jetpack Compose screens + ViewModels (MVVM, one package per feature)
data/local/    Room entities, DAOs, AppDatabase, TypeConverters
data/remote/   Retrofit ApiService, DTOs, RetrofitInstance
data/repository/ Repositories mediating between Room / Retrofit / DataStore and ViewModels
data/datastore/  SessionManager (Jetpack DataStore)
util/          Password hashing, generic ViewModel factory, Resource wrapper
```

No DI framework is used (keeps the project easy to read for grading) — a tiny
service locator (`FootballApp`) exposes shared singletons (database, session
manager, repositories) that each screen's `viewModelFactory { ... }` pulls from.

## Running the project

1. Open the project root folder in Android Studio (Koala/Ladybug or newer).
2. Let Gradle sync — it will download the Gradle 8.7 wrapper distribution
   automatically the first time (internet required for the very first sync).
3. Run on an emulator or device with API 24+.
4. On first launch: **Register** an account (stored locally in Room), then
   go to the **Teams** tab, pick a league, tap **Fetch** (HTTP request #1),
   tap a team card to load its squad (HTTP request #2), then check the
   **Players** tab to see the scrollable, DB-backed list.
5. Use **My Team** to pick a formation/tactic and assign players to slots.
6. Use a player's detail screen to **propose a transfer**, then vote on it
   from the **Transfers** tab.

No API key is required — the app uses TheSportsDB's free public test key (`3`).
If you want a private/production key, replace it in
`data/remote/ApiService.kt`.

## Git access

To grant access to this repository:
1. Push this project to a private GitHub/GitLab repo.
2. Add your instructor's account as a collaborator (or make the repo public
   for grading), per the rubric's "Provide GIT access" line.

```bash
cd FootballManagerApp
git init
git add .
git commit -m "Initial commit: Football Manager Android app"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```

## Notes / assumptions

- Player market prices aren't provided by the free API, so they're
  simulated (`€5M–€50M`) when a squad is imported — clearly marked in
  `FootballDataRepository.refreshPlayers()`.
- `PlayerTeam.ucl` / `.cup` default to `false` since the API doesn't expose
  competition participation; you can toggle these manually if needed for a
  demo.
