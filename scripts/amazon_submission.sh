#!/bin/bash

# For local execution, set the following environment variables.
# CLIENT_ID=""
# CLIENT_SECRET=""
# APP_ID=""

API_VERSION="v1"
BASE_URL="https://developer.amazon.com/api/appstore"

# Get the access token
auth_response=$(curl -X POST \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d "grant_type=client_credentials&client_id=$CLIENT_ID&client_secret=$CLIENT_SECRET&scope=appstore::apps:readwrite" \
    https://api.amazon.com/auth/O2/token)
access_token=$(echo "$auth_response" | jq -r '.access_token')

# Get active edit (Get the next version currently being edited)
active_edit_response=$(curl -X GET \
    -H "Authorization: Bearer $access_token" \
    -H "accept: application/json" \
    "$BASE_URL/$API_VERSION/applications/$APP_ID/edits")
active_edit_id=$(echo "$active_edit_response" | jq -r '.id')

edit_id=""

if [ "$active_edit_id" == "null" ]; then
    # # Create a new edit (Created next version)
    new_edit_response=$(curl -X POST \
        -H "Authorization: Bearer $access_token" \
        -H "accept: application/json" \
        "$BASE_URL/$API_VERSION/applications/$APP_ID/edits")
    new_edit_id=$(echo "$new_edit_response" | jq -r '.id')
    edit_id=$new_edit_id
else 
    edit_id=$active_edit_id
fi

# # Get list apks
list_apks_response=$(curl -X GET \
    -H "Authorization: Bearer $access_token" \
    -H "accept: application/json" \
    "$BASE_URL/$API_VERSION/applications/$APP_ID/edits/$edit_id/apks")
apk_ids=$(echo "$list_apks_response" | jq -r '.[].id')
apk_ids_array=($apk_ids)
apk_id=${apk_ids_array[0]}
# echo "$apk_id"

# # Get an etag
get_apk_response=$(curl -i -X GET \
    -H "Authorization: Bearer $access_token" \
    -H "accept: application/json" \
    "$BASE_URL/$API_VERSION/applications/$APP_ID/edits/$edit_id/apks/$apk_id")
etag=$(echo "$get_apk_response" | grep ETag | awk -F': ' '{print $2}')
# echo "$etag"

apk_file_path="$(find "$(pwd)" -name "fireframe*.apk" -path "*/release/*")"
echo "$apk_file_path"

# Replace an apk
replace_apk_response=$(curl -X PUT \
    -H "Authorization: Bearer $access_token" \
    -H "Content-Type: application/octet-stream" \
    -H "accept: application/json" \
    -H "If-Match: $etag" \
    --data-binary @"$apk_file_path" \
    "$BASE_URL/$API_VERSION/applications/$APP_ID/edits/$edit_id/apks/$apk_id/replace")
echo "$replace_apk_response"