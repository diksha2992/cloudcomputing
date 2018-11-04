# Assignment 1 repository

GitHub link: https://github.com/diksha2992/cloudcomputing/tree/master/assignments/assignment1

ELB Url: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com


Example GET call:
http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/programs/CSE


## Program:

POST: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/programs

Content-Type: application/x-www-form-urlencoded

Body:programName=MSIS&programCode=1

GET:http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/programs/{programCode}


## Professor:

POST : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/professor

Content-Type: application/x-www-form-urlencoded

Body : firstName=Teja&lastName=Burugu&department=CSE

GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/professor/<professor_id>



