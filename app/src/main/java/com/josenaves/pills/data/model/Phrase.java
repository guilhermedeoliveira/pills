package com.josenaves.pills.data.model;

/**
 * Represents a phrase.
 */
public class Phrase {

    private long id;
    private String author;
    private String phrase;
    private long views;
    private long shared;

    public Phrase(long id, String author, String phrase, long views, long shared) {
        this.id = id;
        this.author = author;
        this.phrase = phrase;
        this.views = views;
        this.shared = shared;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getPhrase() {
        return phrase;
    }

    public long getViews() {
        return views;
    }

    public long getShared() {
        return shared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phrase phrase1 = (Phrase) o;

        if (id != phrase1.id) return false;
        if (views != phrase1.views) return false;
        if (shared != phrase1.shared) return false;
        if (author != null ? !author.equals(phrase1.author) : phrase1.author != null) return false;
        return phrase != null ? phrase.equals(phrase1.phrase) : phrase1.phrase == null;

    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (phrase != null ? phrase.hashCode() : 0);
        result = 31 * result + (int) (views ^ (views >>> 32));
        result = 31 * result + (int) (shared ^ (shared >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", phrase='" + phrase + '\'' +
                ", views=" + views +
                ", shared=" + shared +
                '}';
    }
}
