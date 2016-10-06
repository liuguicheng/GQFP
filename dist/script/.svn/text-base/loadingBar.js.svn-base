// 生成并显示进度条，放到首页，由首页创建，其他地方控制显示，避免重复创建
///document.write('<div id="LoadingBox" class="LoadingBar" >&nbsp;正在加载，请稍候……</div>');


//显示进度条
function showLoadingBar() {
    var box = top.document.getElementById("LoadingBox");
    if (box) {
        box.style.display = "";
    }
    //alert(top.document.getElementById("LoadingBox").style);
    //$('PendingMessage').style.display = "block";
}
//隐藏进度条
function hiddenLoadingBar() {
    var box = top.document.getElementById("LoadingBox");
    if (box) {
        box.style.display = "none";
    }

}

//设置DivScroll的宽度
function setDivScroll() {
    var divs = document.getElementsByTagName("div");
    for (var i = 0; element = divs[i]; i++) {
        if ((" "+ element.className + " ").indexOf(" DivScroll ") != -1){
             element.style.width = document.body.clientWidth - 5;
             //alert(element.parentNode.nodeType + "-" + element.parentNode.nodeName);
        }
    }
}

if (window.attachEvent){
    window.attachEvent("onload", hiddenLoadingBar);//对于IE
    window.attachEvent("onload", setDivScroll);//对于IE
//    window.attachEvent("onresize", setDivScroll);//对于IE
//    window.attachEvent("onunload", showLoadingBar);//对于IE
} else {
    window.addEventListener("load",hiddenLoadingBar,false);//对于FireFox
//    window.addEventListener("unload",showLoadingBar,false);//对于FireFox
}
