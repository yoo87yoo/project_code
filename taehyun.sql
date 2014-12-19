/*invoice_db table*/
create table mydb.invoice_db (
	call_number			varchar(15),  
	call_date			varchar(12),  
	call_start			varchar(12),  
	call_terminating 	varchar(15),
	area_name			varchar(18),
	call_time			varchar(12),
	extension_division	varchar(9),
	call_kind			varchar(12),
	call_account		varchar(8)
);


/*국내 청구서 발행*/
select call_number as telephone, ceil(sum(f.a)/60) as price 
from (select call_number,case  when substring(call_time,-1,1) = '0'  then time_to_sec(call_time)  else time_to_sec(call_time)+1 end a , call_kind from mydb.invoice_db) f 
where call_kind='ＩＰ間呼' or call_kind='国内呼' 
group by f.call_number;


/*국제 청구서 발행*/
select call_number as telephone ,sum(call_account) as price
					from mydb.invoice_db
					where call_kind='国際呼'
					group by call_number;
					
/*무료 이력 발행*/
select call_number as telephone ,call_kind,count(*) as call_count, sum(f.a) as price
from
select call_number, case
when substring(call_time,-1,1) = '0'
then time_to_sec(call_time)
else time_to_sec(call_time)+1
end a
, call_kind,call_account from mydb.invoice_db) f
group by f.call_number,call_kind,f.call_account
having (f.call_account=0)


/*db에 저장*/
insert into mydb.sb_save values (?,?,date_format(sysdate(),'%Y/%m/%d')
<<<<<<< .mine
/*사이버패스*/



drop table mydb.sb_save;
drop table mydb.sob_save;
drop table mydb.sob_save;


create table mydb.sb_save (
	call_number			varchar(40) not null,  
	price    			varchar(20) not null,
	sb_date				varchar(15) not null
);
/*소프트베이*/
create table mydb.sob_save (
	call_number			varchar(40) not null,  
	price    			varchar(20) not null,
	sob_date			varchar(15) not null
=======

create table mydb.call_duration (
	call_number			varchar(15) not null,
	call_account		varchar(8) not null,
	call_price    		varchar(20) not null,
	call_date			varchar(15) not null
);

drop table mydb.call_duration;


<<<<<<< .mine
/*국제*/
create table mydb.international_save (
	call_number			varchar(40) not null,  
	price    			varchar(20) not null,
	international_date	varchar(15) not null
);

create table mydb.phone_save (
	call_number			varchar(40) not null,  
	price    			varchar(20) not null,
	phone_date			varchar(15) not null
);=======

>>>>>>> .r52
