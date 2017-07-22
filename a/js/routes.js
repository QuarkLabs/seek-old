angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    

      .state('tabsController.search', {
    url: '/searchpage',
    views: {
      'tab1': {
        templateUrl: 'templates/search.html',
        controller: 'searchCtrl'
      }
    }
  })

  .state('tabsController.map', {
    url: '/mappage',
    views: {
      'tab2': {
        templateUrl: 'templates/map.html',
        controller: 'mapCtrl'
      }
    }
  })

  .state('tabsController.inbox', {
    url: '/inboxpage',
    views: {
      'tab3': {
        templateUrl: 'templates/inbox.html',
        controller: 'inboxCtrl'
      }
    }
  })

  .state('tabsController.profile', {
    url: '/profilepage',
    views: {
      'tab4': {
        templateUrl: 'templates/profile.html',
        controller: 'profileCtrl'
      }
    }
  })

  .state('tabsController', {
    url: '/page1',
    templateUrl: 'templates/tabsController.html',
    abstract:true
  })

  .state('settings', {
    url: '/page7',
    templateUrl: 'templates/settings.html',
    controller: 'settingsCtrl'
  })

  .state('contactUS', {
    url: '/page8',
    templateUrl: 'templates/contactUS.html',
    controller: 'contactUSCtrl'
  })

  .state('about', {
    url: '/page9',
    templateUrl: 'templates/about.html',
    controller: 'aboutCtrl'
  })

$urlRouterProvider.otherwise('/page1/mappage')


});