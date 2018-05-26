package org.karaoke.parser;

import org.karaoke.domain.Argument;

import java.io.IOException;

public interface Parser {

    String getUrl(Argument argument) throws IOException;

    String getQuery();

}
