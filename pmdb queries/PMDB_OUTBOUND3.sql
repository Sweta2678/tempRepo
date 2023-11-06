SELECT a.username, a.osuser,a.logon_time, a.program, b.spid,b.pga_used_mem/1024/1024 "PGA USED (MB)", a.sid, a.serial# ,a.module,a.logon_time,a.terminal FROM v$session a, v$process b WHERE a.paddr = b.addr and a.logon_time > '25-05-2022' order by b.pga_used_mem desc;

