
# Assignment 1 repository

GitHub link: https://github.com/diksha2992/cloudcomputing/tree/master/assignments/assignment2

ELB Url: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com


Example GET call:
http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/CS101


## Courses

### Add Course
POST : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course

Ex:
Body:
{
	"courseId":"CS101",
	"department":"CSE"
}

### Get Course
GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/{courseId}

Ex: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/CS101

## Professor:

POST : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/professor

Ex:
Body :
{
	"firstName":"Myfirstname",
  "lastName":"Mylastname",
	"department":"CSE"
}

GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/professor/<professor_id>

## Students
POST :  http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students

Ex:
Body :
{
	"firstName":"Myfirstname",
  "lastName":"Mylastname",
	"department":"CSE"
}

GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students/{studentId}

### Update Student
PUT :  http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students/{studentId}

Ex:
Body :
{
	"firstName":"MyUpdatedfirstname"
}

###  Assign Course to Professor
POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/professor

Ex:
Body:
{
  "courseId":"CS101",
  "professorId":"4123123"
}

### Enroll Student into Course

POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/student

Ex:
Body:
{
  "courseId":"CS101",
  "studentId":"4123123"
}

## Assign TA to Course
POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/ta

Ex:
Body:
{
  "courseId":"CS101",
  "taId":"4123123"
}

## Delete TA to Course

DELETE: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/{courseID}/ta/{studentID}


## Post Announcements to Board

POST: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board

Ex:
Body:
{
  "courseId":"CS101",
  "announcementText":"MidTerm Exam is on 10th Nov"
}

## Get Announcements from Board

GET : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board/{courseID}

## Delete Announcements on Board

DELETE: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board/{courseID}/{announcementID}

## Update Announcements on Board

PUT: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board/{courseID}/{announcementID}

Ex:
Body:
{
  "announcementText":"MidTerm Exam is on 12th Nov"
}
