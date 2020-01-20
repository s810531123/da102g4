// var coll = $(".collapsible");
// var i;

// for (i = 0; i < coll.length; i++) {
//   coll[i].addEventListener("click", function() {
//     this.classList.toggle("active");
//     var content = this.nextElementSibling;
//     if (content.style.display === "block") {
//       content.style.display = "none";
//     } else {
//       content.style.display = "block";
//     }
//   });
// }


  $(document).ready(function() {
	  
	  var tt=0;
	  
      if (document.getElementsByClassName('card_1')[0]||
        document.getElementsByClassName('cright')[0]||
        document.getElementsByClassName('formBox')[0]) {
        $('.content:eq(0)').slideDown();
      }
    $(".collapsible").click(function() {
        $(this).next().animate({
          height:'toggle'
//    	$(this).next().toggle();
        });
    });  

//    $(".magnifier").click(function(){
//      $(".updateCourse").show(1000);
//    })

   

  });
