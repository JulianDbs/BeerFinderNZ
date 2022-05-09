const setLocalStorageItems = ()=> {
	localStorage.setItem("app-isle", "");
	localStorage.setItem("app-region", "");
	localStorage.setItem("app-location", "");
	localStorage.setItem("app-brand", "");
}

const stepTo = (from, to)=> {from.style.display = "none"; to.style.display = "flex";}

const stringToArray = (s) => {return (s != null) ? s.substring(1, (s.length - 1) ).split(",") : [];}

const asyncFetch = async (name, value, to) => {
	let result = "";
	let data = {};
	data[name] = value;
	const host = to;
	const settings = {
		method: "POST",
                headers : {
                        "Accept": "application/json",
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
	};
	const fetchResponse = await fetch(host, settings);
	if (fetchResponse.status == 200) {
		const dataResponse = await fetchResponse.text();
		result = dataResponse;
	} else {
		throw "error";
	}
	return result;
};


const buildRegionList = (region)=> {
	let span = document.createElement("span");
	span.className = "tab-option";
	span.setAttribute("region", region);
	span.textContent = region;
	span.addEventListener("click", (e)=> {
		let currentOption = document.getElementById("region-tab-current-option");
                currentOption.dataset.region = region;
                currentOption.textContent = region;
		let select = document.getElementById("region-tab-option-select");
                if (!select.contains(e.target)) {
                        select.classList.remove("open");
                }
	});
	document.getElementById("region-tab-options").appendChild(span);
}

const buildLocationList = (city)=> {
	let span = document.createElement("span");
	span.className = "tab-option";
	span.setAttribute("city", city);
	span.textContent = city;
	span.addEventListener("click", (e)=> {
		let currentOption = document.getElementById("location-tab-current-option");
                currentOption.dataset.city = city;
		currentOption.textContent = city;
                let select = document.getElementById("location-tab-option-select");
                if (!select.contains(e.target)) {
                        select.classList.remove("open");
                }
	});
	document.getElementById("location-tab-options").appendChild(span);
}

const showErrorTab = (title, message)=> {
	document.getElementById("error-tab-title").textContent = title;
	document.getElementById("error-tab-message").textContent = message;
	document.getElementById("error-tab").style.display = "flex";
}

window.onload = function() {
	const loadingTab = document.getElementById("loading-tab");
	const isleTab = document.getElementById("isle-tab");
	const regionTab = document.getElementById("region-tab");
	const locationTab = document.getElementById("location-tab");
	const brandTab = document.getElementById("brand-tab");
	const formTab = document.getElementById("form-tab");
	const isleTabList = document.getElementById("isle-tab-list");
	const locationTabList = document.getElementById("location-tab-list");
	const isleStep = document.getElementById("isle-step");
	const regionStep = document.getElementById("region-step");
	const locationStep = document.getElementById("location-step");
	const brandStep = document.getElementById("brand-step");
	const loadingTabOn = (on)=> {
		loadingTab.style.display = (on)? "flex" : "none";
		document.getElementById("loading-tab-external").classList.toggle("on");
		document.getElementById("loading-tab-internal").classList.toggle("on");
	}

	const loadRegions = (isle, to) => {
		let result = [];
		let promise = new Promise( (response, reject) => {
			try {
				response(asyncFetch("isle", isle, "/getRegions"));
			} catch(error) {
				reject(error);
			}
		});
		promise.then(
			(response) => {
				result = stringToArray(response);
				if (result.length > 0) {
					let currentOption = document.getElementById("region-tab-current-option");
					currentOption.dataset.region = result[0];
					currentOption.textContent = result[0];
					for (let i = 0; i < result.length; i++)  { buildRegionList(result[i]); }
					loadingTabOn(false);
					to.style.display = "flex";
				} else {
					loadingTabOn(false);
					showErrorTab("Regions not found", "There are not regions available on this isle");
				}
			}, (reject) => {
				loadingTabOn(false);
				showErrorTab("Connection Error", "App unable to perform POST request");
			}
		);
	};

	const setIsleTabButtonsEvent = ()=> {
		let tabOptions = isleTab.getElementsByClassName("tab-button");
		for (let i = 0; i < tabOptions.length; i++) {
			tabOptions[i].addEventListener("click", ()=> {
				let isle = tabOptions[i].id;
				localStorage.setItem("app-isle", isle);
				isleTab.style.display = "none";
				isleStep.style.visibility = "visible";
				loadingTabOn(true);
				if (isle != null) {
					loadRegions(isle, regionTab);
				} else {
					loadingTabOn(false);
					showErrorTab("Internal Error", "App handle null values");
				}
			}, false);
		}
	}

	const loadLocations = (region, to) => {
		let result = [];
		let promise = new Promise( (response, reject) => {
			try {
				response(asyncFetch("region", region, "/getLocations"));
			} catch (error) {
				reject(error);
			}
		});
		promise.then(
			(response) => {
				result = stringToArray(response);
				if (result.length > 0) {
					let currentOption = document.getElementById("location-tab-current-option");
					currentOption.dataset.city = result[0];
					currentOption.textContent = result[0];
					for (let i = 0; i < result.length; i++)  { buildLocationList(result[i]); }
					loadingTabOn(false);
					to.style.display = "flex";
				} else {
					loadingTabOn(false);
					showErrorTab("Locations not found", "There are not locations available on this region");
				}
			}, (reject) => {
				loadingTabOn(false);
				showErrorTab("Connection Error", "App unable to perform POST request");
			}
		);
	}

	const setTabButtonsEvents = ()=> {
		setIsleTabButtonsEvent();
		document.getElementById("region-tab-button-goback").addEventListener("click", ()=> {
			regionTab.style.display = "none";
			loadingTabOn(true);
			isleStep.style.visibility = "hidden";
			let currentOption = document.getElementById("region-tab-current-option");
			currentOption.dataset.location = "";
			currentOption.textContent = "";
			let regionTabOptions = document.getElementById("region-tab-options");
			while(regionTabOptions.firstChild) {regionTabOptions.removeChild(regionTabOptions.lastChild);}
			loadingTabOn(false);
			isleTab.style.display = "flex";

		});
		document.getElementById("region-tab-button-next").addEventListener("click", (e)=> {
			let region = document.getElementById("region-tab-current-option").dataset.region;
			localStorage.setItem("app-region", region);
			regionTab.style.display = "none";
			regionStep.style.visibility = "visible";
			loadingTabOn(true);
			loadLocations(region, locationTab);
		});
		document.getElementById("location-tab-button-goback").addEventListener("click", (e)=> {
			locationTab.style.display = "none";
			loadingTabOn(true);
			regionStep.style.visibility = "hidden";
			let currentOption = document.getElementById("location-tab-current-option");
			currentOption.dataset.city = "";
			currentOption.textContent = "";
			let locationTabOptions = document.getElementById("location-tab-options");
			while(locationTabOptions.firstChild) {locationTabOptions.removeChild(locationTabOptions.lastChild);}
			loadingTabOn(false);
			regionTab.style.display = "flex";

		});
		document.getElementById("location-tab-button-next").addEventListener("click", (e)=> {
			localStorage.setItem("app-location", document.getElementById("location-tab-current-option").dataset.city);
			locationTab.style.display = "none";
			locationStep.style.visibility = "visible";
			brandTab.style.display = "flex";
		});
		document.getElementById("brand-tab-button-goback").addEventListener("click", (e)=> {
			let region = localStorage.getItem("app-region");
			brandTab.style.display = "none";
			locationStep.style.visibility = "hidden";
			loadingTabOn(true);
			loadLocations(region, locationTab);
		});
		document.getElementById("brand-tab-button-next").addEventListener("click", (e)=> {
			if (document.getElementById("brand-tab-input").value.length >= 4 ) {
				brandTab.style.display = "none";
				loadingTabOn(true);
				brand = document.getElementById("brand-tab-input").value;
				localStorage.setItem("app-brand", brand);
				document.getElementById("form-label-isle").textContent = localStorage.getItem("app-isle");
				document.getElementById("form-label-isle").value = localStorage.getItem("app-isle");
				document.getElementById("form-label-region").textContent = localStorage.getItem("app-region");
				document.getElementById("form-label-region").value = localStorage.getItem("app-region");
				document.getElementById("form-label-location").textContent = localStorage.getItem("app-location");
				document.getElementById("form-label-location").value = localStorage.getItem("app-location");
				document.getElementById("form-label-brand").textContent = localStorage.getItem("app-brand");
				document.getElementById("form-label-brand").value = localStorage.getItem("app-brand");
				loadingTabOn(false);
				brandStep.style.visibility = "visible";
				formTab.style.display = "flex";
			}
		});
		document.getElementById("form-tab-button-goback").addEventListener("click", (e)=> {
			formTab.style.display = "none";
			loadingTabOn(true);
			loadingTabOn(false);
			brandStep.style.visibility = "hidden";
			brandTab.style.display = "flex";
		});
		document.getElementById("form-tab-button-search").addEventListener("click", (e)=> {
			formTab.style.display = "none";
			loadingTabOn(true);
		});

	}
	setLocalStorageItems();
	setTabButtonsEvents();
}

function checkInputLength() {
	document.getElementById("brand-tab-button-next").disable = (document.getElementById("brand-tab-input").value.length >=4)? true : false;
}
