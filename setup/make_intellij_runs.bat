@echo off
title Generating run files for IntelliJ IDEA...
echo Generating run files for IntelliJ IDEA...
echo.
cd ..
call gradlew.bat genIntellijRuns
cd setup
echo.
echo Now that the runs have been generated, go back to IntelliJ IDEA.
pause
exit /b