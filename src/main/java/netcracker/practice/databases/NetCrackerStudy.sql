--����� �������� ����� ��������� �����������, ����� �������� ���������� ��������� ������. 
--��� ������ ������� ����� �������� ��������� �����������, � ������ ��������� ����� ����� ��������� �����. 
--��������� ������ ���� ������.

CREATE TABLE Worker (
  worker_id INT NOT NULL,
  family VARCHAR (20),
  firstName VARCHAR (20),
  PRIMARY KEY (worker_id)
);

CREATE TABLE Task (
  task_id INT NOT NULL,
  description VARCHAR (100),
  PRIMARY KEY (task_id)
);

CREATE TABLE WorkersTasks (
  worker_id INT NOT NULL,
  task_id INT NOT NULL,
  PRIMARY KEY (worker_id, task_id),
  FOREIGN KEY (worker_id) 
    REFERENCES Worker(worker_id),
  FOREIGN KEY (task_id) 
    REFERENCES Task(task_id)
);

INSERT INTO Worker VALUES (1, '������', '����');
INSERT INTO Worker VALUES (2, '�������', '������');
INSERT INTO Worker VALUES (3, '������', '����');

SELECT * FROM Worker;

INSERT INTO Task VALUES (1, '��������� ������ ��');
INSERT INTO Task VALUES (2, '��������� ��������� ��� ������ ��');
INSERT INTO Task VALUES (3, '��������� �� ��������� �������');
INSERT INTO Task VALUES (4, '�������� �������� �������');

SELECT * FROM Task;

INSERT INTO WorkersTasks VALUES (1, 1);
INSERT INTO WorkersTasks VALUES (1, 2);
INSERT INTO WorkersTasks VALUES (2, 3);
INSERT INTO WorkersTasks VALUES (3, 4);

SELECT firstName, family, Task.DESCRIPTION FROM 
  Worker INNER JOIN WorkersTasks ON (WorkersTasks.WORKER_ID = Worker.WORKER_ID)
  INNER JOIN Task ON (WorkersTasks.TASK_ID = Task.TASK_ID);
