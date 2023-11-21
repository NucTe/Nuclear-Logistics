package me.catdev.nuclearlogistics.init;

import me.catdev.nuclearlogistics.items.CreativeTabs;
import me.catdev.nuclearlogistics.items.ModArmorMaterials;
import me.catdev.nuclearlogistics.items.custom.RadiationMeterItem;
import me.catdev.nuclearlogistics.items.custom.RadiationSuitArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, me.catdev.nuclearlogistics.NuclearLogistics.MOD_ID);

    public static final RegistryObject<Item> RADIATION_METER = ITEMS.register("radiationmeter", () -> new RadiationMeterItem(new Item.Properties().tab(CreativeTabs.NUCLEARCORE)));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.NUCLEARCORE)));
    public static final RegistryObject<Item> RADIATION_MASK = ITEMS.register("radiation_mask", () -> new RadiationSuitArmorItem(ModArmorMaterials.RADIATION_SUIT, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeTabs.NUCLEARCORE)));
    public static final RegistryObject<Item> URANIUM_LIQUID_BUCKET = ITEMS.register("uranium_liquid_bucket", () ->
            new BucketItem(FluidInit.SOURCE_URANIUM_LIQUID, new Item.Properties().tab(CreativeTabs.NUCLEARCORE).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
