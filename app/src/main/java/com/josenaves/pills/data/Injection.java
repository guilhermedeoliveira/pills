package com.josenaves.pills.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.josenaves.pills.data.local.PhraseDataSourceImpl;

/**
 * Dependency injection without frameworks
 */
public class Injection {
    public static PhraseRepository providePhraseRepository(@NonNull Context context) {
        return new PhraseRepository(new PhraseDataSourceImpl(context));
    }
}
