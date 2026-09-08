package com.example.footballmanager.ui.players;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0007J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0016"}, d2 = {"Lcom/example/footballmanager/ui/players/PlayersViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/footballmanager/data/repository/FootballDataRepository;", "(Lcom/example/footballmanager/data/repository/FootballDataRepository;)V", "errorMsg", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "loading", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/footballmanager/ui/players/PlayersUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "refreshFromApi", "", "league", "refreshPlayersForTeam", "remoteTeamId", "localTeamId", "", "app_debug"})
public final class PlayersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.FootballDataRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> loading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> errorMsg = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.players.PlayersUiState> uiState = null;
    
    public PlayersViewModel(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.repository.FootballDataRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.players.PlayersUiState> getUiState() {
        return null;
    }
    
    /**
     * Fetches teams (HTTP #1) and caches them via Room.
     */
    public final void refreshFromApi(@org.jetbrains.annotations.NotNull()
    java.lang.String league) {
    }
    
    public final void refreshPlayersForTeam(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteTeamId, long localTeamId) {
    }
}