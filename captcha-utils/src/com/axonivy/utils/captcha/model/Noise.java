package com.axonivy.utils.captcha.model;

import static java.util.Optional.ofNullable;

import java.awt.Color;

import com.axonivy.utils.captcha.enums.NoiseType;

import net.logicsquad.nanocaptcha.image.noise.CurvedLineNoiseProducer;
import net.logicsquad.nanocaptcha.image.noise.GaussianNoiseProducer;
import net.logicsquad.nanocaptcha.image.noise.NoiseProducer;
import net.logicsquad.nanocaptcha.image.noise.SaltAndPepperNoiseProducer;
import net.logicsquad.nanocaptcha.image.noise.StraightLineNoiseProducer;

import static com.axonivy.utils.captcha.helper.ColorHelper.hexToColor;

public class Noise {
  private static final int DEFAULT_STRAIGHTLINE_WIDTH = 4;
  private static final float DEFAULT_CURVEDLINE_WIDTH = 3f;
  private static final int DEFAULT_STANDARD_DEVIATION = 20;
  private static final int DEFAULT_MEAN = 0;
  private static final double DEFAULT_NOISE_DENSITY = 0.15d;

  private NoiseType type;
  private String color;
  private Float lineWidth;
  private Integer standardDeviation;
  private Integer mean;
  private Double noiseDensity;

  public Noise() {
    this.type = NoiseType.STRAIGHTLINE;
    this.noiseDensity = 0.12;
  }

  public NoiseType getType() {
    return type;
  }

  public void setType(NoiseType type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Float getLineWidth() {
    return lineWidth;
  }

  public void setLineWidth(Float lineWidth) {
    this.lineWidth = lineWidth;
  }

  public Integer getStandardDeviation() {
    return standardDeviation;
  }

  public void setStandardDeviation(Integer standardDeviation) {
    this.standardDeviation = standardDeviation;
  }

  public Integer getMean() {
    return mean;
  }

  public void setMean(Integer mean) {
    this.mean = mean;
  }

  public Double getNoiseDensity() {
    return noiseDensity;
  }

  public void setNoiseDensity(Double noiseDensity) {
    this.noiseDensity = noiseDensity;
  }

  public NoiseProducer getNoiseProducer() {
    return switch (this.type) {
      case STRAIGHTLINE -> new StraightLineNoiseProducer(hexToColor(color).orElse(Color.RED),
          ofNullable(lineWidth).map(Float::intValue).orElse(DEFAULT_STRAIGHTLINE_WIDTH));
      case CURVEDLINE -> new CurvedLineNoiseProducer(hexToColor(color).orElse(Color.BLACK),
          ofNullable(lineWidth).orElse(DEFAULT_CURVEDLINE_WIDTH));
      case GAUSSIAN -> new GaussianNoiseProducer(ofNullable(standardDeviation).orElse(DEFAULT_STANDARD_DEVIATION),
          ofNullable(mean).orElse(DEFAULT_MEAN));
      case SALTANDPEPPER -> new SaltAndPepperNoiseProducer(ofNullable(noiseDensity).orElse(DEFAULT_NOISE_DENSITY));
      default -> new StraightLineNoiseProducer();
    };
  }
}
