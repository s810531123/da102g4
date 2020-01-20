$(document).ready(function() {
	
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#cou_sdate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#cou_edate').val()?$('#cou_edate').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#cou_edate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#cou_sdate').val()?$('#cou_sdate').val():false
	   })
	  },
	  timepicker:false
	 });
});


$('.stdate').datetimepicker({
 	  format:'Y-m-d',
 	  onShow:function(){
 	   this.setOptions({
 	    maxDate:$('.edate').val()?$('.edate').val():false
 	   })
 	  },
 	  timepicker:false
 	 });
 	 
 	 $('.edate').datetimepicker({
 	  format:'Y-m-d',
 	  onShow:function(){
 	   this.setOptions({
 	    minDate:$('.stdate').val()?$('.stdate').val():false
 	   })
 	  },
 	  timepicker:false
 	 });


 	 
 	 
 	 ///////////////////////////////////////
 	 
 	 $(".CourseCancel").click(function(){
      $(".updateAddCourse").hide(300);
      $(".updateAddCourseList").hide(300);
      
      return false;
    })

    

    $("#addCourse").click(function(){
      $(".insertCourse").show(1000);
    })
     
    $(".updatePic").change(function(){
        readUpdateURL(this);
        tt = $(this).next().val();
        $("#upPic"+tt).show(1000);
    });

    $("#addPic").change(function(){
        readAddURL(this);
        $("#displayAddPic").show(1000);
    });

//    $(".arrow").click(function(){
//
//    	$(this).next().submit();
//    })
    
    
     $(".magnifier1").click(function(){
    	var t = $(this).next().val();
    	$("#"+t).show(1000);
    
    });
    
    $(".children").click(function(){
    	var c = $(this).next().val();
    	$("#cl"+c).show(1000);
    })
 
    /////////////////////////////////////
    /////////////////////////////////////
    
    function readUpdateURL(input){
        if(input.files && input.files[0]){
          var reader = new FileReader();
          reader.onload = function (e) {
        	 
             $("#upPic"+tt).attr('src', e.target.result);
          }
          reader.readAsDataURL(input.files[0]);
        }
      }

      function readAddURL(input){
        if(input.files && input.files[0]){
          var reader = new FileReader();
          reader.onload = function (e) {
             $("#displayAddPic").attr('src', e.target.result);
          }
          reader.readAsDataURL(input.files[0]);
        }
      }
 	 
  });	 