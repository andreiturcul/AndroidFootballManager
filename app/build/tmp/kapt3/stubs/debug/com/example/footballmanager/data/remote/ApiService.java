package com.example.footballmanager.data.remote;

/**
 * Two HTTP requests are used by the app (rubric requirement, min. 2):
 *  1) searchTeams  -> populates PlayerTeam table
 *  2) lookupPlayers -> populates Player table for a given team
 *
 * Backed by TheSportsDB's free public test endpoint (key "3", no registration
 * required). Swap BASE_URL / key for a production key if needed.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/example/footballmanager/data/remote/ApiService;", "", "lookupPlayers", "Lcom/example/footballmanager/data/remote/dto/PlayersResponse;", "teamId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchTeams", "Lcom/example/footballmanager/data/remote/dto/TeamsResponse;", "league", "Companion", "app_debug"})
public abstract interface ApiService {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://www.thesportsdb.com/";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.footballmanager.data.remote.ApiService.Companion Companion = null;
    
    @retrofit2.http.GET(value = "api/v1/json/3/search_all_teams.php")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchTeams(@retrofit2.http.Query(value = "l")
    @org.jetbrains.annotations.NotNull()
    java.lang.String league, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.remote.dto.TeamsResponse> $completion);
    
    @retrofit2.http.GET(value = "api/v1/json/3/lookup_all_players.php")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object lookupPlayers(@retrofit2.http.Query(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String teamId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.remote.dto.PlayersResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/footballmanager/data/remote/ApiService$Companion;", "", "()V", "BASE_URL", "", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://www.thesportsdb.com/";
        
        private Companion() {
            super();
        }
    }
}