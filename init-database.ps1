# TalkSync Database Initialization Script
Write-Host "TalkSync Database Initialization Script" -ForegroundColor Green
Write-Host "======================================" -ForegroundColor Green

# This script attempts to create the required database for TalkSync
# You may need to adjust the MySQL path based on your installation

$MYSQL_PATH = "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe"
$DB_NAME = "talksync"
$DB_USER = "root"
$DB_PASS = "password"

Write-Host "Checking if MySQL is accessible..." -ForegroundColor Yellow
if (Test-Path $MYSQL_PATH) {
    Write-Host "MySQL found at $MYSQL_PATH" -ForegroundColor Green
} else {
    Write-Host "MySQL not found at expected location" -ForegroundColor Red
    Write-Host "Please ensure MySQL is installed" -ForegroundColor Red
    Write-Host "You may need to manually create the database" -ForegroundColor Red
    Show-ManualInstructions
    exit 1
}

Write-Host "Creating database $DB_NAME..." -ForegroundColor Yellow
& $MYSQL_PATH -u $DB_USER -p$DB_PASS -e "CREATE DATABASE IF NOT EXISTS $DB_NAME;"
if ($LASTEXITCODE -eq 0) {
    Write-Host "Database $DB_NAME created successfully!" -ForegroundColor Green
} else {
    Write-Host "Failed to create database. You may need to create it manually." -ForegroundColor Red
    Show-ManualInstructions
    exit 1
}

Write-Host ""
Write-Host "Database initialization complete!" -ForegroundColor Green
Write-Host "Now you can run the TalkSync application" -ForegroundColor Green
Write-Host ""
pause

function Show-ManualInstructions {
    Write-Host ""
    Write-Host "Manual Database Setup Instructions:" -ForegroundColor Yellow
    Write-Host "1. Open MySQL Command Line Client or Workbench" -ForegroundColor Yellow
    Write-Host "2. Execute the following SQL command:" -ForegroundColor Yellow
    Write-Host "   CREATE DATABASE IF NOT EXISTS talksync;" -ForegroundColor Yellow
    Write-Host "3. Update application.properties with your MySQL credentials if needed" -ForegroundColor Yellow
    Write-Host ""
    pause
}