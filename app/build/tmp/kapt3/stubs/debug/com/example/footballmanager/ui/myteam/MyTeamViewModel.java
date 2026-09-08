package com.example.footballmanager.ui.myteam;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0012J\u0006\u0010!\u001a\u00020\u001eJ\u0016\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0011J\u0006\u0010%\u001a\u00020\u001eJ\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010\'\u001a\u00020\u0007J\u000e\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u0007R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006*"}, d2 = {"Lcom/example/footballmanager/ui/myteam/MyTeamViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/footballmanager/data/repository/UserTeamRepository;", "footballDataRepository", "Lcom/example/footballmanager/data/repository/FootballDataRepository;", "currentUserId", "", "(Lcom/example/footballmanager/data/repository/UserTeamRepository;Lcom/example/footballmanager/data/repository/FootballDataRepository;J)V", "_saveMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_team", "Lcom/example/footballmanager/data/local/entities/UserTeam;", "assignmentsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "", "Lcom/example/footballmanager/data/local/entities/Player;", "positionsFlow", "", "Lcom/example/footballmanager/data/local/entities/FormationPosition;", "teamPlayersFlow", "Lcom/example/footballmanager/data/local/entities/UserTeamPlayer;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/footballmanager/ui/myteam/MyTeamUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "assignPlayer", "", "slotNumber", "player", "clearSaveMessage", "movePlayer", "fromSlot", "toSlot", "saveTeam", "selectFormation", "formationId", "selectTactic", "tacticId", "app_debug"})
public final class MyTeamViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.UserTeamRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.FootballDataRepository footballDataRepository = null;
    private final long currentUserId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.footballmanager.data.local.entities.UserTeam> _team = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _saveMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.UserTeamPlayer>> teamPlayersFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.Integer, com.example.footballmanager.data.local.entities.Player>> assignmentsFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.footballmanager.data.local.entities.FormationPosition>> positionsFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.myteam.MyTeamUiState> uiState = null;
    
    public MyTeamViewModel(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.repository.UserTeamRepository repository, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.repository.FootballDataRepository footballDataRepository, long currentUserId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.myteam.MyTeamUiState> getUiState() {
        return null;
    }
    
    public final void selectFormation(long formationId) {
    }
    
    public final void selectTactic(long tacticId) {
    }
    
    public final void assignPlayer(int slotNumber, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.Player player) {
    }
    
    public final void movePlayer(int fromSlot, int toSlot) {
    }
    
    public final void saveTeam() {
    }
    
    public final void clearSaveMessage() {
    }
}