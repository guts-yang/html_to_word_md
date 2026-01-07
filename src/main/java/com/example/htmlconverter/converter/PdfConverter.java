package com.example.htmlconverter.converter;

import com.example.htmlconverter.core.DocumentConverter;
import com.example.htmlconverter.core.HtmlParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.text.Font;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Converts HTML to PDF format using iText and XMLWorker.
 */
public class PdfConverter implements DocumentConverter {

    @Override
    public void convert(File inputFile, File outputFile) throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        // Font provider to support Chinese characters
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        // Register common fonts for Chinese support on Windows
        // In a real production app, you might bundle a font like NotoSansCJK
        try {
             // Try to register SimSum/SimHei which are common on Windows
            fontProvider.register("c:/windows/fonts/simsun.ttc", "SimSun");
            fontProvider.register("c:/windows/fonts/msyh.ttc", "Microsoft YaHei");
        } catch (Exception e) {
            // Log warning or ignore if not found
            System.err.println("Warning: Could not register system fonts: " + e.getMessage());
        }

        // Parse HTML using Jsoup first to ensure it is valid XHTML (required by XMLWorker)
        org.jsoup.nodes.Document jsoupDoc = HtmlParser.parse(inputFile);
        jsoupDoc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        
        try (InputStream is = new ByteArrayInputStream(jsoupDoc.html().getBytes(StandardCharsets.UTF_8))) {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    is, 
                    null, 
                    StandardCharsets.UTF_8, 
                    fontProvider);
        }

        document.close();
    }
}
