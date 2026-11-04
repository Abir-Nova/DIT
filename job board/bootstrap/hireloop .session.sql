create database hireloop;
use hireloop;
create table company(
	id_company int PRIMARY KEY auto_increment,
    name_company varchar(50) not null ,
    address_company varchar(100),
    description_company varchar(500)
);

create table category(
	id_category int PRIMARY KEY auto_increment,
    name_category varchar(50) not null
);

create table jobAD(
	id_jobAD int PRIMARY KEY auto_increment,
    title  varchar(100)not null ,
    position varchar(50),
    id_company int not null,
    id_category int not null,
    description_jobAD varchar(500),
    foreign key (id_company) references company(id_company),
    foreign key (id_category) references category(id_category)
);

create table applicant(
	id_applicant int PRIMARY KEY auto_increment,
    name_applicant varchar(50) not null,
    experience_years int ,
    email varchar(50) not null unique
);

create table application(
	id_application int PRIMARY KEY auto_increment,
	message varchar(500),
    date_application datetime not null default current_timestamp,
    id_jobAD int not null, 
    id_applicant int not null ,
    foreign key (id_jobAD) references jobAD(id_jobAD),
    foreign key (id_applicant) references applicant(id_applicant),
    unique(id_jobAD,id_applicant)
	-- Prevent the same applicant applying to the same job more than once
);


-- INSERT 1: Two companies
INSERT INTO company
    (name_company, address_company, description_company)
VALUES
    ('Atlas Digital', 'Marrakech, Morocco',
     'A company building modern web applications.'),

    ('NovaTech', 'Paris, France',
     'A technology company focused on cloud and data.');

-- INSERT 2: Three categories

INSERT INTO category (name_category)
VALUES
    ('Web Development'),
    ('Data Science'),
    ('Cybersecurity');

-- INSERT 3: Three job ads

INSERT INTO jobAD
    (title, position, id_company, id_category, description_jobAD)
VALUES
    ('Junior Angular Developer', 'Frontend Developer',
     1, 1, 'Build user interfaces using Angular and TypeScript.'),

    ('Data Analyst Intern', 'Data Analyst',
     2, 2, 'Analyze datasets and create useful reports.'),

    ('PHP Developer', 'Backend Developer',
     1, 1, 'Develop APIs and maintain PHP applications.');

-- INSERT 4: Three applicants

INSERT INTO applicant
    (name_applicant, experience_years, email)
VALUES
    ('Sara Benali', 1, 'sara@example.com'),
    ('Youssef Amrani', 2, 'youssef@example.com'),
    ('Lina Haddad', 0, 'lina@example.com');

-- INSERT 5: Four applications

INSERT INTO application
    (id_jobAD, id_applicant, message)
VALUES
    (1, 1, 'I have experience with Angular and TypeScript.'),
    (1, 2, 'I am interested in frontend development.'),
    (2, 3, 'I enjoy data analysis and working with Python.'),
    (3, 1, 'I would also like to apply for this PHP position.');

-- how many ads each company posted 

select 
	company.name_company , 
    count(jobAD.id_jobAD) as total_ads
from company
left join jobAD 
on company.id_company = jobAD.id_company
group by company.id_company,company.name_company ;

-- which ad has received the most applications?

SELECT
    j.id_jobAD,
    j.title,
    COUNT(a.id_application) AS total_applications
FROM jobAD AS j
LEFT JOIN application AS a
    ON j.id_jobAD = a.id_jobAD
GROUP BY j.id_jobAD, j.title
ORDER BY total_applications DESC
LIMIT 1;

-- List all applicants for a given ad, with their message

-- Let's say we want all applicants for job ad ID 2

SELECT
    j.title,
    p.name_applicant,
    p.email,
    a.message,
    a.date_application
FROM application AS a
JOIN applicant AS p
    ON a.id_applicant = p.id_applicant
JOIN jobAD AS j
    ON a.id_jobAD = j.id_jobAD
WHERE j.id_jobAD = 2;


	-- SELECT columns_I_want
	-- FROM first_table
	-- JOIN second_table
	-- 		ON matching_condition
	-- WHERE filtering_condition
	-- GROUP BY columns_for_grouping
	-- ORDER BY sorting_column DESC / ASC
	-- LIMIT 1;

  







    




