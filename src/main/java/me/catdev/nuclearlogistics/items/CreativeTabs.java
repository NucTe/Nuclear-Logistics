package me.catdev.nuclearlogistics.items;

import me.catdev.nuclearlogistics.init.ItemsInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabs {

    public static final CreativeModeTab NUCLEARCORE = new CreativeModeTab("nuclearcoretab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemsInit.RADIATION_METER.get());
        }
    };

}
