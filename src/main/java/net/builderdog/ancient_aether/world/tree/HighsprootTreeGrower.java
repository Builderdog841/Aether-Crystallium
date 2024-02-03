package net.builderdog.ancient_aether.world.tree;

import net.builderdog.ancient_aether.data.resources.registries.features.AncientAetherTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class HighsprootTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        if (random.nextInt(10) == 0) {
            return AncientAetherTreeFeatures.TALL_HIGHSPROOT_TREE;
        } else {
            return AncientAetherTreeFeatures.HIGHSPROOT_TREE;
        }
    }
}