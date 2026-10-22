package at.yedel.fontrast.utils;



import at.yedel.fontrast.mixin.DebugScreenEntriesInvoker;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;



public class InvocationTracker {
    // rdh
    private static final LongArrayFIFOQueue invokes = new LongArrayFIFOQueue();

    public static void init() {
        DebugScreenEntriesInvoker.fontrast$register(Identifier.fromNamespaceAndPath("fontrast", "invocation_tracker"), new InvocationDebugEntry());
    }

    public static void increment() {
        long millis = System.currentTimeMillis();
        invokes.enqueue(millis);
        while (!invokes.isEmpty() && millis - invokes.firstLong() > 1000) {
            invokes.dequeueLong();
        }
    }

    public static class InvocationDebugEntry implements DebugScreenEntry {
        @Override
        public void display(@NonNull DebugScreenDisplayer displayer, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
            displayer.addLine("Fontrast config invocations: " + invokes.size() + "/s");
        }
    }
}
