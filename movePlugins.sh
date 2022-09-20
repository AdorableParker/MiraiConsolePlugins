#!/bin/bash

echo 移动开始
for file in `ls`
do
    if [ "$file" != "pluginSet" ]
    then
        jarL+=("./$file/build/mirai/*.jar")
    fi
done

for j in ${jarL[@]}
do
    if [ -f $j ]
    then
        cp $j "./pluginSet/"
    fi
done
ls pluginSet