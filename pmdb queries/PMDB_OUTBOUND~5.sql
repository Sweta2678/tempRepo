CREATE TABLE random_data3 (
  id           NUMBER,
  small_number NUMBER(5),
  big_number   NUMBER,
  short_string VARCHAR2(50),
  long_string  VARCHAR2(400),
  created_date DATE,
  --CONSTRAINT random_data_pk1 PRIMARY KEY (id)
);

INSERT /*+ APPEND */ INTO random_data3
SELECT level AS id,
       TRUNC(DBMS_RANDOM.value(1,5)) AS small_number,
       TRUNC(DBMS_RANDOM.value(10,100)) AS big_number,
       DBMS_RANDOM.string('L',TRUNC(DBMS_RANDOM.value(5,10))) AS short_string,
       DBMS_RANDOM.string('L',TRUNC(DBMS_RANDOM.value(10,40))) AS long_string,
       TRUNC(SYSDATE - DBMS_RANDOM.value(0,366)) AS created_date
FROM   dual
CONNECT BY level <= 3000000;
COMMIT;


CREATE TABLE random_data5 (
  id           NUMBER,
  small_number NUMBER(5),
  big_number   NUMBER,
  short_string VARCHAR2(50),
  long_string  VARCHAR2(400),
  created_date DATE
);