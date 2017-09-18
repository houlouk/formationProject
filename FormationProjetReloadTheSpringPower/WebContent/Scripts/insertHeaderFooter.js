
var txtInput;
var txtResult;
var header;


//$('#btnSubmit').off('click',increment);


function submitTheForm() {
  $('#myForm').submit();

}

function init() {
  $('#myButton').on('click',submitTheForm);
  $('input[name="comment"]').focus();
  console.log("test");


  header=$('header').html();
  //console.log(header);
  $.get("/banniere",function(data){
           $('header').html($(data).find('#outBanniere').html()+header);
  }

);








  $('footer').load("../jsp/footer.html");
  txtInput=$("#txtInput");/*document.getElementById('txtInput');*/
  txtResult=$("#txtResult");/*"document.getElementById('txtResult');*/
  console.log(txtInput.value);

  $('#btnSubmit').on('click',increment);
  console.log("test 2");
  clear();
}

function clear() {
  txtInput.val('3');
  txtResult.val('3');
  console.log(txtInput.val());

}

function increment() {
  console.log("test");

  console.log(txtInput.value);
  txtInput.val((Number(txtInput.val())+1));
  txtResult.val((Number(txtResult.val())*2));


}
