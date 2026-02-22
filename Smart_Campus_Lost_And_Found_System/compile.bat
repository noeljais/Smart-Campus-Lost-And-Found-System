@echo off
echo Compiling Smart Campus Lost and Found (Simple)...

javac -cp "lib\postgresql-42.7.1.jar" -d "bin" src\*.java

if %ERRORLEVEL% == 0 (
    echo.
    echo BUILD SUCCESSFUL!
) else (
    echo.
    echo BUILD FAILED. Check errors above.
)
pause
