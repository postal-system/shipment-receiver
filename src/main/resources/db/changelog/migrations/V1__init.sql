create table if not exists shipment (
  source_id uuid primary key,
  raw_shipment jsonb not null,
  sender varchar(250) not null,
  content varchar(250),
  full_name varchar(250) not null,
  portion_id uuid
);