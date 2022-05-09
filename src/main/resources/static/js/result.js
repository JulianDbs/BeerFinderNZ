window.onload = function() {
	const productMenu = document.getElementById("product-menu");
	const productList = document.getElementById("product-list");
	const productItems = document.getElementsByClassName("product-item");

	const toProductItem = (productId)=> {
		productMenu.style.display = "none";
		productList.style.display = "flex";
		for (let i = 0; i < productItems.length; i++) {productItems[i].style.display = "none";}
		document.getElementById(productId).style.display = "flex";
	};

	const showProductMenu = ()=> {
		productMenu.style.display = "flex";
	}

	const hideProductList = ()=> {
		productList.style.display = "none";
		for (let i = 0; i < productItems.length; i++) {productItems[i].style.display = "none";}
	};

	if (productMenu != null && productList != null && productList != null) {
		const productIconOpenButtons = productMenu.getElementsByClassName("product-icon-button");
		for (let i = 0; i < productIconOpenButtons.length; i++) {
			productItems[i].id = ("product-item-" + i);
			productIconOpenButtons[i].addEventListener("click", (e)=> {
				toProductItem("product-item-" + i);
			});
		}

		const productDescriptionGoBackButtons = document.getElementsByClassName("product-description-button");
		for (let i = 0; i < productDescriptionGoBackButtons.length; i++) {
			productDescriptionGoBackButtons[i].addEventListener("click", (e)=> {
				hideProductList();
				showProductMenu();
			});
		}
	}
};

