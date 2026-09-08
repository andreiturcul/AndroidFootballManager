package com.example.footballmanager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/footballmanager/data/local/dao/UserTeamVotingDao;", "", "countForTeam", "", "userTeamId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findVote", "Lcom/example/footballmanager/data/local/entities/UserTeamVoting;", "userId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "vote", "(Lcom/example/footballmanager/data/local/entities/UserTeamVoting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface UserTeamVotingDao {
    
    @androidx.room.Query(value = "SELECT * FROM user_team_votings WHERE userTeamId = :userTeamId AND userId = :userId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findVote(long userTeamId, long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.UserTeamVoting> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.UserTeamVoting vote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM user_team_votings WHERE userTeamId = :userTeamId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countForTeam(long userTeamId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}