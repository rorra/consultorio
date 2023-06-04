package ar.com.rorra.db;

import ar.com.rorra.bo.*;
import ar.com.rorra.entidad.*;
import ar.com.rorra.exceptions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Manager de la base de datos.
 * Se utiliza para la instalación del sistema, creando todas las tablas necesarias para operar el sistema.
 */
public class SchemaTasks {
  /**
   * Crea las tablas necesarias para el correcto funcionamiento del sistema
   *
   * @throws SQLException
   */
  public void crearTablas() throws DBException {
    Connection c = DBConnection.getInstance().getConnection();
    ArrayList<String> list = new ArrayList<>();
    list.add("""
          CREATE TABLE IF NOT EXISTS consultorios(
              id INTEGER AUTO_INCREMENT PRIMARY KEY,
              nombre VARCHAR(255),
              direccion VARCHAR(255)
          )
      """);
    list.add("""
          CREATE TABLE IF NOT EXISTS obrasSociales(
              id INTEGER AUTO_INCREMENT PRIMARY KEY,
              nombre VARCHAR(255),
              descuento INTEGER,
              UNIQUE (nombre)
          )
      """);
    list.add("""
          CREATE TABLE IF NOT EXISTS doctores(
              id INTEGER AUTO_INCREMENT PRIMARY KEY,
              consultorio_id INTEGER,
              email VARCHAR(255),
              password VARCHAR(255),
              legajo INTEGER,
              nombre VARCHAR(255), 
              telefono VARCHAR(255),
              tarifa numeric,
              FOREIGN KEY (consultorio_id) REFERENCES consultorios(id),
              UNIQUE (email),
              UNIQUE (legajo)
          )
      """);
    list.add("""
          CREATE TABLE IF NOT EXISTS pacientes(
              id INTEGER AUTO_INCREMENT PRIMARY KEY,
              obra_social_id INTEGER,
              email VARCHAR(255),
              password VARCHAR(255),
              dni INTEGER, 
              nombre VARCHAR(255),
              telefono VARCHAR(255),
              UNIQUE (email)
          )
      """);
    list.add("""
          CREATE TABLE IF NOT EXISTS administradores(
              id INTEGER AUTO_INCREMENT PRIMARY KEY,
              email VARCHAR(255),
              password VARCHAR(255),
              legajo INTEGER,
              nombre VARCHAR(255),
              telefono VARCHAR(255),
              UNIQUE (email),
              UNIQUE (legajo)
          )
      """);
    list.add("""
          CREATE TABLE IF NOT EXISTS turnos(
              id INTEGER AUTO_INCREMENT PRIMARY KEY, 
              doctor_id INTEGER, 
              paciente_id INTEGER,
              consultorio_id INTEGER,
              fecha TIMESTAMP,
              FOREIGN KEY (doctor_id) REFERENCES doctores(id),
              FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
              FOREIGN KEY (consultorio_id) REFERENCES consultorios(id)
          )
      """);

    try {
      Statement s = c.createStatement();
      for (String sql : list) {
        s.execute(sql);
      }
    } catch (SQLException ex) {
      try {
        c.rollback();
      } catch (SQLException ex1) {
        throw new DBException(
          DBExceptionType.TRANSACTION_ERROR,
          "Error en rollback de creación de las tablas: " + ex1.getMessage()
        );
      }
      throw new DBException(
        DBExceptionType.TRANSACTION_ERROR,
        "Error al crear las tablas para correr el sistema: " + ex.getMessage()
      );
    } finally {
      try {
        c.close();
      } catch (SQLException ex) {
        throw new DBException(
          DBExceptionType.CONNECTION_ERROR,
          "Error al cerrar la conexión de la base de datos: " + ex.getMessage()
        );
      }
    }
  }

