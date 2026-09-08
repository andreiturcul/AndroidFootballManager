package com.example.footballmanager.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020 H&\u00a8\u0006\""}, d2 = {"Lcom/example/footballmanager/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "eventDao", "Lcom/example/footballmanager/data/local/dao/EventDao;", "formationPositionDao", "Lcom/example/footballmanager/data/local/dao/FormationPositionDao;", "playerDao", "Lcom/example/footballmanager/data/local/dao/PlayerDao;", "playerTeamDao", "Lcom/example/footballmanager/data/local/dao/PlayerTeamDao;", "tacticDao", "Lcom/example/footballmanager/data/local/dao/TacticDao;", "teamFormationDao", "Lcom/example/footballmanager/data/local/dao/TeamFormationDao;", "transferDao", "Lcom/example/footballmanager/data/local/dao/TransferDao;", "transferVotingDao", "Lcom/example/footballmanager/data/local/dao/TransferVotingDao;", "userDao", "Lcom/example/footballmanager/data/local/dao/UserDao;", "userTeamDao", "Lcom/example/footballmanager/data/local/dao/UserTeamDao;", "userTeamPlayerDao", "Lcom/example/footballmanager/data/local/dao/UserTeamPlayerDao;", "userTeamVotingDao", "Lcom/example/footballmanager/data/local/dao/UserTeamVotingDao;", "userTransferProposalDao", "Lcom/example/footballmanager/data/local/dao/UserTransferProposalDao;", "userTransferProposalVotingDao", "Lcom/example/footballmanager/data/local/dao/UserTransferProposalVotingDao;", "voteDao", "Lcom/example/footballmanager/data/local/dao/VoteDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.footballmanager.data.local.entities.Player.class, com.example.footballmanager.data.local.entities.PlayerTeam.class, com.example.footballmanager.data.local.entities.Transfer.class, com.example.footballmanager.data.local.entities.TransferVoting.class, com.example.footballmanager.data.local.entities.User.class, com.example.footballmanager.data.local.entities.UserTeam.class, com.example.footballmanager.data.local.entities.UserTeamPlayer.class, com.example.footballmanager.data.local.entities.UserTeamVoting.class, com.example.footballmanager.data.local.entities.UserTransferProposal.class, com.example.footballmanager.data.local.entities.UserTransferProposalVoting.class, com.example.footballmanager.data.local.entities.TeamFormation.class, com.example.footballmanager.data.local.entities.FormationPosition.class, com.example.footballmanager.data.local.entities.Tactic.class, com.example.footballmanager.data.local.entities.Event.class, com.example.footballmanager.data.local.entities.Vote.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.footballmanager.data.local.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.example.footballmanager.data.local.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.footballmanager.data.local.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.PlayerDao playerDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.PlayerTeamDao playerTeamDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.TransferDao transferDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.TransferVotingDao transferVotingDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.UserTeamDao userTeamDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.UserTeamPlayerDao userTeamPlayerDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.UserTeamVotingDao userTeamVotingDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.UserTransferProposalDao userTransferProposalDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao userTransferProposalVotingDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.TeamFormationDao teamFormationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.FormationPositionDao formationPositionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.TacticDao tacticDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.EventDao eventDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.footballmanager.data.local.dao.VoteDao voteDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/footballmanager/data/local/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/footballmanager/data/local/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.footballmanager.data.local.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}