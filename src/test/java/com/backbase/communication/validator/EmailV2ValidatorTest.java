package com.backbase.communication.validator;

import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.communication.model.EmailV2;
import com.backbase.communication.testutils.EmailV2Factory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailV2ValidatorTest {
    private final EmailV2Validator emailV2Validator = new EmailV2Validator();

    @Test
    void validate_success() {
        EmailV2 randomEmailV2 = EmailV2Factory.createRandomEmailV2();
        assertDoesNotThrow(() -> emailV2Validator.validate(randomEmailV2));
    }

    @Test
    void validate_BadRequestException_emptyReceipants() {
        EmailV2 randomEmailV2 = EmailV2Factory.createRandomEmailV2();
        randomEmailV2.setTo(List.of());
        randomEmailV2.setCc(List.of());
        randomEmailV2.setBcc(List.of());
        assertThrows(BadRequestException.class, () -> emailV2Validator.validate(randomEmailV2));
    }
}