package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;

@Entity
@Table(name = "anh")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Anh {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ten")
    private String ten;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;

}
