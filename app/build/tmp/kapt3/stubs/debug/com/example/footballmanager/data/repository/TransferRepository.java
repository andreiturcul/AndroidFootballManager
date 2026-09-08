package com.example.footballmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ2\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00160\u0015J2\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001cJ*\u0010\u001d\u001a\u00020\f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00162\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0016H\u0086@\u00a2\u0006\u0002\u0010\"J&\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J&\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/footballmanager/data/repository/TransferRepository;", "", "transferDao", "Lcom/example/footballmanager/data/local/dao/TransferDao;", "transferVotingDao", "Lcom/example/footballmanager/data/local/dao/TransferVotingDao;", "proposalDao", "Lcom/example/footballmanager/data/local/dao/UserTransferProposalDao;", "proposalVotingDao", "Lcom/example/footballmanager/data/local/dao/UserTransferProposalVotingDao;", "(Lcom/example/footballmanager/data/local/dao/TransferDao;Lcom/example/footballmanager/data/local/dao/TransferVotingDao;Lcom/example/footballmanager/data/local/dao/UserTransferProposalDao;Lcom/example/footballmanager/data/local/dao/UserTransferProposalVotingDao;)V", "createTransfer", "", "playerId", "", "fromTeamId", "toTeamId", "price", "", "(JLjava/lang/Long;Ljava/lang/Long;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeProposals", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/footballmanager/data/local/entities/UserTransferProposal;", "observeTransfers", "Lcom/example/footballmanager/data/local/entities/Transfer;", "proposeTransfer", "userId", "(JJLjava/lang/Long;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedIfEmpty", "players", "Lcom/example/footballmanager/data/local/entities/Player;", "teams", "Lcom/example/footballmanager/data/local/entities/PlayerTeam;", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "voteProposal", "proposalId", "upvote", "", "(JJZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "voteTransfer", "transferId", "app_debug"})
public final class TransferRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.TransferDao transferDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.TransferVotingDao transferVotingDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.UserTransferProposalDao proposalDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao proposalVotingDao = null;
    
    public TransferRepository(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.TransferDao transferDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.TransferVotingDao transferVotingDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.UserTransferProposalDao proposalDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao proposalVotingDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.Transfer>> observeTransfers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.UserTransferProposal>> observeProposals() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createTransfer(long playerId, @org.jetbrains.annotations.Nullable()
    java.lang.Long fromTeamId, @org.jetbrains.annotations.Nullable()
    java.lang.Long toTeamId, double price, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object voteTransfer(long transferId, long userId, boolean upvote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object proposeTransfer(long userId, long playerId, @org.jetbrains.annotations.Nullable()
    java.lang.Long fromTeamId, @org.jetbrains.annotations.Nullable()
    java.lang.Long toTeamId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object voteProposal(long proposalId, long userId, boolean upvote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedIfEmpty(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.footballmanager.data.local.entities.Player> players, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.footballmanager.data.local.entities.PlayerTeam> teams, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}