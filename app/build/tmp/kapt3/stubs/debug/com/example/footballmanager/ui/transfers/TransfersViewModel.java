package com.example.footballmanager.ui.transfers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\'\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001a"}, d2 = {"Lcom/example/footballmanager/ui/transfers/TransfersViewModel;", "Landroidx/lifecycle/ViewModel;", "transferRepository", "Lcom/example/footballmanager/data/repository/TransferRepository;", "footballDataRepository", "Lcom/example/footballmanager/data/repository/FootballDataRepository;", "currentUserId", "", "(Lcom/example/footballmanager/data/repository/TransferRepository;Lcom/example/footballmanager/data/repository/FootballDataRepository;J)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/footballmanager/ui/transfers/TransfersUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "proposeTransfer", "", "playerId", "fromTeamId", "toTeamId", "(JLjava/lang/Long;Ljava/lang/Long;)V", "voteProposal", "proposalId", "up", "", "voteTransfer", "transferId", "app_debug"})
public final class TransfersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.TransferRepository transferRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.footballmanager.data.repository.FootballDataRepository footballDataRepository = null;
    private final long currentUserId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.transfers.TransfersUiState> uiState = null;
    
    public TransfersViewModel(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.repository.TransferRepository transferRepository, @org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.repository.FootballDataRepository footballDataRepository, long currentUserId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.footballmanager.ui.transfers.TransfersUiState> getUiState() {
        return null;
    }
    
    public final void voteTransfer(long transferId, boolean up) {
    }
    
    public final void voteProposal(long proposalId, boolean up) {
    }
    
    public final void proposeTransfer(long playerId, @org.jetbrains.annotations.Nullable()
    java.lang.Long fromTeamId, @org.jetbrains.annotations.Nullable()
    java.lang.Long toTeamId) {
    }
}