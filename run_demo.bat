@echo off
if not exist target\html-converter-1.0-SNAPSHOT.jar (
    echo Jar file not found. Running build...
    call build.bat
    if errorlevel 1 exit /b 1
)

echo Converting sample.html to Markdown...
java -jar target/html-converter-1.0-SNAPSHOT.jar -i sample.html -o output.md -f markdown

echo Converting sample.html to PDF...
java -jar target/html-converter-1.0-SNAPSHOT.jar -i sample.html -o output.pdf -f pdf

echo Converting sample.html to Word...
java -jar target/html-converter-1.0-SNAPSHOT.jar -i sample.html -o output.docx -f word

echo.
echo Done! Please check output.md, output.pdf, and output.docx.
