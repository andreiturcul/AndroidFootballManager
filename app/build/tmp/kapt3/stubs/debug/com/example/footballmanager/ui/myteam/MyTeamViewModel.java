package com.example.footballmanager.ui.myteam;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\rJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0007J\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u0007J\u0006\u0010!\u001a\u00020\u001aR \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\""}, d2 = {"Lcom/example/footballmanager/ui/myteam/MyTeamViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/footballmanager/data/repository/UserTeamRepository;", "footballDataRepository", "Lcom/example/footballmanager/data/repository/FootballDataRepository;", "currentUserId", "", "(Lcom/example/footballmanager/data/repository/UserTeamRepository;Lcom/example/footballmanager/data/repository/FootballDataRepository;J)V", "_assignments", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "Lcom/example/footballmanager/data/local/entities/Player;", "_team", "Lcom/example/footballmanager/data/local/entities/UserTeam;", "positionsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/footballmanager/data/local/entities/FormationPosition;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/footballmanager/ui/myteam/MyTeamUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "assignPlayer", "", "slotNumber", "player", "selectFormation", "formationId", "selectTactic", "tacticId", "submitTeam", "app_debug"})
public final class MyTeamViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.UserTeamRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.FootballDataRepository footballDataRepository = null;
    private final long currentUserId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.footballmanager.data.local.entities.UserTeam> _team = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.Integer, com.example.footballmanager.data.local.entities.Player>> _assignments = null;
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
    
    public final void submitTeam() {
    }
}