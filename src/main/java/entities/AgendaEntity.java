package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "AGENDA", schema = "ROOT")
public class AgendaEntity {
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "TELEF")
    private Object telef;
    @Id
    private Long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getTelef() {
        return telef;
    }

    public void setTelef(Object telef) {
        this.telef = telef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaEntity that = (AgendaEntity) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (telef != null ? !telef.equals(that.telef) : that.telef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (telef != null ? telef.hashCode() : 0);
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
