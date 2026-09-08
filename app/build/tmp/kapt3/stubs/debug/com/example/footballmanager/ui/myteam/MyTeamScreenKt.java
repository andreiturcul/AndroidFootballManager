package com.example.footballmanager.ui.myteam;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0006\u001a\u00020\u0003H\u0003\u001a\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0007\u001ah\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0016H\u0003\u001a\u001a\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007\u001a(\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"MAX_BUDGET", "", "BudgetCard", "", "totalSpent", "maxBudget", "FootballPitchHeader", "MyTeamScreen", "currentUserId", "", "PlayerPickerDialog", "slot", "Lcom/example/footballmanager/data/local/entities/FormationPosition;", "currentSlotPlayer", "Lcom/example/footballmanager/data/local/entities/Player;", "allPlayers", "", "assignedPlayerIds", "", "onDismiss", "Lkotlin/Function0;", "onPick", "Lkotlin/Function1;", "PositionBadge", "position", "", "modifier", "Landroidx/compose/ui/Modifier;", "SlotRow", "assignedPlayer", "onTap", "app_debug"})
@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
public final class MyTeamScreenKt {
    private static final double MAX_BUDGET = 900.0;
    
    @androidx.compose.runtime.Composable()
    public static final void MyTeamScreen(long currentUserId) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BudgetCard(double totalSpent, double maxBudget) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PositionBadge(@org.jetbrains.annotations.NotNull()
    java.lang.String position, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FootballPitchHeader() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SlotRow(com.example.footballmanager.data.local.entities.FormationPosition slot, com.example.footballmanager.data.local.entities.Player assignedPlayer, kotlin.jvm.functions.Function0<kotlin.Unit> onTap) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PlayerPickerDialog(com.example.footballmanager.data.local.entities.FormationPosition slot, com.example.footballmanager.data.local.entities.Player currentSlotPlayer, double totalSpent, double maxBudget, java.util.List<com.example.footballmanager.data.local.entities.Player> allPlayers, java.util.Set<java.lang.Long> assignedPlayerIds, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super com.example.footballmanager.data.local.entities.Player, kotlin.Unit> onPick) {
    }
}