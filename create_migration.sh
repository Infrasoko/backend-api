#!/bin/bash

# Create SQL migration file with timestamped filename

# Usage:
#   ./create_migration.sh [description]

#   - If description is provided as an argument, it will be used directly.
#   - If no description is provided, the script will prompt for it.


## Get current timestamp in ISO 8601 UTC format
#TIMESTAMP=$(date -u +"%Y%m%dT%H%M%SZ")

# Get current date in the desired format (YYYYMMDD)
CURRENT_DATE=$(date +%Y%m%d)

# Get current UTC time component (HHMMSS)
UTC_TIME=$(date -u +%H%M)


# Define the target directory
TARGET_DIR="src/main/resources/db/migration"

# Get count of existing .sql files in the target directory
FILE_COUNT=$(find "$TARGET_DIR" -maxdepth 1 -type f -name "*.sql" | wc -l)

# Pad the count with leading zeros (e.g., 001, 002)
FILE_COUNT_PADDED=$(printf "%03d" "${FILE_COUNT}")

# Check if a description argument was provided
if [[ $# -eq 0 ]]; then
    # No argument, prompt for description
    read -p "Enter Description: " DESCRIPTION
else
    # Argument provided, use it directly
    DESCRIPTION="$1"
fi

# Replace spaces with underscores in the description for file safety
DESCRIPTION_SAFE=${DESCRIPTION// /_}

# Construct the full filename
FILENAME="v${CURRENT_DATE}_${UTC_TIME}_${FILE_COUNT_PADDED}__${DESCRIPTION_SAFE}.sql"

# Create the target directory if it doesn't exist
mkdir -p "$TARGET_DIR"

# Create the file and add the comment with the current timestamp
echo "-- Created at $(date -u)" > "$TARGET_DIR/$FILENAME"

echo "File created successfully: $TARGET_DIR/$FILENAME"