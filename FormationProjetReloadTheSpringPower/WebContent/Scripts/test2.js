//var play;

$(document).ready(function () {

  $('#play').on('click',playstop);
  $('#video1').on('play',function () {
    $('#play').html('Pause')

  });
  $('#video1').on('play',function () {
    $('#play').html('Play')

  });
});

function init(){
  document.getElementById('showAntique').addEventListener('click',showAntiqueCars);
  document.getElementById('showNonAntique').addEventListener('click',showNonAntiqueCars);
  document.getElementById('showAll').addEventListener('click',showAll);
  document.getElementById('showNothing').addEventListener('click',showNothing);

  //document.getElementById('play').addEventListener('click',play);
  //play=false;
}


function playstop() {
  console.log("test");

  var media=$('#video1')[0];

  if (media.paused) {
    media.play();


  } else {
    media.pause();

  }

}

function showAntiqueCars() {
  console.log("testss");
  document.getElementById('antiqueCars').className="visible";
  document.getElementById('nonAntiqueCars').className="hidden";

}

function showNonAntiqueCars() {
  document.getElementById('antiqueCars').className="hidden";
  document.getElementById('nonAntiqueCars').className="visible";

}

function showAll() {
  document.getElementById('antiqueCars').className="visible";
  document.getElementById('nonAntiqueCars').className="visible";
}

function showNothing() {
  document.getElementById('antiqueCars').className="hidden";
  document.getElementById('nonAntiqueCars').className="hidden";

}
