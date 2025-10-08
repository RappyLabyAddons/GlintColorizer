package com.rappytv.glintcolorizer.v1_21;

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
  public VertexConsumer addVertex(float x, float y, float z) {
    return this.delegate.addVertex(x, y, z);
  }

  @Override
  public VertexConsumer setColor(int red, int green, int blue, int alpha) {
    return this.delegate.setColor(
        (int) (red * this.red),
        (int) (green * this.green),
        (int) (blue * this.blue),
        alpha
    );
  }

  @Override
  public VertexConsumer setUv(float v, float v1) {
    return this.delegate.setUv(v, v1);
  }

  @Override
  public VertexConsumer setUv1(int i, int i1) {
    return this.delegate.setUv1(i, i1);
  }

  @Override
  public VertexConsumer setUv2(int i, int i1) {
    return this.delegate.setUv2(i, i1);
  }

  @Override
  public VertexConsumer setNormal(float v, float v1, float v2) {
    return this.delegate.setNormal(v, v1, v2);
  }
}