package com.example.htmlconverter.converter;

import com.example.htmlconverter.core.DocumentConverter;
import com.example.htmlconverter.core.HtmlParser;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Converts HTML to Word (.docx) format using Apache POI.
 */
public class WordConverter implements DocumentConverter {

    @Override
    public void convert(File inputFile, File outputFile) throws Exception {
        Document htmlDoc = HtmlParser.parse(inputFile);
        try (XWPFDocument docx = new XWPFDocument()) {
            
            htmlDoc.body().traverse(new NodeVisitor() {
                XWPFParagraph currentParagraph = null;

                @Override
                public void head(Node node, int depth) {
                    if (node instanceof Element) {
                        Element el = (Element) node;
                        String tagName = el.tagName().toLowerCase();

                        // Start a new paragraph for block elements
                        if (tagName.startsWith("h") || tagName.equals("p") || tagName.equals("li") || tagName.equals("div")) {
                            // Only create if we aren't already in one (or close previous? No, HTML allows blocks in blocks, but Word is flat)
                            // Flattening: if we encounter a block inside a block, we usually start a new paragraph in Word.
                            currentParagraph = docx.createParagraph();
                            
                            // Basic Header styling
                            if (tagName.startsWith("h")) {
                                currentParagraph.setStyle("Heading" + tagName.substring(1));
                            }
                        }
                    } else if (node instanceof TextNode) {
                        TextNode textNode = (TextNode) node;
                        String text = textNode.text();
                        
                        // Add text if we have a current paragraph and text is not just whitespace
                        // (unless it's a significant whitespace, but let's assume trimmed for now to avoid empty runs)
                        if (currentParagraph != null && !text.isEmpty()) {
                            XWPFRun run = currentParagraph.createRun();
                            run.setText(text);

                            // Apply styles based on ancestry
                            Element parent = (Element) node.parent();
                            while (parent != null && !parent.tagName().equals("body")) {
                                String tag = parent.tagName().toLowerCase();
                                if (tag.equals("b") || tag.equals("strong")) {
                                    run.setBold(true);
                                }
                                if (tag.equals("i") || tag.equals("em")) {
                                    run.setItalic(true);
                                }
                                if (tag.startsWith("h")) {
                                    run.setBold(true);
                                    // Handle font size manually if style doesn't apply
                                    if (tag.equals("h1")) run.setFontSize(16);
                                    if (tag.equals("h2")) run.setFontSize(14);
                                }
                                if (tag.equals("li")) {
                                     // Simulate list item
                                     // In real POI, you setNumID. Here we might just ensure it looks okay.
                                }
                                parent = parent.parent();
                            }
                        }
                    }
                }

                @Override
                public void tail(Node node, int depth) {
                    if (node instanceof Element) {
                        String tagName = ((Element) node).tagName().toLowerCase();
                         // When leaving a block, we are done with that paragraph
                        if (tagName.startsWith("h") || tagName.equals("p") || tagName.equals("li") || tagName.equals("div")) {
                            currentParagraph = null;
                        }
                    }
                }
            });

            try (FileOutputStream out = new FileOutputStream(outputFile)) {
                docx.write(out);
            }
        }
    }
}
