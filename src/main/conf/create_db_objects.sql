CREATE TABLE APPUSER(user_login varchar(100) not null primary key, fio varchar(100), password varchar(100))|APPUSER|NOREWRITE
CREATE TABLE APPUSER_LOG(user_login varchar(100) not null, log_time date, action_name varchar(100), action_data varchar(500))|APPUSER_LOG|NOREWRITE


CREATE TABLE PERSON(person_id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),create_date date,label varchar(100),puser_puser_id integer)|PERSON|NOREWRITE


CREATE TABLE OPERATION(operation_id INT not null primary key, caption varchar(300), pos int, parent_id INT)|OPERATION

INSERT INTO OPERATION(operation_id,caption,pos) VALUES(1,'Граждане РФ',1)
INSERT INTO OPERATION(operation_id,caption,pos) VALUES(2,'Иностранцы',2)

INSERT INTO OPERATION VALUES(201,'Миграционный учет',1,2)
INSERT INTO OPERATION VALUES(202,'Разрешение на временное проживание в Российской Федерации',2,2)
INSERT INTO OPERATION VALUES(203,'Вид на жительство в Российской Федерации',3,2)
INSERT INTO OPERATION VALUES(204,'Оформление, продление срока действия и восстановление визы',4,2)
INSERT INTO OPERATION VALUES(205,'Приглашения на въезд в Российскую Федерацию иностранных граждан и лиц без гражданства',5,2)
INSERT INTO OPERATION VALUES(206,'Получение патента на осуществление трудовой деятельности иностранными гражданами, прибывшими в Российскую Федерацию в порядке, не требующем получения визы',6,2)
INSERT INTO OPERATION VALUES(207,'Получение высококвалифицированным иностранным специалистом разрешения на работу',7,2)

CREATE TABLE FORM2OPERATION(form_form_id int,operation_operation_id int)|FORM2OPERATION
CREATE TABLE FORM(form_id INT not null primary key, caption varchar(1000), template  varchar(100), pos int)|FORM

INSERT INTO FORM VALUES(1,'Уведомление о прибытии иностранного гражданина в место пребывания','uvedomlenie.jasper',1)
INSERT INTO FORM2OPERATION VALUES(1,201)
INSERT INTO FORM VALUES(2,'Заявление о выдаче разрешения на временное проживание','rvp_3.jasper',1)
INSERT INTO FORM2OPERATION VALUES(2,202)

CREATE TABLE PROPERTY(property_id varchar(100) not null primary key, caption varchar(200),  p_type varchar(50),multiple int default 0,extra varchar(1000) default null)|PROPERTY

