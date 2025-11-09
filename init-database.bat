@echo off
echo TalkSync Database Initialization Script
echo ======================================

REM This script attempts to create the required database for TalkSync
REM You may need to adjust the MySQL path based on your installation

set MYSQL_PATH="C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe"
set DB_NAME=talksync
set DB_USER=root
set DB_PASS=password

echo Checking if MySQL is accessible...
if exist %MYSQL_PATH% (
    echo MySQL found at %MYSQL_PATH%
) else (
    echo MySQL not found at expected location
    echo Please ensure MySQL is installed
    echo You may need to manually create the database
    goto :manual_instructions
)

echo Creating database %DB_NAME%...
%MYSQL_PATH% -u %DB_USER% -p%DB_PASS% -e "CREATE DATABASE IF NOT EXISTS %DB_NAME%;"
if %errorlevel% equ 0 (
    echo Database %DB_NAME% created successfully!
) else (
    echo Failed to create database. You may need to create it manually.
    goto :manual_instructions
)

echo.
echo Database initialization complete!
echo Now you can run the TalkSync application
echo.
pause
goto :eof

:manual_instructions
echo.
echo Manual Database Setup Instructions:
echo 1. Open MySQL Command Line Client or Workbench
echo 2. Execute the following SQL command:
echo    CREATE DATABASE IF NOT EXISTS talksync;
echo 3. Update application.properties with your MySQL credentials if needed
echo.
pause