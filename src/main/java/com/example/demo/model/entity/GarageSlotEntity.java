package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "garageSlot")
public class GarageSlotEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long garageSlotId;
    private Integer slotNumber;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    private TicketEntity ticket;
}
