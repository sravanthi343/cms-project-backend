INSERT INTO users (full_name, email, password, user_id, role)
SELECT 'Dr. Ramesh Kumar', 'faculty@college.edu',
'$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6pJmy',
'FAC001', 'FACULTY'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'faculty@college.edu'
);