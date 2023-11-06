--INSERT INTO PMDB_OUTBOUND.dbinput_events(OBJECT_KEY, OBJECT_NAME, OBJECT_VERB, OBJECT_TRANDATE,OBJECT_DATEREC,SYSTEM_,OBJECT_BRAND,OBJECT_PARAMS)
select sku.uniqueid as OBJECT_KEY,
	   'LCSSKU' as OBJECT_NAME,
	   'Drop' as OBJECT_VERB, 
	   to_timestamp(sysdate) as OBJECT_TRANDATE,
	   to_timestamp(sysdate) as OBJECT_DATEREC,
	   'JOOR' as SYSTEM_ ,
        CASE UPPER(sku.FACTORYSTORETYPESKU)
            WHEN UPPER('noFactory') THEN 'KS-Retail' 
            WHEN UPPER('factoryExclusive') THEN 'KS-Outlet'
            ELSE 'KS'
        END as OBJECT_BRAND,
	   CASE  WHEN product.stylenumber is Not NULL and sku.nrf is not null then stylenumber|| '/'|| stylenumber || '/'|| sku.nrf ELSE NULL END as OBJECT_PARAM
	   from SKUmaster sku INNER JOIN (select uniqueid,stylenumber from productmaster
						where (UPPER(RETAILFASHIONSEASON) like '%HOLIDAY2021%' OR UPPER(OUTLETFASHIONSEASON) like '%HOLIDAY2021%')
						and	  brand = 'KS'
						and   department in (select DISTINCT department from season_gate where status = 1 and UPPER(season) like '%HOLIDAY 2021%')) PRODUCT 
                        ON  sku.styleuniqueid = product.uniqueid
and  UPPER(sku.status) in ('DROPPED')
and sku.nrf is not null
and  (UPPER(sku.RETAILFASHIONSEASON) like '%HOLIDAY2021%' OR UPPER(SKU.OUTLETFASHIONSEASON) like '%HOLIDAY2021%');