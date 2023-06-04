# Consigna

## Funcionalidad básica

- Administrar médicos. Cada médico es un usuario del sistema, pudiendo consultar los turnos que tiene para una 
  fecha determinada.
- Cada médico cobra cierta cantidad de dinero por su consulta
- Administrar pacientes.
- Administrar turnos, fecha y hora. No se puede tomar un turno con un mismo
  médico a una misma hora. El médico debe elegirse de una lista, al igual que el
  paciente.
- Reportes: se debe poder obtener una lista de cuánto ha cobrado un médico, por
  cuántas consultas (turnos) entre dos fechas.

## Adicionales

- Los pacientes también son usuarios, pudiendo consultar cuándo tienen que asistir
  a una consulta.
- Administrar lugares de atención (consultorios) con la consecuencia de que un
  médico debe atender en un cierto lugar entre una y otra fecha, y el turno debe
  tomarse en un cierto consultorio (es solo una restricción más al turno).
- Reporte adicional: listar los médicos y su recaudación entre dos fechas.

## Bonus points

- Manejar obras sociales. Si un paciente tiene la misma obra social que la que
  atiende un médico, se le hace un descuento del 50 % en la consulta
- Mostar un grilla mensual o semanal con turnos (como si se mostrara un
  calendario).

# Desarrollo

## Modelos
- Usuario
  - Doctor
  - Paciente
  - Administrador
- Turno
- Consultorio
- Obra Social

## Requerimientos
- Modelo
- DAO
- Excepciones
- UI

# Ejecución

1. Correr ar.com.rorra.db.SchemaTasks para crear las tablas que necesita el sistema.
2. Correr ar.com.rorra.Main para iniciar el sistema.