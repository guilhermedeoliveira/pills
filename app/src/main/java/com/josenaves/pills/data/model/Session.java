package com.josenaves.pills.data.model;

/**
 * This class is used to keep track of user session.
 *
 */
public final class Session {

    public static final String PHRASE = "current_phrase";
    public static final String AUTHOR = "author";
    public static final String DATE = "date";

    /** current phrase to be displayed */
    private String currentPhrase;

    /** author */
    private String author;

    /** date when the phrase was drawn */
    private String date;

    public Session(String currentPhrase, String author, String date) {
        this.currentPhrase = currentPhrase;
        this.author = author;
        this.date = date;
    }

    public String getCurrentPhrase() {
        return currentPhrase;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Session{" +
                "currentPhrase='" + currentPhrase + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
