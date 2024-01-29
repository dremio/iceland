#!/bin/bash

DATE="$1"

OLD="`date +%Y%m%d`"

if [ -z $DATE ]; then
  DATE="`date +%Y%m%d`"
fi

echo "Downloading from http://data.gdeltproject.org/events/$DATE.export.CSV.zip"
curl -O http://data.gdeltproject.org/events/$DATE.export.CSV.zip

echo "Extracting $DATE.export.CSV.zip"
unzip $DATE.export.CSV.zip
