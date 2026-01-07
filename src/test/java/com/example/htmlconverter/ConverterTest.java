package com.example.htmlconverter;

import com.example.htmlconverter.converter.MarkdownConverter;
import com.example.htmlconverter.converter.PdfConverter;
import com.example.htmlconverter.converter.WordConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConverterTest {

    @TempDir
    Path tempDir;

    private File inputHtml;

    @BeforeEach
    void setUp() throws IOException {
        inputHtml = tempDir.resolve("test.html").toFile();
        try (Writer fw = new OutputStreamWriter(new FileOutputStream(inputHtml), StandardCharsets.UTF_8)) {
            fw.write("<html>\n" +
                    "<body>\n" +
                    "    <h1>Title ??</h1>\n" +
                    "    <p>This is a <b>bold</b> paragraph.</p>\n" +
                    "    <ul>\n" +
                    "        <li>Item 1</li>\n" +
                    "        <li>Item 2</li>\n" +
                    "    </ul>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    @Test
    void testMarkdownConversion() throws Exception {
        File output = tempDir.resolve("output.md").toFile();
        new MarkdownConverter().convert(inputHtml, output);
        
        assertTrue(output.exists(), "Markdown output file should exist");
        assertTrue(output.length() > 0, "Markdown file should not be empty");
    }

    @Test
    void testPdfConversion() throws Exception {
        File output = tempDir.resolve("output.pdf").toFile();
        new PdfConverter().convert(inputHtml, output);
        
        assertTrue(output.exists(), "PDF output file should exist");
        assertTrue(output.length() > 0, "PDF file should not be empty");
    }

    @Test
    void testWordConversion() throws Exception {
        File output = tempDir.resolve("output.docx").toFile();
        new WordConverter().convert(inputHtml, output);
        
        assertTrue(output.exists(), "Word output file should exist");
        assertTrue(output.length() > 0, "Word file should not be empty");
    }
}
