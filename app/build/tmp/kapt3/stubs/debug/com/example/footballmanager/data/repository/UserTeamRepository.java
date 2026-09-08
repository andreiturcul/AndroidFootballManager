package com.example.footballmanager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0 J\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0!0 2\u0006\u0010%\u001a\u00020\u0012J\u0012\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0!0 J\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0!0 J\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0!0 2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0 2\u0006\u0010\u001b\u001a\u00020\u0012J\u000e\u0010,\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010-J\u0016\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u00100J*\u00101\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u001a2\b\u0010%\u001a\u0004\u0018\u00010\u00122\b\u00102\u001a\u0004\u0018\u00010\u0012H\u0086@\u00a2\u0006\u0002\u00103J\u0016\u00104\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001e\u00105\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u00106R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/example/footballmanager/data/repository/UserTeamRepository;", "", "userTeamDao", "Lcom/example/footballmanager/data/local/dao/UserTeamDao;", "userTeamPlayerDao", "Lcom/example/footballmanager/data/local/dao/UserTeamPlayerDao;", "formationDao", "Lcom/example/footballmanager/data/local/dao/TeamFormationDao;", "formationPositionDao", "Lcom/example/footballmanager/data/local/dao/FormationPositionDao;", "tacticDao", "Lcom/example/footballmanager/data/local/dao/TacticDao;", "userTeamVotingDao", "Lcom/example/footballmanager/data/local/dao/UserTeamVotingDao;", "(Lcom/example/footballmanager/data/local/dao/UserTeamDao;Lcom/example/footballmanager/data/local/dao/UserTeamPlayerDao;Lcom/example/footballmanager/data/local/dao/TeamFormationDao;Lcom/example/footballmanager/data/local/dao/FormationPositionDao;Lcom/example/footballmanager/data/local/dao/TacticDao;Lcom/example/footballmanager/data/local/dao/UserTeamVotingDao;)V", "assignPlayerToSlot", "", "userTeamId", "", "playerId", "slotNumber", "", "(JJILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearTeamPlayers", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrCreateTeam", "Lcom/example/footballmanager/data/local/entities/UserTeam;", "userId", "defaultName", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeFormations", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/footballmanager/data/local/entities/TeamFormation;", "observePositionsForFormation", "Lcom/example/footballmanager/data/local/entities/FormationPosition;", "formationId", "observeSubmittedTeams", "observeTactics", "Lcom/example/footballmanager/data/local/entities/Tactic;", "observeTeamPlayers", "Lcom/example/footballmanager/data/local/entities/UserTeamPlayer;", "observeUserTeam", "seedIfEmpty", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitTeam", "team", "(Lcom/example/footballmanager/data/local/entities/UserTeam;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFormationAndTactic", "tacticId", "(Lcom/example/footballmanager/data/local/entities/UserTeam;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "voteCount", "voteUserTeam", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserTeamRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.UserTeamDao userTeamDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.UserTeamPlayerDao userTeamPlayerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.TeamFormationDao formationDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.FormationPositionDao formationPositionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.TacticDao tacticDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.local.dao.UserTeamVotingDao userTeamVotingDao = null;
    
    public UserTeamRepository(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.UserTeamDao userTeamDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.UserTeamPlayerDao userTeamPlayerDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.TeamFormationDao formationDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.FormationPositionDao formationPositionDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.TacticDao tacticDao, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.dao.UserTeamVotingDao userTeamVotingDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.TeamFormation>> observeFormations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.Tactic>> observeTactics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.FormationPosition>> observePositionsForFormation(long formationId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.footballmanager.data.local.entities.UserTeam> observeUserTeam(long userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.UserTeamPlayer>> observeTeamPlayers(long userTeamId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.UserTeam>> observeSubmittedTeams() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOrCreateTeam(long userId, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.footballmanager.data.local.entities.UserTeam> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFormationAndTactic(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.UserTeam team, @org.jetbrains.annotations.Nullable()
    java.lang.Long formationId, @org.jetbrains.annotations.Nullable()
    java.lang.Long tacticId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object assignPlayerToSlot(long userTeamId, long playerId, int slotNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearTeamPlayers(long userTeamId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object submitTeam(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.UserTeam team, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object voteUserTeam(long userTeamId, long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object voteCount(long userTeamId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Seeds a few formations/tactics on first run so the "build your team" screen isn't empty.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}