(function($){
	/**
	 * table分页,属性pagenum值表示第几页
	 * 
	 * url:null			//请求地址
	 * pageNum:1		//第几页
	 * pageSize:15 		//每页大小	
	 * callback:null	//回调函数
	 * pager:'#pager'	//分页tag
	 * formId:'#form'	//form表单
	 * exParam:{}		//扩展参数
	 * isPage:true		//是否分页
	 * 
	 */
	$.fn.extend({
		initTable:function(options){
			var defaults={
				url:null			//请求地址
				,pageNum:1			//第几页
				,pageSize:15 		//每页大小	
				,callback:null		//回调函数
				,pager:'#pager'		//分页tag
				,formId:'#form'		//form表单
				,exParam:{}			//扩展参数
				,isPage:true		//是否分页
			}
			var opts = $.extend(defaults, options);
			var $this=$(this);
			
			//处理第一页
			$this.attr('pagenum',opts.pageNum);
			
			if(!opts.url){
				console.log('参数url不能为空...');
				return;
			}
			if(!opts.callback && typeof opts.callback !='function'){
				console.log('参数callback为funtion...');
				return;
			}
			
			var _data=$(opts.formId).getFormsValue();
			//
			if(Object.prototype.toString.call(opts.exParam)=='[object Object]'){
				$.extend(_data,opts.exParam);
			}
	    	_data.pageSize=opts.pageSize;
	    	_data.pageNum=opts.pageNum;
			$.post(opts.url, _data, function (rev) {
				if(rev.msg){
					console.log(rev.msg);
					return;
				}
	        	$.each(rev.list, function (i, v) {
	                v.n0 = i + 1 + (_data.pageNum - 1) * _data.pageSize;
	            });
	        	opts.callback(rev.list);
	        	if(opts.isPage){
	        		bindPager(rev.total,opts.pageNum);
	        	}
	        },'json');
			
			var bindPager = function (tCounts,cPage) {
		        $.jqPaginator(opts.pager, {
		            totalCounts: tCounts,
		            pageSize: opts.pageSize,
		            visiblePages: 6,
		            currentPage: parseInt(cPage),
		            onPageChange: function (num, type) {
		                if (type != 'init') {
		                	$this.attr('pagenum',num);
		                	$this.initTable($.extend(options,{pageNum:num}));
		                }
		            }
		        });
		    }
		}
	
	});
	
})(jQuery);