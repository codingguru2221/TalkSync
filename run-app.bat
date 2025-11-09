@echo off
echo TalkSync Application Runner
echo =========================

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 17 or higher and try again
    pause
    exit /b 1
)

REM Check if Maven is available
mvnw --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Maven wrapper not found
    echo Please ensure you're running this script from the project root directory
    pause
    exit /b 1
)

echo.
echo Initializing database...
call init-database.bat

echo Starting TalkSync application...
echo Make sure MySQL is running and the 'talksync' database exists
echo If you encounter connection issues:
echo   1. Run test-db-connection.bat to test your credentials
echo   2. Run update-db-credentials.bat to update your credentials
echo Press CTRL+C to stop the application
echo.

REM Run the application
call mvnw spring-boot:run

echo Application stopped
pause