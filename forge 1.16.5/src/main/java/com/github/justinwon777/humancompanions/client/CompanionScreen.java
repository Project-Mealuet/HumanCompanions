package com.github.justinwon777.humancompanions.client;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.container.CompanionContainer;
import com.github.justinwon777.humancompanions.core.PacketHandler;
import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import com.github.justinwon777.humancompanions.entity.Arbalist;
import com.github.justinwon777.humancompanions.entity.Archer;
import com.github.justinwon777.humancompanions.entity.Knight;
import com.github.justinwon777.humancompanions.networking.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@OnlyIn(Dist.CLIENT)
public class CompanionScreen extends ContainerScreen<CompanionContainer> implements IHasContainer<CompanionContainer> {
    private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation(HumanCompanions.MOD_ID,
            "textures/inventory.png");
    private static final ResourceLocation ALERT_BUTTON = new ResourceLocation(HumanCompanions.MOD_ID, "textures/alertbutton.png");
    private static final ResourceLocation HUNTING_BUTTON = new ResourceLocation(HumanCompanions.MOD_ID, "textures" +
            "/huntingbutton.png");
    private static final ResourceLocation PATROL_BUTTON = new ResourceLocation(HumanCompanions.MOD_ID, "textures" +
            "/patrolbutton.png");
    private static final ResourceLocation CLEAR_BUTTON = new ResourceLocation(HumanCompanions.MOD_ID, "textures" +
            "/clearbutton.png");
    private static final ResourceLocation STATIONERY_BUTTON = new ResourceLocation(HumanCompanions.MOD_ID, "textures" +
            "/stationerybutton.png");
    private static final ResourceLocation RELEASE_BUTTON = new ResourceLocation(HumanCompanions.MOD_ID, "textures" +
            "/releasebutton.png");
    private final int containerRows;
    private final AbstractHumanCompanionEntity companion;
    private CompanionButton alertButton;
    private CompanionButton huntingButton;
    private CompanionButton patrolButton;
    private CompanionButton clearButton;
    private CompanionButton stationeryButton;
    private CompanionButton releaseButton;
    DecimalFormat df = new DecimalFormat("#.#");
    int sidebarx;
    int rowHeight;
    int colwidth;
    int row1;
    int row2;
    int row3;
    int col1;
    int col2;

    public CompanionScreen(CompanionContainer p_98409_, PlayerInventory p_98410_,
                           AbstractHumanCompanionEntity companion) {
        super(p_98409_, p_98410_, companion.getName());
        this.companion = companion;
        this.passEvents = false;
        this.containerRows = p_98409_.getRowCount();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        this.imageWidth = 226;
        df.setRoundingMode(RoundingMode.CEILING);
        sidebarx = 174;
        rowHeight = 15;
        colwidth = 19;
    }

    @Override
    public void render(MatrixStack p_98418_, int p_98419_, int p_98420_, float p_98421_) {
        this.renderBackground(p_98418_);
        super.render(p_98418_, p_98419_, p_98420_, p_98421_);
        this.renderTooltip(p_98418_, p_98419_, p_98420_);
    }

