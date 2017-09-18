//var play;

$(document).ready(function () {

  $('#play').on('click',playstop);
  $('#video1').on('play',function () {
    $('#play').html('Pause')

  });
  $('#video1').on('pause',function () {
    $('#play').html('Play')

  });

  

  //$('#rangeControl').on('change')
});


function playstop() {
  console.log("test");

  var media=$('#video1')[0];

  if (media.paused) {
    media.play();


  } else {
    media.pause();

  }

}
