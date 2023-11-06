 DECLARE
    TYPE ARRAY
      IS TABLE OF dbinput_events%ROWTYPE;
      
    l_data ARRAY;
    CURSOR cu_cursor IS
      SELECT *
      FROM   dbinput_events
      WHERE  object_trandate <= Add_months(SYSDATE, -1);
BEGIN
    dbms_output.Put_line('init..');

    OPEN cu_cursor;

    LOOP
        FETCH cu_cursor bulk collect INTO l_data limit 10;

        forall i IN 1 .. l_data.count
          INSERT INTO dbinput_archive
          VALUES L_data(i);
          dbms_output.Put_line('init..');
       
        exit WHEN cu_cursor%NOTFOUND;   
    END LOOP;
    
    CLOSE cu_cursor;
END;  