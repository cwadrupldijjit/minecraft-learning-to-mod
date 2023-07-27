package com.cwdj.learningtomod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import com.cwdj.learningtomod.blocks.entities.CounterEntity;
import com.cwdj.learningtomod.groups.PlateupFoodItemGroup;
import com.cwdj.learningtomod.groups.PlateupToolItemGroup;
import com.cwdj.learningtomod.items.CwdjBlockItem;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class Counter extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier("cwdj", "counter");
    public static final DirectionProperty FACING = DirectionProperty.of("facing");
    private static Counter REGISTRY_ENTRY;
    private static CwdjBlockItem<Counter> ITEM_REGISTRY_ENTRY;
    
    public static CwdjBlockItem<Counter> registerBlock() {
        if (REGISTRY_ENTRY == null) {
            REGISTRY_ENTRY = new Counter(FabricBlockSettings.create());
        }
        
        if (ITEM_REGISTRY_ENTRY == null) {
            ITEM_REGISTRY_ENTRY = new CwdjBlockItem<Counter>(REGISTRY_ENTRY);
        }
        
        if (!Registries.BLOCK.containsId(ID)) {
            Registry.register(Registries.BLOCK, ID, REGISTRY_ENTRY);
            Registry.register(Registries.ITEM, ID, ITEM_REGISTRY_ENTRY);
        }
        
        return ITEM_REGISTRY_ENTRY;
    }
    
    public Counter(Settings settings) {
        super(settings);
        
        setDefaultState(getDefaultState().with(FACING, Direction.EAST));
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
 
    @Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}
    
    // THIS IS VERY TEMPORARY, TESTING HOW TO DO THINGS
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // this replacement is because the Hand enum and the EquipmentSlot enum only differ in name with underscores
        var normalizedHand = hand.name().replace("_", "").toLowerCase();
        
        var stack = player.getEquippedStack(EquipmentSlot.byName(normalizedHand));
        var blockEntity = (CounterEntity) world.getBlockEntity(pos);
        
        if (
            stack != ItemStack.EMPTY &&
            blockEntity.getItem() == null &&
            (
                PlateupFoodItemGroup.contains(stack.getItem()) ||
                PlateupToolItemGroup.contains(stack.getItem())
            )
        ) {
            blockEntity.storeItem(stack.getItem());
            
            if (stack.getCount() == 1) {
                player.equipStack(EquipmentSlot.byName(normalizedHand), ItemStack.EMPTY);
            }
            else {
                stack.setCount(stack.getCount() - 1);
            }
            
            return ActionResult.SUCCESS;
        }
        else if (stack == ItemStack.EMPTY && blockEntity.getItem() != null) {
            player.equipStack(
                EquipmentSlot.byName(normalizedHand),
                new ItemStack(blockEntity.pickUpItem(), 1)
            );
            
            return ActionResult.SUCCESS;
        }
        else if (stack != ItemStack.EMPTY) {
            return ActionResult.PASS;
        }
        
        var currentFacing = world.getBlockState(pos).get(FACING);
        Direction targetDirection;
        
        if (currentFacing == Direction.EAST) {
            targetDirection = Direction.SOUTH;
        }
        else if (currentFacing == Direction.SOUTH) {
            targetDirection = Direction.WEST;
        }
        else if (currentFacing == Direction.WEST) {
            targetDirection = Direction.NORTH;
        }
        else {
            targetDirection = Direction.EAST;
        }
        
        world.setBlockState(pos, state.with(FACING, targetDirection));
        
        return ActionResult.SUCCESS;
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        var direction = state.get(FACING);
        var counterTop = VoxelShapes.cuboid(0f, 7/8f, 0f, 1f, 1f, 1f);
        
        if (direction == Direction.EAST) {
            return VoxelShapes.combine(
                counterTop,
                VoxelShapes.cuboid(0f, 0f, 0f, 15/16f, 7/8f, 1f),
                BooleanBiFunction.OR
            );
        }
        else if (direction == Direction.SOUTH) {
            return VoxelShapes.combine(
                counterTop,
                VoxelShapes.cuboid(0f, 0f, 0f, 1f, 7/8f, 15/16f),
                BooleanBiFunction.OR
            );
        }
        else if (direction == Direction.WEST) {
            return VoxelShapes.combine(
                counterTop,
                VoxelShapes.cuboid(1/16f, 0f, 0f, 1f, 7/8f, 1f),
                BooleanBiFunction.OR
            );
        }
        
        return VoxelShapes.combine(
            counterTop,
            VoxelShapes.cuboid(0f, 0f, 1/16f, 1f, 7/8f, 1f),
            BooleanBiFunction.OR
        );
    }
    
    @Override
    public BlockEntity createBlockEntity(BlockPos position, BlockState state) {
        return new CounterEntity(position, state);
    }
}
