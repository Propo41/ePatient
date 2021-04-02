
Alter login sa with password = '123456'

Alter login sa enable

CREATE DATABASE ePatient
Use ePatient

-- to terminate all processes connected to the db, use this line and then drop database
USE master;
GO
ALTER DATABASE ePatient SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO
DROP DATABASE ePatient

-- makes this database a multi user db
USE MASTER;
ALTER DATABASE ePatient SET MULTI_USER
GO

CREATE TABLE Receptionist
(
    receptionist_id INT IDENTITY(1,1) PRIMARY KEY,
    receptionist_name varchar(50),
    receptionist_pass varchar(50) NOT NULL,
)


CREATE TABLE Admin
(
    admin_id INT IDENTITY(1,1) PRIMARY KEY,
    admin_name varchar(50),
    admin_pass varchar(50),
)


CREATE TABLE Doctor
(
    doctor_id int IDENTITY(1,1) PRIMARY KEY,
    doctor_name varchar(50) NOT NULL,
    doctor_email varchar(50) NOT NULL,
    doctor_phone varchar(20)NOT NULL,
    doctor_address varchar(80) NOT NULL,
    department varchar(30) NOT NULL,
    doctor_specialist varchar(50) NOT NULL,
    hospital_affiliations varchar(max) NOT NULL,
    professional_experience varchar(max) NOT NULL,
    room_number int NOT NULL ,
    educaional_background varchar(max) NOT NULL,
    doctor_password varchar(100) NOT NULL,
    visit_fee varchar(30) NOT NULL,
    joined_date date NOT NULL
)


CREATE TABLE Patient
(
    patient_id int IDENTITY(1,1) PRIMARY KEY,
    patient_name varchar(50) NOT NULL,
    patient_email varchar(50) NOT NULL,
    patient_contact varchar(25) NOT NULL,
    patient_address varchar(80) NULL,
    patient_gender varchar(10) NOT NULL,
    patient_blood_group varchar(5) NOT NULL,
    patient_age int NOT NULL,
    patient_height varchar(5) NOT NULL,
    patient_weight varchar(5) NOT NULL,
    joined_date date NOT NULL,
    patient_emergency_contact varchar(20),
    patient_dob varchar(20)


)


create table MedicalHistory
(
    medical_history_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    patient_id int NOT NULL FOREIGN KEY REFERENCES Patient (patient_id),
    high_blood_pressure bit default 0,
    high_cholesterol bit default 0,
    kidney_disease bit default 0,
    thyroid_problems bit default 0,
    tuberculosis bit default 0,
    sinus bit default 0,
    tonsilities bit default 0,
    lung_disease bit default 0,
    ashtma bit default 0,
    seasonal_allergies bit default 0,
    arthritis bit default 0,
    cancer bit default 0,
    stroke bit default 0,
    diabetes bit default 0,
    pneumonia bit default 0,
    hiv bit default 0,
    hepatitis bit default 0,
)

create table SocialHistory
(
    social_history_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    patient_id int NOT NULL FOREIGN KEY REFERENCES Patient (patient_id),
    alcohol_use varchar(20) NOT NULL,
    tobacco_use varchar(20) NOT NULL,
    caffeine_use varchar(20) NOT NULL,
    drugs_use varchar(20) NOT NULL,
    excercise varchar(20) NOT NULL,
    sleep varchar(20) NOT NULL
)


create table SurgicalHistory
(
    surgical_history_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    patient_id int NOT NULL FOREIGN KEY REFERENCES Patient (patient_id),
    descrption varchar(max) NOT NULL
)

create table Schedule
(
    schedule_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    doctor_id int NOT NULL FOREIGN KEY REFERENCES Doctor (doctor_id),
    doctor_schedule_start_time time NOT NULL,
    doctor_schedule_end_time time NOT NULL,
    schedule_day varchar(100) NOT NULL,
    duration time NOT NULL,
)


