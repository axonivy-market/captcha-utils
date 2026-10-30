package com.axonivy.utils.captcha.service;

import java.util.Locale;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

import com.axonivy.utils.captcha.ImageCaptchaBuilder;

import net.logicsquad.nanocaptcha.audio.AudioCaptcha;
import net.logicsquad.nanocaptcha.audio.noise.RandomNoiseProducer;
import net.logicsquad.nanocaptcha.audio.producer.RandomNumberVoiceProducer;
import net.logicsquad.nanocaptcha.image.ImageCaptcha;

public class AdvancedCaptchaService implements CaptchaService {

  @Override
  public ImageCaptcha generateImageCaptcha(ImageCaptchaBuilder imageCaptchaBuilder) {
    return imageCaptchaBuilder.build();
  }

  @Override
  public AudioCaptcha generateVoiceCaptcha(boolean soundNoise) {
    AudioCaptcha.Builder builder =
        new AudioCaptcha.Builder().addContent().addVoice(new RandomNumberVoiceProducer(Locale.US));

    if (soundNoise) {
      builder.addNoise(new RandomNoiseProducer());
    }
    return builder.build();
  }

  @Override
  public boolean validateCaptcha(String input, String captcha) {
    return allNotNull(input, captcha)
        && !input.isEmpty()
        && captcha.equals(input);
  }
}
