package com.example.htmlconverter.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for parsing HTML files.
 */
public class HtmlParser {

    /**
     * Parses an HTML file into a Jsoup Document.
     *
     * @param file The HTML file to parse.
     * @return The parsed Jsoup Document.
     * @throws IOException If an I/O error occurs.
     */
    public static Document parse(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IOException("Input file does not exist: " + file);
        }
        // "UTF-8" is the default, but being explicit is good.
        // The baseUri is set to the file's path to resolve relative links if needed.
        return Jsoup.parse(file, "UTF-8", file.getAbsoluteFile().getParentFile().toURI().toString());
    }
}
