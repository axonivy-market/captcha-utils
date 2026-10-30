package com.axonivy.utils.captcha.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.axonivy.utils.captcha.ImageCaptchaBuilder;
import com.axonivy.utils.captcha.enums.ContentType;
import com.axonivy.utils.captcha.model.Content;
import com.axonivy.utils.captcha.service.AdvancedCaptchaService;
import com.axonivy.utils.captcha.service.CaptchaService;

import ch.ivyteam.ivy.environment.IvyTest;
import net.logicsquad.nanocaptcha.audio.AudioCaptcha;
import net.logicsquad.nanocaptcha.image.ImageCaptcha;

@IvyTest
public class CaptchaTest {

  private CaptchaService captchaService = new AdvancedCaptchaService();

  @Test
  public void shouldHaveSameDimensions() {
    // Create test data
    ImageCaptchaBuilder imageCaptchaBuilder = new ImageCaptchaBuilder().withDimensions(150, 50);

    // Test
    ImageCaptcha imageCaptcha = captchaService.generateImageCaptcha(imageCaptchaBuilder);

    // Assertions
    assertEquals(imageCaptchaBuilder.getWidth(), imageCaptcha.getImage().getWidth());
    assertEquals(imageCaptchaBuilder.getHeight(), imageCaptcha.getImage().getHeight());
  }

  @Test
  public void shouldHaveContentNotNull() {
    // Test
    ImageCaptchaBuilder imageCaptchaBuilder = new ImageCaptchaBuilder();
    ImageCaptcha imageCaptcha = captchaService.generateImageCaptcha(imageCaptchaBuilder);

    // Assertions
    assertNotNull(imageCaptcha.getContent());
  }

  @Test
  public void shouldHaveContentLength() {
    // Create test data
    int length = 6;

    Content content = new Content();
    content.setType(ContentType.NUMBERS);
    content.setLength(6);

    ImageCaptchaBuilder imageCaptchaBuilder = new ImageCaptchaBuilder().withContent(content);

    // Test
    ImageCaptcha imageCaptcha = captchaService.generateImageCaptcha(imageCaptchaBuilder);

    // Assertions
    assertEquals(length, imageCaptcha.getContent().length());
  }

  @Test
  public void shouldHaveContentNumber() {
    // Create test data
    Content content = new Content();
    content.setType(ContentType.NUMBERS);
    ImageCaptchaBuilder imageCaptchaBuilder = new ImageCaptchaBuilder().withContent(content);

    // Test
    ImageCaptcha imageCaptcha = captchaService.generateImageCaptcha(imageCaptchaBuilder);

    // Assertions
    assertEquals(true, imageCaptcha.getContent().matches("[0-9]+"));
  }

  @Test
  public void shouldVoiceCaptchaHaveCorrectDefaultLength() {
    // Create test data
    int length = 5;

    // Test
    AudioCaptcha audioCaptcha = captchaService.generateVoiceCaptcha(false);

    // Assertions
    assertEquals(length, audioCaptcha.getContent().length());
  }

  @Test
  public void shouldValidateCaptcha() {
    // Create test data
    String input = "123456";
    String captcha = "111111";

    // Test
    boolean isValid = captchaService.validateCaptcha(input, captcha);

    // Assertions
    assertEquals(false, isValid);
  }

  @Test
  public void shouldAcceptCompleteCaptchaAnswer() {
    assertTrue(captchaService.validateCaptcha("483921", "483921"));
  }

  @Test
  public void shouldRejectEverySingleDigitGuess() {
    for (char digit = '0'; digit <= '9'; digit++) {
      assertFalse(captchaService.validateCaptcha(String.valueOf(digit), "483921"),
          "single digit " + digit + " must not match");
    }
  }

  @Test
  public void shouldRejectPrefixMiddleAndSuffixSubstrings() {
    String captcha = "483921";
    assertFalse(captchaService.validateCaptcha("48", captcha));
    assertFalse(captchaService.validateCaptcha("4839", captcha));
    assertFalse(captchaService.validateCaptcha("392", captcha));
    assertFalse(captchaService.validateCaptcha("21", captcha));
    assertFalse(captchaService.validateCaptcha("921", captcha));
    assertFalse(captchaService.validateCaptcha("39214", captcha));
  }

  @Test
  public void shouldRejectUnrelatedAnswers() {
    String captcha = "483921";
    assertFalse(captchaService.validateCaptcha("123456", captcha));
    assertFalse(captchaService.validateCaptcha("999999", captcha));
    assertFalse(captchaService.validateCaptcha("000000", captcha));
  }

  @Test
  public void shouldRejectExtraCharacters() {
    String captcha = "483921";
    assertFalse(captchaService.validateCaptcha("4839211", captcha));
    assertFalse(captchaService.validateCaptcha("1483921", captcha));
    assertFalse(captchaService.validateCaptcha("483921 ", captcha));
    assertFalse(captchaService.validateCaptcha(" 483921", captcha));
  }

  @Test
  public void shouldRejectCaseDifferences() {
    String captcha = "AbC123";
    assertTrue(captchaService.validateCaptcha("AbC123", captcha));
    assertFalse(captchaService.validateCaptcha("abc123", captcha));
    assertFalse(captchaService.validateCaptcha("ABC123", captcha));
    assertFalse(captchaService.validateCaptcha("bC1", captcha));
  }

  @Test
  public void shouldRejectEmptyInputs() {
    assertFalse(captchaService.validateCaptcha("", "483921"));
    assertFalse(captchaService.validateCaptcha("", ""));
  }

  @Test
  public void shouldRejectNullInputOrChallenge() {
    assertFalse(captchaService.validateCaptcha(null, "483921"));
    assertFalse(captchaService.validateCaptcha("483921", null));
    assertFalse(captchaService.validateCaptcha(null, null));
  }
}
