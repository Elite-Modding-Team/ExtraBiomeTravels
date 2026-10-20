package mod.emt.ebt.proxy;

import mod.emt.ebt.init.EBTBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

@SuppressWarnings("deprecation")
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
        IBlockColor leavesColor = (state, world, pos, tintIndex) -> {
            // Custom Colors
            if (tintIndex == 0 && state.getBlock() == EBTBlocks.douglas_fir_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(2), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == EBTBlocks.rainbow_eucalyptus_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(3), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == EBTBlocks.white_fir_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(1), null, null, tintIndex);
            }

            return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getDefaultState(), world, pos, tintIndex);
        };

        IItemColor leavesItemColor = (stack, tintIndex) -> {
            IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

            // Custom Colors
            if (tintIndex == 0 && state.getBlock() == EBTBlocks.douglas_fir_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(2), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == EBTBlocks.rainbow_eucalyptus_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(3), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == EBTBlocks.white_fir_leaves) {
                return 6594930;
            }

            return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getDefaultState(), null, null, tintIndex);
        };

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, EBTBlocks.douglas_fir_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, EBTBlocks.rainbow_eucalyptus_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, EBTBlocks.scalewood_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, EBTBlocks.wattle_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, EBTBlocks.white_fir_leaves);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, EBTBlocks.douglas_fir_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, EBTBlocks.rainbow_eucalyptus_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, EBTBlocks.scalewood_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, EBTBlocks.wattle_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, EBTBlocks.white_fir_leaves);
    }
}
