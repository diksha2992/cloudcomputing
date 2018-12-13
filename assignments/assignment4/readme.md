# Assignment 4 repository

GitHub link: https://github.com/diksha2992/cloudcomputing/tree/master/assignments/assignment4

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
	"department":"Mech"
}

### Adding a course would trigger stepfunctions, which creates
- Registrar
- Board
- NotificationTopic

### Bonus: Creating Registrar by calling Rest API instead of directly calling DynamoDB, as asked in the question.

### Get Course
GET: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/{courseId}

Ex: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/CS101

