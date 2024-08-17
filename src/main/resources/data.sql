INSERT INTO
    person(
        id,
        name,
        gender,
        cpf,
        birthday,
        email,
        password,
        cep,
        number
    )
    VALUES(
        1,
        'John Doe',
        'MALE',
        '00000000001',
        '1988-12-25',
        'john.doe@exemple.com',
        'strongpassword',
        '01310200',
        '1578'
    )
    ON CONFLICT(id) DO NOTHING;
SELECT setval('person_id_seq', (SELECT MAX(id) FROM person));