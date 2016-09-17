package com.josenaves.pills.data.local;

import static android.support.test.InstrumentationRegistry.getTargetContext;

import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import com.josenaves.pills.data.PhraseDataSource;
import com.josenaves.pills.data.model.Phrase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PillsDbHelpTest {

    private PillsDbHelper pillsDbHelper;
    private PhraseDataSource phraseDataSource;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(PillsDbHelper.DATABASE_NAME);
        pillsDbHelper = PillsDbHelper.getInstance(getTargetContext());
        phraseDataSource = new PhraseDataSourceImpl(getTargetContext());

        phraseDataSource.importPhrases();

    }

    @After
    public void tearDown() throws Exception {
        pillsDbHelper.close();
    }

    @Test
    public void shouldLoadAllPhrases() throws Exception {
        assertEquals(458, phraseDataSource.countPhrases());
    }

    @Test
    public void shouldFoundTwoPhrasesByChicoXavier() throws Exception {
        List<Phrase> phraseList = phraseDataSource.getPhrasesByAuthor("Chico Xavier");
        assertEquals(2, phraseList.size());
    }
}
