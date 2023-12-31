package me.catdev.nuclearlogistics.init;

import me.catdev.nuclearlogistics.blocks.custom.UraniumOre;
import me.catdev.nuclearlogistics.items.CreativeTabs;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID);

    public static final RegistryObject<Block> URANIUM_ORE = RegisterBlock("uranium_ore", () -> new UraniumOre(
            BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(),
            UniformInt.of(5, 9)), CreativeTabs.NUCLEARCORE);

//    public static final RegistryObject<Block> ELECTRICAL_WIRE = RegisterBlock("electrical_wire", () -> new ElectricalWire(
//            BlockBehaviour.Properties.of(Material.STONE).strength(0f).noOcclusion()), CreativeTabs.NUCLEARCORE);

    public static final RegistryObject<LiquidBlock> URANIUM_LIQUID_BLOCK = BLOCKS.register("uranium_liquid_block",
            () -> new LiquidBlock(FluidInit.SOURCE_URANIUM_LIQUID, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static <T extends Block> RegistryObject<T> RegisterBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        RegisterBlockItem(name, ret, tab);
        return ret;
    }

    public static <T extends Block> RegistryObject<Item> RegisterBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ItemsInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void Register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
