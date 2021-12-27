create table if not exists letter (
  id uuid primary key,
  raw_letter jsonb not null,
  sender varchar(250) not null,
  content varchar(250),
  receiver varchar(250) not null,
  time_stamp timestamp,
  portion_id uuid
);

create table if not exists parcel (
  id uuid primary key,
  raw_parcel jsonb not null,
  sender varchar(250) not null,
  receiver varchar(250) not null,
  post_office_receiver_id int
);