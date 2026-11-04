-- Create the project database if it does not already exist
CREATE DATABASE IF NOT EXISTS hireloop;
USE hireloop;

-- Create the company table
CREATE TABLE IF NOT EXISTS company (
    id_company INT PRIMARY KEY AUTO_INCREMENT,
    name_company VARCHAR(50) NOT NULL,
    address_company VARCHAR(100),
    description_company VARCHAR(500)
);

-- Create the category table
CREATE TABLE IF NOT EXISTS category (
    id_category INT PRIMARY KEY AUTO_INCREMENT,
    name_category VARCHAR(50) NOT NULL
);

-- Create the job advertisement table
CREATE TABLE IF NOT EXISTS jobAD (
    id_jobAD INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    id_company INT NOT NULL,
    id_category INT NOT NULL,
    description_jobAD VARCHAR(500),
    CONSTRAINT fk_jobad_company
        FOREIGN KEY (id_company) REFERENCES company(id_company),
    CONSTRAINT fk_jobad_category
        FOREIGN KEY (id_category) REFERENCES category(id_category)
);

-- Create the applicant table
CREATE TABLE IF NOT EXISTS applicant (
    id_applicant INT PRIMARY KEY AUTO_INCREMENT,
    name_applicant VARCHAR(50) NOT NULL,
    experience_years INT,
    email VARCHAR(50) NOT NULL UNIQUE
);

-- Create the application table
CREATE TABLE IF NOT EXISTS application (
    id_application INT PRIMARY KEY AUTO_INCREMENT,
    message VARCHAR(500),
    date_application DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_jobAD INT NOT NULL,
    id_applicant INT NOT NULL,
    CONSTRAINT fk_application_jobad
        FOREIGN KEY (id_jobAD) REFERENCES jobAD(id_jobAD),
    CONSTRAINT fk_application_applicant
        FOREIGN KEY (id_applicant) REFERENCES applicant(id_applicant),
    CONSTRAINT uq_application_job_applicant UNIQUE (id_jobAD, id_applicant)
);

-- Add sample companies only if their names are not already present
INSERT INTO company (name_company, address_company, description_company)
SELECT 'Atlas Digital', 'Marrakech, Morocco', 'A company building modern web applications.'
WHERE NOT EXISTS (
    SELECT 1 FROM company WHERE name_company = 'Atlas Digital'
);

INSERT INTO company (name_company, address_company, description_company)
SELECT 'NovaTech', 'Paris, France', 'A technology company focused on cloud and data.'
WHERE NOT EXISTS (
    SELECT 1 FROM company WHERE name_company = 'NovaTech'
);

-- Add sample categories only if their names are not already present
INSERT INTO category (name_category)
SELECT 'Web Development'
WHERE NOT EXISTS (
    SELECT 1 FROM category WHERE name_category = 'Web Development'
);

INSERT INTO category (name_category)
SELECT 'Data Science'
WHERE NOT EXISTS (
    SELECT 1 FROM category WHERE name_category = 'Data Science'
);

INSERT INTO category (name_category)
SELECT 'Cybersecurity'
WHERE NOT EXISTS (
    SELECT 1 FROM category WHERE name_category = 'Cybersecurity'
);

-- Add sample job ads only if the same title and company combination is absent
INSERT INTO jobAD (title, position, id_company, id_category, description_jobAD)
SELECT 'Junior Angular Developer', 'Frontend Developer', c.id_company, cat.id_category,
       'Build user interfaces using Angular and TypeScript.'
FROM company AS c
JOIN category AS cat ON cat.name_category = 'Web Development'
WHERE c.name_company = 'Atlas Digital'
  AND NOT EXISTS (
      SELECT 1 FROM jobAD AS j
      WHERE j.title = 'Junior Angular Developer'
        AND j.id_company = c.id_company
  );

INSERT INTO jobAD (title, position, id_company, id_category, description_jobAD)
SELECT 'Data Analyst Intern', 'Data Analyst', c.id_company, cat.id_category,
       'Analyze datasets and create useful reports.'
FROM company AS c
JOIN category AS cat ON cat.name_category = 'Data Science'
WHERE c.name_company = 'NovaTech'
  AND NOT EXISTS (
      SELECT 1 FROM jobAD AS j
      WHERE j.title = 'Data Analyst Intern'
        AND j.id_company = c.id_company
  );