INSERT INTO PROPERTY VALUES('lastName','Фамилия','text',0,'')
INSERT INTO PROPERTY VALUES('firstName','Имя','text',0,'')
INSERT INTO PROPERTY VALUES('middleName','Отчество','text',0,'')
INSERT INTO PROPERTY VALUES('citizenship','Гражданство, подданство','text',0,'')
INSERT INTO PROPERTY VALUES('birthday','Дата рождения','text',0,'')
INSERT INTO PROPERTY VALUES('sex','Пол','select',0,'мужской#женский')
INSERT INTO PROPERTY VALUES('birthCountry','Государство рождения','text',0,'')
INSERT INTO PROPERTY VALUES('birthCity','Город рождения','text',0,'')
INSERT INTO PROPERTY VALUES('passportType','Документ, удостоверяющий личность','text',0,'')
INSERT INTO PROPERTY VALUES('passportSerial','Серия','text',0,'')
INSERT INTO PROPERTY VALUES('passportNumber','Номер','text',0,'')
INSERT INTO PROPERTY VALUES('passportDate','Дата выдачи','text',0,'')
INSERT INTO PROPERTY VALUES('passportExpired','Срок действия','text',0,'')
INSERT INTO PROPERTY VALUES('entryResolutionName','Разрешение на пребывание в РФ','select',0,'Нет#Виза#Вид на жительство#Разрешение на временное проживание')
INSERT INTO PROPERTY VALUES('entryResolutionSerial','Серия разрешения','text',0,'')
INSERT INTO PROPERTY VALUES('entryResolutionNumber','Номер разрешения','text',0,'')
INSERT INTO PROPERTY VALUES('entryResolutionDate','Дата выдачи разрешения','text',0,'')
INSERT INTO PROPERTY VALUES('entryResolutionExpired','Срок действия разрешения','text',0,'')
INSERT INTO PROPERTY VALUES('entryGoal','Цель въезда','select',0,'служебная#туризм#деловая#учеба#работа#частная#транзит#гуманитарная#другая')
INSERT INTO PROPERTY VALUES('profession','Профессия','text',0,'')
INSERT INTO PROPERTY VALUES('entryDate','Дата въезда','text',0,'')
INSERT INTO PROPERTY VALUES('entryEndDate','Срок пребывания до','text',0,'')
INSERT INTO PROPERTY VALUES('migrationCardSerial','Миграционная карта: серия','text',0,'')
INSERT INTO PROPERTY VALUES('migrationCardNumber','Миграционная карта: номер','text',0,'')
INSERT INTO PROPERTY VALUES('representative1','Законный представитель','text',0,'')
INSERT INTO PROPERTY VALUES('representative2','Законный представитель','text',0,'')
INSERT INTO PROPERTY VALUES('prevAddress1','Прежний адрес','text',0,'')
INSERT INTO PROPERTY VALUES('prevAddress2','Прежний адрес','text',0,'')
INSERT INTO PROPERTY VALUES('prevAddress3','Прежний адрес','text',0,'')
INSERT INTO PROPERTY VALUES('prevAddress4','Прежний адрес','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressRegion','МП область','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressDistrict','МП район','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressCity','МП нас. пункт','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressStreet','МП улица','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressHouse','МП дом','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressCorpus','МП корпус','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressBuilding','МП строение','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressApartment','МП квартира','text',0,'')
INSERT INTO PROPERTY VALUES('entryAddressPhone','МП тел.','text',0,'')
INSERT INTO PROPERTY VALUES('hostType','Тип принимающей стороны','select',0,'Организация#Физ. лицо')
INSERT INTO PROPERTY VALUES('hostLastName','Фамилия','text',0,'')
INSERT INTO PROPERTY VALUES('hostFirstName','Имя','text',0,'')
INSERT INTO PROPERTY VALUES('hostMiddleName','Отчество','text',0,'')
INSERT INTO PROPERTY VALUES('hostBirthday','Дата рождения','text',0,'')
INSERT INTO PROPERTY VALUES('hostPassportType','Документ, удостоверяющий личность','text',0,'')
INSERT INTO PROPERTY VALUES('hostPassportSerial','Серия','text',0,'')
INSERT INTO PROPERTY VALUES('hostPassportNumber','Номер','text',0,'')
INSERT INTO PROPERTY VALUES('hostPassportDate','Дата выдачи','text',0,'')
INSERT INTO PROPERTY VALUES('hostPassportExpired','Срок действия','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressRegion','Область','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressDistrict','Район','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressCity','Нас. пункт','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressStreet','Улица','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressHouse','Дом','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressCorpus','Корпус','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressBuilding','Строение','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressApartment','Квартира','text',0,'')
INSERT INTO PROPERTY VALUES('hostAddressPhone','Тел','text',0,'')
INSERT INTO PROPERTY VALUES('hostCompanyName','Наименование организации','text',0,'')
INSERT INTO PROPERTY VALUES('hostFactAddress','Фактический адрес','text',0,'')
INSERT INTO PROPERTY VALUES('hostINN','ИНН','text',0,'')
INSERT INTO PROPERTY VALUES('relatives', 'Родственники', 'table',0,'')
INSERT INTO PROPERTY VALUES('work_exp', 'Трудовая деятельность', 'table',0,'')

CREATE TABLE TAB_PROPERTY_FIELD(tab_name varchar(100) not null, tab_field_name varchar(100), caption varchar(200),  p_type varchar(50),multiple int default 0,extra varchar(1000) default null, position int)|TAB_PROPERTY_FIELD

INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'rel_degree', 'Степень родства', 'select',0,'отец#мать#супруг(а)#брат#сестра#ребенок', 0)
INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'fio', 'ФИО', 'text',0,'', 1)
INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'birthday', 'Дата рождения', 'date',0,'', 2)
INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'birth_place', 'Место рождения', 'text',0,'', 3)
INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'citizenship', 'Гражданство', 'text',0,'', 4)
INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'address', 'Страна проживания и адрес', 'text',0,'', 4)
INSERT INTO TAB_PROPERTY_FIELD VALUES('relatives', 'workplace', 'Место работы, учебы', 'text',0,'', 5)

INSERT INTO TAB_PROPERTY_FIELD VALUES('work_exp', 'hire_date', 'Дата приема', 'date',0,'', 1)
INSERT INTO TAB_PROPERTY_FIELD VALUES('work_exp', 'dismissal_date', 'Дата увольнения', 'date',0,'', 2)
INSERT INTO TAB_PROPERTY_FIELD VALUES('work_exp', 'position', 'Должность', 'text',0,'', 3)
INSERT INTO TAB_PROPERTY_FIELD VALUES('work_exp', 'organisation', 'Организация', 'text',0,'', 4)
INSERT INTO TAB_PROPERTY_FIELD VALUES('work_exp', 'address', 'Адрес места работы (страна, город, область, населенный пункт)', 'text',0,'', 5)


CREATE TABLE P_PROPERTY(person_person_id int not null, property_property_id varchar(100) not null, p_value varchar(4000))|P_PROPERTY|NOREWRITE

CREATE TABLE F_PROPERTY(form_form_id int not null, property_property_id varchar(100), required int default 0, presentation varchar(50), position int)|F_PROPERTY

