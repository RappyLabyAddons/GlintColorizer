package com.rappytv.glintcolorizer.v1_20_5;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;

public class CustomRenderTypes extends RenderType {

  private static final RenderType GLOW_GLINT = create(
      "glint_glow",
      DefaultVertexFormat.POSITION_TEX,
      Mode.QUADS,
      256,
      CompositeState.builder()
          .setTextureState(new TextureStateShard(
              ItemRenderer.ENCHANTED_GLINT_ITEM,
              true,
              false
          ))
          .setWriteMaskState(COLOR_WRITE)
          .setCullState(NO_CULL)
          .setDepthTestState(NO_DEPTH_TEST)
          .setTransparencyState(GLINT_TRANSPARENCY)
          .setTexturingState(GLINT_TEXTURING)
          .setShaderState(ShaderStateShard.RENDERTYPE_GLINT_SHADER)
          .createCompositeState(false)
  );

  private static final RenderType GLOW_ENTITY_GLINT = create(
      "entity_glint_glow",
      DefaultVertexFormat.POSITION_TEX,
      Mode.QUADS,
      256,
      CompositeState.builder()
          .setTextureState(new TextureStateShard(
              ItemRenderer.ENCHANTED_GLINT_ENTITY,
              true,
              false
          ))
          .setWriteMaskState(COLOR_WRITE)
          .setCullState(NO_CULL)
          .setDepthTestState(NO_DEPTH_TEST)
          .setTransparencyState(GLINT_TRANSPARENCY)
          .setTexturingState(ENTITY_GLINT_TEXTURING)
          .setShaderState(ShaderStateShard.RENDERTYPE_ENTITY_GLINT_SHADER)
          .createCompositeState(false)
  );

  private static final RenderType GLOW_GLINT_DIRECT = create(
      "glint_direct_glow",
      DefaultVertexFormat.POSITION_TEX,
      Mode.QUADS,
      256,
      CompositeState.builder()
          .setTextureState(new TextureStateShard(
              ItemRenderer.ENCHANTED_GLINT_ITEM,
              true,
              false
          ))
          .setWriteMaskState(COLOR_WRITE)
          .setCullState(NO_CULL)
          .setDepthTestState(NO_DEPTH_TEST)
          .setTransparencyState(GLINT_TRANSPARENCY)
          .setTexturingState(GLINT_TEXTURING)
          .setShaderState(ShaderStateShard.RENDERTYPE_GLINT_SHADER)
          .createCompositeState(false)
  );

  private static final RenderType GLOW_ENTITY_GLINT_DIRECT = create(
      "entity_glint_direct_glow",
      DefaultVertexFormat.POSITION_TEX,
      Mode.QUADS,
      256,
      CompositeState.builder()
          .setTextureState(new TextureStateShard(
              ItemRenderer.ENCHANTED_GLINT_ENTITY,
              true,
              false
          ))
          .setWriteMaskState(COLOR_WRITE)
          .setCullState(NO_CULL)
          .setDepthTestState(NO_DEPTH_TEST)
          .setTransparencyState(GLINT_TRANSPARENCY)
          .setTexturingState(ENTITY_GLINT_TEXTURING)
          .setShaderState(ShaderStateShard.RENDERTYPE_ENTITY_GLINT_SHADER)
          .createCompositeState(false)
  );

  private CustomRenderTypes(
      String name,
      VertexFormat format,
      Mode mode,
      int bufferSize,
      boolean affectsCrumbling,
      boolean sortOnUpload,
      Runnable setupState,
      Runnable clearState
  ) {
    super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
  }

  public static RenderType getGlowGlint() {
    return GLOW_GLINT;
  }

  public static RenderType getGlowEntityGlint() {
    return GLOW_ENTITY_GLINT;
  }

  public static RenderType getGlowGlintDirect() {
    return GLOW_GLINT_DIRECT;
  }

  public static RenderType getGlowEntityGlintDirect() {
    return GLOW_ENTITY_GLINT_DIRECT;
  }
}
