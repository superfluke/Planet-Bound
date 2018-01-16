@echo off
title Decompiling Minecraft...
echo Decompiling Minecraft...
echo.
cd ..
call gradlew.bat setupDecompWorkspace
cd setup
echo.
pause
exit /b