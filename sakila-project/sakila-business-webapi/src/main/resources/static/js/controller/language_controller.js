'use strict';

App.controller('LanguageController', ['$scope', 'LanguageService', function($scope, LanguageService) {
	var self = this;
	self.language={languageId:null,name:''};
	self.languages=[];

	self.fetchAllLanguages = function(){
		LanguageService.fetchAllLanguages()
		.then(
				function(d) {
					self.languages = d;
				},
				function(errResponse){
					console.error('Error while fetching Currencies');
				}
		);
	};

	self.createLanguage = function(language){
		LanguageService.createLanguage(language)
		.then(
				self.fetchAllLanguages, 
				function(errResponse){
					console.error('Error while creating Language.');
				}	
		);
	};

	self.updateLanguage = function(language){
		LanguageService.updateLanguage(language)
		.then(
				self.fetchAllLanguages, 
				function(errResponse){
					console.error('Error while updating Language.');
				}	
		);
	};

	self.deleteLanguage = function(languageId){
		LanguageService.deleteLanguage(languageId)
		.then(
				self.fetchAllLanguages, 
				function(errResponse){
					console.error('Error while deleting Language.');
				}	
		);
	};

	self.fetchAllLanguages();

	self.submit = function() {
		if(self.language.languageId==null){
			console.log('Saving New Language', self.language);    
			self.createLanguage(self.language);
		}else{
			console.log('Language updating with id ', self.language.languageId);
			console.log('Language: ', self.language);
			self.updateLanguage(self.language);
		}
		self.reset();
	};

	self.edit = function(languageId){
		console.log('id to be edited', languageId);
		for(var i = 0; i < self.languages.length; i++){
			if(self.languages[i].languageId == languageId) {
				self.language = angular.copy(self.languages[i]);
				break;
			}
		}
	};

	self.remove = function(languageId){
		console.log('id to be deleted', languageId);
		for(var i = 0; i < self.languages.length; i++){
			if(self.languages[i].languageId == languageId) {
				self.reset();
				break;
			}
		}
		self.deleteLanguage(languageId);
	};


	self.reset = function(){
		self.language={languageId:null,name:''};
		$scope.myForm.$setPristine(); //reset Form
	};

}]);