package me.catdev.nuclearlogistics.fluid;

import com.mojang.math.Vector3f;
import me.catdev.nuclearlogistics.NuclearLogistics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation URANIUM_LIQUID_OVERLAY_RL = new ResourceLocation("misc/in_uranium_liquid");

    public static DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, NuclearLogistics.MOD_ID);

    public static final RegistryObject<FluidType> URANIUM_LIQUID_FLUID_TYPE = register("uranium_liquid", FluidType.Properties.create()
            .lightLevel(2).density(19).viscosity(6));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        // TODO: change purple to green
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, URANIUM_LIQUID_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
