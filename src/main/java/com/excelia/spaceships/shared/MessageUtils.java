package com.excelia.spaceships.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageUtils {

    public static String getMessageSource(String key, Object[] values) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");

        return messageSource.getMessage(key, values, Locale.getDefault());
    }
}