with a as (select sizelist from SIZESCALEMASTER where uniqueid='20170810')
select
 case
  when instr(sizelist,'7') > 0 then 'exists'
 else
 'does not exist'
end as result
from a ;
