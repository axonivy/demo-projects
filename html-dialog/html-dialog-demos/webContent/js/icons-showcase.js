let icons = [];

// Tabler Icons
const iconsCssTi = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("tabler-icons.min.css"))[0];
if(iconsCssTi){ 
  const iconsTI = Object.values(iconsCssTi.rules).filter(rule => rule.selectorText?.startsWith(".ti-"));
  iconsTI.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
  icons = icons.concat(iconsTI);
}

// PrimeIcons
const iconsCssPI = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("primeicons.css"))[0];
if(iconsCssPI){ 
  const iconsPI = Object.values(iconsCssPI.rules).filter(rule => rule.selectorText?.startsWith(".pi-"));
  iconsPI.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
  icons = icons.concat(iconsPI);
}

// Streamline Icons
const iconsCssSI = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("streamlineicons.css"))[0];
if(iconsCssSI){ 
  const iconsSI = Object.values(iconsCssSI.rules).filter(rule => rule.selectorText?.startsWith(".si-"));
  iconsSI.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
  icons = icons.concat(iconsSI);
}

// FontAwesome
const iconsCssFA = Object.values(document.styleSheets).filter(sheet => sheet.href?.toLowerCase()?.includes("font-awesome")).filter(sheet=>!sheet.href?.includes("shim"))[0];
if(iconsCssFA){ 
  const iconsFA = Object.values(iconsCssFA.rules).filter(rule => rule.selectorText?.startsWith(".fa-"));
  iconsFA.sort((a, b) => (a.selectorText > b.selectorText) ? 1 : -1);
  icons = icons.concat(iconsFA);
}

const element = document.getElementById("icons");
element.innerHTML = '';

icons.forEach(icon => {
  let iconClass = icon.selectorText.substring(1, icon.selectorText.length - 8);
  const iconPrefix = iconClass.substring(0,2) + " ";
  const iconClasses = iconClass.split(", ");//comma separated list of icons 
  iconClass = iconClasses[iconClasses.length - 1];//use last found css class in comma separated list
  iconClass = iconClass.split("::")[0];//use everything before ::
  iconClass = iconClass.replaceAll(".", "");//remove dots in style name
  if(iconClass.length < 4){//some non-valid icon classes
    return;
  }
  const iconImage = document.createElement("i");
  iconImage.className = iconPrefix  + iconClass;

  const iconTitle = document.createElement("p");
  iconTitle.appendChild(document.createTextNode(iconPrefix + iconClass));
  
  const iconDiv = document.createElement("div");
  iconDiv.className = "col icon-block";
  iconDiv.appendChild(iconImage)
  iconDiv.appendChild(iconTitle);
  element.appendChild(iconDiv);
});

function search() {
  const filter = document.querySelector("#search input").value;
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
