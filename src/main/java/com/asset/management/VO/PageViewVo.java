
package com.asset.management.VO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageViewVo {
	Date dateFrom;
	Date dateTo;
	int page;
	int size;
	String empNo;
	String sortKey;
	String sortOrder;
}

