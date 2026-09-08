package com.example.footballmanager.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007\u00a8\u0006\r"}, d2 = {"Lcom/example/footballmanager/data/local/Converters;", "", "()V", "fromEventName", "", "name", "Lcom/example/footballmanager/data/local/entities/EventName;", "fromTacticStyle", "style", "Lcom/example/footballmanager/data/local/entities/TacticStyle;", "toEventName", "value", "toTacticStyle", "app_debug"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromTacticStyle(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.TacticStyle style) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.local.entities.TacticStyle toTacticStyle(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromEventName(@org.jetbrains.annotations.NotNull()
    com.example.footballmanager.data.local.entities.EventName name) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.footballmanager.data.local.entities.EventName toEventName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
}