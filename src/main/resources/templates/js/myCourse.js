function getMyCourseData(){
				 $.ajax({
				        async : false,
				        cache : false,
						type: "get",
						url:"http://localhost:8080/student/query/mycourse" ,
						success:function (data) {
							var jsonlist = data;
							var str = "";
							for(var i = 0; i < jsonlist.length;i++){
								str = "<tr><td>"+jsonlist[i].courseId+"</td><td>"+jsonlist[i].courseName +
								"</td><td>"+jsonlist[i].weekday+"</td><td>"+jsonlist[i].startTime +
								"</td><td>"+jsonlist[i].endTime+"</td><td>"+jsonlist[i].gymName+jsonlist[i].gymId+
								"</td><td>"+jsonlist[i].teacherName+"</td></tr>";
								
								document.getElementById("#table-7").append(str);
							}
				        },
						
				 };