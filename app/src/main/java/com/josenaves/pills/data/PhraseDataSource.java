package com.josenaves.pills.data;

/**
 * Created by josenaves on 9/17/16.
 */
public interface PhraseDataSource {

    String PHRASES_FILENAME = "phrases.txt";
    String SEPARATOR = "|";

    void importPhrases();
}
