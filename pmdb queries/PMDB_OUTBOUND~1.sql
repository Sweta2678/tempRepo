create or replace procedure arch_t is
begin
  insert into t_hist
    select * from t
    where  up_dt <= add_months(sysdate, -12);
    
  delete t
  where  up_dt <= add_months(sysdate, -12);
end arch_t;


create or replace PROCEDURE demo_proc (p_array_size IN PLS_INTEGER DEFAULT 10000)
 IS
 TYPE ARRAY IS TABLE OF DBINPUT_EVENTS%ROWTYPE;
 l_data ARRAY;
 CURSOR c IS SELECT * FROM DBINPUT_EVENTS;
 BEGIN
   OPEN c;
   LOOP
   FETCH c BULK COLLECT INTO l_data LIMIT p_array_size;
   FORALL i IN 1..l_data.COUNT
   INSERT INTO DBINPUT_ARCHIVE VALUES l_data(i);
   commit;
   EXIT WHEN c%NOTFOUND;
   END LOOP;
   CLOSE c;
 END;