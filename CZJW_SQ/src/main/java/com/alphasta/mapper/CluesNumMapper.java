package com.alphasta.mapper;

import com.alphasta.model.CluesNum;

public interface CluesNumMapper {
	public int addCluesNum(CluesNum cluesNum);
	public int updateCluesNum(CluesNum cluesNum);
	public CluesNum findCluesNumBySuffixAndTime(CluesNum cluesNum);
	public CluesNum findCluesNumBySuffix(CluesNum cluesNum);
}
