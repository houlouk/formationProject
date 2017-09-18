var fun=function () {
  alert("bouh");


}

var pizzaParts = new Array();
pizzaParts[0]="pepperoni";
pizzaParts[1]="oigon";
pizzaParts.push("bacon");
pizzaParts.push("bacon");
pizzaParts.pop();

  console.log(pizzaParts);
  console.log(Math.log(2));

var age = prompt('Entrez votre age','');
if (isNaN(age)) {

    console.log("Entrez un nombre valide");

}

else {
    console.log('vous allez avoir ' + (Number(age)+1)+' ans');
    console.log('Voues êtes nés en ' + (new Date().getFullYear() - (Number(age))+'ans'));
}

switch (true) {
  case isNaN(age):
    age=0;
    console.log('Entrez un nombre valide');


    break;

    case age>=90:
      age=Number(age)+1;
      console.log('Vous êtes agé')


      break;

      case age<=20:
      age=Number(age)+1;
      console.log('Vous êtes jeune')

        break;

}

try {
  fonctionnondefini();
  console.log("succes, donc la fonction existe");
} catch (e) {
  console.log("Une erreur est apparue: "+e.message);
} finally {
  console.log("bloc finally")

}






var carre = function(x){
  return x*x;
}

window.addEventListener('load',winEvent,false);

function winEvent() {
  console.log("chargement de la fenetre");

}

function applyToArray(array,fun) {
  for (var i = 0; i < array.length; i++) {
    array[i]=fun(array[i]);
  }

}

function saveData() {
  console.log("save");

}

var btn=document.getElementById('btnSave');
btn.addEventListener('click',saveData,'false');

btn.onclick = function(){
  var element=document.getElementsByTagName('img');
  for (index = element.length - 1; index >= 0; index--) {
      element[index].parentNode.removeChild(element[index]);
  }
};

var lastName=document.getElementById('txtLastName');
lastName.addEventListener('focus', goFocus,false);

function goFocus() {
  console.log("j'ai le focus");

}


var array = new Array();
var taille=Math.floor(Math.random()*100);

for (var i = 0; i <=taille; i++) {
  array[i] = Math.floor(Math.random()*100);
}

var exp= prompt("Entrez l'expression à appliquer au tableau, la variable est a");

var fun=new Function("a","return " + exp);
 applyToArray(array,fun);
console.log(array);


function taillepartpizza(diametre,nbreParts) {
  return taillePizza(diametre)/nbreParts;
  function taillePizza(diametre) {
    var rayon=diametre/2;
    return 3.14*rayon*rayon;

  }

}

console.log("Une part de pizza fait " + taillepartpizza(50,8))
  class Test {
    constructor(x) {
      this.x=x;
    }
  }

  var test=new Test();
  test.x=23;
  var t=test.x;

  console.log(t);

   prompt=function () {
     return "Bonjour encore";
  }
