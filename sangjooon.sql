create table mydb.invoice_db (
	call_number			varchar(15),  
	call_date			varchar(12),  
	call_start			varchar(12),  
	call_terminating 	varchar(15),
	area_name			varchar(18),
	call_time			varchar(12),
	extension_division	varchar(9),
	call_kind			varchar(12),
	call_account		varchar(10)
);

truncate mydb.invoice_db;
desc invoice_db;
select count(*) from invoice_db;

create table mydb.rate_list(
	sb_rate		double,
	sob_rate	double,
	phone_rate	double	
);

insert mydb.rate_list values(1.9,2.1,12.5);


drop table mydb.rate_list;

select * from mydb.rate_list;


---------------------------------------------------------------------------------
drop table mydb.invoice_basics;


create table mydb.invoice_basics( 
	details		varchar(30),
	unit		int,
	rate		int,
	kind		int,
	remarks		varchar(30)
);

insert mydb.invoice_basics (details,unit,rate,kind,remarks) 
values('光D コーロケーション',7,2000,0,'null');
insert mydb.invoice_basics  (details,unit,rate,kind,remarks) 
values('光D ダイヤルイン番号利用料',1,100,0,'null');
insert mydb.invoice_basics (details,unit,rate,kind,remarks) 
values('ユニバーサルサービス料',7,3,0,'null');
insert mydb.invoice_basics (details,unit,rate,kind,remarks) 
values('光D L3SWレンタル料',7,32200,1,'null');
insert mydb.invoice_basics (details,unit,rate,kind,remarks)  
values('光D コーロケーション',7,2000,1,'null');
insert mydb.invoice_basics (details,unit,rate,kind,remarks) 
values('光D ダイヤルイン番号利用料',1,100,1,'null');
insert mydb.invoice_basics (details,unit,rate,kind,remarks) 
values('光D ダイヤルイン代表組',2,20000,1,'null');
insert mydb.invoice_basics (details,unit,rate,kind,remarks) 
values('ユニバーサルサービス料',7,3,1,'null');

select * from mydb.invoice_basics;
--------------------------------------------------------------
create table mydb.call_duration (
	call_number			varchar(15) not null,
	call_account		varchar(8) not null,
	call_price    		varchar(20) not null,
	call_date			varchar(15) not null
);


