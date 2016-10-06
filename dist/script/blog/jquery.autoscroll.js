/*
* jquery.autoscroll
* Copyright (c) 2012-2013 CERC HuangAnzhi 2011-07-06
* huanganzhi@cerc.net.cn
* Version 1.0.0 build 20131125
* Requires jquery v1.4.4+
*使用说明：$("#wrap").autoscroll([options]);
*如：$("#wrap").autoscroll({height:125,slider:"table",node:"tr"});
*参数说明
*height: 高度（数值型）
*line: 每次滚动行数，默认值1
*speed: 滚动速度，数值越大，速度越慢（数值型，默认1000，毫秒）
*delay: 滚动的时间间隔（数值型，默认3000，毫秒）
*slider: 滚动的元素（字符串型）默认值"ul"
 *node: 行元素DOM（字符串型）默认值"li"
*遗留问题：
*当滚动内容不能完全占满容器时，滚动结束后，会出现闪动补充现象
*/

(function ($) {
    $.fn.autoscroll = function (options) {

        var opts = $.extend({}, $.fn.autoscroll.defaults, options);

        var wrap = this.eq(0).find(opts.slider + ":first");
        var lineHeight = wrap.find(opts.node + ":first").height();

        $(this).css({ "height": +opts.height + "px", "overflow": "hidden" });

        var upHeight = 0 - opts.line * lineHeight;

        //滚动函数
        scrollUp = function () {
            wrap.animate({
                marginTop: upHeight
            }, opts.speed, function () {
                for (i = 1; i <= opts.line; i++) {
                    wrap.find(opts.node + ":first").appendTo(wrap);
                }
                wrap.css({ marginTop: 0 });
            });
        };
        //鼠标事件绑定
        wrap.hover(function () {
            if (delayID != null) {
                clearInterval(delayID);
            }
        }, function () {
            delayID = setInterval("scrollUp()", opts.delay);
        }).mouseout();
    };

    $.fn.autoscroll.defaults = {
        height: 200,
        line: 1,
        speed: 1000,
        delay: 3000,
        slider: "ul",
        node: "li"
    };

})(jQuery);