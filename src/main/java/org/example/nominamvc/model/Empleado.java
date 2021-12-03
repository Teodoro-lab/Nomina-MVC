package org.example.nominamvc.model;

public abstract class Empleado implements Comparable<Empleado>{
    private int id;
    private String nombre;
    private String apellido;
    private double salario;
    private String email;
    private double numHorasTrabajadas;
    private int level;

    public Empleado(int id, String nombre, String apellido, String email, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;
        this.salario = salario;
    }
    
    public Empleado(String nombre, String apellido, String email, double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public double getNumHorasTrabajadas() {
        return numHorasTrabajadas;
    }
    
    public void setNumHorasTrabajadas(double numHorasTrabajadas) {
        this.numHorasTrabajadas = numHorasTrabajadas;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public double calcularSalario(int numHorasTrabajadas, double tarifa) {
        salario = numHorasTrabajadas * tarifa;
        return salario;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nSalario: " + salario;
    }

    @Override
    public int compareTo(Empleado o) {
        if(this.getLevel() > o.getLevel()) {
            return -1;
        } else if (this.getLevel() == o.getLevel()) {
            if (this.getSalario() > o.getSalario()) {
                return -1;
            }
            else if (this.getSalario() == o.getSalario()) {
                return 0;
            }
        }
        return 1;
    }
}