create table Appointment
(
    appointment_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    patient_id int NOT NULL FOREIGN KEY REFERENCES Patient(patient_id),
    doctor_id int NOT NULL FOREIGN KEY REFERENCES Doctor(doctor_id),
    start_time time NOT NULL,
    end_time time NOT NULL,
    reason varchar(50) NOT NULL,
    date_of_appointment date NOT NULL,
    appointment_status bit default 0
)

create table Prescription
(
    prescription_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    patient_id int NOT NULL FOREIGN KEY REFERENCES Patient(patient_id),
    doctor_id int NOT NULL FOREIGN KEY REFERENCES Doctor(doctor_id),
    appointment_id int NOT NULL FOREIGN KEY REFERENCES Appointment(appointment_id),
    patient_health_condition varchar(50) NULL,
    comment varchar(100) NULL
)


create table Recommendations
(
    recommendation_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    prescription_id int NOT NULL FOREIGN KEY REFERENCES Prescription(prescription_id),
    exercise_routine varchar(50)  NULL,
    food_chart varchar(50) NULL

)



create table Disease
(
    disease_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    prescription_id int NOT NULL FOREIGN KEY REFERENCES Prescription(prescription_id),
    disease_name varchar(50),
    disease_type varchar(50),
    disease_description varchar(100),

)

CREATE TABLE Test
(
    test_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    prescription_id int NOT NULL FOREIGN KEY REFERENCES Prescription(prescription_id),
    test_name varchar(30) NULL,
    test_report varchar(50) NULL,
    test_description varchar(100) NULL,
    test_date varchar(50) NULL
)


CREATE TABLE Medicine
(
    medicine_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    prescription_id int NOT NULL FOREIGN KEY REFERENCES Prescription(prescription_id),
    medicine_name varchar(50) NULL,
    duration varchar(50) NULL,
    comment varchar(200) NULL
)

CREATE TABLE DoctorQueue
(
    doctor_queue_id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    doctor_id int NOT NULL FOREIGN KEY REFERENCES Doctor(doctor_id) default -1,
    patient_id int  NOT NULL FOREIGN KEY REFERENCES Patient(patient_id) default -1,
    appointment_id int  NOT NULL FOREIGN KEY REFERENCES Appointment(appointment_id) default -1
)

select * from DoctorQueue

insert into DoctorQueue (appointment_id, doctor_id, patient_id)
values(2, 1, 2);



insert into Receptionist (receptionist_name,receptionist_pass)
values('Midoriya','admin1')


insert into Admin (admin_name,admin_pass)
values('Admin','admin1')


INSERT INTO Doctor (doctor_name, doctor_email, doctor_phone, doctor_address, department, doctor_specialist, hospital_affiliations, professional_experience, room_number, educaional_background, doctor_password, visit_fee, joined_date)
VALUES ('Dr. Michel Jordan', 'jordan@gmail.com', '01555500000','Dhanmondi','Dental Surgeon','Dentistry,Surgery','Fellowship','5 years',401,'MS(medicine),FCPS,MMBS','doctor1', '500', '2008-09-15'),
       ('Dr. Karen Dawson', 'karen@gmail.com', '01555500001','Dhanmondi','Paediatric Surgeon','Paediatric,Laparoscopy,Endoscoic','Fellowship','8 years',402,'MS M.CH(Paediatric),FCPS,MMBS', 'doctor1', '500', '2008-11-16'),
       ('Dr. Zakaria', 'zakaria@gmail.com', '01555500022','Dhanmondi','Cardiology','Cardiologist,Surgery','Fellowship','15 years',505,'MS(Cardiology),FCPS,MMBS', 'doctor1', '500', '2008-12-11'),
       ('Dr. Safa', 'safa@gmail.com', '01555500030','Dhanmondi','Neurology','Neurologist,Surgery','Fellowship','10 years',501,'MS(Neurology),FCPS,MMBS', 'doctor1', '500', '2008-11-11'),
       ('Dr. Adam', 'adam@gmail.com', '01555500011','Dhanmondi','Dental Surgeon','Dentistry,Surgery,Implantology','Fellowship','20 years',402,'MS(medicine),FCPS,MMBS','doctor1', '500', '2008-11-11')



