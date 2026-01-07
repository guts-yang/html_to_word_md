package com.example.htmlconverter.converter;

import com.example.htmlconverter.core.DocumentConverter;
import com.example.htmlconverter.core.HtmlParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Converts HTML to Markdown format.
 */
public class MarkdownConverter implements DocumentConverter {

    @Override
    public void convert(File inputFile, File outputFile) throws Exception {
        Document doc = HtmlParser.parse(inputFile);
        StringBuilder md = new StringBuilder();

        // Traverse the body of the HTML document
        doc.body().traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int depth) {
                if (node instanceof TextNode) {
                    String text = ((TextNode) node).text();
                    // Avoid printing just whitespace unless it's significant (which is hard to tell, but usually we trim)
                    // But for markdown, preserving some space is good. Let's just append.
                    // Actually, HTML treats multiple spaces as one. Jsoup's text() normalizes.
                    // getWholeText() preserves. Let's use text() but be careful.
                    // If we use text(), it might be empty if it's just formatting newlines.
                    if (!text.trim().isEmpty()) {
                        md.append(text);
                    }
                } else if (node instanceof Element) {
                    Element el = (Element) node;
                    switch (el.tagName().toLowerCase()) {
                        case "h1": md.append("# "); break;
                        case "h2": md.append("## "); break;
                        case "h3": md.append("### "); break;
                        case "h4": md.append("#### "); break;
                        case "h5": md.append("##### "); break;
                        case "h6": md.append("###### "); break;
                        case "p": md.append("\n"); break;
                        case "li": md.append("- "); break; // Simplified list handling
                        case "strong": 
                        case "b": 
                            md.append("**"); 
                            break;
                        case "em": 
                        case "i": 
                            md.append("*"); 
                            break;
                        case "a": 
                            md.append("["); 
                            break;
                        case "code":
                            md.append("`");
                            break;
                        case "br":
                            md.append("\n");
                            break;
                        case "hr":
                            md.append("\n---\n");
                            break;
                    }
                }
            }

            @Override
            public void tail(Node node, int depth) {
                if (node instanceof Element) {
                    Element el = (Element) node;
                    switch (el.tagName().toLowerCase()) {
                        case "h1": 
                        case "h2": 
                        case "h3": 
                        case "h4": 
                        case "h5": 
                        case "h6": 
                            md.append("\n\n"); 
                            break;
                        case "p": 
                            md.append("\n\n"); 
                            break;
                        case "ul": 
                        case "ol": 
                            md.append("\n"); 
                            break;
                        case "li": 
                            md.append("\n"); 
                            break;
                        case "strong": 
                        case "b": 
                            md.append("**"); 
                            break;
                        case "em": 
                        case "i": 
                            md.append("*"); 
                            break;
                        case "a": 
                            md.append("](").append(el.attr("href")).append(")"); 
                            break;
                        case "code":
                            md.append("`");
                            break;
                        case "div":
                            md.append("\n");
                            break;
                    }
                }
            }
        });

        // Write to file
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(md.toString().trim());
        }
    }
}
