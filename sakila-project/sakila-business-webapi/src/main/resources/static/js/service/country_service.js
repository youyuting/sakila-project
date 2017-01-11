'use strict';

App.factory('CountryService', ['$http', '$q', function($http, $q){
	return {
		fetchAllCountries : function(){
			return $http.get('http://localhost:8080/country/').then(
					function(res){
						console.log('Success du service contry: getCountries');
						return res.data;
					},
					function(err){
						console.log('Erreur du service contry: getCountries');
						$q.reject(err);
					}
			);
		},
		addCountry : function(country){
			return $http.post('http://localhost:8080/createCountry/', country).then(
					function(res){
						console.log('Success du service contry: addCountry');
						return res.data;
					},
					function(err){
						console.log('Erreur du service contry: addCountry');
						$q.reject(err);
					}		
			);
		},
		updateCountry : function(country){
			return $http.post('http://localhost:8080/updateCountry/', country).then(
					function(res){
						console.log('Success du service contry: updateCountry');
						return res.data;
					},
					function(err){
						console.log('Erreur du service contry: updateCountry');
						$q.reject(err);
					}		
			);
		},
		deleteCountry: function(countryId){
			return $http.get('http://localhost:8080/deleteCountry/' +countryId).then(
					function(res){
						console.log('Success du service contry: deleteCountry');
						return res.data;
					},
					function(err){
						console.log('Erreur du service contry: deleteCountry');
						$q.reject(err);
					}
					
			);		
		}
	}}
]);