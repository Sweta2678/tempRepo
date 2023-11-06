SET SERVEROUTPUT ON;
DECLARE
    TYPE ARRAY
      IS TABLE OF random_data3%ROWTYPE;
      
    l_data ARRAY;
    CURSOR cu_cursor IS
      SELECT *
      FROM   random_data3
      WHERE  CREATED_DATE <= Add_months(SYSDATE, -1);
BEGIN
    dbms_output.Put_line('init..');

    OPEN cu_cursor;

    LOOP
        FETCH cu_cursor bulk collect INTO l_data limit 100000;

        forall i IN 1 .. l_data.count
          INSERT INTO random_data4
          VALUES L_data(i);
          commit;
          dbms_output.Put_line('init..');
        exit WHEN cu_cursor%NOTFOUND;   
    END LOOP;
    
    CLOSE cu_cursor;
END;

select count(*) from RANDOM_DATA4;

delete from RANDOM_DATA4;