/**
 * 去左空格
 */
//function ltrim(s) {
//    return s.replace(/^\s*/, "");
//}
/**
 * 去右空格
 */
//function rtrim(s) {
//    return s.replace(/\s*$/, "");
//}
function checkPassword(pw) {
	if (pw && pw.length >= 8) {
		if　(! /^(?=.*\d)(?=.*[a-zA-Z]).{8,24}$/.test(pw)) {
			alert("密码必须同时包含字母和数字");
			return false;
		}
	} else {
		alert("请输入至少8位密码");
		return false;
	}
	return true;
}
/**
 * 去左右空格
 */
function trim(s) {
    return rtrim(ltrim(s));
}

function isArrayObject(obj) {
    return (obj.length && (!obj.tagName));
}

//判断日期格式如2009-01-08
function isDate(DateString, Dilimeter){
    if (DateString==null) return false;

    if(Dilimeter=="" || Dilimeter==null) Dilimeter = "-";

    var tempy="";
    var tempm="";
    var tempd="";
    var tempArray;
    if (DateString.length<8 || DateString.length>10) return false;

    tempArray = DateString.split(Dilimeter);
    if (tempArray.length!=3) return false;

    tempy = tempArray[0];
    tempm = tempArray[1];
    tempd = tempArray[2];

    var tDateString = tempy + "/"+tempm + "/"+tempd+" 8:0:0";//加八小时是因为我们处于东八区
    var tempDate = new Date(tDateString);
    if (isNaN(tempDate)) return false;

    if (((tempDate.getUTCFullYear()).toString()==tempy) && (tempDate.getMonth()==parseInt(tempm)-1) && (tempDate.getDate()==parseInt(tempd))) {
       return true;
    } else {
       return false;
    }

}
function isPhone(str) {//验证是否是正确的电话号码
    var re = /^((\d{11})|^((\(\d{3}\))|(\d{3}\-))?((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
    return re.test(str);
}

function isEmail(str) {//验证是否是正确的邮箱
    var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    return re.test(str);
}

function isZip(str) {//验证是否是正确的邮政编码
    var re = /^[1-9]\d{5}$/;
    return re.test(str);
}

function isChinese(str) {//验证是否是中文
    var re = /^[\u0391-\uFFE5]+$/;
    return re.test(str);
}

function isEnglish(str) {//验证是否是英文字母或数字
    var re = /^[A-Za-z0-9]+$/;
    return re.test(str);
}


var FormValidator_NotFocusObjectType = /^(hidden|button)$/;
function isFocusObjectType(objectType) {
    return !FormValidator_NotFocusObjectType.test(objectType);
}

function FormValidator_focusObject(fieldObj, nodeObj) {
    if (nodeObj && (nodeObj.validator) && (nodeObj.validator.focusCallBack)) {
        if (typeof(nodeObj.validator.focusCallBack) == "function") {
            nodeObj.validator.focusCallBack(fieldObj);
        }
    } else if ( (fieldObj) && isFocusObjectType(fieldObj.type)) {
        fieldObj.focus();
    }
}

var FormValidator = function(formName) {
    this.formName = formName;
    this.allCheckList = new Array();
    this.focusCallBack = null;
    initFormValidatorFunc(this);
}

function initFormValidatorFunc(v) {
    v.addNormalCheckPoint = FormValidator_addNormalCheckPoint;
    v.addRangeCheckPoint = FormValidator_addRangeCheckPoint;
    v.addRelationCheckPoint = FormValidator_addRelationCheckPoint;
    v.checkAll = FormValidator_checkAll;
}

var NormalCheckPoint = function (validator, fieldName, fieldTitle, checkType, customMessage) {
      this.validator = validator;
    this.funcType = 1;
    this.fieldName = fieldName;
    this.fieldTitle = fieldTitle;
    this.checkType = checkType;
    this.customMessage = customMessage;
}

var RangeCheckPoint = function(validator, fieldName, fieldTitle, checkType, rangeStart, rangeEnd, customMessage) {
      this.validator = validator;
    this.funcType = 2;
    this.fieldName = fieldName;
    this.fieldTitle = fieldTitle;
    this.checkType = checkType;
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
    this.customMessage = customMessage;
}

var RelationCheckPoint = function(validator, fieldName1, fieldTitle1, fieldName2, fieldTitle2, checkType, isNumber, customMessage) {
      this.validator = validator;
    this.funcType = 3;
    this.fieldName1 = fieldName1;
    this.fieldTitle1 = fieldTitle1;
    this.fieldName2 = fieldName2;
    this.fieldTitle2 = fieldTitle2;
    this.checkType = checkType;
    this.isNumber = isNumber;
    this.customMessage = customMessage;
}

var Reg_CheckNormal_Cmd = /^check(NotNull|Int|Float|NotEmpty|Phone|Zip|Email|Chinese|English)$/;
function isNormalCheckType(checkType) {
    return Reg_CheckNormal_Cmd.test(checkType);
}

var Reg_CheckRange_Cmd = /^check(Length|Lesser|EquLesser|Greater|EquGreater|Equal|Between)$/;
function isRangeCheckType(checkType) {
    return Reg_CheckRange_Cmd.test(checkType);
}

var Reg_CheckRelation_Cmd = /^check(Lesser|LesserEqual|Equal)$/;
function isRelationCheckType(checkType) {
    return Reg_CheckRelation_Cmd.test(checkType);
}

/*
*  加入一个要检查值的域以及要进行检查的类型
*  fieldName 域的名字
*  fieldTitle 表示域的标题信息,显示提示信息时用
*  checkType 对该域进行的检查类型,类型值是以下的类型名.
*            目前支持的检查类型有:
*               类型名            功能解释
*               checkNotNull   被检查的域必须有值
*               checkInt       被检查的域中为空或者填入的是一个合法的整数
*               checkFloat     被检查的域中为空或者填入的是一个合法的浮点数
*               checkNotEmpty  被检查的域不能为空字符串
*  customMessage 用户定义的错误提示信息。该消息可以不设置，没有设置该消息时由函数自己产生错误提示信息.
*
*/
function FormValidator_addNormalCheckPoint(fieldName, fieldTitle, checkType, customMessage) {
    var typeArr = checkType.split(';');
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ( (aCheckType.length > 0) && (!isNormalCheckType(aCheckType)) ) {
            alert("检查类型不正确!fieldName[" + fieldName + "], fieldTitle[" + fieldTitle + "], checkType[" + aCheckType + "]");
            return;
        }
    }
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ((aCheckType) && (aCheckType.length > 0)) {
            this.allCheckList[this.allCheckList.length] = new NormalCheckPoint(this, fieldName, fieldTitle, "Normal_" + aCheckType, customMessage);
        }
    }
}

/*
*  加入一个要检查值的域以及要进行的范围检查的类型
*  fieldName 域的名字
*  fieldTitle 表示域的标题信息,显示提示信息时用
*  checkType 对该域进行的检查类型,类型值是以下的类型名.
*            目前支持的检查类型有:
*               类型名            功能解释
*               checkLength    rangeStart<=被检查的域的值的长度大于<=rangeEnd,被检查的域的值必须是字符串
*               checkLesser    被检查的域的值<rangeStart,被检查的域的值必须是浮点数或者整数
*               checkEquLesser    被检查的域的值<=rangeStart,被检查的域的值必须是浮点数或者整数
*               checkGreater   rangeStart<被检查的域的值,被检查的域的值必须是浮点数或者整数
*               checkEquGreater   rangeStart<=被检查的域的值,被检查的域的值必须是浮点数或者整数
*               checkEqual     rangeStart=被检查的域的值,被检查的域的值必须是浮点数或者整数
*               checkBetween   rangeStart<被检查的域的值大于<rangeEnd,被检查的域的值必须是浮点数或者整数
*  rangeStart 范围检查的第一个值
*  rangeEnd   范围检查的第二个值
*  customMessage 用户定义的错误提示信息。该消息可以不设置，没有设置该消息时由函数自己产生错误提示信息.
*
*/
function FormValidator_addRangeCheckPoint(fieldName, fieldTitle, checkType, rangeStart, rangeEnd, customMessage) {
    var typeArr = checkType.split(';');
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ( (aCheckType.length > 0) && (!isRangeCheckType(aCheckType)) ) {
            alert("检查类型不正确!fieldName[" + fieldName + "], fieldTitle[" + fieldTitle + "], checkType[" + aCheckType + "]");
            return;
        }
    }
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ((aCheckType) && (aCheckType.length > 0)) {
            this.allCheckList[this.allCheckList.length] = new RangeCheckPoint(this, fieldName, fieldTitle, "Range_" + aCheckType, rangeStart, rangeEnd, customMessage);
        }
    }
}

