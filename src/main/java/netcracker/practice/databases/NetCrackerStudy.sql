--Пусть компания имеет несколько сотрудников, перед которыми поставлены некоторые задачи. 
--Над каждой задачей могут работать несколько сотрудников, и каждый сотрудник может иметь несколько задач. 
--Постройте модель базы данных.

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

INSERT INTO Worker VALUES (1, 'Èâàíîâ', 'Èâàí');
INSERT INTO Worker VALUES (2, 'Ñìèðíîâ', 'Ñåðãåé');
INSERT INTO Worker VALUES (3, 'Ïåòðîâ', 'Ïåòð');

SELECT * FROM Worker;

INSERT INTO Task VALUES (1, 'Ïîñòðîèòü ìîäåëü ÁÄ');
INSERT INTO Task VALUES (2, 'Ïîñòðîèòü äèàãðàììû äëÿ ìîäåëè ÁÄ');
INSERT INTO Task VALUES (3, 'Çàïîëíèòü ÁÄ òåñòîâûìè äàííûìè');
INSERT INTO Task VALUES (4, 'Íàïèñàòü òåñòîâûå çàïðîñû');

SELECT * FROM Task;

INSERT INTO WorkersTasks VALUES (1, 1);
INSERT INTO WorkersTasks VALUES (1, 2);
INSERT INTO WorkersTasks VALUES (2, 3);
INSERT INTO WorkersTasks VALUES (3, 4);

SELECT firstName, family, Task.DESCRIPTION FROM 
  Worker INNER JOIN WorkersTasks ON (WorkersTasks.WORKER_ID = Worker.WORKER_ID)
  INNER JOIN Task ON (WorkersTasks.TASK_ID = Task.TASK_ID);
