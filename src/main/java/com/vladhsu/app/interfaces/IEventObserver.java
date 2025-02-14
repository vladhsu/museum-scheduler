package com.vladhsu.app.interfaces;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IEventObserver {
    void notify(long code, String museumName, String messsage, BufferedWriter writer) throws IOException;
}

