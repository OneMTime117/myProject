package com.yh.common.demo.lombok;

import com.yh.common.domain.dto.IdDTO;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Data
@Builder
public final class LombokDemoEntity extends IdDTO {

	@ToString.Include
	private Integer age;

	private String name;

	private final String ageInt;

	private static  String nameSearch = null;

	String hh;

	public static void main(String[] args) {
		LombokDemoEntity build = LombokDemoEntity.builder()
				.age(1)
				.name("2")
				.ageInt("")
				.build();
	}

}
