(function ($) {
    'use strict';

    $.jqPaginator = function (el, options) {
        if (!(this instanceof $.jqPaginator)) {
            return new $.jqPaginator(el, options);
        }

        var self = this;

        self.$container = $(el);

        self.$container.data('jqPaginator', self);

        self.init = function () {

            if (options.first || options.prev || options.next || options.last || options.page) {
                options = $.extend({}, {
                    first: '',
                    prev: '',
                    next: '',
                    last: '',
                    page: ''
                }, options);
            }

            self.options = $.extend({}, $.jqPaginator.defaultOptions, options);

            self.verify();

            self.extendJquery();

            self.render();

            self.fireEvent(this.options.currentPage, 'init');
        };

        self.verify = function () {
            var opts = self.options;

            if (!self.isNumber(opts.totalPages)) {
                throw new Error('[jqPaginator] type error: totalPages');
            }

            if (!self.isNumber(opts.totalCounts)) {
                throw new Error('[jqPaginator] type error: totalCounts');
            }

            if (!self.isNumber(opts.pageSize)) {
                throw new Error('[jqPaginator] type error: pageSize');
            }

            if (!self.isNumber(opts.currentPage)) {
                throw new Error('[jqPaginator] type error: currentPage');
            }

            if (!self.isNumber(opts.visiblePages)) {
                throw new Error('[jqPaginator] type error: visiblePages');
            }

            //if (!opts.totalPages && !opts.totalCounts) {
            //    throw new Error('[jqPaginator] totalCounts or totalPages is required');
            //}

            //if (!opts.totalPages && !opts.totalCounts) {
            //    throw new Error('[jqPaginator] totalCounts or totalPages is required');
            //}

            if (!opts.totalPages && opts.totalCounts && !opts.pageSize) {
                throw new Error('[jqPaginator] pageSize is required');
            }

            if (opts.totalCounts && opts.pageSize) {
                opts.totalPages = Math.ceil(opts.totalCounts / opts.pageSize);
            }

            //if (opts.currentPage < 1 || opts.currentPage > opts.totalPages) {
            //    throw new Error('[jqPaginator] currentPage is incorrect');
            //}

            //if (opts.totalPages < 1) {
            //    throw new Error('[jqPaginator] totalPages cannot be less currentPage');
            //}
        };

        self.extendJquery = function () {
            $.fn.jqPaginatorHTML = function (s) {
                return s ? this.before(s).remove() : $('<p>').append(this.eq(0).clone()).html();
            };
        };

        self.render = function () {
            self.renderHtml();
            self.setStatus();
            self.bindEvents();
        };

        self.renderHtml = function () {
            var html = [];

            var pages = self.getPages();
            for (var i = 0, j = pages.length; i < j; i++) {
                html.push(self.buildItem('page', pages[i]));
            }
            if (self.options.totalPages > 0) {
                self.isEnable('prev') && html.unshift(self.buildItem('prev', self.options.currentPage - 1));
                self.isEnable('first') && html.unshift(self.buildItem('first', 1));
                self.isEnable('statistics') && html.unshift(self.buildItem('statistics'));
                self.isEnable('next') && html.push(self.buildItem('next', self.options.currentPage + 1));
                self.isEnable('last') && html.push(self.buildItem('last', self.options.totalPages));


                html.push('<li class="disabled"><a href="javascript:;">' + self.options.currentPage + '/' + self.options.totalPages + '</a></li>');
                if (self.options.totalCounts > 0) {
                    html.push('<li class="disabled"><a href="javascript:;">每页' + self.options.pageSize + '条</a></li>');
                    html.push('<li class="disabled"><a href="javascript:;">共' + self.options.totalCounts + '条</a></li>');
                }
            }

            if (self.options.wrapper) {
                self.$container.html($(self.options.wrapper).html(html.join('')).jqPaginatorHTML());
            } else {
                self.$container.html(html.join(''));
            }
        };

        self.buildItem = function (type, pageData) {
            var html = self.options[type]
                .replace(/{{page}}/g, pageData)
                .replace(/{{totalPages}}/g, self.options.totalPages)
                .replace(/{{totalCounts}}/g, self.options.totalCounts);

            return $(html).attr({
                'jp-role': type,
                'jp-data': pageData
            }).jqPaginatorHTML();
        };

        self.setStatus = function () {
            var options = self.options;

            if (!self.isEnable('first') || options.currentPage === 1) {
                $('[jp-role=first]', self.$container).addClass(options.disableClass);
            }
            if (!self.isEnable('prev') || options.currentPage === 1) {
                $('[jp-role=prev]', self.$container).addClass(options.disableClass);
            }
            if (!self.isEnable('next') || options.currentPage >= options.totalPages) {
                $('[jp-role=next]', self.$container).addClass(options.disableClass);
            }
            if (!self.isEnable('last') || options.currentPage >= options.totalPages) {
                $('[jp-role=last]', self.$container).addClass(options.disableClass);
            }

            $('[jp-role=page]', self.$container).removeClass(options.activeClass);
            $('[jp-role=page][jp-data=' + options.currentPage + ']', self.$container).addClass(options.activeClass);
        };

        self.getPages = function () {
            var pages = [],
                visiblePages = self.options.visiblePages,
                currentPage = self.options.currentPage,
                totalPages = self.options.totalPages;

            if (visiblePages > totalPages) {
                visiblePages = totalPages;
            }

            var half = Math.floor(visiblePages / 2);
            var start = currentPage - half + 1 - visiblePages % 2;
            var end = currentPage + half;

            if (start < 1) {
                start = 1;
                end = visiblePages;
            }
            if (end > totalPages) {
                end = totalPages;
                start = 1 + totalPages - visiblePages;
            }

            var itPage = start;
            while (itPage <= end) {
                pages.push(itPage);
                itPage++;
            }

            return pages;
        };

        self.isNumber = function (value) {
            var type = typeof value;
            return type === 'number' || type === 'undefined';
        };

        self.isEnable = function (type) {
            return self.options[type] && typeof self.options[type] === 'string';
        };

        self.switchPage = function (pageIndex) {
            self.options.currentPage = pageIndex;
            self.render();
        };

        self.fireEvent = function (pageIndex, type) {
            return (typeof self.options.onPageChange !== 'function') || (self.options.onPageChange(pageIndex, type) !== false);
        };

        self.callMethod = function (method, options) {
            switch (method) {
                case 'option':
                    self.options = $.extend({}, self.options, options);
                    self.verify();
                    self.render();
                    break;
                case 'destroy':
                    self.$container.empty();
                    self.$container.removeData('jqPaginator');
                    break;
                default:
                    throw new Error('[jqPaginator] method "' + method + '" does not exist');
            }

            return self.$container;
        };

        self.bindEvents = function () {
            var opts = self.options;

            self.$container.off();
            self.$container.on('click', '[jp-role]', function () {
                var $el = $(this);
                if ($el.hasClass(opts.disableClass) || $el.hasClass(opts.activeClass)) {
                    return;
                }

                var pageIndex = +$el.attr('jp-data');
                if (self.fireEvent(pageIndex, 'change')) {
                    self.switchPage(pageIndex);
                }
            });
        };

        self.init();

        return self.$container;
    };

    $.jqPaginator.defaultOptions = {
        wrapper: '',
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        last: '<li class="last"><a href="javascript:;">未页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        totalPages: 0,
        totalCounts: 0,
        pageSize: 0,
        currentPage: 1,
        visiblePages: 7,
        disableClass: 'disabled',
        activeClass: 'active',
        onPageChange: null
    };

    $.fn.jqPaginator = function () {
        var self = this,
            args = Array.prototype.slice.call(arguments);

        if (typeof args[0] === 'string') {
            var $instance = $(self).data('jqPaginator');
            if (!$instance) {
                throw new Error('[jqPaginator] the element is not instantiated');
            } else {
                return $instance.callMethod(args[0], args[1]);
            }
        } else {
            return new $.jqPaginator(this, args[0]);
        }
    };

})(jQuery);

