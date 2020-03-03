package com.alphasta.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.en.State;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.mapper.MeasuresMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.SlsResultMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.ListParam;
import com.alphasta.model.Measures;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.SlsResult;
import com.alphasta.service.BaseService;
import com.alphasta.service.SlsService;

@Service
public class SlsServiceImpl extends BaseService implements SlsService{
	@Autowired
	private ProgressMapper progressMapper;
	@Autowired
	private ProblemCluesMapper problemCluesMapper;
	@Autowired
	private ReflectedPersonMapper reflectedPersonMapper;
	@Autowired
	private AccessoriesMapper accessoriesMapper;
	@Autowired
	private MeasuresMapper measuresMapper;
	@Autowired
	private SlsResultMapper slsResultMapper;
	
    /**
     * 审理室案件处理情况
     */
	@Override
	public boolean sls_clues(ListParam listParam,HttpServletRequest request) {
		// TODO Auto-generated method stub
		ReflectedPerson reflectedPerson = listParam.getReflectedPerson();
		ProblemClues problemClues = listParam.getProblemClues();
		if ( StringUtils.isEmpty(reflectedPerson.getId()) ) {
			ProblemClues forRef = problemCluesMapper.findProblemCluesById(problemClues.getId());
			reflectedPerson.setId(forRef.getReflectedPerson().getId());
			
		}
		List<Progress> progress = listParam.getProgress();
		Measures measures = listParam.getMeasures();
		SlsResult slsResult = listParam.getSlsResult();
	    String id2="";		   
	    Progress findLastProgress = progressMapper.findLastProgress(problemClues.getId());		    
	    if(findLastProgress.getId()!=null&&!"".equals(findLastProgress.getId())) {
		    id2=findLastProgress.getId();
	    }
		for(int i=0;i<progress.size();i++) {
			    boolean canStore=false;
			    
			    if(progress.get(i).getPointName()!=null) {
			    	//审理室受理时间 这条进度如果想添加  时间就必须存在
			    	if(progress.get(i).getPointName().equals("审理室受理时间")&&StringUtils.isNoneEmpty(progress.get(i).getTimeForday())) {
			    		canStore=true;
			    	}
			    	//以此类推
			    	if(progress.get(i).getPointName().equals("审理室审结时间")&&StringUtils.isNoneEmpty(progress.get(i).getTimeForday())) {
			    		canStore=true;
			    	}
			    	//审理室市委常委会
			    	if(progress.get(i).getPointName().equals("审理室市委常委会")&&StringUtils.isNoneEmpty(progress.get(i).getTimeForday())) {
			    		canStore=true;
			    	}
			    	//
			    	if(progress.get(i).getPointName().equals("审理室纪委监委会")&&StringUtils.isNoneEmpty(progress.get(i).getTimeForday())) {
			    		canStore=true;
			    		
			    	}
			    	if(progress.get(i).getPointName().equals("审理室处理结果")&&StringUtils.isNoneEmpty(progress.get(i).getTimeForday())) {
			    		canStore=true;
			    	}
			    }
			    
			    
			    if(canStore) {
			    	//进度的保存
					String id=GetIdUtil.getId();
					//存储进度
					//创建id				
					progress.get(i).setId(id);
					if(!"".equals(id2)) {
						progress.get(i).setLastPoint(id2);		
					}
					if("".equals(progress.get(i).getTimeForday())) {
						progress.get(i).setTimeForday(null);		
					}
				    progress.get(i).setOrganId(getCurrentOrganId());
				    progress.get(i).setUser(getCurrentUser());
				    progress.get(i).setCauseId(problemClues.getId());
				    progress.get(i).setState(problemClues.getState());
					progressMapper.addProgress(progress.get(i));
					id2=id;
			    }
		}
		//案件审理结束  将审理室到期设置为空
	    problemClues.setExpireTime("del"); 
	    //案件审理结束  案件了结  留党察看到期
	    problemClues.setFinalState("-1");
	    if(reflectedPerson.getStayTermEndTime()!=null&&!"".equals(reflectedPerson.getStayTermEndTime())) {
	    	if(problemClues.getState().indexOf(State.TJ.toString())!=-1) {
	    		problemClues.setExpireTime("留党察看_"+reflectedPerson.getStayTermEndTime());
	    	}
	    	
	    }
	    //处分执行完毕时间  处分生效时间不为空
	    if(reflectedPerson.getTakeEffectTime()!=null&&!"".equals(reflectedPerson.getTakeEffectTime())) {
	    	//处分执行完毕时间为空
	    	if(StringUtils.isEmpty(slsResult.getTakeEffectEndTime())) {
	    		if(problemClues.getState().indexOf(State.TJ.toString())!=-1) {
	    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    			Date parse;
					try {
						parse = sdf.parse(reflectedPerson.getTakeEffectTime());
						problemClues.setExpireTime(Param.cfjdName+"_"+DateUtil.getDateFromNow(parse,Param.cfjdTime));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    	}
	    	}
	    	
	    	
	    }
	    problemCluesMapper.update(problemClues);
	    reflectedPersonMapper.updateReflectedPerson(reflectedPerson);
	    if(measures!=null&&!measures.equals("")) {
	    	measures.setId(GetIdUtil.getId());
	    	measures.setMeaState("sls");
	    	measures.setReflectedId(reflectedPerson.getId());
	    	measuresMapper.addMeasures(measures);
	    }
	    if(slsResult!=null&&!slsResult.equals("")) {
	    	slsResult.setId(GetIdUtil.getId());
	    	slsResult.setReflectedId(reflectedPerson.getId());
	    	slsResultMapper.addSlsResult(slsResult);
	    }
	    
	    /*****************************/
	    addMaked(problemClues.getId());
	    /*****************************/
	    
		if(problemClues.getState().indexOf(State.TJ.toString())!=-1) {
		//反馈信息
		problemClues.setReflectedPerson(reflectedPerson);
		//给案管室发送一条消息
		sendMessage("一条案件了结_"+Param.agsId+"X", request);
		}
		return true;
	}
	
		
	@Override
	public Map<String, Object> sls_data(String id,String ip) {
		if(StringUtils.isNoneEmpty(ip)) {
			DataSourceContextHolder.setDbType(ip);
		}
		ProblemClues findProblemCluesById = problemCluesMapper.findProblemCluesById(id);
		Map<String,Object>result=new HashMap<String,Object>();
		if(!StringUtils.isEmpty(id)) {
			
		
		List<Accessories> slbg =null;
		List<Accessories> cfjd =null;
		List<Accessories> yjs =null;
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>accMap=new HashMap<String,Object>();
		
		Accessories acc=new Accessories();
		acc.setCaseId(id);
		accMap.put("Accessories", acc);
		//审理室
			map.put("pointValues", "'22','14','13','27','28','26'");
			//审理报告
			accMap.put("type", "审理报告");
			slbg = accessoriesMapper.getAccByMap(accMap);
			if(slbg!=null&&!slbg.isEmpty()) {
				result.put("slbg", slbg);
			}
			
			//处分决定
			accMap.put("type", "处分决定");
			cfjd = accessoriesMapper.getAccByMap(accMap);
			if(cfjd!=null&&!cfjd.isEmpty()) {
				result.put("cfjd", cfjd);
			}
			
			//起诉意见书
			accMap.put("type", "起诉意见书");
			yjs = accessoriesMapper.getAccByMap(accMap);
			if(yjs!=null&&!yjs.isEmpty()) {
			result.put("yjs", yjs);
			}
		map.put("causeId", id);
		List<Progress> progressByMap = progressMapper.getProgressByMap(map);
		//审理室处置建议
		if(!progressByMap.isEmpty()) {
			String[]ids = null;
			for (Progress progress : progressByMap) {
				if("22".equals(progress.getPointValue())&&(progress.getDetail()!=null&&!"".equals(progress.getDetail()))) {
					ids=progress.getDetail().split(",");
					result.put("sls_czjy", ids);
				}
			}
		}
		//审理室处理结果
		if(!progressByMap.isEmpty()) {
			String[]ids = null;
			for (Progress progress : progressByMap) {
				if("14".equals(progress.getPointValue())&&(progress.getDetail()!=null&&!"".equals(progress.getDetail()))) {
					ids=progress.getDetail().split(",");
					result.put("sls_czjg", ids);
				}
			}
	
		}
		
		Measures measures = new Measures();
		measures.setMeaState("sls");
		measures.setReflectedId(findProblemCluesById.getReflectedPerson().getId());
		Measures findMeasures = measuresMapper.findMeasures(measures);
		if(findMeasures!=null) {
			String[]ids = null;
			ids=findMeasures.getMeaResult().split(",");
			result.put("sls_zzcs", ids);
		}
		List<SlsResult> findSlsResultByRid = slsResultMapper.findSlsResultByRid(findProblemCluesById.getReflectedPerson().getId());
		if(findSlsResultByRid.size()>0) {
			result.put("slsResult", findSlsResultByRid.get(0));			
		}		
		result.put("progress", progressByMap);
		}
		//切换回原来的数据源
		if(StringUtils.isNotEmpty(ip)) {
			DataSourceContextHolder.setDbType("master_dataSource");
		}
		return result;
	}
	
	
}
