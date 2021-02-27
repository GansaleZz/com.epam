package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.IOException;
import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException, IOException {
        final Supplier<ApplicationContext> applicationContextSupplier = null; // todo
        final NassaContext nassaContext = new NassaContext();

        nassaContext.init();
        return applicationContextSupplier::get;
    }
}
