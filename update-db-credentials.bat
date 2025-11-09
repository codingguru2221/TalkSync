@echo off
echo Update Database Credentials
echo ========================

set /p DB_USER="Enter MySQL username (default: root): " || set DB_USER=root
set /p DB_PASS="Enter MySQL password (leave empty if no password): " || set DB_PASS=

echo.
echo Updating application.properties with:
echo Username: %DB_USER%
echo Password: %DB_PASS%
echo.

REM Create a backup of the original file
copy "src\main\resources\application.properties" "src\main\resources\application.properties.bak" >nul

REM Update the credentials in application.properties
powershell -Command "(gc 'src\main\resources\application.properties') -replace 'spring.datasource.username=.*', 'spring.datasource.username=%DB_USER%' | Out-File -encoding ASCII 'src\main\resources\application.properties'"
powershell -Command "(gc 'src\main\resources\application.properties') -replace 'spring.datasource.password=.*', 'spring.datasource.password=%DB_PASS%' | Out-File -encoding ASCII 'src\main\resources\application.properties'"

echo Database credentials updated successfully!
echo A backup has been created at src/main/resources/application.properties.bak
echo.
pause