$(function () {
    if ($('.mypager').length > 0) {
        //初始化表单
        $('#form').setFormsValue(urlParam2Obj());
        //
        var _vm_ = new _viewModel_();
        ko.applyBindings(_vm_, $(bindingContainer).get(0));
        _vm_.search();
    }
});

//定义ViewModel对象
var _viewModel_ = function () {
    var self = this;
    //总量
    self.rowNum = ko.observable(0);
    //数据
    self.items = ko.observableArray([]);
    //当前页
    var cnum = parseInt($('#hidPageIndex').val());
    if (isNaN(cnum))
        cnum = 1;
    self.currentPage = ko.observable(cnum);
    //查询
    self.search = function (d, e) {
        if (d) {
            //window.console && console.log('重置当前页……');
            self.currentPage(1);
            $('input[name="_hidid_"]').val('');
        }
        //window.console && console.log('执行查询……');
        var _data_ = $('#' + formContainer).getFormsValue();
        //_data_.pageIndex = self.currentPage();
        _data_.pageSize = pageSize;
        $.post(dataUrl, _data_, function (rev) {
            $('#table>tbody').empty();
            if (rev.count == 0) {
                var _tr = '<tr><td colspan="' + $('#table>thead').find('tr:first').find('th').length + '">没有数据</td></tr>';
                $('#table>tbody').append(_tr);
                return;
            }
            $.each(rev.list, function (i, v) {
                v.N0 = i + 1 + (_data_.pageIndex - 1) * _data_.pageSize;
                $.each(v, function (i2, v2) {
                    if (/\/Date\(\d+\)\//.test(v2)) {
                        var dateVal = v2.replace(/\/Date\((\d+)\)\//gi, '$1');
                        var js = "rev.list[i]." + i2 + "=new Date(" + dateVal + ").format('yyyy-MM-dd hh:mm:ss')";
                        eval(js);
                    }
                });
            });
            self.rowNum(rev.count);
            self.items(rev.list);
            bindPager();
            initChecked();
        });
    }
    //增加
    self.addOne = function (d, e, addUrl) {
        location.href = addUrl + '?refUrl=' + encodeURIComponent(location.pathname + '?' + $('#form').serialize());
    }
    //修改
    self.editOne = function (d, e, editUrl) {
		var idsArr = $('input[name="_hidid_"]').val().split(',');
        if (idsArr.indexOf('') > -1)
            idsArr.remove(idsArr.indexOf(''));
        if (idsArr.length == 0) {
            top.warn('请选择要修改的数据');
            return false;
        }
		if (idsArr.length >1) {
            top.warn('一次只能修改一条数据');
            return false;
        }
        location.href = editUrl+idsArr[0] + '?refUrl=' + encodeURIComponent(location.pathname + '?' + $('#form').serialize());
    }
    ////删除
    //self.delOne = function (d, e, delUrl) {
    //    top.ask('确定要删除么?', function () {
    //        $.post(delUrl, d, function (rev) {
    //            if (rev.error == 0) {
    //                location.reload();
    //            }
    //            else {
    //                top.warn('操作失败,请稍后再试');
    //            }
    //        }, 'json');
    //    });
    //}
    //批量删除
    self.delBatch = function (d, e, delUrl) {
        var idsArr = $('input[name="_hidid_"]').val().split(',');
        if (idsArr.indexOf('') > -1)
            idsArr.remove(idsArr.indexOf(''));
        if (idsArr.length == 0) {
            top.warn('请选择要删除的数据');
            return false;
        }
        top.ask('确定要删除么?', function () {
            $.post(delUrl, { ids: idsArr.join(',') }, function (rev) {
                if (rev.error == 0) {
                    $('input[name="_hidid_"]').val('');
                    location.reload();
                }
                else {
                    top.warn('操作失败,请稍后再试');
                }
            });
        });
    }
    //
    var bindPager = function () {
        //window.console && console.log('执行分页……');
        $.jqPaginator('.mypager', {
            totalCounts: self.rowNum(),
            pageSize: pageSize,
            visiblePages: 6,
            currentPage: self.currentPage(),
            onPageChange: function (num, type) {
                if (type != 'init') {
                    $('#hidPageIndex').val(num);
                    self.currentPage(num);
                    self.search(null, null);
                }
            }
        });
    }
    //
    function initChecked() {
        if ($('input[name="_hidid_"]').length == 1) {
            var $ids = $('input[name="_hidid_"]').val().split(',');
            $.each($('input:checkbox[name="ckson"]'), function (i2, v2) {
                $.each($ids, function (i, v) {
                    if (v == v2.value) {
                        v2.checked = true;
                    }
                });
            });
            var ckAll = true;
            $.each($('input[name="ckson"]'), function (i2, v2) {
                if (!v2.checked) {
                    ckAll = false;
                    return false;
                }
            });
            if ($('input[name="ckson"]').length == 0)
                ckAll = false;
            if (ckAll) {
                $('input[name="ckfather"]').prop('checked', true);
            }
            else {
                $('input[name="ckfather"]').prop('checked', false);
            }
        }
    }
};

$(document).on('change', 'input[name="ckfather"]', function () {
    var $ids = $('input[name="_hidid_"]').val().split(',');
    if (this.checked) {
        $.each($('input:checkbox[name="ckson"]'), function (i, v) {
            if ($ids.indexOf(v.value) < 0)
                $ids.push(v.value);
            v.checked = true;
        });
    }
    else {
        $.each($('input:checkbox[name="ckson"]'), function (i, v) {
            if ($ids.indexOf(v.value) > -1)
                $ids.remove($ids.indexOf(v.value));
            v.checked = false;
        });
    }
    if ($ids.indexOf("") > -1)
        $ids.remove($ids.indexOf(""));
    $('input[name="_hidid_"]').val($ids.join(','));
});

$(document).on('change', 'input[name="ckson"]', function () {
    var $ids = $('input[name="_hidid_"]').val().split(',');
    if (this.checked) {
        if ($ids.indexOf(this.value) < 0)
            $ids.push(this.value);
    }
    else {
        if ($ids.indexOf(this.value) > -1)
            $ids.remove($ids.indexOf(this.value));
    }
    if ($ids.indexOf("") > -1)
        $ids.remove($ids.indexOf(""));
    $('input[name="_hidid_"]').val($ids.join(','));

    var ckAll = true;
    $.each($('input[name="ckson"]'), function (i2, v2) {
        if (!v2.checked) {
            ckAll = false;
            return false;
        }
    });
    if (ckAll) {
        $('input[name="ckfather"]').prop('checked', true);
    }
    else {
        $('input[name="ckfather"]').prop('checked', false);
    }
});
