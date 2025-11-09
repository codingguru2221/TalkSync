# TalkSync Application Runner
Write-Host "TalkSync Application Runner" -ForegroundColor Green
Write-Host "=========================" -ForegroundColor Green

# Check if Java is installed
try {
    $javaVersion = java -version 2>&1
    if ($LASTEXITCODE -ne 0) {
        throw "Java not found"
    }
    Write-Host "Java is installed" -ForegroundColor Green
} catch {
    Write-Host "Error: Java is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Java 17 or higher and try again" -ForegroundColor Red
    pause
    exit 1
}

# Check if Maven wrapper exists
if (-not (Test-Path "mvnw")) {
    Write-Host "Error: Maven wrapper not found" -ForegroundColor Red
    Write-Host "Please ensure you're running this script from the project root directory" -ForegroundColor Red
    pause
    exit 1
}

Write-Host ""
Write-Host "Initializing database..." -ForegroundColor Yellow
./init-database.ps1

Write-Host "Starting TalkSync application..." -ForegroundColor Yellow
Write-Host "Make sure MySQL is running and the 'talksync' database exists" -ForegroundColor Yellow
Write-Host "If you encounter connection issues:" -ForegroundColor Yellow
Write-Host "  1. Run test-db-connection.ps1 to test your credentials" -ForegroundColor Yellow
Write-Host "  2. Run update-db-credentials.ps1 to update your credentials" -ForegroundColor Yellow
Write-Host "Press CTRL+C to stop the application" -ForegroundColor Yellow
Write-Host ""

# Run the application
./mvnw spring-boot:run

Write-Host "Application stopped" -ForegroundColor Yellow
pause