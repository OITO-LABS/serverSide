
package com.asset.management.dao;

import com.asset.management.VO.ReimbursementTrackVo;
import com.asset.management.VO.ResponseVO;

public interface Validator {

	ResponseVO validate(ReimbursementTrackVo data);
}
