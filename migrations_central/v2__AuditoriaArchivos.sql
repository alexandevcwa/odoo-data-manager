-- Audotia de archivos OXML importados
create table au_oxml_files (
    id serial primary key,
    fecha timestamp not null default current_timestamp,
    archivo varchar(255) not null,
    sha256 varchar(64) not null
);