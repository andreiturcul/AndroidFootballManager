package com.example.footballmanager.ui.teams;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0003\u001a\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0003\u001a\u001c\u0010\u000b\u001a\u00020\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\rH\u0007\u001a\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00012\u0006\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"LEAGUES", "", "", "TeamRow", "", "team", "Lcom/example/footballmanager/data/local/entities/PlayerTeam;", "onClick", "Lkotlin/Function0;", "TeamTrophiesDialog", "onDismiss", "TeamsScreen", "onTeamSelected", "Lkotlin/Function1;", "", "getTeamTrophies", "Lcom/example/footballmanager/ui/teams/TrophyInfo;", "teamName", "league", "app_debug"})
@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
public final class TeamsScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> LEAGUES = null;
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.example.footballmanager.ui.teams.TrophyInfo> getTeamTrophies(@org.jetbrains.annotations.NotNull()
    java.lang.String teamName, @org.jetbrains.annotations.Nullable()
    java.lang.String league) {
        return null;
    }
    
    /**
     * Requirement: 2nd HTTP endpoint is triggered from here (squad lookup per team).
     */
    @androidx.compose.runtime.Composable()
    public static final void TeamsScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onTeamSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TeamTrophiesDialog(com.example.footballmanager.data.local.entities.PlayerTeam team, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TeamRow(com.example.footballmanager.data.local.entities.PlayerTeam team, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}