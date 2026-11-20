package at.yedel.fontrast.modblocks;



import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;



public class ModBlocks implements ModInitializer {
    @Override
    public void onInitialize() {
        for (ModContainer mod: FabricLoader.getInstance().getAllMods()) {
            ModMetadata meta = mod.getMetadata();
            String id = meta.getId();
            String name = meta.getName();
            Block modBlock = registerBlock(id, name);
            registerItem(modBlock, id, name);
        }
    }

    private Block registerBlock(String id, String name) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("modblocks", id));
        Block block = new ModBlock(Block.Properties.of().setId(key), id, name);
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        return block;
    }

    private void registerItem(Block block, String id, String name) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("modblocks", id));
        Item item = new ModBlockItem(block, new Item.Properties().setId(key), id, name);
        Registry.register(BuiltInRegistries.ITEM, key, item);
    }
}
