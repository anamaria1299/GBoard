---tables
CREATE TABLE "users" (
   id int  NOT NULL,
   name varchar(20)  NOT NULL,
   lastName varchar(20)  NOT NULL,
   nickName varchar(20)  NOT NULL,
   password varchar(20)  NOT NULL,
   initialDate date  NOT NULL,
   CONSTRAINT user_pk PRIMARY KEY (id)
);

ALTER TABLE public.users ADD gender varchar(20) NULL;
ALTER TABLE public.users ADD webpage varchar(50) NULL;
ALTER TABLE public.users ADD email varchar(30) NULL;
ALTER TABLE public.users ADD country varchar(20) NULL;
ALTER TABLE public.users ADD profile varchar(700) NULL;
ALTER TABLE public.users ALTER COLUMN country TYPE varchar(100) USING country::varchar;
ALTER TABLE public.users ALTER COLUMN email TYPE varchar(100) USING email::varchar;
ALTER TABLE public.users ALTER COLUMN webpage TYPE varchar(100) USING webpage::varchar;
ALTER TABLE public.users ALTER COLUMN gender TYPE varchar(100) USING gender::varchar;



CREATE TABLE user_room (
   userId int  NOT NULL,
   roomId int  NOT NULL,
   CONSTRAINT user_room_pk PRIMARY KEY (userId,roomId)
);
CREATE TABLE room (
   id int  NOT NULL,
   title varchar(20)  NOT NULL,
   type int  NOT NULL,
   owner int  NOT NULL,
   creationDate date  NOT NULL,
   password varchar(20)  NOT NULL,
   CONSTRAINT room_pk PRIMARY KEY (id)
);
CREATE TABLE roomType (
   id int  NOT NULL,
   roomType varchar(20)  NOT NULL,
   CONSTRAINT roomType_pk PRIMARY KEY (id)
);
CREATE TABLE room_tag (
   roomId int  NOT NULL,
   tagId int  NOT NULL,
   CONSTRAINT room_tag_pk PRIMARY KEY (roomId,tagId)
);
CREATE TABLE tag (
   id int  NOT NULL,
   tag varchar(20)  NOT NULL,
   CONSTRAINT tag_pk PRIMARY KEY (id)
);

---FK
ALTER TABLE user_room ADD CONSTRAINT room_user_members
   FOREIGN KEY (roomId)
   REFERENCES "user" (id)  
   NOT DEFERRABLE 
   INITIALLY IMMEDIATE
;
ALTER TABLE user_room ADD CONSTRAINT user_room_members
   FOREIGN KEY (userId)
   REFERENCES room (id)  
   NOT DEFERRABLE 
   INITIALLY IMMEDIATE
;
ALTER TABLE room ADD CONSTRAINT owner_room
   FOREIGN KEY (owner)
   REFERENCES "user" (id)  
   NOT DEFERRABLE 
   INITIALLY IMMEDIATE
;
ALTER TABLE room ADD CONSTRAINT room_roomType
   FOREIGN KEY (type)
   REFERENCES roomType (id)  
   NOT DEFERRABLE 
   INITIALLY IMMEDIATE
;
ALTER TABLE room_tag ADD CONSTRAINT room_tag_fk
   FOREIGN KEY (roomId)
   REFERENCES room (id)  
   NOT DEFERRABLE 
   INITIALLY IMMEDIATE
;
ALTER TABLE room_tag ADD CONSTRAINT tag_room_fk
   FOREIGN KEY (tagId)
   REFERENCES tag (id)  
   NOT DEFERRABLE 
   INITIALLY IMMEDIATE
;

ALTER TABLE public.users ADD CONSTRAINT users_un UNIQUE (nickname);
ALTER TABLE public.users ADD lastdate date NULL;


--insert users
INSERT INTO "users" VALUES (1,'Ana','Rincon','anamaria1299','ana123',now());
INSERT INTO "users" VALUES (2,'Santiago','Rocha','srd98','santiago123',now());
INSERT INTO "users" VALUES (3,'Yohanna','Toro','yowis','yohanna123',now());
INSERT INTO "users" VALUES (4,'John','Ibanez','CrkJohn','john123',now());
INSERT INTO "users" VALUES (5,'Diego','Cardenas','diegocar','diego123',now());
INSERT INTO "users" VALUES (6,'Javier','Vargas','jajaja','javier123',now());
INSERT INTO "users" VALUES (7,'Sebastian','Goenaga','consteno','sebastian123',now());
INSERT INTO "users" VALUES (1,'Camilo','Velandia','Vlan','camilo123',now());
