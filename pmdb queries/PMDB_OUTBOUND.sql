SET SERVEROUTPUT ON

BEGIN
 Dbms_Output.Put_Line(Systimestamp);
END;


BEGIN
  SELECT * FROM DBINPUT_EVENTS;
END;

DECLARE
  v_full_name VARCHAR2(500);
BEGIN
  SELECT FIRST_NAME ||' '|| LAST_NAME 
  INTO v_full_name 
  FROM students 
  WHERE STUDENT_ID=1;
  DBMS_OUTPUT.put_line('v_full_name: '||v_full_name);
END;


