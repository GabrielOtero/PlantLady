#!/bin/bash

printf "\nRunning Basic Project Setup..."
printf "\nYou should run this script from root directory (not from 'script' directory)\n\n"


echo "cp scripts/pre-push .git/hooks/"
cp scripts/pre-push .git/hooks/

status=$?

# return 1 exit code if running checks fails
if [ $status -ne 0 ]; then 
    printf "\n   Oops... something went wrong\n\n"  
    exit 1
fi
printf "\n   DONE!! YOU ARE ALL GOOD\n\n"
exit 0