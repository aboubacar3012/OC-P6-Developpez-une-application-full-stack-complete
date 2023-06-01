DROP TABLE IF EXISTS User;
CREATE TABLE User
(
  user_id               INT PRIMARY KEY AUTO_INCREMENT,
  firstname             VARCHAR(50)  NOT NULL,
  lastname              VARCHAR(50)  NOT NULL,
  email                 VARCHAR(100) NOT NULL,
  password              VARCHAR(255) NOT NULL,
  role                  ENUM ('USER', 'ADMIN') NOT NULL,
  profile               TEXT
);

DROP TABLE IF EXISTS Subject;
CREATE TABLE Subject
(
  subject_id       INT PRIMARY KEY AUTO_INCREMENT,
  name             VARCHAR(100) NOT NULL,
  description      TEXT,
  created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  subscriber_count INT       DEFAULT 0
);

DROP TABLE IF EXISTS Post;
CREATE TABLE Post
(
  post_id    INT PRIMARY KEY AUTO_INCREMENT,
  title      VARCHAR(255) NOT NULL,
  content    TEXT         NOT NULL,
  author_id  INT          NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  subject_id INT          NOT NULL,
  FOREIGN KEY (author_id) REFERENCES User (user_id),
  FOREIGN KEY (subject_id) REFERENCES Subject (subject_id)
);

DROP TABLE IF EXISTS Subscription;
CREATE TABLE Subscription
(
  subscription_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id         INT NOT NULL,
  subject_id      INT NOT NULL,
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User (user_id),
  FOREIGN KEY (subject_id) REFERENCES Subject (subject_id)
);

DROP TABLE IF EXISTS Feed;
CREATE TABLE Feed
(
  feed_id    INT PRIMARY KEY AUTO_INCREMENT,
  user_id    INT NOT NULL,
  post_id    INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User (user_id),
  FOREIGN KEY (post_id) REFERENCES Post (post_id)
);

DROP TABLE IF EXISTS Comment;
CREATE TABLE Comment
(
  comment_id INT PRIMARY KEY AUTO_INCREMENT,
  author_id INT NOT NULL,
  post_id INT NOT NULL,
  content TEXT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES User (user_id),
  FOREIGN KEY (post_id) REFERENCES Post (post_id)
);


-- Table Subject :

INSERT INTO Subject (name, description)
VALUES
  ('JavaScript', 'Tout ce qui concerne la programmation en JavaScript.'),
  ('Java', 'Tout ce qui concerne la programmation en Java.'),
  ('Python', 'Tout ce qui concerne la programmation en Python.'),
  ('Web3', 'Tout ce qui concerne la technologie Web3.'),
  ('Machine Learning', 'Tout ce qui concerne l apprentissage automatique.');

-- Table Post :

INSERT INTO Post (title, content, author_id, subject_id)
VALUES
  ('Les bases de JavaScript', 'Voici un petit guide pour apprendre les bases de JavaScript.', 1, 1),
  ('Utilisation de Java pour créer une application de bureau', 'Découvrez comment créer une application de bureau en Java.', 2, 2),
  ('Python pour les débutants', 'Voici un guide simple pour apprendre les bases de Python.', 1, 3),
  ('Introduction à la technologie Web3', 'Découvrez comment la technologie Web3 fonctionne et comment elle est utilisée.', 2, 4),
  ('Apprentissage automatique pour la reconnaissance d "images', 'Découvrez comment l"apprentissage automatique est utilisé pour la reconnaissance d"images.', 2, 5);

-- Table Subscription :

INSERT INTO Subscription (user_id, subject_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 4),
  (3, 5);

-- Table Feed :

INSERT INTO Feed (user_id, post_id) VALUES (1, 1);
INSERT INTO Feed (user_id, post_id) VALUES (2, 2);

