package com.franco227.doob.event;

import com.franco227.doob.item.DoobItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ShearSaplingListener {

    private static ItemEntity createDropItemEntity(Item item, int count, World world, BlockPos pos, Direction dropDirection) {
        return new ItemEntity(
            world,
            (double) pos.getX() + 0.5 + (double) dropDirection.getOffsetX() * 0.65,
            (double) pos.getY() + 0.1,
            (double) pos.getZ() + 0.5 + (double) dropDirection.getOffsetZ() * 0.65,
            new ItemStack(item, count)
        );
    }

    private static void setDropItemVelocity(ItemEntity itemEntity, World world, Direction dropDirection) {
        itemEntity.setVelocity(
            0.05 * (double) dropDirection.getOffsetX() + world.random.nextDouble() * 0.02,
            0.05,
            0.05 * (double) dropDirection.getOffsetZ() + world.random.nextDouble() * 0.02
        );
    }

    public static ActionResult callback(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();

        if (!player.isSpectator() && player.getStackInHand(hand).isOf(Items.SHEARS) && block instanceof SaplingBlock) {
            BlockPos pos = hitResult.getBlockPos();
            Direction direction = hitResult.getSide();
            Direction dropDirection = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;

            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, Blocks.DEAD_BUSH.getDefaultState());

            ItemEntity smallLeavesItemEntity = createDropItemEntity(DoobItems.SMALL_LEAVES, 3, world, pos, dropDirection);
            ItemEntity rootsItemEntity = createDropItemEntity(DoobItems.ROOTS, 1, world, pos, dropDirection);
            setDropItemVelocity(smallLeavesItemEntity, world, dropDirection);
            setDropItemVelocity(rootsItemEntity, world, dropDirection);
            world.spawnEntity(smallLeavesItemEntity);
            world.spawnEntity(rootsItemEntity);

            player.getStackInHand(hand).damage(1, player, playerEntity -> {});
            world.emitGameEvent(player, GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
