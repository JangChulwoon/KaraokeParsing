package org.karaoke.domain;

import java.sql.Timestamp;
import java.util.List;

public class KaraokesTime {

    private List<Karaoke> karaokes;
    private Timestamp saveTime;

    public KaraokesTime(List<Karaoke> karaokes, Timestamp saveTime) {
        this.karaokes = karaokes;
        this.saveTime = saveTime;
    }

    public List<Karaoke> getKaraokes() {
        return karaokes;
    }

    public KaraokesTime setKaraokes(List<Karaoke> karaokes) {
        this.karaokes = karaokes;
        return this;
    }

    public Timestamp getSaveTime() {
        return saveTime;
    }

    public KaraokesTime setSaveTime(Timestamp saveTime) {
        this.saveTime = saveTime;
        return this;
    }
}
