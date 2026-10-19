package mod.emt.traverse.proxy;

import mod.emt.traverse.init.TBXLBlocks;
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
            if (tintIndex == 0 && state.getBlock() == TBXLBlocks.douglas_fir_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(2), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == TBXLBlocks.rainbow_eucalyptus_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(3), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == TBXLBlocks.white_fir_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(1), null, null, tintIndex);
            }

            return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getDefaultState(), world, pos, tintIndex);
        };

        IItemColor leavesItemColor = (stack, tintIndex) -> {
            IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

            // Custom Colors
            if (tintIndex == 0 && state.getBlock() == TBXLBlocks.douglas_fir_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(2), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == TBXLBlocks.rainbow_eucalyptus_leaves) {
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(3), null, null, tintIndex);
            }

            if (tintIndex == 0 && state.getBlock() == TBXLBlocks.white_fir_leaves) {
                return 6594930;
            }

            return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getDefaultState(), null, null, tintIndex);
        };

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, TBXLBlocks.douglas_fir_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, TBXLBlocks.rainbow_eucalyptus_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, TBXLBlocks.scalewood_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, TBXLBlocks.wattle_leaves);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, TBXLBlocks.white_fir_leaves);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, TBXLBlocks.douglas_fir_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, TBXLBlocks.rainbow_eucalyptus_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, TBXLBlocks.scalewood_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, TBXLBlocks.wattle_leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, TBXLBlocks.white_fir_leaves);
    }
}
