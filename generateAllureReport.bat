@echo off

cd /d "%~dp0"

if not exist reports\allure (
    mkdir reports\allure
)

set timestamp=%date:~-4%%date:~3,2%%date:~0,2%_%time:~0,2%%time:~3,2%
set timestamp=%timestamp: =0%

set reportname=reports\allure\allure-report-%timestamp%

echo.
echo Generating Allure Report...

allure generate allure-results -o "%reportname%" --clean

echo.
echo ==================================
echo Report Generated Successfully:
echo ==================================
echo %reportname%

explorer "%reportname%\index.html"
allure open "%reportname%"

pause