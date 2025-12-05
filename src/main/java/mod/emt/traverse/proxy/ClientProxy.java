package mod.emt.traverse.proxy;

import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("deprecation")
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
        IBlockColor leavesColor = new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
                // Custom Colors
                if (tintIndex == 0 && state.getBlock() == TBXLBlocks.fir_leaves) {
                    return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getStateFromMeta(1), null, null, tintIndex);
                }

                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getDefaultState(), world, pos, tintIndex);
            }
        };

        IItemColor leavesItemColor = new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

                // Custom Colors
                if (tintIndex == 0 && state.getBlock() == TBXLBlocks.fir_leaves) {
                    return 6594930;
                }

                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.LEAVES.getDefaultState(), null, null, tintIndex);
            }
        };

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(leavesColor, TBXLBlocks.fir_leaves);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(leavesItemColor, TBXLBlocks.fir_leaves);
    }
}
