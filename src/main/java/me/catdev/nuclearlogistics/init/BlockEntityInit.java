package me.catdev.nuclearlogistics.init;

import me.catdev.nuclearlogistics.blocks.entity.UraniumOreEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES  =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID);

    public static final RegistryObject<BlockEntityType<UraniumOreEntity>> URANIUM_ORE_BLOCK =
            BLOCK_ENTITIES .register("uranium_ore",
                    () -> BlockEntityType.Builder.of(UraniumOreEntity::new, BlockInit.URANIUM_ORE.get())
                            .build(null));

    public static void Register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
