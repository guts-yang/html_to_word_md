package com.example.htmlconverter.cli;

import com.example.htmlconverter.converter.MarkdownConverter;
import com.example.htmlconverter.converter.PdfConverter;
import com.example.htmlconverter.converter.WordConverter;
import com.example.htmlconverter.core.DocumentConverter;
import org.apache.commons.cli.*;

import java.io.File;

/**
 * Main entry point for the HTML Converter application.
 */
public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Input HTML file or directory path (Required)");
        options.addOption("o", "output", true, "Output file or directory path (Optional)");
        options.addOption("f", "format", true, "Target format: md, pdf, docx (Required)");
        options.addOption("h", "help", false, "Show usage information");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h") || args.length == 0) {
                printHelp(options);
                return;
            }

            String inputPath = cmd.getOptionValue("i");
            if (inputPath == null) {
                System.err.println("Error: Input path (-i) is required.");
                printHelp(options);
                return;
            }

            String format = cmd.getOptionValue("f");
            if (format == null) {
                 System.err.println("Error: Target format (-f) is required.");
                 printHelp(options);
                 return;
            }
            
            File input = new File(inputPath);
            if (!input.exists()) {
                 System.err.println("Error: Input path does not exist: " + inputPath);
                 return;
            }

            // Select converter strategy
            DocumentConverter converter;
            String ext;
            switch (format.toLowerCase()) {
                case "md": 
                case "markdown": 
                    converter = new MarkdownConverter(); 
                    ext = ".md";
                    break;
                case "pdf": 
                    converter = new PdfConverter(); 
                    ext = ".pdf";
                    break;
                case "docx": 
                case "word": 
                    converter = new WordConverter(); 
                    ext = ".docx";
                    break;
                default:
                    System.err.println("Error: Unsupported format '" + format + "'. Supported: md, pdf, docx");
                    return;
            }

            String outputPath = cmd.getOptionValue("o");
            
            if (input.isDirectory()) {
                // Batch processing
                File outputDir = outputPath != null ? new File(outputPath) : input;
                if (!outputDir.exists()) {
                    if (!outputDir.mkdirs()) {
                        System.err.println("Error: Could not create output directory: " + outputDir);
                        return;
                    }
                }
                
                File[] files = input.listFiles((dir, name) -> name.toLowerCase().endsWith(".html") || name.toLowerCase().endsWith(".htm"));
                if (files == null || files.length == 0) {
                    System.out.println("No HTML files found in directory: " + inputPath);
                    return;
                }

                System.out.println("Found " + files.length + " files to convert.");
                for (File f : files) {
                    String filename = f.getName();
                    int dotIndex = filename.lastIndexOf(".");
                    String baseName = dotIndex == -1 ? filename : filename.substring(0, dotIndex);
                    File outputFile = new File(outputDir, baseName + ext);
                    convertFile(converter, f, outputFile);
                }
            } else {
                // Single file processing
                File outputFile;
                if (outputPath == null) {
                    String filename = input.getName();
                    int dotIndex = filename.lastIndexOf(".");
                    String baseName = dotIndex == -1 ? filename : filename.substring(0, dotIndex);
                    outputFile = new File(input.getParent(), baseName + ext);
                } else {
                    outputFile = new File(outputPath);
                    if (outputFile.isDirectory()) {
                         String filename = input.getName();
                         int dotIndex = filename.lastIndexOf(".");
                         String baseName = dotIndex == -1 ? filename : filename.substring(0, dotIndex);
                         outputFile = new File(outputFile, baseName + ext);
                    }
                }
                convertFile(converter, input, outputFile);
            }

        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            printHelp(options);
        }
    }

    private static void convertFile(DocumentConverter converter, File in, File out) {
        try {
            System.out.println("Converting [" + in.getName() + "] -> [" + out.getName() + "] ...");
            converter.convert(in, out);
            System.out.println("Done.");
        } catch (Exception e) {
            System.err.println("Failed to convert " + in.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar html-converter.jar", options);
    }
}