INSERT INTO Patient (patient_name, patient_email, patient_contact, patient_address, patient_gender, patient_blood_group, patient_age, patient_height, patient_weight, joined_date, patient_emergency_contact, patient_dob)
VALUES ('Samia', 'samia@yahoo.com','0160000011','Shiddeshwari','Female','B+',21,5.3,60, '2008-11-11', '0168545512', '2008-11-11'),
       ('Ahnaf', 'ahnaf@yahoo.com','0150333011','Dhanmondi','Male','A+',22,5.6,80, '2008-11-11', '0168545512', '2008-11-11'),
       ('Mustofa', 'mustofa@yahoo.com','0133333311','Lalbag','Male','O+',22,5.7,70, '2008-11-11', '0168545512', '2008-11-11'),
       ('Nusrat', 'nusrat@gmail.com','0169999011','Ajimpur','Female','B+',20,5,58, '2008-11-11', '0168545512', '2008-11-11'),
       ('Priota', 'priota@yahoo.com','0177000011','Tejgan','Female','AB+',21,5.4,48, '2008-11-11', '0168545512', '2008-11-11')

-- duration doesnt matter. delete it
INSERT INTO Schedule (doctor_id,doctor_schedule_start_time, doctor_schedule_end_time, schedule_day, duration)
VALUES ('1','07:30:00', '12:00:00','sunday','02:30:00'),
       ('1','10:30:00', '13:00:00','monday','02:30:00'),
       ('1','14:00:00', '18:00:00','tuesday','04:00:00'),
       ('2','10:30:00', '13:00:00','tuesday','02:30:00'),
       ('2','14:00:00', '18:00:00','thursday','04:00:00'),
       ('3','15:00:00', '18:00:00','wednesday','04:00:00'),
       ('3','14:00:00', '18:00:00','tuesday','04:00:00'),
       ('3','14:00:00', '18:00:00','monday','04:00:00'),
       ('4','14:00:00', '18:00:00','sunday','04:00:00'),
       ('4','14:00:00', '18:00:00','thursday','04:00:00'),
       ('5','11:00:00', '18:00:00','friday','04:00:00'),
       ('5','06:30:00', '10:30:00','saturday','04:00:00'),
       ('5','14:00:00', '18:00:00','sunday','04:00:00')



-- stop copying from here. Incorrect dummy entries starts from here
INSERT INTO Appointment (patient_id,doctor_id,start_time, end_time, reason, date_of_appointment, appointment_status)
VALUES ('1','1','10:30:00', '11:00:00','Tooth Pain','2021-03-31', 0),
       ('2','5','11:10:00', '11:50:00','Bleeding from leftside tooth','2021-03-31',0),
       ('3','5','10:30:00', '11:10:00','Tooth Pain','2021-03-31',0),
       ('4','4','14:40:00', '15:20:00','Left eye have pain and headache','2021-03-31',0),
       ('5','4','14:00:00', '14:40:00','Headache since 1 week','2021-03-31', 0)

INSERT INTO Appointment (patient_id,doctor_id,start_time, end_time, reason, date_of_appointment, appointment_status)
VALUES
('2','1','15:00:00', '15:30:00','Bleeding from leftside tooth','2021-04-08',0),
('3','1','15:30:00', '16:00:00','Tooth Pain','2021-04-08',0),
('4','1','16:30:00', '17:00:00','Left eye have pain and headache','2021-04-08',0),
('5','1','17:30:00', '18:00:00','Headache since 1 week','2021-04-08', 0)

INSERT INTO Appointment (patient_id,doctor_id,start_time, end_time,reason,date_of_appointment, appointment_status)
VALUES ('2','1','12:30:00', '14:10:00','Tooth Pain','2021-03-10', 0)

INSERT INTO Appointment (patient_id,doctor_id,start_time, end_time,reason,date_of_appointment, appointment_status)
VALUES ('4','1','12:30:00', '14:10:00','Tooth Pain','2021-03-20', 1)

