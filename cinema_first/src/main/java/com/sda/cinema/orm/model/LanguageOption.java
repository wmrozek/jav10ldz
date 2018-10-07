package com.sda.cinema.orm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language_options")
public class LanguageOption {
    @Id
    private int id;
    @Column(name = "language_audio")
    private String audio;
    @Column(name = "subtitles")
    private Integer subtitles;

    public int getId() {
        return id;
    }

    public String getAudio() {
        return audio;
    }

    public Integer getSubtitles() {
        return subtitles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setSubtitles(Integer subtitles) {
        this.subtitles = subtitles;
    }
}