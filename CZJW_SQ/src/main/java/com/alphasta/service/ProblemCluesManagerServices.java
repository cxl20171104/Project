package com.alphasta.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.alphasta.commons.result.ExcelException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.en.Stage;
import com.alphasta.model.Accessories;
import com.alphasta.model.Back;
import com.alphasta.model.BootstapTree;
import com.alphasta.model.Delete_Clues;
import com.alphasta.model.ExcelPojo;
import com.alphasta.model.Group;
import com.alphasta.model.LegalAct;
import com.alphasta.model.Lien;
import com.alphasta.model.ListParam;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.TransModel;
import com.alphasta.model.User;
import com.alphasta.model.ZyViolation;

public interface ProblemCluesManagerServices {
	
	/**
	 * 根据ID查询线索
	 */
	ProblemClues findProblemCluesById(String id);
	/**
	 * 案件线索添加
	 * @return 
	 */
	void insertProblemClues(ProblemClues problemClues);
	/**
	 * 案件线索更新
	 */
	int update(ProblemClues problemClues);
	/**
	 * 案件线索删除
	 */
	void deleteByids(Delete_Clues delete_Clues);
	//根据被反映人姓名和被反映人职级  查询库中是否存在相同的数据 且 重复件 
			//duplicateId重复情况（如果重复了，保存重复线索的id）
			//duplicate  是否重复（人工判定不重复0，人工判定重复1，系统判定不重复2，系统判定重复3）
            void   isDoubleXin (ProblemClues problemClues);
			/**
			 * 
			 * @param file
			 * @return
			 * @throws ExcelException
			 */
            Object imporByExcel(MultipartFile file,HttpServletRequest req) throws ExcelException;
			/**
			 * 
			 * @param file
			 * @param filePath
			 * @return
			 */
			/**
			 * 判断当前用户在到期案件界可以显示那些按钮
			 */
             String buttonHas();
			 /**
			  * 编辑线索业务
			  */
              int updateMainService(ProblemClues problemClues );
			 /**
			  * 
			  * @param problemClues
			  * @param organId
			  */
			 List<ExcelPojo>excelData(String sql);
			/**
			 * 不同科室打开不同界面
			 */
			 String whichPage(String pageName,ProblemClues problemClues) ;
			/**
			 * 根据CASEID查询线索
			 */
			ProblemClues findProblemCluesByCluesNum(String cluesNum);

			List<ProblemClues>  findProblemCluesRepeat(ProblemClues problemClues2);
			/**
			 * 下拉列表多选
			 */
			List<BootstapTree>GetExpandJson(String id);
			/**
			 * 案件签收
			 */
			boolean updateProblemCluesService(ProblemClues problemClues);
			/**
			 * 
			 *添加涉法行为表
			 */
		    String laAddLegalActService(List<LegalAct> legalAct, String reflectedPersonId);
			/**
			 * 
			 *添加违纪行为表
			 */
			String laAddZyViolationService(ZyViolation zyViolation, String reflectedPersonId);
			
			/**
			 * 返回当前用户的organId
			 */
		    String getCurrentOrganId();
			/**
			 * 是否存在重复的被反映人 用于重复件处理
			 * @param reflectedPerson
			 * @return
			 */
			 boolean has_reapeat(ReflectedPerson reflectedPerson);
			
			 List<ProblemClues>find_repeat(ReflectedPerson reflectedPerson);
			
			 String get_organId_all();
			
			 User getCurrentUser();
			/**
			 * 退回 撤回
			 * @param id
			 * @param reason
			 * @return
			 */
			 boolean backService(Back back);
			/**
			 * 撤回退回申请
			 */
			 boolean back_please_service(Back back);
			/**
			 * 撤回的详情
			 */
			 Back call_back_detail(Back back);
			
			 Delete_Clues del_reason(Delete_Clues delete_Clues);
}
