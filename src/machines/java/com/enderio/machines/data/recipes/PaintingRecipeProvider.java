package com.enderio.machines.data.recipes;

import com.enderio.EnderIO;
import com.enderio.base.common.init.EIOBlocks;
import com.enderio.core.data.recipes.EnderRecipeProvider;
import com.enderio.machines.common.init.MachineRecipes;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.function.Consumer;

public class PaintingRecipeProvider extends EnderRecipeProvider {

    public PaintingRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        build(EIOBlocks.PAINTED_FENCE, Ingredient.of(ItemTags.WOODEN_FENCES), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_FENCE_GATE, Ingredient.of(ItemTags.FENCE_GATES), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_SAND, Ingredient.of(ItemTags.SAND), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_STAIRS, Ingredient.of(ItemTags.WOODEN_STAIRS), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_CRAFTING_TABLE, Ingredient.of(Items.CRAFTING_TABLE), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_REDSTONE_BLOCK, Ingredient.of(Items.REDSTONE_BLOCK), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_TRAPDOOR, Ingredient.of(ItemTags.WOODEN_TRAPDOORS), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_WOODEN_PRESSURE_PLATE, Ingredient.of(ItemTags.WOODEN_PRESSURE_PLATES), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_SLAB, Ingredient.of(ItemTags.WOODEN_SLABS), pFinishedRecipeConsumer);
        build(EIOBlocks.PAINTED_GLOWSTONE, Ingredient.of(Items.GLOWSTONE), pFinishedRecipeConsumer);
    }


    protected void build(ItemLike output, Ingredient input, Consumer<FinishedRecipe> recipeConsumer) {
        recipeConsumer.accept(new FinishedPaintingRecipe(EnderIO.loc("painting/" + ForgeRegistries.ITEMS.getKey(output.asItem()).getPath()), input, output.asItem()));
    }

    protected static class FinishedPaintingRecipe extends EnderFinishedRecipe {
        private final Ingredient input;
        private final Item output;

        public FinishedPaintingRecipe(ResourceLocation id, Ingredient input, Item output) {
            super(id);
            this.input = input;
            this.output = output;
        }

        @Override
        protected Set<String> getModDependencies() {
            return Set.of(ForgeRegistries.ITEMS.getKey(output).getNamespace());
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("input", input.toJson());
            json.addProperty("output", ForgeRegistries.ITEMS.getKey(output).toString());

            super.serializeRecipeData(json);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return MachineRecipes.PAINTING.serializer().get();
        }
    }
}