INSERT INTO jobAD (title, position, id_company, id_category, description_jobAD)
SELECT 'PHP Developer', 'Backend Developer', c.id_company, cat.id_category,
       'Develop APIs and maintain PHP applications.'
FROM company AS c
JOIN category AS cat ON cat.name_category = 'Web Development'
WHERE c.name_company = 'Atlas Digital'
  AND NOT EXISTS (
      SELECT 1 FROM jobAD AS j
      WHERE j.title = 'PHP Developer'
        AND j.id_company = c.id_company
  );

-- Add sample applicants only if their email addresses are not already present
INSERT INTO applicant (name_applicant, experience_years, email)
SELECT 'Sara Benali', 1, 'sara@example.com'
WHERE NOT EXISTS (
    SELECT 1 FROM applicant WHERE email = 'sara@example.com'
);

INSERT INTO applicant (name_applicant, experience_years, email)
SELECT 'Youssef Amrani', 2, 'youssef@example.com'
WHERE NOT EXISTS (
    SELECT 1 FROM applicant WHERE email = 'youssef@example.com'
);

INSERT INTO applicant (name_applicant, experience_years, email)
SELECT 'Lina Haddad', 0, 'lina@example.com'
WHERE NOT EXISTS (
    SELECT 1 FROM applicant WHERE email = 'lina@example.com'
);

-- Add sample applications only if that applicant has not already applied to that ad
INSERT INTO application (id_jobAD, id_applicant, message)
SELECT j.id_jobAD, p.id_applicant, 'I have experience with Angular and TypeScript.'
FROM jobAD AS j
JOIN applicant AS p ON p.email = 'sara@example.com'
WHERE j.title = 'Junior Angular Developer'
  AND NOT EXISTS (
      SELECT 1 FROM application AS a
      WHERE a.id_jobAD = j.id_jobAD AND a.id_applicant = p.id_applicant
  );

INSERT INTO application (id_jobAD, id_applicant, message)
SELECT j.id_jobAD, p.id_applicant, 'I am interested in frontend development.'
FROM jobAD AS j
JOIN applicant AS p ON p.email = 'youssef@example.com'
WHERE j.title = 'Junior Angular Developer'
  AND NOT EXISTS (
      SELECT 1 FROM application AS a
      WHERE a.id_jobAD = j.id_jobAD AND a.id_applicant = p.id_applicant
  );

INSERT INTO application (id_jobAD, id_applicant, message)
SELECT j.id_jobAD, p.id_applicant, 'I enjoy data analysis and working with Python.'
FROM jobAD AS j
JOIN applicant AS p ON p.email = 'lina@example.com'
WHERE j.title = 'Data Analyst Intern'
  AND NOT EXISTS (
      SELECT 1 FROM application AS a
      WHERE a.id_jobAD = j.id_jobAD AND a.id_applicant = p.id_applicant
  );

INSERT INTO application (id_jobAD, id_applicant, message)
SELECT j.id_jobAD, p.id_applicant, 'I would also like to apply for this PHP position.'
FROM jobAD AS j
JOIN applicant AS p ON p.email = 'sara@example.com'
WHERE j.title = 'PHP Developer'
  AND NOT EXISTS (
      SELECT 1 FROM application AS a
      WHERE a.id_jobAD = j.id_jobAD AND a.id_applicant = p.id_applicant
  );

-- Query 1: Count how many ads each company has posted
SELECT
    c.name_company,
    COUNT(j.id_jobAD) AS total_ads
FROM company AS c
LEFT JOIN jobAD AS j ON c.id_company = j.id_company
GROUP BY c.id_company, c.name_company;

-- Query 2: Find the ad that has received the most applications
SELECT
    j.id_jobAD,
    j.title,
    COUNT(a.id_application) AS total_applications
FROM jobAD AS j
LEFT JOIN application AS a ON j.id_jobAD = a.id_jobAD
GROUP BY j.id_jobAD, j.title
ORDER BY total_applications DESC
LIMIT 1;

-- Query 3: List applicants and their messages for a chosen ad
-- Change the value 2 to another job ad ID to inspect a different ad
SELECT
    j.title,
    p.name_applicant,
    p.email,
    a.message,
    a.date_application
FROM application AS a
JOIN applicant AS p ON a.id_applicant = p.id_applicant
JOIN jobAD AS j ON a.id_jobAD = j.id_jobAD
WHERE j.id_jobAD = 2;
