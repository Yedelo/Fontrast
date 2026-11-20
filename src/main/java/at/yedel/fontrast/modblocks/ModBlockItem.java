package at.yedel.fontrast.modblocks;



import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;



public class ModBlockItem extends BlockItem {
    private final String id;
    private final String name;
    public ModBlockItem(Block block, Item.Properties properties, String id, String name) {
        this.id = id;
        this.name = name;
        super(block, properties);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return Component.literal(name);
    }
}
