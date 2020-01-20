	$(document).ready(function() {

        $("#main-nav").css("background-color","rgba(173,216,230,1)");
        $(window).scroll(function(){
          var scrollTop= $(this).scrollTop(); 
          if (scrollTop<=4) {
            $("#main-nav").css("background-color","rgba(173,216,230,1)");
          }else{
            $("#main-nav").css("background-color","rgba(173,216,230,0.9)");
          }
        })
           $('#login').click(function(){
            $('#lightBox').toggle();
           })

           $('.login-wrap').click(function(){
          event.stopPropagation();
           })

           $('#lightBox').click(function(){
            $('#lightBox').hide();
           })

         });