package me.catdev.nuclearlogistics.items.custom;

import me.catdev.nuclearlogistics.blocks.entity.UraniumOreEntity;
import me.catdev.nuclearlogistics.init.ItemsInit;
import me.catdev.nuclearlogistics.utils.RadiationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class RadiationMeterItem extends Item {

    private static UraniumOreEntity uraniumOre;

    public RadiationMeterItem(Properties pProperties) {
        super(pProperties);
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.START) {
            // Check if the player is holding your RadiationMeterItem in the main hand (right hand)
            ItemStack heldItem = event.player.getMainHandItem();
            Item radiationMeterItem = ItemsInit.RADIATION_METER.get(); // Get the RadiationMeterItem

            if (heldItem.isEmpty() && heldItem.getItem() == radiationMeterItem) {
                // Do something when the RadiationMeterItem is in the player's right hand
                // For example, check the distance to the radiation source

                double strength = RadiationUtils.GetRadiationStrength(Vec3.atCenterOf(event.player.blockPosition()));
                Component actionBarMessage = Component.translatable("commands.title.show.actionbar.single.nuclearcoremod.radiationmeter", String.format("%.2f", strength));
                event.player.displayClientMessage(actionBarMessage, true);

            }
        }
    }

}
