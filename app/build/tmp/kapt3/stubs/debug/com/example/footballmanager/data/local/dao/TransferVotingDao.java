package com.example.footballmanager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/example/footballmanager/data/local/dao/TransferVotingDao;", "", "findVote", "Lcom/example/footballmanager/data/local/entities/TransferVoting;", "transferId", "", "userId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "vote", "(Lcom/example/footballmanager/data/local/entities/TransferVoting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "app_debug"})
@androidx.room.Dao()
public abstract interface TransferVotingDao {
    
    @androidx.room.Query(value = "SELECT * FROM transfer_votings WHERE transferId = :transferId AND userId = :userId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findVote(long transferId, long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.TransferVoting> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.TransferVoting vote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.TransferVoting vote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}