package me.catdev.nuclearlogistics;

import com.mojang.logging.LogUtils;
import me.catdev.nuclearlogistics.entity.armor.RadiationSuitRenderer;
import me.catdev.nuclearlogistics.fluid.ModFluidTypes;
import me.catdev.nuclearlogistics.init.*;
import me.catdev.nuclearlogistics.items.custom.RadiationSuitArmorItem;
import me.catdev.nuclearlogistics.world.features.ModConfiguredFeatures;
import me.catdev.nuclearlogistics.world.features.ModPlacedFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.extensions.IForgeBakedModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod(me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID)
public class NuclearLogistics
{
    public static final String MOD_ID = "nuclearlogistics";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NuclearLogistics()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.Register(modEventBus);
        BlockEntityInit.Register(modEventBus);

        ItemsInit.Register(modEventBus);
        EffectsInit.Register(modEventBus);

        ModConfiguredFeatures.Register(modEventBus);
        ModPlacedFeatures.Register(modEventBus);

        FluidInit.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents  {
        @SubscribeEvent
        public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
            GeoArmorRenderer.registerArmorRenderer(RadiationSuitArmorItem.class, new RadiationSuitRenderer());
        }

        @SubscribeEvent
        public static void onClientStartup(FMLClientSetupEvent ev) {
            ItemBlockRenderTypes.setRenderLayer(FluidInit.SOURCE_URANIUM_LIQUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidInit.FLOWING_URANIUM_LIQUID.get(), RenderType.translucent());
        }
    }
}
