# Testing MySQL Connection
Write-Host "Testing MySQL Connection" -ForegroundColor Green
Write-Host "=======================" -ForegroundColor Green

# Try to connect to MySQL with different common credentials
Write-Host "Testing with root/no password..." -ForegroundColor Yellow
try {
    $result = mysql -u root -e "SELECT VERSION();" 2>$null
    if ($LASTEXITCODE -eq 0) {
        Write-Host "SUCCESS: Connected with root and no password" -ForegroundColor Green
        Write-Host "Update application.properties with:" -ForegroundColor Yellow
        Write-Host "spring.datasource.username=root" -ForegroundColor Yellow
        Write-Host "spring.datasource.password=" -ForegroundColor Yellow
        exit 0
    }
} catch {}

Write-Host "Testing with root/password..." -ForegroundColor Yellow
try {
    $result = mysql -u root -ppassword -e "SELECT VERSION();" 2>$null
    if ($LASTEXITCODE -eq 0) {
        Write-Host "SUCCESS: Connected with root and password 'password'" -ForegroundColor Green
        Write-Host "Update application.properties with:" -ForegroundColor Yellow
        Write-Host "spring.datasource.username=root" -ForegroundColor Yellow
        Write-Host "spring.datasource.password=password" -ForegroundColor Yellow
        exit 0
    }
} catch {}

Write-Host "Testing with root/root..." -ForegroundColor Yellow
try {
    $result = mysql -u root -proot -e "SELECT VERSION();" 2>$null
    if ($LASTEXITCODE -eq 0) {
        Write-Host "SUCCESS: Connected with root and password 'root'" -ForegroundColor Green
        Write-Host "Update application.properties with:" -ForegroundColor Yellow
        Write-Host "spring.datasource.username=root" -ForegroundColor Yellow
        Write-Host "spring.datasource.password=root" -ForegroundColor Yellow
        exit 0
    }
} catch {}

Write-Host ""
Write-Host "None of the common credentials worked." -ForegroundColor Red
Write-Host "You'll need to find your actual MySQL credentials." -ForegroundColor Red
Write-Host ""
Write-Host "Common MySQL setups:" -ForegroundColor Yellow
Write-Host "1. Username: root, Password: (empty)" -ForegroundColor Yellow
Write-Host "2. Username: root, Password: password" -ForegroundColor Yellow
Write-Host "3. Username: root, Password: root" -ForegroundColor Yellow
Write-Host ""
Write-Host "If you're using XAMPP or WAMP, try username 'root' with no password." -ForegroundColor Yellow
Write-Host "If you've set a custom password, use that instead." -ForegroundColor Yellow
Write-Host ""
pause