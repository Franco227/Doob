package com.franco227.doob.mixin;

import com.franco227.doob.item.DoobItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin {

    @Shadow
    private static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), levelIncreaseChance);
    }

    @Inject(at = @At("TAIL"), method = "registerDefaultCompostableItems")
    private static void registerDefaultCompostableItems(CallbackInfo ci) {
        registerCompostableItem(0.3F, DoobItems.SMALL_LEAVES);
        registerCompostableItem(0.3F, DoobItems.ROOTS);
    }
}
