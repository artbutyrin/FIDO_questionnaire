DROP TABLE IF EXISTS student_disciplines CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS disciplines CASCADE;

CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    admission_year INTEGER NOT NULL
);

CREATE TABLE disciplines (
    discipline_id SERIAL PRIMARY KEY,
    discipline_name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    credits_count INTEGER NOT NULL,
    hours_count INTEGER NOT NULL
);

CREATE TABLE student_disciplines (
    student_id INTEGER NOT NULL,
    discipline_id INTEGER NOT NULL,
    specialty VARCHAR(255) NOT NULL,
    course_number INTEGER NOT NULL,

    PRIMARY KEY (student_id, discipline_id),

    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (discipline_id) REFERENCES disciplines(discipline_id) ON DELETE CASCADE
);

CREATE INDEX idx_student_disciplines_student_id ON student_disciplines(student_id);
CREATE INDEX idx_student_disciplines_discipline_id ON student_disciplines(discipline_id);
CREATE INDEX idx_student_disciplines_specialty ON student_disciplines(specialty);
CREATE INDEX idx_student_disciplines_course_number ON student_disciplines(course_number);
CREATE INDEX idx_disciplines_name ON disciplines(discipline_name);

-- add tests data

INSERT INTO students (full_name, date_of_birth, email, phone_number, admission_year) VALUES
('Уявний Артем Васильович', '2006-12-23', 'a.uiavnii@ukma.edu.ua', '+380501234567', 2024),
('Вигадана Марія Олександрівна', '2007-02-20', 'm.vuhadana@ukma.edu.ua', '+380502345678', 2023),
('Мальовнича Дарина Юріївна', '2006-12-09', 'd.malyovnicha@ukma.edu.ua', '+380503456789', 2024),
('Смішнявка Вікторія Андріївна', '2007-01-18', 'v.smishniavka@ukma.edu.ua', '+380504567890', 2024),
('Королева Ірина Русланівна', '2007-09-27', 'i.queen@ukma.edu.ua', '+380671122334', 2024),
('Згенерований Дмитро Петрович', '2006-10-25', 'd.generate@ukma.edu.ua', '+380505678901', 2023);

INSERT INTO disciplines (discipline_name, description, credits_count, hours_count) VALUES
('Мови програмування', 'Курс відноситься до блоку фундаментальних дисциплін бакалаврської програми "Комп''ютерні науки"', 5, 150),
('Математичний аналіз', 'Базова математична дисципліна факультету інформатики.', 8, 240),
('Архітектури обчислювальних систем', 'Курс спрямований на вивчення організації і архітектури комп''ютерних систем, починаючи зі стандартної моделі фон Неймана і закінчуючи сучасними новітніми поняттями в архітектурі ЕОМ.', 4, 120),
('Організація і обробка ел. інформації', 'Курс надає базові поняття організації інформаційних систем, систем документообігу, способів структурування та пошуку інформації.', 4, 120),
('Бази даних та інформаційні системи', 'Курс передбачає набуття теоретичних та практичних знань в одній з найактуальніших на сьогодні галузей інформаційних технологій, що стосується баз даних та баз знань.', 9, 270);

INSERT INTO student_disciplines (student_id, discipline_id, specialty, course_number) VALUES
(1, 1, 'Комп''ютерні науки', 1),
(3, 1, 'Комп''ютерні науки', 1),
(4, 1, 'Комп''ютерні науки', 1),
(5, 1, 'Комп''ютерні науки', 1),

(1, 2, 'Комп''ютерні науки', 1),
(3, 2, 'Комп''ютерні науки', 1),
(4, 2, 'Комп''ютерні науки', 1),
(5, 2, 'Комп''ютерні науки', 1),

(1, 3, 'Комп''ютерні науки', 1),
(3, 3, 'Комп''ютерні науки', 1),
(4, 3, 'Комп''ютерні науки', 1),
(5, 3, 'Комп''ютерні науки', 1),

(1, 4, 'Комп''ютерні науки', 1),
(3, 4, 'Комп''ютерні науки', 1),
(4, 4, 'Комп''ютерні науки', 1),
(5, 4, 'Комп''ютерні науки', 1),

(1, 5, 'Комп''ютерні науки', 1),
(3, 5, 'Комп''ютерні науки', 1),
(4, 5, 'Комп''ютерні науки', 1),
(5, 5, 'Комп''ютерні науки', 1),

(2, 1, 'Інженерія програмного забезпечення', 2),
(2, 3, 'Інженерія програмного забезпечення', 2),
(2, 5, 'Інженерія програмного забезпечення', 2),

(6, 1, 'Інженерія програмного забезпечення', 3),
(6, 4, 'Інженерія програмного забезпечення', 3);


-- basic query
SELECT
    s.full_name AS "ПІБ студента",
    s.email AS "Корпоративна пошта студента"
FROM
    students AS s
JOIN
    student_disciplines AS sd ON s.student_id = sd.student_id
JOIN
    disciplines AS d ON sd.discipline_id = d.discipline_id
WHERE
    d.discipline_name = 'Мови програмування'
    AND sd.specialty = 'Комп''ютерні науки'
    AND sd.course_number = 1;

SELECT
    s.full_name AS "Студент",
    s.admission_year AS "Рік вступу",
    d.discipline_name AS "Дисципліна",
    sd.specialty AS "Спеціальність",
    sd.course_number AS "Курс"
FROM
    students s
JOIN
    student_disciplines sd ON s.student_id = sd.student_id
JOIN
    disciplines d ON sd.discipline_id = d.discipline_id
ORDER BY
    s.full_name, d.discipline_name;

SELECT
    d.discipline_name AS "Дисципліна",
    sd.specialty AS "Спеціальність",
    sd.course_number AS "Курс",
    COUNT(s.student_id) AS "Кількість студентів"
FROM
    disciplines d
JOIN
    student_disciplines sd ON d.discipline_id = sd.discipline_id
JOIN
    students s ON sd.student_id = s.student_id
GROUP BY
    d.discipline_name, sd.specialty, sd.course_number
ORDER BY
    d.discipline_name, sd.specialty, sd.course_number;

SELECT
    s.full_name AS "Студент",
    s.email AS "Корпоративна пошта",
    COUNT(sd.discipline_id) AS "Кількість дисциплін"
FROM
    students s
JOIN
    student_disciplines sd ON s.student_id = sd.student_id
GROUP BY
    s.student_id, s.full_name, s.email
HAVING
    COUNT(sd.discipline_id) > 1
ORDER BY
    "Кількість дисциплін" DESC;

SELECT
    sd.specialty AS "Спеціальність",
    sd.course_number AS "Курс",
    COUNT(DISTINCT s.student_id) AS "Кількість студентів"
FROM
    students s
JOIN
    student_disciplines sd ON s.student_id = sd.student_id
GROUP BY
    sd.specialty, sd.course_number
ORDER BY
    sd.specialty, sd.course_number;