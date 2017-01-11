'use strict';

App.controller('CityController', [
		'$scope',
		'CityService',
		function($scope, CityService) {
			var self = this;
			self.city = {
				cityId : '',
				city : '',
				//countryId : ''
			};
			self.cities = [];

			self.fetchAllCitys = function() {
				CityService.fetchAllCitys().then(function(d) {
					self.cities = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			self.createCity = function(city) {
				console.log(city)
				CityService.addCity(city).then(self.fetchAllCities,
						function(errResponse) {
							console.error('Error while creating City.');
						});
			};

			self.updateCity = function(city) {
				CityService.updateCity(city).then(self.fetchAllCities,
						function(errResponse) {
							console.error('Error while updating City.');
						});
			};

			self.fetchAllCitys();

			self.submit = function() {
				if (self.city.cityId == ' ') {
					console.log('Saving New City', self.city.city);
					console.log('Saving New City', self.city.countryId);
					console.log('Saving New City', self.city);
					self.createCity(self.city);
				} else {
					console.log('City updating with id ', self.city.cityId);
					console.log('City: ', self.city);
					self.updateCity(self.city);
				}
				self.reset();
			};

			self.edit = function(cityId) {
				console.log('id to be edited : ', cityId);
				for (var i = 0; i < self.cities.length; i++) {
					if (self.cities[i].cityId == cityId) {
						self.city = angular.copy(self.cities[i]);
						break;
					}
				}
			};

			/*
			 * self.edit = function(city){ self.city = angular.copy(city);
			 * //self.fetchAllCities();
			 *  };
			 */

			self.reset = function() {
				self.city = {
					cityId : null,
					city : '',
					countryId : ''
				};
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);