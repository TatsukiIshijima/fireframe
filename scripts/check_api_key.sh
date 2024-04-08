#!/bin/bash

file_path="local.properties"
api_key="$open_weather_api_key"

if [ ! -f "$file_path" ]; then
  echo "Creating $file_path..."
  echo "openWeatherApiKey=$api_key" > "$file_path"
else
  if grep -q "openWeatherApiKey" "$file_path"; then
    echo "openWeatherApiKey is already present in $file_path."
  else
    echo "Adding openWeatherApiKey to $file_path..."
    echo "openWeatherApiKey=$api_key" >> "$file_path"
  fi
fi