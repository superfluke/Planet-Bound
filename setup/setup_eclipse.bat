@echo off
title Setting up Eclipse workspace...
echo Setting up Eclipse workspace...
echo.
cd ..
call gradlew.bat eclipse
cd setup
echo.
pause
exit /b