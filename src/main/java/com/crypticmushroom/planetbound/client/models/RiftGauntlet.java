package com.crypticmushroom.planetbound.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * test - RetroGamerSP
 * Created using Tabula 7.0.0
 */
public class RiftGauntlet extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape4_1;
    public ModelRenderer shape4_2;
    public ModelRenderer shape4_3;
    public ModelRenderer shape4_4;
    public ModelRenderer shape4_5;
    public ModelRenderer shape4_6;
    public ModelRenderer shape3_1;
    public ModelRenderer shape3_2;

    public RiftGauntlet() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape4_5 = new ModelRenderer(this, 0, 0);
        this.shape4_5.setRotationPoint(13.1F, 2.15F, 3.1F);
        this.shape4_5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4_5, 0.0F, 0.0F, 0.3141592653589793F);
        this.shape3_1 = new ModelRenderer(this, 0, 0);
        this.shape3_1.setRotationPoint(7.1F, 0.6F, 3.0F);
        this.shape3_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(shape3_1, 0.0F, 0.0F, 0.06981317007977318F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(10.5F, 2.0F, 3.1F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4, 0.0F, 0.0F, 0.08726646259971647F);
        this.shape4_2 = new ModelRenderer(this, 0, 0);
        this.shape4_2.setRotationPoint(13.1F, 2.15F, 1.3F);
        this.shape4_2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4_2, 0.0F, 0.0F, 0.3141592653589793F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(6.0F, 1.0F, 1.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 5, 3, 5, 0.0F);
        this.setRotateAngle(shape2, 0.0F, 0.0F, 0.08726646259971647F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(1.1F, 1.1F, 1.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 6, 3, 5, 0.0F);
        this.shape4_3 = new ModelRenderer(this, 0, 0);
        this.shape4_3.setRotationPoint(10.5F, 2.0F, 1.3F);
        this.shape4_3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4_3, 0.0F, 0.0F, 0.08726646259971647F);
        this.shape3_2 = new ModelRenderer(this, 0, 0);
        this.shape3_2.setRotationPoint(5.7F, 0.8F, 2.0F);
        this.shape3_2.addBox(0.0F, 0.0F, 0.0F, 5, 1, 3, 0.0F);
        this.setRotateAngle(shape3_2, 0.0F, 0.0F, 0.06981317007977318F);
        this.shape4_4 = new ModelRenderer(this, 0, 0);
        this.shape4_4.setRotationPoint(10.0F, 4.3F, 5.4F);
        this.shape4_4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4_4, 1.5707963267948966F, -0.23387411976724018F, 0.12217304763960307F);
        this.shape4_1 = new ModelRenderer(this, 0, 0);
        this.shape4_1.setRotationPoint(13.1F, 2.15F, 4.6F);
        this.shape4_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4_1, 0.0F, 0.0F, 0.3141592653589793F);
        this.shape4_6 = new ModelRenderer(this, 0, 0);
        this.shape4_6.setRotationPoint(10.5F, 2.0F, 4.6F);
        this.shape4_6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(shape4_6, 0.0F, 0.0F, 0.08726646259971647F);
        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(1.1F, 0.8F, 2.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape4_5.render(f5);
        this.shape3_1.render(f5);
        this.shape4.render(f5);
        this.shape4_2.render(f5);
        this.shape2.render(f5);
        this.shape1.render(f5);
        this.shape4_3.render(f5);
        this.shape3_2.render(f5);
        this.shape4_4.render(f5);
        this.shape4_1.render(f5);
        this.shape4_6.render(f5);
        this.shape3.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}