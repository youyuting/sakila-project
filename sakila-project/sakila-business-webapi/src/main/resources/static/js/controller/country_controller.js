'use strict';

App.controller('CountryController', ['$scope', 'CountryService', function($scope, CountryService) {
          var self = this;
          self.country={countryId:'', country:''};
          self.countries=[];
          
          self.fetchAllCountries = function(){
        	  CountryService.fetchAllCountries().then(function(res){
        				  console.log('Succes du controller coutry: fetchAllCountries');
        				  self.countries = res;
        			  },
        			  function(err){
        				  console.log('Erreur du controller Contry: fetchAllCountries');
        			  })
          };
          
          
          self.remove = function(id){
        	  CountryService.deleteCountry(id).then(
        			  self.fetchAllCountries,
        			  function(){}
        	  )
          };
          
          self.edit = function(country){
        	  self.country = angular.copy(country);
        	  
          };
          
          self.reset = function(){
        	  self.country={countryId:' ', country:''};
          };
          
          self.submit = function(){
        	if(self.country.countryId == ' '){
        		console.log(self.country)
        		CountryService.addCountry(self.country).then(
        				self.fetchAllCountries,
        				function(){}
        		)
        	}  else {
        		CountryService.updateCountry(self.country).then(
        				self.fetchAllCountries,
        				function(){}
        		)
        	}
          };
          
          self.fetchAllCountries();
}]);