  /**
   * Inserta datos de prueba para el funcionamiento del sistema
   *
   * @throws DBException
   */
  public void insertarDatosDePrueba() throws DBException, BOException {
    ConsultorioBO consultorioBO = new ConsultorioBO();
    ObraSocialBO obraSocialBO = new ObraSocialBO();
    AdministradorBO administradorBO = new AdministradorBO();
    DoctorBO doctorBO = new DoctorBO();
    PacienteBO pacienteBO = new PacienteBO();
    TurnoBO turnoBO = new TurnoBO();

    // Dos consultorios
    Consultorio consultorio1 = consultorioBO.save(new Consultorio("Microcentro", "Balcarce 223"));
    Consultorio consultorio2 = consultorioBO.save(new Consultorio("Balvanera", "Pueyrredón 1200"));

    // Obras sociales
    ObraSocial obraSocial1 = obraSocialBO.save(new ObraSocial("Particular", 0));
    ObraSocial obraSocial2 = obraSocialBO.save(new ObraSocial("Sancor", 30));
    ObraSocial obraSocial3 = obraSocialBO.save(new ObraSocial("Osde", 50));

    // Administradores
    administradorBO.save(new Administrador("admin@gmail.com", "password", 1000, "Administrador", "(011)4200-9536"));

    // Doctores
    doctorBO.save(new Doctor("doctor1@gmail.com", "password", 100, "Juan Perez", "(011)4138-9536", consultorio1, new BigDecimal(100.0)));
    doctorBO.save(new Doctor("doctor2@gmail.com", "password", 101, "Marcos Donato", "(011)4974-9279", consultorio1, new BigDecimal(80.0)));
    doctorBO.save(new Doctor("doctor3@gmail.com", "password", 102, "Valeria Gurzi", "(011)4875-8669", consultorio1, new BigDecimal(120.0)));
    doctorBO.save(new Doctor("doctor4@gmail.com", "password", 103, "Miguel Lombardo", "(011)4682-3776", consultorio2, new BigDecimal(100.0)));
    doctorBO.save(new Doctor("doctor5@gmail.com", "password", 104, "Maria Suarez", "(011)4955-7607", consultorio2, new BigDecimal(80.0)));

    // Pacientes
    pacienteBO.save(new Paciente("paciente1@gmail.com", "password", 39538686, "Hernan Miguel", "(011)4565-3723", obraSocial1));
    pacienteBO.save(new Paciente("paciente2@gmail.com", "password", 22923485, "Anas Checa", "(011)4184-6264", obraSocial1));
    pacienteBO.save(new Paciente("paciente3@gmail.com", "password", 93640290, "Asier Sevillano", "(011)4618-3339", obraSocial2));
    pacienteBO.save(new Paciente("paciente4@gmail.com", "password", 54869297, "Juan Montaño", "(011)4623-8693", obraSocial2));
    pacienteBO.save(new Paciente("paciente5@gmail.com", "password", 67354156, "Adolfo Villena", "(011)4004-6411", obraSocial2));
    pacienteBO.save(new Paciente("paciente6@gmail.com", "password", 45498141, "Isidro Palomares", "(011)4977-3691", obraSocial3));
    pacienteBO.save(new Paciente("paciente7@gmail.com", "password", 85621904, "Carlos Alberto Pico", "(011)4640-5741", obraSocial3));
    pacienteBO.save(new Paciente("paciente8@gmail.com", "password", 50072015, "Manuel Angel Paredes", "(011)4208-7194", obraSocial3));
    pacienteBO.save(new Paciente("paciente9@gmail.com", "password", 67278824, "Jose Ramon Toro", "(011)4156-4255", obraSocial1));
    pacienteBO.save(new Paciente("paciente10@gmail.com", "password", 38748791, "Robert Salgado", "(011)4517-2594", obraSocial1));

    // Crear turnos para los próximos 60 días
    Random rand = new Random();
    ArrayList<Paciente> pacientes = pacienteBO.getAll();
    ArrayList<Doctor> doctores = doctorBO.getAll();
    LocalDateTime fechaLaboral = ar.com.rorra.util.Dates.getNextWorkDay();
    for (int i = 0; i <= 60; i++) {
      // Crear 10 a 20 turnos por dia
      int cantidadTurnos = rand.nextInt(10) + 10;
      for (int j = 0; j < cantidadTurnos; j++) {
        boolean stored;
        do {
          // Primero obtener la fecha y hora del turno
          LocalDateTime fecha = LocalDateTime.from(fechaLaboral.plusDays(i));
          int hours = rand.nextInt(9) + 9;
          fecha = fecha.withHour(hours);

          // Crear el turno con un doctor y paciente aleatorios
          try {
            Turno turno = new Turno();
            Doctor doctor = doctores.get(rand.nextInt(doctores.size()));
            Paciente paciente = pacientes.get(rand.nextInt(pacientes.size()));
            turno.setDoctor(doctor);
            turno.setPaciente(paciente);
            turno.setFecha(fecha);
            turnoBO.save(turno);
            stored = true;
          } catch (BOException ex) {
            System.out.println("Turno invalido: " + ex.getMessage());
            stored = false;
          }
        } while (!stored);
      }
    }
  }

  /**
   * Elimina las tablas creadas para el funcionamiento del sistema
   *
   * @throws DBException
   */
  public void eliminarTablas() throws DBException {
    Connection c = DBConnection.getInstance().getConnection();
    ArrayList<String> list = new ArrayList<>();
    list.add("DROP TABLE IF EXISTS turnos");
    list.add("DROP TABLE IF EXISTS pacientes");
    list.add("DROP TABLE IF EXISTS doctores");
    list.add("DROP TABLE IF EXISTS obrasSociales");
    list.add("DROP TABLE IF EXISTS consultorios");
    list.add("DROP TABLE IF EXISTS administradores");

    try {
      Statement s = c.createStatement();
      for (String sql : list) {
        s.execute(sql);
      }
    } catch (SQLException ex) {
      try {
        c.rollback();
      } catch (SQLException ex1) {
        throw new DBException(
          DBExceptionType.TRANSACTION_ERROR,
          "Error en rollback de eliminación de las tablas: " + ex1.getMessage()
        );
      }
      throw new DBException(
        DBExceptionType.TRANSACTION_ERROR,
        "Error al eliminar las tablas para correr el sistema: " + ex.getMessage()
      );
    } finally {
      try {
        c.close();
      } catch (SQLException ex) {
        throw new DBException(
          DBExceptionType.CONNECTION_ERROR,
          "Error al cerrar la conexión de la base de datos: " + ex.getMessage()
        );
      }
    }
  }

  public static void main(String[] args) throws BOException {
    System.out.println("1 - Eliminar y crear tablas, insertar datos de prueba");
    System.out.println("2 - Crear tablas");
    System.out.println("3 - Insertar datos de pruebas");
    System.out.println("4 - Eliminar tablas");
    System.out.print("Digite su opción: ");

    Scanner sc = new Scanner(System.in);
    int opcion = sc.nextInt();

    switch (opcion) {
      case 1:
        System.out.println("Eliminando tablas...");
        (new SchemaTasks()).eliminarTablas();
        System.out.println("Creando tablas...");
        (new SchemaTasks()).crearTablas();
        System.out.println("Insertando datos de prueba...");
        (new SchemaTasks()).insertarDatosDePrueba();
      case 2:
        System.out.println("Creando tablas...");
        (new SchemaTasks()).crearTablas();
        break;
      case 3:
        System.out.println("Insertando datos de prueba...");
        (new SchemaTasks()).insertarDatosDePrueba();
        break;
      case 4:
        System.out.println("Eliminando tablas...");
        (new SchemaTasks()).eliminarTablas();
        break;
    }
  }
}
