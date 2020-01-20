
 $(document).ready(function() {

	 $(".courseList").click(function () {
		 var t = $(this).next().val();
		 $("#front" + t).show(1000);
	 })
	 
	  $(".CourseCancel").click(function(){
		  $(".updateAddCourse").hide(300);
		  $(".updateAddCourseList").hide(300);
      
      return false;
    })
    
    $("#ownCourse").click(function(){
    	$(this).next().submit();
    })
    
    $(".magnifier").click(function(){
    	var id = $(this).next().val();
    	$("#front" + id).show(1000);
    })
    
   

})