# DBS_A1
Database System Java Code
Deadline: 4/4

bab1754dcc5c35b8c79d080079f4622f4e721200

mongoimport --db DBS_A1 --collection data --file ./output-mongodb.json --jsonArray

For local: java -cp ./DBS_Assign1/DBS_Java/src db.main.Dbload a b c

For Amnz Linux:  [ec2-user@ip-10-88-184-51 PCP_A1]$ java -cp DBS_Java/src db.main.Dbload a b c

export CLASSPATH=/home/ec2-user/PCP_A1/DBS_Java/src

java org.apache.derby.tools.ij

Hello, world