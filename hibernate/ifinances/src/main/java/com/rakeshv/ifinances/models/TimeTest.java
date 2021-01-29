package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "time_test")
public class TimeTest {
    @Id
    @GeneratedValue
    @Column(name = "TIME_TEST_ID")
    private long timeTestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETIME_COLUMN")
    private Date datetimeColumn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP_COLUMN")
    private Date timestampColumn;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_COLUMN")
    private Date dateColumn;

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_COLUMN")
    private Date timeColumn;

    @Column(name = "SQL_DATETIME_COLUMN")
    private Timestamp sqlDateTimeColumn;

    @Column(name = "SQL_TIMESTAMP_COLUMN")
    private Timestamp sqlTimestampColumn;

    @Column(name = "SQL_DATE_COLUMN")
    private java.sql.Date sqlDateColumn;

    @Column(name = "SQL_TIME_COLUMN")
    private Time sqlTimeColumn;
}
