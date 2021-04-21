package com.training.platform.entities;

import lombok.Data;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="shops")
@Data
public class Shop implements Serializable {
    //pk
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;


    //FetchType มี 2 แบบ
    //FetchType.LAZY เป็นไปตามความต้องการ (เช่นเมื่อเราต้องการข้อมูล)
    //FetchType.EAGER เป็นการดึงข้อมูลล่วงหน้า (เช่นก่อนที่ความต้องการของเราจะมาถึง)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id"))
    private User user;

}

