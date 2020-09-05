./mvnw clean package
scp ./target/competition-0.0.1-SNAPSHOT.jar root@119.3.184.67:/root/
ssh root@119.3.184.67 "
killport 8000
nohup java -jar competition-0.0.1-SNAPSHOT.jar >log.txt &
exit
"