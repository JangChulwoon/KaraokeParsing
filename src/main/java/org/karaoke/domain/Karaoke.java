package org.karaoke.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

public class Karaoke {

    private String number;
    private String title;
    private String singer;

    public String getNumber() {
        return number;
    }

    public Karaoke setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Karaoke setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public Karaoke setSinger(String singer) {
        this.singer = singer;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
