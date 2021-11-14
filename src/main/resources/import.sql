drop table if exists monitor_result
drop table if exists monitor_user
drop table if exists monitored_endpoint
create table monitor_result (result_id bigint not null auto_increment, date_of_check datetime, endpoint_id bigint, returned_payload varchar(10000), status_code integer not null, primary key (result_id)) engine=MyISAM
create table monitor_user (user_id bigint not null auto_increment, user_email varchar(255), user_token varchar(255), user_name varchar(255), primary key (user_id)) engine=MyISAM
create table monitored_endpoint (endpoint_id bigint not null auto_increment, date_of_creation datetime, date_of_last_check datetime, monitored_interval_in_seconds bigint, name varchar(255), url varchar(255), user_id bigint, primary key (endpoint_id)) engine=MyISAM
alter table monitor_result add constraint FK1w9oj9x8umsfcakexkxyoaow2 foreign key (endpoint_id) references monitored_endpoint (endpoint_id)
alter table monitored_endpoint add constraint FKbqb1ymty3mcvvo7om6l6ke5ym foreign key (user_id) references monitor_user (user_id)
INSERT INTO monitor_user (`user_id`, `user_email`, `user_token`, `user_name`) VALUES (1, 'dima@gmail.com', '2bb7eb3a-db9c-4492-a06d-2288056b2d62', 'Dima');
INSERT INTO monitor_user (`user_id`, `user_email`, `user_token`, `user_name`) VALUES (2, 'iggy@gmail.com', '6c3001ce-5763-417e-9f0f-aa73dbae03bd', 'Iggy');