INSERT INTO Prescription(patient_id, doctor_id, appointment_id, patient_health_condition)
VALUES ('1','1','1', 'Critical'),
       ('2','5','2', 'Mild'),
       ('3','5','3', 'Mild'),
       ('4','4','4', 'Mild'),
       ('5','4','5', 'Critical')

INSERT INTO Recommendations(exercise_routine, food_chart, prescription_id)
VALUES  ('Daily', 'Eat potato only', 1),
        ('Weekly', 'Eat chips only', 2),
        ('2 days a week', 'Eat potato only', 3),
        ('every month', 'Eat potato only', 4),
        ('Daily', 'Eat potato only', 5)


INSERT INTO Disease(prescription_id,disease_name, disease_type, disease_description)
VALUES ('1','Toothache', 'Tooth','Rightside tooth have cavity'),
       ('2','Cracked Tooth', 'Tooth','Leftside tooth cracked and bleeding'),
       ('3','Migraine', 'Headache','Migraine problems are severe'),
       ('4','Headache and left eye pain', 'Headache','Left eye have some pain'),
       ('5','Toothache', 'Tooth','Middel tooth have cavity')

INSERT INTO  Medicine(prescription_id,medicine_name, duration, comment)
VALUES ('1','Napa','continue for 1 month','10 Days when pain started and take after meal'),
       ('2','Napa','1 week','15 Days when pain started and take after meal'),
       ('5','Napa','1 year','28 Days when pain started and take after meal'),
       ('4','Napa','2 years','38 Days when pain started and take after meal'),
       ('3','Napa','2 days','7 Days when pain started and take after meal')


INSERT INTO  Test(prescription_id,test_name, test_report, test_description, test_date)
VALUES ('1','Dental X-ray', 'Cavity','For cavity rightside tootyh was damaged ','11 March 2021'),
       ('2','Dental X-ray', 'Cracked tooth','Infected leftside tooth ','10 March 2021'),
       ('3','Dental X-ray', 'Cavity','For cavity rightside tootyh was damaged ','13 March 2021'),
       ('4','Dental X-ray', 'Cavity','For cavity rightside tootyh was damaged ','14 March 2021'),
       ('5','Dental X-ray', 'Cavity','For cavity rightside tootyh was damaged ','12 March 2021')


select * from Schedule where doctor_id = '5'
select * from Doctor
select * from Patient
select * from Admin
select COUNT(doctor_id) as 'count' from Appointment where doctor_id='1'

/*-----------DOCTOR---------*/

-- get total visits
select count(doctor_id) as 'visits'
from Appointment
where doctor_id = '1'
  and Appointment.appointment_status = '1'

-- get top 5 from recent patient list
-- assumed that if appointment_status = 1, then the patient has visited the doctor before
select top 5 Patient.patient_name, Appointment.date_of_appointment
from Appointment
         left join Patient
                   on Appointment.patient_id = Patient.patient_id
where doctor_id = 1
  and Appointment.appointment_status = 1
ORDER BY Appointment.date_of_appointment DESC

-- get total bill by multiplying total visits by visit fee
-- used sub-query
select (
           select count(doctor_id) as 'visits'
           from Appointment
           where doctor_id = '1'
             and Appointment.appointment_status = 1
       )
           * (select Doctor.visit_fee from Doctor where doctor_id = 1) totalCost


-- get patient list using patient name or id as keyword
select *
from Patient
where (
                  patient_name LIKE '%1%'
              OR patient_id LIKE '% 1%'
          )

-- get patient profile
select *
from Patient
where patient_id = 1

-- get patient prescription history list
-- this will fetch all the prescriptions associated with a patient
select Appointment.patient_id,
       Doctor.doctor_id,
       Doctor.doctor_name,
       Appointment.reason,
       Appointment.date_of_appointment,
       Prescription.prescription_id
