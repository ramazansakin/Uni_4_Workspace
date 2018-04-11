$(function(){
	$("#Commentform").validate({
			rules: {
				txtAuthor: {
					required: true,
					minlength: 3
				},
				txtMessage: {
					required: true,
					minlength: 5
				}
			},
			messages: {
				txtAuthor: {
					required: "Lütfen isminizi giriniz",
					minlength: "İsminiz en az 3 karakter uzunluğunda olmalıdır."
				},
				txtMessage: {
					required: "Lütfen mesaj giriniz",
					minlength: "Mesajınız  en az 5 karakter uzunluğunda olmalıdır."
				}
			}
		});
});