    @Override
    protected void renderBg(MatrixStack p_98413_, float p_98414_, int p_98415_, int p_98416_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(CONTAINER_BACKGROUND);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_98413_, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        this.blit(p_98413_, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }

    @Override
    protected void init() {
        super.init();
        row1 = topPos + 66;
        row2 = row1 + rowHeight;
        row3 = row2 + rowHeight;
        col1 = leftPos + sidebarx + 3;
        col2 = col1 + colwidth;
        this.alertButton = addButton(new CompanionButton("alert", col1, row1, 16, 12, 0, 0, 13,
                ALERT_BUTTON,
                btn -> {
                    PacketHandler.INSTANCE.sendToServer(new SetAlertPacket(companion.getId()));
                }));
        this.huntingButton = addButton(new CompanionButton("hunting", col2, row1, 16, 12, 0, 0
                ,13,
                HUNTING_BUTTON,
                btn -> {
                    PacketHandler.INSTANCE.sendToServer(new SetHuntingPacket(companion.getId()));
                }));
        this.patrolButton = addButton(new CompanionButton("patrolling", col1, row2, 16, 12,
                0, 0
                ,13,
                PATROL_BUTTON,
                btn -> {
                    PacketHandler.INSTANCE.sendToServer(new SetPatrollingPacket(companion.getId()));
                }));
        if (companion instanceof Archer || companion instanceof Arbalist) {
            this.stationeryButton = addButton(new CompanionButton("stationery", col2, row2,
                    16, 12, 0, 0, 13,
                    STATIONERY_BUTTON,
                    btn -> {
                        PacketHandler.INSTANCE.sendToServer(new SetStationeryPacket(companion.getId()));
                    }));
        }
        this.clearButton = addButton(new CompanionButton("clear", leftPos + sidebarx + 5, row3, 31,
                12, 0, 0
                ,13,
                CLEAR_BUTTON,
                btn -> {
                    PacketHandler.INSTANCE.sendToServer(new ClearTargetPacket(companion.getId()));
                }));
        this.releaseButton = addButton(new CompanionButton("release", leftPos + sidebarx + 3, topPos + 148,
                34,
                12, 0, 0
                ,13,
                RELEASE_BUTTON,
                btn -> {
                    PacketHandler.INSTANCE.sendToServer(new ReleasePacket(companion.getId()));
                    this.onClose();
                }));
    }

    @Override
    protected void renderLabels(MatrixStack pPoseStack, int pMouseX, int pMouseY) {
        super.renderLabels(pPoseStack, pMouseX, pMouseY);
        int classHeight = this.titleLabelY + 14;
        int classLeft = sidebarx + 4;
        StringTextComponent classTitle = new StringTextComponent("Class");
        StringTextComponent healthTitle = new StringTextComponent("Health");
        StringTextComponent health =
                new StringTextComponent(df.format(companion.getHealth()) + "/" + (int) companion.getMaxHealth());

        this.font.draw(pPoseStack, classTitle.withStyle(TextFormatting.UNDERLINE), sidebarx + 4, this.titleLabelY + 3,
                4210752);
        if (companion instanceof Arbalist) {
            this.font.draw(pPoseStack, "Arbalist", classLeft, classHeight, 4210752);
        } else if (companion instanceof Archer) {
            this.font.draw(pPoseStack, "Archer", classLeft, classHeight, 4210752);
        } else if (companion instanceof Knight) {
            this.font.draw(pPoseStack, "Knight", classLeft, classHeight, 4210752);
        } else {
            this.font.draw(pPoseStack, "Axe", classLeft, classHeight, 4210752);
        }

        this.font.draw(pPoseStack, healthTitle.withStyle(TextFormatting.UNDERLINE), sidebarx + 4, this.titleLabelY + 26,
                4210752);
        this.font.draw(pPoseStack, health, sidebarx + 4, this.titleLabelY + 37, 4210752);

        this.font.draw(pPoseStack, "Level " + companion.getExpLvl(), sidebarx, this.titleLabelY + 49,
                4210752);
    }

    @Override
    protected void renderTooltip(MatrixStack stack, int x, int y) {
        super.renderTooltip(stack, x, y);
        if (this.alertButton.isHovered()) {
            List<ITextComponent> tooltips = new ArrayList<>();
            if (this.companion.isAlert()) {
                tooltips.add(new StringTextComponent("Alert mode: On"));
            } else {
                tooltips.add(new StringTextComponent("Alert mode: Off"));
            }
            tooltips.add(new StringTextComponent("Attacks nearby hostile mobs").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));

            this.renderComponentTooltip(stack, tooltips, x, y);
        }
        if (this.huntingButton.isHovered()) {
            List<ITextComponent> tooltips = new ArrayList<>();
            if (this.companion.isHunting()) {
                tooltips.add(new StringTextComponent("Hunting mode: On"));
            } else {
                tooltips.add(new StringTextComponent("Hunting mode: Off"));
            }
            tooltips.add(new StringTextComponent("Attacks nearby mobs for food").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));

            this.renderComponentTooltip(stack, tooltips, x, y);
        }
        if (this.patrolButton.isHovered()) {
            List<ITextComponent> tooltips = new ArrayList<>();
            if (this.companion.isFollowing()) {
                tooltips.add(new StringTextComponent("Follow"));
                tooltips.add(new StringTextComponent("Follows you").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
            } else if (this.companion.isPatrolling()){
                tooltips.add(new StringTextComponent("Patrol"));
                tooltips.add(new StringTextComponent("Patrols a 4 block radius").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
            } else {
                tooltips.add(new StringTextComponent("Guard"));
                tooltips.add(new StringTextComponent("Stands at its position ready for action").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
            }

            this.renderComponentTooltip(stack, tooltips, x, y);
        }
        if (this.clearButton.isHovered()) {
            List<ITextComponent> tooltips = new ArrayList<>();
            tooltips.add(new StringTextComponent("Clear target"));
            tooltips.add(new StringTextComponent("Useful if it gets stuck attacking").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));

            this.renderComponentTooltip(stack, tooltips, x, y);
        }

        if (this.releaseButton.isHovered()) {
            List<ITextComponent> tooltips = new ArrayList<>();
            tooltips.add(new StringTextComponent("Release Companion"));
            tooltips.add(new StringTextComponent("Releases companion from your command. It can be tamed again.").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));

            this.renderComponentTooltip(stack, tooltips, x, y);
        }

        if (companion instanceof Archer || companion instanceof Arbalist) {
            if (this.stationeryButton.isHovered()) {
                List<ITextComponent> tooltips = new ArrayList<>();
                if (this.companion.isStationery()) {
                    tooltips.add(new StringTextComponent("Stationery: On"));
                } else {
                    tooltips.add(new StringTextComponent("Stationery: Off"));
                }
                tooltips.add(new StringTextComponent("Companion will not move while attacking in guard mode").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));

                this.renderComponentTooltip(stack, tooltips, x, y);
            }
        }
    }

    class CompanionButton extends ImageButton {

        private String name;

        public CompanionButton(String name, int p_94269_, int p_94270_, int p_94271_, int p_94272_, int p_94273_,
                               int p_94274_,
                               int p_94275_, ResourceLocation p_94276_,
                               Button.IPressable p_94277_) {
            super(p_94269_, p_94270_, p_94271_, p_94272_, p_94273_, p_94274_, p_94275_, p_94276_, p_94277_);
            this.name = name;
        }

        @Override
        public void renderButton(MatrixStack p_94282_, int p_94283_, int p_94284_, float p_94285_) {
            if (this.name.equals("alert")) {
                if (CompanionScreen.this.companion.isAlert()) {
                    this.xTexStart = 0;
                } else {
                    this.xTexStart = 17;
                }
            } else if (this.name.equals("hunting")) {
                if (CompanionScreen.this.companion.isHunting()) {
                    this.xTexStart = 0;
                } else {
                    this.xTexStart = 17;
                }
            } else if (this.name.equals("patrolling")) {
                if (CompanionScreen.this.companion.isFollowing()) {
                    this.xTexStart = 0;
                } else if (CompanionScreen.this.companion.isPatrolling()){
                    this.xTexStart = 17;
                } else {
                    this.xTexStart = 34;
                }
            } else if (this.name.equals("stationery")) {
                if (CompanionScreen.this.companion.isStationery()) {
                    this.xTexStart = 0;
                } else {
                    this.xTexStart = 17;
                }
            }
            RenderSystem.enableBlend();
            super.renderButton(p_94282_, p_94283_, p_94284_, p_94285_);
            RenderSystem.disableBlend();
        }
    }
}
