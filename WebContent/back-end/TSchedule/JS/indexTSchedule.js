//取得當日日期
		var today = new Date();
		
		//取得1970至今天0點的毫秒  /*new Date(year,month,today.getDate())*/
		var today_LongTime = today.getTime();
		
		var today_LongTime
		//取得年分
		var year = today.getFullYear();
		//取得月份
		var month = today.getMonth();		

		var dates = $(".date");
		var class_sorts = $(".class_sort");
		
		var month_first
		var numDay


		function now(){
	
			//當月1號星期幾
			 month_first = new Date(year,month,1).getDay();
			//取得當月有幾天  ex:7/0--->6月最後一天
			 numDay = new Date(year,month+1,0).getDate();
			var count = 1;
			//當月日期
			for(var i = month_first; i < numDay+month_first; i++){
				dates[i].innerHTML = count;
				//取得每天的毫秒數
				var longTime = new Date(year,month,count).getTime();
				count++;
	
//置入Add icon    				//當天的毫秒<每天的毫秒
//if(($("#set").text()=="完成") && (today_LongTime < longTime)){					
//class_sorts[i].innerHTML = "<img class=img_add src=images/add.png>";					
//}
			
			}
			
//add按下
//$(".img_add").click(function(){
//alert(666);
//});


			//顯示現在年月
			$("#year_month").html(year+"　　　年　　　"+(month+1)+"　　　月") ;

			//判斷年月是否等於當下年月
			if(today.getFullYear()==year && today.getMonth()==month){
				dates[today.getDate()+month_first-1].style.backgroundColor="rgba(255,247,0,0.9)";
			}			
			//前面
			for(var i =0; i<month_first; i++){				
				disable(i);
			}
			//後面
			for(var i = numDay+month_first; i < dates.length; i++){
				disable(i);
			}

		}
		//上鎖
		function disable(i){
			class_sorts[i].disabled = true;
			dates[i].disabled = true;
			class_sorts[i].style.backgroundColor="rgba(180,180,180,0.9)";
			dates[i].style.backgroundColor="rgba(180,180,180,0.9)";
		}
		//清除
		function clear(){
			$("div.date").html("&nbsp;").css({"disabled":"false","background-color":"rgba(240,240,240,0.9)"});
			$("div.class_sort").html("").css({"disabled":"false","background-color":"rgba(240,240,240,0.9)"});
			// for(var i = 0; i<dates.length; i++){
			// 	dates[i].innerHTML ="&nbsp;";
			// 	class_sorts[i].disabled = false;
			// 	dates[i].disabled = false;
			// 	class_sorts[i].style.backgroundColor="rgba(240,240,240,0.9)";
			// 	dates[i].style.backgroundColor="rgba(240,240,240,0.9)";
			// 	class_sorts[i].innerHTML="";
			// }

		}

		
		//Ajax取得每月資料
		function getDate(year, month){

			$.ajax({
				
				type:"POST",
/*注意路徑*/		url:"../../schedule/schedule.do",
				data:{"action":"getAll","year":year,"month":month},
				dataType:"json",
				success:function(data){
//					console.log(data);
					$(data).each(function(i,item){
//						console.log(i);
//						$(item).each(function(name, value){
							placeDate(item);
							
//						})
					});
				},
//				error: function(){alert("AJAX-grade發生錯誤囉!")}
				
			});
		}
		
		
		function placeDate(item){
			var t_name = item.t_name;
			var on_duty_cs = item.on_duty_cs;
			var ts_date = parseInt(item.ts_date.substring(8,10));
			for(var i =0; i < dates.length; i++){
				if($('.date').eq(i).html() == ts_date){
					$('.class_sort').eq(i).append('<span class="span">'+ on_duty_cs +" "+ t_name +'</span>')
					
					
				}
			}
			
		}
		
		function creatQueryString(value){
			var queryString = {"action":"addDateChange","changeDate":value};
			return queryString;
			
		}
		
		function dailySchedule(i,item){
			var t_name = item.t_name;
			var on_duty_cs = item.on_duty_cs;
			var t_num = item.t_num;
			var on_duty_num = item.on_duty_num;
//			var selTeachers =$('.selTeacher');
//			var selClasses =$('.selClass');
			$('.selTeacher').eq(i).append('<option value="' + t_num +'">' + t_name);
			$('.selClass').eq(i).append('<option value="' + on_duty_num +'">' + on_duty_cs);
		}
		
		//清空select
		function clearSelect(){
			$('.selTeacher').empty().append("<option value='-1'>請選擇</option>");
			$('.selClass').empty().append("<option value='-1'>請選擇</option>");
		}
		
		
		
		
		
		
		
		
		
		//初始化
		$(document).ready(function() {
  			now();
  			getDate(year,month);
  			
  			
  			$("#next").click(function(){
  				clear();
				//按一下 月份+1
				month++;
				//超過12月年分+1
				if(month>11){
					month=0;
					year++;
				}
				//在執行一次
				now();
				
				getDate(year,month);
  			});

  			$("#previous").click(function(){
  				clear();
				//按一下 月份-1
				month--;
				//低於1月 年分-1
				if(month<0){
					month=11;
					year--;
				}
				//在執行一次
				now();
				
				getDate(year,month);
  			});
  			//當下
  			$("#now").click(function(){
  				year = today.getFullYear();	
				month = today.getMonth();
				clear();
				now();
				getDate(year, month);
  			});
  			//新增
  			$("#set").click(function(){
  				    
  				
  				$('div#lightbox').css('z-index', '100');
  				  
  				
//					clear();
//  					now();
//  					getDate(year, month);
  			});
  			//關閉新增頁面
  			$('#addClose').on('click',function(){
  				$('div#lightbox').css('z-index', '0');
  			});

  			
  			
  			//日期選擇器值改變
  			$('#f_date1').change(function(){
  				console.log($(this).val());
  				$.ajax({
  					type: "POST",
/*注意路徑*/			url: "../../schedule/schedule.do",
					data: creatQueryString($(this).val()),
					dataType: "json",
					success:function(data){
						console.log(data)
						clearSelect();
						$(data).each(function(i,item){
							dailySchedule(i,item)
						});
					},
//					error: function(){alert("AJAX-grade發生錯誤囉!")}
  					
  				});
  			});
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
		});
		
		
		
		