/*****************************************************************************************************
 * 统一设置名称相同的一类checkbox的选择状态，用于checkbox的全选控制
 * $param @name checkbox 的name值
 * $param @value true 全选 false 取消全选
****************************************************************************************************/
function setCheckBoxValue(name, value) {
    var dataList = document.getElementsByName(name);
    if (!dataList || (dataList.length <= 0)) {
        return;
    } else if (isArrayObject(dataList)) {
        for (i=0; i < dataList.length; i++) {
            if (!dataList[i].disabled && dataList[i].value) {
                dataList[i].checked = value;
            }
        }
    } else {
        dataList.checked = value;
    }
}

/*****************************************************************************************************
 * 判断obj是否是数组对象
 * $param @obj checkbox 的name值
 * $return true 数组对象 false 单一对象
****************************************************************************************************/
function isArrayObject(obj) {
    return (obj.length && (!obj.tagName));
}
/*****************************************************************************************************
 * 判断checkbox是否有选择项
 * $param @name 要判断的对象
 * $return true 有 false 没有选择
****************************************************************************************************/
function hasCheckBoxSelect(name) {
    var dataList = document.getElementsByName(name);
    if (!dataList || (dataList.length <= 0)) {
        return false;
    } else if (isArrayObject(dataList)) {
        for (i=0; i < dataList.length; i++) {
            if (dataList[i].checked) {
                return true;
            }
        }
    } else {
        if (dataList.checked) {
            return true;
        }
    }
    return false;
}
/*****************************************************************************************************
 * 切换Div的显示状态，多用于查询块的显示和隐藏
 * $param @DivId 要控制div对象Id
****************************************************************************************************/
function showDiv(DivId,width)  {
/*
    divobj = eval(DivId);
    if(divobj.style.display =="none")  {
        divobj.style.display = "";
    } else  {
        divobj.style.display = "none";
    }
    */
    width = width || 800;
    var divobj = $("#" + DivId);
    if(divobj.is(":visible")) {
        divobj.searchBox("close");
    } else  {
        divobj.searchBox({title: "信息查询", width: width });
    }
}

/////////////////////////////////////////////////////
//该函数用于将源列表框的选中项移到目标列表框                 //
//source_list:源列表框                                   //
//target_list:目标列表框                                 //
/////////////////////////////////////////////////////
function listbox_item_move(source_list,target_list) {
    var source_len=source_list.options.length;
    for(i=0;i<source_len;i++)
    {
       if(source_list.options[i].selected && listbox_item_find(source_list.options[i].value,target_list))
       {
           source_list.options[i]=null;
          source_len=source_len-1;
          i=i-1;
       }
        else if(source_list.options[i].selected && !listbox_item_find(source_list.options[i].value,target_list))
        {
            new_option=new Option(source_list.options[i].text,source_list.options[i].value);
            var len=target_list.options.length;
          target_list.options[len]=new_option;
          source_list.options[i]=null;
          source_len=source_len-1;
          i=i-1;
       }
  }
}
/////////////////////////////////////////////////////
//该函数用于将源列表框的选中项移到目标列表框(仅能选择一项)                 //
//source_list:源列表框                                   //
//target_list:目标列表框                                 //
/////////////////////////////////////////////////////
function listbox_single_item_move(source_list, target_list) {
    var source_len = source_list.options.length;
    if (target_list.options.length > 0) {
        new_option = new Option(target_list.options[0].text, target_list.options[0].value);
        source_list.options[source_len] = new_option;
        target_list.options[0] = null;
        source_len++;
    }
    for (i = 0; i < source_len; i++) {
        if (source_list.options[i].selected && listbox_item_find(source_list.options[i].value, target_list)) {
            source_list.options[i] = null;
            source_len = source_len - 1;
            break;
        } else if (source_list.options[i].selected && !listbox_item_find(source_list.options[i].value, target_list)) {
            new_option = new Option(source_list.options[i].text, source_list.options[i].value);
            var len = target_list.options.length;
            target_list.options[len] = new_option;
            source_list.options[i] = null;
            source_len = source_len - 1;
            break;
        }
    }
}

//////////////////////////////////////////////////////////
//该函数用于查找目标列表框中是否有源列表框中的某列表项  //
//source_item:待查的源列表框中的某列表项的值            //
//target_list:目标列表框                                //
//////////////////////////////////////////////////////////
function listbox_item_find(source_item,target_list){
    var target_len=target_list.options.length;
    for (var i=0;i<target_len;i++)
  {
       if(target_list.options[i].value==source_item)
    return true
  }
  return false;
}

/////////////////////////////////////////////////////////
//该函数用于清除列表框中的所有列表项                   //
//listbox_sel:要清除的列表框                           //
/////////////////////////////////////////////////////////
function listbox_clear(listbox_sel){
    var listbox_sel_len=listbox_sel.options.length;
    for (var i=0;i<listbox_sel_len;i++)
  {
      listbox_sel.options[0]=null;
  }
}

