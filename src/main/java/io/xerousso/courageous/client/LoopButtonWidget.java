package io.xerousso.courageous.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.xerousso.courageous.Courageous;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class LoopButtonWidget extends ButtonWidget {

    private static final Identifier TEXTURE = new Identifier(Courageous.MOD_ID, "textures/gui/pottery_wheel.png");

    private boolean selected;

    private final BiConsumer<LoopButtonWidget, Boolean> onToggle;

    public LoopButtonWidget(int x, int y, BiConsumer<LoopButtonWidget, Boolean> onToggle) {
        super(x, y, 20, 20, new TranslatableText("gui.courageous.pottery.loop"), button -> {});
        this.onToggle = onToggle;
    }

    public LoopButtonWidget(int x, int y, BiConsumer<LoopButtonWidget, Boolean> onToggle, TooltipSupplier tooltipSupplier) {
        super(x, y, 20, 20, new TranslatableText("gui.courageous.pottery.loop"), button -> {}, tooltipSupplier);
        this.onToggle = onToggle;
    }

    @Override
    public void onPress() {
        setSelected(!selected);
        onToggle.accept(this, selected);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        MinecraftClient.getInstance().getTextureManager().bindTexture(TEXTURE);

        IconLocation location;
        if (selected) {
            if (isHovered()) location = IconLocation.SELECTED_HOVER;
            else location = IconLocation.SELECTED;
        } else {
            if (isHovered()) location = IconLocation.UNSELECTED_HOVER;
            else location = IconLocation.UNSELECTED;
        }

        drawTexture(matrices, x, y, getZOffset(), location.getU(), location.getV(), width, height, 256, 512);
    }

    @Environment(EnvType.CLIENT)
    enum IconLocation {
        UNSELECTED(44, 166),
        UNSELECTED_HOVER(44, 186),
        SELECTED(64, 166),
        SELECTED_HOVER(64, 186);

        private final int u;
        private final int v;

        IconLocation(int j, int k) {
            this.u = j;
            this.v = k;
        }

        public int getU() {
            return this.u;
        }

        public int getV() {
            return this.v;
        }
    }

}
