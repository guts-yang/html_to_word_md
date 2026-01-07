package com.example.htmlconverter.core;

import java.io.File;

/**
 * Interface for document conversion strategies.
 */
public interface DocumentConverter {
    /**
     * Converts an HTML file to the target format.
     *
     * @param inputFile  The source HTML file.
     * @param outputFile The destination file.
     * @throws Exception If conversion fails.
     */
    void convert(File inputFile, File outputFile) throws Exception;
}
