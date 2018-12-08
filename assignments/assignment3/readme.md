# Assignment 3 repository

GitHub link: https://github.com/diksha2992/cloudcomputing/tree/master/assignments/assignment3

ELB Url: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com


Example GET call:
http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/CS101


## Courses

### Add Course
POST : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course

Ex:
Body:
{
	"courseId":"CS105",
	"department":"Mech",
  "notificationTopic":"<sns topic name>"
}

### Get Course
GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/{courseId}

Ex: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/CS101

## Students
POST :  http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students

Ex:
Body :
{
	"firstName":"Myfirstname",
  	"lastName":"Mylastname",
	"department":"CSE",
  	"emailId":"youremailid@domain.com"
}

GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students/{studentId}

### Enroll Student into Course

POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/student/{studentId}/register

Ex:
Body:
{
  "courseId":"CS105"
}

## Post Announcements to Board

POST: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board

Ex:
Body:
{
  "courseId":"CS101",
  "announcementText":"MidTerm Exam is on 10th Nov"
}
