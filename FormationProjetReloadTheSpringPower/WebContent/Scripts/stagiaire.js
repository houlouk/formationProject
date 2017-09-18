var app=angular.module('app',[]);

var lastId = 0;

app.controller ('monController',function ($scope) {
  $scope.modify = false;
  $scope.unmodify=true;

  $scope.mesStagiaires = [


  ];

  $scope.ajouterModifierStagiaire = function () {
    lastId++;

    for (var i = 0; i < $scope.mesStagiaires.length; i++) {
      if ($scope.mesStagiaires[i].id == $scope.monStagiaire.id) {
        return;
      }
    }
    $scope.monStagiaire.id=lastId;
    $scope.monStagiaire.modify=false;
    $scope.monStagiaire.unmodify=true;


    $scope.mesStagiaires.push($scope.monStagiaire);
    $scope.monStagiaire={};
  };

  $scope.removeStagiaire = function (id){


  for (var i = 0; i < $scope.mesStagiaires.length; i++) {
    if ($scope.mesStagiaires[i].id == id) {
      $scope.mesStagiaires.splice(i,1);
      break;
    }
  }


  };

  $scope.modifyStagiaire = function (id) {
    if($scope.monStagiaire.unmodify){
      $scope.monStagiaire.modify = true;
      $scope.monStagiaire.unmodify = false;
    }

    else {
      $scope.monStagiaire.unmodify = true;
      $scope.monStagiaire.modify = false;
    }
  };

  $scope.reinitStagiaire = function(){
    $scope.monStagiaire={};
  };


});
