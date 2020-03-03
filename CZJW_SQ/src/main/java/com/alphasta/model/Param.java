package com.alphasta.model;

import java.util.ResourceBundle;

import com.alphasta.commons.utils.ResourceBundleUtil;

public class Param {

	/**
	 * 私有构造器，防止常量类被实例化
	 */
	private Param() {

	}
	/**
	 * 资源文件.
	 */
	public static final String BUNDLE_KEY = "config.application";
	/**
	 * 资源绑定对象
	 */
	public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_KEY);
	/**
	 * 流程信息
	 */
	public static final String agssl_value="0";
	public static final String agssl_name="案管室受理";//*****用于统计******//
	
	public static final String dfssl_value="0";
	public static final String dfssl_name="党风室受理";
	
	public static final String gbjdsl_value="0";
	public static final String gbjdsl_name="干部监督室受理";
	
	//其他受理
	public static final String qt_value="0";
	public static final String qt_name="其他受理";
	//***********************************************************************************************
	public static final String agsfb_value="1";    //*****用于统计******//
	public static final String agsfb_name="案管室分办";
	
	public static final String dfsfb_value="1";
	public static final String dfsfb_name="党风室分办";
	//***********************************************************************************************
	public static final String jdssl_value="3";
	public static final String jdssl_name="监督室受理";
	
	public static final String jdsjy_value="3.5";
	public static final String jdsjy_name="监督室处置建议";

	public static final String jdsjd_value="3.6";
	public static final String jdsjd_name="监督室处置决定";
	
	public static final String jdsxf_value="3.7";
	public static final String jdsxf_name="监督室下发案件";
	public static final String fhags_value="4";
	public static final String fhags_name="返回案管室";  
	
	//*************************************************************************************    
	public static final String agszfb_value="2";
	public static final String agszfb_name="案管室受理再分办";
	//*************************************************************************************
	public static final String scssl_value="9";
	public static final String scssl_name="审查室受理";
	
	public static final String scsjy_value="10";
	public static final String scsjy_name="审查室处置建议";
	
	 public static final String scshyjd_value="25";
	 public static final String scshyjd_name="审查室会议决定";
	 
	 public static final String lasc_value="21";
	 public static final String lasc_name="立案审查（调查）";
	
	 public static final String scsldspyj_value="20";
	 public static final String scsldspyj_name="审查室领导审批意见";

	 public static final String zyldqp_value="24";
	 public static final String zyldqp_name="市委主要领导签批意见";
	 //**************************************************************************************
	 public static final String slssl_value="13";
	 public static final String slssl_name="审理室受理";
	
	 public static final String slssljy_value="22";
	 public static final String slssljy_name="审理室审理建议";
	
	 public static final String slscf_value="14";
	 public static final String slscf_name="审理室处理结果";
	 
	 //*************************************************************************************
	 public static final String xq_value="23";
	 public static final String xq_name="各区县受理";
	 //*************************************************************************************
     //结案
	 public static final String ja_value="-1";
	 public static final String ja_name="结案";
	 
	 /**
	  * 各科室的角色
	  */
	 //案管室
	 public static long ags_roleId=9L;
	 //监督室
	 public static long jds_roleId=10L;
	 //审查室
	 public static long scs_roleId=11L;
	 //审理室
	 public static long sls_roleId=12L;
	 //信访室
	 public static long xfs_roleId=13L;
	 //派驻纪检组
	 public static long pz_roleId=14L;
	 //党风室
	 public static long df_roleId=15L;
	 //专案小组
	 public static long zaxz_roleId=16L;
	 //全程办理
	 public static long qcbl_roleId=17L;
  	 //案管室id
	 public static final String agsId="11";
	 //监督室和扫黑办ids
	 public static final String jdsIds=",10,13,14,15,16,17,";
	 //审查室ids
	 public static final String scsIds=",18,19,20,21,22,23,24,25,";
	 //党风室id
	 public static final String dfsId="27";
	 //审理室
	 public static final String slsId="30"; 
	 //信访室id
	 public static final String xfsId="54";
	 //干部监督室
	 public static final String gbjdsId="26";
	 //当前用户的部门id
	 public static  String organId;
	 //当前用户id
	 public static  String userId;
	 //当前部门名
	 public static  String orgaName;
	 //特殊的线索来源
	 //来自监督室
	 public static  final String jdsFrom="-1";
	 //来自案管室
	 public static  final String agsFrom="1";
	 //来自党风室
	 public static  final String dfsFrom="0";
	 //来自信访室
	 public static  final String xfsFrom="xfs";
	 //来自信访室
	 public static  final String gbjdsFrom="gbjds";
	 //来自审理室
	 public static  final String slsFrom="sls";
	 //呈批签记录件数的
	 public static  int  cpq_num=0;
	 //处置建议时间长度
	 public static  final String czjyTime="30";
	 //谈话函询时间长度
	 public static  final String thhxTime="30";
	 //暂存待查时间长度
	 public static  final String zcdcTime="30";
	 //立案审查时间长度
	 public static  final String lascTime="90";
	 //案件审理时间长度
	 public static  final String ajslTime="30";
	 //处置建议到期名称
	 public static  final String czjyName="处置建议";
	 //处分决定执行完毕到期时间期限
	 public static final int cfjdTime=30;
	 //谈话函询到期名称
	 public static  final String thhxName="谈话函询";
	 //暂存待查到期名称
	 public static  final String zcdcName="暂存待查";
	 //立案审查到期名称
	 public static  final String lascName="立案审查";
	 //
	 public static  final String ajslName="案件审理";
	 //是否要结果到期
	 public static final String resultName="是否要结果";
	 //处分决定执行完毕时间到期
	 public static final String cfjdName="处分决定执行完毕到期";
	 //省纪委等上级
	 public static  final String sjwd_value=",1,2,15,23,";
	 //审查室领导批示意见 了结
	 public static  final String scslg_ja="2,3,4,5";
	 //监督处置决定阶段案件了结情况
	 public static  final String jds_jd_ja="予以了结,给予组织处理,了结澄清,谈话提醒,诫勉谈话,责令检查批评教育";
	 //数据传输
	 public static  final  int insert=0;
	 public static  final  int update=1;
	 public static  final  int delete=2;
	 //本机ip
	 public static   String ip;
	 //本机名称
	 public static   String  theName="沧州市纪委";
	 //获取线索方式
	 public static   String  sdlr="手动录入";
	 //
	 public static   String  sjfb="上级分办";
	 //
	 public static   String  xsdr="导入线索";
	 //tomcat下webapp的路径
	 public static   String  webapp;
	 //**是否使用
	 //0可以使用
	 public static   int use=0;
	 //使用过了
	 public static   int no_use=1;
	 //（新添）
	 public static   final String th_value="29";
	 public static   final String th_name="退回";
	 //（新添）
	 public static   final String ch_value="31";
	 public static   final String ch_name="撤回";
	 //行政区号
	 public static   final String  xzqh=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "xzqh", "00");
	 //案管室标识符
	 public static   final String  ags_suffix=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "ags_suffix", "AG");
	 //案管室标识符
	 public static   final String  jds_suffix=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "jds_suffix", "JD");
	 //案管室标识符
	 public static   final String  dfs_suffix=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "dfs_suffix", "DF");
	 //案管室标识符
	 public static   final String  gbjds_suffix=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "gbjds_suffix", "GB");
	 //信访室标识符
	 public static   final String  xfs_suffix=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "xfs_suffix", "XF");
	 //=============================================================================================================
	 //菜单名称对应
	 public static   final String  djs=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "djs", "待接收");
	 public static   final String  dbj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "dbj", "待办件");
	 public static   final String  jdhy=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "jdhy", "监督会议决定初核");
	 public static   final String  thj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "thj", "退还件");
	 public static   final String  ybj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "ybj", "已办件");
	 public static   final String  jdstj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "jdstj", "监督室添加件");
	 public static   final String  dfstj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "dfstj", "党风室添加件");
	 public static   final String  gbjdstj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "gbjdstj", "干部监督室添加件");
	 public static   final String  yja=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "yja", "已结案");
	 public static   final String  dqyj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "dqyj", "到期预警");
	 public static   final String  zcdc=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "zcdc", "暂存待查件");
	 public static   final String  jdslj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "jdslj", "监督室了结件");
	 public static   final String  wsxz=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "wsxz", "文书下载");
	 public static   final String  scslj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "scslj", "审查室了结件");
	 public static   final String  qx=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "qx", "区县线索库");
	 public static   final String  xsk=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "xsk", "线索库");
	 public static   final String  zbj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "zbj", "在办件");
	 public static   final String  bjj=ResourceBundleUtil.getString(RESOURCE_BUNDLE, "bjj", "办结件");
	 
}
