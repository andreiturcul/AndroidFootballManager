package com.example.footballmanager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0018\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u001aH\'J\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u001a2\u0006\u0010\u001c\u001a\u00020\rH\'\u00a8\u0006\u001d"}, d2 = {"Lcom/example/footballmanager/data/local/dao/PlayerDao;", "", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDuplicates", "", "deleteNonPlayers", "getAllOnce", "", "Lcom/example/footballmanager/data/local/entities/Player;", "getById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "player", "(Lcom/example/footballmanager/data/local/entities/Player;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "players", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "normalizeDef", "normalizeFwd", "normalizeGk", "normalizeMid", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "observeByTeam", "teamId", "app_debug"})
@androidx.room.Dao()
public abstract interface PlayerDao {
    
    @androidx.room.Query(value = "SELECT * FROM players ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.Player>> observeAll();
    
    @androidx.room.Query(value = "SELECT * FROM players WHERE teamId = :teamId ORDER BY position")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.Player>> observeByTeam(long teamId);
    
    @androidx.room.Query(value = "SELECT * FROM players WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.Player> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.footballmanager.data.local.entities.Player> players, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM players")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object count(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM players")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllOnce(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.footballmanager.data.local.entities.Player>> $completion);
    
    @androidx.room.Query(value = "\n        DELETE FROM players \n        WHERE id NOT IN (\n            SELECT MIN(id) \n            FROM players \n            GROUP BY COALESCE(NULLIF(remoteId, \'\'), name || \'_\' || COALESCE(teamId, 0))\n        )\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDuplicates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        DELETE FROM players \n        WHERE LOWER(position) LIKE \'%manager%\' \n           OR LOWER(position) LIKE \'%coach%\' \n           OR LOWER(position) LIKE \'%staff%\' \n           OR LOWER(position) LIKE \'%trainer%\' \n           OR LOWER(position) LIKE \'%physio%\'\n           OR LOWER(position) LIKE \'%scout%\'\n           OR LOWER(position) LIKE \'%analyst%\'\n           OR LOWER(position) LIKE \'%assistant%\'\n           OR LOWER(name) LIKE \'%manager%\'\n           OR LOWER(name) LIKE \'%assistant%\'\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteNonPlayers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE players SET position = \'GK\' WHERE LOWER(position) LIKE \'%keeper%\' OR LOWER(position) LIKE \'%gk%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object normalizeGk(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE players SET position = \'DEF\' WHERE LOWER(position) LIKE \'%defender%\' OR LOWER(position) LIKE \'%back%\' OR LOWER(position) LIKE \'%cb%\' OR LOWER(position) LIKE \'%lb%\' OR LOWER(position) LIKE \'%rb%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object normalizeDef(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE players SET position = \'FWD\' WHERE LOWER(position) LIKE \'%forward%\' OR LOWER(position) LIKE \'%striker%\' OR LOWER(position) LIKE \'%winger%\' OR LOWER(position) LIKE \'%attack%\' OR LOWER(position) LIKE \'%cf%\' OR LOWER(position) LIKE \'%st%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object normalizeFwd(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE players SET position = \'MID\' WHERE position NOT IN (\'GK\', \'DEF\', \'MID\', \'FWD\')")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object normalizeMid(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}