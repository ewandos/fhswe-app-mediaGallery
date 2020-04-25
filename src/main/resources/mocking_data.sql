drop table if exists PICTURE_TAG;
drop table if exists TAG;
drop table if exists PICTURE;
drop table if exists PHOTOGRAPHER;
drop table if exists IPTC;
drop table if exists EXIF;

create table PICTURE (
    ID integer primary key auto_increment,
    FILENAME varchar2 not null unique,
    PHOTOGRAPHER_ID integer,
    EXIF_CAMERA varchar2,
    EXIF_DATE datetime,
    EXIF_ISO integer,
    EXIF_APERTURE float,
    IPTC_DESCRIPTION varchar2,
    IPTC_RATING integer
);

create table PHOTOGRAPHER (
    ID integer primary key auto_increment,
    FORENAME varchar2,
    NAME varchar2,
    BIRTH date,
    NOTE varchar2
);

create table TAG (
    ID integer primary key auto_increment,
    description varchar2
);

create table PICTURE_TAG (
    PICTURE_ID integer,
    TAG_ID integer
);

alter table PICTURE
    add foreign key (PHOTOGRAPHER_ID) references PHOTOGRAPHER(ID)
        on delete cascade;

alter table PICTURE_TAG
    add foreign key (PICTURE_ID) references PICTURE(ID)
        on delete cascade;

alter table PICTURE_TAG
    add foreign key (TAG_ID) references TAG(ID)
        on delete cascade;

insert into PHOTOGRAPHER (FORENAME, NAME, BIRTH, NOTE)
VALUES ( 'Peter', 'Lustig', '1995-02-15', 'Ein Meister seiner Kunst');

// Alles vorhanden
insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING)
VALUES ( 'maus.jpg', 1, 'iPhone 11 Pro', '2020-01-15', 1400, 2.5, 'Eine Maus die sitzt', 3);

// Fotograf fehlt
insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING)
VALUES ( 'hund.jpg', null, 'Sony Alpha', '2020-01-02', 800, 2.5, 'Ein sehr niedlicher Hund', 4 );

// IPTC fehlt
insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING)
VALUES ( 'katze.jpg', 1, 'Canon EOS 550D', '2020-01-12', 1200, 1.5, null, null );

// EXIF fehlt
insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING)
VALUES ( 'haus.jpg', 1, null, null, null, null, 'Ein grosses Haus', 4 );

// Alles fehlt
insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING)
VALUES ( 'garten.jpg', null, null, null, null, null, null, null);