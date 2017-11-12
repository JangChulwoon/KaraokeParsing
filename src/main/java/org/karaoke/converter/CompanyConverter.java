package org.karaoke.converter;

import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.springframework.core.convert.converter.Converter;

import java.beans.PropertyEditorSupport;

public class CompanyConverter implements Converter<String,Company> {

    @Override
    public Company convert(String text) {
        return Company.valueOf(text.toUpperCase());
    }
}
