package org.karaoke.config;

import org.karaoke.domain.Category;

import java.beans.PropertyEditorSupport;

public class CategoryConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Category category = Category.valueOf( text.toLowerCase());
        setValue(category);
    }
}
