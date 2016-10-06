/*
* 数字应急系统消息台页面脚本
* Copyright (c) 2014 CERC HuangAnzhi
* huanganzhi@cerc.net.cn
* Version 1.0.0 build 20141215
* Requires jquery v1.8.0+
*/

var chatTools = {
    addChatInfo: function (isSelf, sender, headImg, msg, sendTime) {
        /// <summary>在聊天面板中添加聊天信息
        /// <para>isSelf：是否是自己发送的消息 ；sender：发送人； headImg：头像； msg：消息内容，请对消息内容进行HTML转码后显示，否者会生成DOM元素； sendTime：消息发送时间</para>
        /// </summary>
        if (typeof (msg) != 'undefined' && msg != '') {

            var html = "<li class=\"ChatItem " + (isSelf ? "Self" : "") + "\">\r\n" +
                             "<div class=\"Head\" ><img src=\"" + headImg + "\" /></div>\r\n" +
                             "<div class=\"Split\"></div>\r\n" +
                             "<div class=\"Content\">\r\n" +
                             "<h2 class=\"SenderInfo\"><span class=\"Name\">" + sender + "</span><span class=\"Time\">" + sendTime + "</span></h2>" +
                             "<div class=\"ChatContent\">" + msg + "</div>\r\n" +
                             "</div><div class=\"ClearFix\"></div>\r\n" +
                             "</li>";

            $(".ChatList").append(html);
            if (isSelf) {
                $("#txtContent").val('');
                $("#txtContent").focus();
            }
            $(".ChatContainer").mCustomScrollbar("update");
            $(".ChatContainer").mCustomScrollbar("scrollTo", "bottom");
            html = null;
        }
    }

};

$(document).ready(function () {
    /*$("#btnSend").click(function () {

        var content = $("#txtContent").val();
        var sender = "八戒";
        var headImg = "images/user4.png";

        //消息发送效果测试
        chatTools.addChatInfo(true, sender, headImg, content, new Date());
    });*/
    $(".ChatContainer").mCustomScrollbar({ theme: "dark" });


    $("#ckbKey").click(function () {
        $("#txtContent").focus();
        if ($(this).attr("checked")) {
            $("#txtContent").bind("keydown", function (e) {
                if (e.keyCode == 13) {
                	//发送消息
                	doSend();
                    //这里必须返回false值，否则消息发送之后，输入框将出现换行，影响效果
                    return false;
                }
            });
        }
        else {
            $("#txtContent").unbind("keydown");
        }
    });
});