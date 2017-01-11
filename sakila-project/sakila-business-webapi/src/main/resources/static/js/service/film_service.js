'use strict';

App.factory('FilmService', ['$http', '$q', function($http, $q){
	return {
		fetchAllFilms: function(){
			console.log('Using the Rental Service: getFilm.');
			return $http.get('http://localhost:8080/getFilms/').then(
					function(res){
						console.log('Sucess du service, getFilm ');
						return res.data;
					},
					function(err){
						console.log('Erreur du service, getFilm ');
						return $q.reject(err);
					}
			);
		},
		getRentalForCustomer: function(customerId){
			console.log('Using the Rental Service: getRentalForCustomer.');
			return $http.get('http://localhost:8080/getRentalAndFilmByCustomerId/' + customerId).then(
					function(res){
						console.log('Sucess du service, getRentalForCustomer');
						return res.data
					},
					function(err){
						console.log('Erreur du service, getRentalForCustomer');
						return $q.reject(err);
					}
			);
		},
		createFilm: function(film){
			console.log('Using the Rental Service: creatFilm.');
			return  $http.post('http://localhost:8080/createFilm/', film).then(
					function(res){
						console.log('Sucess du service, creatFilm ');
						console.log(res.data)
						return res.data;
					},
					function(err){
						console.log('Erreur du service, creatFilm ');
						console.log(err);
						return $q.reject(err);
					}
			);
		},
		deleteFilm: function(id){
			console.log('Using the Rental Service: deleteFilm.');
			return $http.post('http://localhost:8080/deleteFilm/' + id).then(
					function(res){
						console.log('Sucess du service, deleteFilm ');
						return res.data;
					},
					function(err){
						console.log('Erreur du service, deleteFilm ');
						return $q.reject(err);
					}
			);
		},
		updateFilm: function(film){
			console.log('Using the Rental Service: updateFilm.');
			return $http.post('http://localhost:8080/updateFilm/', film).then(
					function(res){
						console.log('Sucess du service, updateFilm ');
					},
					function(err){
						console.log('Erreur du service, updateFilm ');
						return $q.reject(err);
					}
			);
		}
	}
}]);