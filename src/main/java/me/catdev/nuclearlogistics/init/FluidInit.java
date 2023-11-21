package me.catdev.nuclearlogistics.init;

import me.catdev.nuclearlogistics.NuclearLogistics;
import me.catdev.nuclearlogistics.fluid.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.Keys.FLUIDS, NuclearLogistics.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_URANIUM_LIQUID = FLUIDS.register("uranium_liquid",
            () -> new ForgeFlowingFluid.Source(FluidInit.URANIUM_LIQUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_URANIUM_LIQUID = FLUIDS.register("flowing_uranium_liquid",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.URANIUM_LIQUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties URANIUM_LIQUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.URANIUM_LIQUID_FLUID_TYPE, SOURCE_URANIUM_LIQUID, FLOWING_URANIUM_LIQUID).slopeFindDistance(2)
            .levelDecreasePerBlock(2).block(BlockInit.URANIUM_LIQUID_BLOCK).bucket(ItemsInit.URANIUM_LIQUID_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

}
