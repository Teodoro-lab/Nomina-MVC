package org.example.nominamvc.model;

public class Administrador extends Empleado {
    private final static double porcentajeCompensacionMayor80Horas = .30;
    private final static double porcentajeCompensacionMenor80Horas = .20;
    private int level = 1;

     public Administrador(int id, String nombre, String apellido, String email, double salario ) {
        super(id, nombre, apellido, email, salario);
    }
    
    public Administrador(String nombre, String apellido, String email, double salario) {
        super(nombre, apellido, email, salario);
    }

    @Override
    public String toString() {
        double porcentajeCompensacion = getNumHorasTrabajadas() >= 80? 
                    porcentajeCompensacionMayor80Horas:
                    porcentajeCompensacionMenor80Horas;
        return "Puesto: Administrador\n" + 
                super.toString() +
                "\nPorcentaje: " + porcentajeCompensacion;
    }

    @Override
    public double calcularSalario(int numHorasTrabajadas, double tarifa) {
        this.setNumHorasTrabajadas(numHorasTrabajadas);
    
        double porcentajeCompensacion = getNumHorasTrabajadas() >= 80? 
                    porcentajeCompensacionMayor80Horas:
                    porcentajeCompensacionMenor80Horas;

        super.calcularSalario(numHorasTrabajadas, tarifa);
        double salarioConCompensacion = this.getSalario() * (1 + porcentajeCompensacion);
        setSalario(salarioConCompensacion);
        return salarioConCompensacion;
    }
}