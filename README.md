# Runway Manager

## Build
git clone git@github.com:bhavin-05/RunwayManager.git

cd RunwayManager

mvn clean install

## Start Application
cd RunwayManager

java -jar ./target/RunwayManager-0.0.1-SNAPSHOT.jar

## Command Line Instructions
takeoff 10

land 15

status

priority takeoff 11 0

## Rest Instructions

### Add Runway
POST /v1/api/runway/1

### Landing
POST /v1/api/landing/airplane/10

### Takeoff
POST /v1/api/takeoff/airplane/11

### Status
GET  /v1/api/status

### Priority Change
POST  /v1/api/landing/airplane/10/0     ---- performs PATCH

POST  /v1/api/takeoff/airplane/10/0		  ---- performs PATCH		