/////////////////////////////////////////////////////
//该函数用于将源列表框的选中项增加目标列表框       //
//source_list:源列表框                             //
//target_list:目标列表框                           //
/////////////////////////////////////////////////////
function listbox_item_add(source_list,target_list) {
    var source_len=source_list.options.length;
    for(i=0;i<source_len;i++)
    {
      if(source_list.options[i].selected && !listbox_item_find(source_list.options[i].value,target_list))
      {
          new_option=new Option(source_list.options[i].text,source_list.options[i].value);
            var len=target_list.options.length;
          target_list.options[len]=new_option;
       }
  }
}

/////////////////////////////////////////////////////
//该函数用于将源列表框的选中项清除                   //
//source_list:源列表框                             //
//target_list:目标列表框                           //
/////////////////////////////////////////////////////
function listbox_selecteditem_move(source_list) {
    var source_len=source_list.options.length;
    for(i=0;i<source_len;i++)
    {
       if(source_list.options[i].selected )
       {
           source_list.options[i]=null;
          source_len=source_len-1;
          i=i-1;
       }

    }

}




/**
 * 重定向到指定页面 -> 不再建议使用
 * reqUrl：要重定向到的URL
 */
function submitRequest(reqUrl) {
    location.href = reqUrl;
}


/**
 * 封装打开对话框操作
 */
var FCKDialog = new Object();
/**
 * 打开一个对话框
 * dialogName   ：对话框的名字
 * dialogTitle  ：对话框的标题
 * dialogPage   ：对话框显示页面的URL
 * width        ：对话框的宽度
 * height       ：对话框的高度
 * customValue  ：传递给对话框的自定义对象（在对话框内嵌页面中可通过window.parent.dialogArguments.CustomValue取回）
 * parentWindow ：用来打开的对话框的窗口（对话框的父窗口）
 */
FCKDialog.OpenDialog = function(dialogName, dialogTitle, dialogPage, width, height, customValue, parentWindow) {
    var oDialogInfo = new Object();
    oDialogInfo.Title = dialogTitle;
    oDialogInfo.Page = dialogPage;
    oDialogInfo.OpenWindow = window;
    oDialogInfo.CustomValue = customValue;

    if (!parentWindow) {
        parentWindow = window;
    }
    var sUrl = '../html/fckdialog.html';
    this.Show(oDialogInfo, dialogName, sUrl, width, height, parentWindow);
}
if (document.all) { // IE
    FCKDialog.Show = function(dialogInfo, dialogName, pageUrl, dialogWidth, dialogHeight, parentWindow) {
        parentWindow.showModalDialog(pageUrl, dialogInfo, "dialogWidth:" + dialogWidth + "px;dialogHeight:" + dialogHeight + "px;help:no;scroll:no;status:no;resizable:yes;");
    }
} else {
    FCKDialog.Show = function(dialogInfo, dialogName, pageUrl, dialogWidth, dialogHeight, parentWindow) {
        var iTop = (screen.height - dialogHeight) / 2;
        var iLeft = (screen.width - dialogWidth) / 2;
        var sOption = "location=no,menubar=no,resizable=no,toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,width=" + dialogWidth + ",height=" + dialogHeight + ",top=" + iTop + ",left=" + iLeft;
        var oWindow = parentWindow.open('', 'FCKEditorDialog_' + dialogName, sOption, true);
        oWindow.moveTo(iLeft, iTop);
        oWindow.resizeTo(dialogWidth, dialogHeight);
        oWindow.focus();
        oWindow.location.href = pageUrl;
        oWindow.dialogArguments = dialogInfo;
        parentWindow.FCKLastDialogInfo = dialogInfo;
    }
}

/**
 * 去左空格
 */
function ltrim(s) {
    return s.replace(/^\s*/, "");
}
/**
 * 去右空格
 */
function rtrim(s) {
    return s.replace(/\s*$/, "");
}
/**
 * 去左右空格
 */
function trim(s) {
    return rtrim(ltrim(s));
}

function isLong(s) {
   if(s=="null"||s=="undefined"||s.length<1)
      return false;
   if(isNaN(parseFloat(s)))
      return false;
   if (new String(parseFloat(s)).length != s.length)
      return false;
   if (s.indexOf(".") != -1)
      return false;
   return true;
}
/**
 * 判断是否为浮点数、正浮点数、负浮点数、正浮点数+0、负浮点数+0
 */
