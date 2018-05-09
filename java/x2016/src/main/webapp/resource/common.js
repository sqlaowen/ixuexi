(function ($) {
    var returnValue = {}, checkboxName = [];
    $.fn.getFormsValue = function () {
        returnValue = {}, checkboxName = [];
        this.find("[name][type != 'button'][type != 'submit'][type != 'reset']").each(function () {
            var _this = this;
            switch (_this.tagName.toLowerCase()) {
                case "input":
                    processInput(_this);
                    break;
                case "textarea":
                    returnValue[_this.name] = $.trim($(_this).val());
                    break;
                case "select":
                    returnValue[_this.name] = $(_this).val();
                    break;
                default:

                    break;
            }
        });
        return returnValue;
    }

    //_obj_:对象，如{name:value}
    $.fn.setFormsValue = function (_obj_) {
        $this = this;
        var inputs = $this.find('input[name]');
        $this.find('textarea[name]').each(function (i, v) {
            inputs.push(v);
        });
        $this.find('select[name]').each(function (i, v) {
            inputs.push(v);
        });
        for (var item in _obj_) {
            if (_obj_[item]) {
                $.each(inputs, function () {
                    if ($(this).attr("name") == item) {
                        var type = ($(this).attr("type") || "").toLowerCase();
                        switch (type) {
                            case "radio":
                                $this.find(":radio[name='" + item + "']").each(function () {
                                    var $r = $(this)
                                    if ($r.val() == _obj_[item]) {
                                        $r.prop("checked", true);
                                        return false;
                                    }
                                });
                                break;
                            case "checkbox":
                                $this.find(":checkbox[name='" + item + "']").each(function () {
                                    var $chk = $(this)
                                    if (("," + _obj_[item] + ",").indexOf("," + $chk.val() + ",") > -1) {
                                        $chk.prop("checked", true);
                                    }
                                });
                                break;
                            default:
                                $(this).val(_obj_[item]);
                        }
                        return false;
                    }
                });
            }
        }
    }

    function processInput(input) {
        var nodetype = input.type.toLowerCase();
        if (nodetype != "button" && nodetype != "submit" && nodetype != "reset") {
            if (nodetype == "radio" || nodetype == "checkbox") {
                if (input.checked) {
                    if (nodetype == "radio") {
                        if (input.value != 'on')
                            returnValue[input.name] = input.value;
                    }
                    else {
                        if (("," + checkboxName.join(',') + ",").indexOf(("," + input.name + ",")) < 0) {
                            checkboxName.push(input.name);
                            var tempValue = [];
                            $("input:checkbox[name='" + input.name + "']").each(function (index, item) {
                                if (item.checked) {
                                    if (input.value != 'on')
                                        tempValue.push(item.value);
                                }
                            });
                            returnValue[input.name] = tempValue.join(",");
                        }
                    }
                }
            }
            else {
                returnValue[input.name] = $.trim(input.value);
            }
        }
    }
})(jQuery);

//取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}

//url参数转化为对象
function urlParam2Obj() {
    var obj = {};
    if ($.trim(window.location.search) == '')
        return obj;
    var parm = window.location.search.substr(1);
    var parmArr = parm.split('&');
    for (var i = 0; i < parmArr.length; i++) {
        if (!(/\w+=[^&]*/.test(parmArr[i])))
            continue;
        var arrC = parmArr[i].split('=');
        obj[arrC[0].replace(/ /g, '')]=decodeURI(arrC[1]);
    }
    return obj;
}
//parm:name=value&name=vlaue&name=vlaue……
function param2Obj(parm) {
    var obj = {};
    if(parm==undefined ||parm==null)
    	return obj;
    var parmArr = parm.split('&');
    for (var i = 0; i < parmArr.length; i++) {
        if (!(/\w+=[^&]*/.test(parmArr[i])))
            continue;
        var arrC = parmArr[i].split('=');
        obj[arrC[0].replace(/ /g, '')]=decodeURI(arrC[1]);
    }
    return obj;
}
//obj:{k:v,k:v}
function obj2Param(obj){
	var str='';
	$.each(obj,function(i,v){
		if(str=='')
			str+= decodeURI(i+'='+v);
		else
			str+= decodeURI('&'+i+'='+v);
	});
	return str;
}