INSERT INTO F_PROPERTY VALUES(1,'lastName',1,'as is',1)
INSERT INTO F_PROPERTY VALUES(1,'firstName',1,'as is',2)
INSERT INTO F_PROPERTY VALUES(1,'middleName',1,'as is',3)
INSERT INTO F_PROPERTY VALUES(1,'citizenship',1,'as is',4)
INSERT INTO F_PROPERTY VALUES(1,'birthday',1,'as is',5)
INSERT INTO F_PROPERTY VALUES(1,'sex',1,'as is',6)
INSERT INTO F_PROPERTY VALUES(1,'birthCountry',1,'as is',7)
INSERT INTO F_PROPERTY VALUES(1,'birthCity',1,'as is',8)
INSERT INTO F_PROPERTY VALUES(1,'passportType',1,'as is',9)
INSERT INTO F_PROPERTY VALUES(1,'passportSerial',1,'as is',10)
INSERT INTO F_PROPERTY VALUES(1,'passportNumber',1,'as is',11)
INSERT INTO F_PROPERTY VALUES(1,'passportDate',1,'as is',12)
INSERT INTO F_PROPERTY VALUES(1,'passportExpired',1,'as is',13)
INSERT INTO F_PROPERTY VALUES(1,'entryResolutionName',1,'as is',14)
INSERT INTO F_PROPERTY VALUES(1,'entryResolutionSerial',1,'as is',15)
INSERT INTO F_PROPERTY VALUES(1,'entryResolutionNumber',1,'as is',16)
INSERT INTO F_PROPERTY VALUES(1,'entryResolutionDate',1,'as is',17)
INSERT INTO F_PROPERTY VALUES(1,'entryResolutionExpired',1,'as is',18)
INSERT INTO F_PROPERTY VALUES(1,'entryGoal',1,'as is',19)
INSERT INTO F_PROPERTY VALUES(1,'profession',1,'as is',20)
INSERT INTO F_PROPERTY VALUES(1,'entryDate',1,'as is',21)
INSERT INTO F_PROPERTY VALUES(1,'entryEndDate',1,'as is',22)
INSERT INTO F_PROPERTY VALUES(1,'migrationCardSerial',1,'as is',22)
INSERT INTO F_PROPERTY VALUES(1,'migrationCardNumber',1,'as is',22)
INSERT INTO F_PROPERTY VALUES(1,'representative1',1,'as is',23)
INSERT INTO F_PROPERTY VALUES(1,'representative2',1,'as is',24)
INSERT INTO F_PROPERTY VALUES(1,'prevAddress1',1,'as is',25)
INSERT INTO F_PROPERTY VALUES(1,'prevAddress2',1,'as is',26)
INSERT INTO F_PROPERTY VALUES(1,'prevAddress3',1,'as is',27)
INSERT INTO F_PROPERTY VALUES(1,'prevAddress4',1,'as is',28)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressRegion',1,'as is',29)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressDistrict',1,'as is',30)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressCity',1,'as is',31)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressStreet',1,'as is',32)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressHouse',1,'as is',33)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressCorpus',1,'as is',34)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressBuilding',1,'as is',35)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressApartment',1,'as is',36)
INSERT INTO F_PROPERTY VALUES(1,'entryAddressPhone',1,'as is',37)
INSERT INTO F_PROPERTY VALUES(1,'hostType',1,'as is',38)
INSERT INTO F_PROPERTY VALUES(1,'hostLastName',1,'as is',39)
INSERT INTO F_PROPERTY VALUES(1,'hostFirstName',1,'as is',40)
INSERT INTO F_PROPERTY VALUES(1,'hostMiddleName',1,'as is',41)
INSERT INTO F_PROPERTY VALUES(1,'hostBirthday',1,'as is',42)
INSERT INTO F_PROPERTY VALUES(1,'hostPassportType',1,'as is',43)
INSERT INTO F_PROPERTY VALUES(1,'hostPassportSerial',1,'as is',44)
INSERT INTO F_PROPERTY VALUES(1,'hostPassportNumber',1,'as is',45)
INSERT INTO F_PROPERTY VALUES(1,'hostPassportDate',1,'as is',46)
INSERT INTO F_PROPERTY VALUES(1,'hostPassportExpired',1,'as is',47)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressRegion',1,'as is',48)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressDistrict',1,'as is',49)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressCity',1,'as is',50)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressStreet',1,'as is',51)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressHouse',1,'as is',52)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressCorpus',1,'as is',53)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressBuilding',1,'as is',54)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressApartment',1,'as is',55)
INSERT INTO F_PROPERTY VALUES(1,'hostAddressPhone',1,'as is',56)
INSERT INTO F_PROPERTY VALUES(1,'hostCompanyName',1,'as is',57)
INSERT INTO F_PROPERTY VALUES(1,'hostFactAddress',1,'as is',58)
INSERT INTO F_PROPERTY VALUES(1,'hostINN',1,'as is',59)

INSERT INTO F_PROPERTY VALUES(2,'relatives',1,'as is',1)
INSERT INTO F_PROPERTY VALUES(2,'work_exp',1,'as is',2)



