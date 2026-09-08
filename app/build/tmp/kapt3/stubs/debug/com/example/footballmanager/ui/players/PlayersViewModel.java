package com.example.footballmanager.ui.players;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0007J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/example/footballmanager/ui/players/PlayersViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/footballmanager/data/repository/FootballDataRepository;", "(Lcom/example/footballmanager/data/repository/FootballDataRepository;)V", "_searchQuery", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "errorMsg", "loading", "", "searchQuery", "Lkotlinx/coroutines/flow/StateFlow;", "getSearchQuery", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "Lcom/example/footballmanager/ui/players/PlayersUiState;", "getUiState", "refreshFromApi", "", "league", "refreshPlayersForTeam", "remoteTeamId", "localTeamId", "", "updateSearchQuery", "query", "app_debug"})
public final class PlayersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.FootballDataRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> loading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> errorMsg = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.players.PlayersUiState> uiState = null;
    
    public PlayersViewModel(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.repository.FootballDataRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.players.PlayersUiState> getUiState() {
        return null;
    }
    
    public final void updateSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
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