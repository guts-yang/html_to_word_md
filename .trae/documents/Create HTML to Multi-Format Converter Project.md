# HTML to Multi-Format Converter Project Plan

This plan outlines the creation of a Java Maven project to convert HTML files into Markdown, PDF, and Word documents.

## 1. Project Initialization & Structure
- **Root Directory**: `d:\coding\java\html_to_pdf`
- **Build Tool**: Maven
- **Directory Structure**:
  - `src/main/java/com/example/htmlconverter`
  - `src/test/java/com/example/htmlconverter`
  - `src/main/resources` (for logging config, etc.)
- **Dependencies (`pom.xml`)**:
  - **HTML Parsing**: `jsoup` (Robust HTML parser)
  - **PDF Conversion**: `itextpdf` (5.5.x) and `xmlworker` (for HTML to PDF)
  - **Word Conversion**: `apache-poi` and `apache-poi-ooxml` (for .docx generation)
  - **Markdown Conversion**: `flexmark-html2md-converter` (or custom Jsoup implementation if preferred for simplicity/control) -> *I will implement a custom Jsoup Visitor for better control and to satisfy the "implementation" requirement.*
  - **CLI Parsing**: `commons-cli`
  - **Testing**: `junit-jupiter` (JUnit 5)
  - **Logging**: `slf4j-simple`

## 2. Architecture Design
The project will follow a modular strategy pattern.

### Core Interfaces
- **`HtmlParser`**: Wrapper around Jsoup to read and clean HTML.
- **`DocumentConverter`**: Interface with method `void convert(File inputFile, File outputFile)`.

### Implementation Classes
- **`MarkdownConverter`**: Implements `DocumentConverter`. Uses Jsoup traversal to map HTML tags to Markdown syntax.
- **`PdfConverter`**: Implements `DocumentConverter`. Uses iText `XMLWorkerHelper` or `PdfWriter` to render HTML to PDF.
- **`WordConverter`**: Implements `DocumentConverter`. Uses Jsoup traversal to map HTML nodes to Apache POI `XWPFDocument` elements (Paragraphs, Runs, Tables).

### CLI Module
- **`Main`**: Entry point. Parses arguments (`-i`, `-o`, `-f`) using Commons CLI. Handles batch processing logic.

## 3. Implementation Steps
1.  **Setup**: Create `pom.xml` and directory structure.
2.  **Core Logic**:
    -   Implement `HtmlParser` utility.
    -   Define `DocumentConverter` interface.
3.  **Converters**:
    -   **Markdown**: Implement `MarkdownConverter` (handling headers, lists, links, formatting).
    -   **PDF**: Implement `PdfConverter` (handling font loading for Chinese characters).
    -   **Word**: Implement `WordConverter` (mapping DOM to POI objects).
4.  **CLI**: Implement argument parsing and batch processing loop.
5.  **Quality Assurance**:
    -   Add Unit Tests for each converter.
    -   Add Exception handling (File not found, Malformed HTML).
    -   Check Chinese character support (Fonts).
6.  **Documentation**: Create `README.md` and JavaDoc.

## 4. Verification
- Create a sample HTML file (with Chinese text and various formatting).
- Run the tool to generate `.md`, `.pdf`, and `.docx`.
- Verify the output files manually and via automated tests.

## 5. Deliverables
- Source code.
- `pom.xml`.
- `README.md`.
- `sample.html` and generated outputs.
