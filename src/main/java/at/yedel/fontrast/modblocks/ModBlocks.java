package at.yedel.fontrast.modblocks;



import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



public class ModBlocks implements ModInitializer {
    private static final List<Item> MOD_ITEMS = new ArrayList<>();

    @Override
    public void onInitialize() {
        for (ModContainer mod: FabricLoader.getInstance().getAllMods()) {
            ModMetadata meta = mod.getMetadata();
            String id = meta.getId();
            String name = meta.getName();
            Block modBlock = registerBlock(id, name);
            Item modItem = registerItem(modBlock, id, name);
            MOD_ITEMS.add(modItem);
        }
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .register((creativeTab) -> {
                for (Item modItem: MOD_ITEMS) {
                    creativeTab.accept(modItem);
                }
            });
    }

    private Block registerBlock(String id, String name) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("modblocks", id));
        Block block = new ModBlock(Block.Properties.of().setId(key), id, name);
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        return block;
    }

    private Item registerItem(Block block, String id, String name) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("modblocks", id));
        Item item = new ModBlockItem(block, new Item.Properties().setId(key), id, name);
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }
}
