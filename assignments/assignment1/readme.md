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

## Students
POST :  http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students

Content-Type: application/x-www-form-urlencoded

Body: name=Diksha&image=imageurl&programCode=1

GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/{studentID}


## Courses

POST : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course

Content-Type: application/x-www-form-urlencoded

Body: courseName=Cloud&programCode=1

## Enroll Student into Course

POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/student

Content-Type: application/x-www-form-urlencoded

Body: studentID={studentID}&courseID={courseID}

## // Assign Course to Professor
POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/professor

Content-Type: application/x-www-form-urlencoded

Body: professorID={professorID}&courseID={courseID}





