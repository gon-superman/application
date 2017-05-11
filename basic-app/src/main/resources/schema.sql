insert into sys_user(username,password,enabled) SELECT * FROM (SELECT 'admin', '$10$nVTw6oWmDzsboYTQYNoztOTyOHScISPxu/59IGLnTHeLeOBWyzZaW',1) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM sys_user WHERE username = 'admin'
) ;

insert into sys_role(name) SELECT * FROM (SELECT 'ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM sys_role WHERE name = 'ADMIN'
) ;
commit;