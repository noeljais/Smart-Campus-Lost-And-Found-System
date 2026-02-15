@echo off
echo Compiling Smart Campus Lost and Found System...
echo.

REM Create bin directory if it doesn't exist
if not exist "bin" mkdir bin

REM Compile all Java files
javac -cp "lib\postgresql-42.7.1.jar" -d bin src\config\*.java src\model\*.java src\dao\*.java src\util\*.java src\controller\*.java src\view\*.java src\Main.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilation successful!
    echo Compiled files are in the 'bin' directory.
) else (
    echo.
    echo Compilation failed! Please check the errors above.
)

echo.
pause
