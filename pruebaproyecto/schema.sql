 create sequence hibernate_sequence start with 4 increment by 1;

 create table Habitacion (
        id bigint not null, 
        precio double not null, 
        tipoHab varchar(255), 

        primary key (id)
        );
 

 create table Reserva (
        id bigint not null,
        fechaFin date, 
        fechaInicio date, 
        precio double not null, 
        habitacion_id bigint, 
        usuario_id bigint,
         
        primary key (id)
        );

 create table Usuario (
        id bigint not null,
        admin boolean not null,
        apellidos varchar(255),
        contrasena varchar(255),
        email varchar(255),
        nombre varchar(255),
        primary key (id)
        );


 alter table Reserva add constraint FK5lgaswvjmuimxc9826gxefwhx foreign key (habitacion_id) references Habitacion;
 alter table Reserva add constraint FKkrxqwqvewd2pls5tdigwqprj2 foreign key (usuario_id) references Usuario;