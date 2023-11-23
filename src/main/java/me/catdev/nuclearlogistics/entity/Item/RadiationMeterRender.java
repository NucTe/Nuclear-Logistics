package me.catdev.nuclearlogistics.entity.Item;

import me.catdev.nuclearlogistics.items.custom.RadiationMeterItem;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.decoration.ItemFrame;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RadiationMeterRender extends GeoItemRenderer<RadiationMeterItem> {

    public RadiationMeterRender() {
        super(new RadiationMeterModel());
    }
}
