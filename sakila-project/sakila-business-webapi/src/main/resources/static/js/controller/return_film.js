App.controller('GiveBackController', ['$scope', 'RentalService', 'FilmService',  
                                    function($scope, RentalService, FilmService) {
	var self = this;
	self.film = {
		filmId:null, title:'', description:'',
		releaseYear:'', rentalDuration:'', rentalRate:'',
		lenght:'', language:null, features:'', actors:'', categories:''
	};
	
	self.customer = {
		customerId: null, firstName:'', lastName:'',
		email:'', phone:'', address:'', address2:'',
		district:'', cityId:'', postalCode:'',active:null
	};
	
	self.films = [];
	self.customers = [];
	
	self.fetchRentals = function(customerId){
		FilmService.getRentalForCustomer(customerId).then(
			function(res){
				self.films = res;
			},
			function(err){
				console.log('Some erro occures');
			}
		);
	};
	
	self.change = function(customerId){
		self.fetchRentals(customerId);
	};
	
	self.setRental = function(film){
		self.film = film;
	};
	
	self.submit = function(){
		self.film['returnDate'] = new Date().toISOString().slice(0, 10);
		console.log(self.film);
		RentalService.deleteRental(self.film).then(
				function(){
					console.log('Sucess de la récupération du DVD');
				},
				function(err){
					console.log('Err...')
				}
		);
	};
	
}]);