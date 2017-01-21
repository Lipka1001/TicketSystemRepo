package com.kubistalipowska.ticketsystem.entities;

/**
 * Created by wilek on 2017-01-19.
 */
public class SongEntity {
    private String name;
    private int length;
    private String genre;
    private String album;

    public SongEntity(String album, int length, String genre, String name) {
        this.album = album;
        this.length = length;
        this.genre = genre;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
