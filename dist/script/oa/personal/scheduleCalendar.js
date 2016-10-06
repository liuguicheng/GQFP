$(document).ready(function() {
  var date = new Date();
  var d = date.getDate();
  var m = date.getMonth();
  var y = date.getYear();
  $('#calendar').fullCalendar({
    header: {
      left: 'today',
      center: 'prevYear,prev,title,next,nextYear',
      right: 'month,agendaWeek,agendaDay'
    },

    monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
    monthNamesShort: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
    dayNames: ['周日','周一','周二','周三','周四','周五','周六'],
    dayNamesShort: ['日','一','二','三','四','五','六'],
    firstDay: 1,
    allDayText: '全天',
    buttonText: {
      prev: "<span class='fc-text-arrow' title='上一月'>&lsaquo;</span>",
      next: "<span class='fc-text-arrow' title='下一月'>&rsaquo;</span>",
      prevYear: "<span class='fc-text-arrow' title='上一年'>&laquo;</span>",
      nextYear: "<span class='fc-text-arrow' title='下一年'>&raquo;</span>",
      today: '今天',
      month: '月',
      week: '周',
      day: '日'
    },
    
    currentTimezone: 'Asia/Beijing',
    ignoreTimezone: true,
    lazyFetching: true,
    startParam: 'start',
    endParam: 'end',
    
    theme: false,
    selectable: true,
    select: function (startDate, endDate, allDay, jsEvent, view) {
      var startD = jQuery.fullCalendar.formatDate(startDate,'yyyy-MM-dd HH:mm:ss');
      var endD = jQuery.fullCalendar.formatDate(endDate,'yyyy-MM-dd HH:mm:ss');
      showScheduleDlg(startD,endD);
    },
    eventClick: function (event) {
      var obj = new Object();
      obj.startTime = jQuery.fullCalendar.formatDate(event.start, 'yyyy-MM-dd HH:mm:ss');
      obj.endTime = jQuery.fullCalendar.formatDate(event.end, 'yyyy-MM-dd HH:mm:ss');
      obj.scheduleId = event.scheduleId;
      obj.title = event.title;
      obj.content = event.content;
      obj.remindTime = event.remindTime;
      obj.allDay = event.allDay;
      // 显示窗口
      clickShowScheduleDlg(obj);
    },
    events: "../oa/personalScheduleSelect.action"
  }); 
});

// 显示日程安排对话框
function showScheduleDlg (start,end) {
  clearInfo();
  $("#divScheduleDlg").dialog({
    width: 800,
    height: 310,
    modal: true,
    resizable: false,
    draggable: false,
    buttons: [
      { text:'保存',handler:selectSchedule },
      { text:'关闭',handler:function () { $("#divScheduleDlg").dialog("close"); } }
    ]
  });
  $("#divScheduleDlg").dialog("open");
  // 设置窗口参数
  $("#startTime").val(start);
  $("#endTime").val(end);
}

// 点击显示日程安排对话框
function clickShowScheduleDlg (data) {
  $("#scheduleId").val(data.scheduleId);
  $("#title").val(data.title);
  $("#content").val(data.content);
  $("#startTime").val(data.startTime);
  $("#endTime").val(data.endTime);
  $("#remindTime").val(data.remindTime)
  $("#content").val(data.content);
  if (data.allDay == true) {
    $(".allDay[value=1]").attr("checked",true);
  } else {
    $(".allDay[value=0]").attr("checked",true);
  }
  $("#divScheduleDlg").dialog({
    width: 800,
    height: 310,
    modal: true,
    resizable: false,
    draggable: false,
    buttons: [
      { text:'保存',handler:selectSchedule },
      { text:'删除',handler:deleteSchedule },
      { text:'关闭',handler:function () { $("#divScheduleDlg").dialog("close"); } }
    ]
  });
  $("#divScheduleDlg").dialog("open");  
}

// 清空窗口信息
function clearInfo () {
  $("#scheduleId").val("");
  $("#title").val("");
  $("#content").val("");
  $("#startTime").val("");
  $("#endTime").val("");
  $("#remindTime").val("")
  $("#content").val("");
  $(".allDay[value=0]").attr("checked",true);
}

// 查询日程安排
function selectSchedule() {
  var scheduleId = $("#scheduleId").val();
  var id = "";
  var title = $("#title").val();
  var startTime = $("#startTime").val();
  var endTime = $("#endTime").val();
  var remindTime = $("#remindTime").val();
  var content = $("#content").val();
  var isMsg = $(".isMsg:checked").val();
  var allDay = true;
  if ($(".allDay").filter(":checked").val()=="0") {
    allDay = false;
  }
  var time = new Date();
  // 查询是否已经存在日程
  jQuery.getJSON(
    '../oa/personalScheduleSelectByDate.do?time=' + time, 
    { 
      scheduleId: scheduleId, 
      start: startTime,
      end: endTime
    }, 
    function(json, textStatus) {
      var obj = new Object();
      if (json == null) {
        obj.id = id;
      } else {
        obj.id = json.scheduleId;
      }
      obj.scheduleId = scheduleId;
      obj.title = title;
      obj.content = content;
      obj.start = startTime;
      obj.end = endTime;
      obj.remindTime = remindTime;
      obj.allDay = allDay;
      obj.isMsg = isMsg;
      saveSchedule(obj);
  });
  
}

// 保存日程安排
function saveSchedule (data) {
  var d1 = new Date(data.start.replace(/-/g,"/"));
  var d2 = new Date(data.end.replace(/-/g,"/"));
  if (jQuery.trim(data.title) == "") {
    alert("请输入标题！");
  } else if (jQuery.trim(data.start) == "") {
    alert("请输入开始时间！");
  } else if (jQuery.trim(data.end) == "") {
    alert("请输入结束时间！");
  } else if ((Date.parse(d2) - Date.parse(d1)) <= 0) {
    alert("结束时间不能小于等于开始时间！");
  } else {
    var saveFlag = false;
    if (data.id != "") {
      // 日程有冲突
      if (confirm("您的日程安排与其他日程安排有冲突，您确定添加该日程吗？")) {
        saveFlag = true;
      }
    } else {
      // 日程无冲突
      if (confirm("您确定要保存该日程吗？")) {
        saveFlag = true;
      }
    }

    if (saveFlag) {
      // 保存日程
      var time = new Date();
      jQuery.post(
        '../oa/personalScheduleEdit.do?time=' + time, 
        {
          scheduleId: data.scheduleId,
          title: data.title,
          content: data.content,
          startTime: data.start,
          endTime: data.end,
          remindTime: data.remindTime,
          allDay: data.allDay,
          isMsg: data.isMsg
        }, 
        function(data, textStatus, xhr) {
          alert("保存成功！");
          clearInfo();
          // 刷新
          $("#calendar").fullCalendar("refetchEvents");
          // 关闭窗口
          $("#divScheduleDlg").dialog("close");
      });
      
    }
  }
}

// 删除日程安排
function deleteSchedule () {
  var scheduleId = $("#scheduleId").val();
  if (confirm("您确定要删除该日程吗？")) {
    var time = new Date();
    jQuery.post(
      '../oa/personalScheduleDelete.do?time' + time, 
      { id: scheduleId }, 
      function(data, textStatus, xhr) {
        alert("删除成功！");
        clearInfo();
        // 刷新
        $("#calendar").fullCalendar("refetchEvents");
        // 关闭窗口
        $("#divScheduleDlg").dialog("close");        
    });
    
  }
}