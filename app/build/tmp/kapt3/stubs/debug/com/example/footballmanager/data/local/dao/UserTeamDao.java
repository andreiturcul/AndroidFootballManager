package com.example.footballmanager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\r0\u000bH\'J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lcom/example/footballmanager/data/local/dao/UserTeamDao;", "", "findByOwner", "Lcom/example/footballmanager/data/local/entities/UserTeam;", "userId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "team", "(Lcom/example/footballmanager/data/local/entities/UserTeam;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeByOwner", "Lkotlinx/coroutines/flow/Flow;", "observeSubmitted", "", "update", "", "app_debug"})
@androidx.room.Dao()
public abstract interface UserTeamDao {
    
    @androidx.room.Query(value = "SELECT * FROM user_teams WHERE ownerUserId = :userId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findByOwner(long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.UserTeam> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_teams WHERE ownerUserId = :userId LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.footballmanager.data.local.entities.UserTeam> observeByOwner(long userId);
    
    @androidx.room.Query(value = "SELECT * FROM user_teams WHERE submitted = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.UserTeam>> observeSubmitted();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.UserTeam team, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.UserTeam team, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}