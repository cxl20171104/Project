/**
 * 获取路径
 * 
 * @returns
 */
function getRootPath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}
/**
 * form到json的序列化方法 这个function对于普通的对象转换是足够的， 但是如果出现对象内部又包含子对象的情形就不能支持了。
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
/**
 * 使用示例： new Date().Format("yyyy年MM月dd日") new Date().Format("MM/dd/yyyy") new
 * Date().Format("yyyyMMdd") new Date().Format("yyyy-MM-dd hh:mm:ss")
 * 
 * @param format
 * @returns
 */
Date.prototype.Format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};
/**
 * 毫秒数时间转成显示时间
 */
function toShowTime(time,fmtStr) {
	var date = new Date(time);
	var Year = date.getFullYear();
	var Month = date.getMonth() + 1;
	var Day = date.getDate();
	if(fmtStr=="年月日"){
		return Year + "年" + Month + "月" + Day + "日";
	}else if(fmtStr==" "){
		return Year + " " + Month + " " + Day;
	}else if(fmtStr=="."){
		return Year + "." + Month + "." + Day;
	}else{
		return Year + "-" + Month + "-" + Day;
	}
}

/**
 * 获取年龄
 * @param strBirthday 出生日期
 * @param fmtStr 分隔符
 * @returns
 */
function getAge(strBirthday,fmtStr){       
    var returnAge;
    var birthYear;
    var birthMonth;
    var birthDay;
    if(fmtStr==""||fmtStr==null){
    	birthYear = parseInt(strBirthday.substring(0,4));
    	birthMonth = parseInt(strBirthday.substring(4,6));
    	birthDay = parseInt(strBirthday.substring(6,8));
    }else{
    	var strBirthdayArr=strBirthday.split(fmtStr);
    	birthYear = parseInt(strBirthdayArr[0]);
    	birthMonth = parseInt(strBirthdayArr[1]);
    	birthDay = parseInt(strBirthdayArr[2]);
    }
    
    var d = new Date();
    var nowYear = d.getYear()+1900;
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    
    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth){
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0){
                    returnAge = ageDiff - 1;
                }else{
                    returnAge = ageDiff ;
                }
            }else{
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0){
                    returnAge = ageDiff - 1;
                }else{
                    returnAge = ageDiff ;
                }
            }
        }else{
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    return returnAge;//返回周岁年龄
}

Array.prototype.unique = function(){
	var res = [];
	var json = {};
	for(var i = 0; i < this.length; i++){
		if(!json[this[i]]){
			res.push(this[i]);
			json[this[i]] = 1;
		}
	}
	return res;
};
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
