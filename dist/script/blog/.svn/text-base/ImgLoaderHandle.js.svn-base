  
    
    var maxImgHeight = 510;
    var maxImgWidth = 800;
    
    var currentImgIndex = 1;
    var imgCount;
    var scrollNextFlag = false;
    var scrollPrevFlag = false;

     
    var imgWidth = 600;
    var imgHeight = 400;

    $(document).ready(function () {

        var LoadImage = function (url) {

            var wrap = $("#image_wrap").fadeTo("fast", 0.5, function () { $('.DivImgLoadingTips').show(); });

            ImgLoader(url, function () {
                //图片加载完成后隐藏加载进度条
                //wrap.fadeTo("fast", 1, function(){ $('.DivImgLoadingTips').hide();});
                SetImgSize(this.width, this.height);
            },
		        function () {
		            $("#image_wrap").fadeTo("fast", 1, function () { $('.DivImgLoadingTips').hide(); });
		            $("#image_wrap").empty();
		            $("#image_wrap").html("<img src='" + url + "' width=\"" + imgWidth + "\" height=\"" + imgHeight + "\"  align=\"absmiddle\"/>");

		            $('.IcoSource').attr('href', url);
		            $('.IcoSource').attr('target', '_blank');

		        },
		        function () {
		            alert("Image Not Found!");
		            wrap.fadeTo("fast", 1, function () { $('.DivImgLoadingTips').hide(); });
		        });
        };

        var SetImgSize = function (_w, _h) {

            imgWidth = _w;
            imgHeight = _h;

            var proportion = _w / _h;

            if (_w > maxImgWidth) {
                _h = maxImgWidth / proportion;

                if (_h > maxImgHeight) {
                    imgWidth = proportion * maxImgHeight;
                    imgHeight = maxImgHeight;
                }
                else {
                    imgWidth = maxImgWidth;
                    imgHeight = _h;
                }
            }
            else if (_h > maxImgHeight) {
                imgWidth = proportion * maxImgHeight;
                imgHeight = maxImgHeight;
            }

        }

        var SetScrollFlagVal = function (obj) {
            if (obj.is(":last-child")) {
                scrollNextFlag = true;
            }
            else {
                scrollNextFlag = false;
            }
            if (obj.is(":first-child")) {
                scrollPrevFlag = true;
            }
            else {
                scrollPrevFlag = false;
            }
        };

        var InitScrollBar = function (obj) {
            $(".items img").removeClass("active");
            obj.addClass("active");

            SetScrollFlagVal(obj);
        }

        var srcList = new Array();
        $(".items img").each(function () {
            srcList.push(this.link);
        });

        imgCount = srcList.length;

        $('.spanImgCount').text(imgCount);

        $(".items img").each(function () {

            $(this).bind('click', function () {

                var imgObj = $(this);

                if (imgObj.hasClass("active")) { return; }

                var url = $(this).attr("link");

                currentImgIndex = parseInt($(this).attr("idx"));

                $('.spanCurrentIndex').text(currentImgIndex);

                $('#hPhotoTitle').text(unescape(imgObj.attr('title')));
                $('#pPhotoDsn').text(unescape(imgObj.attr('description')));

                LoadImage(url);
                InitScrollBar(imgObj);

                $('.DivBtnNext').unbind('click');
                $('.DivBtnNext').bind('click', function () {

                    if (scrollNextFlag) {
                        $('a.next').click();
                        imgObj.parent().next().find('img:first').click();
                    }
                    else {
                        imgObj.next().click();

                    }

                });

                $('.DivBtnPrev').unbind('click');
                $('.DivBtnPrev').click(function () {

                    if (scrollPrevFlag) {
                        $('a.prev').click();
                        imgObj.parent().prev().find('img:last').click();
                    }
                    else {
                        imgObj.prev().click();

                    }

                });
            });
        });

        $(".items img").filter(":first").click()

        $(".scrollable").scrollable();

        $('.DivPhtoCon').hover(
            function () {
                $('.DivBtnPrev').fadeIn();
                $('.DivBtnNext').fadeIn();
            },
            function () {
                $('.DivBtnPrev').fadeOut();
                $('.DivBtnNext').fadeOut();
            }
      );

    });