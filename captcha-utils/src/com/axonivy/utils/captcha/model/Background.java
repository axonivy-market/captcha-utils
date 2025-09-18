package com.axonivy.utils.captcha.model;

import java.awt.Color;

import com.axonivy.utils.captcha.enums.BackgroundType;

import net.logicsquad.nanocaptcha.image.backgrounds.BackgroundProducer;
import net.logicsquad.nanocaptcha.image.backgrounds.FlatColorBackgroundProducer;
import net.logicsquad.nanocaptcha.image.backgrounds.GradiatedBackgroundProducer;
import net.logicsquad.nanocaptcha.image.backgrounds.SquigglesBackgroundProducer;
import net.logicsquad.nanocaptcha.image.backgrounds.TransparentBackgroundProducer;

import static com.axonivy.utils.captcha.helper.ColorHelper.hexToColor;

public class Background {

  private BackgroundType type;
  private String color;
  private String colorNext;

  public Background() {
    this.type = BackgroundType.GRADIATED;
  }

  public BackgroundType getType() {
    return type;
  }

  public void setType(BackgroundType type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColorNext() {
    return colorNext;
  }

  public void setColorNext(String colorNext) {
    this.colorNext = colorNext;
  }

  public BackgroundProducer getBackgroundProducer() {
    return switch (type) {
      case FLATCOLOR -> new FlatColorBackgroundProducer(hexToColor(color).orElse(Color.GRAY));
      case GRADIATED -> new GradiatedBackgroundProducer(hexToColor(color).orElse(Color.DARK_GRAY),
          hexToColor(colorNext).orElse(Color.WHITE));
      case SQUIGGLES -> new SquigglesBackgroundProducer();
      case TRANSPARENT -> new TransparentBackgroundProducer();
      default -> new FlatColorBackgroundProducer();
    };
  }
}
