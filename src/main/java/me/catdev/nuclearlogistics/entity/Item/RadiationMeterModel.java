package me.catdev.nuclearlogistics.entity.Item;

import me.catdev.nuclearlogistics.NuclearLogistics;
import me.catdev.nuclearlogistics.items.custom.RadiationMeterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RadiationMeterModel extends AnimatedGeoModel<RadiationMeterItem> {
    @Override
    public ResourceLocation getModelResource(RadiationMeterItem object) {
        return new ResourceLocation(NuclearLogistics.MOD_ID, "geo/RadiationMeter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RadiationMeterItem object) {
        return new ResourceLocation(NuclearLogistics.MOD_ID, "textures/models/item/radiation_meter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RadiationMeterItem animatable) {
        return null;
    }
}
