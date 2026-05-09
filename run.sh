#!/bin/bash

echo "Compiling..."

mvn -q clean compile

rm -rf database.*

mvn -q exec:java -Dexec.mainClass="se.yrgo.client.SimpleClient"

echo "Done :)"