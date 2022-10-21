package com.StarFoxMods.strangemod.client;

import com.StarFoxMods.strangemod.StrangeMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ThirstHudOverlay {

    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(StrangeMod.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation HALF_FILLED_THIRST = new ResourceLocation(StrangeMod.MOD_ID,
            "textures/thirst/half_filled_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(StrangeMod.MOD_ID,
            "textures/thirst/empty_thirst.png");

    public static final IGuiOverlay HUD_THIRST = ((gui, poseStack, partialTick, width, height) -> {
        int x = width/2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0,EMPTY_THIRST);

        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,x + 95 + (i * 6), y - 39, 0, 0, 9, 9,
                    9, 9);
        }

        RenderSystem.setShaderTexture(0,HALF_FILLED_THIRST);

        for(int i = 0; i < 10; i++) {
            if(ClientThirstData.getPlayerThirst_half_filled() > i) {
                GuiComponent.blit(poseStack,x + 95 + (i * 6), y - 39, 0, 0, 9, 9,
                        9, 9);
            } else {
                break;
            }
        }

        RenderSystem.setShaderTexture(0,FILLED_THIRST);

        for(int i = 0; i < 10; i++) {
            if(ClientThirstData.getPlayerThirst_filled() > i) {
                GuiComponent.blit(poseStack,x + 95 + (i * 7), y - 39, 0, 0, 9, 9,
                        9, 9);
            } else {
                break;
            }
        }
    });
}
