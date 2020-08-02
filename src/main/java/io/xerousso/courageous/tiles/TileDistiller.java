//package io.xerousso.courageous.tiles;
//
//import io.xerousso.courageous.blocks.pot.BlockDistiller;
//import io.xerousso.courageous.recipes.distiller.DistillerRecipe;
//import io.xerousso.courageous.recipes.wrappers.FluidRecipeWrapper;
//import io.xerousso.courageous.util.config.ConfigHandler;
//import io.xerousso.courageous.util.pseudofluids.PseudoFluidStack;
//import io.xerousso.courageous.util.pseudofluids.PseudoFluidTank;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.CampfireBlock;
//import net.minecraft.block.HorizontalBlock;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.SUpdateTileEntityPacket;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.tileentity.ITickableTileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.SoundEvents;
//import net.minecraft.world.Explosion;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.fluids.FluidStack;
//import net.minecraftforge.fluids.FluidUtil;
//import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
//import net.minecraftforge.fluids.capability.IFluidHandler;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import java.util.Random;
//
//public class TileDistiller extends TileFluidPot implements ITickableTileEntity {
//    private PseudoFluidTank output;
//    private DistillerRecipe currentRecipe;
//    private int progress;
//    private boolean recipeNeedsChecking = false;
//
//    private final FluidRecipeWrapper wrapper;
//    private static final Random random = new Random();
//
//    public TileDistiller() {
//        super(ModTiles.DISTILLER.get());
//        output = new PseudoFluidTank(1000); //Output tank
//        wrapper = new FluidRecipeWrapper(this, null);
//    }
//
//    @Override
//    public void copyPotInfo(TileFluidPot pot) {
//        super.copyPotInfo(pot);
//        recipeNeedsChecking = true;
//        progress = 0;
//    }
//
//    @Override
//    public void tick() {
//        BlockState blockUnder = world.getBlockState(pos.down());
//        if (recipeNeedsChecking) updateRecipeInfo();
//        //If above a campfire block, make progress 1/nth of the time
//        if (isRecipePresent() && blockUnder.getBlock() == Blocks.CAMPFIRE && blockUnder.get(CampfireBlock.LIT) && random.nextInt(ConfigHandler.COMMON.distillerProgressChance.get()) == 0) {
//            if (++progress > currentRecipe.recipeCycles) {
//                tank.setFluid(currentRecipe.getOutputResidue(wrapper, world));
//                world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0, 0);
//                recipeNeedsChecking = true;
//                progress = 0;
//            } else {
//                PseudoFluidStack outFlow = currentRecipe.getOutputFlowPerCycle(wrapper, world);
//                //Explode if there is still too much fluid in output tank, otherwise fill output tank
//                if (output.fill(outFlow, FluidAction.EXECUTE) != outFlow.getAmount()) {
//                    explode();
//                    return;
//                }
//            }
//            world.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0, 0);
//            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 0.5f, 0.5f, false);
//            SynchroniseTile();
//        }
//        tryFillToTarget();
//    }
//
//    private void tryFillToTarget() {
//        if (output.isEmpty()) return;
//        Direction direction = world.getBlockState(getPos()).get(HorizontalBlock.HORIZONTAL_FACING);
//        LazyOptional<IFluidHandler> targetAttempt = FluidUtil.getFluidHandler(world, pos.offset(direction), direction.getOpposite());
//        if (targetAttempt.isPresent()) {
//            IFluidHandler target = targetAttempt.orElse(output);
//            FluidUtil.tryFluidTransfer(target, output, ConfigHandler.COMMON.distillerMaxDrainPerTick.get(), true);
//        }
//    }
//
//    private void updateRecipeInfo() {
//        currentRecipe = world.getRecipeManager().getRecipe(DistillerRecipe.recipeType, wrapper, world).orElse(null);
//        SynchroniseTile();
//        recipeNeedsChecking = false;
//    }
//
//    //For when someone messes up doing a recipe
//    private void explode() {
//        world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.0F, Explosion.Mode.BREAK);
//    }
//
//    public boolean isRecipePresent() {
//        return currentRecipe != null;
//    }
//
//    @Override
//    public CompoundNBT write(CompoundNBT compound) {
//        super.write(compound);
//        compound.put("output", output.writeToNBT(new CompoundNBT()));
//        compound.putInt("progress", progress);
//        return compound;
//    }
//
//    @Override
//    public void read(CompoundNBT compound) {
//        super.read(compound);
//        output.readFromNBT(compound.getCompound("output"));
//        progress = compound.getInt("progress");
//        recipeNeedsChecking = true;
//    }
//
//    @Override
//    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
//        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && facing == getBlockState().get(BlockDistiller.HORIZONTAL_FACING)) {
//            return fluidHandler.cast();
//        }
//        return super.getCapability(capability, facing);
//    }
//
//    @Override
//    public int fill(FluidStack resource, FluidAction action) {
//        return 0;
//    }
//
//    @Nonnull
//    @Override
//    public PseudoFluidStack drain(FluidStack resource, FluidAction action) {
//        SynchroniseTile();
//        return output.drain(resource, action);
//    }
//
//    @Nonnull
//    @Override
//    public PseudoFluidStack drain(int maxDrain, FluidAction action) {
//        SynchroniseTile();
//        return output.drain(maxDrain, action);
//    }
//
//    @Nonnull
//    @Override
//    public PseudoFluidStack getFluidInTank(int index) {
//        if (index == 0) return super.getFluidInTank(0);
//        return output.getFluidInTank(0);
//    }
//
//    @Override
//    public int getTankCapacity(int index) {
//        if (index == 0) return super.getTankCapacity(0);
//        return output.getCapacity();
//    }
//
//    @Override
//    public boolean isFluidValid(int index, @Nonnull FluidStack stack) {
//        return output.isFluidValid(stack);
//    }
//
//    @Override
//    public net.minecraft.nbt.CompoundNBT getUpdateTag() {
//        return this.write(new CompoundNBT());
//    }
//
//    @Override
//    public void handleUpdateTag(CompoundNBT tag) {
//        this.read(tag);
//    }
//
//    @Override
//    public SUpdateTileEntityPacket getUpdatePacket() {
//        return new SUpdateTileEntityPacket(this.pos, this.getType().hashCode(), this.write(new CompoundNBT()));
//    }
//
//    @Override
//    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
//        CompoundNBT compound = packet.getNbtCompound();
//        this.read(compound);
//    }
//}