/*
*  加入一个检查以比较两个域的值符合一定的关系
*  fieldName1 第一个域的名字
*  fieldTitle1 表示第一个域的标题信息,显示提示信息时用
*  fieldName2 第二个域的名字
*  fieldTitle2 表示第二个域的标题信息,显示提示信息时用
*  checkType 对该域进行的检查类型,类型值是以下的类型名.
*            目前支持的检查类型有:
*               类型名              功能解释
*               checkLesser      field1<field2
*               checkLesserEqual field1<=field2
*               checkEqual       field1=field2
*  isNumber 表示要比较的域是否是数值类型的.取值为1表示数值型将按数值的大小比较域的值;其他取值表示不是数值型,将直接比较域的值
*  customMessage 用户定义的错误提示信息。该消息可以不设置，没有设置该消息时由函数自己产生错误提示信息.
*/
function FormValidator_addRelationCheckPoint(fieldName1, fieldTitle1, fieldName2, fieldTitle2, checkType, isNumber, customMessage) {
    var typeArr = checkType.split(';');
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ( (aCheckType.length > 0) && (!isRelationCheckType(aCheckType)) ) {
            alert("检查类型不正确!fieldName1[" + fieldName1 + "], fieldTitle1[" + fieldTitle1 + "], checkType[" + aCheckType + "]");
            return;
        }
    }

    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ((aCheckType) && (aCheckType.length > 0)) {
            this.allCheckList[this.allCheckList.length] = new RelationCheckPoint(this, fieldName1, fieldTitle1, fieldName2, fieldTitle2, "Relation_" + aCheckType, isNumber, customMessage);
        }
    }
}

