/*
* Jquery FormatInput Plugin
* Copyright (c) 2012-2013 CERC HuangAnzhi 2013-02-20
* huanganzhi@cerc.net.cn
* Version 1.0.0 build 20130422
* Requires jquery v1.4.4+
*/
(function ($) {

    var currentPath = {};

    var dynamicLoadExtJs = false;

    var browser = {
        ie: false,
        ie6: false,
        ie8: false,
        firefox: false,
        safari: false,
        opera: false,
        version: -1
    };

    var formatInputVariable = {
        skinName: "default",
        wrapClass: "indieui-form",
        scriptName: "jquery.formatinput.js",
        cssName: "jquery.formatinput.css",
        pluginName: "Jquery FormatInput"
    };

    var externalPlugin = {
        poshyTip: { js: "jquery.poshytip-1.1/src/jquery.poshytip.min.js", css: "", isLoaded: false },
        processmask: { js: "jquery.processmask/jquery.processmask.js", css: "", isLoaded: false },
        my97DatePicker: { js: "My97DatePicker/WdatePicker.js", css: "", isLoaded: false }
    };

    /*
    自定义调试函数，当浏览器支持FireBug时，将在控制台输出调试信息，否则将使用alert()方法弹出调试信息
    */
    var debug = function (info) {
        if ($.formatInput.defaults.debug) {
            if (typeof console != "undefined" && typeof console.debug != "undefined") {
                console.log('formatInput plugin debug info:' + info);
            } else {
                alert(info);
            }
        }
    };

    /*
    识别浏览器版本
    */
    var useragent = navigator.userAgent.toLowerCase();
    if (useragent.indexOf("opera") != -1) {
        browser.opera = true;
    } else if (useragent.indexOf("msie") != -1) {
        browser.ie = true;
        browser.version = parseFloat(useragent.substring(useragent.indexOf('msie') + 4));
        if (browser.version == 6) {
            browser.ie6 = true;
            browser.ie8 = false;
        }
        else if (browser.version == 8) {
            browser.ie6 = false;
            browser.ie8 = true;
        }
    } else if (useragent.indexOf("safari") != -1) {
        browser.safari = true;
        browser.version = parseFloat(useragent.substring(useragent.indexOf('safari') + 7));
    } else if (useragent.indexOf("gecko") != -1) {
        browser.firefox = true;
    }

    /*
    获取执行脚本所在目录(src)路径，并使用currentPath变量进行保存
    */
    var scriptElements = document.scripts;
    for (var i = scriptElements.length; i > 0; i--) {
        var obj = scriptElements[i - 1];
        if (obj.src.indexOf(formatInputVariable.scriptName) > -1) {
            var url = obj.src.replace('\\', '\/');
            currentPath = url.substring(0, url.lastIndexOf("src/" + formatInputVariable.scriptName));
            break;
        }
    }

    //类扩展
    $.formatInput = {
        //默认属性
        defaults: {
            skin: formatInputVariable.skinName,
            debug: false,
            external: {
                my97DatePicker: true,
                poshyTip: true
            }
        },

        /*
        类函数
        */
        formatInput: function (options) {
            var currentSkin = $.formatInput.defaults.skin;

            $.formatInput.defaults = $.extend({}, $.formatInput.defaults, options);

            if (currentSkin != $.formatInput.defaults.skin) {
                $.formatInput.loadSkin(formatInputVariable.skinName);
            }
            $("." + formatInputVariable.wrapClass).formStyle();
        },

        /*
        加载皮肤主题
        */
        loadSkin: function (skinName) {
            if (currentPath == '') {
                debug(formatInputVariable.pluginName + ' 插件加载异常');
                return false;
            }
            var cssUrl = currentPath + "themes/" + skinName + "/formatinput/" + formatInputVariable.cssName;
            $.formatInput.loadStylesheet(cssUrl);
        },

        /*
        加载脚本
        */
        loadScript: function (scriptUrl, callback) {
            var scriptExits = false;
            var scripts = document.getElementsByTagName("script");
            for (var i = 0; i < scripts.length; i++) {
                if ($(scripts[i]).attr("src") == scriptUrl) {
                    scriptExits = true;
                    break;
                }
            }
            if (!scriptExits) {
                var scriptDom = document.createElement("script");
                scriptDom.type = "text/javascript";
                scriptDom.src = scriptUrl;
                scriptDom.onload = scriptDom.onreadystatechange = function () {
                    if (!scriptDom.readyState || scriptDom.readyState == "loaded" || scriptDom.readyState == "complete") {
                        scriptExits = true;
                        scriptDom.onload = scriptDom.onreadystatechange = null;
                        callback && callback.call(scriptDom);
                    }
                };
                document.getElementsByTagName("head")[0].appendChild(scriptDom);
            }
        },

        /*
        加载样式文件
        */
        loadStylesheet: function (cssUrl, callback) {

            var linkExits = false;
            var links = document.getElementsByTagName("link");
            for (var i = 0; i < links.length; i++) {
                if ($(links[i]).attr("src") == cssUrl) {
                    linkExits = true;
                    break;
                }
            }
            if (!linkExits) {
                var linkDom = document.createElement("link");
                linkDom.rel = "stylesheet";
                linkDom.type = "text/css";
                linkDom.media = "screen";
                linkDom.href = cssUrl;
                document.getElementsByTagName("head")[0].appendChild(linkDom);
                callback && callback.call(linkDom);
            }
        }
    };

    //加载默认样式文件
    $.formatInput.loadSkin(formatInputVariable.skinName);

    //默认格式化样式
    $(document).ready(function () {
        $.formatInput.formatInput();
    });

    /*
    将扩展插件脚本的动态引用逻辑加入formStyle函数中，在逻辑上可提高执行效率和提高资源引用性能，但是
    经测试，发现将动态引用逻辑放入formStyle函数中后，对脚本资源是否完成的逻辑判断比较复杂，而且经常
    出现无法预知的错误，因此将该逻辑放置与formatInput类函数外部，暂时可解决该问题或者可在页面中手动引
    用扩展插件脚本以解决该问题
    */
    if ($.formatInput.defaults.external.my97DatePicker) {
        $.formatInput.loadScript(currentPath + "external/" + externalPlugin.my97DatePicker.js);
    }
    if ($.formatInput.defaults.external.poshyTip) {
        $.formatInput.loadScript(currentPath + "external/" + externalPlugin.poshyTip.js);
    }

    $.fn.formStyle = function () {

        this.each(function () {
            var $this = $(this);
            //初始化文本框样式
            var $inputTxt = $this.find(":text").not("[disableStyle]");
            debug("共搜索到查询范围内文本框（:text）" + $inputTxt.length + "个");
            debug("开始执行样式格式化");

            $inputTxt.each(function () {
                setInputTxtStyle(this);
                if ($(this).attr("disabled")) {
                    $(this).disable();
                }
                else {
                    if ($(this).attr("readonly")) {
                        $(this).readonly();
                    }
                    if (!dynamicLoadExtJs && true) {
                        addInputTipEvent(this);
                        var dateFormat = $(this).attr("dateFormat");
                        if (dateFormat != undefined) {

                            debug("检索到日期选择控件，日期格式设置:" + dateFormat + "，开始进行初始化！");

                            $(this).addClass("FormatInputInputDate");
                            $(this).bind("focus", function () {
                                try {
                                    if (dateFormat == "") {
                                        dateFormat = "yyyy-MM-dd";
                                        $(this).attr("dateFormat", "yyyy-MM-dd");
                                    }
                                    WdatePicker({ dateFmt: dateFormat, autoPickDate: true });
                                }
                                catch (e) { alert('日期控件加载失败！' + '\r\n' + e); }
                            });

                            debug("日期控件初始化完成！");
                        }
                    }
                }
            });
            debug("内文本框（:text）样式格式化完成");

            //初始化密码框样式
            var $inputPwd = $this.find(":password").not("[disableStyle]");

            debug("共搜索到查询范围内密码框（:password）" + $inputPwd.length + "个");
            debug("开始执行样式格式化");

            $inputPwd.each(function () {
                setInputTxtStyle(this);
                if ($(this).attr("disabled")) {
                    $(this).disable();
                }
                else {
                    if ($(this).attr("readonly")) {
                        $(this).readonly();
                    }
                    if (!dynamicLoadExtJs && true) {
                        addInputTipEvent(this);
                    }
                }
            });
            debug("密码框（:password）样式格式化完成");

            //初始化文件选择域样式
            var $inputFile = $this.find(":file").not("[disableStyle]");

            debug("共搜索到查询范围内文件域（:file）" + $inputFile.length + "个");
            debug("开始执行样式格式化");

            $inputFile.each(function () {
                setInputTxtStyle(this);
                if ($(this).attr("disabled") || $(this).attr("readonly")) {
                    $(this).disable();
                }
            });
            debug("文件域（:file）样式格式化完成");

            var $inputTextarea = $this.find("textarea").not("[disableStyle]");

            debug("共搜索到查询范围内多行文本框（textarea）" + $inputTextarea.length + "个");
            debug("开始执行样式格式化");

            $inputTextarea.each(function () {
                $(this).addClass("FormatInputInputMultiTxt");
                if ($(this).attr("disabled")) {
                    $(this).disable();
                }
                else {
                    if ($(this).attr("readonly")) {
                        $(this).readonly();
                    }
                    $(this).focus(function () {
                        $(this).addClass("FormatInputInputTxtFocus");
                    });
                    $(this).blur(function () {
                        $(this).removeClass("FormatInputInputTxtFocus");
                    });
                    if (!dynamicLoadExtJs && true) {
                        addInputTipEvent(this);
                    }
                }
            });
            debug("多行文本框（textarea）样式格式化完成");

            var $inputButton = $this.find("input[type='button'], input[type='submit'], input[type='reset'], button").not("[disableStyle]");
            $inputButton.each(function () {
                $(this).addClass("FormatInputBtn");
            });

            //IE6不支持CSS伪类，通过脚本实现显示兼容效果
            if (browser.ie6) {
                debug("IE6兼容设置：按钮鼠标划过效果");
                $inputButton.each(function () {
                    $(this).hover(
                                function () {
                                    $(this).addClass("FormatInputBtnHover");
                                },
                                function () {
                                    $(this).removeClass("FormatInputBtnHover");
                                }
                            );
                });
            }
        });
    };

    $.fn.disable = function () {
        return this.each(function () {
            var $this = $(this);
            $this.attr('disabled', true);
            $this.addClass("FormatInputCtrlDisable");
        });
    };

    $.fn.enable = function () {
        return this.each(function () {
            var $this = $(this);
            $this.removeAttr('disabled');
            $this.removeClass("FormatInputCtrlDisable");
        });
    };

    $.fn.readonly = function () {
        return this.each(function () {
            var $this = $(this);
            $this.attr('readonly', true);
            $this.addClass("FormatInputCtrlDisable");
        });
    };

    $.fn.nonreadonly = function () {
        return this.each(function () {
            var $this = $(this);
            $this.removeAttr('readonly');
            $this.removeClass("FormatInputCtrlDisable");
        });
    };

    var addInputTipEvent = function (o) {
        var $this = $(o);
        if (!$this.attr("disabled") && !$this.attr("readonly")) {
            if ($this.poshytip && $this.attr("title") != undefined && $this.attr('title') != "") {
                $this.poshytip({
                    className: 'tip-yellow',
                    showOn: 'focus',
                    alignTo: 'target',
                    alignX: 'inner-left',
                    offsetX: 0,
                    offsetY: 5
                });
            }
        }
    };

    var setInputTxtStyle = function (o) {
        $(o).addClass("FormatInputInputTxt");
        $(o).focus(function () {
            $(o).addClass("FormatInputInputTxtFocus");
        });
        $(o).blur(function () {
            $(o).removeClass("FormatInputInputTxtFocus");
        });
    };

})(jQuery);