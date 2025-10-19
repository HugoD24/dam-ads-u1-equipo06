# dam-ads-u1-equipo06
dam-ads-u1-equipo06
CLASE DAMA SPORTS – LÓGICA 

Este repo contiene el “modelo”, “servicio” y “persistencia en ficheros” para integrar con la UI JavaFx de Juana.


## Estructura.

club-deportivo/
│
├── src/
│   ├── modelo/          # Clases: Socio, Pista, Reserva, IdObligatorioException
│   ├── servicio/        # Clase ClubDeportivo
│   └── vista/           # MainApp y subpaquete views con formularios y dashboards
│
├── data/
│   ├── socios.csv       
│   ├── pistas.csv
│   └── reservas.csv
│
├── docs/
│   └── presentacion.pdf # Presentación del proyecto
│
└── README.md


## Como ejecutar.

1. Instala JDK 23 y Maven.
2. Desde la raiz:
     ````bash
     mvn – q -DskipTest exec:java
     ````
     Esto carga /data, añade un ejemplo y guarda.


## Integración con la UI.
- Invoca los métodos públicos especificados por la práctica: altaSocio, bajaSocio, altaPista, cambiarDisponibilidadPista, crearReserva, cancelarReserva, guardar().
- Las listas se exponen como inmutables (getSocios(), getPistas(), getReservas()) para enlazar con tablas.


## Reglas clave implementadas.
- IDs únicos para socio/pista/reserva.
- Baja de socio prohibida si tiene reservas futuras.
- Crear reserva: pista operativa + sin solapes en esa pista y fecha.
- Persistencia: UTF‑8, separador ;, líneas inválidas se ignoran.


## Persistencia: UTF‑8, separador ;, líneas inválidas se ignoran.
- socios.csv: idSocio;dni;nombre;apellidos;telefono;email
- pistas.csv: idPista;deporte;descripcion;disponible
- reservas.csv: idReserva;idSocio;idPista;fecha(YYYY-MM-DD);hora(HH:MM);duracionMin;precio


## Notas.
- Mantén la lógica de negocio fuera de la UI.
- Añade pruebas y validaciones adicionales según necesitéis.
