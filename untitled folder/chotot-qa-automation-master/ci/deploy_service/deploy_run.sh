#!/bin/bash

total_line_config=$(grep -o -v "#" $1 | grep -c -v "^$")
echo "TOTAL_LINE_CONFIGURATION: $total_line_config"

while IFS= read -r line || [ -n "$line" ]; do
  # Skip comment
  if [[ "$line" =~ "#" || -z "$line" ]]; then continue; fi

  # Get values
  domain=$(echo "$line" | awk -F___ '{print $1}')
  service=$(echo "$line" | awk -F___ '{print $2}')
  pod_name=$(echo "$line" | awk -F___ '{print $3}')
  tags=$(echo "$line" | awk -F___ '{print $4}')
  isFirstPod=$(echo "$line" | awk -F___ '{print $5}')
  sheet_name_fixed=$(echo "$line" | awk -F___ '{print $6}')
  echo "DOMAIN_VALUE: $domain"
  echo "SERVICE_VALUE: $service"
  echo "POD_NAME_VALUE: $pod_name"
  echo "CUCUMBER_TAGS_VALUE: $tags"
  echo "IS_FIRST_POD_VALUE: $isFirstPod"
  echo "SHEET_NAME_FIXED_VALUE": $sheet_name_fixed
  echo "--------------"

  # Create new file
  current_dir=$(pwd)
  temp_file=$current_dir/ci/temp/config_deploy_$pod_name.yml
  if [[ $pod_name == *"ui"* ]]; then
    sed -e "s/DOMAIN_VALUE/$domain/g" \
          -e "s/SERVICE_VALUE/$service/g" \
          -e "s/POD_NAME_VALUE/$pod_name/g" \
          -e "s/CUCUMBER_TAGS_VALUE/$tags/g" \
          -e "s/IS_FIRST_POD_VALUE/$isFirstPod/g" \
          -e "s/SHEET_NAME_FIXED_VALUE/$sheet_name_fixed/g" \
          $current_dir/ci/deploy_service/deploy_config_ui.yml > $temp_file
  else
    sed -e "s/DOMAIN_VALUE/$domain/g" \
        -e "s/SERVICE_VALUE/$service/g" \
        -e "s/POD_NAME_VALUE/$pod_name/g" \
        -e "s/CUCUMBER_TAGS_VALUE/$tags/g" \
        -e "s/IS_FIRST_POD_VALUE/$isFirstPod/g" \
        -e "s/SHEET_NAME_FIXED_VALUE/$sheet_name_fixed/g" \
        $current_dir/ci/deploy_service/deploy_config_api.yml > $temp_file
  fi

  # Delete existing pod
  kubectl delete -f $temp_file --grace-period 0 --force || true

  # Create new pod
  kubectl create -f $temp_file
#  sleep 1

done < $1