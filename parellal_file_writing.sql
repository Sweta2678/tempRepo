CREATE FUNCTION parallel_dump (
                p_source    IN SYS_REFCURSOR,
                p_filename  IN VARCHAR2,
                p_directory IN VARCHAR2
                ) RETURN dump_ntt
                  PIPELINED
                  PARALLEL_ENABLE (PARTITION p_source BY ANY) AS

   TYPE row_ntt IS TABLE OF VARCHAR2(32767);
   v_rows    row_ntt;
   v_file    UTL_FILE.FILE_TYPE;
   v_buffer  VARCHAR2(32767);
   v_sid     NUMBER;
   v_name    VARCHAR2(128);
   v_lines   PLS_INTEGER := 0;
   c_eol     CONSTANT VARCHAR2(1) := CHR(10);
   c_eollen  CONSTANT PLS_INTEGER := LENGTH(c_eol);
   c_maxline CONSTANT PLS_INTEGER := 32767;

BEGIN

   SELECT sid into v_sid FROM v$mystat WHERE ROWNUM = 1;
   v_name := p_filename || '_' || TO_CHAR(v_sid) || '.txt';
   v_file := UTL_FILE.FOPEN(p_directory, v_name, 'w', 32767);

   LOOP
     FETCH p_source BULK COLLECT INTO v_rows LIMIT 100;

      FOR i IN 1 .. v_rows.COUNT LOOP

         IF LENGTH(v_buffer) + c_eollen + LENGTH(v_rows(i)) <= c_maxline THEN
            v_buffer := v_buffer || c_eol || v_rows(i);
         ELSE
            IF v_buffer IS NOT NULL THEN
               UTL_FILE.PUT_LINE(v_file, v_buffer);
            END IF;
            v_buffer := v_rows(i);
         END IF;

      END LOOP;

      v_lines := v_lines + v_rows.COUNT;

      EXIT WHEN p_source%NOTFOUND;
   END LOOP;
   CLOSE p_source;

   UTL_FILE.PUT_LINE(v_file, v_buffer);
   UTL_FILE.FCLOSE(v_file);

   PIPE ROW (dump_ot(v_name, v_lines, v_sid));
   RETURN;

END parallel_dump;

SELECT *
FROM   TABLE(
          parallel_dump(
             CURSOR(SELECT /*+ PARALLEL(s,4) */
                           uniqueid ||','||
                           productname ||','||
                           stylenumber AS csv
                    FROM   PRODUCTMASTER s),
             'utl_file_parallel_pipelined_demo',
             'EXTRACT_DIR'
             )) nt;
             
             
             
             
             
             
             
             
             
             
             
             
             
             
SELECT value(e).getClobVal() AS "xmltype"
FROM TABLE(XMLSequence(Cursor(             
SELECT *
FROM   TABLE(
          parallel_dump(
             CURSOR(SELECT /*+ PARALLEL(s,4) */
                           uuid ||','||
                           season ||','||
                           status AS csv
                    FROM   season_gate s),
             'utl_file_parallel_pipelined_demo',
             'EXTRACT_DIR'
             )) nt  ))) e ;             

             
             
SELECT value(e).getClobVal() AS "xmltype"
FROM TABLE(XMLSequence(Cursor(SELECT * FROM PRODUCTMASTER where uniqueid='235912459'))) e;              