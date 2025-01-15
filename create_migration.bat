@echo off

:: Create SQL migration file with timestamped filename

:: Usage:
::   create_migration.bat [description]

::   - If description is provided as an argument, it will be used directly.
::   - If no description is provided, the script will prompt for it.

:: Get current date and time in the desired format
for /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set CURRENT_DATE=%%c%%a%%b)
for /f "tokens=1-2 delims=: " %%a in ('time /t') do (set UTC_TIME=%%a%%b)

:: Define the target directory (note the use of backslashes in Windows paths)
set TARGET_DIR=src\main\resources\db\migration

:: Get count of existing .sql files in the target directory
set FILE_COUNT=0
for %%f in (%TARGET_DIR%\*.sql) do set /a FILE_COUNT+=1

:: Pad the count with leading zeros (e.g., 001, 002)
set FILE_COUNT_PADDED=00%FILE_COUNT%
set FILE_COUNT_PADDED=%FILE_COUNT_PADDED:~-3%

:: Check if a description argument was provided
if "%1"=="" (
    :: No argument, prompt for description
    set /p DESCRIPTION="Enter Description: "
) else (
    :: Argument provided, use it directly
    set DESCRIPTION=%1
)

:: Replace spaces with underscores in the description for file safety
set DESCRIPTION_SAFE=%DESCRIPTION: =_%

:: Construct the full filename (ensure backslashes in the path)
set FILENAME=v%CURRENT_DATE%_%UTC_TIME%_%FILE_COUNT_PADDED%__%DESCRIPTION_SAFE%.sql

:: Create the target directory if it doesn't exist
if not exist %TARGET_DIR% mkdir %TARGET_DIR%

:: Create the file and add the comment with the current timestamp
echo -- Created at %DATE% %TIME% > %TARGET_DIR%\%FILENAME%

echo File created successfully: %TARGET_DIR%\%FILENAME%