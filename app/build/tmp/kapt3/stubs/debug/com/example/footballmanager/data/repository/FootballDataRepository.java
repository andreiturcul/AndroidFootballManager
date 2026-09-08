package com.example.footballmanager.data.repository;

/**
 * Talks to the remote API (2 HTTP endpoints), deserializes the JSON and stores
 * the result into the local Room database, which the UI then observes.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001f0\u001eJ\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001f0\u001e2\u0006\u0010!\u001a\u00020\nJ\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001f0\u001eJ,\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010)J$\u0010*\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010+\u001a\u00020\u0019H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010-J\u000e\u0010.\u001a\u00020\u000fH\u0082@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006/"}, d2 = {"Lcom/example/footballmanager/data/repository/FootballDataRepository;", "", "api", "Lcom/example/footballmanager/data/remote/ApiService;", "playerTeamDao", "Lcom/example/footballmanager/data/local/dao/PlayerTeamDao;", "playerDao", "Lcom/example/footballmanager/data/local/dao/PlayerDao;", "(Lcom/example/footballmanager/data/remote/ApiService;Lcom/example/footballmanager/data/local/dao/PlayerTeamDao;Lcom/example/footballmanager/data/local/dao/PlayerDao;)V", "addManualPlayer", "", "player", "Lcom/example/footballmanager/data/local/entities/Player;", "(Lcom/example/footballmanager/data/local/entities/Player;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanupAndSeed", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlayer", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTeam", "Lcom/example/footballmanager/data/local/entities/PlayerTeam;", "isNonPlayer", "", "name", "", "position", "normalizePosition", "rawPosition", "observePlayers", "Lkotlinx/coroutines/flow/Flow;", "", "observePlayersForTeam", "teamId", "observeTeams", "refreshPlayers", "Lkotlin/Result;", "", "remoteTeamId", "localTeamId", "refreshPlayers-0E7RQCE", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshTeams", "league", "refreshTeams-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedTopTeamsAndPlayers", "app_debug"})
public final class FootballDataRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.remote.ApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.PlayerTeamDao playerTeamDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.PlayerDao playerDao = null;
    
    public FootballDataRepository(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.remote.ApiService api, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.PlayerTeamDao playerTeamDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.PlayerDao playerDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.PlayerTeam>> observeTeams() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.Player>> observePlayers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.Player>> observePlayersForTeam(long teamId) {
        return null;
    }
    
    private final boolean isNonPlayer(java.lang.String name, java.lang.String position) {
        return false;
    }
    
    private final java.lang.String normalizePosition(java.lang.String rawPosition) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cleanupAndSeed(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object seedTopTeamsAndPlayers(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addManualPlayer(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPlayer(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.Player> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTeam(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.PlayerTeam> $completion) {
        return null;
    }
}