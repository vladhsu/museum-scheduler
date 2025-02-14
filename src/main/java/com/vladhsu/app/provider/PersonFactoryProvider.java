package com.vladhsu.app.provider;

import com.vladhsu.app.factory.PersonFactory;
import com.vladhsu.app.interfaces.IPersonFactory;

public class PersonFactoryProvider {
    private static IPersonFactory personFactory;

    private PersonFactoryProvider() {}

    public static IPersonFactory getInstance() {
        if (personFactory == null) {
            personFactory = new PersonFactory();
        }

        return personFactory;
    }
}

