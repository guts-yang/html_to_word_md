# HTML to Multi-Format Converter

A comprehensive Spring Boot Web Application for converting HTML documents into Markdown and Word (.docx) formats.

## Features

- **Web Interface**: User-friendly dashboard for file uploads and task management.
- **Authentication**: Secure login and registration system.
- **Async Processing**: Non-blocking conversion tasks with retry logic.
- **Formats Supported**:
    - HTML to Markdown
    - HTML to Word (.docx)
- **REST API**: Fully functional API for programmatic access.

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/com/example/htmlconverter/
│   │   │   ├── cli/          # CLI entry point (Main.java)
│   │   │   ├── config/       # Spring Configuration (Security)
│   │   │   ├── controller/   # REST Controllers
│   │   │   ├── converter/    # Format Converters (Markdown, Word)
│   │   │   ├── core/         # Core Interfaces
│   │   │   ├── model/        # JPA Entities
│   │   │   ├── repository/   # Data Access Layer
│   │   │   └── service/      # Business Logic
│   │   └── resources/
│   │       ├── static/       # Frontend Assets (HTML, CSS, JS)
│   │       └── application.properties
│   └── test/                 # Unit and Integration Tests
├── pom.xml                   # Maven Build Configuration
└── README.md                 # Project Documentation
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Installation & Running

1. **Build the project**:
   ```bash
   mvn clean package
   ```

2. **Run the application**:
   ```bash
   java -jar target/html-converter-1.0-SNAPSHOT.jar
   ```

3. **Access the Web UI**:
   Open [http://localhost:8080](http://localhost:8080) in your browser.

## API Usage

- **Login**: `POST /api/auth/login`
- **Register**: `POST /api/auth/register`
- **Convert**: `POST /api/convert` (Multipart file upload)
- **Get Tasks**: `GET /api/tasks`

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
