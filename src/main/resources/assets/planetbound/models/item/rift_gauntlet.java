//Made with Blockbench, a free, modern block model editor by JannisX11

package net.minecraft.src;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockbench extends ModelBase {

    //fields
    ModelRenderer e0;
    ModelRenderer e1;
    ModelRenderer e2;
    ModelRenderer e3;
    ModelRenderer e4;
    ModelRenderer e5;
    ModelRenderer e6;
    ModelRenderer e7;
    ModelRenderer e8;
    ModelRenderer e9;
    ModelRenderer e10;
    ModelRenderer e11;
    ModelRenderer e12;
    ModelRenderer e13;
    ModelRenderer e14;
    ModelRenderer e15;
    ModelRenderer e16;
    ModelRenderer e17;
    ModelRenderer e18;
    ModelRenderer e19;
    ModelRenderer e20;
    ModelRenderer e21;

    public ModelBlockbench() {
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.e0 = new ModelRenderer(this, 0, 0);
        this.e0.addBox(4.5F, 4.625F, 4.59722F, 7, 4, 5, 1.0F);
        this.e1 = new ModelRenderer(this, 0, 0);
        this.e1.addBox(4.5F, 7.625F, 2.09722F, 1, 1, 3, 1.0F);
        this.e2 = new ModelRenderer(this, 0, 0);
        this.e2.addBox(4.5F, 7F, -0.90278F, 1, 1, 3, 1.0F);
        this.2.setRotationPoint(5F, 7.5F, 0.59722F);
        this.setRotateAngle(e2, -22.5F, 0.0F, 0.0F);
        this.e3 = new ModelRenderer(this, 0, 0);
        this.e3.addBox(4.5F, 7.875F, 1.59722F, 1, 1, 1, 1.0F);
        this.e4 = new ModelRenderer(this, 0, 0);
        this.e4.addBox(6.5F, 7.625F, 2.09722F, 1, 1, 3, 1.0F);
        this.e5 = new ModelRenderer(this, 0, 0);
        this.e5.addBox(6.5F, 7F, -0.90278F, 1, 1, 3, 1.0F);
        this.5.setRotationPoint(7F, 7.5F, 0.59722F);
        this.setRotateAngle(e5, -22.5F, 0.0F, 0.0F);
        this.e6 = new ModelRenderer(this, 0, 0);
        this.e6.addBox(6.5F, 7.875F, 1.59722F, 1, 1, 1, 1.0F);
        this.e7 = new ModelRenderer(this, 0, 0);
        this.e7.addBox(4.5F, 6.625F, 3.59722F, 7, 2, 3, 1.0F);
        this.e8 = new ModelRenderer(this, 0, 0);
        this.e8.addBox(5F, 4.625F, 9.09722F, 6, 4, 6, 1.0F);
        this.e9 = new ModelRenderer(this, 0, 0);
        this.e9.addBox(8.5F, 7.625F, 2.09722F, 1, 1, 3, 1.0F);
        this.e10 = new ModelRenderer(this, 0, 0);
        this.e10.addBox(8.5F, 7F, -0.90278F, 1, 1, 3, 1.0F);
        this.10.setRotationPoint(9F, 7.5F, 0.59722F);
        this.setRotateAngle(e10, -22.5F, 0.0F, 0.0F);
        this.e11 = new ModelRenderer(this, 0, 0);
        this.e11.addBox(8.5F, 7.875F, 1.59722F, 1, 1, 1, 1.0F);
        this.e12 = new ModelRenderer(this, 0, 0);
        this.e12.addBox(10.5F, 7.625F, 2.09722F, 1, 1, 3, 1.0F);
        this.e13 = new ModelRenderer(this, 0, 0);
        this.e13.addBox(10.5F, 7F, -0.90278F, 1, 1, 3, 1.0F);
        this.13.setRotationPoint(11F, 7.5F, 0.59722F);
        this.setRotateAngle(e13, -22.5F, 0.0F, 0.0F);
        this.e14 = new ModelRenderer(this, 0, 0);
        this.e14.addBox(10.5F, 7.875F, 1.59722F, 1, 1, 1, 1.0F);
        this.e15 = new ModelRenderer(this, 0, 0);
        this.e15.addBox(5F, 6.125F, 10.09722F, 6, 3, 1, 1.0F);
        this.e16 = new ModelRenderer(this, 0, 0);
        this.e16.addBox(5F, 7.125F, 11.09722F, 6, 2, 1, 1.0F);
        this.e17 = new ModelRenderer(this, 0, 0);
        this.e17.addBox(7F, 8.125F, 7.59722F, 2, 1, 2, 1.0F);
        this.e18 = new ModelRenderer(this, 0, 0);
        this.e18.addBox(3.25F, 4.25F, 5F, 1, 1, 3, 1.0F);
        this.18.setRotationPoint(4F, 5F, 6.75F);
        this.setRotateAngle(e18, -22.5F, 0.0F, 0.0F);
        this.e19 = new ModelRenderer(this, 0, 0);
        this.e19.addBox(3.25F, 3.25F, 1F, 1, 1, 3, 1.0F);
        this.e20 = new ModelRenderer(this, 0, 0);
        this.e20.addBox(3.25F, 3F, 4F, 2, 2, 2, 1.0F);
        this.e21 = new ModelRenderer(this, 0, 0);
        this.e21.addBox(3.5F, 5F, 7F, 1, 2, 2, 1.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.e0.render(f5);
        this.e1.render(f5);
        this.e2.render(f5);
        this.e3.render(f5);
        this.e4.render(f5);
        this.e5.render(f5);
        this.e6.render(f5);
        this.e7.render(f5);
        this.e8.render(f5);
        this.e9.render(f5);
        this.e10.render(f5);
        this.e11.render(f5);
        this.e12.render(f5);
        this.e13.render(f5);
        this.e14.render(f5);
        this.e15.render(f5);
        this.e16.render(f5);
        this.e17.render(f5);
        this.e18.render(f5);
        this.e19.render(f5);
        this.e20.render(f5);
        this.e21.render(f5);

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}