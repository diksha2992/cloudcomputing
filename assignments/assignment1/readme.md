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

PUT :  http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/students

Content-Type: application/x-www-form-urlencoded

Body: name=Diksha&image=imageurl&programCode=1&studentID={studentID}


## Courses

POST : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course

Content-Type: application/x-www-form-urlencoded

Body: courseName=Cloud&programCode=1

## Enroll Student into Course

POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/student

Content-Type: application/x-www-form-urlencoded

Body: studentID={studentID}&courseID={courseID}

##  Assign Course to Professor
POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/professor

Content-Type: application/x-www-form-urlencoded

Body: professorID={professorID}&courseID={courseID}

## Assign TA to Course
POST :http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/ta

Content-Type: application/x-www-form-urlencoded

Body: studentID={studentID}&courseID={courseID}

## Delete TA to Course

DELETE: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/{courseID}/ta/{studentID}


## Add Notes to Lectures	

POST: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/lectures/notes

Content-Type: application/x-www-form-urlencoded

Body: courseID={courseID}&lectureID={lectureID}&notes=vhdsfhesgfj

## Add Material to Lectures

POST: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/lectures/materials

Content-Type: application/x-www-form-urlencoded

Body: courseID={courseID}&lectureID={lectureID}&material=vhdsfhesgfj


## Post Announcements to Board

POST: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board

Content-Type: application/x-www-form-urlencoded

Body: courseID={courseID}&context=MidtermAnnouncement

## Get Announcements from Board

GET : http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board/{courseID}

## Delete Announcements on Board

DELETE: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board/{courseID}/{announcementID}

## Update Announcements on Board

PUT: http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi/course/board

Content-Type: application/x-www-form-urlencoded

Body: courseID={courseID}&context=MidtermAnnouncement&announcementID={announcementID}