from (Prescription inner join Appointment
    on Prescription.appointment_id = Appointment.appointment_id)
         inner join Doctor
                    on Doctor.doctor_id = Appointment.doctor_id
where Appointment.patient_id = 1
  and Appointment.appointment_status = 1


--view patient prescription history list using doctor id or doctor name as keyword
select *
from (Prescription inner join Appointment
    on Prescription.appointment_id = Appointment.appointment_id)
         inner join Doctor on Doctor.doctor_id = Appointment.doctor_id
where (
                  Appointment.patient_id = 4 and
                  Appointment.appointment_status = 1 and
                  (Doctor.doctor_id like '%%' OR
                   Doctor.doctor_name like '%%'
                      ))




-- view doctor profile
select *
from Doctor
where doctor_id = 1

--view patient profile
select *
from (Patient left join MedicalHistory
    on Patient.patient_id = MedicalHistory.patient_id)
         left join SurgicalHistory
                   on SurgicalHistory.patient_id = Patient.patient_id
         left join SocialHistory
                   on SocialHistory.patient_id = Patient.patient_id
where Patient.patient_id = 1


-- get appointment list of the specified doctor in descending order
select Patient.patient_name,
       Appointment.reason,
       Appointment.date_of_appointment,
       Appointment.start_time,
       Appointment.end_time
from Appointment
         left join Patient
                   on Appointment.patient_id = Patient.patient_id
where doctor_id = 1
  and appointment_status = 0
ORDER BY Appointment.date_of_appointment DESC


-- view prescription
select *
from (Prescription left join Recommendations
    on Prescription.prescription_id = Recommendations.prescription_id)
         left join Appointment
                   on Prescription.appointment_id = Appointment.appointment_id
where Prescription.prescription_id = 1

-- check for new entry from DoctorQueue
-- default value is -1
select patient_id, appointment_id
from DoctorQueue
where doctor_id = 1;

/*------------RECEPTIONIST---------*/

-- get the appointment list on a certain date in descending order
select *
from (Appointment left join Doctor
    on Appointment.doctor_id = Doctor.doctor_id)
         left join Patient
                   on Appointment.patient_id = Patient.patient_id
where appointment_status = 0
  and date_of_appointment = '2021-04-01'
ORDER BY Appointment.start_time



-- get the appointment list on a certain date in descending order
-- but search using keyword (patient's name or ID)
select *
from (Appointment left join Doctor
    on Appointment.doctor_id = Doctor.doctor_id)
         left join Patient
                   on Appointment.patient_id = Patient.patient_id
where (
            appointment_status = 0 and date_of_appointment = '2021-04-01')
  and (Patient.patient_id like '%a%' OR patient_name like '%a%'
    )
ORDER BY Appointment.date_of_appointment DESC


select * from
    Appointment left join Patient
                          on Appointment.patient_id = Patient.patient_id
where (doctor_id = 1 and date_of_appointment ='2021-04-04' and appointment_status = 0)


select * from DoctorQueue
select * from Appointment where (doctor_id = 1 and date_of_appointment ='2021-04-04' and appointment_status = 0)


-- get Doctor list with their schedule
select * from
    Doctor left join Schedule
                     on Doctor.doctor_id = Schedule.doctor_id
where (
                  Doctor.doctor_id like '%a%' OR Doctor.doctor_name like '%a%'
          )


-- update queue entry from reception
UPDATE DoctorQueue
SET appointment_id = 3,
    patient_id     = 1
WHERE doctor_id = 1;

select * from Prescription


-- insert into doctor queue if not exists else enter new entry
if exists(SELECT * from DoctorQueue where doctor_id=2)
    begin
        UPDATE DoctorQueue set patient_id= 2 where doctor_id= 1
    end
else
    begin
        insert into DoctorQueue(doctor_id, patient_id, appointment_id)
        values(2,4,6)
    end


--approve appointment
select * from Appointment
UPDATE Appointment set appointment_status= 0 where appointment_id = 1

-- decline appointment
DELETE FROM Appointment WHERE appointment_id = 1;


select * from Patient