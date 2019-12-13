
package com.asset.management.VO;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageData {
	private List<ResultSetVO> resultSet;
	private Pageable pageable;
	private int number;
	private int numberOfElements;
	private int size;
	private Long totalElements;
	private int totalPages;
}

