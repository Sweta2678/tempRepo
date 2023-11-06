DECLARE
   
   v_file    UTL_FILE.FILE_TYPE;
   v_buffer  VARCHAR2(32767);
   v_name    VARCHAR2(128) := 'utl_file_buffered.txt';
   v_lines   PLS_INTEGER := 0;
   c_eol     CONSTANT VARCHAR2(1) := CHR(10);
   c_eollen  CONSTANT PLS_INTEGER := LENGTH(c_eol);
   c_maxline CONSTANT PLS_INTEGER := 32767;

BEGIN

   v_file := UTL_FILE.FOPEN('EXTRACT_DIR',v_name,'W',32767);

   FOR r IN (SELECT uniqueid || ',' || PRODUCTNAME || ',' || STYLENUMBER AS csv
             FROM  productmaster )
   LOOP

      IF LENGTH(v_buffer) + c_eollen + LENGTH(r.csv) <= c_maxline THEN
         v_buffer := v_buffer || c_eol || r.csv;
      ELSE
         IF v_buffer IS NOT NULL THEN
            UTL_FILE.PUT_LINE(v_file, v_buffer);
         END IF;
         v_buffer := r.csv;
      END IF;

      v_lines := v_lines + 1;

   END LOOP;

   UTL_FILE.PUT_LINE(v_file, v_buffer);
   UTL_FILE.FCLOSE(v_file);

   DBMS_OUTPUT.PUT_LINE('File='||v_name||'; Lines='||v_lines);

END;