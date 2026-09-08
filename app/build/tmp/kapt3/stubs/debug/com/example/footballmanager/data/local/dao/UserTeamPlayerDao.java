package com.example.footballmanager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00110\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0012"}, d2 = {"Lcom/example/footballmanager/data/local/dao/UserTeamPlayerDao;", "", "clearSlot", "", "userTeamId", "", "slotNumber", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearTeam", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "entry", "Lcom/example/footballmanager/data/local/entities/UserTeamPlayer;", "(Lcom/example/footballmanager/data/local/entities/UserTeamPlayer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeForTeam", "Lkotlinx/coroutines/flow/Flow;", "", "app_debug"})
@androidx.room.Dao()
public abstract interface UserTeamPlayerDao {
    
    @androidx.room.Query(value = "SELECT * FROM user_team_players WHERE userTeamId = :userTeamId ORDER BY slotNumber")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.UserTeamPlayer>> observeForTeam(long userTeamId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.UserTeamPlayer entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "DELETE FROM user_team_players WHERE userTeamId = :userTeamId AND slotNumber = :slotNumber")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearSlot(long userTeamId, int slotNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM user_team_players WHERE userTeamId = :userTeamId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearTeam(long userTeamId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}