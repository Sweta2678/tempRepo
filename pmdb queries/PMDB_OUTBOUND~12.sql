declare
  start_time   pls_integer;
  insert_count pls_integer := 50000;
begin
  start_time := dbms_utility.get_time ();
    
  for i in 1 .. insert_count loop
       INSERT INTO RANDOM_DATA5
          select * from RANDOM_DATA3  WHERE CREATED_DATE <= Add_months(SYSDATE, -1) connect by level <= insert_count;
  end loop;
  
  dbms_output.put_line ( 'Single-row duration = ' || ( dbms_utility.get_time () - start_time) );
  
  --rollback;
  
  --start_time := dbms_utility.get_time ();
  
  --insert into bricks
    --select level, 'red', 'cube' from dual
    --connect by level <= insert_count;
  
  --dbms_output.put_line ( 'Multi-row duration = ' || ( dbms_utility.get_time () - start_time) );
  
  rollback;
end;