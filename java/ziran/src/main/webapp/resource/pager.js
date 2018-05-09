(function($){
	var _viewModel = function (opts) {
	    var self = this;
	    //总量
	    self.rowNum = ko.observable(0);
	    //数据
	    self.items = ko.observableArray([]);
	    //当前页
	    self.currentPage = ko.observable(1);
	    //消息
	    self.msg=ko.observable('');
	    //查询
	    self.search = function (d, e) {
	        //
	    	if (d) {
	            self.currentPage(1);
	        }
	    	var _data=$(opts.formId).getFormsValue();
	    	_data.pageNum=self.currentPage();
	    	_data.pageSize=opts.pageSize;
	    	var _url='';
	    	if(opts.callback && typeof opts.callback =='function'){
	    		_url=opts.callback();
	    	}
	        $.post(_url, _data, function (rev) {
	            self.rowNum(rev.total);
	            self.items(rev.list);
	            self.msg(rev.msg);
	            
	            bindPager();
	        },'json');
	    }
		var bindPager = function () {
	        $.jqPaginator(pager, {
	            totalCounts: self.rowNum(),
	            pageSize: opts.pageSize,
	            visiblePages: 6,
	            currentPage: self.currentPage(),
	            onPageChange: function (num, type) {
	                if (type != 'init') {
	                    self.currentPage(num);
	                    self.search(null, null);
	                }
	            }
	        });
	    }
	}
	$.fn.extend({
		initTable:function(options){
			var defaults={
					url:null			//请求地址
					,pageSize:20 		//每页大小	
					,callback:null		//回调函数
					,pager:'#pager'		//分页tag
					,formId:'#form'		//form表单
					,bindId:'body' 		//绑定对象
				}
			var opts = $.extend(defaults, options);
			var $this=$(this);
			if(!opts.url){
				console.log('参数url不能为空...');
				return;
			}
			/*if(!opts.callback && typeof opts.callback !='function'){
				console.log('参数callback为funtion...');
				return;
			}*/
			var _vm= new _viewModel(opts);
			ko.applyBindings(_vm, $(opts.bindId).get(0));
			_vm.search();
		}
	});
	
})(jQuery);