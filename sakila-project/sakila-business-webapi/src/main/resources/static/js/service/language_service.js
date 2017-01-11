'use strict';

App.factory('LanguageService', ['$http', '$q', function($http, $q){

	return {

		fetchAllLanguages: function() {
			return $http.get('http://localhost:8080/getLanguages/')
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching language');
						return $q.reject(errResponse);
					}
			);
		},

		createLanguage: function(language){
			return $http.post('http://localhost:8080/createLanguage/', language)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while creating language');
						return $q.reject(errResponse);
					}
			);
		},

		updateLanguage: function(language, languageId){
			return $http.post('http://localhost:8080/updateLanguage/', language)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating language');
						return $q.reject(errResponse);
					}
			);
		},

		deleteLanguage: function(languageId){
			return $http.get('http://localhost:8080/deleteLanguage/'+languageId)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while deleting language');
						return $q.reject(errResponse);
					}
			);
		}

	};

}]);