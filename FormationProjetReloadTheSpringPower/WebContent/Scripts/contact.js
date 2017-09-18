
function initContact(){
  $('#btnSubmit').on('click',submitTheForm);
  console.log("test");

}
function submitTheForm() {
  console.log("submit contact form");

  $('#contactForm').submit();
  
}
