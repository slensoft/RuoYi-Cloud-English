DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

-- ----------------------------
-- 1、Store detailed information of each configured jobDetail
-- ----------------------------
create table QRTZ_JOB_DETAILS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    job_name             varchar(200)    not null            comment 'Job Name',
    job_group            varchar(200)    not null            comment 'Job Group Name',
    description          varchar(250)    null                comment 'Description',
    job_class_name       varchar(250)    not null            comment 'Job Class Name',
    is_durable           varchar(1)      not null            comment 'Is Durable',
    is_nonconcurrent     varchar(1)      not null            comment 'Is Non-Concurrent',
    is_update_data       varchar(1)      not null            comment 'Is Update Data',
    requests_recovery    varchar(1)      not null            comment 'Requests Recovery',
    job_data             blob            null                comment 'Stores Persisted Job Object',
    primary key (sched_name, job_name, job_group)
) engine=innodb comment = 'Job Details Table';

-- ----------------------------
-- 2、 Store information of configured Triggers
-- ----------------------------
create table QRTZ_TRIGGERS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    trigger_name         varchar(200)    not null            comment 'Trigger Name',
    trigger_group        varchar(200)    not null            comment 'Trigger Group Name',
    job_name             varchar(200)    not null            comment 'Foreign Key to QRTZ_JOB_DETAILS.job_name',
    job_group            varchar(200)    not null            comment 'Foreign Key to QRTZ_JOB_DETAILS.job_group',
    description          varchar(250)    null                comment 'Description',
    next_fire_time       bigint(13)      null                comment 'Next Fire Time (milliseconds)',
    prev_fire_time       bigint(13)      null                comment 'Previous Fire Time (default -1 means no fire)',
    priority             integer         null                comment 'Priority',
    trigger_state        varchar(16)     not null            comment 'Trigger State',
    trigger_type         varchar(8)      not null            comment 'Trigger Type',
    start_time           bigint(13)      not null            comment 'Start Time',
    end_time             bigint(13)      null                comment 'End Time',
    calendar_name        varchar(200)    null                comment 'Calendar Name',
    misfire_instr        smallint(2)     null                comment 'Misfire Instruction',
    job_data             blob            null                comment 'Stores Persisted Job Object',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group) references QRTZ_JOB_DETAILS(sched_name, job_name, job_group)
) engine=innodb comment = 'Trigger Details Table';

-- ----------------------------
-- 3、 Store simple Triggers, including repeat count, interval, and times triggered
-- ----------------------------
create table QRTZ_SIMPLE_TRIGGERS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    trigger_name         varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_name',
    trigger_group        varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_group',
    repeat_count         bigint(7)       not null            comment 'Repeat Count',
    repeat_interval      bigint(12)      not null            comment 'Repeat Interval',
    times_triggered      bigint(10)      not null            comment 'Times Triggered',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Simple Trigger Details Table';

-- ----------------------------
-- 4、 Store Cron Triggers, including Cron expression and time zone information
-- ----------------------------
create table QRTZ_CRON_TRIGGERS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    trigger_name         varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_name',
    trigger_group        varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_group',
    cron_expression      varchar(200)    not null            comment 'Cron Expression',
    time_zone_id         varchar(80)                         comment 'Time Zone',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Cron Trigger Details Table';

-- ----------------------------
-- 5、 Store Triggers as Blob type (used when Quartz users create custom Trigger types via JDBC, and JobStore does not know how to store the instance)
-- ----------------------------
create table QRTZ_BLOB_TRIGGERS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    trigger_name         varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_name',
    trigger_group        varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_group',
    blob_data            blob            null                comment 'Stores Persisted Trigger Object',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Blob Trigger Details Table';

-- ----------------------------
-- 6、 Store calendar information as Blob type, Quartz can configure a calendar to specify a time range
-- ----------------------------
create table QRTZ_CALENDARS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    calendar_name        varchar(200)    not null            comment 'Calendar Name',
    calendar             blob            not null            comment 'Stores Persisted Calendar Object',
    primary key (sched_name, calendar_name)
) engine=innodb comment = 'Calendar Information Table';

-- ----------------------------
-- 7、 Store information of paused Trigger groups
-- ----------------------------
create table QRTZ_PAUSED_TRIGGER_GRPS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    trigger_group        varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_group',
    primary key (sched_name, trigger_group)
) engine=innodb comment = 'Paused Trigger Groups Table';

-- ----------------------------
-- 8、 Store state information related to fired Triggers and execution information of associated Jobs
-- ----------------------------
create table QRTZ_FIRED_TRIGGERS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    entry_id             varchar(95)     not null            comment 'Scheduler Instance ID',
    trigger_name         varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_name',
    trigger_group        varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_group',
    instance_name        varchar(200)    not null            comment 'Scheduler Instance Name',
    fired_time           bigint(13)      not null            comment 'Fired Time',
    sched_time           bigint(13)      not null            comment 'Scheduled Time',
    priority             integer         not null            comment 'Priority',
    state                varchar(16)     not null            comment 'State',
    job_name             varchar(200)    null                comment 'Job Name',
    job_group            varchar(200)    null                comment 'Job Group Name',
    is_nonconcurrent     varchar(1)      null                comment 'Is Non-Concurrent',
    requests_recovery    varchar(1)      null                comment 'Requests Recovery',
    primary key (sched_name, entry_id)
) engine=innodb comment = 'Fired Triggers Table';

-- ----------------------------
-- 9、 Store a small amount of information about the Scheduler state, visible in a cluster to see other Scheduler instances
-- ----------------------------
create table QRTZ_SCHEDULER_STATE (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    instance_name        varchar(200)    not null            comment 'Instance Name',
    last_checkin_time    bigint(13)      not null            comment 'Last Checkin Time',
    checkin_interval     bigint(13)      not null            comment 'Checkin Interval',
    primary key (sched_name, instance_name)
) engine=innodb comment = 'Scheduler State Table';

-- ----------------------------
-- 10、 Store information of pessimistic locks (if pessimistic locks are used)
-- ----------------------------
create table QRTZ_LOCKS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    lock_name            varchar(40)     not null            comment 'Pessimistic Lock Name',
    primary key (sched_name, lock_name)
) engine=innodb comment = 'Pessimistic Lock Information Table';

-- ----------------------------
-- 11、 Quartz cluster synchronization mechanism row lock table
-- ----------------------------
create table QRTZ_SIMPROP_TRIGGERS (
    sched_name           varchar(120)    not null            comment 'Scheduler Name',
    trigger_name         varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_name',
    trigger_group        varchar(200)    not null            comment 'Foreign Key to QRTZ_TRIGGERS.trigger_group',
    str_prop_1           varchar(512)    null                comment 'First String Property of Trigger',
    str_prop_2           varchar(512)    null                comment 'Second String Property of Trigger',
    str_prop_3           varchar(512)    null                comment 'Third String Property of Trigger',
    int_prop_1           int             null                comment 'First Integer Property of Trigger',
    int_prop_2           int             null                comment 'Second Integer Property of Trigger',
    long_prop_1          bigint          null                comment 'First Long Property of Trigger',
    long_prop_2          bigint          null                comment 'Second Long Property of Trigger',
    dec_prop_1           numeric(13,4)   null                comment 'First Decimal Property of Trigger',
    dec_prop_2           numeric(13,4)   null                comment 'Second Decimal Property of Trigger',
    bool_prop_1          varchar(1)      null                comment 'First Boolean Property of Trigger',
    bool_prop_2          varchar(1)      null                comment 'Second Boolean Property of Trigger',
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Synchronization Mechanism Row Lock Table';

commit;
