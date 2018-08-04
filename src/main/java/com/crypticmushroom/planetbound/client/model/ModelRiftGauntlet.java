package com.crypticmushroom.planetbound.client.model;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class ModelRiftGauntlet implements IBakedModel {

    private final IBakedModel inventoryModel;
    private final IBakedModel heldModel;

    public ModelRiftGauntlet(IBakedModel inventoryModel, IBakedModel heldModel) {
        this.inventoryModel = inventoryModel;
        this.heldModel = heldModel;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return this.inventoryModel.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return this.inventoryModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.inventoryModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return this.inventoryModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return this.inventoryModel.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return this.inventoryModel.getOverrides();
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return ForgeHooksClient.handlePerspective(cameraTransformType == ItemCameraTransforms.TransformType.GUI ? this.inventoryModel : this.heldModel, cameraTransformType);
    }
}
