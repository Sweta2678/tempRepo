declare

BEGIN
DBMS_OUTPUT.PUT_LINE('init..');
 FOR x IN (SELECT event_id,
            object_key,
           object_verb,
           object_name,
           object_trans,
           object_trandate
                 FROM dbinput_events
                 where rownum<20000
             order by 1)
   LOOP
         DBMS_OUTPUT.PUT_LINE(x.event_id);
      DBMS_OUTPUT.PUT_LINE(x.object_key);
      DBMS_OUTPUT.PUT(x.object_trandate);
   END LOOP;
END;

select * from RANDOM_DATA1 ORDER BY created_date desc;

delete from DBINPUT_ARCHIVE;

CURSOR db_dbinput IS 
   SELECT * FROM DBINPUT_EVENTS; 