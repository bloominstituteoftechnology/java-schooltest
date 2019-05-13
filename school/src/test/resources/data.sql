DELETE
FROM studcourses;

DELETE
FROM course;

DELETE
FROM student;

DELETE
FROM instructor;

INSERT INTO instructor (instructid, instructname)
    VALUES(1, 'Sally'),
          (2, 'Lucy'),
          (3, 'Charlie');

INSERT INTO course (courseid, coursename, instructid)
	VALUES (1, 'Data Science', 1),
           (2, 'JavaScript', 1),
           (3, 'Node.js',  1),
           (4, 'Java Back End', 2),
           (5, 'Mobile IOS', 2),
           (6, 'Mobile Android',  3);

INSERT INTO student (studid, studname)
    VALUES (1, 'John'),
           (2, 'Julian'),
           (3, 'Mary');

INSERT INTO studcourses (studid, courseid)
    VALUES (1, 1),
           (1, 4),
           (2, 2),
           (3, 3),
           (3, 1),
           (3, 6);

alter sequence hibernate_sequence restart with 10;