package org.karaoke.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Karaoke {

    private String number;
    private String title;
    private String singer;
    private String additionalInfo;

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

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Karaoke setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
