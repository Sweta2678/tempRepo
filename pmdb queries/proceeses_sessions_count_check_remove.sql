select resource_name, current_utilization, max_utilization from v$resource_limit where resource_name in ('processes', 'sessions');

SELECT 'ALTER SYSTEM KILL SESSION '''||sid||','||serial#||''' IMMEDIATE;' FROM v$session where status='INACTIVE';

SELECT sid, serial#, status, username FROM v$session where status='INACTIVE';

select * from  v$session;

SELECT a.username,a.status, a.osuser,a.logon_time, a.program, b.spid,b.pga_used_mem/1024/1024 "PGA USED (MB)", a.sid, a.serial# ,a.module,a.logon_time,a.terminal FROM v$session a, v$process b WHERE a.paddr = b.addr  order by b.pga_used_mem desc;

select * from v$resource_limit;

select * from v$process;

SELECT a.username,a.status, a.osuser,a.logon_time, a.program, b.spid,b.pga_used_mem/1024/1024 "PGA USED (MB)", a.sid, a.serial# ,a.module,a.logon_time,a.terminal FROM v$session a, v$process b WHERE a.paddr = b.addr and a.logon_time > '25-05-2022' order by b.pga_used_mem desc;

SELECT a.username, a.osuser,a.logon_time, a.program, b.spid,b.pga_used_mem/1024/1024 "PGA USED (MB)", a.sid, a.serial# ,a.module,a.logon_time,a.terminal FROM v$session a, v$process b WHERE a.paddr = b.addr and a.logon_time > '25-05-2022' order by b.pga_used_mem desc;

