var WebAnnexManager = new Object();

//判断浏览器类型
var Sys = new Object();
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
(s = ua.match(/trident.*rv:([\d.]+)/)) ? Sys.ie11 = s[1] : 
(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0 ;   

// 添加控件到页面
WebAnnexManager.addOcx = function (divId, id, width, height) {
  if (Sys.ie || Sys.ie11) {
    $("#" + divId).append("<OBJECT id='" + id + "' " 
      + "classid='clsid:2F86DF3A-E829-4995-9E84-7480DD7FFD2D' " 
      + "codebase='../lib/webannex/WebAnnexManager.ocx#version=1,2,0,0' " 
      + "width='" + width + "' height='" + height + "' " 
      + "align='center' hspace='0' vspace='0'></OBJECT>");
  } else if (Sys.firefox || Sys.chrome) {
    $("#" + divId).append("<OBJECT id='" + id + "' " 
      + "TYPE='application/x-itst-activex' " 
      + "clsid='{2F86DF3A-E829-4995-9E84-7480DD7FFD2D}' " 
      + "progid='' " 
      + "codebase='../lib/webannex/WebAnnexManager.ocx#version=1,2,0,0' " 
      + "width='" + width + "' height='" + height + "' " 
      + "align='center' hspace='0' vspace='0'></OBJECT>");
  }
};
// 控件生成情况判断——外部调用
WebAnnexManager.checkOcx = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if (!ocxsample.Font) {
    if (Sys.ie || Sys.ie11) {
      document.write('<br><br><font color=red>警告：如需在线打开公文附件，须做浏览器设置进行控件的安装(浏览器设置如下)<br>“工具”->“Internet选项”->“安全”设置->“自定义级别进行设置,以下均要点击启用：ActiveX控件自动提示、对标记为可安全执行脚本的ActiveX控件初始化并执行脚本、下载未签名的ActiveX控件，然后点击【确定】按钮。”，<br>选择“运行ActiveX控件”，确认之后刷新页面重试。</font>');   
      //<br><font color=red>警告：您的计算机没有下载并安装WEB文件管理器控件！无法直接打开公文附件。<br>请到浏览器菜单找到“工具”->“Internet选项”->“安全”设置->“自定义级别”，<br>选择“运行ActiveX控件”，确认之后刷新页面重试。</font>
    } else if (Sys.chrome || Sys.firefox) {
      document.write("<br><font style='color:red;'>警告：非IE内核浏览器实现附件直接打开需要安装插件！请下载插件&nbsp;<a href='../download/ffactivex-setup-r39.exe' style='font-weight:bold;'>谷歌火狐附件打开插件</a>。</font>");
    }
  }
};
// 初始化控件——外部调用
WebAnnexManager.initOcx = function (controlId, user, id, moduleName, actionUrl) {
  var ocxsample = document.getElementById(controlId);
  if (ocxsample.Font) {
    //不要带有端口
    //var actionUrl = getUri();
    var port = window.location.port;
    if (port == "") {
      port = 80;
    }
    ocxsample.ocxConfig(actionUrl, port, user, moduleName, id);
    return true;
  }
  return false;
};
// 添加文件——外部调用
WebAnnexManager.addOcxFile = function (controlId, fileName, fileCnName, filePath, fileId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.add(fileName, fileCnName, filePath, fileId);
  }
}
// 添加文件，指定OwnerType和OwnerID——外部调用
WebAnnexManager.addOcxOwnerFile = function (controlId, fileName, fileCnName, filePath, fileId, ownerType, ownerId, visible) {
  var ocxsample = document.getElementById(controlId);
  if (ocxsample.Font) {
    ocxsample.addFile(fileName, fileCnName, filePath, fileId, ownerType, ownerId, visible);
  }
};
// 打开文件——外部调用
WebAnnexManager.openFile = function (controlId, fileId) {
  var ocxsample = document.getElementById(controlId);
  if (ocxsample.Font) {
    ocxsample.openFile(fileId);
  }
};
// 上传文件——外部调用
WebAnnexManager.saveOcxFile = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    return ocxsample.saveNewFile();
  }
  return "F";
};
// 设置控件只读——外部调用
WebAnnexManager.setOcxReadOnly = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.ReadOnly = true;
  }
};
// 设置控件容量大小——外部调用
WebAnnexManager.setOcxLimitSize = function (controlId, limitValue) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.LimitSize = limitValue;
  }
  return 0;
};
// 设置控件可以添加新文件——外部调用
WebAnnexManager.setOcxCanAdd = function (controlId,canAdd) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.CanAdd = canAdd;
  }
};
//设置控件可以删除－－外部调用
WebAnnexManager.setOcxCanDelete = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.CanDelete = true;
  }
};
// 设置控件修订状态——外部调用(  0，1，2，3:无修订、隐藏修订、显示修订、合并修订)
WebAnnexManager.setOcxRevisionState = function (controlId, rs) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.RevisionState = rs;
  }
};
// 获取文件个数——外部调用
WebAnnexManager.getOcxFileCount = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    return ocxsample.fileCount;
  }
  return 0;
};
// 判断是否可以套用模板——外部调用
WebAnnexManager.canUseTemplate = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    return ocxsample.canUseTemplate;
  }
  return false;
};
// 套用模板——外部调用
WebAnnexManager.useTemplate = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.template(templateFile, templatePath, tempInfo);
  }
};
// 清理控件——外部调用
WebAnnexManager.clearOcx = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.clear();
  }
};
