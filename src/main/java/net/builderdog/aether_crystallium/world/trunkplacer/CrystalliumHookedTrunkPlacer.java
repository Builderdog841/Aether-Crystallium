package net.builderdog.aether_crystallium.world.trunkplacer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class CrystalliumHookedTrunkPlacer extends BaseCrystalliumHookedTrunkPlacer {

    //This code was written by the Aether Team and not by me
    public static final Codec<CrystalliumHookedTrunkPlacer> CODEC = RecordCodecBuilder.create((codec) -> trunkPlacerParts(codec).apply(codec, CrystalliumHookedTrunkPlacer::new));

    public CrystalliumHookedTrunkPlacer(int height, int heightRandA, int heightRandB) {
        super(height, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.CRYSTALLIUM_HOOKED_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        setDirtAt(level, blockSetter, random, pos.below(), config);

        list.addAll(this.placeVerticalTrunk(level, blockSetter, random, height, pos, config.trunkProvider));
        list.addAll(this.placeBranches(level, blockSetter, random, height, pos, config));

        return list;
    }

    @Override
    public boolean isTrunk(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, (state) -> state.is(BlockTags.LOGS));
    }
}