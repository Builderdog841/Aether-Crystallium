package net.builderdog.ancient_aether.world.trunkplacer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.builderdog.ancient_aether.AncientAetherTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class AncientAetherSkinnyHookedTrunkPlacer extends BaseAncientAetherHookedTrunkPlacer {
    public static final Codec<AncientAetherSkinnyHookedTrunkPlacer> CODEC = RecordCodecBuilder.create((codec) -> trunkPlacerParts(codec)
            .and(BlockStateProvider.CODEC.fieldOf("skinnyTrunkProvider").forGetter((placer) -> placer.skinnyTrunkProvider))
            .apply(codec, AncientAetherSkinnyHookedTrunkPlacer::new));
    private final BlockStateProvider skinnyTrunkProvider;

    public AncientAetherSkinnyHookedTrunkPlacer(int height, int heightRandA, int heightRandB, BlockStateProvider skinnyTrunkProvider) {
        super(height, heightRandA, heightRandB);
        this.skinnyTrunkProvider = skinnyTrunkProvider;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return AncientAetherTrunkPlacerTypes.ANCIENT_AETHER_SKINNY_HOOKED_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        list.addAll(this.placeVerticalTrunk(level, blockSetter, random, height, pos, this.skinnyTrunkProvider));
        list.addAll(this.placeBranches(level, blockSetter, random, height, pos, config));

        return list;
    }

    @Override
    public boolean isTrunk(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, (state) -> state.is(BlockTags.LOGS) || state.is(AncientAetherTags.Blocks.ANCIENT_AETHER_LOG_WALLS));
    }
}