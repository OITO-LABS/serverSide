
package com.asset.management.VO;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ListPageData {

	private List<ListReimbursementVo> reimbursementDetails;
	private Pageable pageable;
	private int number;
	private int numberOfElements;
	private int size;
	private Long totalElements;
	private int totalPages;

}

