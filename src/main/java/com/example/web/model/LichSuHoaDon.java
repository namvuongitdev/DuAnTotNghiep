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
import org.hibernate.annotations.Nationalized;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "lich_su_hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LichSuHoaDon {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @Nationalized
    @Column(name = "nguoi_thao_tac")
    private String nguoiThaoTac;

    @Nationalized
    @Column(name = "thao_tac")
    private String thaoTac;

    @Column(name = "ngay_thao_tac")
    private Date ngayThaoTac;

    @Nationalized
    @Column(name = "ghi_chu" , length = 3000)
    private String ghiChu;

}
