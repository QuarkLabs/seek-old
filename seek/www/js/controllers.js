/* global angular, document, window */
'use strict';

angular.module('starter.controllers', [])



.controller('AppCtrl', function($scope, $ionicModal, $ionicPopover, $timeout) {
    // Form data for the login modal
    $scope.loginData = {};
    $scope.isExpanded = false;
    $scope.hasHeaderFabLeft = false;
    $scope.hasHeaderFabRight = false;

    var navIcons = document.getElementsByClassName('ion-navicon');
    for (var i = 0; i < navIcons.length; i++) {
        navIcons.addEventListener('click', function() {
            this.classList.toggle('active');
        });
    }

    ////////////////////////////////////////
    // Layout Methods
    ////////////////////////////////////////

    $scope.hideNavBar = function() {
        document.getElementsByTagName('ion-nav-bar')[0].style.display = 'none';
    };

    $scope.showNavBar = function() {
        document.getElementsByTagName('ion-nav-bar')[0].style.display = 'block';
    };

    $scope.noHeader = function() {
        var content = document.getElementsByTagName('ion-content');
        for (var i = 0; i < content.length; i++) {
            if (content[i].classList.contains('has-header')) {
                content[i].classList.toggle('has-header');
            }
        }
    };

    $scope.setExpanded = function(bool) {
        $scope.isExpanded = bool;
    };

    $scope.setHeaderFab = function(location) {
        var hasHeaderFabLeft = false;
        var hasHeaderFabRight = false;

        switch (location) {
            case 'left':
                hasHeaderFabLeft = true;
                break;
            case 'right':
                hasHeaderFabRight = true;
                break;
        }

        $scope.hasHeaderFabLeft = hasHeaderFabLeft;
        $scope.hasHeaderFabRight = hasHeaderFabRight;
    };

    $scope.hasHeader = function() {
        var content = document.getElementsByTagName('ion-content');
        for (var i = 0; i < content.length; i++) {
            if (!content[i].classList.contains('has-header')) {
                content[i].classList.toggle('has-header');
            }
        }

    };

    $scope.hideHeader = function() {
        $scope.hideNavBar();
        $scope.noHeader();
    };

    $scope.showHeader = function() {
        $scope.showNavBar();
        $scope.hasHeader();
    };

    $scope.clearFabs = function() {
        var fabs = document.getElementsByClassName('button-fab');
        if (fabs.length && fabs.length > 1) {
            fabs[0].remove();
        }
    };
})

.controller('LoginCtrl', function($scope, $timeout, $stateParams, ionicMaterialInk) {
    $scope.$parent.clearFabs();
    $timeout(function() {
        $scope.$parent.hideHeader();
    }, 0);
    ionicMaterialInk.displayEffect();
})

.controller('MapCtrl', function($scope, $stateParams, $timeout, ionicMaterialInk, ionicMaterialMotion) {
    // Set Header
    $scope.$parent.showHeader();
    $scope.$parent.clearFabs();
    $scope.$parent.setHeaderFab('left');

    // Delay expansion
    $timeout(function() {
        $scope.isExpanded = true;
        $scope.$parent.setExpanded(true);
    }, 300);


    // Set Motion
    ionicMaterialMotion.fadeSlideInRight();

    // Set Ink
    ionicMaterialInk.displayEffect();

})

.controller('MapCtrl', function($scope, $ionicPopup) {
$scope.showConfirm = function() {
   var confirmPopup = $ionicPopup.confirm({
     title: 'Meet',
     template: 'Are you sure you want to meet this person?',
     cancelText: 'No',
     okText: 'Yes'
   });

 
   confirmPopup.then(function(res) {
     if(res) {
       console.log('Request sent!');
     } else {
       console.log('Meeting canceled !');
     }
   });
 };
 
})



