#!/bin/sh

# https://github.com/takahirom/android-project-template-2022/blob/main/scripts/dependency_diff.sh

# Usage: scripts/dependency_diff.sh from-branch to-branch
# Example: scripts/dependency_diff.sh main update-compose

echo "dependency diff"
echo "branch: $1 -> $2, module: $3, configuration: $4"

FROM_DEPENDENCIES_FILE="$(echo "$1" | sed 's/\//\\_/g')-dependency.txt"
TO_DEPENDENCIES_FILE="$(echo "$2" | sed 's/\//\\_/g')-dependency.txt"
MODULE=$3
CONFIGURATION=$4

git checkout -q "$1"
git pull -q origin "$1"
./gradlew "$MODULE:dependencies" --configuration "$CONFIGURATION" > "$FROM_DEPENDENCIES_FILE"
git checkout -q "$2"
git pull -q origin "$2"
./gradlew "$MODULE:dependencies" --configuration "$CONFIGURATION" > "$TO_DEPENDENCIES_FILE"

curl -L0 https://github.com/careem/dependency-diff-tldr/releases/download/v0.0.6/dependency-diff-tldr-r8.jar > dependency-diff-tldr-r8.jar

CHECKSUM="$(shasum dependency-diff-tldr-r8.jar | awk '{ print $1 }')"
EXPECTED_CHECKSUM=b76af4e71fe1bc3362207d648542337c21ab91e8
if [ "$CHECKSUM" != $EXPECTED_CHECKSUM ]; then
  echo "dependency-diff-tldr-r8.jar CHECKSUM is not same"
  exit 1
fi
java -jar dependency-diff-tldr-r8.jar "$FROM_DEPENDENCIES_FILE" "$TO_DEPENDENCIES_FILE"