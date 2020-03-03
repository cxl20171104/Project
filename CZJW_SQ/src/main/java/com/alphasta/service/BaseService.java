package com.alphasta.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.GsonUtil;
import com.alphasta.commons.utils.HttpClientUtil;
import com.alphasta.en.Stage;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.mapper.GroupMapper;
import com.alphasta.mapper.LienMapper;
import com.alphasta.mapper.MakedMapper;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.ReflectingPersonMapper;
import com.alphasta.mapper.StaticticsMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.Group;
import com.alphasta.model.LegalAct;
import com.alphasta.model.Lien;
import com.alphasta.model.Maked;
import com.alphasta.model.MapToBean;
import com.alphasta.model.Measures;
import com.alphasta.model.Organization;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
import com.alphasta.model.SlsResult;
import com.alphasta.model.TransModel;
import com.alphasta.model.User;
import com.alphasta.model.ZyViolation;

@Service
public abstract class BaseService {
	@Autowired
	UserService  userService;
	@Autowired
	private StaticticsMapper stm;
	@Autowired
	private ProblemCluesMapper problemCluesMapper;
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private ReflectedPersonMapper reflectedPersonMapper;
	@Autowired
	private ReflectingPersonMapper reflectingPersonMapper;
	@Autowired
	private AccessoriesMapper accessoriesMapper;
	@Autowired
	private ProgressMapper progressMapper;
	@Autowired
	private LienMapper lienMapper;
	@Autowired
	private OrganizationMapper organization;
	@Autowired
	private MakedMapper makedMapper;
	public static SimpleDateFormat sdf_yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat sdf_yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");
	public User getCurrentUser() {
		 ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	     User currentUser = userService.findUserById(user.id);
	     return currentUser;
	}
	public String getCurrentOrganId() {
		 ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	     User currentUser = userService.findUserById(user.id);
		return currentUser.getOrganizationId().toString();
	}
	 public Long getCurrentUserId() {
	    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	    	return user.id;
    }
	 
	 
	 //获取市案管室ip
	 public String getCip() {
		 Organization organ = organization.findOrganizationById(53l);
		 return organ.getAddress();
	 }
	 
