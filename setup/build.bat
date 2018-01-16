@echo off
title Building Planet Bound...
echo Building Planet Bound...
echo.
cd ..
call gradlew.bat build
cd setup
echo.
pause
exit /b