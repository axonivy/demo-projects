let iconsCssSI = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("streamlineicons.css"))[0];
let icons = [];
if(iconsCssSI){ 
  icons = Object.values(iconsCssSI.rules).filter(rule => rule.selectorText?.startsWith(".si-"));
  icons.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
}
let iconsCssFA = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("font-awesome")).filter(sheet=>!sheet.href?.includes("shim"))[0];
if(iconsCssFA){ 
	let iconsFA = Object.values(iconsCssFA.rules).filter(rule => rule.selectorText?.startsWith(".fa-"));
	iconsFA.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
	icons = icons.concat(iconsFA);
}
let iconsCssPI = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("primeicons.css"))[0];
if(iconsCssPI){ 
	let iconsPI = Object.values(iconsCssPI.rules).filter(rule => rule.selectorText?.startsWith(".pi-"));
	iconsPI.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
	icons = icons.concat(iconsPI);
}

var element = document.getElementById("icons");
element.innerHTML = '';

icons.forEach(icon => {
  var iconClass = icon.selectorText.substring(1, icon.selectorText.length - 8);
  var iconPrefix = iconClass.substring(0,2)+" ";
  const iconClasses = iconClass.split(", ");//comma separated list of icons 
  iconClass = iconClasses[iconClasses.length-1];//use last found css class in comma separated list
  iconClass = iconClass.split("::")[0];//use everything before ::
  iconClass = iconClass.replaceAll(".","");//remove dots in style name
  if(iconClass.length<4){//some non-valid icon classes
	return;
  }
  var iconImage = document.createElement("i");
  iconImage.className = iconPrefix  + iconClass;

  var iconTitle = document.createElement("p");
  iconTitle.appendChild(document.createTextNode(iconPrefix + iconClass));
  
  var iconDiv = document.createElement("div");
  iconDiv.className = "col icon-block";
  iconDiv.appendChild(iconImage)
  iconDiv.appendChild(iconTitle);
  element.appendChild(iconDiv);
})

;

function search() {
  var filter = document.querySelector("#search input").value;
  console.log(filter)
  document.querySelectorAll(".icon-block").forEach(i => {
    if (i.querySelector("p").textContent.includes(filter)) {
      i.style.display = "block";
    }
    else {
      i.style.display = "none";
    }
  });
}
