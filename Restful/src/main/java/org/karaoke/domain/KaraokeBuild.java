package org.karaoke.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class KaraokeBuild {
	private String number;
	private String title;
	private String singer;
	private String lyricist;
	private String composer;

	public String getNumber() {
		return number;
	}

	public KaraokeBuild setNumber(String number) {
		this.number = number;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public KaraokeBuild setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSinger() {
		return singer;
	}

	public KaraokeBuild setSinger(String singer) {
		this.singer = singer;
		return this;
	}

	public String getLyricist() {
		return lyricist;
	}

	public KaraokeBuild setLyricist(String lyricist) {
		this.lyricist = lyricist;
		return this;
	}

	public String getComposer() {
		return composer;
	}

	public KaraokeBuild setComposer(String composer) {
		this.composer = composer;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
