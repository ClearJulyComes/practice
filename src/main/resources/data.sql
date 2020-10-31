INSERT INTO Organization (id, version, name, full_name, inn, kpp, address) VALUES ( 1, 0, 'Cola', 'Coca-Cola', '34284438932',
                                                                          '1111333432', 'Moscow, 22' );
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address) VALUES ( 2, 0, 'Pepsi', 'Pepsi-Cola HBC', '11114438932',
                                                                           '2222333432', 'Moscow, 20' );
INSERT INTO Office (id, version, org_id, name ) VALUES ( 1, 0, 1, 'HR' );
INSERT INTO Office (id, version, org_id, name ) VALUES ( 2, 0, 1, 'Developers' );
INSERT INTO Office (id, version, org_id, name ) VALUES ( 3, 0, 2, 'Testers' );
INSERT INTO Office (id, version, org_id, name ) VALUES ( 4, 0, 2, 'Jurists' );

INSERT INTO User_Position (id, position ) VALUES ( 1, 'Director' );
INSERT INTO User_Position (id, position ) VALUES ( 2, 'Developer' );

INSERT INTO Country (code, name ) VALUES ( 422, 'Russia' );
INSERT INTO Country (code, name ) VALUES ( 787, 'South Korea' );

INSERT INTO User_Info (id, version, office_id, first_name, position_id, citizenship_code ) VALUES ( 1, 0, 1, 'Viktor', 1, 422 );
INSERT INTO User_Info (id, version, office_id, first_name, position_id, citizenship_code ) VALUES ( 2, 0, 1, 'Andrey', 2, 787 );
INSERT INTO User_Info (id, version, office_id, first_name, position_id ) VALUES ( 3, 0, 1, 'Alex', 2 );
INSERT INTO User_Info (id, version, office_id, first_name, position_id ) VALUES ( 4, 0, 2, 'Andrey', 1 );
INSERT INTO User_Info (id, version, office_id, first_name, position_id ) VALUES ( 5, 0, 3, 'Andrey', 1 );

INSERT INTO Doc (code, name ) VALUES ( 22, 'Driver ID' );
INSERT INTO Doc (code, name ) VALUES ( 77, 'Passport' );

INSERT INTO User_Doc (id, version, user_id, doc_name, doc_number, doc_date ) VALUES ( 1, 0, 1, 'Driver ID', 1111, '2011-11-22' );
INSERT INTO User_Doc (id, version, user_id, doc_name, doc_number, doc_date ) VALUES ( 2, 0, 2, 'Driver ID', 1112, '2010-08-02' );
INSERT INTO User_Doc (id, version, user_id, doc_name, doc_number, doc_date ) VALUES ( 3, 0, 3, 'Passport', 2311, '2008-02-01' );
INSERT INTO User_Doc (id, version, user_id, doc_name, doc_number, doc_date ) VALUES ( 4, 0, 4, 'Passport', 3344, '2015-10-13' );
INSERT INTO User_Doc (id, version, user_id, doc_name, doc_number, doc_date ) VALUES ( 5, 0, 5, 'Passport', 2433, '2014-08-12' );