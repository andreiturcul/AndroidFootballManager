package com.example.footballmanager;

/**
 * Simple, dependency-free service locator. Keeps the sample easy to read
 * without pulling in Hilt/Koin, while still giving every screen a single
 * shared instance of the database, session manager and repositories.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\b\u001a\u0004\b\u001f\u0010 \u00a8\u0006%"}, d2 = {"Lcom/example/footballmanager/FootballApp;", "Landroid/app/Application;", "()V", "authRepository", "Lcom/example/footballmanager/data/repository/AuthRepository;", "getAuthRepository", "()Lcom/example/footballmanager/data/repository/AuthRepository;", "authRepository$delegate", "Lkotlin/Lazy;", "database", "Lcom/example/footballmanager/data/local/AppDatabase;", "getDatabase", "()Lcom/example/footballmanager/data/local/AppDatabase;", "database$delegate", "footballDataRepository", "Lcom/example/footballmanager/data/repository/FootballDataRepository;", "getFootballDataRepository", "()Lcom/example/footballmanager/data/repository/FootballDataRepository;", "footballDataRepository$delegate", "sessionManager", "Lcom/example/footballmanager/data/datastore/SessionManager;", "getSessionManager", "()Lcom/example/footballmanager/data/datastore/SessionManager;", "sessionManager$delegate", "transferRepository", "Lcom/example/footballmanager/data/repository/TransferRepository;", "getTransferRepository", "()Lcom/example/footballmanager/data/repository/TransferRepository;", "transferRepository$delegate", "userTeamRepository", "Lcom/example/footballmanager/data/repository/UserTeamRepository;", "getUserTeamRepository", "()Lcom/example/footballmanager/data/repository/UserTeamRepository;", "userTeamRepository$delegate", "onCreate", "", "Companion", "app_debug"})
public final class FootballApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy sessionManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy footballDataRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy transferRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userTeamRepository$delegate = null;
    private static com.example.footballmanager.FootballApp INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.footballmanager.FootballApp.Companion Companion = null;
    
    public FootballApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.local.AppDatabase getDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.datastore.SessionManager getSessionManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.repository.AuthRepository getAuthRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.repository.FootballDataRepository getFootballDataRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.repository.TransferRepository getTransferRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.repository.UserTeamRepository getUserTeamRepository() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/footballmanager/FootballApp$Companion;", "", "()V", "<set-?>", "Lcom/example/footballmanager/FootballApp;", "INSTANCE", "getINSTANCE", "()Lcom/example/footballmanager/FootballApp;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.footballmanager.FootballApp getINSTANCE() {
            return null;
        }
    }
}