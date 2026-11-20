package at.yedel.fontrast.modblocks;



import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;



public class ModBlock extends Block {
    private final String name;
    private final String id;
    public ModBlock(Block.Properties properties, String id, String name) {
        this.id = id;
        this.name = name;
        super(properties);
    }

    @Override
    public @NonNull MutableComponent getName() {
        return Component.literal(name);
    }
}
