package mod.emt.ebt.item;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EBTItemFood extends ItemFood {
    private final EnumRarity rarity;
    private final boolean alwaysEdible;
    private int itemUseDuration;

    public EBTItemFood(String unlocName, int amount, float saturation, boolean isWolfFood, boolean alwaysEdible, EnumRarity rarity) {
        super(amount, saturation, isWolfFood);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.alwaysEdible = alwaysEdible;
        this.rarity = rarity;
    }

    public EBTItemFood(String unlocName, int amount, float saturation, boolean isWolfFood, int eatingSpeed, boolean alwaysEdible, EnumRarity rarity) {
        super(amount, saturation, isWolfFood);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.alwaysEdible = alwaysEdible;
        this.itemUseDuration = eatingSpeed; // 32 by default
        this.rarity = rarity;
    }

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        return this.rarity;
    }

    @Override
    protected void onFoodEaten(@NotNull ItemStack stack, @NotNull World world, @NotNull EntityPlayer player) {
        /*if (this == Items.COOKIE)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5 * 20, 1));
        }*/

        super.onFoodEaten(stack, world, player);
    }

    @Override
    public int getMaxItemUseDuration(@NotNull ItemStack stack) {
        if (itemUseDuration == 0) {
            return 32;
        }

        return itemUseDuration;
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, EntityPlayer player, @NotNull EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (player.canEat(this.alwaysEdible)) {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }
    }
}