if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (e) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == e) {
                return i;
            }
        }
        return -1;
    }
}

if (!Array.prototype.remove) {
    Array.prototype.remove = function (i) {
        this.splice(i, 1)
    }
}

if (!Array.prototype.removeValue) {
    Array.prototype.removeValue = function (val) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) {
                this.splice(i, 1);
                //break;
            }
        }
    }
}

//日期格式 yyyy-MM-dd
function string2Date(dateStr)
{
	var converted = Date.parse(dateStr);
	var myDate = new Date(converted);
	if (isNaN(myDate))
	{
		var arys= dateStr.split('-');
		myDate = new Date(arys[0],arys[1],arys[2]);
	}
	return myDate;
}

//当前时间 yyyy-MM-dd HH:mm:ss 星期一
function getCurrentDate(){
	var t=new Date();
	var arrWeek = ['日', '一', '二', '三', '四', '五', '六'];
	return t.getFullYear()+'-'+lpad((t.getMonth()+1),2)+'-'+lpad(t.getDate(),2)+' '
	+lpad(t.getHours(),2)+':'+lpad(t.getMinutes(),2)+':'+lpad(t.getSeconds(),2)
	+' 星期'+arrWeek[t.getDay()];
}

//日期差
Date.prototype.DateDiff = function(strInterval, dtEnd) {
	var dtStart = this;
	if (typeof dtEnd == 'string' )//如果是字符串转换为日期型
	{
		dtEnd = string2Date(dtEnd);
	}
	switch (strInterval) {
		case 's' : return parseInt((dtEnd - dtStart) / 1000);
		case 'n' : return parseInt((dtEnd - dtStart) / 60000);
		case 'h' : return parseInt((dtEnd - dtStart) / 3600000);
		case 'd' : return parseInt((dtEnd - dtStart) / 86400000);
		case 'w' : return parseInt((dtEnd - dtStart) / (86400000 * 7));
		case 'm' : return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);
		case 'y' : return dtEnd.getFullYear() - dtStart.getFullYear();
	}
}

//日期计算
Date.prototype.DateAdd = function(strInterval, Number) {
	var dtTmp = this;
	switch (strInterval) {
		case 's' : return new Date(Date.parse(dtTmp) + (1000 * Number));
		case 'n' : return new Date(Date.parse(dtTmp) + (60000 * Number));
		case 'h' : return new Date(Date.parse(dtTmp) + (3600000 * Number));
		case 'd' : return new Date(Date.parse(dtTmp) + (86400000 * Number));
		case 'w' : return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
		case 'q' : return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
		case 'm' : return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
		case 'y' : return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
	}
}

//---------------------------------------------------
// 判断闰年
//---------------------------------------------------
Date.prototype.isLeapYear = function()
{
	return (0==this.getYear()%4&&((this.getYear()%100!=0)||(this.getYear()%400==0)));
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//时间戳
function uniqueId() {
    var a = Math.random, b = parseInt;
    return Number(new Date()).toString() + b(10 * a()) + b(10 * a()) + b(10 * a());
}

//不足用0填充
function lpad(strArg,len){
	var str=strArg.toString();
	var strLen=str.length;
	for(i=0;i<len-strLen;i++){
		str='0'+str;
	}
	return str;
}

//
function null2Val(val,defVal){
	if(null==val||undefined==val)
		return defVal;
	return val;
}

$(document).ajaxError(function(event, xhr, options) {
    if (xhr.status == 403) {
        alert('您没有执行此操作的权限，请联系系统管理员');
        return false;
    } else {
    	alert('发生【"' + xhr.status + '"】错误，请重试');
        return false;
    }
});