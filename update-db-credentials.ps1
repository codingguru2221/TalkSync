# Update Database Credentials
Write-Host "Update Database Credentials" -ForegroundColor Green
Write-Host "========================" -ForegroundColor Green

# Get user input
$DB_USER = Read-Host "Enter MySQL username (default: root)"
if ([string]::IsNullOrEmpty($DB_USER)) {
    $DB_USER = "root"
}

$DB_PASS = Read-Host "Enter MySQL password (leave empty if no password)"

Write-Host ""
Write-Host "Updating application.properties with:" -ForegroundColor Yellow
Write-Host "Username: $DB_USER" -ForegroundColor Yellow
Write-Host "Password: $DB_PASS" -ForegroundColor Yellow
Write-Host ""

# Create a backup of the original file
Copy-Item "src/main/resources/application.properties" "src/main/resources/application.properties.bak" -Force

# Update the credentials in application.properties
(Get-Content "src/main/resources/application.properties") -replace 'spring.datasource.username=.*', "spring.datasource.username=$DB_USER" | Set-Content "src/main/resources/application.properties"
(Get-Content "src/main/resources/application.properties") -replace 'spring.datasource.password=.*', "spring.datasource.password=$DB_PASS" | Set-Content "src/main/resources/application.properties"

Write-Host "Database credentials updated successfully!" -ForegroundColor Green
Write-Host "A backup has been created at src/main/resources/application.properties.bak" -ForegroundColor Yellow
Write-Host ""
pause