package com.josenaves.pills.data.model;

/**
 * This class is used to keep track of user session.
 *
 */
public final class Session {

    public static final String PHRASE_ID = "current_phrase_id";
    public static final String PHRASE = "current_phrase";
    public static final String AUTHOR = "author";
    public static final String DATE = "date";
    public static final String IMPORTED = "imported_phrases";

    /** current phrase to be displayed */
    private String currentPhrase;

    /** author */
    private String author;

    /** current phrase id */
    private int phraseId;

    /** date when the phrase was drawn */
    private String date;

    /** indicates whether the phrases file were imported into db */
    private boolean imported;

    public Session(String currentPhrase, String author, String date) {
        this.currentPhrase = currentPhrase;
        this.author = author;
        this.date = date;
    }

    public Session(int phraseId, String currentPhrase, String author, String date, boolean imported) {
        this(currentPhrase, author, date);
        this.phraseId = phraseId;
        this.imported = imported;
    }

    public String getCurrentPhrase() {
        return currentPhrase;
    }

    public String getShareablePhrase() {
        StringBuilder builder = new StringBuilder();
        builder.append(currentPhrase).append("\n")
                .append("\"").append(author).append("\"");;
        String shareablePhrase = builder.toString();

        return shareablePhrase;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public int getPhraseId() {
        return phraseId;
    }

    public boolean isImported() {
        return imported;
    }

    @Override
    public String toString() {
        return "Session{" +
                "currentPhrase='" + currentPhrase + '\'' +
                ", author='" + author + '\'' +
                ", phraseId=" + phraseId +
                ", date='" + date + '\'' +
                ", imported=" + imported +
                '}';
    }
}
