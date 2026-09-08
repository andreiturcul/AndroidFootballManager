package com.example.footballmanager.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ&\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u0016\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fJ \u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/footballmanager/util/TeamNotificationHelper;", "", "()V", "CHANNEL_ID", "", "CHANNEL_NAME", "nextId", "", "createChannel", "", "context", "Landroid/content/Context;", "notifyPlayerSigned", "playerName", "price", "", "remaining", "notifyTeamSaved", "show", "title", "body", "app_debug"})
public final class TeamNotificationHelper {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "team_actions";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "Team actions";
    private static int nextId = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.footballmanager.util.TeamNotificationHelper INSTANCE = null;
    
    private TeamNotificationHelper() {
        super();
    }
    
    public final void createChannel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void notifyPlayerSigned(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String playerName, double price, double remaining) {
    }
    
    public final void notifyTeamSaved(@org.jetbrains.annotations.NotNull()
    android.content.Context context, double remaining) {
    }
    
    private final void show(android.content.Context context, java.lang.String title, java.lang.String body) {
    }
}