.directive('map', function() {
    return {
        restrict: 'A',
        link:function(scope, element, attrs){

          var zValue = scope.$eval(attrs.zoom);
          var lat = scope.$eval(attrs.lat);
          var lng = scope.$eval(attrs.lng);


          var myLatlng = new google.maps.LatLng(lat,lng),
          mapOptions = {
              zoom: zValue,
              center: myLatlng
          },
          map = new google.maps.Map(element[0],mapOptions),
          marker = new google.maps.Marker({
			    position: myLatlng,
			    map: map,
			    draggable:true
		  });

		  google.maps.event.addListener(marker, 'dragend', function(evt){
		    scope.$parent.user.latitude = evt.latLng.lat();
		    scope.$parent.user.longitude = evt.latLng.lng();
		    scope.$apply();
		  });


//~ showConfirm = function() {
   //~ var confirmPopup = $ionicPopup.confirm({
     //~ title: 'Meet',
     //~ template: 'Are you sure you want to meet this person?',
     //~ cancelText: 'No',
     //~ okText: 'Yes'
   //~ });

 
   //~ confirmPopup.then(function(res) {
     //~ if(res) {
       //~ console.log('Request sent!');
     //~ } else {
       //~ console.log('Meeting canceled !');
     //~ }
   //~ });
 //~ };

 
    var locations = [
      ['Sumedhe Dissanayake', 6.903186, 79.862185, 4, '../img/sumedhe_icon.png'],
      ['Pramodya Abeysighe', 6.901695, 79.861659, 5, '../img/pramodya_icon.png'],
      ['Oshan Ivantha', 6.903662, 79.858868, 3, '../img/oshan_icon.png'],
      ['Supul Dulanka', 6.901979, 79.859775, 2, '../img/supul_icon.png'],
      ['You', 6.902230, 79.861323, 1],
    ];

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: new google.maps.LatLng(6.902230, 79.861323),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;



    for (i = 0; i < locations.length; i++) { 
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        icon: locations[i][4],
        map: map
      })
      ;

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }


        }
    };


 
})

////

.controller('ProfileCtrl', function($scope, $stateParams, $timeout, ionicMaterialMotion, ionicMaterialInk) {
    // Set Header
    $scope.$parent.showHeader();
    $scope.$parent.clearFabs();
    $scope.isExpanded = false;
    $scope.$parent.setExpanded(false);
    $scope.$parent.setHeaderFab(false);

    // Set Motion
    $timeout(function() {
        ionicMaterialMotion.slideUp({
            selector: '.slide-up'
        });
    }, 300);

    $timeout(function() {
        ionicMaterialMotion.fadeSlideInRight({
            startVelocity: 3000
        });
    }, 700);

    // Set Ink
    ionicMaterialInk.displayEffect();
})

.controller('QuestionsCtrl', function($scope, $stateParams, $timeout, ionicMaterialMotion, ionicMaterialInk) {
    $scope.$parent.showHeader();
    $scope.$parent.clearFabs();
    $scope.isExpanded = true;
    $scope.$parent.setExpanded(true);
    $scope.$parent.setHeaderFab('right');

    $timeout(function() {
        ionicMaterialMotion.fadeSlideIn({
            selector: '.animate-fade-slide-in .item'
        });
    }, 200);

    // Activate ink for controller
    ionicMaterialInk.displayEffect();
})

.controller('GalleryCtrl', function($scope, $stateParams, $timeout, ionicMaterialInk, ionicMaterialMotion) {
    $scope.$parent.showHeader();
    $scope.$parent.clearFabs();
    $scope.isExpanded = true;
    $scope.$parent.setExpanded(true);
    $scope.$parent.setHeaderFab(false);

    // Activate ink for controller
    ionicMaterialInk.displayEffect();

    ionicMaterialMotion.pushDown({
        selector: '.push-down'
    });
    ionicMaterialMotion.fadeSlideInRight({
        selector: '.animate-fade-slide-in .item'
    });

})






.controller('MainCtrl', function($scope, $ionicPopup) {
$scope.showPopup = function() {
$scope.data = {};
$ionicPopup.show({
template: '<input type="username">',
title: 'Enter Username',
subTitle: 'Please Enter Username',
scope: $scope,
buttons: [
{ text: 'Cancel' },
{
text: '<b>Save</b>',
type: 'button-positive',
onTap: function(e) {
// add your action
}
}
]
});
}
});


;
