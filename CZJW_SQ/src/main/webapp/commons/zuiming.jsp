<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style type="text/css">  
  		
        .dropdown-submenu {  
            position: relative;  
        }  
        .dropdown-submenu > .dropdown-menu {  
            top: 0;  
            left: 100%;  
            margin-top: -6px;  
            margin-left: -1px;  
            -webkit-border-radius: 0 6px 6px 6px;  
            -moz-border-radius: 0 6px 6px;  
            border-radius: 0 6px 6px 6px;  
        }  
        .dropdown-submenu:hover > .dropdown-menu {  
            display: block;  
        }  
        .dropdown-submenu > a:after {  
            display: block;  
            content: " ";  
            float: right;  
            width: 0;  
            height: 0;  
            border-color: transparent;  
            border-style: solid;  
            border-width: 5px 0 5px 5px;  
            border-left-color: #ccc;  
            margin-top: 5px;  
            margin-right: -10px;  
        }  
        .dropdown-submenu:hover > a:after {  
            border-left-color: #fff;  
        }  
        .dropdown-submenu.pull-left {  
            float: none;  
        }  
        .dropdown-submenu.pull-left > .dropdown-menu {  
            left: -100%;  
            margin-left: 10px;  
            -webkit-border-radius: 6px 0 6px 6px;  
            -moz-border-radius: 6px 0 6px 6px;  
            border-radius: 6px 0 6px 6px;  
        }  
    </style>  
 <input type="hidden" name="problemClues.crimeCharge" id="category_id" value="" />  
   <div class="dropdown">  
   
 <%--    <select id="fields"  name="fields"  class="selectpicker form-control" multiple  data-s="multiple" >
                                                   
						                          <c:forEach var="act" items="${fields}">
								                     <option value="${act.value }">${act.name}</option>
		                                          </c:forEach>
                                            </select> --%>
   <button type="button" id="crimeCharge" name="crimeCharge2" multiple="multiple" style="width:220px;" class="btn dropdown-toggle" data-toggle="dropdown" role="button" title="请选择"><span class="filter-option pull-left" style="width:180px;display: inline-block;text-align: left;float: left!important;" id="select-title">请选择</span>&nbsp;<span class="bs-caret"><span class="caret"></span></span></button>
   
   
<!-- <button type="button" id="dLabel" role="button" data-toggle="dropdown"
	 class="btn dropdown-toggle btn-info" data-style="btn-info"data-target="#" href="javascript:;"><span
	id="select-title">请选择</span> <span class="caret"></span></button> -->
