package com.mas.pjatk.masfinalproject.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Visit {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal charge;
    private boolean deleted;
    private boolean confirmed;
    private boolean cancelled;
}
