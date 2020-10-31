CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                 COMMENT 'Уникальный идентификатор'      PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50) NOT NULL    COMMENT 'Название организации',
    full_name   VARCHAR(50) NOT NULL    COMMENT 'Полное название организации',
    inn         VARCHAR(15) NOT NULL    COMMENT 'ИНН организации',
    kpp         VARCHAR(15) NOT NULL    COMMENT 'КПП организации',
    address     VARCHAR(80) NOT NULL    COMMENT 'Адрес организации',
    phone       VARCHAR(20)             COMMENT 'Номер организации',
    is_active   BOOLEAN                 COMMENT 'Состояние организации'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id          INTEGER                 COMMENT 'Уникальный идентификатор'      PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    org_id      INTEGER     NOT NULL    COMMENT 'Индентификатор организации',
    name        VARCHAR(50)             COMMENT 'Название отдела',
    phone       VARCHAR(20)             COMMENT 'Номер офиса',
    is_active   BOOLEAN                 COMMENT 'Состояние офиса'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User_Position (
    id          INTEGER                         COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
    position    VARCHAR(20) NOT NULL    UNIQUE  COMMENT 'Название должности'
);
COMMENT ON TABLE User_Position IS 'Должность';

CREATE TABLE IF NOT EXISTS Country (
    code    INTEGER               COMMENT 'Уникальный идентификатор'    PRIMARY KEY,
    name    VARCHAR(20) NOT NULL  COMMENT 'Гражданство'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE IF NOT EXISTS User_Info (
    id                  INTEGER                 COMMENT 'Уникальный идентификатор'  PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    office_id           INTEGER     NOT NULL    COMMENT 'Индентификатор офиса',
    first_name          VARCHAR(50) NOT NULL    COMMENT 'Имя пользователя',
    second_name         VARCHAR(50)             COMMENT 'Фамилия пользователя',
    middle_name         VARCHAR(50)             COMMENT 'Отчество пользователя',
    position_id         INTEGER     NOT NULL    COMMENT 'Идентификатор должности',
    phone               VARCHAR(20)             COMMENT 'Номер офиса',
    citizenship_code    INTEGER                 COMMENT 'Код страны',
    is_identified       BOOLEAN                 COMMENT 'Состояние человека'
);
COMMENT ON TABLE User_Info IS 'Пользователь';

CREATE TABLE IF NOT EXISTS Doc (
    code    INTEGER                      COMMENT 'Код документа'        PRIMARY KEY,
    name    VARCHAR(20) NOT NULL UNIQUE  COMMENT 'Название документа'
);
COMMENT ON TABLE Doc IS 'Документ';

CREATE TABLE IF NOT EXISTS User_Doc (
    id          INTEGER                     COMMENT 'Уникальный идентификатор'      PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER     NOT NULL        COMMENT 'Служебное поле hibernate',
    user_id     INTEGER     NOT NULL UNIQUE COMMENT 'Идентификатор пользователя',
    doc_name    VARCHAR(20) NOT NULL        COMMENT 'Название документа',
    doc_number  INTEGER     NOT NULL        COMMENT 'Номер документа',
    doc_date    DATE        NOT NULL        COMMENT 'Дата выдачи документа',
    CONSTRAINT UserUC UNIQUE (doc_name, doc_number)
);
COMMENT ON TABLE User_Doc IS 'Документ пользователя';

CREATE INDEX IX_Organization_Is_Active ON Organization (is_active);

CREATE INDEX IX_Office_Is_Active ON Office (is_active);
CREATE INDEX IX_Office_Org_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization (id);

CREATE INDEX IX_User_Info_Office_Id ON User_Info (office_id);
CREATE INDEX IX_User_Info_Position_Id ON User_Info (position_id);
CREATE INDEX IX_User_Info_Citizenship_Code ON User_Info (citizenship_code);
ALTER TABLE User_Info ADD FOREIGN KEY (office_id) REFERENCES Office (id);
ALTER TABLE User_Info ADD FOREIGN KEY (position_id) REFERENCES User_Position (id);
ALTER TABLE User_Info ADD FOREIGN KEY (citizenship_code) REFERENCES Country (code);

CREATE INDEX IX_User_Doc_Doc_Name ON User_Doc (doc_name);
CREATE UNIQUE INDEX UX_User_Doc_User_Id ON User_Doc (user_id);
ALTER TABLE User_Doc ADD FOREIGN KEY (doc_name) REFERENCES Doc (name);
ALTER TABLE User_Doc ADD FOREIGN KEY (user_id) REFERENCES User_Info (id);