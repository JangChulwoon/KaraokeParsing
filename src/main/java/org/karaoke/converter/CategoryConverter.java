package org.karaoke.converter;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;


@Slf4j
public class CategoryConverter implements Converter<String,Category> {
    @Override
    public Category convert(String text) {
        return Category.valueOf(text.toUpperCase());
    }
}
