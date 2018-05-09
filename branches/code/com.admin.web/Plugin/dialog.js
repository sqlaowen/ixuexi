// 获取函数名称
function getFuncName(_callee) {
    return /function\s+([^\{\(\s]+)/.test(_callee) ? RegExp['$1'] : '[Unknown]';
}

// 模式窗口返回
var modal_ok = function () {
    if (window.frames["modalFrm"].form2JSON != undefined) {
        window.frames["modalFrm"].form2JSON();
    }
    if (typeof (window.__CALLBK__) != "function") {
        $("#dialog").dialog("close");
    }
    else if (window.__RETVAL__ != undefined && window.__RETVAL__ != null) {
        window.__CALLBK__(window.__RETVAL__);
        $("#dialog").dialog("close");
    }
}

// 打开模式窗口
function modal(title, url, func, in_args, w, h) {
    var width = 760;
    if (w != undefined && w != null) {
        width = w;
    }
    var height = 500;
    if (h != undefined && h != null) {
        height = h;
    }
    window.__CALLBK__ = func;
    window.__IN_ARGS__ = in_args;
    // 强制浏览器每次读取新内容
    if (url.indexOf("?") > -1) {
        url = url + "&v=1";
    }
    else {
        url = url + "?v=1";
    }
    var ifm = '<iframe name="modalFrm" id="modalFrm" frameborder="0" scrolling="auto" style="margin:0;padding:0;height:' + (height + 200) + 'px;width:100%;" src="' + url + '"  ></iframe>';
    var dlg = $("#dialog");
    dlg.attr("title", title);
    dlg.html(ifm);
    dlg.dialog({
        modal: true,
        height: height,
        width: width,
        buttons: {
            确定: function () {
                modal_ok();
            },
            取消: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $(this).dialog("close");
        }
    });
}

// 提示
function warn(msg, w, h) {
    var width = 300;
    if (w != undefined && w != null) {
        width = w;
    }
    var height = 150;
    if (h != undefined && h != null) {
        height = h;
    }
    var dlg = $("#dialog");
    var html = "<div style='margin: 8px auto 5px auto;padding:2px;'>" + msg + "</div>";
    dlg.html(html);
    dlg.dialog({
        modal: true,
        height: height,
        width: width,
        buttons: {
            确定: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $(this).dialog("close");
        }
    });
}

// 确认
function ask(msg, func, in_args) {
    var dlg = $("#dialog");
    var html = "<div style='margin: 8px auto 5px auto;padding:2px;'>" + msg + "</div>";
    dlg.html(html);
    dlg.dialog({
        modal: true,
        height: 150,
        width: 310,
        buttons: {
            确定: function () {
                if (func != undefined && typeof (func) == "function") {
                    if (in_args != undefined && in_args != null)
                        func(in_args);
                    else
                        func();
                }
                $(this).dialog("close");
            },
            取消: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $(this).dialog("close");
        }
    });
}