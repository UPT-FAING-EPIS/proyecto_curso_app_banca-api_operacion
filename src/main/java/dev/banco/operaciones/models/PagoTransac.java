package dev.banco.operaciones.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PagoTransac")
public class PagoTransac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codPagoTransac;

    private String nroTarjeta;
    private Double montoPagado;
    private String fechaTransac;
    private String horaTransac;
    private String tipoTransac;

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public Integer getCodPagoTransac() {
        return codPagoTransac;
    }

    public void setCodPagoTransac(Integer codPagoTransac) {
        this.codPagoTransac = codPagoTransac;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getFechaTransac() {
        return fechaTransac;
    }

    public void setFechaTransac(String fechaTransac) {
        this.fechaTransac = fechaTransac;
    }

    public String getHoraTransac() {
        return horaTransac;
    }

    public void setHoraTransac(String horaTransac) {
        this.horaTransac = horaTransac;
    }

    public String getTipoTransac() {
        return tipoTransac;
    }

    public void setTipoTransac(String tipoTransac) {
        this.tipoTransac = tipoTransac;
    }
}