function isMulFloat(objStr, sign, zero) {
    var reg;
    var bolzero;

    if (trim(objStr) == "") {
        return true;
    } else {
        objStr = objStr.toString();
    }

    switch (sign) {
        case "+-":
            //浮点数
            reg = /^((-?|\+?)\d+)(\.\d+)?$/;
            break;
        case "+":
            if (!bolzero) {
                //正浮点数
                reg = /^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            } else {
                //正浮点数+0
                reg = /^\+?\d+(\.\d+)?$/;
            }
            break;
        case "-":
            if (!bolzero) {
                //负浮点数
                reg = /^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            } else {
                //负浮点数+0
                reg = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
            }
            break;
        default:
            reg = /^((-?|\+?)\d+)(\.\d+)?$/;
            break;
    }

    var r = objStr.match(reg);
    if (r == null) {
        return false;
    } else {
        return true;
    }
}





function findRowIndex(node) {
    var depth=5;
    var target = node.parentNode;
    while ((target) && (depth-- > 0)) {
        if (target.nodeName == 'TR') {
            return target.rowIndex;
        }
        target = target.parentNode;
    }
    return null;
}

/**
 * 表格处理
 */
function addMore(id,name){
    var table=document.getElementById("sources");
    var co=table.rows.length;
      var row=table.insertRow(co);
      var co1=row.insertCell();
      //co1.className="TdCommand";
      co1.align="left";

      var co2=row.insertCell();
      co2.align="center";

      var co3=row.insertCell();
    co3.align="center";

      var co4=row.insertCell();
    co4.align="center";

      var co5=row.insertCell();
    co5.align="center";

    //  var co6=row.insertCell();
    //co6.align="center";

    var co7=row.insertCell();
    co7.align="center";

      co1.innerHTML='<lable align="center">'+name+'</lable> <input type="hidden" name="depid"  value="'+id+'" />';
      co2.innerHTML='<input type="checkbox"  value="add'+id+ '" onclick="javascript:setHidden(this);" checked  /> <input type="hidden" name="add" id="add'+id+'"  value="1"/>';
     co3.innerHTML='<input type="checkbox"  value="edit'+id+ '" onclick="javascript:setHidden(this);"  /> <input type="hidden" name="edit" id="edit'+id+'"  value="0"/>';
     co4.innerHTML='<input type="checkbox"  value="delete'+id+ '" onclick="javascript:setHidden(this);"  /> <input type="hidden" name="delete" id="delete'+id+'"  value="0"/>';
     co5.innerHTML='<input type="checkbox"  value="look'+id+ '" onclick="javascript:setHidden(this);"  checked/> <input type="hidden" name="look" id="look'+id+'"  value="1"/>';
//    co6.innerHTML='<input type="checkbox"  value="manager'+id+ '" onclick="javascript:setHidden(this);"  /> <input type="hidden" name="manager" id="manager'+id+'"  value="0"/>';
    co7.innerHTML='<div class="DivButton"><a href="#" class="btnDel" onclick="delly();"> 删除&nbsp; </a></div> '
  }

  function  delly(){
      if(window.confirm('你确定删除吗?')){
        document.all.sources.deleteRow(window.event.srcElement.parentElement.parentElement.parentElement.rowIndex);
      }
     }


/**
 * 标签对象
 */
function TabContain(jsonObj,name){
  //标签名称与对应的页面所组成的json对象
  this.contain = jsonObj;
  this.name = name;
}

TabContain.prototype.switchTab = function(id){
  if(this.contain != null){
    for(var key in this.contain){
      var bd = document.getElementById(key);
        if(key == id){
          bd.style.display = "";
        }else{
          bd.style.display = "none";
        }
    }
  }else{
    alert("请初始化TabContain！");
  }
}

TabContain.prototype.initButton = function(){
  var a = "&nbsp;|&nbsp;";
  for(var key in this.contain){
      a += "<a href=javascript:; onclick=javascript:"+this.name+".switchTab('"+key+"');>"+this.contain[key]+"</a>&nbsp;|&nbsp;";
  }
  return a;
}

/**
 * 清空查询条件
 */
function clearQuery(form){
  if(form != null){
    for(var i = 0 ; i<form.length;i++){
      if(form[i].type=="text"){//表单元素为input text
        form[i].value="";
      }else if (form[i].type == "select-one") {//表单元素为select
        form[i].selectedIndex = 0;
      }else if (form[i].type == "radio") {//表单元素为radio
        form[i].checked = false;
      }
    }
  }else{
    alert("请设置查询表单！");
  }
}

function clearAllQuery(form) {
  if (form != null) {
    for (var i = 0; i < form.length; i++) {
      if(form[i].type=="text"){//表单元素为input text
            form[i].value="";
          }else if (form[i].type == "select-one") {//表单元素为select
            form[i].options.value= "";
          }else if (form[i].type == "radio") {//表单元素为radio
            form[i].value= "";
          } else if (form[i].type == "hidden") {
            form[i].value= "";
          }
    }
  } else {
      alert("请设置查询表单！");
  }
}
/**
 * 设置iframe的高度与iframe内的页面相同
 *
 * @param frameObj
 * @return
 */
