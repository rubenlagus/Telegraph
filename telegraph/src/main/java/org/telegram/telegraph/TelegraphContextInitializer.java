package org.telegram.telegraph;

import org.telegram.telegraph.executors.DefaultTelegraphExecutorFactory;
import org.telegram.telegraph.executors.TelegraphExecutorFactory;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public final class TelegraphContextInitializer {
    private TelegraphContextInitializer() {
    }

    public static void init() {
        TelegraphContext.register(TelegraphExecutorFactory.class, DefaultTelegraphExecutorFactory.class);
    }
}
