#!/bin/bash
while IFS= read -r line || [ -n "$line" ]; do
  # Skip comment
  if [[ "$line" =~ "#" || -z "$line" ]]; then continue; fi

  # Get values
  domain=$(echo "$line" | awk -F___ '{print $1}')
  service=$(echo "$line" | awk -F___ '{print $2}')
  pod_name=$(echo "$line" | awk -F___ '{print $3}')
  tags=$(echo "$line" | awk -F___ '{print $4}')
  new_column=$(echo "$line" | awk -F___ '{print $5}')
  echo "DOMAIN_VALUE: $domain"
  echo "SERVICE_VALUE: $service"
  echo "POD_NAME_VALUE: $pod_name"
  echo "CUCUMBER_TAGS_VALUE: $tags"
  echo "INSERT_NEW_STATUS_COLUMN_VALUE: $new_column"
  echo "--------------"

  # Create new file
  current_dir=$(pwd)
  temp_file=$current_dir/ci/temp/config_nightly_$pod_name.yml
  sed -e "s/DOMAIN_VALUE/$domain/g" -e "s/SERVICE_VALUE/$service/g" \
      -e "s/POD_NAME_VALUE/$pod_name/g" -e "s/CUCUMBER_TAGS_VALUE/$tags/g" \
      -e "s/INSERT_NEW_STATUS_COLUMN_VALUE/$new_column/g" \
      $current_dir/ci/nightly/nightly_config.yml > $temp_file

  # Delete existing pod
  kubectl delete -f $temp_file --grace-period 0 --force || true

  # Create new pod
  kubectl create -f $temp_file
#  sleep 5

done < $1