function loadFrame(frameObj){
  var control=frameObj;
    if(control){
    if(document.all){
      control.height=control.Document.body.scrollHeight;
      control.width=control.Document.body.scrollWidth;
    }else{
      control.height=control.contentDocument.body.offsetHeight;
      control.width=control.contentDocument.body.offsetHeight;
    }
  }
}

function reinitIframe(frame_content){
  var iframe = document.getElementById(frame_content);
    try{
      if(iframe.all){
        var bHeight = iframe.contentWindow.document.body.scrollHeight;
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(bHeight, dHeight);
            iframe.height =  height;
      }else{
        iframe.height=iframe.contentDocument.body.offsetHeight;
        iframe.width=iframe.contentDocument.body.offsetHeight;
      }

    }catch (ex){

  }
}


function showTbody(tBodyId) {
  var showBodys = document.getElementsByTagName("tbody");
  for(i = 0;i < showBodys.length;i++){
    if(showBodys[i].id == tBodyId){
      showBodys[i].style.display = "";
    }else if(showBodys[i].id != "" && showBodys[i].id != tBodyId){
      showBodys[i].style.display = "none";
    }
  }
}
/**
 * 检查打印控件LODOP是否已安装及升级
 *
 * */
function getLodop(oOBJECT,oEMBED) {
    var strHtml1="<br><br><font color='#FF00FF' size='5'>打印控件未安装!点击这里<a href='../script/install_lodop.exe'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtml2="<br><br><font color='#FF00FF' size='5'>打印控件需要升级!点击这里<a href='../script/install_lodop.exe'>执行升级</a>,升级后请重新进入。</font>";
    var strHtml3="<br><br><br><font color='#FF00FF' size='5'>(注：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】中先卸载它)</font>";
    var LODOP=oEMBED;
  try{
      if (navigator.appVersion.indexOf("MSIE")>=0) LODOP=oOBJECT;

      if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
    if (navigator.userAgent.indexOf('Firefox')>=0)
            document.documentElement.innerHTML=strHtml3+document.documentElement.innerHTML;
    if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml1); else
      document.documentElement.innerHTML=strHtml1+document.documentElement.innerHTML;
      } else if (LODOP.VERSION<"6.0.2.2") {
    if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml2); else
      document.documentElement.innerHTML=strHtml2+document.documentElement.innerHTML;
      }
      return LODOP;
  }catch(err){
      document.documentElement.innerHTML="Error:"+strHtml1+document.documentElement.innerHTML;
      return LODOP;
  }
}
/**
 * 验证短年月 2010-02
 * @param str
 * @return
 */
function strmonth(str)
{
var r = str.match(/^(\d{4})(-)(\d{2})$/);
if(r==null){
  return false;
}else{
  return r[0] == str;
}
}

//取多选框的第一个选中的数据
function getFirstSigleData(dataList) {
  if (dataList.options.length <= 0) {
        return "";
    } else {
        for (var i=0; i < dataList.options.length; i++) {
            if (dataList.options[i].selected) {
                return dataList.options[i].value;
            }
        }
    }
    return "";
}

/**
 * 打开一个新的ajax请求
 * Url   ：请求的地址
 * callbackfunc  ：回调函数
 * Jyb 对ajax的简单应用方式进行封装
 */
function doAjaxRequest(Url, callbackfunc) {
    var xmlReq = false;
    try {
        xmlReq = new XMLHttpRequest();
    } catch (trymicrosoft) {
        try {
            xmlReq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (othermicrosoft) {
            try {
                xmlReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (failed) {
                xmlReq = false;
            }
        }
    }
    if (!xmlReq) {
        alert("Error initializing XMLHttpRequest!");
        return;
    }

    xmlReq.open("GET", Url, true);
    xmlReq.onreadystatechange = onReadyStateChangeForXmlReq;
    function onReadyStateChangeForXmlReq() {
        if (xmlReq.readyState == 4) {
            if (xmlReq.status == 200) {
                callbackfunc(xmlReq.responseText);
            } else {
                alert("服务器无法响应该请求！");
            }
        }
    }

    xmlReq.send(null);
}

/**
 * 日期格式化
 */
Date.prototype.format = function (format) {
  var o = {
    "M+": this.getMonth()+1,// month
    "d+": this.getDate(),//day
    "H+": this.getHours(),//hour
    "m+": this.getMinutes(),//minute
    "s+": this.getSeconds(),//second
    "q+": Math.floor((this.getMonth()+3)/3),//quarter
    "S": this.getMilliseconds()//millisecond
  }
  if (/(y+)/.test(format)) {
    format = format.replace(RegExp.$1,(this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for(var k in o){
    if (new RegExp("(" + k + ")").test(format)) {
      format = format.replace(RegExp.$1,RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    }
  }
  return format;
};