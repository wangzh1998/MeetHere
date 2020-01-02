$("login").onclick(function () {
		    /*
		     获取form元素，调用其ajaxForm(...)方法
		     内部的function(data)的data就是后台返回的数据
		    */
		    $("#form1").ajaxForm(function (data) {
		            if(data == 0){
						window.location.href='main.html';
					}
		        }
		    );
		});