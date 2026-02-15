@echo off
echo Running Smart Campus Lost and Found System...
echo.

REM Check if bin directory exists
if not exist "bin" (
    echo Error: Compiled files not found!
    echo Please run compile.bat first.
    pause
    exit /b 1
)

REM Run the application
java -cp "bin;lib\postgresql-42.7.1.jar" Main

pause
