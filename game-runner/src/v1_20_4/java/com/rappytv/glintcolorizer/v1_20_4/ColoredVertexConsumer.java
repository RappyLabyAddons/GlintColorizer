package com.rappytv.glintcolorizer.v1_20_4;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.labymod.api.util.Color;

public class ColoredVertexConsumer implements VertexConsumer {

  private final VertexConsumer delegate;
  private final float red;
  private final float green;
  private final float blue;

  public ColoredVertexConsumer(VertexConsumer delegate, Color color) {
    this.delegate = delegate;
    this.red = color.getRed() / 255f;
    this.green = color.getGreen() / 255f;
    this.blue = color.getBlue() / 255f;
  }

  @Override
  public VertexConsumer vertex(double x, double y, double z) {
    this.delegate.vertex(x, y, z);
    return this;
  }

  @Override
  public VertexConsumer color(int red, int green, int blue, int alpha) {
    this.delegate.color(
        (int) (red * this.red),
        (int) (green * this.green),
        (int) (blue * this.blue),
        alpha
    );
    return this;
  }

  @Override
  public VertexConsumer uv(float u, float v) {
    this.delegate.uv(u, v);
    return this;
  }

  @Override
  public VertexConsumer overlayCoords(int u, int v) {
    this.delegate.overlayCoords(u, v);
    return this;
  }

  @Override
  public VertexConsumer uv2(int u, int v) {
    this.delegate.uv2(u, v);
    return this;
  }

  @Override
  public VertexConsumer normal(float x, float y, float z) {
    this.delegate.normal(x, y, z);
    return this;
  }

  @Override
  public void endVertex() {
    this.delegate.endVertex();
  }

  @Override
  public void defaultColor(int red, int green, int blue, int alpha) {
    this.delegate.defaultColor(red, green, blue, alpha);
  }

  @Override
  public void unsetDefaultColor() {
    this.delegate.unsetDefaultColor();
  }
}