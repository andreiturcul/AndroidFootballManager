# Football Manager

!!! virtualization doesn't work on arm processors :(

Android app for a simple fantasy football manager. Browse real teams and players, propose and vote on transfers, and build your own squad with a formation and tactic.

Built with **Kotlin** and **Jetpack Compose**. Team and player data come from [TheSportsDB](https://www.thesportsdb.com/) (free public test API, no key required). Accounts, squads, and votes are stored locally with Room.

## Features

- Register / login (session kept with DataStore)
- Fetch league teams and squads over HTTP, then browse them from the local database
- Player details and transfer proposals
- Vote on transfers
- My Team: pick a formation and tactic, assign players to slots

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


## Requirements

- Android Studio (Koala / Ladybug or newer)
- JDK 17 (bundled with Android Studio is fine)
- Android emulator or device with **API 24+**
- Internet on first Gradle sync, and when fetching teams/players

## How to run

1. Open this folder in Android Studio.
2. Wait for Gradle to sync (the wrapper downloads Gradle on first sync).
3. Select an emulator or a connected device.
4. Run the app (`Shift+F10` / the green Run button).

From a terminal:

```bash
./gradlew assembleDebug
```

On Windows: `gradlew.bat assembleDebug`

Install and launch from Android Studio, or install the APK from `app/build/outputs/apk/debug/`.

## First use

1. **Register** an account (stored on the device).
2. Open **Teams**, pick a league, tap **Fetch**, then tap a team to load its squad.
3. Check **Players** for the imported list.
4. Use **My Team** to set formation/tactic and fill slots.
5. Propose a transfer from a player’s detail screen, then vote on it in **Transfers**.

## Stack

Kotlin, Jetpack Compose, Navigation, Room, Retrofit, DataStore, Coil, Material 3.




