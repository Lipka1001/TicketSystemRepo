package com.kubistalipowska.ticketsystem.entities;

/**
 * Created by wilek on 2017-01-19.
 */
public class AlbumEntity {
    private String name;
    private String artist;
    private String date;

    public AlbumEntity(String name, String artist, String date) {
        this.name = name;
        this.artist = artist;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
