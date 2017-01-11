'use strict';

App.controller('RentalController', ['$scope', '$cookieStore', 'RentalService', 'FilmService',
                                    function($scope, $cookieStore, RentalService, FilmService) {
	var self = this;
	self.currentRentalName = ''
    self.rental={rentalId:null, rentalDate:'',customerId:'',returnDate:'',staffId:'', inventoryId:''};
    self.customer={customerId:null,firstName:'',lastName:'',email:'',phone:'',
			address:'',address2:'',district:'',cityId:'',postalCode:'',active:null};
    self.rentals=[];
    self.customers=[];
	
	self.loadRental = function(filmId, filmName){
		self.rental['inventoryId'] = filmId;
		self.rental['staffId'] = $cookieStore.get('staff_id');
		self.currentRentalName = filmName;
		
	};
	
	self.saveRental = function(rental){
		RentalService.createRental(rental)
		.then(
				function(){},
				function(errResponse){
					console.error('Error while recording Rental.');
				}	
			);	
	};
	
	self.submit = function(){
		if(self.rental.inventoryId != null){
			var rent = angular.copy(self.rental);
			if(rent.rentalDate.length > 5){
				rent.rentalDate = rent.rentalDate+" 00:00:00"
			} else {
				rent.rentalDate = null;
			}
			if(rent.returnDate.length > 5){
				rent.returnDate = rent.returnDate+" 00:00:00"
			} else {
				rent.returnDate = null;
			}
			rent['staffId'] =  $cookieStore.get('staff_id');
			self.saveRental(rent);	
		}
	};
	
}]);