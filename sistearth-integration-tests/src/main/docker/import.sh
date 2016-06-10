#!/bin/bash

host="mongo"
db="sistearth"
data_dir="/data"

function import {
    collection="$1"
    file="${data_dir}/$2"

    mongoimport \
        --host ${host} \
        --db ${db} \
        --type "json" \
        --file ${file} \
        --jsonArray
}

sleep 5

import users users.json
import posts posts.json

exit 0