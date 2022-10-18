package com.StarFoxMods.strangemod.screen;

import com.StarFoxMods.strangemod.StrangeMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class StrangeChestScreen extends AbstractContainerScreen<StrangeChestMenu> {
    private static final ResourceLocation TEXTURE =
                new ResourceLocation(StrangeMod.MOD_ID,"textures/gui/strange_chest_gui.png");

    public StrangeChestScreen(StrangeChestMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int xOffset = (width - imageWidth) / 2;
        int yOffset = (height - imageHeight) / 2;

        this.blit(pPoseStack, xOffset, yOffset, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pPoseStack, (xOffset), (yOffset));
        renderProgressFire(pPoseStack, (xOffset), (yOffset));
    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 29, y + 35, 178, 0, menu.getScaledProgressArrow(),16);
        }
    }

    private void renderProgressFire(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 9, y + 36, 203, 0, 14,  menu.getScaledProgressFire());
        }   else {
            blit(pPoseStack, x + 9, y + 36, 203, 0, 14, 14);
            }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
