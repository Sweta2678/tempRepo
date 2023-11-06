DECLARE
  --event_id_d        NUMBER(8, 2);
  object_key_d      VARCHAR2(80);
  object_verb_d     VARCHAR2(40);
  object_name_d     VARCHAR2(65);
  object_trans_d    VARCHAR2(1);
  object_trandate_d TIMESTAMP;
  --object_msg_d      VARCHAR2(400);
  --object_daterec_d  TIMESTAMP;
  --object_retrycnt_d VARCHAR2(1);
  --system_d          VARCHAR2(100);
  --object_brand_d    VARCHAR2(40);
  --object_params_d   VARCHAR2(100);
BEGIN
    SELECT 
    --event_id,
           object_key,
           object_verb,
           object_name,
           object_trans,
           object_trandate
           --object_msg,
           --object_daterec,
           --object_retrycnt,
           --system_,
           --object_brand,
           --object_params
    INTO object_key_d, object_verb_d, object_name_d,
    object_trans_d, object_trandate_d--, object_msg_d, object_daterec_d,
    --object_retrycnt_d, system_d, object_brand_d, object_params_d
    FROM   dbinput_events
    WHERE  object_trandate <= Add_months(SYSDATE, -6);
END;  


