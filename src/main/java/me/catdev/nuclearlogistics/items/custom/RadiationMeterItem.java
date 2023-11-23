package me.catdev.nuclearlogistics.items.custom;

import me.catdev.nuclearlogistics.blocks.entity.UraniumOreEntity;
import me.catdev.nuclearlogistics.init.ItemsInit;
import me.catdev.nuclearlogistics.utils.RadiationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.awt.*;


public class RadiationMeterItem extends Item implements IAnimatable, ISyncable {
    private static final int ACTIVATE_ANIM_STATE = 0;
    private static final AnimationBuilder ACTIVATE_ANIM = new AnimationBuilder().addAnimation("misc.activate", false);

    public final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private static UraniumOreEntity uraniumOre;

    public RadiationMeterItem(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "Activation", 20, event -> PlayState.CONTINUE));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(stack , (ServerLevel)level);
            // Assign an ID for animations to this stack, then use it to sync an animation to the client
            GeckoLibNetwork.syncAnimation(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), this, id, ACTIVATE_ANIM_STATE);
        }

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ACTIVATE_ANIM_STATE) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, "Activation");

            if (controller.getAnimationState() == AnimationState.Stopped) {
                final Player player = MyClientUtils.getPlayer();

                if (player != null)
                    player.sendMessage(new TextComponent("Activating my item!"));

                // If you don't do this, the popup animation will only play once because the
                // animation will be cached.
                controller.markNeedsReload();
                // Set the animation to open the JackInTheBoxItem which will start playing music
                // and
                // eventually do the actual animation. Also sets it to not loop
                controller.setAnimation(ACTIVATE_ANIM);
            }
        }
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