<ul class="dropdown-menu multi-level" role="menu"
	aria-labelledby="dropdownMenu">
	<li class="dropdown-submenu"><a href="javascript:;" data-index="1-1" data-title="贪污贿赂犯罪">贪污贿赂犯罪
	<ul class="dropdown-menu">
			<li><a data-index="1" href="javascript:;" data-title="贪污罪">贪污罪</a></li>
			<li class="divider"></li>
			<li><a data-index="2" href="javascript:;" data-title="二挪用公款罪">挪用公款罪</a></li>
			<li class="divider"></li>
			<li><a data-index="3" href="javascript:;" data-title="受贿罪">受贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="4" href="javascript:;" data-title="单位受贿罪">单位受贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="5" href="javascript:;" data-title="利用影响力受贿罪">利用影响力受贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="6" href="javascript:;" data-title="行贿罪">行贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="7" href="javascript:;" data-title="为利用影响力行贿罪">为利用影响力行贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="8" href="javascript:;" data-title="对单位行贿罪">对单位行贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="9" href="javascript:;" data-title="介绍贿赂罪">介绍贿赂罪</a></li>
			<li class="divider"></li>
			<li><a data-index="10" href="javascript:;" data-title="单位行贿罪">单位行贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="11" href="javascript:;" data-title="巨额财产来源不明罪">巨额财产来源不明罪</a></li>
			<li class="divider"></li>
			<li><a data-index="12" href="javascript:;" data-title="隐瞒境外存款罪">隐瞒境外存款罪</a></li>
			<li class="divider"></li>
			<li><a data-index="13" href="javascript:;" data-title="私分国有资产罪">私分国有资产罪</a></li>
			<li class="divider"></li>
			<li><a data-index="14" href="javascript:;" data-title="私分罚没财物罪">私分罚没财物罪</a></li>
		</ul>
		</li>
	<li class="dropdown-submenu"><a href="javascript:;" data-index="2-1"
		data-title="渎职犯罪">渎职犯罪</a>
		
		<ul class="dropdown-menu">
			<li><a data-index="15" href="javascript:;" data-title="滥用职权罪">滥用职权罪</a></li>
			<li class="divider"></li>
			<li><a data-index="16" href="javascript:;" data-title="玩忽职守罪">玩忽职守罪</a></li>
			<li class="divider"></li>
			<li><a data-index="17" href="javascript:;" data-title="故意泄露国家秘密罪">故意泄露国家秘密罪</a></li>
			<li class="divider"></li>
			<li><a data-index="18" href="javascript:;" data-title="过失泄露国家秘密罪">过失泄露国家秘密罪</a></li>
			<li class="divider"></li>
			<li><a data-index="19" href="javascript:;" data-title="利用影响力受贿罪">利用影响力受贿罪</a></li>
			<li class="divider"></li>
			<li><a data-index="20" href="javascript:;" data-title="徇私枉法罪">徇私枉法罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="民事、行政枉法裁判罪">民事、行政枉法裁判罪</a></li>
			<li class="divider"></li>
			<li><a data-index="22" href="javascript:;" data-title="执行判决裁定失职罪">执行判决裁定失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="23" href="javascript:;" data-title="执行判决裁定滥用职权罪">执行判决裁定滥用职权罪</a></li>
			<li class="divider"></li>
			<li><a data-index="24" href="javascript:;" data-title="枉法仲裁罪">枉法仲裁罪</a></li>
			<li class="divider"></li>
			<li><a data-index="25" href="javascript:;" data-title="私放在押人员罪">私放在押人员罪</a></li>
			<li class="divider"></li>
			<li><a data-index="26" href="javascript:;" data-title="失职致使在押人员脱逃罪">失职致使在押人员脱逃罪</a></li>
			<li class="divider"></li>
			<li><a data-index="27" href="javascript:;" data-title="徇私舞弊减刑、假释、暂予监外执行罪">徇私舞弊减刑、假释、暂予监外执行罪</a></li>
			<li class="divider"></li>
			<li><a data-index="28" href="javascript:;" data-title="徇私舞弊不移交刑事案件罪">徇私舞弊不移交刑事案件罪</a></li>
			<li class="divider"></li>
			<li><a data-index="29" href="javascript:;" data-title="滥用管理公司、证券职权罪">滥用管理公司、证券职权罪</a></li>
			<li class="divider"></li>
			<li><a data-index="30" href="javascript:;" data-title="徇私舞弊发售发票、抵扣税款、出口退税罪">徇私舞弊发售发票、抵扣税款、出口退税罪</a></li>
			<li class="divider"></li>
			<li><a data-index="31" href="javascript:;" data-title="违法提供出口退税凭证罪">违法提供出口退税凭证罪</a></li>
			<li class="divider"></li>
			<li><a data-index="32" href="javascript:;" data-title="国家机关工作人员签订、履行合同失职被骗罪">国家机关工作人员签订、履行合同失职被骗罪</a></li>
			<li class="divider"></li>
			<li><a data-index="33" href="javascript:;" data-title="违法发放林木采伐许可证罪">违法发放林木采伐许可证罪</a></li>
			<li class="divider"></li>
			<li><a data-index="34" href="javascript:;" data-title="环境监管失职罪">环境监管失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="35" href="javascript:;" data-title="食品监管渎职罪">食品监管渎职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="36" href="javascript:;" data-title="传染病防治失职罪">传染病防治失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="37" href="javascript:;" data-title="非法批准征收、征用、占用土地罪">非法批准征收、征用、占用土地罪</a></li>
			<li class="divider"></li>
			<li><a data-index="38" href="javascript:;" data-title="非法低价出让国有土地使用权罪">非法低价出让国有土地使用权罪</a></li>
			<li class="divider"></li>
			<li><a data-index="39" href="javascript:;" data-title="放纵走私罪">放纵走私罪</a></li>
			<li class="divider"></li>
			<li><a data-index="40" href="javascript:;" data-title="商检徇私舞弊罪">商检徇私舞弊罪</a></li>
			<li class="divider"></li>
			<li><a data-index="41" href="javascript:;" data-title="商检失职罪">商检失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="42" href="javascript:;" data-title="动植物检疫徇私舞弊罪">动植物检疫徇私舞弊罪</a></li>
			<li class="divider"></li>
			<li><a data-index="43" href="javascript:;" data-title="动植物检疫失职罪">动植物检疫失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="44" href="javascript:;" data-title="放纵制售伪劣商品犯罪行为罪">放纵制售伪劣商品犯罪行为罪</a></li>
			<li class="divider"></li>
			<li><a data-index="45" href="javascript:;" data-title="办理偷越国（边）境人员出入境证件罪">办理偷越国（边）境人员出入境证件罪</a></li>
			<li class="divider"></li>
			<li><a data-index="46" href="javascript:;" data-title="放行偷越国（边）境人员罪">放行偷越国（边）境人员罪</a></li>
			<li class="divider"></li>
			<li><a data-index="47" href="javascript:;" data-title="放纵制售伪劣商品犯罪行为罪">放纵制售伪劣商品犯罪行为罪</a></li>
			<li class="divider"></li>
			<li><a data-index="48" href="javascript:;" data-title="不解救被拐卖、绑架妇女、儿童罪">不解救被拐卖、绑架妇女、儿童罪</a></li>
			<li class="divider"></li>
			<li><a data-index="49" href="javascript:;" data-title="阻碍解救被拐卖、绑架妇女儿童罪">阻碍解救被拐卖、绑架妇女儿童罪</a></li>
			<li class="divider"></li>
			<li><a data-index="50" href="javascript:;" data-title="帮助犯罪分子逃避处罚罪">帮助犯罪分子逃避处罚罪</a></li>
			<li class="divider"></li>
			<li><a data-index="51" href="javascript:;" data-title="招收公务员、学生徇私舞弊罪">招收公务员、学生徇私舞弊罪</a></li>
			<li class="divider"></li>
			<li><a data-index="52" href="javascript:;" data-title="失职造成珍贵文物损毁、流失罪">失职造成珍贵文物损毁、流失罪</a></li>
		</ul>
		
	</li>

	<li class="dropdown-submenu"><a tabindex="3-1" href="javascript:;"
		data-title="利用职权侵犯公民人身权利、民主权利犯罪">利用职权侵犯公民人身权利、民主权利犯罪</a>
			<ul class="dropdown-menu">
			<li><a data-index="15" href="javascript:;" data-title="非法拘禁罪">非法拘禁罪</a></li>
			<li class="divider"></li>
			<li><a data-index="16" href="javascript:;" data-title="诬告陷害罪">诬告陷害罪</a></li>
			<li class="divider"></li>
			<li><a data-index="17" href="javascript:;" data-title="非法搜查罪">非法搜查罪</a></li>
			<li class="divider"></li>
			<li><a data-index="18" href="javascript:;" data-title="非法侵入住宅罪">非法侵入住宅罪</a></li>
			<li class="divider"></li>
			<li><a data-index="19" href="javascript:;" data-title="刑讯逼供罪">刑讯逼供罪</a></li>
			<li class="divider"></li>
			<li><a data-index="20" href="javascript:;" data-title="暴力取证罪">暴力取证罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="虐待被监管人罪">虐待被监管人罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="非法剥夺公民宗教信仰自由罪">非法剥夺公民宗教信仰自由罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="侵犯少数民族风俗习惯罪">侵犯少数民族风俗习惯罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="报复陷害罪">报复陷害罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="国家机关工作人员利用职权实施的破坏选举罪">国家机关工作人员利用职权实施的破坏选举罪</a></li>
		</ul>
		</li>	
			<li class="dropdown-submenu"><a tabindex="4-1" href="javascript:;"
		data-title="其他利用职权进行权力寻租、利益输送、徇私舞弊、浪费国家资财犯罪">其他利用职权进行权力寻租、利益输送、徇私舞弊、浪费国家资财犯罪</a>
			<ul class="dropdown-menu">
			<li><a data-index="15" href="javascript:;" data-title="非法经营同类营业罪">非法经营同类营业罪</a></li>
			<li class="divider"></li>
			<li><a data-index="16" href="javascript:;" data-title="为亲友非法牟利罪">为亲友非法牟利罪</a></li>
			<li class="divider"></li>
			<li><a data-index="17" href="javascript:;" data-title="签订、履行合同失职被骗罪">签订、履行合同失职被骗罪</a></li>
			<li class="divider"></li>
			<li><a data-index="18" href="javascript:;" data-title="国有公司、企业人员失职罪">国有公司、企业人员失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="19" href="javascript:;" data-title="国有公司、企业人员滥用职权罪">国有公司、企业人员滥用职权罪</a></li>
			<li class="divider"></li>
			<li><a data-index="20" href="javascript:;" data-title="国有事业单位人员失职罪">国有事业单位人员失职罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="国有事业单位人员滥用职权罪">国有事业单位人员滥用职权罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="徇私舞弊低价折股、出售国有资产罪">徇私舞弊低价折股、出售国有资产罪</a></li>
			<li class="divider"></li>
			<li><a data-index="21" href="javascript:;" data-title="违法运用资金罪">违法运用资金罪</a></li>
			
		</ul>
</ul>
</div>
<script type="text/javascript">  

  
</script>  