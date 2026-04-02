
INSERT INTO users (full_name, email, password, user_id, role)
SELECT 'Dr. Ramesh Kumar', 'faculty@college.edu',
'$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6pJmy',
'FAC001', 'FACULTY'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'faculty@college.edu'

-- MERGE = insert if not exists, skip if already there (safe on every restart)

-- Faculty: login with FAC001 / faculty123
MERGE INTO users (user_id, full_name, email, password, role)
KEY(user_id)
VALUES (
  'FAC001',
  'Dr. Ramesh Kumar',
  'faculty@college.edu',
  '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6pJmy',
  'FACULTY'
);

-- Student: login with STU001 / student123
MERGE INTO users (user_id, full_name, email, password, role)
KEY(user_id)
VALUES (
  'STU001',
  'Priya Sharma',
  'student@college.edu',
  '$2a$10$8K1p/a0dR1LXMIgoEDFrwOfMQkLmWMaHJPxqP4Gg/AvXY9bFzwYYi',
  'STUDENT'

);
