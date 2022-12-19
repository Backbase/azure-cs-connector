package com.backbase.communication.validator;

import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.Error;
import com.backbase.communication.model.EmailV1;
import com.backbase.communication.util.ErrorCodes;
import com.backbase.outbound.integration.communications.rest.spec.v1.model.Content;
import com.backbase.outbound.integration.communications.rest.spec.v1.model.Recipient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class EmailV1Validator {
    public void validate(EmailV1 emailV1) {

        if (CollectionUtils.isEmpty(emailV1.getRecipients())) {
            throw new BadRequestException().withMessage(ErrorCodes.ERR_01.getErrorMessage())
                    .withErrors(List.of(new Error().withKey(ErrorCodes.ERR_01.getErrorCode())
                            .withMessage(ErrorCodes.ERR_01.getErrorMessage())));
        }

        if (CollectionUtils.isEmpty(emailV1.getContent())) {
            throw new BadRequestException().withMessage(ErrorCodes.ERR_02.getErrorMessage())
                    .withErrors(List.of(new Error().withKey(ErrorCodes.ERR_02.getErrorCode())
                            .withMessage(ErrorCodes.ERR_02.getErrorMessage())));
        }

        for (Recipient recipient : emailV1.getRecipients()) {

            if (emailV1.getContent()
                    .stream()
                    .map(Content::getContentId)
                    .noneMatch(contentId -> Objects.equals(contentId, recipient.getContentId()))) {

                throw new BadRequestException().withMessage(ErrorCodes.ERR_03.getErrorMessage())
                        .withErrors(List.of(new Error().withKey(ErrorCodes.ERR_03.getErrorCode())
                                .withMessage(ErrorCodes.ERR_03.getErrorMessage())
                                .withContext(Map.of("contentId", recipient.getContentId()))));
            }
        }

    }

}
