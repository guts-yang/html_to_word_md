@echo off
echo Attempting build with mvnd...
call mvnd clean package
if %ERRORLEVEL% equ 0 goto success

echo mvnd failed or not found. Attempting with mvn...
call mvn clean package
if %ERRORLEVEL% equ 0 goto success

echo Build failed. Please ensure Maven (mvn) or Maven Daemon (mvnd) is in your PATH.
exit /b 1

:success
echo.
echo Build complete.
echo Executable jar is at: target\html-converter-1.0-SNAPSHOT.jar
