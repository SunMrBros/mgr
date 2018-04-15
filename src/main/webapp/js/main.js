fn();

function fn() {
	var winWidth = window.innerWidth;
	// alert(winWidth)
	var w = window.innerWidth / 7.5;
	var html = document.querySelector("html");
	if(winWidth > 750) {
		html.style.fontSize = 100 + "px";
	} else {
		html.style.fontSize = w + "px";
	}
}