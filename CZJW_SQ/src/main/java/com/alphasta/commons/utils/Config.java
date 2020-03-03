package com.alphasta.commons.utils;

/**
 * @description：系统参数
 * @author：liyunhao
 * @date：2015年8月13日 下午9:18:11
 */
public interface Config {

	String UPLOADPATH="d://upload//";
    Integer RESOURCE_MENU = 0; // 菜单
    Integer RESOURCE_BUTTON = 1; // 按钮
    String FILEPATH = "FILEPATH";
    String NUMBERRULEID = "RULEID";
    String IMAGEPATH = "IMAGEPATH";
    Integer ARTICLE_SEE=0;  //  查看文章   评论表中ctype类型
    Integer ARTICLE_GREAT=1;  //  文章点赞   评论表中ctype类型
    Integer ARTICLE_COMMENT=2;  //  文章评论  评论表中ctype类型
    Integer ARTICLE_RECOMMENT=3;  //  评论留言  评论表中ctype类型
    Integer ARTICLE_PUTE=4;  //       发布文章  评论表中ctype类型
    
    Integer ARTICLE_STATE_WSH=0;  //文章未审核
    Integer ARTICLE_STATE_YSH=1;  //文章已审核
    Integer ARTICLE_STATE_BBH=2;  //文章被驳回
    Integer ARTICLE_SCORETYPE_SEE=1;  //查看  文章给积分类型
    Integer ARTICLE_SCORETYPE_COM=2;   //评论  文章给积分类型
    Integer ATTACHMENT_TYPE_ART=1;   //文章  附件表 
    Integer ATTACHMENT_TYPE_ACT=2;    //活动   附件表 
    Integer ATTACHMENT_TYPE_CATa=3;    //栏目   附件表 
    String ATTACHMENT_FILLTYPE_IMG="1";    //图片   附件表 附件类型
    Integer SYS_MSGPUT_TYPE_GR=1;  //消息发送类型  个人
    Integer SYS_MSGPUT_TYPE_BM=2;  //消息发送类型  部门
    Integer SYS_MSGPUT_TYPE_QT=3;  //消息发送类型  全体
    String QUESTION_STATE_YD = "已答"; //答题状态 已答
    String QUESTION_STATE_WD = "未答"; //答题状态 未答
    Integer LOGIN_YZ_PAWCW = 2; //密码错误
    Integer LOGIN_YZ_WYH = 3; //未找到用户
    Integer ZDLOGIN_STATE_KY = 4; //可以自动登录
    Integer ZDLOGIN_STATE_BKY = 5; //不可以自动登录
}
