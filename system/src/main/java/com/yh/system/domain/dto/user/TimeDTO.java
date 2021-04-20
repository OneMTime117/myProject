package com.yh.system.domain.dto.user;

import lombok.Data;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class TimeDTO {
	private LocalTime localTime;

	private LocalDate localDate;

	private LocalDateTime localDateTime;

	private Date date;

	public static void main(String[] args) throws IOException {


	}

}