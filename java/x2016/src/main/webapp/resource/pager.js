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
				,isPage:true		//是否分页
			}
			var $this=$(this);
			
			//处理页面不知道pagenum情况
			if(options.pageNum==undefined || options.pageNum==null ){
				if($this.attr('pagenum')==undefined ||$this.attr('pagenum')==null){
					options.pageNum=1;
				} else{
					options.pageNum=$this.attr('pagenum');
				}
			}
			
			var opts = $.extend(defaults, options);
			
			//处理第几页
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
	    	_data.pageSize=opts.pageSize;
	    	_data.pageNum=opts.pageNum;
			$.post(opts.url, _data, function (rev) {
				if(rev.msg){
					alter(rev.msg);
				}
				
	        	$.each(rev.list, function (i, v) {
	        		//加序号
	                v.n0 = i + 1 + (_data.pageNum - 1) * _data.pageSize;
	                
	                //替换null值为空字符串
	                $.each(v,function(_i,_v){
	                	if(null==_v){
	                		v[_i]='';
	                	}
	                });
	            });
	        	callback(rev.list);
	        	
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