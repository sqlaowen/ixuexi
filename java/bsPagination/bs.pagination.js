jQuery.fn.bspagination = function(maxentries, opts) {
	opts = jQuery.extend({
		pagination_class : 'pagination-sm', // 分页样式
		total_entries : maxentries, // 总计路数
		items_per_page : 10, // 每页记录数
		num_display_entries : 5, // 显示按钮数
		current_page : 1, // 当前页
		count_page : 10, // 总页数
		link_to : "javascript:;",
		prev_text : "&laquo;", // 上一页文本
		next_text : "&raquo", // 下一页文本
		jump_text : "跳转", // 跳转文本
		isSum : true, // 是否显示总数
		isJump : true, // 是否显示跳转
		isChangeItems : true, // 是否修改记录数
		isShow : true, // 是否显示页码
		jump_format_text : "错误：请填写正确数字", // 跳转提示信息
		jump_outofrange_text : "错误：超出页数范围", // 跳转超出页数范围提示信息
		jump_null_text : "错误：跳转页数不许为空", // 跳转页数为空提示信息
		prev_show_always : true, // 显示上一页按钮
		next_show_always : true, // 显示下一页按钮
		callback : function() {
			return true;
		} // 回调函数
	}, opts || {});

	function getShowBtn() {
		var btns = [];
		var maxNum = 1;
		var minNum = 1;
		var btns_num = opts.num_display_entries / 2;
		if (opts.num_display_entries % 2 == 1) {
			btns_num = (opts.num_display_entries - 1) / 2;
		}
		if (opts.count_page < opts.num_display_entries) {
			maxNum = opts.count_page;
		} else {
			if ((opts.current_page - btns_num) > 1) {
				minNum = opts.current_page - btns_num
			} else {
				maxNum = opts.num_display_entries;
			}
			if (maxNum == 1) {
				if ((opts.current_page - 0 + btns_num) <= opts.count_page) {
					maxNum = opts.current_page - 0 + btns_num;
				} else {
					maxNum = opts.count_page;
					minNum = opts.count_page - opts.num_display_entries + 1;
				}
			}
		}
		for (var i = minNum; i <= maxNum; i++) {
			btns.push(i);
		}
		return btns;
	}

	function initDiv() {
		panel.append(jQuery('<nav><ul class="pagination '
				+ opts.pagination_class + '" style="margin:0px;"></ul></nav>'));
		var ulHtml = panel.find('nav ul');

		if (opts.isSum) {
			var htmlText = "";
			htmlText += '<li class="disabled">';
			htmlText += '<span>共' + opts.total_entries + '条 ,<strong>'
					+ opts.current_page + '/' + opts.count_page
					+ '</strong></span>'
			htmlText += '</li>'
			ulHtml.append(htmlText);
		}

		if (opts.isChangeItems) {
			var htmlText = "";
			var arr = [ 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 100, 200, 500, 1000 ];
			htmlText = '<li><span>'
			htmlText += '<div class="dropup">'
			htmlText += '<strong class="dropdown-toggle" data-toggle="dropdown" id="_recPerPage">每页 <strong>'
					+ opts.items_per_page
					+ '</strong> 条<span class="caret"></span></strong>';
			htmlText += '<ul class="dropdown-menu" aria-labelledby="_recPerPage">';
			arr.forEach(function(item, index, array) {
				if (opts.items_per_page == item) {
					htmlText += '<li class="active"><span>' + item
							+ '</span></li>';
				} else {
					htmlText += '<li ><span>' + item + '</span></li>';
				}

			});
			htmlText += '</ul></div></span></li>';
			ulHtml.append(htmlText);

		}

		if (opts.prev_show_always) {
			var htmlText = '<li class="Previous"><span aria-label="Previous">'
					+ opts.prev_text + '</span></li>';
			ulHtml.append(htmlText);
		}

		if (opts.isShow) {
			var htmlText = "";
			var arr = getShowBtn();
			arr.forEach(function(item, index, array) {
				if (opts.current_page == item) {
					htmlText += '<li class="PageNum active"><span >' + item
							+ '</span></li>';
				} else {
					htmlText += '<li class="PageNum"><span >' + item
							+ '</span></li>';
				}
			});
			ulHtml.append(htmlText);
		}

		if (opts.next_show_always) {
			var htmlText = '<li class="Next"><span aria-label="Next">'
					+ opts.next_text + '</span></li>';
			ulHtml.append(htmlText);
		}

		if (opts.isJump) {
			var htmlText = '<li class="Jump"><span class="input-group">';
			htmlText += '<input id="jump-index" class="form-control" type="text" placeholder="页码">'
			htmlText += '<span class="input-group-addon">Go</span>'
			htmlText += '</span></li>'
			ulHtml.append(htmlText);
		}
	}

	function bindEvent() {
		if (opts.isChangeItems) {
			$('.dropdown-toggle').dropdown();
			panel.find("div.dropup ul li").on("click", function() {
				$(this).siblings().removeClass('active');
				$(this).addClass('active');
				var newItems = $(this).find("span").html();
				panel.find("#_recPerPage strong").html(newItems);
				opts.items_per_page = newItems;
				initConfig();
				var pageNum = 1;
				var pageSize = opts.items_per_page;
				opts.callback(pageNum, pageSize);
			});
		}

		if (opts.prev_show_always) {
			panel.find('nav ul>li.Previous').on("click", function() {
				var pageNum = opts.current_page;
				var pageSize = opts.items_per_page;
				if (opts.current_page > 1) {
					pageNum = opts.current_page - 1;
					opts.callback(pageNum, pageSize);
				}
			});
		}

		if (opts.next_show_always) {
			panel.find('nav ul>li.Next').on("click", function() {
				var pageNum = opts.current_page;
				var pageSize = opts.items_per_page;
				if (opts.current_page < opts.count_page) {
					pageNum = opts.current_page - 0 + 1;
					opts.callback(pageNum, pageSize);
				}
			});
		}

		if (opts.isShow) {
			panel.find('nav ul>li.PageNum').on("click", function() {
				var pageNum = $(this).find("span").html();
				var pageSize = opts.items_per_page;
				if (pageNum != opts.current_page) {
					opts.callback(pageNum, pageSize);
				}

			});
		}

		if (opts.isJump) {
			panel.find('nav ul>li.Jump').on("click", "span.input-group-addon",
					function() {
						var pageNum;
						var pageSize = opts.items_per_page;
						var jumpinput = panel.find("#jump-index");
						index = jumpinput.val();
						if (index == null || index == "") {
							alert(opts.jump_null_text);
							return false;
						}
						if (/^\d+$/.test(index)) {
							if (index > opts.count_page || index < 1) {
								alert(opts.jump_outofrange_text);
								jumpinput.val("");
								return false;
							} else {
								pageNum = index;
								if (pageNum != opts.current_page) {
									opts.callback(pageNum, pageSize);
								}
							}
						} else {
							alert(opts.jump_format_text);
							jumpinput.val("");
							return false;
						}

					});
		}

	}

	function initConfig() {
		opts.count_page = Math.ceil(maxentries / opts.items_per_page);
		if (isNaN(opts.current_page) || opts.current_page == 0) {
			opts.current_page = 1;
		}
	}
	var panel = jQuery(this);
	panel.empty();
	initConfig();
	initDiv();
	bindEvent();
}
