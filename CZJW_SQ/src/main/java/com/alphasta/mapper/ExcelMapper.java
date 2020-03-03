package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.ExcelPojo;

public interface ExcelMapper {
	List<ExcelPojo> excelData(String sql);
	int insertData(String sql);
}
