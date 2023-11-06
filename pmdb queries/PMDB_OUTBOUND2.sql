 DECLARE
    TYPE ARRAY
      IS TABLE OF RANDOM_DATA4%ROWTYPE;
      
    l_data ARRAY;
    CURSOR cu_cursor IS
      SELECT *
      FROM   RANDOM_DATA4
      WHERE  object_trandate <= Add_months(SYSDATE, -3);
BEGIN
  start_time := dbms_utility.get_time ();
    dbms_output.Put_line('init..');

    OPEN cu_cursor;

    LOOP
        FETCH cu_cursor bulk collect INTO l_data limit 10;

        forall i IN 1 .. l_data.count
          INSERT INTO RANDOM_DATA5
          VALUES L_data(i);
          dbms_output.Put_line('init..');
          
       
        exit WHEN cu_cursor%NOTFOUND;   
    END LOOP;
    
      dbms_output.put_line ( 'Single-row duration = ' || ( dbms_utility.get_time () - start_time) );

    
    CLOSE cu_cursor;
END;  