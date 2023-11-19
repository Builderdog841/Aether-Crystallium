package net.builderdog.ancient_aether;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AncientAetherTags {

    public static class Blocks {
        public static final TagKey<Block> WYNDCAPS_ANIMAL_SPAWNABLE_ON = tag("wyndcaps_animals_spawnable_on");
        public static final TagKey<Block> DRIFTWEED_PLACEABLE_ON = tag("driftweed_placeable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(AncientAether.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> CRAFTS_HIGHSPROOT_PLANKS = tag("crafts_highsproot_planks");
        public static final TagKey<Item> CRAFTS_SAKURA_PLANKS = tag("crafts_sakura_planks");
        public static final TagKey<Item> HIGHLAND_BUFFALO_TEMPTATION_ITEMS = tag("highland_buffalo_temptation_items");
        public static final TagKey<Item> COMMON_LOOT = tag("common_loot");
        public static final TagKey<Item> RARE_LOOT = tag("rare_loot");
        public static final TagKey<Item> EPIC_LOOT = tag("epic_loot");
        public static final TagKey<Item> LEGENDARY_LOOT = tag("legendary_loot");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(AncientAether.MOD_ID, name));
        }
    }
}