package me.catdev.nuclearlogistics.entity.armor;

import me.catdev.nuclearlogistics.items.custom.RadiationSuitArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RadiationSuitModel extends AnimatedGeoModel<RadiationSuitArmorItem> {
    @Override
    public ResourceLocation getModelResource(RadiationSuitArmorItem object) {
        return new ResourceLocation(me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID, "geo/radiation_mask.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RadiationSuitArmorItem object) {
        return new ResourceLocation(me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID, "textures/models/armor/mask.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RadiationSuitArmorItem animatable) {
        return new ResourceLocation(me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID, "animations/radiation_suit.animation.json");
    }
}
