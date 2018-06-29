@echo off
title Generating IntelliJ run files...
cd ../..
call gradlew.bat genIntellijRuns
pause