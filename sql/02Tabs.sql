create table Refs(
  Dom varchar2(25) not null,
  Key number(6) not null,
  Wert varchar2(500)
);

create table Kuben(
  Id number(4) not null,
  Orga number(2) not null,
  Name varchar2(25) not null,
  Verzeichnis varchar2(300),
  Kommentar varchar2(1000)
);

create table DataDef(
  Id number(4) not null,
  Kubus number(4) not null,
  Name varchar2(25) not null,
  Art number(2) not null,
  TabName varchar2(40),
  DateiName varchar2(300)
);

create table Felder(
  DDId number(4) not null,
  Folge number(4) not null,
  Name varchar2(25) not null,
  Art number(2) not null,
  Kommentar varchar2(1000)
);
  
create table Data10(
  DDId number(4) not null,
  Folge number(9) not null,
  W0 varchar2(100),
  W1 varchar2(100),
  W2 varchar2(100),
  W3 varchar2(100),
  W4 varchar2(100),
  W5 varchar2(100),
  W6 varchar2(100),
  W7 varchar2(100),
  W8 varchar2(100),
  W9 varchar2(100)
) tablespace bigdw;

create table Data32(
  DDId number(4) not null,
  Folge number(9) not null,
  W00 varchar2(100),
  W01 varchar2(100),
  W02 varchar2(100),
  W03 varchar2(100),
  W04 varchar2(100),
  W05 varchar2(100),
  W06 varchar2(100),
  W07 varchar2(100),
  W08 varchar2(100),
  W09 varchar2(100),
  W10 varchar2(100),
  W11 varchar2(100),
  W12 varchar2(100),
  W13 varchar2(100),
  W14 varchar2(100),
  W15 varchar2(100),
  W16 varchar2(100),
  W17 varchar2(100),
  W18 varchar2(100),
  W19 varchar2(100),
  W20 varchar2(100),
  W21 varchar2(100),
  W22 varchar2(100),
  W23 varchar2(100),
  W24 varchar2(100),
  W25 varchar2(100),
  W26 varchar2(100),
  W27 varchar2(100),
  W28 varchar2(100),
  W29 varchar2(100),
  W30 varchar2(100),
  W31 varchar2(100)  
) tablespace bigdw;
create index Data32_idx1 on Data32(DdId)
 tablespace bigdw;

create table LandTab(
  Id number(6) not null,
  Elter number(6),
  Art number(2) not null, -- Dom LandTabArten
  NameDeu varchar2(100),
  NameEng varchar2(100),
  NameFra varchar2(100),
  NameSpa varchar2(100),
  Iso2 varchar2(5),
  Iso3 varchar2(5),
  IsoNum number(6),
  InfCode varchar2(10),
  InfBeschreibung varchar2(100),
  StatKng varchar2(4),
  Intra varchar2(8),
  IdlKey varchar2(25)  
);

create table Kurse(
  Waeh number(2) not null,
  Datum date not null,
  Kurs float not null
);
create unique index Kurse_pk_prim on Kurse(Waeh,Datum);

create table HistKurse(
  Waeh number(2) not null,
  Von date not null,
  Bis date,
  Kurs float not null
);
create unique index HistKurse_pk_prim on Kurse(Waeh,Von);
