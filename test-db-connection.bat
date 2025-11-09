@echo off
echo Testing MySQL Connection
echo =======================

REM Try to connect to MySQL with different common credentials
echo Testing with root/no password...
mysql -u root -e "SELECT VERSION();" >nul 2>&1
if %errorlevel% equ 0 (
    echo SUCCESS: Connected with root and no password
    echo Update application.properties with:
    echo spring.datasource.username=root
    echo spring.datasource.password=
    goto :eof
)

echo Testing with root/password...
mysql -u root -ppassword -e "SELECT VERSION();" >nul 2>&1
if %errorlevel% equ 0 (
    echo SUCCESS: Connected with root and password 'password'
    echo Update application.properties with:
    echo spring.datasource.username=root
    echo spring.datasource.password=password
    goto :eof
)

echo Testing with root/root...
mysql -u root -proot -e "SELECT VERSION();" >nul 2>&1
if %errorlevel% equ 0 (
    echo SUCCESS: Connected with root and password 'root'
    echo Update application.properties with:
    echo spring.datasource.username=root
    echo spring.datasource.password=root
    goto :eof
)

echo.
echo None of the common credentials worked.
echo You'll need to find your actual MySQL credentials.
echo.
echo Common MySQL setups:
echo 1. Username: root, Password: (empty)
echo 2. Username: root, Password: password
echo 3. Username: root, Password: root
echo.
echo If you're using XAMPP or WAMP, try username 'root' with no password.
echo If you've set a custom password, use that instead.
echo.
pause