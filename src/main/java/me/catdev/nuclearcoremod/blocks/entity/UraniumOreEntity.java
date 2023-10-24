package me.catdev.nuclearcoremod.blocks.entity;

import me.catdev.nuclearcoremod.NuclearCoreMod;
import me.catdev.nuclearcoremod.blocks.custom.UraniumOre;
import me.catdev.nuclearcoremod.init.BlockEntityInit;
import me.catdev.nuclearcoremod.init.EffectsInit;
import me.catdev.nuclearcoremod.utils.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class UraniumOreEntity extends BlockEntity implements TickableBlockEntity {
    public UraniumOreEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.URANIUM_ORE_BLOCK.get(), pos, state);
    }
    @Override
    public void tick() {
        // Find the radiation source dynamically in the world
        BlockPos radiationSourcePos = findRadiationSourcePosition();

        if (radiationSourcePos != null) {
            // Check player proximity to the radiation source
            Player closestPlayer = level.getNearestPlayer(radiationSourcePos.getX() + 0.5, radiationSourcePos.getY() + 0.5, radiationSourcePos.getZ() + 0.5, -1.0, player -> true);

            if (closestPlayer != null) {
                double distance = closestPlayer.distanceToSqr(radiationSourcePos.getX() + 0.5, radiationSourcePos.getY() + 0.5, radiationSourcePos.getZ() + 0.5);

                double triggerDistance = 10.0 * 10.0; // 10 blocks * 10 blocks = 100.0 (meters)

                if (distance < triggerDistance) {
                    // Calculate the strength of the radiation effect based on the distance
                    int effectStrength = (int)(10.0 - Math.sqrt(distance)); // Adjust this formula as needed

                    if (effectStrength > 0) {
                        closestPlayer.addEffect(new MobEffectInstance(EffectsInit.RADIATION_EFFECT.get(), 200, effectStrength - 1));
                    }
                }
            }
        }
    }

    private BlockPos findRadiationSourcePosition() {
        BlockPos currentPos = worldPosition;
        int searchRadius = 10; // Set your desired search radius

        // Iterate through nearby blocks in a cubic region
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -searchRadius; y <= searchRadius; y++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    BlockPos checkPos = currentPos.offset(x, y, z);
                    BlockState blockState = level.getBlockState(checkPos);
                    Block block = blockState.getBlock();

                    // Check if the block is your radiation source block
                    if (block instanceof UraniumOre) {
                        return checkPos; // Return the radiation source position
                    }
                }
            }
        }

        return null; // No radiation source found within the search radius
    }


}