function isNull(str) {
    return (!str) || (0 == str.length);
}

function isInt(str) {//以负号或者1－9之间的数字打头，或者就单单是一个0
    var re = /(^-?[1-9]\d*$)|^0$/;
    return re.test(str);
}

function isFloat(str) {
    //var re = /^(-?\d+)(\.\d+)?$/;
    var re =/^(\d{1,3}(,\d\d\d)*(\.\d+)?|\d+(\.\d+)?)$/;
    return re.test(str);
}

function Normal_checkObject(fieldObj, nodeObj, checkFunc) {
    if (fieldObj) {
        if (isArrayObject(fieldObj)) {
            for (var i = 0; i < fieldObj.length; i++) {
                if (!checkFunc(fieldObj[i], nodeObj)) {
                    return false;
                }
            }
        } else {
            return checkFunc(fieldObj, nodeObj);
        }
    }
    return true;
}

/*
*   如有用户定义的错误提示信息则显示它并返回true，否则返回false。
*/
function Common_AlertCustomMessage(nodeObj) {
    if (nodeObj.customMessage) {
        alert(nodeObj.customMessage);
        return true;
    }
    return false;
}

/*
*   被检查的域必须有值
*/
function Normal_checkNotNull(fieldObj, nodeObj) {
    if (isNull(fieldObj.value)) {
        if (!Common_AlertCustomMessage(nodeObj)) alert("必须录入\"" + nodeObj.fieldTitle + "\"！");
        FormValidator_focusObject(fieldObj, nodeObj);
        return false;
    }
    return true;
}
/*
*   被检查的域不能为空字符串
*/
function Normal_checkNotEmpty(fieldObj, nodeObj) {
    if (!isNull(fieldObj.value) && trim(fieldObj.value).length == 0) {
        if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"不能为空值！");
        FormValidator_focusObject(fieldObj, nodeObj);
        return false;
    }
    return true;
}
/*
*   被检查的域中为空或者填入的是一个合法的整数
*/
function Normal_checkInt(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isInt(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个整数！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   被检查的域中为空或者填入的是一个合法的浮点数
*/
function Normal_checkFloat(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isFloat(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个数值！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * 被检查的域为空或填入的是一个合法的电话号码
 */
function Normal_checkPhone(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isPhone(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个正确的电话号码格式！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * 被检查的域为空或填入的是一个合法的邮政编码
 */
function Normal_checkZip(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isZip(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个正确的邮政编码格式！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * 被检查的域为空或填入的是一个合法的邮箱
 */
function Normal_checkEmail(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isEmail(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个正确的邮箱格式！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * 被检查的域为空或填入的是必须是中文
 */
function Normal_checkChinese(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isChinese(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个中文！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * 被检查的域为空或填入的是一个必须是一个英文
 */
function Normal_checkEnglish(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isEnglish(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"必须是一个英文字母或数字！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
function Range_checkObject(fieldObj, nodeObj, checkFunc) {
    if (fieldObj) {
        if (isArrayObject(fieldObj)) {
            for (var i = 0; i < fieldObj.length; i++) {
                if (!checkFunc(fieldObj[i], nodeObj)) {
                    return false;
                }
            }
        } else {
            return checkFunc(fieldObj, nodeObj);
        }
    }
    return true;
}

/*
*   rangeStart<=被检查的域的值的长度大于<=rangeEnd,被检查的域的值必须是字符串
*/
function Range_checkLength(fieldObj, nodeObj) {
    var va = fieldObj.value;
    if (!isNull(va)) {
        if (!(nodeObj.rangeStart)) {
            if (va.length > parseInt(nodeObj.rangeEnd, 10)) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"中录入的字符串长度不能超过" + nodeObj.rangeEnd + "个字符！");
                FormValidator_focusObject(fieldObj, nodeObj);
                return false;
            }
        } else if (!(nodeObj.rangeEnd)) {
            if (va.length < parseInt(nodeObj.rangeStart, 10)) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"中录入的字符串长度不能少于" + nodeObj.rangeStart + "个字符！");
                FormValidator_focusObject(fieldObj, nodeObj);
                return false;
            }
        } else {
            if ( (va.length < parseInt(nodeObj.rangeStart, 10)) || (va.length > parseInt(nodeObj.rangeEnd, 10)) ) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"中录入的字符串长度要在" + nodeObj.rangeStart + "个字符到" + nodeObj.rangeEnd + "个字符之间！");
                FormValidator_focusObject(fieldObj, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*   被检查的域的值<rangeStart,被检查的域的值必须是浮点数或者整数
*/
function Range_checkLesser(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) >= parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"的值必须小于" + nodeObj.rangeStart + "！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   被检查的域的值<=rangeStart,被检查的域的值必须是浮点数或者整数
*/
function Range_checkEquLesser(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) > parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"的值必须小于等于" + nodeObj.rangeStart + "！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   rangeStart<被检查的域的值,被检查的域的值必须是浮点数或者整数
*/
function Range_checkGreater(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) <= parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"的值必须大于" + nodeObj.rangeStart + "！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   rangeStart<=被检查的域的值,被检查的域的值必须是浮点数或者整数
*/
function Range_checkEquGreater(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) < parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"的值必须大于等于" + nodeObj.rangeStart + "！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}


/*
*   rangeStart=被检查的域的值,被检查的域的值必须是浮点数或者整数
*/
function Range_checkEqual(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) != parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"的值必须等于" + nodeObj.rangeStart + "！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   rangeStart<被检查的域的值大于<rangeEnd,被检查的域的值必须是浮点数或者整数
*/
function Range_checkBetween(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if ( (parseFloat(va) <= parseFloat(nodeObj.rangeStart)) || (parseFloat(va) >= parseFloat(nodeObj.rangeEnd)) ){
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"的值必须在" + nodeObj.rangeStart + "和" + nodeObj.rangeEnd + "之间！");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

function Relation_checkObject(fieldObj1, fieldObj2, nodeObj, checkFunc) {
    if (fieldObj1) {
        if (isArrayObject(fieldObj1)) {
            for (var i = 0; i < fieldObj1.length; i++) {
                if (!checkFunc(fieldObj1[i], fieldObj2[i], nodeObj)) {
                    return false;
                }
            }
        } else {
            return checkFunc(fieldObj1, fieldObj2, nodeObj);
        }
    }
    return true;
}

/*
*   field1<field2
*/
function Relation_checkLesser(fieldObj1, fieldObj2, nodeObj) {
    var data1 = fieldObj1.value;
    var data2 = fieldObj2.value;
    if ( (!isNull(data1)) && (!isNull(data2)) ) {
        if ((nodeObj.isNumber) && (1 == nodeObj.isNumber)) {
            data1 = parseFloat(data1);
            data2 = parseFloat(data2);
            if (data1 >= data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"必须小于\"" + nodeObj.fieldTitle2 + "\"！");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        } else {
            if (data1 >= data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"必须小于\"" + nodeObj.fieldTitle2 + "\"！");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*   field1<=field2
*/
function Relation_checkLesserEqual(fieldObj1, fieldObj2, nodeObj) {
    var data1 = fieldObj1.value;
    var data2 = fieldObj2.value;
    if ( (!isNull(data1)) && (!isNull(data2)) ) {
        if ((nodeObj.isNumber) && (1 == nodeObj.isNumber)) {
            data1 = parseFloat(data1);
            data2 = parseFloat(data2);
            if (data1 > data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"必须小于等于\"" + nodeObj.fieldTitle2 + "\"！");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        } else {
            if (data1 > data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"必须小于等于\"" + nodeObj.fieldTitle2 + "\"！");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*   field1<=field2
*/
function Relation_checkEqual(fieldObj1, fieldObj2, nodeObj) {
    var data1 = fieldObj1.value;
    var data2 = fieldObj2.value;
    if ( (!isNull(data1)) && (!isNull(data2)) ) {
        if ((nodeObj.isNumber) && (1 == nodeObj.isNumber)) {
            data1 = parseFloat(data1);
            data2 = parseFloat(data2);
            if (data1 != data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"必须等于\"" + nodeObj.fieldTitle2 + "\"！");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        } else {
            if (data1 != data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"必须等于\"" + nodeObj.fieldTitle2 + "\"！");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*  执行所有checkPoint检查点的检查,所有检查都通过返回true,当有一个不通过时立即返回false
*  vObj 要执行检查的FormValidator对象
*
*/
function FormValidator_checkAllCheckPoint(vObj) {
    var node;
    var command;
    var formObjStr = "document." + vObj.formName;
    var result;
    for (var i = 0; i < vObj.allCheckList.length; i++) {
        node = vObj.allCheckList[i];
        if (1 == node.funcType) {//NormalCheckPoint检查点的检查
            command = "Normal_checkObject(" + formObjStr + "." + node.fieldName + ", node, " + node.checkType + ")";
        } else if (2 == node.funcType) {//RangeCheckPoint检查点的检查
            command = "Range_checkObject(" + formObjStr + "." + node.fieldName + ", node, " + node.checkType + ")";
        } else if (3 == node.funcType) {//RelationCheckPoint检查点的检查
            command = "Relation_checkObject(" + formObjStr + "." + node.fieldName1 + "," + formObjStr + "." + node.fieldName2 + ", node, " + node.checkType + ")";
        }
        result = eval(command);
        if (!result) {
            return false;
        }
    }
    return true;
}

/*
*  执行所有检查点的检查,所有检查都通过返回true,当有一个不通过时立即返回false
*/
function FormValidator_checkAll() {
    if (!FormValidator_checkAllCheckPoint(this)) {
        return false;
    }
    return true;
}

//缺省的form校验器
var validator = new FormValidator();
