# ZTest
Runway Manager

## Build
cd RunwayManager
mvn clean install

## Start Application
cd target
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
POST /v1/api/takeoff/airplane/11
GET  /v1/api/status

### Priority Change
POST  /v1/api/landing/airplane/10/0     // performs PATCH
POST  /v1/api/takeoff/airplane/10/0		// performs PATCH		


