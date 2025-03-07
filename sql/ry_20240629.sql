SET NAMES utf8mb4;

-- ----------------------------
-- 1、Department Table
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id           bigint(20)      not null auto_increment    comment 'Department ID',
  parent_id         bigint(20)      default 0                  comment 'Parent Department ID',
  ancestors         varchar(50)     default ''                 comment 'Ancestor List',
  dept_name         varchar(30)     default ''                 comment 'Department Name',
  order_num         int(4)          default 0                  comment 'Display Order',
  leader            varchar(20)     default null               comment 'Leader',
  phone             varchar(11)     default null               comment 'Phone Number',
  email             varchar(50)     default null               comment 'Email',
  status            char(1)         default '0'                comment 'Department Status (0 Normal 1 Disabled)',
  del_flag          char(1)         default '0'                comment 'Deletion Flag (0 Exists 2 Deleted)',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time 	    datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  primary key (dept_id)
) engine=innodb auto_increment=200 comment = 'Department Table';

-- ----------------------------
-- Initialize - Department Table Data
-- ----------------------------
insert into sys_dept values(100,  0,   '0',          'RuoYi Technology',   0, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(101,  100, '0,100',      'Shenzhen Headquarters', 1, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(102,  100, '0,100',      'Changsha Branch', 2, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(103,  101, '0,100,101',  'Research Department',   1, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(104,  101, '0,100,101',  'Marketing Department',   2, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(105,  101, '0,100,101',  'Testing Department',   3, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(106,  101, '0,100,101',  'Finance Department',   4, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(107,  101, '0,100,101',  'Operations Department',   5, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(108,  102, '0,100,102',  'Marketing Department',   1, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(109,  102, '0,100,102',  'Finance Department',   2, 'RuoYi', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);


-- ----------------------------
-- 2、User Information Table
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id           bigint(20)      not null auto_increment    comment 'User ID',
  dept_id           bigint(20)      default null               comment 'Department ID',
  user_name         varchar(30)     not null                   comment 'User Account',
  nick_name         varchar(30)     not null                   comment 'User Nickname',
  user_type         varchar(2)      default '00'               comment 'User Type (00 System User)',
  email             varchar(50)     default ''                 comment 'User Email',
  phonenumber       varchar(11)     default ''                 comment 'Phone Number',
  sex               char(1)         default '0'                comment 'User Gender (0 Male 1 Female 2 Unknown)',
  avatar            varchar(100)    default ''                 comment 'Avatar Address',
  password          varchar(100)    default ''                 comment 'Password',
  status            char(1)         default '0'                comment 'Account Status (0 Normal 1 Disabled)',
  del_flag          char(1)         default '0'                comment 'Deletion Flag (0 Exists 2 Deleted)',
  login_ip          varchar(128)    default ''                 comment 'Last Login IP',
  login_date        datetime                                   comment 'Last Login Time',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time       datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  remark            varchar(500)    default null               comment 'Remarks',
  primary key (user_id)
) engine=innodb auto_increment=100 comment = 'User Information Table';

-- ----------------------------
-- Initialize - User Information Table Data
-- ----------------------------
insert into sys_user values(1,  103, 'admin', 'RuoYi', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', null, 'Administrator');
insert into sys_user values(2,  105, 'ry',    'RuoYi', '00', 'ry@qq.com',  '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', null, 'Tester');


-- ----------------------------
-- 3、Post Information Table
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
  post_id       bigint(20)      not null auto_increment    comment 'Post ID',
  post_code     varchar(64)     not null                   comment 'Post Code',
  post_name     varchar(50)     not null                   comment 'Post Name',
  post_sort     int(4)          not null                   comment 'Display Order',
  status        char(1)         not null                   comment 'Status (0 Normal 1 Disabled)',
  create_by     varchar(64)     default ''                 comment 'Creator',
  create_time   datetime                                   comment 'Creation Time',
  update_by     varchar(64)     default ''			       comment 'Updater',
  update_time   datetime                                   comment 'Update Time',
  remark        varchar(500)    default null               comment 'Remarks',
  primary key (post_id)
) engine=innodb comment = 'Post Information Table';

-- ----------------------------
-- Initialize - Post Information Table Data
-- ----------------------------
insert into sys_post values(1, 'ceo',  'Chairman',    1, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(2, 'se',   'Project Manager',  2, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(3, 'hr',   'Human Resources',  3, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(4, 'user', 'General Employee',  4, '0', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 4、Role Information Table
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id              bigint(20)      not null auto_increment    comment 'Role ID',
  role_name            varchar(30)     not null                   comment 'Role Name',
  role_key             varchar(100)    not null                   comment 'Role Permission String',
  role_sort            int(4)          not null                   comment 'Display Order',
  data_scope           char(1)         default '1'                comment 'Data Scope (1: All Data Permissions 2: Custom Data Permissions 3: This Department Data Permissions 4: This Department and Below Data Permissions)',
  menu_check_strictly  tinyint(1)      default 1                  comment 'Whether Menu Tree Selection Items Are Associated Display',
  dept_check_strictly  tinyint(1)      default 1                  comment 'Whether Department Tree Selection Items Are Associated Display',
  status               char(1)         not null                   comment 'Role Status (0 Normal 1 Disabled)',
  del_flag             char(1)         default '0'                comment 'Deletion Flag (0 Exists 2 Deleted)',
  create_by            varchar(64)     default ''                 comment 'Creator',
  create_time          datetime                                   comment 'Creation Time',
  update_by            varchar(64)     default ''                 comment 'Updater',
  update_time          datetime                                   comment 'Update Time',
  remark               varchar(500)    default null               comment 'Remarks',
  primary key (role_id)
) engine=innodb auto_increment=100 comment = 'Role Information Table';

-- ----------------------------
-- Initialize - Role Information Table Data
-- ----------------------------
insert into sys_role values('1', 'Super Administrator',  'admin',  1, 1, 1, 1, '0', '0', 'admin', sysdate(), '', null, 'Super Administrator');
insert into sys_role values('2', 'Common Role',    'common', 2, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, 'Common Role');


-- ----------------------------
-- 5、Menu Permission Table
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment 'Menu ID',
  menu_name         varchar(50)     not null                   comment 'Menu Name',
  parent_id         bigint(20)      default 0                  comment 'Parent Menu ID',
  order_num         int(4)          default 0                  comment 'Display Order',
  path              varchar(200)    default ''                 comment 'Route Path',
  component         varchar(255)    default null               comment 'Component Path',
  query             varchar(255)    default null               comment 'Route Parameters',
  route_name        varchar(50)     default ''                 comment 'Route Name',
  is_frame          int(1)          default 1                  comment 'Whether External Link (0 Yes 1 No)',
  is_cache          int(1)          default 0                  comment 'Whether Cache (0 Cache 1 No Cache)',
  menu_type         char(1)         default ''                 comment 'Menu Type (M Directory C Menu F Button)',
  visible           char(1)         default 0                  comment 'Menu Status (0 Display 1 Hide)',
  status            char(1)         default 0                  comment 'Menu Status (0 Normal 1 Disabled)',
  perms             varchar(100)    default null               comment 'Permission Identifier',
  icon              varchar(100)    default '#'                comment 'Menu Icon',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time       datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  remark            varchar(500)    default ''                 comment 'Remarks',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = 'Menu Permission Table';

-- ----------------------------
-- Initialize - Menu Information Table Data
-- ----------------------------
-- Level 1 Menu
insert into sys_menu values('1', 'System Management', '0', '1', 'system',           null, '', '', 1, 0, 'M', '0', '0', '', 'system',   'admin', sysdate(), '', null, 'System Management Directory');
insert into sys_menu values('2', 'System Monitor', '0', '2', 'monitor',          null, '', '', 1, 0, 'M', '0', '0', '', 'monitor',  'admin', sysdate(), '', null, 'System Monitor Directory');
insert into sys_menu values('3', 'System Tools', '0', '3', 'tool',             null, '', '', 1, 0, 'M', '0', '0', '', 'tool',     'admin', sysdate(), '', null, 'System Tools Directory');
insert into sys_menu values('4', 'RuoYi Official Website', '0', '4', 'http://ruoyi.vip', null, '', '', 0, 0, 'M', '0', '0', '', 'guide',    'admin', sysdate(), '', null, 'RuoYi Official Website URL');
-- Level 2 Menu
insert into sys_menu values('100',  'User Management',       '1',   '1', 'user',       'system/user/index',                 '', '', 1, 0, 'C', '0', '0', 'system:user:list',        'user',          'admin', sysdate(), '', null, 'User Management Menu');
insert into sys_menu values('101',  'Role Management',       '1',   '2', 'role',       'system/role/index',                 '', '', 1, 0, 'C', '0', '0', 'system:role:list',        'peoples',       'admin', sysdate(), '', null, 'Role Management Menu');
insert into sys_menu values('102',  'Menu Management',       '1',   '3', 'menu',       'system/menu/index',                 '', '', 1, 0, 'C', '0', '0', 'system:menu:list',        'tree-table',    'admin', sysdate(), '', null, 'Menu Management Menu');
insert into sys_menu values('103',  'Department Management', '1',   '4', 'dept',       'system/dept/index',                 '', '', 1, 0, 'C', '0', '0', 'system:dept:list',        'tree',          'admin', sysdate(), '', null, 'Department Management Menu');
insert into sys_menu values('104',  'Post Management',       '1',   '5', 'post',       'system/post/index',                 '', '', 1, 0, 'C', '0', '0', 'system:post:list',        'post',          'admin', sysdate(), '', null, 'Post Management Menu');
insert into sys_menu values('105',  'Dictionary Management', '1',   '6', 'dict',       'system/dict/index',                 '', '', 1, 0, 'C', '0', '0', 'system:dict:list',        'dict',          'admin', sysdate(), '', null, 'Dictionary Management Menu');
insert into sys_menu values('106',  'Parameter Settings',    '1',   '7', 'config',     'system/config/index',               '', '', 1, 0, 'C', '0', '0', 'system:config:list',      'edit',          'admin', sysdate(), '', null, 'Parameter Settings Menu');
insert into sys_menu values('107',  'Notice Announcement',   '1',   '8', 'notice',     'system/notice/index',               '', '', 1, 0, 'C', '0', '0', 'system:notice:list',      'message',       'admin', sysdate(), '', null, 'Notice Announcement Menu');
insert into sys_menu values('108',  'Log Management',        '1',   '9', 'log',        '',                                  '', '', 1, 0, 'M', '0', '0', '',                        'log',           'admin', sysdate(), '', null, 'Log Management Menu');
insert into sys_menu values('109',  'Online Users',          '2',   '1', 'online',     'monitor/online/index',              '', '', 1, 0, 'C', '0', '0', 'monitor:online:list',     'online',        'admin', sysdate(), '', null, 'Online Users Menu');
insert into sys_menu values('110',  'Scheduled Tasks',       '2',   '2', 'job',        'monitor/job/index',                 '', '', 1, 0, 'C', '0', '0', 'monitor:job:list',        'job',           'admin', sysdate(), '', null, 'Scheduled Tasks Menu');
insert into sys_menu values('111',  'Sentinel Console',      '2',   '3', 'http://localhost:8718',        '',                '', '', 0, 0, 'C', '0', '0', 'monitor:sentinel:list',   'sentinel',      'admin', sysdate(), '', null, 'Flow Control Menu');
insert into sys_menu values('112',  'Nacos Console',         '2',   '4', 'http://localhost:8848/nacos',  '',                '', '', 0, 0, 'C', '0', '0', 'monitor:nacos:list',      'nacos',         'admin', sysdate(), '', null, 'Service Governance Menu');
insert into sys_menu values('113',  'Admin Console',         '2',   '5', 'http://localhost:9100/login',  '',                '', '', 0, 0, 'C', '0', '0', 'monitor:server:list',     'server',        'admin', sysdate(), '', null, 'Service Monitoring Menu');
insert into sys_menu values('114',  'Form Builder',          '3',   '1', 'build',      'tool/build/index',                  '', '', 1, 0, 'C', '0', '0', 'tool:build:list',         'build',         'admin', sysdate(), '', null, 'Form Builder Menu');
insert into sys_menu values('115',  'Code Generation',       '3',   '2', 'gen',        'tool/gen/index',                    '', '', 1, 0, 'C', '0', '0', 'tool:gen:list',           'code',          'admin', sysdate(), '', null, 'Code Generation Menu');
insert into sys_menu values('116',  'System API',            '3',   '3', 'http://localhost:8080/swagger-ui/index.html', '', '', '', 0, 0, 'C', '0', '0', 'tool:swagger:list',       'swagger',       'admin', sysdate(), '', null, 'System API Menu');
-- Level 3 Menu
insert into sys_menu values('500',  'Operation Log', '108', '1', 'operlog',    'system/operlog/index',    '', '', 1, 0, 'C', '0', '0', 'system:operlog:list',    'form',          'admin', sysdate(), '', null, 'Operation Log Menu');
insert into sys_menu values('501',  'Login Log', '108', '2', 'logininfor', 'system/logininfor/index', '', '', 1, 0, 'C', '0', '0', 'system:logininfor:list', 'logininfor',    'admin', sysdate(), '', null, 'Login Log Menu');
-- User Management Buttons
insert into sys_menu values('1000', 'User Query', '100', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1001', 'User Add', '100', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1002', 'User Edit', '100', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1003', 'User Delete', '100', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1004', 'User Export', '100', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1005', 'User Import', '100', '6',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1006', 'Reset Password', '100', '7',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd',       '#', 'admin', sysdate(), '', null, '');
-- Role Management Buttons
insert into sys_menu values('1007', 'Role Query', '101', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1008', 'Role Add', '101', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1009', 'Role Edit', '101', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1010', 'Role Delete', '101', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1011', 'Role Export', '101', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export',         '#', 'admin', sysdate(), '', null, '');
-- Menu Management Buttons
insert into sys_menu values('1012', 'Menu Query', '102', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1013', 'Menu Add', '102', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1014', 'Menu Edit', '102', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1015', 'Menu Delete', '102', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove',         '#', 'admin', sysdate(), '', null, '');
-- Department Management Buttons
insert into sys_menu values('1016', 'Department Query', '103', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1017', 'Department Add', '103', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1018', 'Department Edit', '103', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1019', 'Department Delete', '103', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove',         '#', 'admin', sysdate(), '', null, '');
-- Post Management Buttons
insert into sys_menu values('1020', 'Post Query', '104', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1021', 'Post Add', '104', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1022', 'Post Edit', '104', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1023', 'Post Delete', '104', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1024', 'Post Export', '104', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export',         '#', 'admin', sysdate(), '', null, '');
-- Dictionary Management Buttons
insert into sys_menu values('1025', 'Dictionary Query', '105', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1026', 'Dictionary Add', '105', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1027', 'Dictionary Edit', '105', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1028', 'Dictionary Delete', '105', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1029', 'Dictionary Export', '105', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:export',         '#', 'admin', sysdate(), '', null, '');
-- Parameter Settings Buttons
insert into sys_menu values('1030', 'Parameter Query', '106', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1031', 'Parameter Add', '106', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1032', 'Parameter Edit', '106', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1033', 'Parameter Delete', '106', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1034', 'Parameter Export', '106', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:export',       '#', 'admin', sysdate(), '', null, '');
-- Notice Announcement Buttons
insert into sys_menu values('1035', 'Notice Query', '107', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1036', 'Notice Add', '107', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1037', 'Notice Edit', '107', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1038', 'Notice Delete', '107', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove',       '#', 'admin', sysdate(), '', null, '');
-- Operation Log
insert into sys_menu values('1039', 'Operation Query', '500', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:operlog:query',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1040', 'Operation Delete', '500', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:operlog:remove',      '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1041', 'Log Export', '500', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:operlog:export',      '#', 'admin', sysdate(), '', null, '');
-- Login Log Buttons
insert into sys_menu values('1042', 'Login Query', '501', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1043', 'Login Delete', '501', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1044', 'Log Export', '501', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:export',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1045', 'Account Unlock', '501', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:unlock',   '#', 'admin', sysdate(), '', null, '');
-- Online User Buttons
insert into sys_menu values('1046', 'Online Query', '109', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1047', 'Batch Logout', '109', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1048', 'Force Logout', '109', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', sysdate(), '', null, '');
-- Scheduled Task Buttons
insert into sys_menu values('1049', 'Task Query', '110', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1050', 'Task Add', '110', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1051', 'Task Edit', '110', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1052', 'Task Delete', '110', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1053', 'Status Change', '110', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1054', 'Task Export', '110', '6', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export',         '#', 'admin', sysdate(), '', null, '');
-- Code Generation Buttons
insert into sys_menu values('1055', 'Generation Query', '115', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query',             '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1056', 'Generation Edit', '115', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit',              '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1057', 'Generation Delete', '115', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1058', 'Import Code', '115', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1059', 'Preview Code', '115', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1060', 'Generate Code', '115', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code',              '#', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 6、User and Role Association Table  User N-1 Role
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment 'User ID',
  role_id   bigint(20) not null comment 'Role ID',
  primary key(user_id, role_id)
) engine=innodb comment = 'User and Role Association Table';

-- ----------------------------
-- Initialize - User and Role Association Table Data
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');


-- ----------------------------
-- 7、Role and Menu Association Table  Role 1-N Menu
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment 'Role ID',
  menu_id   bigint(20) not null comment 'Menu ID',
  primary key(role_id, menu_id)
) engine=innodb comment = 'Role and Menu Association Table';

-- ----------------------------
-- Initialize - Role and Menu Association Table Data
-- ----------------------------
insert into sys_role_menu values ('2', '1');
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '3');
insert into sys_role_menu values ('2', '4');
insert into sys_role_menu values ('2', '100');
insert into sys_role_menu values ('2', '101');
insert into sys_role_menu values ('2', '102');
insert into sys_role_menu values ('2', '103');
insert into sys_role_menu values ('2', '104');
insert into sys_role_menu values ('2', '105');
insert into sys_role_menu values ('2', '106');
insert into sys_role_menu values ('2', '107');
insert into sys_role_menu values ('2', '108');
insert into sys_role_menu values ('2', '109');
insert into sys_role_menu values ('2', '110');
insert into sys_role_menu values ('2', '111');
insert into sys_role_menu values ('2', '112');
insert into sys_role_menu values ('2', '113');
insert into sys_role_menu values ('2', '114');
insert into sys_role_menu values ('2', '115');
insert into sys_role_menu values ('2', '116');
insert into sys_role_menu values ('2', '500');
insert into sys_role_menu values ('2', '501');
insert into sys_role_menu values ('2', '1000');
insert into sys_role_menu values ('2', '1001');
insert into sys_role_menu values ('2', '1002');
insert into sys_role_menu values ('2', '1003');
insert into sys_role_menu values ('2', '1004');
insert into sys_role_menu values ('2', '1005');
insert into sys_role_menu values ('2', '1006');
insert into sys_role_menu values ('2', '1007');
insert into sys_role_menu values ('2', '1008');
insert into sys_role_menu values ('2', '1009');
insert into sys_role_menu values ('2', '1010');
insert into sys_role_menu values ('2', '1011');
insert into sys_role_menu values ('2', '1012');
insert into sys_role_menu values ('2', '1013');
insert into sys_role_menu values ('2', '1014');
insert into sys_role_menu values ('2', '1015');
insert into sys_role_menu values ('2', '1016');
insert into sys_role_menu values ('2', '1017');
insert into sys_role_menu values ('2', '1018');
insert into sys_role_menu values ('2', '1019');
insert into sys_role_menu values ('2', '1020');
insert into sys_role_menu values ('2', '1021');
insert into sys_role_menu values ('2', '1022');
insert into sys_role_menu values ('2', '1023');
insert into sys_role_menu values ('2', '1024');
insert into sys_role_menu values ('2', '1025');
insert into sys_role_menu values ('2', '1026');
insert into sys_role_menu values ('2', '1027');
insert into sys_role_menu values ('2', '1028');
insert into sys_role_menu values ('2', '1029');
insert into sys_role_menu values ('2', '1030');
insert into sys_role_menu values ('2', '1031');
insert into sys_role_menu values ('2', '1032');
insert into sys_role_menu values ('2', '1033');
insert into sys_role_menu values ('2', '1034');
insert into sys_role_menu values ('2', '1035');
insert into sys_role_menu values ('2', '1036');
insert into sys_role_menu values ('2', '1037');
insert into sys_role_menu values ('2', '1038');
insert into sys_role_menu values ('2', '1039');
insert into sys_role_menu values ('2', '1040');
insert into sys_role_menu values ('2', '1041');
insert into sys_role_menu values ('2', '1042');
insert into sys_role_menu values ('2', '1043');
insert into sys_role_menu values ('2', '1044');
insert into sys_role_menu values ('2', '1045');
insert into sys_role_menu values ('2', '1046');
insert into sys_role_menu values ('2', '1047');
insert into sys_role_menu values ('2', '1048');
insert into sys_role_menu values ('2', '1049');
insert into sys_role_menu values ('2', '1050');
insert into sys_role_menu values ('2', '1051');
insert into sys_role_menu values ('2', '1052');
insert into sys_role_menu values ('2', '1053');
insert into sys_role_menu values ('2', '1054');
insert into sys_role_menu values ('2', '1055');
insert into sys_role_menu values ('2', '1056');
insert into sys_role_menu values ('2', '1057');
insert into sys_role_menu values ('2', '1058');
insert into sys_role_menu values ('2', '1059');
insert into sys_role_menu values ('2', '1060');

-- ----------------------------
-- 8、Role and Department Association Table  Role 1-N Departments
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id   bigint(20) not null comment 'Role ID',
  dept_id   bigint(20) not null comment 'Department ID',
  primary key(role_id, dept_id)
) engine=innodb comment = 'Role and Department Association Table';

-- ----------------------------
-- Initialize - Role and Department Association Table Data
-- ----------------------------
insert into sys_role_dept values ('2', '100');
insert into sys_role_dept values ('2', '101');
insert into sys_role_dept values ('2', '105');


-- ----------------------------
-- 9、User and Post Association Table  User 1-N Posts
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
  user_id   bigint(20) not null comment 'User ID',
  post_id   bigint(20) not null comment 'Post ID',
  primary key (user_id, post_id)
) engine=innodb comment = 'User and Post Association Table';

-- ----------------------------
-- Initialize - User and Post Association Table Data
-- ----------------------------
insert into sys_user_post values ('1', '1');
insert into sys_user_post values ('2', '2');


-- ----------------------------
-- 10、Operation Log Record
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id           bigint(20)      not null auto_increment    comment 'Log Primary Key',
  title             varchar(50)     default ''                 comment 'Module Title',
  business_type     int(2)          default 0                  comment 'Business Type (0 Other 1 Add 2 Edit 3 Delete)',
  method            varchar(200)    default ''                 comment 'Method Name',
  request_method    varchar(10)     default ''                 comment 'Request Method',
  operator_type     int(1)          default 0                  comment 'Operator Type (0 Other 1 Backend User 2 Mobile User)',
  oper_name         varchar(50)     default ''                 comment 'Operator Name',
  dept_name         varchar(50)     default ''                 comment 'Department Name',
  oper_url          varchar(255)    default ''                 comment 'Request URL',
  oper_ip           varchar(128)    default ''                 comment 'Host Address',
  oper_location     varchar(255)    default ''                 comment 'Operation Location',
  oper_param        varchar(2000)   default ''                 comment 'Request Parameters',
  json_result       varchar(2000)   default ''                 comment 'Return Parameters',
  status            int(1)          default 0                  comment 'Operation Status (0 Normal 1 Exception)',
  error_msg         varchar(2000)   default ''                 comment 'Error Message',
  oper_time         datetime                                   comment 'Operation Time',
  cost_time         bigint(20)      default 0                  comment 'Execution Time',
  primary key (oper_id),
  key idx_sys_oper_log_bt (business_type),
  key idx_sys_oper_log_s  (status),
  key idx_sys_oper_log_ot (oper_time)
) engine=innodb auto_increment=100 comment = 'Operation Log Record';


-- ----------------------------
-- 11、Dictionary Type Table
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          bigint(20)      not null auto_increment    comment 'Dictionary Primary Key',
  dict_name        varchar(100)    default ''                 comment 'Dictionary Name',
  dict_type        varchar(100)    default ''                 comment 'Dictionary Type',
  status           char(1)         default '0'                comment 'Status (0 Normal 1 Disabled)',
  create_by        varchar(64)     default ''                 comment 'Creator',
  create_time      datetime                                   comment 'Creation Time',
  update_by        varchar(64)     default ''                 comment 'Updater',
  update_time      datetime                                   comment 'Update Time',
  remark           varchar(500)    default null               comment 'Remarks',
  primary key (dict_id),
  unique (dict_type)
) engine=innodb auto_increment=100 comment = 'Dictionary Type Table';

insert into sys_dict_type values(1,  'User Gender', 'sys_user_sex',        '0', 'admin', sysdate(), '', null, 'User Gender List');
insert into sys_dict_type values(2,  'Menu Status', 'sys_show_hide',       '0', 'admin', sysdate(), '', null, 'Menu Status List');
insert into sys_dict_type values(3,  'System Switch', 'sys_normal_disable',  '0', 'admin', sysdate(), '', null, 'System Switch List');
insert into sys_dict_type values(4,  'Task Status', 'sys_job_status',      '0', 'admin', sysdate(), '', null, 'Task Status List');
insert into sys_dict_type values(5,  'Task Group', 'sys_job_group',       '0', 'admin', sysdate(), '', null, 'Task Group List');
insert into sys_dict_type values(6,  'System Yes/No', 'sys_yes_no',          '0', 'admin', sysdate(), '', null, 'System Yes/No List');
insert into sys_dict_type values(7,  'Notice Type', 'sys_notice_type',     '0', 'admin', sysdate(), '', null, 'Notice Type List');
insert into sys_dict_type values(8,  'Notice Status', 'sys_notice_status',   '0', 'admin', sysdate(), '', null, 'Notice Status List');
insert into sys_dict_type values(9,  'Operation Type', 'sys_oper_type',       '0', 'admin', sysdate(), '', null, 'Operation Type List');
insert into sys_dict_type values(10, 'System Status', 'sys_common_status',   '0', 'admin', sysdate(), '', null, 'Login Status List');


-- ----------------------------
-- 12、Dictionary Data Table
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code        bigint(20)      not null auto_increment    comment 'Dictionary Code',
  dict_sort        int(4)          default 0                  comment 'Dictionary Sort Order',
  dict_label       varchar(100)    default ''                 comment 'Dictionary Label',
  dict_value       varchar(100)    default ''                 comment 'Dictionary Value',
  dict_type        varchar(100)    default ''                 comment 'Dictionary Type',
  css_class        varchar(100)    default null               comment 'CSS Class (Other Style Extensions)',
  list_class       varchar(100)    default null               comment 'Table Echo Style',
  is_default       char(1)         default 'N'                comment 'Is Default (Y Yes N No)',
  status           char(1)         default '0'                comment 'Status (0 Normal 1 Disabled)',
  create_by        varchar(64)     default ''                 comment 'Creator',
  create_time      datetime                                   comment 'Creation Time',
  update_by        varchar(64)     default ''                 comment 'Updater',
  update_time      datetime                                   comment 'Update Time',
  remark           varchar(500)    default null               comment 'Remarks',
  primary key (dict_code)
) engine=innodb auto_increment=100 comment = 'Dictionary Data Table';

insert into sys_dict_data values(1,  1,  'Male',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', sysdate(), '', null, 'Gender Male');
insert into sys_dict_data values(2,  2,  'Female',     '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, 'Gender Female');
insert into sys_dict_data values(3,  3,  'Unknown',    '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, 'Gender Unknown');
insert into sys_dict_data values(4,  1,  'Show',       '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, 'Show Menu');
insert into sys_dict_data values(5,  2,  'Hide',       '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Hide Menu');
insert into sys_dict_data values(6,  1,  'Normal',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, 'Normal Status');
insert into sys_dict_data values(7,  2,  'Disabled',   '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Disabled Status');
insert into sys_dict_data values(8,  1,  'Normal',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, 'Normal Status');
insert into sys_dict_data values(9,  2,  'Paused',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Paused Status');
insert into sys_dict_data values(10, 1,  'Default',    'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', sysdate(), '', null, 'Default Group');
insert into sys_dict_data values(11, 2,  'System',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', sysdate(), '', null, 'System Group');
insert into sys_dict_data values(12, 1,  'Yes',        'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, 'System Default Yes');
insert into sys_dict_data values(13, 2,  'No',         'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'System Default No');
insert into sys_dict_data values(14, 1,  'Notice',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', sysdate(), '', null, 'Notice');
insert into sys_dict_data values(15, 2,  'Announcement', '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', sysdate(), '', null, 'Announcement');
insert into sys_dict_data values(16, 1,  'Normal',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, 'Normal Status');
insert into sys_dict_data values(17, 2,  'Closed',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Closed Status');
insert into sys_dict_data values(18, 99, 'Other',      '0',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, 'Other Operation');
insert into sys_dict_data values(19, 1,  'Add',        '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, 'Add Operation');
insert into sys_dict_data values(20, 2,  'Edit',       '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, 'Edit Operation');
insert into sys_dict_data values(21, 3,  'Delete',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Delete Operation');
insert into sys_dict_data values(22, 4,  'Authorize',  '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', sysdate(), '', null, 'Authorize Operation');
insert into sys_dict_data values(23, 5,  'Export',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, 'Export Operation');
insert into sys_dict_data values(24, 6,  'Import',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, 'Import Operation');
insert into sys_dict_data values(25, 7,  'Force Logout', '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Force Logout Operation');
insert into sys_dict_data values(26, 8,  'Generate Code', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, 'Generate Code Operation');
insert into sys_dict_data values(27, 9,  'Clear Data', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Clear Data Operation');
insert into sys_dict_data values(28, 1,  'Success',    '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', sysdate(), '', null, 'Normal Status');
insert into sys_dict_data values(29, 2,  'Failure',    '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, 'Disabled Status');


-- ----------------------------
-- 13、Parameter Configuration Table
-- ----------------------------
drop table if exists sys_config;
create table sys_config (
  config_id         int(5)          not null auto_increment    comment 'Parameter Primary Key',
  config_name       varchar(100)    default ''                 comment 'Parameter Name',
  config_key        varchar(100)    default ''                 comment 'Parameter Key',
  config_value      varchar(500)    default ''                 comment 'Parameter Value',
  config_type       char(1)         default 'N'                comment 'System Built-in (Y Yes N No)',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time       datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  remark            varchar(500)    default null               comment 'Remarks',
  primary key (config_id)
) engine=innodb auto_increment=100 comment = 'Parameter Configuration Table';

insert into sys_config values(1, 'Main Frame Page - Default Skin Name',     'sys.index.skinName',       'skin-blue',     'Y', 'admin', sysdate(), '', null, 'Blue skin-blue, Green skin-green, Purple skin-purple, Red skin-red, Yellow skin-yellow' );
insert into sys_config values(2, 'User Management - Initial Account Password',         'sys.user.initPassword',    '123456',        'Y', 'admin', sysdate(), '', null, 'Initial Password 123456' );
insert into sys_config values(3, 'Main Frame Page - Sidebar Theme',           'sys.index.sideTheme',      'theme-dark',    'Y', 'admin', sysdate(), '', null, 'Dark Theme theme-dark, Light Theme theme-light' );
insert into sys_config values(4, 'Account Self-service - Enable User Registration Function', 'sys.account.registerUser', 'false',         'Y', 'admin', sysdate(), '', null, 'Enable User Registration Function (true Enable, false Disable)');
insert into sys_config values(5, 'User Login - Blacklist List',           'sys.login.blackIPList',    '',              'Y', 'admin', sysdate(), '', null, 'Set Login IP Blacklist Restrictions, Multiple Matches Separated by ;, Supports Wildcard (*) and Network Segments');


-- ----------------------------
-- 14、System Access Record
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment 'Access ID',
  user_name      varchar(50)    default ''                comment 'User Account',
  ipaddr         varchar(128)   default ''                comment 'Login IP Address',
  status         char(1)        default '0'               comment 'Login Status (0 Success 1 Failure)',
  msg            varchar(255)   default ''                comment 'Message',
  access_time    datetime                                 comment 'Access Time',
  primary key (info_id),
  key idx_sys_logininfor_s  (status),
  key idx_sys_logininfor_lt (access_time)
) engine=innodb auto_increment=100 comment = 'System Access Record';


-- ----------------------------
-- 15、Scheduled Task Scheduling Table
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id              bigint(20)    not null auto_increment    comment 'Job ID',
  job_name            varchar(64)   default ''                 comment 'Job Name',
  job_group           varchar(64)   default 'DEFAULT'          comment 'Job Group Name',
  invoke_target       varchar(500)  not null                   comment 'Invoke Target String',
  cron_expression     varchar(255)  default ''                 comment 'Cron Expression',
  misfire_policy      varchar(20)   default '3'                comment 'Misfire Policy (1 Immediately Execute 2 Execute Once 3 Discard Execution)',
  concurrent          char(1)       default '1'                comment 'Concurrent Execution (0 Allow 1 Prohibit)',
  status              char(1)       default '0'                comment 'Status (0 Normal 1 Paused)',
  create_by           varchar(64)   default ''                 comment 'Creator',
  create_time         datetime                                 comment 'Creation Time',
  update_by           varchar(64)   default ''                 comment 'Updater',
  update_time         datetime                                 comment 'Update Time',
  remark              varchar(500)  default ''                 comment 'Remarks',
  primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 comment = 'Scheduled Task Scheduling Table';

insert into sys_job values(1, 'System Default (No Parameters)', 'DEFAULT', 'ryTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
insert into sys_job values(2, 'System Default (With Parameters)', 'DEFAULT', 'ryTask.ryParams(\'ry\')',  '0/15 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
insert into sys_job values(3, 'System Default (Multiple Parameters)', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 16、Scheduled Task Scheduling Log Table
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          bigint(20)     not null auto_increment    comment 'Job Log ID',
  job_name            varchar(64)    not null                   comment 'Job Name',
  job_group           varchar(64)    not null                   comment 'Job Group Name',
  invoke_target       varchar(500)   not null                   comment 'Invoke Target String',
  job_message         varchar(500)                              comment 'Log Message',
  status              char(1)        default '0'                comment 'Execution Status (0 Normal 1 Failure)',
  exception_info      varchar(2000)  default ''                 comment 'Exception Information',
  create_time         datetime                                  comment 'Creation Time',
  primary key (job_log_id)
) engine=innodb comment = 'Scheduled Task Scheduling Log Table';


-- ----------------------------
-- 17、Notice Announcement Table
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice (
  notice_id         int(4)          not null auto_increment    comment 'Notice ID',
  notice_title      varchar(255)    not null                   comment 'Notice Title',
  notice_type       char(1)         not null                   comment 'Notice Type (1 Notice 2 Announcement)',
  notice_content    longblob        default null               comment 'Notice Content',
  status            char(1)         default '0'                comment 'Notice Status (0 Normal 1 Closed)',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time       datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  remark            varchar(255)    default null               comment 'Remarks',
  primary key (notice_id)
) engine=innodb auto_increment=10 comment = 'Notice Announcement Table';

-- ----------------------------
-- Initialize - Notice Information Table Data
-- ----------------------------
insert into sys_notice values('1', 'Friendly Reminder: RuoYi New Version Released on 2018-07-01', '2', 'New Version Content', '0', 'admin', sysdate(), '', null, 'Administrator');
insert into sys_notice values('2', 'Maintenance Notice: RuoYi System Maintenance at Midnight on 2018-07-01', '1', 'Maintenance Content',   '0', 'admin', sysdate(), '', null, 'Administrator');


-- ----------------------------
-- 18、Code Generation Business Table
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
  table_id          bigint(20)      not null auto_increment    comment 'ID',
  table_name        varchar(200)    default ''                 comment 'Table Name',
  table_comment     varchar(500)    default ''                 comment 'Table Description',
  sub_table_name    varchar(64)     default null               comment 'Sub-table Name',
  sub_table_fk_name varchar(64)     default null               comment 'Sub-table Foreign Key Name',
  class_name        varchar(100)    default ''                 comment 'Entity Class Name',
  tpl_category      varchar(200)    default 'crud'             comment 'Template Used (crud Single Table Operation tree Tree Table Operation)',
  tpl_web_type      varchar(30)     default ''                 comment 'Frontend Template Type (element-ui Template element-plus Template)',
  package_name      varchar(100)                               comment 'Generated Package Path',
  module_name       varchar(30)                                comment 'Generated Module Name',
  business_name     varchar(30)                                comment 'Generated Business Name',
  function_name     varchar(50)                                comment 'Generated Function Name',
  function_author   varchar(50)                                comment 'Generated Function Author',
  gen_type          char(1)         default '0'                comment 'Code Generation Method (0 Zip Package 1 Custom Path)',
  gen_path          varchar(200)    default '/'                comment 'Generated Path (Leave Blank for Project Path)',
  options           varchar(1000)                              comment 'Other Generation Options',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time 	    datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  remark            varchar(500)    default null               comment 'Remarks',
  primary key (table_id)
) engine=innodb auto_increment=1 comment = 'Code Generation Business Table';


-- ----------------------------
-- 19、Code Generation Business Table Columns
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment 'ID',
  table_id          bigint(20)                                 comment 'Belonging Table ID',
  column_name       varchar(200)                               comment 'Column Name',
  column_comment    varchar(500)                               comment 'Column Description',
  column_type       varchar(100)                               comment 'Column Type',
  java_type         varchar(500)                               comment 'JAVA Type',
  java_field        varchar(200)                               comment 'JAVA Field Name',
  is_pk             char(1)                                    comment 'Is Primary Key (1 Yes)',
  is_increment      char(1)                                    comment 'Is Auto Increment (1 Yes)',
  is_required       char(1)                                    comment 'Is Required (1 Yes)',
  is_insert         char(1)                                    comment 'Is Insert Field (1 Yes)',
  is_edit           char(1)                                    comment 'Is Edit Field (1 Yes)',
  is_list           char(1)                                    comment 'Is List Field (1 Yes)',
  is_query          char(1)                                    comment 'Is Query Field (1 Yes)',
  query_type        varchar(200)    default 'EQ'               comment 'Query Type (Equal, Not Equal, Greater Than, Less Than, Range)',
  html_type         varchar(200)                               comment 'Display Type (Text Box, Text Area, Dropdown Box, Checkbox, Radio Button, Date Control)',
  dict_type         varchar(200)    default ''                 comment 'Dictionary Type',
  sort              int                                        comment 'Sort Order',
  create_by         varchar(64)     default ''                 comment 'Creator',
  create_time 	    datetime                                   comment 'Creation Time',
  update_by         varchar(64)     default ''                 comment 'Updater',
  update_time       datetime                                   comment 'Update Time',
  primary key (column_id)
) engine=innodb auto_increment=1 comment = 'Code Generation Business Table Columns';
