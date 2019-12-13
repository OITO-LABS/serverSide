
package com.asset.management.dao.utility;

import com.asset.management.VO.ResultSetVO;

public class AssetQuery {
	private static final String SEARCH_TERM = "searchTerm";
	StringBuilder fromClause = new StringBuilder();
	StringBuilder whereClause = new StringBuilder();
	StringBuilder orderByClause = new StringBuilder();
	ResultSetVO data = null;
}