		public String scsCHAddGroup(List<Group> group, String problemCluesId) {
			for(int i=0;i<group.size();i++) {
				group.get(i).setCauseId(problemCluesId);
				group.get(i).setId(GetIdUtil.getId());
				groupMapper.addGroup(group.get(i));
			}
			return "0";
		}
        /**
         * 向外发送消息
         * @param data
         * @return
         */
		public boolean messageOut(TransModel data) {
			// 将数据转换成json
		    String gsonString = GsonUtil.GsonString(data);
		    HttpClientUtil.send(gsonString, data.getIp());
			return true;
		}
        /**此方法处理反馈案件 **/
		 public boolean makeData(TransModel data) {
         boolean result=true;
         /**
          * 市局向县局分办案件时
          */
         if(data.getOption()==Param.insert) {
         	if(data.getProblemClues()!=null) {
         		problemCluesMapper.insertProblemClues(data.getProblemClues());
         	}
         	if(data.getReflectedPerson()!=null) {
         		reflectedPersonMapper.addReflectedPerson(data.getReflectedPerson());
         	}
         	if(data.getReflectingPerson()!=null) {
         		reflectingPersonMapper.addReflectingPerson(data.getReflectingPerson());
         	}
         	if(data.getAccessories()!=null&&!data.getAccessories().isEmpty()) {
         		for(Accessories acc:data.getAccessories()) {
         			accessoriesMapper.insertSelective(acc);
         		}
         		
         	}
         
         }
         /**
          * 县局反馈案件办理进展时（返回的信息不用修改organId和fromId）
          */
         if(data.getOption()==Param.update) {
         	if(data.getProblemClues()!=null) {
         		data.getProblemClues().setOrganId(null);
         		data.getProblemClues().setFromId(null);
         		problemCluesMapper.update(data.getProblemClues());
         	}
         	if(data.getReflectedPerson()!=null) {
         		reflectedPersonMapper.updateReflectedPerson(data.getReflectedPerson());
         	}
         	if(data.getReflectingPerson()!=null) {
         		reflectingPersonMapper.updateReflectingPerson(data.getReflectingPerson());
         	}
         }
     	if(data.getProgress()!=null&&!data.getProgress().isEmpty()) {
     		Progress last = progressMapper.findLastProgress(data.getProgress().get(0).getCauseId());
     		String lastId=null;
     		if(last!=null) {
     			lastId=last.getId();
     		}
     		for(Progress p:data.getProgress()) {
     			p.setLastPoint(lastId);
     			progressMapper.addProgress(p);
     			lastId=p.getId();
     		}
     	}
     	if(data.getGroup()!=null&&!data.getGroup().isEmpty()) {
     		for(Group g:data.getGroup()) {
     			groupMapper.addGroup(g);
     		}
     	}
     	if(data.getLien()!=null&&!data.getLien().isEmpty()) {
     		for(Lien l:data.getLien()) {
     			lienMapper.addLien(l);
     		}
     	}
			return result;
		}
        /**
         * 市区向县区分办案件时
         * @param problemClues
         * @return
         */
		public boolean MakeOutData(ProblemClues problemClues) {
			String outerIp=problemClues.getIsXf();
			boolean result=true;
			//向县区分办案件
			TransModel tr=new TransModel();
			problemClues.setIsXf(Param.ip);
			problemClues.setFinalState("0");
			problemClues.setOrganId(getCurrentOrganId());
	    	problemClues.setIsGet("0");
	    	problemClues.setFromId(getCurrentOrganId());
			System.out.println(problemClues.getFinalState());
	    	tr.setProblemClues(problemClues);
	    	tr.setReflectedPerson(problemClues.getReflectedPerson());
	    	tr.setReflectingPerson(problemClues.getReflectingPerson());
	    	List<Progress>progre=new ArrayList<Progress>();
	    	Progress pr=new Progress();
	    	pr.setId(GetIdUtil.getId());
	    	pr.setCauseId(problemClues.getId());
	    	pr.setDetail("上级分办");
	    	pr.setPointName(Param.agssl_name);
	    	pr.setPointValue(Param.agssl_value);
	    	progre.add(pr);
	    	tr.setProgress(progre);
	    	tr.setOption(Param.insert);
	    	tr.setIp(outerIp);
	        messageOut(tr);
	        return result;
		}
		public boolean makeTaData(ProblemClues problemClues,List<Progress>progress) {
			String outerIp=problemClues.getIsXf();
			boolean result=true;
			//向县区分办案件
			TransModel tr=new TransModel();
			problemClues.setIsXf(Param.ip);
	    	problemClues.setIsGet("1");
	    	tr.setProblemClues(problemClues);
	    	tr.setProgress(progress);
	    	tr.setOption(Param.insert);
	    	tr.setIp(outerIp);
	        messageOut(tr);
	        return result;
		}
		//案件反馈
	    public boolean makeFkData(ProblemClues problemClues,List<Progress>progress,List<Group>group,List<Lien>lien) {
	    	boolean result=true;
	    	TransModel tr=new TransModel();
	    	tr.setProblemClues(problemClues);
	    	if(progress!=null&&!progress.isEmpty()) {
	    		tr.setProgress(progress);
	    	}
	    	if(group!=null&&!group.isEmpty()) {
	    		tr.setGroup(group);
	    	}
	    	if(problemClues!=null&&problemClues.getReflectedPerson()!=null) {
	    		
	    		tr.setReflectedPerson(problemClues.getReflectedPerson());
	    	}
	    	if(problemClues!=null&&problemClues.getReflectedPerson()!=null) {
	    		tr.setReflectingPerson(problemClues.getReflectingPerson());
	    	}
	    	if(lien!=null&&!lien.isEmpty()) {
	    		tr.setLien(lien);
	    	}
	    	String outerIp=problemClues.getIsXf();
	    	tr.setIp(outerIp);
	    	tr.setOption(Param.update);
	    	messageOut(tr);
	    	return  result;
	    }
	    /**
	     * 上报案件（将这条案件的所有信息提交）
	     * @return
	     */
	    public boolean uploadData(
	    		//线索
	    		ProblemClues problemClues,
	    		//小组成员
	    		List<Group>group,
	    		//留置信息
	    		List<Lien>lien,
	    		//组织纪律
	    		List<ZyViolation> zy,
	    		//审理室移送
	    		List<SlsResult> slsResult,
	    		//犯罪信息
	    		List<LegalAct> legalAct,
	    		//附件
	    		List<Accessories> acc,
	    		//组织措施
	    		List<Measures> measures,
	    		//处置方式
	    		List<Punishment> punishment) {
	    	    //加入到
	    	    TransModel tr=new TransModel();
	    	    if(problemClues!=null) {
	    	    	problemClues.setFinalState("0");
	    	    	problemClues.setIsGet("1");
	    	    	problemClues.setIsXf("upload_"+Param.ip);
	    	    	tr.setProblemClues(problemClues);
	    	    	if(problemClues.getReflectedPerson()!=null) {
	    	    		tr.setReflectedPerson(problemClues.getReflectedPerson());
	    	    	}
	    	    	if(problemClues.getReflectedPerson()!=null) {
	    	    		tr.setReflectingPerson(problemClues.getReflectingPerson());
	    	    	}
	    	    }
	    	    if(group!=null&&!group.isEmpty()) {
	    	    	tr.setGroup(group);
	    	    }
	    	    if(lien!=null&&!lien.isEmpty()) {
	    	    	tr.setLien(lien);
	    	    }
	    	    if(punishment!=null) {
	    	    	tr.setPunishment(punishment);
	    	    }
	    	    if(slsResult!=null&&!slsResult.isEmpty()) {
	    	    	tr.setSlsResult(slsResult);
	    	    }
	    	    if(legalAct!=null&&!legalAct.isEmpty()) {
	    	    	tr.setLegalAct(legalAct);
	    	    }
	    	    if(acc!=null&&!acc.isEmpty()) {
	    	    	tr.setAccessories(acc);
	    	    }
	    	    if(measures!=null&&!measures.isEmpty()) {
	    	    	tr.setMeasures(measures);
	    	    }
	    	    tr.setOption(Param.insert);
	    	    tr.setIp(getCip());
	    	    boolean messageOut = messageOut(tr);
	    	    return messageOut;
	    }
	    
	    
		  /**
		 * 消息提醒:消息格式   消息内容_organId+"X"
		 */
		public void  sendMessage(String msg,HttpServletRequest request) {
			     
					//============================================向前台页面发送消息=======================================
		}
		
		//添加已办件
		public void addMaked(String cluseId) {
			//添加已办案件
			//如果已经办理过该案件则不用再添加
			Maked maked=new Maked(GetIdUtil.getId(),getCurrentOrganId(),cluseId);
			List<Maked> makedl = makedMapper.findMakedByCidAndOrganId(maked);
			if(makedl==null||makedl.isEmpty()) {
				makedMapper.addMaked(maked);
			}
			
			
		}
		
}
