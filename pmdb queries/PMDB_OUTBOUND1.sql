create table temp nologging as select * from t where id not in ( select id from a );

(keep the rows you want)

index temp (unrecoverable, in parrallel )
grant on temp (as you had for t)
drop table t;
rename temp to t;


create table temp nologging as select * from RANDOM_DATA3 where id not in ( select id from RANDOM_DATA4);

(keep the rows you want)

index temp; (unrecoverable, in parrallel )
grant all on temp; --(as you had for t)
drop table RANDOM_DATA3;
rename temp to random_data3;