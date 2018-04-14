package org.karaoke.domain;

import org.joda.time.DateTime;

import java.util.List;

public class KaraokesWrapper {

    private List<Karaoke> karaokes;
    private DateTime lastCalledTime;

    public KaraokesWrapper(List<Karaoke> karaokes, DateTime lastCalledTime) {
        this.karaokes = karaokes;
        this.lastCalledTime = lastCalledTime;
    }

    public List<Karaoke> getKaraokes() {
        return karaokes;
    }

    public KaraokesWrapper setKaraokes(List<Karaoke> karaokes) {
        this.karaokes = karaokes;
        return this;
    }

    public DateTime getLastCalledTime() {
        return lastCalledTime;
    }

    public KaraokesWrapper setLastCalledTime(DateTime lastCalledTime) {
        this.lastCalledTime = lastCalledTime;
        return this;
    }
}
